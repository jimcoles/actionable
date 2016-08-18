package com.oculussoftware.util;

/** Base class for type-safe ints
*/
public class IntEnum
{
  private int _safeInt;
  
  protected IntEnum(int s)
  {
    _safeInt = s;
  }

  public int getIntValue()
  {
    return _safeInt;
  }
  
  public boolean equals(Object obj)
  {
    if ( ! (obj instanceof IntEnum) )
      return false;
    
    return (((IntEnum) obj).getIntValue() == _safeInt);
  }
  
   //override Object hashcode
   public int hashCode()
   {
     Integer i = new Integer(_safeInt);
     return i.hashCode();
   }
		
		public String toString()
		{
		  return ""+_safeInt;
		}

}