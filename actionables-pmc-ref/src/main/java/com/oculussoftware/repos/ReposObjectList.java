package com.oculussoftware.repos;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.rdb.*;

import java.util.*;

/**
* Filename:    ReposObjectList.java
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
*/

abstract public class ReposObjectList implements IRList, IPersistable
{
  protected String COL_OBJECTID = "OBJECTID";
  protected String TABLE = "DATAOBJECT";
  
  protected IObjectContext _context;              // the lists ObjectContext
  protected IGUID _guid;                          // the lists' GUID
  protected IIID _iid;                            // it's IID
  protected boolean _isLocked = false;
  protected boolean _preload = true;
  
  protected String _sortby;                        // sort by criteria
  protected String _groupby;                       // group by criteria
  protected String _filterby;                      // filter by criteria (ie WHERE clause)
  
  protected List _items;                          // list of items
  protected Iterator _ids;                        // cursor to current item


  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the list */
  public ReposObjectList() throws OculusException
  {
    _guid = new GUID();
    _items = new LinkedList();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected ReposObjectList(Comparator sortCrit) throws OculusException
  {
    _guid = new GUID();
    _items = new LinkedList();
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
  *  Resets the cursor to the beginning of the list
  */
  public IRCollection reset()
  {
    _ids = _items.listIterator();
    return this;
  }
  
  /**
  *  Reloads the entire list.
  */
  public IRList refresh()
    throws OculusException
  {
    _items = null;
    _items = new LinkedList();              // start the list from scratch
    load();
    return this;
  }
  
  /** sets the filter that this ProductList will use */
  public IRList setFilter()
    throws OculusException
  {
    return this;
  }

  /** sets the sort that this ProductList will use */
  public abstract IRCollection setSort(Comparator sortCrit)
    throws OculusException;
  
  /** sets the group by that this product list will use */
  public IRList setGroupBy()
    throws OculusException
  {
    return this;
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
  
  public boolean isRemoveable()
  {
    return true;
  }
    
  /**
  *  Adds element to the colleciton.  For IRLists, adds element to the end
  *  of the list.
  */
  public void add(Object iid)
    throws AccessException, ORIOException
  {
    _items.add(iid);
  }
    
    
  /**
  *  Adds element to the colleciton.  For IRLists, adds element to the end
  *  of the list.
  */
  public void add(int index, Object element)
    throws AccessException, ORIOException
  {
    _items.add(index, element);
  }

  /**
  *  Adds element to the colleciton.  For IRLists, adds element to the end
  *  of the list.
  */
  public Object replace(int index, Object element)
    throws AccessException, ORIOException
  {
    return _items.set(index, element);
  }
  
  public Object get(int index)
  {
    return _items.get(index);
  }
  
  public int indexOf(Object element)
  {
    return _items.indexOf(element);
  }

  public Object remove(int index)
  {
    return _items.remove(index);
  }

  /** 
  * @return true if object was found in list.
  */
  public boolean remove(Object iid)
    throws AccessException, ORIOException
  {
    return _items.remove(iid);
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


//----------------- IRPersistable Methods ------------------------------------
  /**
  *  Reads the list of products from the database.
  */
  public IPersistable load()
    throws OculusException
  {
    try
    {
      IRConnection repConn = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
      IQueryProcessor stmt = repConn.createProcessor();
      String query = getLoadQuery();
      IDataSet results = stmt.retrieve(query);
      IRepository repos = getObjectContext().getRepository();
      while (results.next())
      {
        long id = results.getLong(COL_OBJECTID);
        IIID iid = repos.makeReposID(id);
        _items.add(iid);
        if (preLoad())
        {
          results.setIID(iid);
          getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),results);
        }
      }
      stmt.close();
//      CRM.getInstance().returnDatabaseConnection(repConn);
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
  
  /**  */
  abstract public Object dolly() throws OculusException;

  /** Returns the objects IID */
  public IIID getIID()
  {
    return _iid;
  }
  
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