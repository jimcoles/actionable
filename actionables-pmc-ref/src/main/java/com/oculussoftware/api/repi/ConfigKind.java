package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

/** 

ConfigKind has the following meanings...


FULL:       Created by end user. Can be viewed & edited & deleted by end user 
READ_ONLY:  System level. Can be viewed by end user but Cannot be edited or deleted by end user.
DELETEABLE: Created by end user. Cannot be viewed & edited by end user. Can be deleted from a call
            from within an external class i.e., a user created interface (IRType).
            It can be deleted but it will not be shown and edited. 
NO_CONFIG:  System level. End user cannot view, end user cannot delete,edit. i.e., 
            IFeature
EDIT_ONLY:  System level. Can be viewed & edited by the end user. Cannot be deleted            
            by the end user i.e., "Standard input form"                        
            


*/
public class ConfigKind extends IntEnum
{
  public static final ConfigKind READ_ONLY  = new ConfigKind(0);
  public static final ConfigKind FULL       = new ConfigKind(1);
  public static final ConfigKind RENAMEABLE = new ConfigKind(2);
  public static final ConfigKind DELETEABLE = new ConfigKind(3);
  public static final ConfigKind NO_CONFIG = new ConfigKind(4);
  public static final ConfigKind EDIT_ONLY = new ConfigKind(5);
  
  public static final ConfigKind WINLOSS_ONLY = new ConfigKind(6);
  public static final ConfigKind SUMMARY_ONLY = new ConfigKind(7);
  
  
  private ConfigKind(int i)
  {
    super(i);
  }
  
   public static ConfigKind getInstance(int i)
  {
    ConfigKind prim = null;
    switch (i)
    {
      case 0:prim=ConfigKind.READ_ONLY;break;
      
      
      case 1:prim=ConfigKind.FULL;break;
      case 2:prim=ConfigKind.RENAMEABLE;break;
      case 3:prim=ConfigKind.DELETEABLE;break;
      case 4:prim=ConfigKind.NO_CONFIG;break;
      case 5:prim=ConfigKind.EDIT_ONLY;break;      
      case 6:prim=ConfigKind.WINLOSS_ONLY;break;
      case 7:prim=ConfigKind.SUMMARY_ONLY;break;
    }
    
    return prim;
  }
 

}