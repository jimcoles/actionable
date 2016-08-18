package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ClassKind.java $
* Description: String Enumeration of ClassKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of ClassKinds defined in the css file.
*
* @author Egan Royal
*/
public final class ClassKind extends StringEnum
{
  /**
  * value "".  Used to reset the class.
  */
  public final static ClassKind NORMAL  = new ClassKind("");
  
  /**
  * value "oculusLabel"
  */
  public final static ClassKind LABEL  = new ClassKind("oculusLabel");
  
  /**
  * value "oculusAlert"
  */
  public final static ClassKind ALERT = new ClassKind("oculusAlert");
  
  /**
  * value "oculusIndent"
  */
  public final static ClassKind INDENT = new ClassKind("oculusIndent");
  
  /**
  * value "oculusYellow"
  */
  public final static ClassKind YELLOW = new ClassKind("oculusYellow");
  
  /**
  * value "oculusBlue"
  */
  public final static ClassKind BLUE = new ClassKind("oculusBlue");
  
  /**
  * value "oculusRed"
  */
  public final static ClassKind RED = new ClassKind("oculusRed");
  
  /**
  * value "oculusItalic"
  */
  public final static ClassKind ITALIC = new ClassKind("oculusItalic");
  
  /**
  * value "oculusQuestion"
  */
  public final static ClassKind QUESTION = new ClassKind("oculusQuestion");
  
  /**
  * value "oculusHeader"
  */
  public final static ClassKind HEADER = new ClassKind("oculusHeader");
  
  /**
  * value "oculusRequired"
  */
  public final static ClassKind REQUIRED = new ClassKind("oculusRequired");
  
  /**
  * value "link"
  */
  public final static ClassKind LINK = new ClassKind("link");
  
  /** Private constructor */
  private ClassKind(String s) { super(s); }
}