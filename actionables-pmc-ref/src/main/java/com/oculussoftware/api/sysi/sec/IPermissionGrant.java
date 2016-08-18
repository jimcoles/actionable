package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IPermissionGrant.java $
* Create Date:  3-20-2000
* Description: Represents a grant of permission to a user for an IRObject.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IPermissionGrant.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * BUG00086 - Added Accessors.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:47a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Initial creation.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/02/00    Time: 3:18p
 * Created in $/Unfinished code/Jim's folder/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/sysi/sec
 * backup
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;

/**
*
*/
public interface IPermissionGrant
{
  public IIID getContextObjID();
  public IIID getAccessorID();
  public IPermission getPermission();
  public boolean isFixed();
}