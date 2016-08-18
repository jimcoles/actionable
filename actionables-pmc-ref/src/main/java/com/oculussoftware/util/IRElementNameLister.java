package com.oculussoftware.util;

/*
* $Workfile: IRElementNameLister.java $
* Date:        9-06-00
* Description: Supports building lists of things.
*
* Copyright 7-01-2000 productmarketing.com.  All Rights Reserved.
*
* Author:  J. Coles
* Version 1.2
*/
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

public class IRElementNameLister implements Lister
{	
  // enum the singleton instance
  public static Lister INSTANCE = new IRElementNameLister();
  
  // constructor
  private IRElementNameLister() { }
  
  // Lister.getListString()
  public String getListString(Object obj)
  {
    String retVal = "(bad IRElement)";
    if (obj instanceof IRElement) {
      try {
        retVal = ((IRElement) obj).getName();
      }
      catch (OculusException ex) {
        retVal = "err: " + ex.getMessage();
      }
    }
    return retVal;
  }
}
