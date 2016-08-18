package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: BrowserKind.java $
* Description: Integer Enumeration of BrowserKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of BrowserKinds.
*
* @author Egan Royal
*/
public final class BrowserKind extends IntEnum
{
  /**
  * Netscape value 0.
  */
  public final static BrowserKind NETSCAPE = new BrowserKind(0);
  
  /**
  * Internet Explorer value 1.
  */
  public final static BrowserKind IE       = new BrowserKind(1);
  
  /**
  * All value -1. Used when the BrowserKind is not known.
  */
  public final static BrowserKind ALL      = new BrowserKind(-1);
  
  /**
  * Takes an int and returns a BrowserKind iff the int is valid.
  *
  * @param d the int value
  * @return The BrowserKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid BrowserKind
  */
  public static BrowserKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return NETSCAPE;
    else if(d == 1)
      return IE;
    else if(d == -1)
      return ALL;
    else
      throw new OculusException("Invalid BrowserKind.");
  }//end getInstance
  
  /** Private constructor */
  private BrowserKind(int s) { super(s); }
}