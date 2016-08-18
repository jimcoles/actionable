package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.util.DataSet;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.*;

/**
* <B>Filename:</B> PoolMgr.java<BR>
* <B>Date:</B> <BR>
* <B>Description:</B> <P>Handles object requests by caching objects.  Also handles 
* concurrency through locking mechanisms.</P>
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
*	---							Saleem Shafi		2/4/00			Adjusted several throws clauses and refined
*																							parameter types.
*	---							Saleem Shafi		2/5/00			Added setObjectContext() after clone() since
*																							clone no longer copies the contexts.
* ---             Saleem Shafi    2/29/00     Added logic to make some object immune to the PoolCleaner
* ---             Saleem Shafi    3/1/00      Added logic to automatically reset() any IRCollection objects upon request.
* ---             Saleem Shafi    3/3/00      Added construct() method logic to getCompObject() methods.
* ---				      Bob Makowski		2000-03-21	split PoolCleaner from inside PoolMgr.java
* ---             Zain Nemazie    5/8/00      Made a clone of unlocked to get iterator from to avoid concurrent mod exception
* BUG00327        Saleem Shafi    5/23/00     Started adding IRCollections to transactions so that they can be unlocked on a trans commit.
*/

public class PoolMgr implements IPoolMgr
{
  /** Singleton reference to PoolMgr. */
	private static PoolMgr _poolMgr = new PoolMgr();
  /** List of unlocked, cached objects. */
	private Map _unlocked;
  /** List of locked objects. */
  private Map _locked;
  /** List of object locks. */
	private Map _locks;
  /** Table of objects and the last time they were accessed. */
	private Map _request;
	

