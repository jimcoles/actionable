package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IRConnection;

/** This interface describes a transaction factory.  It maps a unique IObjectContext to
* a single ITransaction object.  It is also responsible for creating ITransaction when
* needed.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ITransactionMgr.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Added OculusException to getTransaction()
*/

public interface ITransactionMgr
{
	/**
	* Returns the ITransaction object associated with the given IObjectContext.
	* If no ITransaction object exists for the IObjectContext, one is created
	* and associated with it.
  *
  * @param context the IObjectContext for the transaction being requested.
  * @return the requested transaction
  * @exception com.oculussoftware.api.sysi.OculusException
	*/
  public ITransaction getTransaction(IObjectContext context)
  	throws OculusException;
    
}