package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQSelectItem.java $
* Create Date: 6/26/2000
* Description: An element of an IQSelect clause of an IQuery.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQSelectItem.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/24/00    Time: 9:02a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.bus.xmeta.*;

import java.util.*;
import java.io.*;


/**
* Root element of a repository query.
*/
public interface IQSelectItem
{
  /** Returns either a String or an IQAttrRef */
  public Object getAttr();
  public String getAlias();
}