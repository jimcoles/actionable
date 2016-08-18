package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

/** UML 'ScopeKind' enumeration.
*/
public class PSPKind extends IntEnum
{
  public static final PSPKind NONE        = new PSPKind(0);
  public static final PSPKind STRING      = new PSPKind(1);
  public static final PSPKind INPUTSTREAM = new PSPKind(2);
  
  
  private PSPKind(int i)
  {
    super(i);
  }
  
   public static PSPKind getInstance(int i)
  {
    PSPKind prim = null;
    switch (i)
    {
      case 0:prim=PSPKind.NONE;break;
      
      //Full means eveything read,edit & delete
      case 1:prim=PSPKind.STRING;break;
      case 2:prim=PSPKind.INPUTSTREAM;break;
    }
    
    return prim;
  }
 

}