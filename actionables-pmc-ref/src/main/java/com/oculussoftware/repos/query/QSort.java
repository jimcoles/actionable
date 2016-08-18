package com.oculussoftware.repos.query;

/**
* $Workfile: QSort.java $
* Create Date: 6/4/2000
* Description: Represents a query filter.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QSort.java $
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Implemented properly.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.repi.query.*;

/** Implements IQSort for the PMC query subsystem. */
public class QSort implements IQSort
{
  private List _items = new Vector();
  
	public IQSortItem addSortItem(IQAttrRef attr, SortDir dir)
	{
    IQSortItem item =  new QSortItem(attr, dir);
		_items.add(item);
    return item;
	}

	public List getSortItems()
	{
		return _items;
	}
}
