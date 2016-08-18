package com.oculussoftware.util;

/** Base class for type-safe ints
*/
public class StringEnum
{
  private String _safeString;
  
  protected StringEnum(String s)
  {
    _safeString = s;
  }

  public String getStringValue()
  {
    return _safeString;
  }
  
  public boolean equals(Object obj)
  {
    if ( ! (obj instanceof StringEnum) )
      return false;
    return (((StringEnum)obj).getStringValue().equals(_safeString));
  }
 
	 public String toString()
	 {
	  return _safeString;
	 }

}