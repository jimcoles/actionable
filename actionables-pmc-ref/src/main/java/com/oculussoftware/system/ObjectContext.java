package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;

/**
* <B>Filename:</B> ObjectContext.java<BR>
* <B>Date:</B> <BR>
* <B>Description:</B> <P>Provides business objects with a handle into the context of their
* existence such as user, transaction, environmental info, and concurrency control.</P>
*
* <P>Copyright 1-31-2000 Oculus Software.  All Rights Reserved.</P>
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/3/00			An ObjectContext object is its own ObjectContext
*																							instead of not having an object context.  Added
*																							ORIOException to getLockHolderIID()
* ---             Saleem Shafi    3/3/00      Added getRepository() method
* ---             Saleem Shafi    4/7/00      Added JavaDoc comments.
* ???             Egan Royal      6/20/00     Implement Serializable, because we are putting the
*                                             context on the session.
*/

public class ObjectContext implements IObjectContext, java.io.Serializable
{
  /** Handle to the IEnvironment of this object */
	private IEnvironment _environment;
  /** Handle to the ICRMConnection for this object */
	private ICRMConnection _connection;
  /** IGUID of this object.  Since this object does not persist, consider removing this value. */
	private IGUID _guid;
  /** The IIID of the repository in which this object persists.  Consider making this a direct
  reference to the IRepository instead of the IIID */
  private IIID _reposIID;

	//----------------------------- Public Constructors ----------------------------
	/**
  Default constructor that generates a default object context.  When this system handles
  multiple repositories, this constructor will need an IRepository object, or IIID to refer to.
  @throws OculusException This exception is thrown if there is an error connecting to the
  CRM.
  */
	public ObjectContext() throws OculusException
	{
    // can't gen guid from CRM because it creates a loop.
    _guid = new GUID();
    if (_guid == null) throw new OculusException("Null GUID returned from CRM in ObjectContext constructor.");
    // FIXME : This obviously won't work if we've got multiple repositories.
    _reposIID = new SequentialIID(1);
	}
	
	//----------------------------- IObjectContext Methods ------------------------
	/**
  Returns the IEnvironment object for this object.
  @return The IEnvironment object for this object.
  */
  public IEnvironment getEnvironment()
	{
		return _environment;
	}
	
	/**
  Returns the ICRMConnection object for this object.
  @return the ICRMConnection object for this object.
  */
	public ICRMConnection getConnection()
	{
		return _connection;
	}
	
	/**
  Returns the singleton ICRM object for this VM.  Note: Thie method should be the only
  place where we take advantage of the fact that the CRM is a singleton.
  @return A handle to the ICRM object.
  @throws OculusException This exception is thrown only if there is an error in generating
  the initial instance of the CRM.  The exception is originally thrown from CRM().
  */
  public ICRM getCRM()
    throws OculusException
	{
		return CRM.getInstance();
	}
  
  /**
  Returns the IRepository in which the object persists.  An exception is thrown if a
  connection cannot be made to the repository.
  @return The IRepository object in which the object persists.
  @throws OculusException This exception is thrown if there is an error in connecting to
  the repository.
  */
  public IRepository getRepository()
    throws OculusException
  {
    // FIXME: I think the IRepository object should be a member variable of the IObjectContext
    // instead of just the IIID of the IRepository.  That way we won't have to go to the
    // CRM everytime we want a handle to the repository.
    return (IRepository)getCRM().getCompObject(this,"Repository");
  }

	/**
  Sets the ICRMConnection for this object.  The ICRMConnection can not be null.
  @return A reference to this object.
  @throws ORIOException This exception is thrown if the ICRMConnection parameter is null.
  */
	public IObjectContext setConnection(ICRMConnection conn)
    throws ORIOException
	{
//    if (conn == null) throw new ORIOException("Cannot set the CRM connection of this object to null.");
		conn.setObjectContext(this);
		_connection = conn;		
		return this;
	}
	
	//----------------------------- ILockHolder Methods ----------------------------
	/**
  Returns the IID of this object's user.  Semantically this means the the user for this
  ObjectContext is holding the lock.
  @return The IID of the user holding the lock on the object.
  @throws ORIOException This exception is thrown if there is an error connection to the
  CRM.
  */
	public IIID getLockHolderIID()
		throws ORIOException
	{
    ICRMConnection conn = getConnection();
    if (conn == null) throw new ORIOException("Error connecting to the CRM while returning the lock holder for this object.");
		return conn.getUserIID();
	}
	
	/**
  Invalidates the locks that this lockholder is holding.
  */
	public void invalidate() throws OculusException
  {
    ICRM crm = getCRM();
    ITransaction t = TransactionMgr.getInstance().getTransaction(this);
    if(t != null)
      crm.rollbackTransaction(this);
    crm.removeAccessMgr(this);
    crm.removeCRMConnection(this.getConnection().getIID());  
	}
	
	//----------------------------- IObject Methods ----------------------------
	/**
  Returns itself indicating that an ObjectContext is it's own ObjectContext.
  @return A reference to this object.
  */
	public IObjectContext getObjectContext()
	{
		return this;
	}
	
	/**
  This method will always throw an exception since it does not make sense to set the
  ObjectContext of the ObjectContext.
  @throws ORIOException This exception is throw whenever this method is called.
  */
	public IObject setObjectContext(IObjectContext context)
    throws ORIOException
	{
		throw new ORIOException("Cannot set the ObjectContext of the ObjectContext.");
	}
	
	/**
  Returns this object's GUID.
  @return This object's GUID.
  */
	public IGUID getGUID()
	{
		return _guid;
	}
}