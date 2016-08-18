package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

/** UML 'VisibilityKind' enumeration.
*/
public class VisibilityKind extends IntEnum
{
  public static final VisibilityKind PUBLIC    = new VisibilityKind(1);
  public static final VisibilityKind PROTECTED = new VisibilityKind(2);
  public static final VisibilityKind PACKAGE   = new VisibilityKind(3);
  public static final VisibilityKind PRIVATE   = new VisibilityKind(4);
  
  private VisibilityKind(int i)
  {
    super(i);
  }
}