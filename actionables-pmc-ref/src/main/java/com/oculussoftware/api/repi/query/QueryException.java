package com.oculussoftware.api.repi.query;

/**
* $Workfile: QueryException.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QueryException.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:23a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Added constructors.
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
import com.oculussoftware.api.repi.*;

/**
* Root element of a repository query.
*/
public class QueryException extends ORIOException
{
  public QueryException(String msg) { super(msg); }
  public QueryException(Exception ex) { super(ex); }
}