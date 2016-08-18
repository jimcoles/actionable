package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum; 

/** Typesafe constants relating the persistence state of an item.
*   The integers must be 1...n.
*/
public final class EditState
{
  public static final int NUM_STATES = 4;
  
  public static final int NEW      = 1;
  public static final int MODIFIED = 2;
  public static final int DELETED  = 4;
  public static final int LOADED   = 8;
}