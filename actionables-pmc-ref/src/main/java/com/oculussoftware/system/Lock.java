package com.oculussoftware.system;

import java.sql.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;

/**
* <B>Filename:</B> Lock.java<BR>
* <B>Date:</B> 1/27/00<BR>
* <B>Description:</B> <P>Locks an object for editing so that only one person can edit an object
* at a time.  This is used to preserve concurrency.  Clients should never have to deal
* with this object directly.  It is only used by other objects in this package.  Consider
* making this object package level, instead of public.</P>
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
*	---							Saleem Shafi		2/3/00			Added ORIOException to getLockHolderIID()
* ---             Saleem Shafi    4/7/00      Added JavaDoc comments.
*/

public class Lock implements ILock
{
  /** The holder of the lock. */
	private ILockHolder _lockHolder;
  /** The locked object. */
	private IPoolable _obj;
  /** The GUID for this lock.  Since Lock's do not persist, we may not need this. */
	private IGUID _guid;
	
	//----------------------------- Public Constructors -------------------------------
	/** 
  Creates a Lock object.  The constructor requires both an IPoolable reference to an
  object and a reference to an ILockHolder. Neither value can be null.
  @param lockHolder The object that represents the holder of this Lock.
  @param obj The object that is being locked.
  @throws OculusException This exception is thrown if either parameter is null, or if
  there is an error connecting to the CRM.
  */
	public Lock(ILockHolder lockHolder, IPoolable obj)
		throws OculusException
	{
    if (lockHolder == null) throw new OculusException("Null ILockHolder object passed to Lock constructor.");
    if (obj == null) throw new OculusException("Null IPoolable object passed to Lock constructor.");
    ICRM crm = CRM.getInstance();
    if (crm == null) throw new OculusException("Error connecting to CRM in Lock constructor.");
    _guid = crm.genGUID();
    if (_guid == null) throw new OculusException("Null GUID returned from CRM in Lock constructor.");
		_lockHolder = lockHolder;
		_obj = obj;
	}
	
	//----------------------------- ILock Methods -------------------------------
	/**
  Returns a reference to the locked object.
  @return The reference to the locked object.
  */
	public IPoolable getLockedObject()
	{
		return _obj;
	}
	
	/**
  Returns the IID of the holder of the Lock.
  @return The IID of the holder of the Lock.
  @throws ORIOException This exception is thrown if the ILockHolder object is null.
  */
	public IIID getLockHolderIID()
		throws ORIOException
	{
    if (_lockHolder == null) throw new ORIOException("ILockHolder object is null for this Lock.");
		return _lockHolder.getLockHolderIID();
	}
	
	//----------------------------- IObject Methods -------------------------------
	/**
  Returns the IObjectContext for the locked object.
  @return The IObjectContext of the locked object.
  @throws ORIOException This exception is thrown if there is no locked object.
  */
	public IObjectContext getObjectContext()
    throws ORIOException
	{
    if (_obj == null) throw new ORIOException("The object for this Lock is null.");
		return _obj.getObjectContext();
	}
	
	/**
  This method will always throw an exception.  A Lock does not have it's own ObjectContext
  and therefore you should not be able to set one.
  @throws ORIOException This exception is thrown whenever this method is executed.
  */
	public IObject setObjectContext(IObjectContext context)
    throws ORIOException
	{
    throw new ORIOException("You cannot set the IObjectContext of a Lock object.");
	}
	
	/**
  Returns the IGUID for this Lock.
  @return The IGUID for this Lock.
  */
	public IGUID getGUID()
	{
		return _guid;
	}
}