package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    ActionKind.java
* Date:        
* Description: Typesafe constants indicating an Action's type.
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

public final class ActionKind extends IntEnum
{
	//
  public final static ActionKind PERCENT = new ActionKind(0);
  // 
  public final static ActionKind COMMENT = new ActionKind(1);
  
  public static ActionKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return PERCENT;
    else if(d == 1)
      return COMMENT;
    else
      throw new OculusException("Invalid ActionKind.");
  }//end getInstance
  
  public static String getStringValue(ActionKind ak)
  {
    if(ak == PERCENT)
      return "PERCENT";
    else if(ak == COMMENT)
      return "COMMENT";
    else
      return ("Invalid ActionKind");
  }//end getStringValue()  
  
  /** Private constructor */
  private ActionKind(int s) { super(s); }
}