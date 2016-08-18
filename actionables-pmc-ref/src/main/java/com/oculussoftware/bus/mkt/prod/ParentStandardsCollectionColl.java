package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    ParentStandardsCollectionColl.java
* Date:        4/6/00
* Description: Handles a collection of IStandardsCollection objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class ParentStandardsCollectionColl extends StandardsCollectionColl implements IParentStandardsCollectionColl, IPersistable
{
  protected String TABLE = "STANDARDSCOLLECTION";
  protected String COL_NAME = "NAME";
  protected String COL_PARENT = "PARENTCOLLID";
  protected String COL_DELETESTATE = "DELETESTATE";
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ParentStandardsCollectionColl() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected ParentStandardsCollectionColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+  
          // " WHERE "+COL_OBJECTID+"<>"+getIID().getLongValue()+" AND "+COL_PARENT+"="+getIID().getLongValue()+" AND  "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " WHERE "+COL_OBJECTID + "=" + COL_PARENT+" AND  "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_NAME;
  }

 /* protected String getClassName()
  {
    return "StandardsCollection";
  }

	//----------------- IStandardsCollectionList Methods ------------------------------------
	/**
	* Returns the next available IStandardsCollection in the list.  If the next IStandardsCollection turns
	* out to be null, just go to the next one.
 	*/
 /* public IStandardsCollection nextStandardsCollection()
		throws OculusException
	{
		return (IStandardsCollection)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
 /* public boolean hasMoreStandardsCollection()
	{
		return hasNext();
	}
  
	
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StandardsCollectionColl sortedList = new StandardsCollectionColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IStandardsCollectionList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		ParentStandardsCollectionColl StandardsCollectionList = null;
			StandardsCollectionList = new ParentStandardsCollectionColl();
			StandardsCollectionList.setIID(_iid);
			StandardsCollectionList._items.addAll(this._items);
			StandardsCollectionList.reset();
		return StandardsCollectionList;
	}
}