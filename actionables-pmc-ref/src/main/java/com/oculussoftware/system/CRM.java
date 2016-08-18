package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.IRConnection;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.repi.IQueryProcessor;
import com.oculussoftware.api.repi.IDataSet;
import com.oculussoftware.api.repi.DeleteState;

import com.oculussoftware.system.sec.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

/**
* Filename:    CRM.java
* Date:        1/21/00
* Description: Handles all object requests, logins, logging and security.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/4/00			Added ORIOException to isValid() and 
*																							changed CRM() to throw OculusException
* ---             Saleem Shafi    3/6/00      Optimized getCompObject(context, string, IIID) to use
*                                             getCompObject(Context, string, IIID, boolean)
*/
public class CRM implements ICRM
{
  //---------------------------- Private static variables -----------------------------  
  
  static public final String MSG_INVALID_CONNECT = "Invalid CRM Connection.  ";
	static private CRM _crm;										// singleton CRM object

  //---------------------------- Private instance variables  -----------------------------  
  
	private IObjectContext _context = null;		  // system object context
	private Hashtable _connectionList = null;					// list of valid CRMConnections
  private Hashtable _acmList = null;                 // table of IAccessMgr's (per ICRMConnection)

	//---------------------------- Private Constructors -----------------------------	

	/** Default constructor initializes the connection list and creates a system context. */
	private CRM() throws OculusException
	{
		_connectionList = new Hashtable();
		_context = new ObjectContext();
		_context.setConnection(connect());        // system connection
    _acmList = new Hashtable();
	}
	
	//---------------------------- Static Methods -----------------------------	
	
	/** Returns the singleton CRM object */
	public static ICRM getInstance() throws OculusException
	{
		if (_crm == null)
			_crm = new CRM();
		return _crm;
	}
	  
	//---------------------------- ICRM Methods -----------------------------	

	/** Returns an object of the specified class synonym. */
  public IObject getCompObject(IObjectContext context, String name)
    throws OculusException
	{
		if (isValid(context.getConnection()))
		{
			String classname = (String)SimpleDirectory.getInstance().getValue("classname",name);
			return PoolMgr.getInstance().getObject(context,classname);
		}
    return null;
	}

	/** Returns the object of the specified class synonym and object IID */
  public IObject getCompObject(IObjectContext context, String name, IIID objID)
    throws OculusException
	{
    IDataSet args = new DataSet();
    args.setIID(objID);
		return getCompObject(context,name,args,false);
	}
    

  /** Returns the object of the specified class synonym and given arguments */
  public IObject getCompObject(IObjectContext context, String name, IDataSet args)
    throws OculusException
  {
    return getCompObject(context,name,args,false);
  }
    


	/** Returns the object of the specified class synonym and object IID.  If editable is
	* true, then the object returned is editable. */
  public IObject getCompObject(IObjectContext context, String name, IIID objID, boolean editable)
    throws OculusException
	{
    IDataSet args = null;
    if (objID != null)
      {
        args = new DataSet();
        args.setIID(objID);
      }
		if (isValid(context.getConnection()))
		{
			String classname = (String)SimpleDirectory.getInstance().getValue("classname",name);
			return PoolMgr.getInstance().getObject(context,classname,args,editable);
		}
    return null;
	}
    
  /** Returns the object of the specified class synonym and object IID.  If editable is
  * true, then the object returned is editable. */
  public IObject getCompObject(IObjectContext context, String name, IDataSet args, boolean editable)
    throws OculusException
  {
    if (isValid(context.getConnection()))
    {
      String classname = (String)SimpleDirectory.getInstance().getValue("classname",name);
      return PoolMgr.getInstance().getObject(context,classname, args, editable);
    }
    return null;
  }
    
  /** Returns an object of the specified class GUID. */
  public IObject getCompObject(IObjectContext context, IGUID guid)
    throws OculusException
  {
    return null;
  }
    
  /** Returns the object of the specified class GUID and object IID. */
  public IObject getCompObject(IObjectContext context, IGUID guid, IIID objID)
    throws OculusException
  {
    return null;
  }
    
	/** Returns the object of the specified class GUID and object IID. If editable is
	* true, then the object returned is editable. */
  public IObject getCompObject(IObjectContext context, IGUID guid, IIID objID, boolean editable)
    throws OculusException
	{
		return null;
	}

