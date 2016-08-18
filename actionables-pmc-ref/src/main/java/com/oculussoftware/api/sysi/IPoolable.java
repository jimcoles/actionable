package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.repi.IDataSet;

/** This interface represents an object that can be created by the CRM and PoolMgr
* It also flags this object as cachable, so that the PoolMgr will add it to its cache.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IPoolable.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    3/3/00      Removed the getIID() method from this interfaced
*                                             and moved it to IPersistable.  Added the construct()
*                                             method to act as a pseudo-constructor.
* ---             Saleem Shafi    3/8/00      Changed the construct() method to take an IDataSet
*
*/

public interface IPoolable extends IObject, Cloneable
{
	/** Returns whether or not this object is locked.
  *
  * @return true if this object is locked, false otherwise
  */
	public boolean isLocked();
	
  /** Pseudo-constructor that initializes this object according to the parameters
  * given.
  *
  * @param context the IObjectContext of the creating user
  * @param args the parameters to use to initialize this object
  * @return true if this object is locked, false otherwise
  */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException;
  
	/** Creates and returns an exact copy of this object including all of its properties.
  *
  * @return the cloned object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public Object dolly()
    throws OculusException;
  
  /** Returns whether or not this object should be subject to removal from the cache.
  *
  * @returns true if the object should be removed from the cache, false otherwise
  */
  public boolean isRemoveable();
}