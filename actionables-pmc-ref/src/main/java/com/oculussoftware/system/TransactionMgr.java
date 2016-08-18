package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

/**
* Filename:    TransactionMgr.java
* Date:        
* Description: Manages the transactions by acting as a factory for Transaction objects.
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
*	---							Saleem Shafi		2/3/00			Add OculusException to getTransaction()
* ---             Saleem Shafi    4/21/00     Changed logic to force the client to explicitly start a transaction.
*
*/
public class TransactionMgr implements ITransactionMgr
{
	private static TransactionMgr _transMgr = new TransactionMgr();
																								// singleton instance of the TransactionMgr
	private Map _transactions;							// list of transactions for this manager

	
	//-------------------------- Private Constructors ----------------------------------
	/**
  Default constructor just initializes the transaction list.
  */
	private TransactionMgr()
	{
		_transactions = new HashMap();	
	}
	
	//-------------------------- Static Methods -------------------------------------
	/**
  Returns the singleton TransactionMgr object.
  @return The singleton TransactionMgr object.
  */
  public static ITransactionMgr getInstance()
	{
		return _transMgr;
	}
  
  //-------------------------- ITransactionMgr Methods -----------------------------
  /**
  Returns the Transaction object associated with the given context.  If the context
  does not have a transaction, it creates one and registers it with the context.
  @return The ITransaction object associated with the given context.
  @param context The IObjectContext used to select a Transaction.
  @throw OculusException This exception is thrown if there is an error creating a new
  Transaction object or if context is null.
  */
  public ITransaction getTransaction(IObjectContext context)
    throws OculusException
	{
    if (context == null)
      throw new ORIOException("Attempt to reference Transaction for null context.");
    IGUID guid = context.getGUID();
		// if the context doesn't have a transaction, give it one.
		if (!_transactions.containsKey(guid))
      _transactions.put(guid, new Transaction(context));
  	return (ITransaction)_transactions.get(guid);
	}
  
}