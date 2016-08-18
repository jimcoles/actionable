package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.repi.IDataSet;

/** Defines the interface expected by the CRM of an object that can act 
*	as its object pooler.  Writing this to an interface may allow substitution of 
* other pooling systems in the future.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IPoolMgr.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/4/00			Changed some throws claues and refined
*																							parameter types.
*/

public interface IPoolMgr
{
  /** Returns an object of the requested type.
  *
  * @param context the IObjectContext of the user requesting the object
  * @param classname the fully-qualified Java class name of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPoolable getObject(IObjectContext context, String classname)
    throws OculusException;
    
  /** Returns an object of the requested type that matches the given criteria.
  *
  * @param context the IObjectContext of the user requesting the object
  * @param classname the fully-qualified Java class name of the object
  * @param args the parameters to use to uniquely identify the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPersistable getObject(IObjectContext context, String classname, IDataSet args)
    throws OculusException;
    
  /** Returns an object of the requested type that matches the given criteria.
  *
  * @param context the IObjectContext of the user requesting the object
  * @param classname the fully-qualified Java class name of the object
  * @param args the parameters to use to uniquely identify the object
  * @param editable true if the object should be locked, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPersistable getObject(IObjectContext context, String classname, IDataSet args, boolean editable)
    throws OculusException;

  /** Puts the given object back in the pool and makes it available for other users.
  *
  * @param obj the object being returned
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public void returnObject(IPoolable obj) throws ORIOException;

  /** Locks the given object so that no other users can edit the object.
  *
  * @param obj the object to be locked
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public void lock(IPoolable obj)
		throws OculusException;

  /** Unlocks the given object so that other uses can edit it.
  *
  * @param obj the object to be unlocked
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public void unlock(IPoolable obj)
		throws ORIOException;

  /** Removes the given object from the cache so that it can be re-loaded from the
  * repository.
  *
  * @param obj the object to be removed from the cache
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public void remove(IPoolable obj)
		throws ORIOException;
		
  /** Replaces cached instance of the object with the given state of the object.
  *
  * @param obj the object to use to put in the cache
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public void replace(IPersistable obj)
    throws ORIOException;
    
  /** Updates the cached instance of the object with the given state of the object.
  *
  * @param obj the object to use to put in the cache
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public void updateCache(IPersistable obj)
		throws ORIOException;	
	
  /** Returns whether or not the given object is locked.
  *
  * @param obj the object to use to put in the cache
  * @return true if the object is locked, false otherwise
  */
	public boolean isLocked(IPoolable obj);
	
  /** Clears the cache of any objects that have not been accessed for a particular
  * amount of time.
  */
	public void clean();
}