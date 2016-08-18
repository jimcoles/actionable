package com.oculussoftware.api.sysi;

/** Thrown by data access / business layer when client attempts to
*   select a data object that is locked-for-edit by another user.
*
* @author Jim Coles
*/

/*
* $Workfile: ObjectLockedException.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public class ObjectLockedException extends OculusException
{
  /** Creates the exception with the default message.
  */
  public ObjectLockedException( )
  {
    super("Resource locked");
  }

  /** Creates the exception with the given message.
  *
  * @param msg the message for this exception
  */
  public ObjectLockedException(String msg)
  {
    super(msg);
  }
}