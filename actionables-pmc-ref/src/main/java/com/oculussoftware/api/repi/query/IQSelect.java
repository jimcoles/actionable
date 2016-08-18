package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQSelect.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQSelect.java $
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 9/26/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Support issue #2510 - distinct set/get.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:36p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Added: public void addAttr(IQAttrRef attr, String alias); public void
 * addLiteral(String value, String alias);
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:53a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/repi/query
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.bus.xmeta.*;

import java.util.*;
import java.io.*;


/**
* Root element of a repository query.
*/
public interface IQSelect
{
  public void addAttr(IQAttrRef attr);
  public void addAttr(IQAttrRef attr, String alias);
  public void addLiteral(String value, String alias);
  public List getAttrs( );
  public boolean getUseDistinct( );
  public IQSelect setUseDistinct(boolean huh);
}