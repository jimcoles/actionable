package com.oculussoftware.api.repi.query;

/**
* $Workfile: IQFilterExpr.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQFilterExpr.java $
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 8/03/00    Time: 11:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * useSynonyms
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

/**
*/
public interface IQFilterExpr extends IQExpr
{
  public boolean useSynonyms();
}