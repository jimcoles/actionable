package com.oculussoftware.util;

/*
* $Workfile: IntEnumLister.java $
* Date:        9-06-00
* Description: Supports building lists of things.
*
* Copyright 7-01-2000 productmarketing.com.  All Rights Reserved.
*
* Author:  J. Coles
* Version 1.2
*/
import com.oculussoftware.api.repi.*;

public class IntEnumLister implements Lister
{	
  // enum the singleton instance
  public static Lister INSTANCE = new IntEnumLister();
  
  // constructor
  private IntEnumLister() { }
  
  // Lister.getListString()
  public String getListString(Object obj)
  {
    return Integer.toString(((IntEnum) obj).getIntValue());
  }
}
