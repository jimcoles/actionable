package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.busi.*;

import com.oculussoftware.system.*;
import com.oculussoftware.repos.bmr.view.*;

import java.util.*;
import java.sql.*;  // bloody Timestamps

/**
* $Workfile: ISortedSearch.java $
* Create Date: 6/25/2000
* Description: Supports sortable search, in particular the compass sorted tree search.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: ISortedSearch.java $
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 9/22/00    Time: 3:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * javadoc
 * 
 * *****************  Version 6  *****************
 * User: Znemazie     Date: 8/08/00    Time: 1:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 5  *****************
 * User: Znemazie     Date: 7/30/00    Time: 3:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/26/00    Time: 5:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * Changed get/setQueryClasses( ) to get/setTargetClass( ).  Added
 * getSortTree().
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 12:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * Repackaged.  Still very much broken though.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:33a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:52a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/busi/search
*/


/**
* ISortedSearch defines the methods necessary to run the Sorted Search.  A 
* SortedSearch is basically a persisted Search.
*
* @author Zain Nemazie
*/
public interface ISortedSearch extends ISearch, IRDataView
{
  /**
  * This method takes an IObjectContext and returns an ISortTree.
  * @param context The current ObjectContext.
  * @return The SortTree.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISortTree getSortTree(IObjectContext context)
    throws OculusException;
}
