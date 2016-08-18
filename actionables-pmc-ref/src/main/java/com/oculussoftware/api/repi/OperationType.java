package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;

/** An  enumeration.
*/
public class OperationType extends IntEnum
{
  public static final OperationType VIEW  = new OperationType(1);
  public static final OperationType EDIT =  new OperationType(2);
  public static final OperationType CREATE =  new OperationType(3);
  
  private OperationType(int i)
  {
    super(i);
  }    
  
  
  /**
  I need a method that returns a OperationType object given its code.
    
  Alok  
  */
  public static OperationType getInstance(int i)
  {
    OperationType prim = null;
    switch (i)
    {
      case 1:prim=OperationType.VIEW;break;
      case 2:prim=OperationType.EDIT;break;
      case 3:prim=OperationType.CREATE;break;
    }
    
    return prim;
  }
}