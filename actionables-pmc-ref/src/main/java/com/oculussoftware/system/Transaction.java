package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;
import java.util.Enumeration;

/**
* Filename:    Transaction.java
* Date:        
* Description: Ensures atomicity of changes to IPoolable objects.
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
*	---							Saleem Shafi		2/3/00			Changed the logic of commit() slightly.  
*	---							Saleem Shafi		2/4/00			Added ORIOException to addObject(), rollback()
* ---             Saleem Shafi    4/21/00     Changed logic to force an explicit startTransaction().
* BUG00327        Saleem Shafi    5/23/00     Added check to exclude IRCollections from saving now that IRCollections can be in the transaction.
* DES00628        Egan Royal      6/5/00      Added check to exclude Deleted objects from saving.
*/

public class Transaction implements ITransaction
{
	private Vector _operations;							// list of the operations that need to commit together
	private IObjectContext _context;						// context of the objects in the operations
	private IGUID _guid;												// guid of this transaction
	
	//------------------------ Private Constructors ------------------------------------
	/**
	* Creates a new transaction for the given context.  The context may not be null.
  * @param context The IObjectContext for the requesting user.
  * @throws OculusException This exception is thrown if the context is null or if there
  * is an error generating a GUID.
	*/
	public Transaction(IObjectContext context)
		throws OculusException
	{
    if (context == null)
      throw new ORIOException("Attempt to construct a transaction with a null context.");
		_context = context;
		_guid = _context.getCRM().genGUID();
	}
	
	//------------------------ Static Methods ------------------------------------
	/**
	*	Starts a new transaction.  If the transaction already contains operations, the
  * existing transaction is rolled back before creating a new transaction.
  * @throws ORIOException This exception is thrown if an error occurs during the rollback.
	*/
  public void startTransaction()
  	throws ORIOException
  {
    if (_operations != null)
      rollback();
    _operations = new Vector();
  }
  
	//------------------------ ITransaction Methods ------------------------------------
	/**
	* Adds an IPersistable object to the transaction.  Duplicates are allowed.  The operations
  * are guaranteed to occur in the same order that they were added to the Transaction.
	*/
	public ITransaction addObject(IPersistable obj)	
		throws ORIOException
	{
		// I think storing the IID is faster than calling .getIID() twice.
//		IIID objIID = obj.getIID();
		// if the object isn't already in the transaction, add it
//    if (!_operations.containsKey(objIID))
	  _operations.add(obj);
		return this;
	}
	
	/**
	* Commits the operations in this transaction.  First, all operations are sent to
  * the database.  If there are errors, the database is rolledback as is the 
  * transaction.  If there are no errors, the database connection is commited.  Then,
  * the cache is updated and the locks are released.  If there are any errors during
  * this process, the objects are completed removed from the cache and forced to be
  * reloaded from the database.
  * @return true if the commit was successful
  * @throws OculusException This exception is thrown if there was an error during
  * the transfer of data to the database.
	*/
  public boolean commit()
    throws OculusException
	{
		IRConnection conn = null;
		try
		{
			// This checks out a Connection in the user's name so that one connection
			// is used for all of the operations
			conn = CRM.getInstance().getDatabaseConnection(_context);
			// For every operation, save it
			for (Enumeration e = _operations.elements(); e.hasMoreElements(); )
      {
        IPersistable pObj = ((IPersistable)e.nextElement());
        if (!(pObj instanceof IRCollection) || PersState.DELETED.equals(pObj.getPersState()))
          pObj.save();
      }
			// This will commit all of the changes in the data store
			conn.commit();
		}
		// If there was a problem updating the data store
		catch (Exception exp)
		{
			// rollback the changes
			if (conn != null)
				conn.rollback();
			rollback();
      _operations = null;
			throw new OculusException(exp);
		}
		finally
		{
			// This method is responsible for returning the connection
			CRM.getInstance().returnDatabaseConnection(conn);
		}			

		try
		{
			// For every operation, update the cache
			for (Enumeration e = _operations.elements(); e.hasMoreElements(); )
				PoolMgr.getInstance().updateCache((IPersistable)e.nextElement());
			// and unlock all of the objects
			for (Enumeration e = _operations.elements(); e.hasMoreElements(); )
				PoolMgr.getInstance().unlock((IPoolable)e.nextElement());
		}
		// if we have problems updating the cache, but the repository info is correct
		catch (Exception exp)
		{
			// remove all of the objects from the cache
			for (Enumeration e = _operations.elements(); e.hasMoreElements(); )
				PoolMgr.getInstance().remove((IPoolable)e.nextElement());
			// i don't think we need to throw this exception, since we've handled it
			// maybe we should just log it.
			// throw exp;
		}
    finally
    {
      _operations = null;
    }
		// if we get this far without any errors, then we're ok
		return true;
	}
	
	/**
	* Rolls back the changes to this Transaction.  This does not handle the repository
	* rollback.
  * @return true if the rollback was sucessful.
  * @throws ORIOException This exception is thrown if there is an error unlocking any of
  * the elements.
	*/
  public boolean rollback()
  	throws ORIOException
	{
		// Basically all that is happening is that the objects are all being unlocked so
		// that the changes are lost and the objects are made available to other users.
    if(_operations != null)
    {
      for (Enumeration e = _operations.elements(); e.hasMoreElements(); )
		  	PoolMgr.getInstance().unlock((IPoolable)e.nextElement());
    }//end if
		return true;
	}
  
	//------------------------ IObject Methods ------------------------------------
	/**
  Returns the context of this transaction.
  @return the IObjectContext of this Transaction.
  */
	public IObjectContext getObjectContext()
	{
		return _context;
	}
	
	/**
  Sets the context of this transaction.
  @return this
  @param context The IObjectContext to set for this Transaction.
  */
	public IObject setObjectContext(IObjectContext context)
    throws ORIOException
	{
    if (context == null)
      throw new ORIOException("Attempt to set the object context of the transaction to null.");
		_context = context;
		return this;
	}
	
	/**
  Returns the globally unique ID of this object.
  @return The GUID for this transaction.
  */
	public IGUID getGUID()
	{
		return _guid;
	}
}