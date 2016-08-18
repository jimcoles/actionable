package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: InputKind.java $
* Description: String Enumeration of HTML Inputs.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML Inputs.
*
* @author Egan Royal
*/
public final class InputKind extends StringEnum
{
  /**
  * value "BUTTON"
  */
  public final static InputKind BUTTON   = new InputKind("BUTTON");
  
  /**
  * value "CHECKBOX"
  */
  public final static InputKind CHECKBOX = new InputKind("CHECKBOX");
  
  /**
  * value "FILE"
  */
  public final static InputKind FILE     = new InputKind("FILE");
  
  /**
  * value "HIDDEN"
  */
  public final static InputKind HIDDEN   = new InputKind("HIDDEN");
  
  /**
  * value "IMAGE"
  */
  public final static InputKind IMAGE    = new InputKind("IMAGE");
  
  /**
  * value "PASSWORD"
  */
  public final static InputKind PASSWORD = new InputKind("PASSWORD");
  
  /**
  * value "RADIO"
  */
  public final static InputKind RADIO    = new InputKind("RADIO");
  
  /**
  * value "RESET"
  */
  public final static InputKind RESET    = new InputKind("RESET");
  
  /**
  * value "SUBMIT"
  */
  public final static InputKind SUBMIT   = new InputKind("SUBMIT");
  
  /**
  * value "TEXT"
  */
  public final static InputKind TEXT     = new InputKind("TEXT");
  
  /**
  * Takes a String and returns a InputKind iff the String is valid.
  *
  * @param val The String value
  * @return The InputKind that corresponds to the String value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the String value does not correspond to a valid InputKind
  */
  public static InputKind getInstance(String s) throws OculusException
  {
    if(s.equals("BUTTON"))
      return BUTTON;
    else if(s.equals("CHECKBOX"))
      return CHECKBOX;
    else if(s.equals("FILE"))
      return FILE;
    else if(s.equals("HIDDEN"))
      return HIDDEN;
    else if(s.equals("IMAGE"))
      return IMAGE;
    else if(s.equals("PASSWORD"))
      return PASSWORD;
    else if(s.equals("RADIO"))
      return RADIO;
    else if(s.equals("RESET"))
      return RESET;
    else if(s.equals("SUBMIT"))
      return SUBMIT;
    else if(s.equals("TEXT"))
      return TEXT;
    else
      throw new OculusException("Invalid InputKind.");
  }//end getInstance
  
  /** Private constructor */
  private InputKind(String s) { super(s); }
}