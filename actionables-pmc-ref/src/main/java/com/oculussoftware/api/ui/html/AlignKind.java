package com.oculussoftware.api.ui.html;

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: AlignKind.java $
* Description: String Enumeration of HTML AlignKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML AlignKinds.
*
* @author Egan Royal
*/
public final class AlignKind extends StringEnum
{
  /**
  * value "CENTER"
  */
	public final static AlignKind CENTER  = new AlignKind("CENTER");
	
  /**
  * value "JUSTIFY"
  */
  public final static AlignKind JUSTIFY = new AlignKind("JUSTIFY");
	
  /**
  * value "RIGHT"
  */
  public final static AlignKind RIGHT   = new AlignKind("RIGHT");
	
  /**
  * value "LEFT"
  */
  public final static AlignKind LEFT    = new AlignKind("LEFT");
	
  /** Private constructor */
  private AlignKind(String s) { super(s); }

  /**
  * Takes a String and returns a AlignKind iff the String is valid.
  *
  * @param val The String value
  * @return The AlignKind that corresponds to the String value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the String value does not correspond to a valid AlignKind
  */
  public AlignKind getInstance(String str) throws OculusException
  {
    if (str.equals("CENTER"))
      return CENTER;
    else if (str.equals("JUSTIFY"))
      return JUSTIFY;
    else if (str.equals("LEFT"))
      return LEFT;
    else if (str.equals("RIGHT"))
      return RIGHT;
    else
      throw new OculusException("Invalid AlignKind");
  }
}
