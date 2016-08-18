package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;

/** An  enumeration.
*/
public class UnitPosition extends IntEnum
{
  public static final UnitPosition LEFT   = new UnitPosition(1);
  public static final UnitPosition RIGHT   = new UnitPosition(2);
 
  
  
  private UnitPosition(int i)
  {
    super(i);
  }    
  
 public static UnitPosition getInstance(int i)
  {
    UnitPosition prim = null;
    switch (i)
    {
      case 1:prim=UnitPosition.LEFT;break;
      case 2:prim=UnitPosition.RIGHT;break;      
    }
    
    return prim;
  }
 
  public String toString()
  {
    
    
  String s = null;
    int i = getIntValue();
    switch (i)
    {
      case 1:s="left of the value";break;      
      case 2: s="right of the value";break;      
      
    }
  return s; 
    
    
  }   

}