	//--------------------------- Private Constructors ---------------------------------
	/**
  Default constructor that initializes the pool and starts the thread to clean the pool.
  */
	private PoolMgr()
	{
	// Collections.synchronizedMap(new HashMap()) was added to avoid the ConcurrentModificationException
		_locked = Collections.synchronizedMap(new HashMap());
		_unlocked = Collections.synchronizedMap(new HashMap());
		_locks = Collections.synchronizedMap(new HashMap());
		_request = Collections.synchronizedMap(new HashMap());
    (new PoolCleaner()).start();
	}
	/**
	* This method searches the Request List for any object that has not been accessed in the 
  * specified amount of time and removes them from the pool.
	*/
	public void clean()
	{
	// This is the "specified amount of time.  This should probably be configureable but it isn't.
		long timeoutInterval = 60*60*1000; // 1 hour.
		Long currentTime = new Long(System.currentTimeMillis());
    long cutOff = currentTime.longValue() - timeoutInterval;
    Vector keysToDelete = new Vector();
	try
	{
	  for (Iterator it = _request.keySet().iterator(); it.hasNext(); )
	  {                                    // for each object, check the time interval
		  String key = (String)it.next();    // between right now and the last time it was accessed
		  Long lastRequest = (Long)_request.get(key);
		  if (lastRequest.longValue() < cutOff)
        keysToDelete.add(key);       // if it's been too long, clear it out
	  }

    for (Iterator i = keysToDelete.iterator(); i.hasNext(); )
    {
      String key = (String)i.next();
      removeKey(key);
    }
	}
	catch (ORIOException ignore) { /** the only reason we should get here is for an invalid key */ }
	}
  /**
  * Creates an instance of the given classname.  The default constructor is used to
  * construct the object.
  * @return A shell of an object of the given class.
  * @param classname The String value of the Java classname to instantiate.
  * @throws ClassNotFoundException This exception is thrown if the class specified was not found in the current classpath.
  * @throws InstantiationException This exception is thrown if there is an error creating an instance of the object.  Usually this is caused by a missing or private default constructor.
  * @throws IllegalAccessException This exception is thrown if the user does not have access to the class specified.
  */
	private IPoolable createShell(String classname)
	throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
	Class thisClass = Class.forName(classname);
	if (thisClass == null) throw new InstantiationException("Cannot instantiate a null class");
		return (IPoolable)thisClass.newInstance();
	}
	//--------------------------- Static Methods ---------------------------------
	/**
  Returns the singleton instance of the PoolMgr.
  @return The handle to the PoolMgr.
  */
	public static IPoolMgr getInstance()
	{
		return _poolMgr;
	}
	//--------------------------- IPoolMgr Methods ---------------------------------
  /**
  * Returns an object from the pool if there is no object id provided.  This method 
  * will simply return any instance of the classname in the pool or create one if
  * necessary.  The object that is returned is not accessible to other users.  This
  * acts just as the ConnectionPool did in Accolades 1.1.  Neither parameter may be null.
  * @param context the IObjectContext of the user making the request.
  * @param classname the Java classname of the class being requested.
  * @return An instance of the object being requested.
  * @throws OculusException 
  */
  public IPoolable getObject(IObjectContext context, String classname)
	  throws OculusException
	{
	if (context == null) throw new OculusException("ObjectContext parameter null in PoolMgr.getObject(IObjectContext,String).");
	if (classname == null) throw new OculusException("String parameter null in PoolMgr.getObject(IObjectContext,String).");
		IPoolable pObj = null;
	ICRMConnection conn = context.getConnection();
	if (conn == null) throw new OculusException("Could not create connection to CRM in PoolMgr.getObject(IObjectContext,String).");
		String key = keyGen(classname,conn.getUserIID());	// generate a key using the user IID instead of object IID

	try
	{
	  if (_locked.containsKey(key))
		pObj = (IPoolable)_locked.get(key);            // if the user already has an object of this class, return it to them
	  else                                              // otherwise
	  {
		String available = null;
		Class genClass = Class.forName(classname);        // search for an instance of it in the pool
		if (genClass == null) throw new OculusException("Could not find class: "+classname);
		//Attempt at removing concurrent modification exception
		//duplicate the _unlocked map in a synchronized block so it can complete uninterrupted
		HashMap mp;
		synchronized(_unlocked)
		{
			mp = new HashMap(_unlocked);
		}
		//use the duplicate to get an iterator; it shouldn't matter if unlocked is changed now
	  for (Iterator it = mp.keySet().iterator(); it.hasNext() && available==null;)
	  {			
		  Object possibleKey = it.next();
			if (genClass.isInstance(_unlocked.get(possibleKey)))
			  available = (String)possibleKey;              // if we found one, save the key
	  }
		
		if (available != null)                        // if we did find an instance of the class
		{
		  pObj = (IPoolable)_unlocked.get(available);      //get the object and remove it from the pool
		  _unlocked.remove(available);
		  pObj.setObjectContext(context);
		}
		else                                          // otherwise, create one
		{
		  pObj = createShell(classname);
		  if (pObj == null) throw new OculusException("Instance of class: "+classname+" returned as null.");
		  IDataSet args = new DataSet();
		  pObj.construct(context,args);
		}
		lock(pObj);                                    // lock it so no one else can use it
	  }
  }
	catch (OculusException ex) { throw ex; }
  catch (Exception exp) { throw new OculusException(exp); }  
  pObj.setObjectContext(context);
	return pObj;
	}
  /**
  * Returns an object from the pool if there is an id provided, but it is only being
  * requested for viewing purposes.  This method will return an object that cannot be
  * altered.  This method only deals with persistable objects, since it must do
  * to the data store to retrieve their state.  It also updates the request list.
  * @return A read-only handle to the requested object.
  * @param context the IObjectContext of the user making the request.
  * @param classname the Java classname of the class being requested.
  * @param args a set of arguments to help build the object.
  * @throws OculusException
  */
  public IPersistable getObject(IObjectContext context, String classname, IDataSet args)
	throws OculusException
	{
	if (context == null) throw new OculusException("ObjectContext parameter null in PoolMgr.getObject(IObjectContext,String,IDataSet).");
	if (classname == null) throw new OculusException("String parameter null in PoolMgr.getObject(IObjectContext,String,IDataSet).");
	if (args == null) throw new OculusException("To create a new object, you must use PoolMgr.getObject(IObjectContext,String,IDataSet,true).");
	
	IPersistable pObj = null;
	  IIID objID = args.getIID();
	  if (objID == null) throw new OculusException("Object IID expected in PoolMgr.getObject(IObjectContext,'"+classname+"',IDataSet).");
		String key = keyGen(classname,objID);		// generate a key for the object

	try
	{

    if (_locked.containsKey(key))                  // if it's already checked out
    {
      if (_locks.containsKey(key))
      {
      ILock lock = (ILock)_locks.get(key);
      if (lock == null) throw new OculusException("Invalid Lock object in PoolMgr.getObject(IObjectContext,String,IDataSet).");
      ICRMConnection conn = context.getConnection();
      if (conn == null) throw new OculusException("Error connecting to CRM in PoolMgr.getObject(IObjectContext,String,IDataSet).");
      IIID userID = conn.getUserIID();
      if (userID == null) throw new OculusException("Invalid User ID on CRM Connection.");
      if (userID.equals(lock.getLockHolderIID()))
        return getObject(context,classname,args,true);
      }
    }




	  if (_unlocked.containsKey(key))          // if its in the cache, return it
		pObj = (IPersistable)_unlocked.get(key);
	  else                                    // otherwise
	  {
		try
		{
		  pObj = (IPersistable)createShell(classname);  // create an IPoolable shell
		  if (pObj == null) throw new OculusException("Instance of class: "+classname+" returned as null.");
		  pObj.construct(context,args);
		  if (pObj.getPersState() == null || pObj.getPersState().equals(PersState.UNINITED))
			pObj.load();                          // get data from the data store
	
		  if (!(pObj instanceof IPoolableNotCachable))  // and put it in the view-only cache
			_unlocked.put(key,pObj);            // do not cache collections
		  else
      {
        if (pObj instanceof IRCollection)
        	((IRCollection)pObj).reset();       // reset them.
      }
		}
		catch (ClassNotFoundException exp)
		{
		  throw new ClassNotFoundException("The class name "+classname+" could not be found.");
		}
	  }
    pObj.setObjectContext(context);
	  pObj = (IPersistable)pObj.dolly();            // return a clone so everyone has their own copy
    pObj.setObjectContext(context);
	  if (pObj == null) throw new OculusException("Clone method returned null.");
    pObj.setObjectContext(context);
	  if (pObj.isRemoveable())
		_request.put(key,new Long(System.currentTimeMillis()));  // record when the request was made
	}
  catch (OculusException ex) { throw ex; }
	catch (Exception exp) { throw new OculusException(exp); }
		return pObj;
	}
  /**
  * Returns an object from the pool if there is an id provided and it is being
  * requested for editing purposes.  This method will return an object that can only be
  * altered by the person making the initial request.  If the object is already being
  * held for edit by someone else, an exception is thrown.  This method only deals with 
  * persistable objects, since it must do to the data store to retrieve their state.  
  * @return A read-write handle to the requested object.
  * @param context the IObjectContext of the user making the request.
  * @param classname the Java classname of the class being requested.
  * @param editable specifies whether or not the object is being requested for edit priviledges.
  * @param args a set of arguments to help build the object.
  * @throws OculusException
  */
  public IPersistable getObject(IObjectContext context, String classname, IDataSet args, boolean editable)
	throws OculusException
	{
	if (context == null) throw new OculusException("ObjectContext parameter null in PoolMgr.getObject(IObjectContext,String,IDataSet,boolean).");
	if (classname == null) throw new OculusException("String parameter null in PoolMgr.getObject(IObjectContext,String,IDataSet,boolean).");
	if (args == null && editable == false) throw new OculusException("To create a new object, you must use PoolMgr.getObject(IObjectContext,String,IDataSet,true).");

		IPersistable pObj = null;
	try
	{
	  if (!editable)                                // if request is not for edit
		return getObject(context,classname,args);    // refer to view-only request
	  else                                          // if request IS for edit
	  {
		String key = null;
		if (args != null)                           // not creating a new object.
		{
		  IIID objID = args.getIID();
		  if (objID != null)
			key = keyGen(classname,objID);          // generate a key for the object
		  // WHY IS THIS COMMENTED OUT?
		  // else
		  //   throw new OculusException("Object ID expected in PoolMgr.getObject(IObjectContext,String,IDataSet,boolean).");
		}
		if (key != null && _locked.containsKey(key))                  // if it's already checked out
		{
		  if (_locks.containsKey(key))
		  {
			ILock lock = (ILock)_locks.get(key);
			if (lock == null) throw new OculusException("Invalid Lock object in PoolMgr.getObject(IObjectContext,String,IDataSet,boolean).");
			ICRMConnection conn = context.getConnection();
			if (conn == null) throw new OculusException("Error connecting to CRM in PoolMgr.getObject(IObjectContext,String,IDataSet,boolean).");
			IIID userID = conn.getUserIID();
			if (userID == null) throw new OculusException("Invalid User ID on CRM Connection.");
			if (!userID.equals(lock.getLockHolderIID()))
								  // if i'm not the checker-outer
								  //   then throw the LockException
			  throw new ObjectLockedException("Someone else has a lock on : "+key);
		  }
		  pObj = (IPersistable)_locked.get(key);            // get the obj
      pObj.setObjectContext(context);
		}
		else                                            // if it's not checked out
		{
		  if (key != null && _unlocked.containsKey(key))                  // if it's in the cache
		  {
			pObj = (IPersistable)_unlocked.get(key);        // get a copy of it            
			if (pObj.getPersState() != null && pObj.getPersState().equals(PersState.PARTIAL))      // if it's only partially loaded
			  pObj.load();                                          // we'd better get a fresh copy before editing it
      _unlocked.put(key,pObj);
			pObj.setObjectContext(context);
      pObj = (IPersistable)pObj.dolly();
			if (pObj == null) throw new OculusException("Clone method returned null.");
			pObj.setObjectContext(context);
		  }
		  else                                            // if it's not in the cache
		  {
			pObj = (IPersistable)createShell(classname);
			if (pObj == null) throw new OculusException("Instance of class: "+classname+" returned as null.");
			pObj.construct(context,args);
			if (pObj.getPersState() == null || !pObj.getPersState().equals(PersState.NEW))
			  pObj.load();                          // get data from the data store
			if (key == null)
			  key = keyGen(classname, pObj.getIID());
			if (!(pObj instanceof IPoolableNotCachable))
			  _unlocked.put(key,pObj);                        // and put it in the cache
		  }
      pObj.setObjectContext(context);
		  pObj = (IPersistable)pObj.dolly();                // make a deep-copy as an edit buffer
		  if (pObj == null) throw new OculusException("Clone method returned null.");
		  pObj.setObjectContext(context);
//          This was commented out so that we can lock collections.
//          if (!(pObj instanceof IRCollection))
		  lock(pObj);                                      // lock it so others can't edit it
		  if (pObj instanceof IRCollection) 
		  {
			((IRCollection)pObj).setAsLocked();
			((IRCollection)pObj).reset();
		  }
		  _request.remove(key);
		}
	  }
	}
  catch (OculusException ex) { throw ex; }
	catch (Exception exp) { throw new OculusException(exp); }
		return pObj;
	}
	/**
  Returns true if the object is locked.
  @param obj The object that is being tested for locks.
  @return true, if the object is locked.
  */
	public boolean isLocked(IPoolable obj)
	{
		return _locked.containsValue(obj);
	}
	/**
	* Returns the hash key for the object given.  If it is a persistable object
	* then it uses the objectIID and classname, otherwise it uses the classname
	* and the userIID who is using the object.
  * @param obj The object to generate a key for.
  * @return the hash key value for the object.
  * @throws ORIOException
	*/
	private String keyGen(IObject obj)
		throws ORIOException
	{
	if (obj == null) throw new ORIOException("Cannot generate a key for a null object.");
	Class thisClass = obj.getClass();
	if (thisClass == null) throw new ORIOException("The class for this object is invalid.");
		String classname = obj.getClass().getName();
	if (classname == null) throw new ORIOException("The class name for this object is invalid.");

		if (obj instanceof IPersistable)										// if persistable, then use objIID
			return keyGen(classname,((IPersistable)obj).getIID());
		else																								// otherwise use userIID
	{
	  IObjectContext context = obj.getObjectContext();
	  if (context == null) throw new ORIOException("ObjectContext for this object is invalid.");
	  ICRMConnection conn = context.getConnection();
	  if (conn == null) throw new ORIOException("The CRM Connection for this object is invalid.");
	  return keyGen(classname,conn.getUserIID());
	}
	}
  //-------------------------- Private Methods ---------------------------------
	
  /**
  * Returns the hash key for the classname and id given.  The formula is simply:
  * <code>classname__IID</code>
  * @param classname the class name of the object.
  * @param objID the IIID of the object.
  * @return the hash key value for the object.
  */
	private String keyGen(String classname, IIID objID)
	{
		return classname+"__"+objID.getLongValue();
	}
  /**
  * Locks the object from being edited by anyone other than the user holding the lock.
  * If the object is IPersistable, then it also adds it to the user's transaction.
  * @param obj the object that is being locked.
  * @throws OculusException
  */
	public void lock(IPoolable obj)
		throws OculusException
	{
	if (obj == null) throw new OculusException("Null object cannot be locked.");
		String key = keyGen(obj);												// generate the key
	if (key == null) throw new OculusException("Invalid key generation.");
		_locked.put(key,obj);														// add it to the locked list
		ILock lock = new Lock(obj.getObjectContext(),obj);
	if (lock == null) throw new OculusException("Invalid lock generation.");
		_locks.put(key,lock);														// create an ILock object for the object
		if (_request.containsKey(key))
			_request.remove(key);													// remove the object from the requests so it doesn't get cleaned
		
		if (obj instanceof IPersistable)								// if it's IPersistable
		{																										// add it to the transaction
	  ITransactionMgr tm = TransactionMgr.getInstance();
	  if (tm == null) throw new OculusException("Error connecting to the Transaction Manager.");
			ITransaction trans = tm.getTransaction(obj.getObjectContext());
	  if (trans == null) throw new OculusException("Invalid transaction for this user.");
			trans.addObject((IPersistable)obj);
		}
	}
  /**
  * Removes an object from the pool all-together.  This is normally used when the
  * pool has invalid data and the data needs to be re-loaded from the data store.
  * @param obj object to be removed from the pool.
  * @throws ORIOException This exception is thrown if the key generation is invalid or the obj parameter is invalid.
  */
	public void remove(IPoolable obj)
		throws ORIOException
	{
	if (obj == null) throw new ORIOException("Cannot remove null object from the pool.");
		String key = keyGen(obj);
	if (key == null) throw new ORIOException("Invalid key generation.");
		if (_locked.containsKey(key))										// remove the object from every list
			_locked.remove(key);
		if (_locks.containsKey(key))
			_locks.remove(key);
	removeKey(key);
	}
	/**
	* Removes an object from the viewable and request lists.  This is called by clean() 
  * @param key the key of the object to be removed.
	*/
	private void removeKey(String key)
	throws ORIOException
	{
	if (key == null) throw new ORIOException("Cannot remove null key value from pool.");
		if (_unlocked.containsKey(key))
			_unlocked.remove(key);
		if (_request.containsKey(key))
			_request.remove(key);
	}
  public void replace(IPersistable obj)
	throws ORIOException
  {
	String key = keyGen(obj);
	_unlocked.put(key,obj);
  }  
  /**
  * Returns an object that was retrieve using getObject(IObjectContext, String).
  * Hence, only non-IPersistable objects, like SRConnection, should use this method.
  * @param obj the object that is being returned to the pool.
  * @throws ORIOException This exception is thrown if the key generation is invalid or the obj parameter is invalid.
  */
	public void returnObject(IPoolable obj) throws ORIOException
	{
	if (obj == null) throw new ORIOException("Cannot return null object to the pool.");
		String key = keyGen(obj);												// generate a key
	if (key == null) throw new ORIOException("Invalid key generation.");
		if (_locked.containsKey(key))
		{
			unlock(obj);																	// unlock the object
			_unlocked.put(key,obj);												// make it available to others
	  if (obj.isRemoveable())
			_request.put(key,new Long(System.currentTimeMillis()));	// set the request time
		}
	}
  /**
  * Removes the lock on an object and makes it available for other to edit.
  * @param obj the object to unlock
  * @throws ORIOException This exception is thrown if the key generation is invalid or the obj parameter is null.
  */
	public void unlock(IPoolable obj)
		throws ORIOException
	{
	if (obj == null) throw new ORIOException("Cannot unlock null object.");
	String key = keyGen(obj);                        // generate a key
	if (key == null) throw new ORIOException("Invalid key generation.");
		if (_locked.containsKey(key))										// remove from the locked list
			_locked.remove(key);
		if (_locks.containsKey(key))										// remove the ILock object
			_locks.remove(key);
	}
  /**
  * Updates the cache to reflect changes made while being locked.  If the object was
  * deleted, it is removed from the pool.  Otherwise, the altered object replaces
  * the viewable one so that any new requests will return the new state of the object.
  * @param obj the object to be updated in the pool.
  * @throws ORIOException This exception is thrown if the obj parameter is null or the key generation is invalid.
  */
	public void updateCache(IPersistable obj)
		throws ORIOException
	{
	if (obj == null) throw new ORIOException("Cannot update null object.");
		if (obj.getPersState() != null && obj.getPersState().equals(PersState.DELETED))
		{																							// if delete, remove from pool
			remove(obj);
		}
		else																					// otherwise
		{
			String key = keyGen(obj);
	  if (key == null) throw new ORIOException("Key generation invalid.");
			if (_locked.containsKey(key))										// if it's locked
			{
      if (!(obj instanceof IPoolableNotCachable))
				_unlocked.put(key,obj);												// update the cache.
			if (obj.isRemoveable())
		  		_request.put(key,new Long(System.currentTimeMillis()));
			}
		}
	}
}