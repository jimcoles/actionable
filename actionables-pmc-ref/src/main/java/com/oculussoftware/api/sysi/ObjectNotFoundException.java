package com.oculussoftware.api.sysi;

/** Thrown by data access / business layer when client attempts to
*   operate on a data object that no longer exists.
*
* @author Jim Coles
*/

/*
* $Workfile: ObjectNotFoundException.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public class ObjectNotFoundException extends OculusException
{
  /** Creates the exception with the given message.
  *
  * @param msg the message for this exception
  */
  public ObjectNotFoundException(String msg)
  {
    super(msg);
  }
}