package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.service.mail.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    ChildIndexer.java
* Date:        6/8/00
* Description: This is an object to be used to maintain a redundant index of any heirarchy.
* It currently only writes to one table, but doesn't care what id's refer to.  
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/


public class ChildIndexer
{
	public static final int CATEGORY = 1;
	public static final int FOLDER   = 2;
	public static final int STDCOLL  = 3;

	public ChildIndexer() 
	{
	}


	private static String getIndexTable(int type)
	{
		String tableName = null;
		switch (type)
		{
			case CATEGORY : tableName = "CATCHILDINDEX"; break;
			case FOLDER   : tableName = "FOLDERCHILDINDEX"; break;
			case STDCOLL  : tableName = "STDCOLLCHILDINDEX"; break;
		}
		return tableName;
	}

	private static String getIndexParent(int type)
	{
		String tableName = "PARENTID";
		switch (type)
		{
			case CATEGORY :
        tableName = "PARENTCATID";
        break;
			case FOLDER:
      case STDCOLL:
        break;
		}
		return tableName;
	}

	private static String getIndexChild(int type)
	{
		String tableName = "CHILDID";
		switch (type)
		{
			case CATEGORY : tableName = "CHILDCATID"; break;
			case FOLDER :
			case STDCOLL :
        break;
		}
		return tableName;
	}
	
	private static String getBaseTable(int type)
	{
		String tableName = null;
		switch (type)
		{
			case CATEGORY: tableName = "CATEGORY"; break;
			case FOLDER:   tableName = "FOLDER"; break;
			case STDCOLL:  tableName = "STANDARDSCOLLECTION"; break;
		}
		return tableName;
	}

	private static String getBaseParent(int type)
	{
		String tableName = null;
		switch (type)
		{
			case CATEGORY : tableName = "PARENTCATID"; break;
			case FOLDER : tableName = "FOLDERID"; break;
			case STDCOLL : tableName = "PARENTCOLLID"; break;
		}
		return tableName;
	}

	private static String getBaseChild(int type)
	{
		String tableName = "OBJECTID";
		return tableName;
	}
	
	/**
	This method will add the necessary rows to the index required to make the child tree
	to the parent node.  The child node does not necessarily need to refer to a leaf node.
	If the parent node is not found in the index, it assumes that the child become a root
	in the forest.
	*/
	public static void addChild(int type, IObjectContext context, long parent, long child)
		throws OculusException
	/*
	When adding N to P:
		Step 1: Add the row (N,N)
		Step 2: Add the rows (P',C) where (P',P) and (N,C).
	*/
	{
		IRConnection conn = null;
		IQueryProcessor qp1 = null;
		IQueryProcessor qp2 = null;
		IQueryProcessor qp3 = null;
		String table = getIndexTable(type);
		String parentCol = getIndexParent(type);
		String childCol = getIndexChild(type);
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);

//		Step 1: Add the row (N,N)
			qp1 = conn.createProcessor();
//			System.err.println("Adding ("+child+", "+child+")");
			qp1.update("INSERT INTO "+table+" ("+parentCol+", "+childCol+") VALUES ("+child+","+child+")");
			
//		Step 2: Add the rows (P',C) where (P',P) and (N,C).
//			In the query, parent represents P, child represents N, PARENT represents P', 
//			CHILD represents C, the clause parentList.CHILDCATID=parent represents the 
//			tuple (P',P), and the clause childList.PARENTCATID=child represents the tupe (N,C)

	if (parent != child)
	{
		qp2 = conn.createProcessor();
			IDataSet ds = qp2.retrieve("SELECT parentList."+parentCol+" AS PARENT, childList."+childCol+" AS CHILD "+
																 "FROM "+table+" parentList, "+table+" childList "+
																 "WHERE parentList."+childCol+"="+parent+" AND childList."+parentCol+"="+child);
			while (ds.next())
			{
				long newParent = ds.getLong("PARENT");		// P'
				long newChild = ds.getLong("CHILD");			// C
				qp3 = conn.createProcessor();
//				System.err.println("Adding ("+newParent+", "+newChild+")");
				qp3.update("INSERT INTO "+table+" ("+parentCol+", "+childCol+") VALUES ("+newParent+","+newChild+")");
			}
	}
		}
		finally
		{
			if (qp1 != null) qp1.close();
			if (qp2 != null) qp2.close();
			if (qp3 != null) qp3.close();
		}
	}
	
	/**
	This method will delete the necessary rows in the index required to DETACH the child tree
	from the parent node.  THIS DOES NOT REPRESENT A DELETE OF THE CHILD.  When used for deleting,
	the child will always be a leaf, and therefore detaching and deleting are equivalent.  When
	used for moving, you don't want to lose the hierarchy under the child and therefore not all
	child rows are deleted. The child node does not necessarily need to refer to a leaf node.
	If the parent node is not found in the index, it assumes that the child being deleted is a root
	in the forest.
	*/
	
	public static void removeChild(int type, IObjectContext context, long parent, long child)
		throws OculusException
