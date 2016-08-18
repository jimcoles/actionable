package com.oculussoftware.api.sysi;

/** This interface represents a directory of system information.  It acts somewhat
* like a map in that it uses a key (based on a given directory and property) to retrieve
* a piece of information.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IDirectory.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Changed the names of the parameters so they
*																							are easier to understand. Added OculusException
*																							to getValue()
*/
public interface IDirectory
{
	/** Returns the object associated with the given directory and property pair.
  *
  * @param property the property to retrieve for the given directory
  * @param directory the directory which the property is in
  * @return the value for the given directory/property
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public Object getValue(String property, String directory)
		throws OculusException;
}