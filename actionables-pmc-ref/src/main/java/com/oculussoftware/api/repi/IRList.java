package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import java.util.*;

/** An IRList is an ordered collection good for representing collections
*   for which the client must control order and expect that order to persist.
*   
*   Notice that these methods have the notion of an 'index' which does
*   not exist in the basic IRCollection (which is sorted).    
*/
public interface IRList extends IRCollection
{
  /** Adds at an object at the specified index. */
  public void   add(int index, Object obj)
    throws AccessException, ORIOException;
    
  /** Returns the object at the specified index. */
  public Object get(int index)
    throws AccessException, ORIOException;

  /** Removes the object at the specified index. */
  public Object  remove(int index)
    throws AccessException, ORIOException;

  /**  NOTE: This is same as 'set()' in Java collections framework. */
  public Object replace(int index, Object element)
    throws AccessException, ORIOException;
  
  public int indexOf(Object element);
}