	/** Returns true if the given ICRMConnection is valid */
	public boolean isValid(ICRMConnection conn)
		throws ORIOException
	{
//		if (conn == null) return false;							// it's not valid if it's null
//		if (conn.getIID()==null || !_connectionList.containsKey(conn.getIID())) return false;
//																								// it's not valid if the ID isn't in the list
//		if (!_connectionList.get(conn.getIID()).equals(conn) && conn.getIID().getLongValue() != 0) return false;
//                                                // it's not valid if the one in the list doesn't match the ID
		if (conn == null) throw new ORIOException(MSG_INVALID_CONNECT+"Attempt to use null connection to CRM.");
		if (conn.getIID()==null) throw new ORIOException(MSG_INVALID_CONNECT+"Attempt to use uninitialized connection to CRM.");
    if (!_connectionList.containsKey(conn.getIID())) throw new ORIOException(MSG_INVALID_CONNECT+"Attempt to use unrecognized connection to CRM.  ID="+conn.getIID());
    ICRMConnection realConn = (ICRMConnection)_connectionList.get(conn.getIID());
		if (!realConn.equals(conn) && (conn.getIID().getLongValue() != 0)) throw new ORIOException(MSG_INVALID_CONNECT+"Attempt to spoof connection to CRM. User ID="+conn.getIID()+": "+conn+",  Actual ID="+realConn.getIID()+": "+realConn);
		return true;
	}
	
	/** Returns a valid ICRMConnection if the username and password is valid. */
	public ICRMConnection connect(String username, String password)
		throws OculusException
	{
    if(username.equals("system") && password.equals("system"))
      return connect();
		IIID userIID = null;
    if(validLoginID(username))
      userIID = checkPassword(username,password);
    if(userIID != null)
    {
      ICRMConnection newConn = new CRMConnection(userIID, this); // create a new CRMConnection with it
      
      IIID conniid = newConn.getIID();
      if(!_connectionList.containsKey(conniid))        // if we need to, lets add it to our list of valid connections
        _connectionList.put(conniid,newConn);
      else
        throw new OculusException("Already logged in. UserID="+userIID.getLongValue());
      return newConn;
    }//end if
    else
      throw new OculusException("LoginID and Password could not be authenticated.");
	}

  /** Returns a valid anonymouse ICRMConnection. */
	synchronized public ICRMConnection anonymousConnect()
		throws Exception
  {
		IIID userIID = null;
    IObjectContext context = _context;
    
    ITransaction trans = TransactionMgr.getInstance().getTransaction(context);
    com.oculussoftware.api.busi.common.org.IUser anonUser = (com.oculussoftware.api.busi.common.org.IUser)context.getCRM().getCompObject(context,"User",(IDataSet)null,true);
    userIID = anonUser.getIID();
    trans.rollback();

    ICRMConnection newConn = new CRMConnection(userIID, this); // create a new CRMConnection with it
    IIID conniid = newConn.getIID();
    _connectionList.put(conniid,newConn);
    return newConn;
  }


    
  public boolean isLoggedIn(IObjectContext context) throws ORIOException
  { return isLoggedIn(context.getConnection()); }
    
  public boolean isLoggedIn(ICRMConnection conn) throws ORIOException
  {
    boolean blnRV;
    //issue 2191 uncommented
    //this is commented out so that the invalid connection stacktrace
    //will get to the UI so we can try to debug it
    try
    {
      blnRV = isValid(conn);
    }
    catch(ORIOException ex)
    {
      blnRV = false;
    }
    return blnRV;
  } 
  
  
  /**
  * connect with a System context
  */
  private ICRMConnection connect()
    throws OculusException
  {
    IIID userIID = new SequentialIID(0);   
    ICRMConnection newConn = new CRMConnection(userIID, this);// create a new CRMConnection with it
    if (!_connectionList.containsKey(newConn.getIID()))       // if we need to, lets add it to our list of valid connections
      _connectionList.put(newConn.getIID(),newConn);
    return newConn;
  }
  
