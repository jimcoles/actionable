package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: JSEventKind.java $
* Description: String Enumeration of HTML JavaScript Events.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML JavaScript Events.
*
* @author Egan Royal
*/
public final class JSEventKind extends StringEnum
{
  /**
  * value "ONBLUR"
  */
  public final static JSEventKind ONBLUR      = new JSEventKind("ONBLUR");
  
  /**
  * value "ONCHANGE"
  */
  public final static JSEventKind ONCHANGE    = new JSEventKind("ONCHANGE");
  
  /**
  * value "ONCLICK"
  */
  public final static JSEventKind ONCLICK     = new JSEventKind("ONCLICK");
  
  /**
  * value "ONDBLCLICK"
  */
  public final static JSEventKind ONDBLCLICK  = new JSEventKind("ONDBLCLICK");
  
  /**
  * value "ONFOCUS"
  */
  public final static JSEventKind ONFOCUS     = new JSEventKind("ONFOCUS");
  
  /**
  * value "ONMOUSEDOWN"
  */
  public final static JSEventKind ONMOUSEDOWN = new JSEventKind("ONMOUSEDOWN");
  
  /**
  * value "ONMOUSEUP"
  */
  public final static JSEventKind ONMOUSEUP   = new JSEventKind("ONMOUSEUP");
  
  /**
  * value "ONMOUSEMOVE"
  */
  public final static JSEventKind ONMOUSEMOVE = new JSEventKind("ONMOUSEMOVE");
  
  /**
  * value "ONMOUSEOUT"
  */
  public final static JSEventKind ONMOUSEOUT  = new JSEventKind("ONMOUSEOUT");
  
  /**
  * value "ONMOUSEOVER"
  */
  public final static JSEventKind ONMOUSEOVER = new JSEventKind("ONMOUSEOVER");
  
  /**
  * value "ONKEYDOWN"
  */
  public final static JSEventKind ONKEYDOWN   = new JSEventKind("ONKEYDOWN");
  
  /**
  * value "ONKEYPRESS"
  */
  public final static JSEventKind ONKEYPRESS  = new JSEventKind("ONKEYPRESS");
  
  /**
  * value "ONKEYUP"
  */
  public final static JSEventKind ONKEYUP     = new JSEventKind("ONKEYUP");
  
  /**
  * value "ONSELECT"
  */
  public final static JSEventKind ONSELECT    = new JSEventKind("ONSELECT");
  
  /**
  * value "ONLOAD"
  */
  public final static JSEventKind ONLOAD      = new JSEventKind("ONLOAD");
  
  /**
  * value "ONUNLOAD"
  */
  public final static JSEventKind ONUNLOAD    = new JSEventKind("ONUNLOAD");
  
  /**
  * value "ONSUBMIT"
  */
  public final static JSEventKind ONSUBMIT    = new JSEventKind("ONSUBMIT");
  
  /**
  * value "ONRESET"
  */
  public final static JSEventKind ONRESET     = new JSEventKind("ONRESET");
  
  /**
  * value "ONHELP"
  */
  public final static JSEventKind ONHELP      = new JSEventKind("ONHELP");
  
  /**
  * value "ONRESIZE"
  */
  public final static JSEventKind ONRESIZE    = new JSEventKind("ONRESIZE");
  
  /** Private constructor */
  private JSEventKind(String s) { super(s); }
}