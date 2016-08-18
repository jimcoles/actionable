package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

/**
* $Workfile: ISortTree.java $
* Create Date:  5-12-2000
* Description: A result set that support compass search tree.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ISortTree.java $
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 9/22/00    Time: 3:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * javadoc
 * 
 * *****************  Version 4  *****************
 * User: Znemazie     Date: 8/08/00    Time: 10:43a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/26/00    Time: 5:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * Added regsiterNode( ).
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
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/mkt/prod
 * Remove getQuery().   Added findNode( ).
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:38a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/mkt/prod
 * Initial addition of interface related to market input sorted tree
 * search.
*/
/**
* ISortTree
* @author Zain Nemazie
*/

public interface ISortTree extends ISortNode
{
  /**
  * This method finds and returns the ISortNode for the given ID.
  * @return id The node ID.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ISortNode findNode(long id)
    throws OculusException;

  /**
  * This takes an ISortNode and puts it in the SortTree.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void registerNode(ISortNode node)
    throws OculusException;
}
