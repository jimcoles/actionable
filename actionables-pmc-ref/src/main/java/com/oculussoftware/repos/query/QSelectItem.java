package com.oculussoftware.repos.query;

/**
* $Workfile: QSelectItem.java $
* Create Date: 6/4/2000
* Description: Represents a query select item.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QSelectItem.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/24/00    Time: 9:02a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.repi.query.*;

/** Implements IQSelectItem for the PMC query subsystem. */
public class QSelectItem implements IQSelectItem
{
  //-----------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------
  private Object _attr = null;
  private String _alias = null;
  
  //-----------------------------------------------------------------
  // Constructor(s)
  //-----------------------------------------------------------------
  QSelectItem(IQAttrRef attr, String alias)
  {
    _attr = attr;
    _alias = alias;
  }
  
  QSelectItem(Object attr, String alias)
  {
    _attr = attr;
    _alias = alias;
  }
  
  //-----------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------

	public Object getAttr(){ return _attr; }

	public String getAlias(){ return _alias; }
}
