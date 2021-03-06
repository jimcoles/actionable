package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;

import com.oculussoftware.repos.util.*;

import java.math.*;
import java.util.*;
import java.sql.*;

/**
* Filename:    BMModelElementColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public abstract class BMModelElementColl implements IRModelElementColl, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  protected IObjectContext _context;
  protected IGUID          _guid;
  protected IIID           _iid;
  protected boolean        _isLocked = false;
  protected boolean        _isOrdered = false;
  
  protected ArrayList _coll;
  protected Iterator _ids;
  
  /**
  *
  */
  protected BMModelElementColl() throws OculusException
  {
    _guid = new GUID();
    _coll = new ArrayList();
  }//
  
  //-------------------------- IRCollection --------------------
  
  public boolean isOrdered() throws ORIOException
  { return _isOrdered; }
    
  public boolean isParent() throws ORIOException
  { return false; }
    
  public void add(IIID element) throws AccessException, ORIOException
  { _coll.add(element); }
    
  public boolean remove(IIID element) throws AccessException, ORIOException
  { return _coll.remove(element); }

  public void add(Object element) throws AccessException, ORIOException
  { _coll.add(element); }
    
  public boolean remove(Object element) throws AccessException, ORIOException
  { return _coll.remove(element); }

  public boolean isEmpty()
  { return _coll.isEmpty(); }
  
  public boolean contains(Object element)
  { return _coll.contains(element); }
  
  public int size()
  { return _coll.size(); }
  
  public Object next() throws OculusException
  { 
    Object nextObject = null;
    while (nextObject == null && hasNext()) 
    {
      IIID IID = (IIID)_ids.next();  
      nextObject = getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),IID,isLocked());
    }//end while
    return nextObject; 
  }//
  
  public boolean hasNext()
  { return _ids.hasNext(); }
  
  public void setAsLocked()
  { _isLocked = true; }
  
  public IRCollection reset()
  { 
    _ids = _coll.iterator();
    return this; 
  }//
  
  public void     addAll(IRCollection elements)
    throws AccessException, ORIOException
  { throw new ORIOException("addAll not implemented");}
    
  public Collection getItems()
  { return null; }
  
  abstract protected String getClassName();
   
  //------------------------------ IPersistable ----------------------
  
  public IPersistable setPersState(PersState state)
  { return null; }
  
  public PersState getPersState()
  { return null; }
  
  public IPersistable setIID(IIID objIID) throws ORIOException
  { _iid = objIID; return this; }
  
	abstract protected String getLoadQuery()
		throws ORIOException;
	
  public IPersistable load( ) throws OculusException
  { 
    IQueryProcessor qp = null; long testiid = 0;
    try
    {
      IRepository rep = getObjectContext().getRepository();
      IRConnection repConn = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
      qp = repConn.createProcessor();
      IDataSet results = qp.retrieve(getLoadQuery());
      while (results.next())
      {
        testiid = results.getLong("OBJECTID");
        IIID iid = rep.makeReposID(testiid);
        _coll.add(iid);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),results);
      }//end while
//      CRM.getInstance().returnDatabaseConnection(repConn);
      reset();
    }//end try
    catch (ORIOException sqlExp)
    { throw new OculusException("Error retrieving data from the database.("+testiid+")"+sqlExp.toString()); }
    finally { if(qp!=null) qp.close(); }
    return this;  
  }//end load
    
  public IPersistable save( ) throws OculusException
  { throw new ORIOException("This method (save) is not implemented for this class."); }

  public IPersistable delete( ) throws OculusException
  { throw new ORIOException("This method (delete) is not implemented for this class."); }
  
  //------------------------------ IPoolable --------------------------
  
  public IIID getIID() throws ORIOException
  { return _iid; }

  public boolean isLocked()
  { return _isLocked; }
  
  public boolean isRemoveable()
  { return true; }
  
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = null;
    if(args != null)
      iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);
    
    return this;
  }

  
  //------------------------------ IObject ----------------------------
  
  public IGUID  getGUID()
  { return _guid; }
  
  public IObjectContext getObjectContext()
  { return _context; }
  
  public IObject setObjectContext(IObjectContext context)
  { _context = context;  return this; }
  
}//end class