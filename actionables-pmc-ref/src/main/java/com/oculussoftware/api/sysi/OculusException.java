package com.oculussoftware.api.sysi;

import java.io.*;  

/** This exception acts as a base class for all exceptions thrown be this system.
* It signifies any error in processing or unexpected behavior.
*
* @author Jim Coles
*/

/*
* $Workfile: OculusException.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public class OculusException extends Exception
{
  private Exception _ex = null;
	private ErrorType _errCode = null;

  /** Creates the exception based on the given exception.
  *
  * @param ex the exception to base this exception on
  */
  public OculusException(Exception ex)
  {
	  super();
	  _ex = ex;
  }  

  /** Creates the exception based on the given exception.
  *
  * @param ex the exception to base this exception on
  * @param errCode the type of exception this is
  */
  public OculusException(Exception ex, ErrorType errCode)
  {
		this(ex);
	  _errCode = errCode;		
  }  

  /** Creates the exception with the given message
  *
  * @param msg the message describing this exception
  */
  public OculusException (String msg)
  {
  	super(msg);
  }  
  
  /** Creates the exception with the given message
  *
  * @param msg the message describing this exception
  * @param errCode the type of exception this is
  */
  public OculusException (String msg, ErrorType errCode)
  {
	  this(msg);
 	  _errCode = errCode; 
	}

  /** Returns the error code of this exception.
  *
  * @return the error code of this exception
  */
  public ErrorType getErrorCode()
  {
	  return _errCode;
  }

  /** Returns the exception that this exception was created from.
  *
  * @return the exception that this exception was created from
  */
  public Exception getInternalException()
  {
	  return _ex;
  }

  /** Prints the stack trace of this exception to the standard System.err stream.
  */
  public void printStackTrace()
  {
	  if (_ex != null) _ex.printStackTrace();
	  super.printStackTrace();
  }  

  /** Prints the stack trace of this exception to the given output stream.
  *
  * @param out the PrintStream to send the trace to
  */
  public void printStackTrace(PrintStream out)
  {
	  if (_ex != null) _ex.printStackTrace(out);
	  super.printStackTrace(out);
  }  

  /** Prints the stack trace of this exception to the given PrintWriter.
  *
  * @param out the PrintWriter to send the trace to
  */
  public void printStackTrace(PrintWriter out)
  {
    if (_ex != null) _ex.printStackTrace(out);
    super.printStackTrace(out);
  }  
}
