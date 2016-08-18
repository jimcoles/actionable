package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** Defines the interface of any object that wishes to hold a lock on another object.
* This should probably either be some variety of a user object or a connection/context object.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ILockHolder.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Added ORIOException to getLockHolderIID()
*/
public interface ILockHolder
{
	/** Returns the IIID of the object holding the lock.
  *
  * @return the IIID of the object holding the lock
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public IIID getLockHolderIID()
		throws ORIOException;
	
	/** Invalidates the locks currently being held by this ILockHolder.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public void invalidate() throws OculusException;
}