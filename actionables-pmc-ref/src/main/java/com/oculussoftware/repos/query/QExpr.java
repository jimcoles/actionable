package com.oculussoftware.repos.query;

/**
* $Workfile: QExpr.java $
* Create Date: 6/4/2000
* Description: Represents a query expression for filters.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QExpr.java $
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
import com.oculussoftware.api.repi.query.*;

/** Implements IQExpr for the PMC query subsystem. */
public class QExpr implements IQExpr
{
  //----------------------------------------------------------------------
  // Private class vars
  //----------------------------------------------------------------------
  
  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  private IOperator _oper = null;
  private Object _leftArg = null;
  private Object _rightArg = null;
  
	//----------------------------------------------------------------------
	// Constructor(s)
	//----------------------------------------------------------------------
	public QExpr()
  {
  }

	public QExpr(IOperator oper, Object left, Object right)
  {
    setOper(oper);
    setLeft(left);
    setRight(right);
	}
  
	//----------------------------------------------------------------------
	// Public Methods
	//----------------------------------------------------------------------

	public Object getLeft() { return _leftArg; }
	public Object getRight() { return _rightArg; }
	public IOperator getOper() { return _oper; }

  public void setOper(IOperator oper) { _oper = oper; }
  public void setLeft(Object left) { _leftArg = left; }
  public void setRight(Object right) { _rightArg = right; }

}
