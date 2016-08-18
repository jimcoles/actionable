package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IStyle.java $
* Description: Represents a generic Style interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Style interface.
*
* @author Egan Royal
*/
public interface IStyle extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Type of the 
  * Style Tag.
  * @param paramVal The value to be set for Type of the Style Tag.
  * @return this
  */ 
  public IStyle setType(String paramVal);
  
  /** 
  * Sets the String bounded by the Style tag.
  * @param val The String bounded by the Style tag.
  * @return this
  */
  public IStyle setStringValue(String val);
  
  /** 
  * Appends to the String bounded by the Style tag.
  * @param val The String appended to the String bounded by the Style tag.
  * @return this
  */
  public IStyle appendStringValue(String val);    
}//end IStyle interface def