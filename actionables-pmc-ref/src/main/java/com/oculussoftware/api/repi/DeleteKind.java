package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    DeleteKind.java
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

public final class DeleteKind extends IntEnum
{
	//
  public final static DeleteKind DELETE = new DeleteKind(0);
  // 
  public final static DeleteKind NO_DELETE = new DeleteKind(1);
  
  public static DeleteKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return DELETE;
    else if(d == 1)
      return NO_DELETE;
    else
      throw new OculusException("Invalid DeleteKind.");
  }//end getInstance
  
  /** Private constructor */
  private DeleteKind(int s) { super(s); }
}