/*
	When adding N to P:
		Step 1: Delete the rows (P',C) where (P',P) and (N,C).
		Step 2: Delete the row (N,N)
*/
	{
		IRConnection conn = null;
		IQueryProcessor qp1 = null;
		IQueryProcessor qp2 = null;
		IQueryProcessor qp3 = null;
		String table = getIndexTable(type);
		String parentCol = getIndexParent(type);
		String childCol = getIndexChild(type);
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);

	//		Step 1: Delete the rows (P',C) where (P',P) and (N,C).
	//		See addChild() for further explanation of the query.
		if (parent != child)
		{
			qp2 = conn.createProcessor();
			IDataSet ds = qp2.retrieve("SELECT parentList."+parentCol+" AS PARENT, childList."+childCol+" AS CHILD "+
																 "FROM "+table+" parentList, "+table+" childList "+
																 "WHERE parentList."+childCol+"="+parent+" AND childList."+parentCol+"="+child);
			while (ds.next())
			{
				long newParent = ds.getLong("PARENT");		// P'
				long newChild = ds.getLong("CHILD");			// C
				qp3 = conn.createProcessor();
				qp3.setSingleton(false);
				qp3.update("DELETE "+table+" WHERE "+parentCol+"="+newParent+" AND "+childCol+"="+newChild);
			}
		}

	//		Step 2: Delete the row (N,N)
			qp1 = conn.createProcessor();
			qp1.setSingleton(false);	
			qp1.update("DELETE "+table+" WHERE "+parentCol+"="+child+" AND "+childCol+"="+child);
			

		}
		finally
		{
			if (qp1 != null) qp1.close();
			if (qp2 != null) qp2.close();
			if (qp3 != null) qp3.close();
		}
	}


	/**
	This method acts just as expected and handles the moving of one tree to another.
	The parameter represent the old parent node of the tree, the new parent node of the
	tree, and the top node of the tree being moved.  
	*/
	public static void moveChild(int type, IObjectContext context, long parentOld, long parentNew, long child)
		throws OculusException
	{
//		System.err.println(parentOld+", "+parentNew+", "+child);
		ChildIndexer.removeChild(type,context,parentOld,child);
		ChildIndexer.addChild(type,context,parentNew,child);
	}

	/**
	Wipes the entire index.
	*/
	public static void wipeIndex(int type, IObjectContext context)
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		String table = getIndexTable(type);
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);
			qp = conn.createProcessor();
			qp.setSingleton(false);
			qp.update("DELETE FROM "+table+"");
		}
		finally
		{
			if (qp != null) qp.close();
		}
	}

	/**
	Reindexes the entire system.
	*/
	public static void reindex(int type, IObjectContext context)
		throws OculusException
	{
		wipeIndex(type,context);
		reindexType(type,context);
	}

	/**
	Reindexes all of the categories
	*/
	public static void reindexType(int type, IObjectContext context)
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		String table = getBaseTable(type);
		String parentCol = getBaseParent(type);
		String childCol = getBaseChild(type);
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);
			qp = conn.createProcessor();
			IDataSet ds = qp.retrieve("SELECT "+childCol+" FROM "+table+" WHERE "+childCol+"="+parentCol+"");
			while (ds.next())
			{
				long defaultCatID = ds.getLong(childCol);
				addChild(type,context,defaultCatID,defaultCatID);
				reindexChild(type,context,defaultCatID);
			}
		}
		finally
		{
			if (qp != null) qp.close();
		}
	}

	/**
	Reindexes the specified category.
	*/
	public static void reindexChild(int type, IObjectContext context, long parentid)
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		String table = getBaseTable(type);
		String parentCol = getBaseParent(type);
		String childCol = getBaseChild(type);
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);
			qp = conn.createProcessor();
			IDataSet ds = qp.retrieve("SELECT "+childCol+" FROM "+table+" WHERE "+childCol+"<>"+parentCol+" AND "+parentCol+"="+parentid);
			while (ds.next())
			{
				long defaultCatID = ds.getLong(childCol);
				addChild(type,context,parentid,defaultCatID);
				reindexChild(type,context,defaultCatID);
			}
		}
		finally
		{
			if (qp != null) qp.close();
		}
	}


	public static void main(String args[])
	{
		try
		{
			out("Starting ChildIndexer");
			IObjectContext context = new ObjectContext();
			out("Connecting to database...");
			ICRMConnection conn = context.getCRM().connect("admin",Security.encrypt("1234"));
			out("Connected.");
			context.setConnection(conn);

//			reindex(context);
//			dumpTree(context);
			reindexTest(FOLDER,context);
//			postIndexManipTest(CATEGORY,context);
//			unitTest(context);

			out("Closing connection.");
		}
		catch (Throwable ocuExp)
		{
			ocuExp.printStackTrace(System.err);
		}
		finally
		{
			out("Ending ChildIndexer");
			System.exit(1);
		}
	}
	
	
	public static void unitTest(IObjectContext context)
		throws OculusException
	{
		TransactionMgr.getInstance().getTransaction(context).startTransaction();

		int type = CATEGORY;

		out("Start out empty..");
		wipeIndex(type,context);
		dumpIndex(type,context);

		out("Add C1 to root");
		addChild(type,context,802,802);
		dumpIndex(type,context);

		out("Add C11 to C1");
		addChild(type,context,802,851);
		dumpIndex(type,context);

		out("Add C12 to C1");
		addChild(type,context,802,858);
		dumpIndex(type,context);

		out("Add C121 to C12");
		addChild(type,context,858,880);
		dumpIndex(type,context);

		out("Moving C12 from C1 to C11");
		moveChild(type,context,802,851,858);
		dumpIndex(type,context);

		context.getCRM().commitTransaction(context);
	}


	public static void postIndexManipTest(int type, IObjectContext context)
		throws OculusException
	{
		TransactionMgr.getInstance().getTransaction(context).startTransaction();

//		out("Move 961 to 926");
//		moveChild(context,954,926,961);
//		dumpTree(context);
//
//		out("Add 926 to 954");
//		moveChild(context,926,954,926);
//		dumpTree(context);
//
//		out("Move 1007 to 1076");
//		moveChild(context,802,1076,1007);
//		dumpTree(context);
//
//		out("Delete 1076");
//		removeChild(context,802,1076);
//		dumpTree(context);

		out("Move 1076 to 1014");
		moveChild(type,context,802,1014,1076);
		dumpTree(type,context);

		context.getCRM().commitTransaction(context);
	}

	
	public static void reindexTest(int type, IObjectContext context)
		throws OculusException
	{
		TransactionMgr.getInstance().getTransaction(context).startTransaction();

		out("Start out empty..");
		wipeIndex(type,context);
		dumpTree(type,context);

		out("Start indexing...");
		reindex(type,context);
		out("Done indexing...");
		
		dumpTree(type,context);

		context.getCRM().commitTransaction(context);
	}

	/** 
	Displays the contents of the index in tabular form.
	*/	
	public static void dumpIndex(int type, IObjectContext context)
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		String table = getIndexTable(type);
		String parentCol = getIndexParent(type);
		String childCol = getIndexChild(type);
		try
		{
			out("Starting dump");
			out("-------------");
			
			conn = context.getCRM().getDatabaseConnection(context);
			qp = conn.createProcessor();
			IDataSet ds = qp.retrieve("SELECT * FROM "+table+" ORDER BY 1,2");
			while (ds.next())
			{
				out(ds.getLong(parentCol)+" -- "+ds.getLong(childCol));
			}
			
			out("-------------");
			out("Ending dump");
		}
		finally
		{
			if (qp != null) qp.close();
		}
	}
	
	/**
	Displays the contents of the index in tree form.
	*/
	public static void dumpTree(int type, IObjectContext context)
		throws OculusException
	{
		out("Starting tree");
		out("-------------");

		dumpTree(type, context,-1,"");
				
		out("-------------");
		out("Ending tree");
	}
	
	/**
	Displays the contents of the given parent in tree form.
	*/
	public static void dumpTree(int type, IObjectContext context, long parent, String space)
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		String table = getIndexTable(type);
		String parentCol = getIndexParent(type);
		String childCol = getIndexChild(type);
		
		try
		{
			conn = context.getCRM().getDatabaseConnection(context);
			qp = conn.createProcessor();
			IDataSet ds = null;
			if (parent == -1)
			{
				ds = qp.retrieve(
					"SELECT * "+
					"FROM "+table+" "+
					"WHERE "+parentCol+"="+childCol+" AND "+parentCol+" NOT IN "+
					" (SELECT "+childCol+" "+
					"  FROM "+table+" "+
					"  WHERE "+parentCol+"<>"+childCol+")"
					);
			}
			else
			{
				ds = qp.retrieve(
					" SELECT topTable."+childCol+" "+
					" FROM "+table+" topTable "+
					" WHERE topTable."+parentCol+"="+parent+" AND topTable."+parentCol+"<>topTable."+childCol+" AND topTable."+childCol+" NOT IN "+
					" (SELECT midTable."+childCol+" "+
					"  FROM "+table+" midTable "+
					"  WHERE midTable."+parentCol+"<>midTable."+childCol+" AND midTable."+parentCol+" IN "+
					"    (SELECT botTable."+childCol+" "+
					"     FROM "+table+" botTable "+
					"     WHERE botTable."+parentCol+"<>botTable."+childCol+" AND botTable."+parentCol+"="+parent+"))"
					);
			}
			
			while (ds != null && ds.next())
			{
				long child = ds.getLong(childCol);
				out(space+child);
				dumpTree(type,context,child,space+"  ");
			}
		}
		finally
		{
			if (qp != null) qp.close();
		}
	}
	
	public static void out(String output)
	{
		System.out.println(output);
	}
}