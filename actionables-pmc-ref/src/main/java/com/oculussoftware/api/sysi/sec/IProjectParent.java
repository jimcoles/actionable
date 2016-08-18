package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IProjectParent.java $
* Create Date:  9-12-2000
* Description: Represents a parent level object that prject or process oriented parent
*              object.  This interface allows the ACM to get information needed to
*              determine role-based permissions.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IProjectParent.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 9/12/00    Time: 5:48p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
*
*/

import com.oculussoftware.api.sysi.*;

import java.util.*;

public interface IProjectParent
{
  public List getAllChildOIDs( )
    throws OculusException;
}