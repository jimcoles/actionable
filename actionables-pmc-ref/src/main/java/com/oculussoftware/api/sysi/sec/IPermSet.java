package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IPermSet.java $
* Create Date: 4-18-2000
* Description: An access control atomic permission.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author:  J. Coles
* Version: 1.2
*
* $History: IPermSet.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/24/00    Time: 8:42a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
*/

import com.oculussoftware.api.sysi.*;

/**
* Provides information on the full set of IPermissions registered for an 
* application.
*/
public interface IPermSet
{
  public IPermission getPermForId(int id);
}