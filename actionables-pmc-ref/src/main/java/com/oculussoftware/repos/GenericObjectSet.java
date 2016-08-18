package com.oculussoftware.repos;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* $Workfile: GenericObjectSet.java $
* Date:      5/11/2000  
* Description: Generic collection for holding IRObjects for read only.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

/*
* $History: GenericObjectSet.java $
 * 
 * *****************  Version 5  *****************
 * User: Sshafi       Date: 8/02/00    Time: 2:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:29a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Added a conenience overload of load( ) that loads the collection from
 * and IDataSet.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Move ROInfo to its own class in .../util/
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Inital create.
*/

public class GenericObjectSet implements IGenericObjectCollection
{
  private static final String COL_OBJECTID = "OBJECTID";
  
  //------------------------------------------------------------
  //------------------------------------------------------------
	private IObjectContext _context = null;
  private MC             _mc      = null;
  private List           _items   = new Vector();
  private Iterator       _itr     = null;
  
  //------------------------------------------------------------
  // Public constructor(s)
  //------------------------------------------------------------
	/** Default constructor just initializes the list */
	public GenericObjectSet(IObjectContext context) throws OculusException
	{
    _context = context;
    _mc = new MC(context);
	}

  //------------------------------------------------------------
  // Public methods
  //------------------------------------------------------------
	/**
	*	Resets the cursor to the beginning of the list
	*/
  public Object next()
    throws OculusException
  {
    Object retObj = null;
    ROInfo roi = (ROInfo) _itr.next();
    if (roi != null) {
      retObj = _mc.getObj(roi.getType(), roi.getId());
    }
    return retObj;
  }
  
  public Object get(int index)
    throws OculusException
  {
    Object retObj = null;
    ROInfo roi = (ROInfo)_items.get(index);
    if (roi != null) {
      retObj = _mc.getObj(roi.getType(), roi.getId());
    }
    return retObj;
  }

  public void remove(int index)
    throws OculusException
  {
    _items.remove(index);
  }
  
  public boolean contains(Object element)
  {
    // is this important?
    return false;
  }
  public void add(Object element) throws AccessException, ORIOException
  {
    if (element instanceof ROInfo) {
      _items.add(element);
    }
    else {
      throw new ORIOException("Collection only takes ROInfo objects.");
    }
  }
  
  public void add(long objectID, long classID)
    throws OculusException
  {
    add(new ROInfo(IDCONST.getInstance(classID), objectID));
  }
  
  public int size(){ return _items.size(); }
  public void setAsLocked(){ }
  public IRCollection reset()
  {
    _itr = _items.iterator();
    return this;
  }
  public boolean remove(Object element) throws AccessException, ORIOException
  {
    return false;
  }
  public boolean isParent() throws ORIOException{ return false; }
  public boolean isOrdered() throws ORIOException{ return true; }
  public boolean isEmpty(){ return _items.isEmpty(); }
  public boolean hasNext(){ return _itr.hasNext(); }
  public Collection getItems(){ return _items; }
  public void addAll(IRCollection elements) throws AccessException, ORIOException{ }
  
  public IPersistable setPersState(PersState state){ return this; }
  public IPersistable setIID(IIID objIID) throws ORIOException{ return this; }
  public IPersistable save() throws OculusException{ throw new OculusException("Not a savable collection."); }
  public IPersistable load() throws OculusException{ return this; }
  public IPersistable load(IDataSet data, String oidSyn, String cidSyn) 
    throws OculusException
  {
    while (data.next())
      add(data.getLong(oidSyn), data.getLong(cidSyn));
    return this;
  }
  
  public PersState getPersState(){ return (PersState) null; }
  public IIID getIID() throws ORIOException{ return null; }
  public IPersistable delete() throws OculusException{ return this; }

  public IObject setObjectContext(IObjectContext context) throws ORIOException{ return this; }
  public IObjectContext getObjectContext() throws ORIOException{ return _context; }
  public IGUID getGUID(){ return (IGUID) null;}
  
  public Object dolly( ) { return null; }
  public boolean isLocked( ) { return false; }
  public boolean isRemoveable( ) { return false; }
  
  public IPoolable construct(IObjectContext context, IDataSet ds) { return this; }
}