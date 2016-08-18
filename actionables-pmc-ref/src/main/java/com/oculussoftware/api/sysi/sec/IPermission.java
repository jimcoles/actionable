package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IPermission.java $
* Create Date: 3-20-2000
* Description: An access control atomic permission.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author:  J. Coles
* Version: 1.2
*
* $History: IPermission.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Added isRecursive().
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

/**
*
*/
public interface IPermission
{
  public int getID();
  
  public String getName();
  
  public String getDescription();
  
  public boolean getIsRecursive();
}