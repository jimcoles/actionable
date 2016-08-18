package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

/** UML 'ScopeKind' enumeration.
*/
public class ScopeKind extends IntEnum
{
  public static final ScopeKind STATIC   = new ScopeKind(1);
  public static final ScopeKind INSTANCE = new ScopeKind(2);
  
  private ScopeKind(int i)
  {
    super(i);
  }
}