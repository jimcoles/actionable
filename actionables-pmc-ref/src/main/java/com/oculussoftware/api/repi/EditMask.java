package com.oculussoftware.api.repi; 

import java.util.BitSet;

/** Represents the completed edit state of an item at a given
*   instance.
*/
public final class EditMask
{
  private int _mask = 0;
  
  /** Private constructor */
  public EditMask(  )
  {
    
  }
  
  public EditMask setValue( int value)
  {
    _mask = value;
    return this;
  }
  
  public boolean exactly( int value )
  {
    return (_mask == value);
  }
  
  public boolean allTrue( int value )
  {
    return ((_mask & value) == value);
  }

  public boolean oneTrue( int value )
  {
    return (_mask & value) > 0;
  }

  public boolean inSync()
  {
    if (! oneTrue(EditState.NEW | EditState.DELETED | EditState.MODIFIED) )
      return true;
    else
      return false;
  }
}