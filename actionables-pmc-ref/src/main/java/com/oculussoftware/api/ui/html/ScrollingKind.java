package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ScrollingKind.java $
* Description: String Enumeration of HTML Scrolling Types.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML Scrolling Types.
*
* @author Egan Royal
*/
public final class ScrollingKind extends StringEnum
{
  /**
  * value "AUTO"
  */
  public final static ScrollingKind AUTO = new ScrollingKind("AUTO");
  
  /**
  * value "NO"
  */
  public final static ScrollingKind NO   = new ScrollingKind("NO");
  
  /**
  * value "YES"
  */
  public final static ScrollingKind YES  = new ScrollingKind("YES");
  
  /** Private constructor */
  private ScrollingKind(String s) { super(s); }
}