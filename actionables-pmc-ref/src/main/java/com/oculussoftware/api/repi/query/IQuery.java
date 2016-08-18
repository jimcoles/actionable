package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQuery.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQuery.java $
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Bug Fix: #2537
 * 
 * *****************  Version 3  *****************
 * User: Sshafi       Date: 7/25/00    Time: 5:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * 
 * *****************  Version 2  *****************
 * User: Sshafi       Date: 7/24/00    Time: 5:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
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
import com.oculussoftware.api.repi.xmeta.*;

import java.util.*;
import java.io.*;

/**
* Root element of a repository query.
*
* 7/9/2000 - Lets try this: Make all specifications of select, filter, and sort
*            be fully qualified with respect to the targetted class of item, 
*            i.e., starting with target item class, specified the 'path' of
*            associations to follow to get to the class of item owning the attr.
*            This way, the search engine can infer the required joins.
*/
public interface IQuery
{
  public IQSelect getSelect( );
  public void     setTargetClass(IXClass cls);
  public IXClass  getTargetClass( );
  public IQFilter getFilter( );
  public IQSort   getSort( );
  public IQuery   setScope(IObjectContext context, IXClass target, IXClass scopeType, long scopeID)
    throws OculusException;
  public IQuery   setScope(IObjectContext context, IXClass target, IXClass connectToType, IXClass scopeType, long scopeID)
    throws OculusException;
}