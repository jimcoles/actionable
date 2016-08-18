package com.oculussoftware.api.service.log;

/*
* $Workfile: ILogService.java $
* Description: ILogService supplies the methods necessary to 
* append Strings to a file.  ILogService is implemented with a 
* Singleton that can be accessed and can write to a file.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* ILogService supplies the methods necessary to 
* append Strings to a file.  ILogService is implemented with a 
* Singleton that can be accessed and can write to a file.  The 
* primary use is to print/log stack traces.
*
* @author Egan Royal
*/
public interface ILogService
{
  /**
  * This method sets the full path of the output file.
  * @param path The full path.
  */
  public ILogService setFullPath(String path);
  
  /**
  * This method returns the full path for the destination of the 
  * output file.
  * @return The full path..
  */
  public String getFullPath();

  /**
  * This method takes a Throwable and appends the stack trace to the 
  * output file.
  * @param e The Throwable object.
  * @return this
  */
  public ILogService write(Throwable e);
  
  /**
  * This method takes a String and appends it to the 
  * output file.
  * @param s The String.
  * @return this
  */
  public ILogService write(String s); 
}//end ILogService