/*************************************

A typical use of this class is to store list of model-elements loaded from the CRM
i.e. (attributes, classes, groups). In this case the objects stored in the list will be IIIDs.
However there are some scenarios where I need to store something other than an IIID in the list
and so it is possible to store this generic object too.


***/

package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;

import com.oculussoftware.repos.util.*;

import java.math.*;
import java.util.*;
import java.sql.*;

public abstract class BMModelElementList implements IRModelElementList, IPersistable
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
  protected String _className = null;
    
  /**
  *
  */
  protected BMModelElementList() throws OculusException
  {
    _guid = new GUID();
    _coll = new ArrayList();

  }//
  
  //------------------------------ IRModelElementList ----------------------------
  
  public IRModelElement getModelElement(int index) throws ORIOException
  {
    Object o = null;
    IRModelElement m=null;
    
   try
    {
     o = _coll.get(index); 
    if (o instanceof IIID) 
    {
      IIID IID = (IIID)o; 
      o = null;
      m = (IRModelElement)getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),IID,isLocked());
    }
    }
    catch(Exception ex) { throw new ORIOException(ex);} 
    return m;
  }
  
  public void setClassName(String s) { _className = s;}
  
  public boolean addAll(IRModelElementList ll) throws ORIOException
  {
   return _coll.addAll(ll.getList()); 
  }
  
  public boolean removeAll(IRModelElementList ll) throws ORIOException
  {
   return _coll.removeAll(ll.getList()); 
  }
  
  public java.util.List getList() { return _coll;}
  
  
   //------------------------------ IRList ----------------------------
  
  
  /** Adds at an object at the specified index. */
  public void   add(int index, Object obj) throws AccessException, ORIOException
  {
   
   _coll.add(index,obj);
    
  }
  
   
  /** Returns the object at the specified index. */
  // For lists which store the IIID of the model_element return the model-element.
  
  public Object get(int index) throws AccessException, ORIOException
  {
           
    return _coll.get(index);
  }

  
  /** Removes the object at the specified index. */
  public Object  remove(int index) throws AccessException, ORIOException
  {
    
    return _coll.remove(index); 
  }

  /**  NOTE: This is same as 'set()' in Java collections framework. */
  public Object replace(int index, Object element) throws AccessException, ORIOException
  {
    
   return  _coll.set(index,element); 
  }
  
  public int indexOf(Object element)
  {
    
    return _coll.indexOf(element);
  }
  
  public void     addAll(IRCollection elements)
    throws AccessException, ORIOException
  { throw new ORIOException("addAll not implemented");}
    
  public Collection getItems()
  { return null; }
  
  //-------------------------- IRCollection --------------------
  
  public boolean isOrdered() throws ORIOException
  { return _isOrdered; }
    
  public boolean isParent() throws ORIOException
  { return false; }
  
  /////////////////////

  public void add(Object element) throws AccessException, ORIOException
  { _coll.add(element); }
    
  public boolean remove(Object element) throws AccessException, ORIOException
  { return _coll.remove(element); }



/////////////////////////


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
  
  abstract protected String getClassName();
  
   
  //------------------------------ IPersistable ----------------------
  
  public IPersistable setPersState(PersState state)
  { return null; }
  
  public PersState getPersState()
  { return null; }
  
  public IPersistable setIID(IIID objIID) throws ORIOException
  { _iid = objIID; return this; }
  

  abstract protected String getLoadQuery()
  	throws OculusException;
  

  public IPersistable load( ) throws OculusException
  { 
    IQueryProcessor qp = null;
    try
    {
      
      IRConnection repConn = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
      qp = repConn.createProcessor();
      IDataSet results = qp.retrieve(getLoadQuery());
      while (results.next())
      {
        IIID id = new SequentialIID(results.getLong("OBJECTID"));
        _coll.add(id);
        results.setIID(id);
        getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),results);
      }//end while
//      CRM.getInstance().returnDatabaseConnection(repConn);
      reset();
    }//end try
    catch (ORIOException sqlExp)
    { throw new OculusException("Error retrieving data from the database."+sqlExp.toString()); }
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
  
  
   public IRPropertyMap convert2PropertyMap() throws ORIOException
  {
    return null; 
  }
  
  
}//end class