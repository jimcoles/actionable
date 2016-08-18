package com.oculussoftware.api.sysi.license;

import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: InvalidKeyException.java $
* Description: An OculusException that is thrown when an Invalid Key is detected.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An OculusException that is thrown when an Invalid Key is detected.
*
* @author Egan Royal
*/
public class InvalidKeyException extends OculusException
{
  /**
  * Default Constructor.
  */
  public InvalidKeyException()
  {
    super("InvalidKeyException");
  }
  
  /**
  * Takes a String and calls super.
  * @param msg A String message.
  */
  public InvalidKeyException(String msg)
  {
    super(msg);
  }
  
  /**
  * Takes an Exception and calls super.
  * @param ex An Exception.
  */
  public InvalidKeyException(Exception ex)
  {
    super(ex);
  }
}