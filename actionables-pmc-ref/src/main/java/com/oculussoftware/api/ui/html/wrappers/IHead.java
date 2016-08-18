package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IHead.java $
* Description: Represents a generic Head interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Head interface.
*
* @author Egan Royal
*/
public interface IHead extends IGenericElement
{
  /**
  * Adds and returns an ITitle.
  * @return The ITitle. 
  */
  public ITitle addTitle();
 
  /**
  * Adds and returns an IScript.
  * @return The IScript. 
  */       
  public IScript addScript();
  
  /**
  * Adds and returns an IScript setting the StringValue.
  * @param script The String Value of the IScript
  * @return The IScript. 
  */       
  public IScript addScript(String script);
  
  /**
  * Adds and returns an IMeta.
  * @return The IMeta. 
  */
  public IMeta addMeta();
  
  /**
  * Adds and returns an ILink.
  * @return The ILink. 
  */
  public ILink addLink();
  
  /**
  * Adds and returns an IStyle.
  * @return The IStyle. 
  */
  public IStyle addStyle();  
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Head Tag.
  * @param paramVal The value to be set for Class of the Head Tag.
  * @return this
  */
  public IHead setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Head Tag.
  * @param paramVal The value to be set for ID of the Head Tag.
  * @return this
  */
  public IHead setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Head Tag.
  * @param paramVal The value to be set for Title of the Head Tag.
  * @return this
  */
  public IHead setTitle(String paramVal);
  
  /**
  * Adds and returns an IImageRegisterScript.
  * @return The IImageRegisterScript. 
  */
  public IImageRegisterScript addImageRegisterScript();
  
  /**
  * Returns the IImageRegisterScript (null if it has not been added).
  * @return The IImageRegisterScript (null if it has not been added).
  */
  public IImageRegisterScript getImageRegisterScript();
  
  /**
  * Adds the CalendarScript.
  */
  public void addCalendarScript();
  
  /**
  * Adds the HourGlassScript
  * @return this
  */
	public IHead addHourGlassScript();
  
  /**
  * Adds and returns an IValidationScript.
  * @return The IValidationScript. 
  */
  public IValidationScript addValidationScript();
  
  /**
  * Returns the IValidationScript (null if it has not been added).
  * @return The IValidationScript (null if it has not been added).
  */
  public IValidationScript getValidationScript();  

  /**
  * Adds and returns an ISpellCheckExcludeScript.
  * @return The ISpellCheckExcludeScript. 
  */
  public ISpellCheckExcludeScript addSpellCheckExcludeScript();
  
  /**
  * Adds and returns an IAliasScript.
  * @return The IAliasScript. 
  */
  public IAliasScript addAliasScript();
  
  /**
  * Adds the BrowseScript
  */
  public void addBrowseScript();
  
  /**
  * Adds a Menu js file.
  * @param filename The filename including path to the menu definition js file.
  */
  public void addMenuFile(String filename);
  
  /**
  * Adds the KillEnter Script
  * @return this
  */
  public IHead killEnter();
  
}//end IHead interface def