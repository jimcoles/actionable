package com.oculussoftware.util;

/*
* $Workfile: Lister.java $
* Date:        9-06-00
* Description: Supports building lists of things.
*
* Copyright 7-01-2000 productmarketing.com.  All Rights Reserved.
*
* Author:  J. Coles
* Version 1.2
*/
public interface Lister
{	
  /** Returns the string to use for list building for the argument object. */
  public String getListString(Object obj);
}
