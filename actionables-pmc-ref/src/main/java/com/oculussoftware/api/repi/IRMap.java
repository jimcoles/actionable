package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import java.util.*;

/** An IRMap is a collection that allows access by a key.  It does
*   not directly extend IRCollection, however a client may obtain an
*   IRCollection of the key set, the entry set, or the value set.
*/
public interface IRMap
{
  /**
  * Set key or create it if it doesn't exist.  If key does
  * not yet exist, and value is null, the implementation
  * creates a value.
  *
  * @return The value associated with the key after the put
  *  is complete.
  */
  public void put(Object key, Object value)
    throws OculusException;
    
  /**
  * Gets the object associated with the key.
  *
  * @return The object associated with the key or null if the
  *         key does not exist.
  */
  public Object get(Object key)
    throws OculusException;
    
  /** Provides collection view of the value set. */
  public Collection    values()
    throws ORIOException;
    
  /** Provides collection view of the key set. */
  public Set    keys()
    throws ORIOException;
  
  /** Provides collection view of the this map. */
  public Set    entries()
    throws ORIOException;
  
  public boolean  containsKey(Object key);
  public int size();

  public Map getMap();   
}