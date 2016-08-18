package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

/** UML 'ScopeKind' enumeration.
*/
public class LinkKind extends IntEnum
{
  public static final LinkKind WORKFLOW_DEP  = new LinkKind(0);
  public static final LinkKind FUNCTIONAL_DEP       = new LinkKind(1);
  public static final LinkKind BRANCH = new LinkKind(2);
  public static final LinkKind INPUT = new LinkKind(3);
  public static final LinkKind PROBLEMSTATEMENT = new LinkKind(4);
  
  
  private LinkKind(int i)
  {
    super(i);
  }
  
   public static LinkKind getInstance(int i)
  {
    LinkKind prim = null;
    switch (i)
    {
      case 0:prim=LinkKind.WORKFLOW_DEP;break;
      case 1:prim=LinkKind.FUNCTIONAL_DEP;break;
      case 2:prim=LinkKind.BRANCH;break;
      case 3:prim=LinkKind.INPUT;break;
      case 4:prim=LinkKind.PROBLEMSTATEMENT;break;
    }
    
    return prim;
  }
 

}