package com.oculussoftware.repos;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.rdb.*;

import java.util.*;

/**
* Filename:    ReposObjectColl.java
* Date:        
* Description: Handles a collection of IRObjects
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
* ---             Saleem Shafi    2/17/00     Changed COL_OBJECTID to 1 in the load() method to make it more generic.
* ---             Egan Royal      2/29/00     reset returns an IRCollection
* ---             Zain Nemaze     3/1/00      Added COL_DELETESTATE
*/

abstract public class ReposObjectColl implements IRCollection, IPersistable
{
  protected String COL_OBJECTID = "OBJECTID";
  protected String COL_DELETESTATE = "DELETESTATE";  
  protected String TABLE = "DATAOBJECT";
  
	protected IObjectContext _context;							// the lists ObjectContext
	protected IGUID _guid;													// the lists' GUID
	protected IIID _iid;														// it's IID
  protected boolean _isLocked = false;
  protected boolean _preload = true;
	
	protected String sortby;												// sort by criteria
	protected String groupby;												// group by criteria
	protected String filterby;											// filter by criteria (ie WHERE clause)
	
	protected SortedSet _items;									    // list of items
	protected Iterator _ids;												// cursor to current item


	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public ReposObjectColl() throws OculusException
	{
		_guid = new GUID();
		_items = new TreeSet();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected ReposObjectColl(Comparator sortCrit) throws OculusException
  {
    _guid = new GUID();
    _items = new TreeSet(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return "SELECT "+COL_OBJECTID+" FROM "+TABLE;
  }

  abstract protected String getClassName();

  protected void setPreLoad(boolean preload) { _preload = preload; }
  protected boolean preLoad() { return _preload; }

	//----------------- IBusinessObjectList Methods ------------------------------------
	
	/**
	*	Resets the cursor to the beginning of the list
	*/
  public IRCollection reset()
  {
  	_ids = _items.iterator();
  	return this;
  }
  
  /**
  *	Reloads the entire list.
  */
  public IRCollection refresh()
		throws OculusException
  {
  	_items = null;
  	_items = new TreeSet();							// start the list from scratch
  	load();
  	return this;
  }
  
  /** sets the filter that this ProductList will use */
  public IRCollection setFilter()
    throws OculusException
  {
  	return this;
	}

	/** sets the sort that this ProductList will use */
  public abstract IRCollection setSort(Comparator sortCrit)
    throws OculusException;
	
	/** sets the group by that this product list will use */
  public IRCollection setGroupBy()
    throws OculusException
  {
  	return this;
	}


//----------------- IRCollection Methods ------------------------------------
  /**
  *  Returns true if the collection is ordered in which case it is safe
  *  to cast to an IRList.
  */
  public boolean isOrdered()
    throws ORIOException
	{
		return true;
	}

  public boolean isParent()
    throws ORIOException
	{
		return false;
	}
    
  /**
  *  Adds element to the colleciton.  For IRLists, adds element to the end
  *  of the list.
  */
  public void add(Object element)
    throws AccessException, ORIOException
	{
		_items.add(element);
	}

  public void     addAll(IRCollection elements)
    throws AccessException, ORIOException
  {
    _items.addAll(elements.getItems());
  }

  public Collection getItems()
  {
    return _items;
  }
    
  /** 
  * @return true if object was found in list.
  */
  public boolean remove(Object element)
    throws AccessException, ORIOException
	{
		return _items.remove(element);
	}
  
  public boolean isEmpty()
	{
		return _items.isEmpty();
	}
  
  public boolean contains(Object element)
	{
		return _items.contains(element);
	}
  
  public int size()
	{
		return _items.size();
	}

  public Object next()
    throws OculusException
  {
    Object nextObject = null;
    while (nextObject == null && hasNext())          // as long as we need to and can
    {
      IIID IID = (IIID)_ids.next();                  // get the next IProduct
      nextObject = getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),IID,isLocked());
    }
    return nextObject;
  }

	public boolean hasNext()
	{
		return _ids.hasNext();
	}

  public void setAsLocked() { _isLocked = true; }


//----------------- IRObject Methods ------------------------------------
//  /** i don't know what this does */
//  public IRObject getParentObject()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRCollection  getParentContainers()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public String getName()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRElement setName(String name)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public String getDescription()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRElement setDescription(String name)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRClass  getDefnObject()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRState  getStateObject()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRObject setStateObject(IRState state)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public boolean childExists(IIID iid)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRLinkCollMap  getAllLinksFrom()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRCollection   getAllLinksFrom(IRAssoc assoc)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRLinkCollMap   getAllLinksTo()
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRCollection    getLinksTo(IRAssoc assoc)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRPropertyMap getProperties( )
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
//  /** i don't know what this does */
//  public IRObject setChildObject(IRAssoc metaassoc, IRObject obj)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//
//  /** i don't know what this does */
//  public IRObject setProperty(IRAttribute metaprop, Object value)
//    throws ORIOException
//  {
//    throw new ORIOException("This method not implemented for this class.");
//  }
//  
  //--------------- IRPersistable Methods ------------------------------------
  /** Returns the objects IID */
  public IIID getIID()
  {
    return _iid;
  }
  
	/**
	*	Reads the list of products from the database.
	*/
	public IPersistable load()
		throws OculusException
	{
		try
		{
      IObjectContext context = getObjectContext();
      IRepository repos = context.getRepository();
      IRConnection repConn = repos.getDataConnection(context);
			IQueryProcessor stmt = repConn.createProcessor();
      String query = getLoadQuery();
			IDataSet results = stmt.retrieve(query);
			while (results.next())
			{
				IIID iid = repos.makeReposID(results.getLong(COL_OBJECTID));
				_items.add(iid);
        if (preLoad())
        {
          results.setIID(iid);
          context.getCRM().getCompObject(context,getClassName(),results);
        }
			}
			stmt.close();
//			context.getCRM().returnDatabaseConnection(repConn);
			reset();
		}
		catch (ORIOException sqlExp)
		{
			throw new OculusException("Error retrieving data from the database."+sqlExp.toString());
		}
		return this;
	}

  /** i don't know what this does */
	public IPersistable save()
    throws ORIOException
	{
    throw new ORIOException("This method not implemented for this class.");
	}

  /** i don't know what this does */
	public IPersistable delete()
    throws ORIOException
	{
    throw new ORIOException("This method not implemented for this class.");
	}

  /** i don't know what this does */
  public IPersistable setPersState(PersState state)
	{
    return null;
	}
	
  /** i don't know what this does */
  public PersState getPersState()
	{
    return null;
	}
	
	/** Sets the product list's IID */
	public IPersistable setIID(IIID iid)
	{
		_iid = iid;
		return this;
	}
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns the lock on this object */
  public boolean isLocked()
  {
    return _isLocked;
  }
	
  public boolean isRemoveable()
  {
    return true;
  }
  
//  /**  */
//  abstract public Object clone();

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);

    setPreLoad(!args.containsKey("NOPRELOAD"));

    return this;
  }


//----------------- IObject Methods ------------------------------------
	/** Returns the object's GUID */
	public IGUID getGUID()
	{
		return _guid;
	}

	/** Set's the object's ObjectContext */	
	public IObject setObjectContext(IObjectContext context)
	{
		_context = context;
		return this;
	}
	
	/** Get the object's ObjectContext */
	public IObjectContext getObjectContext()
	{
		return _context;
	}
}