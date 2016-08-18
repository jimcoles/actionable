package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: LangKind.java $
* Description: String Enumeration of HTML Langs.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML Langs.
*
* @author Egan Royal
*/
public final class LangKind extends StringEnum
{
  /**
  * value "FR"
  */
  public final static LangKind FR = new LangKind("FR");
  
  /**
  * value "EN"
  */
  public final static LangKind EN = new LangKind("EN");
  
  /**
  * value "DE"
  */
  public final static LangKind DE = new LangKind("DE");
  
  /** Private constructor */
  private LangKind(String s) { super(s); }
}