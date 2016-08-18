package com.oculussoftware.util;

/** Base class for type-safe ints
*/
public class TypeSafeInt
{
  private int _safeInt;
  
  protected TypeSafeInt(int s)
  {
    _safeInt = s;
  }

  public int getIntValue()
  {
    return _safeInt;
  };
  
  public boolean equals(Object obj)
  {
    if ( ! (obj instanceof TypeSafeInt) )
      return false;
    
    return (((TypeSafeInt) obj).getIntValue() == _safeInt);
  }
}