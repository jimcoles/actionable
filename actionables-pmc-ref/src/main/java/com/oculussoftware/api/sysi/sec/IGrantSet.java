package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IGrantSet.java $
* Create Date: 4-18-2000
* Description: A collection of permission grants.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author:  J. Coles
* Version: 1.2
*
* $History: IGrantSet.java $
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 8/02/00    Time: 12:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * BUG00086 - Added addAll( ) method.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * added size( ) method.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/24/00    Time: 11:22p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
*/

import com.oculussoftware.api.sysi.*;
import java.util.*;

/**
* Returned by an IAccessMgr to the UI to find out what the login user 
* has permission to do.
*/
public interface IGrantSet
{
//  public IPermission getPermForId(int id);
  public void add(IPermission perm);
  public void addAll(IPermission perm[]);
  public void addAll(IGrantSet grant);
  public boolean contains(IPermission perm);
  public Iterator iterator();
  public int size();
}