package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

/**
* $Workfile: ISortNode.java $
* Create Date:  5-12-2000
* Description: A result set that support compass search tree.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ISortNode.java $
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
 * Added setID( ).
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
 * Added accessor methods.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:38a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/mkt/prod
 * Initial addition of interface related to market input sorted tree
 * search.
*/
/**
* ISortNode
* @author Zain Nemazie
*/
public interface ISortNode
{
  /**
  * This method returns the name of the node.
  * @return The name of the node.
  */
  public String getNodeName();

  /**
  * This method returns true iff the Node is a leaf node.
  * @return true iff the Node is a leaf node.
  */
  public boolean isLeaf();
  
  /**
  * This method returns true iff the Node is the root node.
  * @return true iff the Node is the root node.
  */
  public boolean isRoot();

  /**
  * This method sets the ID of the node.
  * @param id The ID.
  */
  public void setID(long id);

  /**
  * This method returns the ID of the node.
  * @return The ID of the node.
  */
  public long getID();

  /**
  * This method returns the level of the node.
  * @return The level of the node.
  */
  public int getLevel();

  /**
  * This method returns the List(ISortNodes) of the node's child nodes.
  * @return The List of the node's child nodes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public List getChildNodes()
    throws OculusException;

  /**
  * This method returns an IRCollection of child objects.
  * @return An IRCollection of child objects.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IRCollection getChildObjects()
    throws OculusException;

  /**
  * This method returns the IQuery object.
  * @return The IQuery object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public com.oculussoftware.api.repi.query.IQuery getQuery( )
    throws OculusException;

  /**
  * This method sets the ISortedSearch.
  * @param srch The ISortedSearch.
  */
  public void setSortedSearch(ISortedSearch srch);
}
