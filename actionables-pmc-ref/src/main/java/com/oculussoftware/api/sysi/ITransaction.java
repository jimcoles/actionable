package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** Defines the interface of an object that ensures the proper commit
* and rollback of changes made to IPersistable objects.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ITransaction.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Changed addObject() to take an IPersistable
*																							instead of an IObject
*	---							Saleem Shafi		2/4/00			Added ORIOException to addObject(), rollback()
*/
public interface ITransaction extends IObject
{
	/**
	* Registers an object to be handled by this ITransaction.  By adding an object
	* to the ITransaction, you ensure that any changes made to the object only take
	* place if all of the changes to the other objects in this ITransaction take place.
  *
  * @param obj the object to bind to the transaction
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
	*/
	public ITransaction addObject(IPersistable obj)
		throws ORIOException;
	
	/** Commits all of the changes to the objects in this ITransaction to the repository.
  *
  * @return true if the commit was successful, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean commit()
    throws OculusException;
    
  /** Rolls back the changes to the objects in this ITransaction.
  *
  * @return true if the rollback was successful, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean rollback()
  	throws ORIOException;

  /** Starts the transaction and prepares it to have objects bound to it.  If this
  * method is run on a transaction that already has objects bound to it, it will rollback
  * before starting the new transaction.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void startTransaction()
    throws OculusException;  
}