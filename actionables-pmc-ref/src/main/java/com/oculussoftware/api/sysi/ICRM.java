package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.IRConnection;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.repi.IDataSet;
import com.oculussoftware.api.sysi.sec.IAccessMgr;

/** This interface represents the component request manager.  It serves as the factory
* for all objects in this system.  It is the portal through which all clients should go
* to retrieve any object.  It also validates logins and handles transactions.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ICRM.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/4/00			Added ORIOException to isValid()
*/
public interface ICRM
{
	/** Returns an object in the class with the given name.
  *
  * @param context the context of the requesting user
  * @param classname the pseudo-name of the class being requested
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, String classname)
    throws OculusException;
   
  /** Returns an object in the class with the given class GUID.  Note this method is not
  * being used.
  *
  * @param context the context of the requesting user
  * @param classGUID the GUID of the class being requested
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, IGUID classGUID)
    throws OculusException;
    
	/** Returns an object in the class with the given name and object IID.
  *
  * @param context the context of the requesting user
  * @param classname the pseudo-name of the class being requested
  * @param objIID the IIID of the object being requested
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, String name, IIID objIID)
    throws OculusException;
    
	/** Returns an object in the class with the given name and parameters.
  *
  * @param context the context of the requesting user
  * @param classname the pseudo-name of the class being requested
  * @param args the parameters to use to identify the object
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, String name, IDataSet args)
    throws OculusException;

	/** Returns an object in the class with the given class GUID and object IID.
  *
  * @param context the context of the requesting user
  * @param classGUID the GUID of the class being requested
  * @param objIID the IIID of the object being requested
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, IGUID guid, IIID objID)
    throws OculusException;
    
	/** Returns an object in the class with the given name and object IID.
  *
  * @param context the context of the requesting user
  * @param classname the pseudo-name of the class being requested
  * @param objIID the IIID of the object being requested
  * @param editable true if the object should be locked, false otherwise
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, String name, IIID objID, boolean editable)
    throws OculusException;
    
	/** Returns an object in the class with the given name and object IID.
  *
  * @param context the context of the requesting user
  * @param classname the pseudo-name of the class being requested
  * @param args the parameters to use to identify the object
  * @param editable true if the object should be locked, false otherwise
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, String name, IDataSet args, boolean editable)
    throws OculusException;

	/** Returns an object in the class with the given class GUID and object IID.
  *
  * @param context the context of the requesting user
  * @param classGUID the GUID of the class being requested
  * @param objIID the IIID of the object being requested
  * @param editable true if the object should be locked, false otherwise
  * @return the object requested
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IObject getCompObject(IObjectContext context, IGUID guid, IIID objID, boolean editable)
    throws OculusException;

  /** Validates the given username and password.  If the name and password are valid,
  * a CRM connection is created and returned for that user.
  *
  * @param username the login id for the user
  * @param password the password for the user
  * @return a valid ICRMConnection
  * @exception java.lang.Exception
  */
	public ICRMConnection connect(String username, String password)
		throws Exception;
    
  /** Returns a valid anonymous connection to the CRM.
  *
  * @return a valid anonymous connection to the CRM
  * @exception java.lang.Exception
  */
	public ICRMConnection anonymousConnect()
		throws Exception;

  /** Returns an access manager for the given object context.
  *
  * @param ctxt the object context to return the access manager for
  * @return the access manager for the context
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAccessMgr getAccessMgr(IObjectContext ctxt)
    throws OculusException;
    
  /** Removes the access manager for the given context
  *
  * @param ctxt the object context to remove the access manager for
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeAccessMgr(IObjectContext context)
    throws OculusException;
	
  /** Removes the access manager for the user IIID
  *
  * @param iid the user IID to remove the access manager for
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeAccessMgr(IIID iid)
    throws OculusException;	
  
  /** Returns a reference to the system's license manager.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public com.oculussoftware.api.sysi.license.ILicenseMgr getLicenseMgr()
    throws OculusException;  
  
	/** Returns the IRConnection to the repository for the given context. Each context will
	* be assigned its own IRConnection, so that each call will return the same object, until
	* the object is explicitly returned.
  *
  * @param context the object context to return the database connection for
  * @return the database connection assigned to the given context
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IRConnection getDatabaseConnection(IObjectContext context)
		throws OculusException;
	
	/** Puts the given database connection back in the pool.  This allows other users
  * to gain access to the connection.
  *
  * @param conn the database connection to return
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public void returnDatabaseConnection(IRConnection conn)
		throws ORIOException;
    
	/** Removes the connection for the given user IIID from the list of valid CRM connections.
  *
  * @param conn_userIID the user IIID whose connection is being removed
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeCRMConnection(IIID conn_userIID)
    throws OculusException;
    
	/** Commits the transaction for the given context.
  *
  * @param context the context whose transaction is being commited
  * @exception com.oculussoftware.api.sysi.OculusException
  */		
	public boolean commitTransaction(IObjectContext context)
		throws OculusException;
	
	/** Rolls back the transaction for the given context.
  *
  * @param context the context whose transaction is being rolled back
  * @exception com.oculussoftware.api.sysi.OculusException
  */		
	public void rollbackTransaction(IObjectContext context)
		throws OculusException;
			
	/** Returns whether or not the given ICRMConnection is a valid connection.
  *
  * @param conn the connection whose validity is being checked
  * @return true if the connection is valid, false otherwise
  * @com.oculussoftware.api.repi.ORIOException
  */		
	public boolean isValid(ICRMConnection conn)
		throws ORIOException;

	/** Returns a new globally unique ID.
  * 
  * @return the new globally unique ID
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IGUID genGUID()
  	throws OculusException;
  
  /** Returns whether or not the user of the given object context is currently logged in.
  *
  * @param context the object context for the user
  * @return true if the user is logged in, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isLoggedIn(IObjectContext context)
    throws ORIOException;

  /** Returns whether or not the user for the given CRM connection is currently logged in.
  *
  * @param conn the CRM connection for the user
  * @return true if the user is logged in, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isLoggedIn(ICRMConnection conn)
    throws ORIOException; 
}