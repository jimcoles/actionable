package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

import java.util.*;

/** The base collection interface for an IRepository.  This behaves
*   essentially like a JCF SortedSet.
*/
public abstract interface IRCollection extends IPersistable, IPoolableNotCachable
{
  /**
  *  Returns true if the collection is ordered in which case it is safe
  *  to cast to an IRList.
  */
  public boolean    isOrdered()
    throws ORIOException;
    
  public boolean    isParent()
    throws ORIOException;
    
  /**
  *  Adds element to the colleciton.  For IRLists, adds element to the end
  *  of the list.
  */
  public void     add(Object element)
    throws AccessException, ORIOException;
    
  public void     addAll(IRCollection elements)
    throws AccessException, ORIOException;
    
  public Collection getItems();
  /** 
  * @return true if object was found in list.
  */
  public boolean  remove(Object element)
    throws AccessException, ORIOException;

    
    
  // -------------- info (operations) methods -------------------
  // These do not change the collection and therefore do not throw
  // exceptions.
  
  public boolean    isEmpty();
  
  public boolean    contains(Object element);
  
  public int        size();
  
  public Object next()
    throws OculusException;
  
  public boolean hasNext();
  
  public void setAsLocked();
  
  public IRCollection reset();//ER
  
//  /** Reloads the list from the data store. */
//  public IRCollection refresh()
//    throws OculusException;
//
//  /** Sets the filter on the list */    
//  public IRCollection setFilter()
//    throws OculusException;
//  
//  /** Sets the sort for the list */
//  public IRCollection setSort(Comparator sortCrit)
//    throws OculusException;
//  
//  /** Sets the group by for the list */
//  public IRCollection setGroupBy()
//    throws OculusException;
}