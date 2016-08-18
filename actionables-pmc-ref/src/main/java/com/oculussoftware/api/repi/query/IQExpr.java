package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQExpr.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQExpr.java $
 * 
 * *****************  Version 2  *****************
 * User: Sshafi       Date: 7/28/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

/**
*/
public interface IQExpr
{
  public IOperator getOper();
  /* Object could be another IQExpr. **/
  public Object getLeft( );
  public Object getRight( );
  
  
  public void setOper(IOperator oper);
  public void setLeft(Object left);
  public void setRight(Object right);
}