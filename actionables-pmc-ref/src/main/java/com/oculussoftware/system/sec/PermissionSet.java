package com.oculussoftware.system.sec;
/**
* $Workfile: PermissionSet.java $
* Create Date: 3-24-2000
* Description: An access control permission entity.  As convenience, provides an enum
*              of valid permissions.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: PermissionSet.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:15p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * No functional changes.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/24/00    Time: 8:42a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/19/00    Time: 8:39p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
 * backup checkin.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:51a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Initial creation.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/02/00    Time: 3:18p
 * Created in $/Unfinished code/Jim's folder/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;

import java.util.*;

/** 
*/
public class PermissionSet implements IPermSet
{
  //
  private static final int EST_NUM_PERMS = 100;
  // singleton reference...
  private static PermissionSet _instance = new PermissionSet();
  //
  public static Map _set = new HashMap(EST_NUM_PERMS);
  
  // singleton method...
  public static PermissionSet getInstance()
  {
    return _instance;
  }
  
  
  // private constructor to support singleton.
  private PermissionSet() {};
  
  //
    
  public IPermission getPermForId(int id)
  {
    IPermission retVal = null;
    
    retVal = (IPermission) _set.get(new Integer(id));
    
    return retVal;
  }
  
  protected Object put(int ikey, IPermission value)
  {
    return _set.put(new Integer(ikey), value);
  }
}