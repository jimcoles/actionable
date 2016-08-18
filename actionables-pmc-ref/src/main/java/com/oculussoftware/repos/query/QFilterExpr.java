package com.oculussoftware.repos.query;

/**
* $Workfile: QFilterExpr.java $
* Create Date: 6/4/2000
* Description: Represents a query expression for filters.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QFilterExpr.java $
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 8/03/00    Time: 11:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * useSynonyms
 * 
 * *****************  Version 2  *****************
 * User: Sshafi       Date: 7/28/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.util.*;

/** Implements IQFilterExpr for the PMC query subsystem. */
public class QFilterExpr extends QExpr implements IQFilterExpr
{
  //----------------------------------------------------------------------
  // Private class vars
  //----------------------------------------------------------------------
  
  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  private boolean _syn = false;
	//----------------------------------------------------------------------
	// Constructor(s)
	//----------------------------------------------------------------------
	public QFilterExpr(IOperator oper, Object left, Object right)
  {
    super(oper, left, right);
	}
  
	public QFilterExpr(IOperator oper, Object left, Object right, boolean syn)
    throws OculusException
  {
    this(oper, left, right);
    _syn = syn;
    if (syn && right instanceof String)
    {
      QFilterExpr newLeft = new QFilterExpr(oper,left,right);
      QFilterExpr newRight = null;
      
      String word = (String)right;
//      for (Enumeration syns = Thesaurus.getInstance().getSynonymsForWord(word); syns.hasMoreElements(); )
//      {
//        if (newRight == null)
//          newRight = new QFilterExpr(oper,left,syns.nextElement());
//        else
//          newRight = new QFilterExpr(BoolOper.BOOL_OR, newRight, new QFilterExpr(oper,left,syns.nextElement()));
//      }
      
      if (newRight != null)
      {
        setOper(BoolOper.BOOL_OR);
        setLeft(newLeft);
        setRight(newRight);
      }
    }
  }
  
  public boolean useSynonyms()
  {
    return _syn;
  }
}