  /**
  *
  */
  private boolean validLoginID(String strLogin) throws OculusException
  {
    IQueryProcessor stmt = null;
    boolean blnRV = false;
    try
    {
      IRConnection repConn = getDatabaseConnection(_context);  // get a db connection
      stmt = repConn.createProcessor();
      IDataSet results = stmt.retrieve(      // check if the username and password match
        " SELECT OBJECTID "+
        " FROM APPUSER "+
        " WHERE LOGINID='"+SQLUtil.primer(strLogin)+"'"+
        " AND ACTIVE = 1"+
        " AND DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue());
      blnRV = results.next();
      returnDatabaseConnection(repConn);
    }//end try
    catch(OculusException exc)
    { 
      com.oculussoftware.service.log.LogService.getInstance().write(exc);
      throw new OculusException("Error connecting to database."); 
    }//end catch
    finally{if(stmt != null) stmt.close();}
    if(!blnRV)
      throw new OculusException("Invalid LoginID.");
    return blnRV;
  }
  
  /**
  * assumes that a valid login id is already verified
  */
  private IIID checkPassword(String strLogin, String strPassword) throws OculusException
  {
    IQueryProcessor stmt = null, stmt2 = null;
    IIID userIID = null;
    try
    {
      IRConnection repConn = getDatabaseConnection(_context);   // get a db connection
      stmt = repConn.createProcessor();
      IDataSet results = stmt.retrieve(        // check if the username and password match
        " SELECT OBJECTID "+
        " FROM APPUSER "+
        " WHERE LOGINID='"+SQLUtil.primer(strLogin)+"' AND PASSWORD='"+SQLUtil.primer(strPassword)+"'"+
        " AND ACTIVE = 1"+
        " AND FAILEDLOGINS < 5"+
        " AND DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue());
      if (results.next())
      {
        long lngObjID = results.getLong("OBJECTID");
        userIID = _context.getRepository().makeReposID(lngObjID);    // if so, get the user's ID
        stmt2 = repConn.createProcessor();
        stmt2.update("UPDATE APPUSER SET FAILEDLOGINS = 0"+
                     " WHERE OBJECTID="+lngObjID);
      }//end if
      else  //increment the failed logins
      {
        stmt2 = repConn.createProcessor();
        stmt2.update("UPDATE APPUSER SET FAILEDLOGINS = FAILEDLOGINS + 1"+
                     " WHERE LOGINID='"+strLogin+"'"); 
      }
      repConn.commit();
      returnDatabaseConnection(repConn);
    }//end try
    finally{if(stmt != null) stmt.close(); if(stmt2 != null) stmt2.close();}
    if(userIID == null)
      throw new OculusException("Invalid Password.");
    return userIID;
  }
	
  /** public void startTransaction() {} ??? */
  
  /** Gets the ACM instance for the given connection. */
  public IAccessMgr getAccessMgr(IObjectContext ctxt)
    throws OculusException
  {
    IAccessMgr retObj = null;
    // 1. ensure context has a valid connection
    // 2. ensure 1 acm per connection
    if ( !isValid(ctxt.getConnection()) )
      throw new OculusException(MSG_INVALID_CONNECT);
    
    // take the ACM init hit now instead of later...
    IIID id = ctxt.getConnection().getIID();
    retObj = (IAccessMgr) _acmList.get(id);
    if (retObj == null)        //
    {
      retObj = new AccessMgr(ctxt, PermissionSet.getInstance());
      _acmList.put(id, retObj);
    }
    
    return retObj;
  }
  
  /** Removes the AccessManager from the hashmap. */
  public void removeAccessMgr(IObjectContext context)
    throws OculusException
  { 
    removeAccessMgr(context.getConnection().getIID()); 
  }
	
	/** Removes the AccessManager from the hashmap. */
  public void removeAccessMgr(IIID iid)
    throws OculusException
  { 
    if(_acmList != null && !_acmList.isEmpty())
      _acmList.remove(iid); 
  }
  
  /** gets the LicenseManager */
  public com.oculussoftware.api.sysi.license.ILicenseMgr getLicenseMgr()
    throws OculusException
  {
    return com.oculussoftware.system.license.LicenseMgr.getInstance();
  }//
  
	/** Commits the transaction for the given context. */
	public boolean commitTransaction(IObjectContext context)
		throws OculusException
	{
		// if it's a valid connection, run the commit.
		if (isValid(context.getConnection()))
			return TransactionMgr.getInstance().getTransaction(context).commit();
		else
			throw new OculusException(MSG_INVALID_CONNECT);
	}

	/** Rolls back the transaction for the given context. */
	public void rollbackTransaction(IObjectContext context)
		throws OculusException
	{
		if (isValid(context.getConnection()))
			TransactionMgr.getInstance().getTransaction(context).rollback();
		else
			throw new OculusException(MSG_INVALID_CONNECT);
	}

	/** Returns a connection to the repository */
	public IRConnection getDatabaseConnection(IObjectContext context)
		throws OculusException
	{
		if (isValid(context.getConnection()))
			return (IRConnection)getCompObject(context,"Connection");
		else
			throw new OculusException(MSG_INVALID_CONNECT);
	}

	/** Makes the given IRConnection available to others */
	public void returnDatabaseConnection(IRConnection conn)
    throws ORIOException
	{
		if (conn != null)
    {
      conn.rollback();
      PoolMgr.getInstance().returnObject(conn);
    }
	}

  /** Remove the CRM connection from the hashmap.  */
  public void removeCRMConnection(IIID conn_userIID)
    throws OculusException
  {
    if(_connectionList != null && conn_userIID != null)
      _connectionList.remove(conn_userIID);
  }
  
	/** Returns a globally-unique ID. */
  public IGUID genGUID() throws OculusException
 	{
 		return new GUID();
	};
}