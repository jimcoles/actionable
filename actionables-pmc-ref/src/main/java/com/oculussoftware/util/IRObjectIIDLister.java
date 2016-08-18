package com.oculussoftware.util;

/*
* $Workfile: IRObjectIIDLister.java $
* Date:        9-06-00
* Description: Supports building lists of things.
*
* Copyright 7-01-2000 productmarketing.com.  All Rights Reserved.
*
* Author:  J. Coles
* Version 1.2
*/
import com.oculussoftware.api.repi.*;

public class IRObjectIIDLister implements Lister
{	
  // enum the singleton instance
  public static Lister INSTANCE = new IRObjectIIDLister();
  
  // constructor
  private IRObjectIIDLister() { }
  
  // Lister.getListString()
  public String getListString(Object obj)
  {
    String retVal = "(bad IRObject)";
    try {
      retVal = "" + ((IRObject) obj).getIID().getLongValue();
    }
    catch (ORIOException ex) {}
    return retVal;
  }
}
