package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import java.sql.*;
import java.util.PropertyResourceBundle;
import java.io.*;

/**
* Filename:    SimpleDirectory.java
* Date:        1/21/00
* Description: Acts as an IDirectory that implements a load once, static directory.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/3/00			Adjusted the initialization of the singleton reference
*																							so that we wouldn't squash any exceptions.  The side
*																							effect is that the constructor had to throw OculusException.
*																							Also added a check to get() for a null directory so it too
*																							has to throw OculusException.
*/

final public class SimpleDirectory implements IDirectory
{
	private static IDirectory _simpleDir;			// singleton instance of SimpleDirectory
	private PropertyResourceBundle _dir;			// handles the list of properties
	
	//--------------------------- Private Constructors ------------------------------
	/**
	* Loads the directory from a properties file.  Because this IDirectory is implemented
	* with a PropertyResourceBundle, the file needs to be in the form 
	* <CODE>propertyname=value</CODE>.
  * @throws FileNotFoundException This exception is thrown when the properties file is
  * not found.
  * @throws IOException This exception is thrown if the properties file is corrupt.
	*/
	private SimpleDirectory()
		throws FileNotFoundException, IOException
	{
		String filename = "../oculus.ini";			// name and directory of the prop file
		InputStream propertiesFile = new FileInputStream(filename);
		_dir = new PropertyResourceBundle(propertiesFile);		// load the props
	}
	
	/**
	* Returns the singleton SimpleDirectory.  If this is the first call, then it has to
	* load the properties first.
  * @return The singleton instance of the directory.
  * @throws OculusException This exception is thrown if the properties file could not
  * be read.
	*/
	public static IDirectory getInstance()
		throws OculusException
	{
		// if we haven't initialized the directory yet
		if (_simpleDir == null)
		{
			try
			{
				// get a new one
				_simpleDir = new SimpleDirectory();
			}
			catch (Exception exp)
			{
				throw new OculusException(exp.toString());
			}
		}
		// return the handle
		return _simpleDir;
	}
	
	/**
  Returns the object associated with the property of the given directory item.
  @return The object value of the property of the given directory item.
  @throws OculusException This exception is thrown if the directory has not been
  initialized.
  @param property The property of the directory item requested.
  @param directory The directory item for which the property is being requested.
  */
	public Object getValue(String property, String directory)
		throws OculusException
	{
    Object retVal = null;
		// if we haven't initialized the directory yet, throw an exception
		if (_dir == null)
			throw new OculusException("Attempt to access directory before it has been initialized.");
		// return the object
    try
    {
  		retVal = _dir.getObject("obj."+directory+"."+property);
    }
    catch (java.util.MissingResourceException exp)
    {
      throw new OculusException(exp.toString()+" -- obj."+directory+"."+property);
    }
    return retVal;
	}
}