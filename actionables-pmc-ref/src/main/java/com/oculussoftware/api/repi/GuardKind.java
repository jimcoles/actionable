package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    GuardKind.java
* Date:        
* Description: Typesafe constants indicating an Guard's type.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public final class GuardKind extends IntEnum
{
	//
  public final static GuardKind PERCENT = new GuardKind(0);
  // 
  public final static GuardKind DESCRIPTION = new GuardKind(1);
  
  public static GuardKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return PERCENT;
    else if(d == 1)
      return DESCRIPTION;
    else
      throw new OculusException("Invalid GuardKind.");
  }//end getInstance
  
  public static String getStringValue(GuardKind gk)
  {
    if(gk == PERCENT)
      return "PERCENT";
    else if(gk == DESCRIPTION)
      return "DESCRIPTION";
    else
      return ("Invalid ActionKind");
  }//end getStringValue()    
  
  /** Private constructor */
  private GuardKind(int s) { super(s); }
}