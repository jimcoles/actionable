package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: MethodKind.java $
* Description: String Enumeration of HTML (HTTP) Method Types.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML (HTTP) Method Types.
*
* @author Egan Royal
*/
public final class MethodKind extends StringEnum
{
  /**
  * value "POST"
  */
  public final static MethodKind POST = new MethodKind("POST");
  
  /**
  * value "GET"
  */
  public final static MethodKind GET  = new MethodKind("GET");
  
  /**
  * Takes a String and returns a MethodKind iff the String is valid.
  *
  * @param val The String value
  * @return The MethodKind that corresponds to the String value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the String value does not correspond to a valid MethodKind
  */
  public static MethodKind getInstance(String s) throws OculusException
  {
    if(s.equals("POST"))
      return POST;
    else if(s.equals("GET"))
      return GET;
    else
      throw new OculusException("Invalid MethodKind.");
  }//end getInstance
  
  /** Private constructor */
  private MethodKind(String s) { super(s); }
}