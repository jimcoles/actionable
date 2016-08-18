package com.oculussoftware.repos.query;

/**
* $Workfile: QSortItem.java $
* Create Date: 7/14/2000
* Description: Represents a query sort item.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QSortItem.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/18/00    Time: 8:58a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * method name change.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:38p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.repi.query.*;

/** Implements IQSort for the PMC query subsystem. */
public class QSortItem implements IQSortItem
{
  private SortDir _dir = null;
  private IQAttrRef _attr = null;
  
  QSortItem(IQAttrRef attr, SortDir dir){
    _attr = attr;
    _dir = dir;
  }

	public SortDir getDir(){ return _dir; }

	public IQAttrRef getAttrRef(){ return _attr; }
}
