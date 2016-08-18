package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;

/** UML 'Changeability' enumeration.
*/
public class Changeability extends IntEnum
{
  public static final Changeability NONE    = new Changeability(1);
  public static final Changeability FROZEN  = new Changeability(2);
  public static final Changeability ADDONLY = new Changeability(3);  
  
  private Changeability(int i)
  {
    super(i);
  }
}