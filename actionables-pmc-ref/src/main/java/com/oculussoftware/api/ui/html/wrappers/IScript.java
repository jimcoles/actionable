package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IScript.java $
* Description: Represents a generic Script interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Script interface.
*
* @author Egan Royal
*/
public interface IScript extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Charset of the 
  * Script Tag.
  * @param paramVal The value to be set for Charset of the Script Tag.
  * @return this
  */ 
  public IScript setCharset(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Defer of the 
  * Script Tag.
  * @return this
  */
  public IScript setDefer();
  
  /**
  * This method sets the HTML parameter value for Event of the 
  * Script Tag.
  * @param paramVal The value to be set for Event of the Script Tag.
  * @return this
  */
  public IScript setEvent(String paramVal);
  
  /**
  * This method sets the HTML parameter value for For of the 
  * Script Tag.
  * @param paramVal The value to be set for For of the Script Tag.
  * @return this
  */
  public IScript setFor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Language of the 
  * Script Tag.
  * @param paramVal The value to be set for Language of the Script Tag.
  * @return this
  */
  public IScript setLanguage(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Src of the 
  * Script Tag.
  * @param paramVal The value to be set for Src of the Script Tag.
  * @return this
  */
  public IScript setSrc(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Type of the 
  * Script Tag.
  * @param paramVal The value to be set for Type of the Script Tag.
  * @return this
  */
  public IScript setType(String paramVal);
  
  /** 
  * Sets the String bounded by the Script tag.
  * @param val The String bounded by the Script tag.
  * @return this
  */
  public IScript setStringValue(String val);
  
  /** 
  * Appends to the String bounded by the Script tag.
  * @param val The String appended to the String bounded by the Script tag.
  * @return this
  */
  public IScript appendStringValue(String val);
  
}//end IScript interface def