package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;

/** UML 'ScopeKind' enumeration.
*/
public class Multiplicity extends IntEnum
{
  public static final Multiplicity M0_1 = new Multiplicity(1, true,  false);
  public static final Multiplicity M1_1 = new Multiplicity(2, false, false);
  public static final Multiplicity M0_M = new Multiplicity(3, true,  true);
  public static final Multiplicity M1_M = new Multiplicity(4, false, true);

  private boolean _optional;
  private boolean _many;
  
  private Multiplicity(int i, boolean opt, boolean many)
  {
    super(i);
    _optional = opt;
    _many = many;
  }
  
  public boolean isOptional()
  {
    return _optional;
  }
  
  public boolean isMany()
  {
    return _many;
  }
}