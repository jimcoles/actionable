package com.oculussoftware.repos.query;

/**
* $Workfile: QSelect.java $
* Create Date: 6/4/2000
* Description: Represents a query filter.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QSelect.java $
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 9/26/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Support issue #2510 - distinct set/get.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added new addAttr( ) and addLiteral( ) impls.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Implemented methods.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.repi.query.*;

/** Implements IQSelect for the PMC query subsystem. */
public class QSelect implements IQSelect
{
  //-----------------------------------------------------------------
  // Private instance vars
  //-----------------------------------------------------------------
  private List _items = new Vector();
  private boolean _useDistinct = false;
  
  //-----------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------
	public List getAttrs()
  {
    return _items;
  }

	public void addAttr(IQAttrRef attr)
	{
    _items.add(new QSelectItem(attr, null));
	}
  public void addAttr(IQAttrRef attr, String alias)
	{
    _items.add(new QSelectItem(attr, alias));
	}
  
  public void addLiteral(String value, String alias)
	{
    _items.add(new QSelectItem(value, alias));
	}

	public IQSelect setUseDistinct(boolean huh)
  {
    _useDistinct = huh;
    return this;
  }

	public boolean getUseDistinct(){ return _useDistinct; }
}
