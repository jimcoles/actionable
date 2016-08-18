package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: FrameKind.java $
* Description: String Enumeration of HTML FrameKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML FrameKinds.
*
* @author Egan Royal
*/
public final class FrameKind extends StringEnum
{
  /**
  * value "ABOVE"
  */
  public final static FrameKind ABOVE  = new FrameKind("ABOVE");
  
  /**
  * value "BELOW"
  */
  public final static FrameKind BELOW  = new FrameKind("BELOW");
  
  /**
  * value "BORDER"
  */
  public final static FrameKind BORDER = new FrameKind("BORDER");
  
  /**
  * value "BOX"
  */
  public final static FrameKind BOX    = new FrameKind("BOX");
  
  /**
  * value "HSIDES"
  */
  public final static FrameKind HSIDES = new FrameKind("HSIDES");
  
  /**
  * value "LHS"
  */
  public final static FrameKind LHS    = new FrameKind("LHS");
  
  /**
  * value "RHS"
  */
  public final static FrameKind RHS    = new FrameKind("RHS");
  
  /**
  * value "VOID"
  */
  public final static FrameKind VOID   = new FrameKind("VOID");
  
  /**
  * value "VSIDES"
  */
  public final static FrameKind VSIDES = new FrameKind("VSIDES");

  
  /** Private constructor */
  private FrameKind(String s) { super(s); }
}