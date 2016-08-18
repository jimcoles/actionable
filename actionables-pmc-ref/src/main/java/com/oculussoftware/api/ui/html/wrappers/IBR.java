package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IBR.java $
* Description: Represents a generic Linebreak interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Linebreak interface.
*
* @author Egan Royal
*/
public interface IBR extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Class of the 
  * BR Tag.
  * @param paramVal The value to be set for Class of the BR Tag.
  * @return this
  */
  public IBR setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Clear of the 
  * BR Tag.
  * @param paramVal The value to be set for Clear of the BR Tag.
  * @return this
  */
  public IBR setClear(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * BR Tag.
  * @param paramVal The value to be set for ID of the BR Tag.
  * @return this
  */
  public IBR setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * BR Tag.
  * @param paramVal The value to be set for Style of the BR Tag.
  * @return this
  */
  public IBR setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * BR Tag.
  * @param paramVal The value to be set for Title of the BR Tag.
  * @return this
  */
  public IBR setTitle(String paramVal);
}//end IBR interface def