package com.oculussoftware.repos;

/**
* $Workfile: SRConnection.java $
* Create Date: Circe 1/31/2000
* Description: IRConnection impl for 1.2 release.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author S. Shafi ?
* @version 1.2
*
* $History: SRConnection.java $
 * 
 * *****************  Version 15  *****************
 * User: Sshafi       Date: 8/30/00    Time: 11:01a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Bug Fix: 2124
 * 
 * *****************  Version 14  *****************
 * User: Sshafi       Date: 8/30/00    Time: 8:36a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 13  *****************
 * User: Sshafi       Date: 8/30/00    Time: 8:35a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 12  *****************
 * User: Sshafi       Date: 8/30/00    Time: 8:11a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Bug Fix: 2124
 * 
 * *****************  Version 11  *****************
 * User: Eroyal       Date: 5/24/00    Time: 9:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Issue # 383
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 5/19/00    Time: 10:05a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Issue #DES00239 - hard coded the db password.
*/

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.rdb.*;

import java.sql.*;
import java.util.*;

public class SRConnection implements IRConnection
{
	private IGUID _guid;
	private IObjectContext _context;
	private Connection _conn;
	private static String _driver;
	private static String _dsn;
	private static String _user;
	private static String _password;

	static
	{
		try
		{
      _driver = (String)SimpleDirectory.getInstance().getValue("driver","Connection");
      _dsn = (String)SimpleDirectory.getInstance().getValue("dsn","Connection");
      _user = (String)SecureDirectory.getInstance().getValue("username","Connection");
      _password = (String)SecureDirectory.getInstance().getValue("password","Connection");
//      _password = "eclatdba";
			Class.forName(_driver).newInstance(); 
		}
		catch (Exception ex) {com.oculussoftware.service.log.LogService.getInstance().write(ex);}
	}
  
	public SRConnection() throws ORIOException
	{
		try
		{
			_conn = DriverManager.getConnection(_dsn, _user, _password);
			_conn.setAutoCommit(false);
		}
		catch (SQLException sqlExp)
		{
			throw new ORIOException("Error creating a connection to the database."+sqlExp.toString());
		}
	}
	
	//---------------------- IRConnection Methods -------------------------------
	public void close() throws ORIOException
	{
		try
		{
			_conn.close();
		}
		catch (SQLException sqlExp)
		{
			throw new ORIOException("Error in closing database connection.");
		}
	}
	
	public void commit() throws ORIOException
	{
		try
		{
			_conn.commit();
		}
		catch (SQLException sqlExp)
		{
			throw new ORIOException("Error in closing database connection.");
		}
	}
	
	public IQueryProcessor createProcessor() throws ORIOException
	{
		return new QueryProcessor(_conn);
	}
	
	public void rollback() throws ORIOException
	{
		try
		{
			_conn.rollback();
		}
		catch (SQLException sqlExp)
		{
			throw new ORIOException("Error in closing database connection.");
		}
	}
	
	public IPreparedStatementProcessor prepareProcessor(String sql) throws ORIOException
	{
		return new PreparedStatementProcessor(_conn, sql);
	}


	//---------------------- IPoolable Methods -------------------------------
	public Object dolly() throws OculusException
	{
		return this;
	}
	
	public boolean isLocked()
	{
		return false;
	} 
	
  public boolean isRemoveable()
  {
    return true;
  }
  
	  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    if (context == null)
      throw new OculusException("Context Argument expected.");

    setObjectContext(context);
    return this;
  }

	//---------------------- IObject Methods -------------------------------
	public IGUID getGUID()
	{
		return _guid;
	}
	
	public IObjectContext getObjectContext()
	{
		return _context;
	}
	
	public IObject setObjectContext(IObjectContext context)
	{
		_context = context;
		return this;
	}
}