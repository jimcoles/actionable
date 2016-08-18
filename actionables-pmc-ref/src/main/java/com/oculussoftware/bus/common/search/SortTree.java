package com.oculussoftware.bus.common.search;

import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;


import com.oculussoftware.system.*;


import java.util.*;

/**
* $Workfile: SortTree.java $
* Create Date:  5-10-2000
* Description: A result set that support compass search tree.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: SortTree.java $
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
 * Removed refs to searcher.  No longer using a Map to hold current row of
 * data.
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
 * User: Jcoles       Date: 5/25/00    Time: 9:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Issues 160 and 272.  Added the guts.  Move some stuff from MktSortNode
 * to here.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/15/00    Time: 2:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Added impl stub for getQuery( ).
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/mkt/prod
 * Inital create.
*/
public class SortTree extends SortNode implements ISortTree
{
	//-----------------------------------------------------------------
	// Private instance var
	//-----------------------------------------------------------------
	private Map         _currRow = null;  // vector of current row data.
	private long        _lastNodeID = 0;
	private Map         _allNodeHash = new HashMap();
	private IObjectContext _context = null;
	private static final int IDX_OBJECTID = 1;

  //-----------------------------------------------------------------
  // Public constructor
  //-----------------------------------------------------------------
  public SortTree(IObjectContext context, IDataSet data)
    throws OculusException
  {
    super(null, "Root", 0, false);
    _context = context;
    _load(data);
  }

  //-----------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------
  
  public boolean isLeaf() {return false;}

  public boolean isRoot() { return true; }

  public long nextNodeID() 
  { 
    _lastNodeID++;
    return _lastNodeID;
  }

  public ISortTree getRoot() 
  {
    return this;
  }

  public IObjectContext getContext() 
  {
    return _context;
  }

  public ISortNode findNode(long id) throws OculusException
  {
    ISortNode retNode = null;
    retNode = (ISortNode) _allNodeHash.get(new Long(id));
    return retNode;
  }

  public void registerNode(ISortNode node)
    throws OculusException
  {
    long id = nextNodeID();
    node.setID(id);
    Object old = _allNodeHash.put(new Long(id), (ISortNode) node);
    if (old != null) 
      throw new OculusException("Duplicate search node id: " + id);
  }

  //-----------------------------------------------------------------
  // Package
  //-----------------------------------------------------------------
  
  //-----------------------------------------------------------------
  // Private instance methods
  //-----------------------------------------------------------------
  private void _load(IDataSet data)
    throws OculusException
  {
    while(data.next()) {
      _loadSubNodes(this, data, 1);
    }
  }

  /**
  * Recursively Builds sub nodes for current row.
  * Idea is to take advantage of the fact that result set is already sorted
  * in the order we need.
  */
  private void _loadSubNodes(SortNode parent, IDataSet row, int idx)
    throws OculusException
  {
    if (parent.isLeaf()) {
      // load sub items (the things we're searching for)
      parent.addSubItem(IDCONST.MARKETINPUT, row.getLong(IDX_OBJECTID));
    }
    else {
      // load sub nodes...
      boolean childIsLeaf = (idx == getQuery().getSort().getSortItems().size()) ? true : false;
      String curval = (String) row.getString(idx+2);
      SortNode nextNode = parent.addOrGetSubNode(curval, childIsLeaf);
      _loadSubNodes(nextNode, row, idx+1);
    }
  }

  //-----------------------------------------------------------------
  // Public constructor
  //-----------------------------------------------------------------
  public SortTree(IObjectContext context, IDataSet data, ISortedSearch srtSrch)
    throws OculusException
  {
    super(null, "Root", 0, false);
    setSortedSearch(srtSrch);
    _context = context;
    _load(data);
  }
}
