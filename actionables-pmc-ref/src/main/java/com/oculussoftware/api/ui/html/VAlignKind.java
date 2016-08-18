package com.oculussoftware.api.ui.html;

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: VAlignKind.java $
* Description: String Enumeration of VAlignKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of VAlignKinds.
*
* @author Egan Royal
*/
public final class VAlignKind extends StringEnum
{
  /**
  * value "BASELINE"
  */
	public final static VAlignKind BASELINE = new VAlignKind("BASELINE");
  
  /**
  * value "BOTTOM"
  */
	public final static VAlignKind BOTTOM   = new VAlignKind("BOTTOM");
  
  /**
  * value "MIDDLE"
  */
	public final static VAlignKind MIDDLE   = new VAlignKind("MIDDLE");
  
  /**
  * value "TOP"
  */
	public final static VAlignKind TOP      = new VAlignKind("TOP");

  /** Private constructor */
  private VAlignKind(String s) { super(s); }

  /**
  * Takes a String and returns a VAlignKind iff the String is valid.
  *
  * @param val The String value
  * @return The VAlignKind that corresponds to the String value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the String value does not correspond to a valid VAlignKind
  */
  public VAlignKind getInstance(String str) throws OculusException
  {
    if (str.equals("BASELINE"))
      return BASELINE;
    else if (str.equals("BOTTOM"))
      return BOTTOM;
    else if (str.equals("MIDDLE"))
      return MIDDLE;
    else if (str.equals("TOP"))
      return TOP;
    else
      throw new OculusException("Invalid VAlignKind");
  }
}
