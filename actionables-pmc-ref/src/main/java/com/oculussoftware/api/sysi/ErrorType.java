package com.oculussoftware.api.sysi;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This is an enumeration of error types.  It allows the system to classify each
* exception with a certain type.  Resembles error codes.
*
* @author Zain Nemazie
*/

/*
* $Workfile: ErrorType.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public final class ErrorType extends IntEnum
{
	/** This value (0) refers to an exception that violates a unique constraint. */
  public final static ErrorType UNIQUE_CONSTRAINT_VIOLATION        = new ErrorType(0); 

  /** Private constructor */
  private ErrorType(int s) { super(s); }      
  
  
	/** Returns the ErrorType instance that corresponds to the given parameter.  If
  * the paramter does not correspond to an ErrorType, then this method returns null.
  *
  * @param i int value of the desired ErrorType
  * @return the ErrorType corresponding to the given parameter
  */
  public static ErrorType getInstance(int d) throws OculusException
  {
	if(d == 0)
	  return UNIQUE_CONSTRAINT_VIOLATION;
	else
	  throw new OculusException("Invalid ErrorType.");
  }         
}
