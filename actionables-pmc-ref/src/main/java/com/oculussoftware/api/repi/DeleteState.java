package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    DeleteState.java
* Date:        
* Description: Typesafe constants indicating a Delete type.
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

public final class DeleteState extends IntEnum
{
	//
  public final static DeleteState DELETED     = new DeleteState(0);
  // 
  public final static DeleteState NOT_DELETED = new DeleteState(1);
  
  public static DeleteState getInstance(int d) throws OculusException
  {
    if(d == 0)
      return DELETED;
    else if(d == 1)
      return NOT_DELETED;
    else
      throw new OculusException("Invalid DeleteState.");
  }//end getInstance
  
  /** Private constructor */
  private DeleteState(int s) { super(s); }
}