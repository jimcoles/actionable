package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ScopeKind.java $
* Description: String Enumeration of HTML Scope Types.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML Scope Types.
*
* @author Egan Royal
*/
public final class ScopeKind extends StringEnum
{
  /**
  * value "COL"
  */
  public final static ScopeKind COL      = new ScopeKind("COL");
  
  /**
  * value "COLGROUP"
  */
  public final static ScopeKind COLGROUP = new ScopeKind("COLGROUP");
  
  /**
  * value "ROW"
  */
  public final static ScopeKind ROW      = new ScopeKind("ROW");
  
  /**
  * value "ROWGROUP"
  */
  public final static ScopeKind ROWGROUP = new ScopeKind("ROWGROUP");
  
  /** Private constructor */
  private ScopeKind(String s) { super(s); }
}