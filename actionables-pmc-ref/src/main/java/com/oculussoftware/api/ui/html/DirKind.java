package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: DirKind.java $
* Description: String Enumeration of HTML DirKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML DirKinds.
*
* @author Egan Royal
*/
public final class DirKind extends StringEnum
{
  /**
  * value "LTR"
  */
  public final static DirKind LTR = new DirKind("LTR");
  
  /**
  * value "RTL"
  */
  public final static DirKind RTL = new DirKind("RTL");
  
  /** Private constructor */
  private DirKind(String s) { super(s); }
}