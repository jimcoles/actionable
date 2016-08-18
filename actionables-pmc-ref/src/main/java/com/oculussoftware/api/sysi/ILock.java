package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** Defines the interfaces of an object that ensures safe concurrency on
* an object by locking the object.  Only objects that implement the IPoolable interface
* can be locked.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ILock.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Decided that only IPoolables can be locked.
*																							Added ORIOException to getLockHolderIID().
*/

public interface ILock extends IObject
{
	/** Returns the IID of the user who has a lock on the object.
  *
  * @return the IIID of the user holding the lock
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public IIID getLockHolderIID()
		throws ORIOException;
	
	/** Returns the object that is being locked.
  *
  * @return the object that is locked
  */
	public IPoolable getLockedObject();
}