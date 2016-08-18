package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    StateKind.java
* Date:        
* Description: Typesafe constants indicating an State's type.
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

public final class StateKind extends IntEnum
{
	//
  public final static StateKind START = new StateKind(0);
  // 
  public final static StateKind FINAL = new StateKind(1);
  // 
  public final static StateKind NORMAL = new StateKind(2);
  // 
  public final static StateKind START_FINAL = new StateKind(3);
  
  public static StateKind getInstance(int s) throws OculusException
  {
    if(s == 0)
      return START;
    else if(s == 1)
      return FINAL;
    else if(s == 2)
      return NORMAL;
    else if(s == 3)
      return START_FINAL;
    else
      throw new OculusException("Invalid StateKind.");
  }//end getInstance
  
  public static String getStringValue(StateKind sk)
  {
    if(sk == START)
      return "START";
    else if(sk == FINAL)
      return "FINAL";
    else if(sk == NORMAL)
      return "NORMAL";
    else if(sk == START_FINAL)
      return "START_FINAL";
    else
      return ("Invalid StateKind");
  }
  
  /** Private constructor */
  private StateKind(int s) { super(s); }
}