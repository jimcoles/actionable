package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQSort.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQSort.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 5:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Added IQSortItem concept.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:53a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/repi/query
*/

import java.util.*;

/**
* Sort portion of an IQuery..
*/
public interface IQSort
{
  public IQSortItem addSortItem(IQAttrRef attr, SortDir dir);
  public List getSortItems();
}