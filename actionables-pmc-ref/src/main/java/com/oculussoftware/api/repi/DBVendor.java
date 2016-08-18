package com.oculussoftware.api.repi;
/**
* $Workfile: DBVendor.java $
* Create Date: 8/15/2000
* Description: Enumerates database vendors supported.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

/* $History: DBVendor.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 8/16/00    Time: 11:18a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
*/

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

public final class DBVendor extends IntEnum
{
  public final static DBVendor SQL_SERVER   = new DBVendor(0); 
  public final static DBVendor ORACLE       = new DBVendor(1);
  /** Private constructor */
  private DBVendor(int s) { super(s); }
}