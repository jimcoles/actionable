package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IIIDEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    AccessKind.java
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

public final class AccessKind extends IIIDEnum
{
	//
  public final static AccessKind PUBLIC  = new AccessKind(IDCONST.ACCESS_PUBLIC.getLongValue());
  // 
  public final static AccessKind PRIVATE = new AccessKind(IDCONST.ACCESS_PRIVATE.getLongValue());
  
  public static AccessKind getInstance(IIID d) throws OculusException
  {
    if(d.equals(IDCONST.ACCESS_PUBLIC.getIIDValue()))
      return PUBLIC;
    else if(d.equals(IDCONST.ACCESS_PRIVATE.getIIDValue()))
      return PRIVATE;
    else
      throw new OculusException("Invalid AccessKind.");
  }//end getInstance
  
  /** Private constructor */
  private AccessKind(long s) { super(s); }
}