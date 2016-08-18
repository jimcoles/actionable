package com.oculussoftware.api.sysi;
  
/** This exception should be thrown whenever an object or resource is being accessed
* and the user does not have access to the object or resource.
*
* @author Jim Coles
*/

/*
* $Workfile: AccessException.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public class AccessException extends OculusException
{
  /** Creates the exception with the generic exception message.
  */
  public AccessException()
  {
    super("Resource access violation.  User does not have access to the operation: ");
  }

  /** Creates the exception with the specified message.
  *
  * @param msg the exception message
  */
  public AccessException(String msg)
  {
    super(msg);
  }
}