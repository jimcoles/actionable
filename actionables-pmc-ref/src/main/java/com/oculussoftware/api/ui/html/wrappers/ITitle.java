package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

/*
* $Workfile: ITitle.java $
* Description: Represents a generic Title interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Title interface.
*
* @author Egan Royal
*/
public interface ITitle extends IGenericElement 
{
  /** 
  * Sets the String bounded by the Title tag.
  * @param value The String bounded by the Title tag.
  * @return this
  */
  public ITitle setStringValue(String value);
}//end ITitle interface def