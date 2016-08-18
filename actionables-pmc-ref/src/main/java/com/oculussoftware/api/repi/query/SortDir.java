package com.oculussoftware.api.repi.query;
/*
* $Workfile: SortDir.java $
* Create Date: 7/10/2000
* Description: 
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*/

/* $History: SortDir.java $
 * 
 * *****************  Version 4  *****************
 * User: Znemazie     Date: 7/17/00    Time: 11:04a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * 
 * *****************  Version 3  *****************
 * User: Znemazie     Date: 7/14/00    Time: 6:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * getXMLInstance constructor that takes a string literal and return an
 * enum literal
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 5:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
 * Moved static init of hash above its first point of reference to avoid
 * runtime static init error.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;

/**
* Enumerates query sort directions: Asc, Desc.
*/
public final class SortDir extends IntEnum
{
  private static Map _hash = new HashMap();
  
  public final static SortDir ASC   = new SortDir(0); 
  public final static SortDir DESC  = new SortDir(1);
  
  /** Private constructor */
  private SortDir(int s) 
  {
    super(s); 
    _hash.put(new Integer(s), this);
  }
  
  public static SortDir getInstance(int i) throws OculusException
  {
    SortDir retVal = null;
    try {
      retVal = (SortDir) _hash.get(new Integer(i));
    }
    catch (ClassCastException ex) {
      throw new OculusException(ex);
    }
    if (retVal == null) {
      throw new OculusException("Invalid id.");
    }
    return retVal;
  }  
 
  public static SortDir getXMLInstance(String str) throws OculusException
  {
    if(str.equals("ASC"))
      return ASC;
    else if(str.equals("DESC"))
      return DESC;
    else
      throw new OculusException("Invalid SortDir");
  }//end getInstance  

  public static String getStringValue(SortDir so) throws OculusException
  {
    if (so == ASC)
      return "ASC";
    else if (so == DESC)
      return "DESC";
    else
      throw new OculusException("Invalid Sort Direction.");  
  }
  
}