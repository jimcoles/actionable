package com.oculussoftware.repos.query;

/**
* $Workfile: QFilter.java $
* Create Date: 6/4/2000
* Description: Represents a query filter.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QFilter.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import java.util.*;
import com.oculussoftware.api.repi.query.*;

/** Implements IQFilter for the PMC query subsystem. */
public class QFilter implements IQFilter
{
  //----------------------------------------------------------------------
  // Private class vars
  //----------------------------------------------------------------------
  
  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  private IQFilterExpr _expr = null;
  
	//----------------------------------------------------------------------
	// Constructor(s)
	//----------------------------------------------------------------------
  QFilter(){
  }
  
	//----------------------------------------------------------------------
	// Private instance vars
	//----------------------------------------------------------------------
  public void setExpr(IQFilterExpr expr){ _expr = expr; }
  public IQFilterExpr getExpr(){ return _expr; }
}
