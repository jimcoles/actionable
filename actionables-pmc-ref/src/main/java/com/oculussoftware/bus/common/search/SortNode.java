package com.oculussoftware.bus.common.search;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.search.*;

import com.oculussoftware.system.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* $Workfile: SortNode.java $
* Create Date:  5-10-2000
* Description: A result set that support compass search tree.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: SortNode.java $
 * 
 * *****************  Version 6  *****************
 * User: Znemazie     Date: 8/08/00    Time: 10:43a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 5  *****************
 * User: Znemazie     Date: 7/30/00    Time: 3:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/26/00    Time: 6:01p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Removed references to searcher.  Changed inocation to GenericObjectSet.
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 7/13/00    Time: 3:49p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * tried to get it to compile
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:59a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:53a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/search
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:36p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Issues 160 and 272.  Added the guts.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/15/00    Time: 2:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Added stub impls for some isLeaf(), getID(), getName() methods.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Inital create.
*/

/**
* A sort node corresponds to a specific value of a specific Attribute for a specific
* parent sort node.
*/
public class SortNode implements ISortNode
{
	//---------------------------------------------------------------------------
	// Private instance vars
	//---------------------------------------------------------------------------
	private Map            _subNodeMap = new HashMap();  // nodes if this is not a leaf node
	private List           _subNodeList = new Vector();  // nodes if this is not a leaf node
	private List           _subItems = new Vector();  // sorted items if this is a leaf node
	private String         _value    = null;  
	private SortNode       _parent   = null;
	private int            _level;
	private boolean        _isLeaf   = false;
	private long           _lNodeId = -1;
	private ISortedSearch	_sortSearch   = null;

  //---------------------------------------------------------------------------
  // Constructor(s)
  //---------------------------------------------------------------------------
  /**
  * @param level 0-based tree level where 0 is the root of the tree, 1 is first, etc.
  */
  public SortNode(SortNode parent, String value, int level, boolean isLeaf)
    throws OculusException
  {
    _parent = parent;
    _level = level;
    _isLeaf = isLeaf;
    _value = value;
    if ( parent != null) {
      parent.getRoot().registerNode(this);
    }
  }

  //---------------------------------------------------------------------------
  // Public instance methods
  //---------------------------------------------------------------------------
  public List getChildNodes()
    throws OculusException
  {
    return _subNodeList;
  }

  public IRCollection getChildObjects()
    throws OculusException
  {
    IRCollection retColl = new GenericObjectSet(getContext());
    Iterator iROIs = _subItems.iterator();
    while(iROIs.hasNext()) {
      retColl.add(iROIs.next());
    }
    retColl.reset();
    return retColl;
  }

  public boolean isLeaf(){ return _isLeaf; }

  public boolean isRoot(){ return false; }

  public int getLevel() { return _level; }

  public String getNodeName() { return  _value; }

  public IObjectContext getContext() 
  {
    return _parent.getContext();
  }

  public ISortTree getRoot() 
  {
    return _parent.getRoot();
  }

  public long getID()
  {
    return _lNodeId;
  }

  public void setID(long id)
  {
    _lNodeId = id;
  }

  //---------------------------------------------------------------------------
  // Package instance methods
  //---------------------------------------------------------------------------
  
  /**
  * Adds new SortNode if needed.  If corresponding node already exists, just returns
  * it.
  */
  SortNode addOrGetSubNode(String value, boolean isLeaf)
    throws OculusException
  {
    SortNode retNode = (SortNode) _subNodeMap.get(value);
    if(retNode == null)
    {
      retNode = new SortNode(this, value, _level+1, isLeaf);
      _subNodeMap.put(value, retNode);
      _subNodeList.add(retNode);
    }
    return retNode;
  }

  /**
  * Adds an ROInfo as needed.
  */
  void addSubItem(IDCONST idcClassID, long loid)
  {
    _subItems.add(new ROInfo(idcClassID, loid));
  }

  public com.oculussoftware.api.repi.query.IQuery getQuery() 
    throws OculusException
  {
    if (_sortSearch == null)
      return _parent.getQuery();
    else
      return _sortSearch.getQuery();
  }

  public void setSortedSearch(ISortedSearch srch)
  {
    _sortSearch = srch;
  }
}
