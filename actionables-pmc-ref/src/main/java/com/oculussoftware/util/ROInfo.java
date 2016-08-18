package com.oculussoftware.util;

/**
* $Workfile: ROInfo.java $
* Date:      5/24/2000  
* Description: Holds essential info needed to retrieve a sinlge object
*              using the CRM.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

/*
* $History: ROInfo.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:44p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/util
 * Initial create.
*/

import com.oculussoftware.api.repi.*;
  
/**
* 
*/
public class ROInfo 
{
  private IDCONST _type = null;
  private long _oid;
  public ROInfo(IDCONST type, long oid)
  {
    _type = type;
    _oid = oid;
  }    
  public IDCONST getType() { return _type; }
  public long getId() { return _oid; }
}