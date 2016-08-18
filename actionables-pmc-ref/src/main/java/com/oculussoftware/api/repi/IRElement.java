package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/**
* Filename:    IRElement.java
* Date:        
* Description: Every repository object, whether it represents a model or
* user data, is an IRElement.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/3/00			Made IRElement extend IPersistable and
*																							removed the now redundant setIID() and 
*																							methods getIID().
* ---             Saleem Shafi    2/17/00     Made all of the methods throw OculusException
*                                             instead of ORIOException.
*/



/**
* 
*/
public interface IRElement extends IObject, IPersistable
{
	/** Returns the name of the object */
  public String getName()  
    throws OculusException;
  
  /** Returns the description of the object */
  public String getDescription()
    throws OculusException;
  
  /** Sets the name of the object to the given string */
  public IRElement setName(String n)
    throws OculusException;
    
  /** Sets the description of the object to the given string */ 
  public IRElement setDescription(String d)
    throws OculusException;
  
} 