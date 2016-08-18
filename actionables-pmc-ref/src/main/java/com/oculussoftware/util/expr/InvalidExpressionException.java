package com.oculussoftware.util.expr;

import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    InvalidExpressionException.java
* Date:        2-15-2000
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class InvalidExpressionException extends OculusException
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  public InvalidExpressionException()
  {
    super("Invalid Expression Exception");
  }//
  
  public InvalidExpressionException(String msg)
  {
    super(msg);
  }//
  
  public InvalidExpressionException(Exception ex)
  {
    super(ex.getClass().getName() + "==>" + ex.getMessage());
  }//
}//end class