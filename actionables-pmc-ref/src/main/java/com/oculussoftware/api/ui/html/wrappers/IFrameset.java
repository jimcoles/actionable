package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IFrameset.java $
* Description: Represents a generic Frameset interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Frameset interface.
*
* @author Egan Royal
*/
public interface IFrameset extends IGenericElement
{  
  /**
  * Adds and returns an IFrame.
  * @return The IFrame. 
  */
  public IFrame addFrame();
  
  /**
  * Adds and returns an IFrameset.
  * @return The IFrameset. 
  */
  public IFrameset addFrameset();
  
  /**
  * This method sets the HTML parameter value for Border of the 
  * Frameset Tag.
  * @param pixels The value to be set for Border of the Frameset Tag.
  * @return this
  */
  public IFrameset setBorder(int pixels);
  
  /**
  * This method sets the HTML parameter value for BorderColor of the 
  * Frameset Tag.
  * @param paramVal The value to be set for BorderColor of the Frameset Tag.
  * @return this
  */
  public IFrameset setBorderColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for FrameBorder of the 
  * Frameset Tag.
  * @param border The value to be set for FrameBorder of the Frameset Tag.
  * @return this
  */
  public IFrameset setFrameBorder(boolean border);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Frameset Tag.
  * @param paramVal The value to be set for Class of the Frameset Tag.
  * @return this
  */
  public IFrameset setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Cols of the 
  * Frameset Tag.
  * @param paramVal The value to be set for Cols of the Frameset Tag.
  * @return this
  */
  public IFrameset setCols(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Frameset Tag.
  * @param paramVal The value to be set for ID of the Frameset Tag.
  * @return this
  */
  public IFrameset setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Rows of the 
  * Frameset Tag.
  * @param paramVal The value to be set for Rows of the Frameset Tag.
  * @return this
  */
  public IFrameset setRows(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Frameset Tag.
  * @param paramVal The value to be set for Style of the Frameset Tag.
  * @return this
  */
  public IFrameset setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Frameset Tag.
  * @param paramVal The value to be set for Title of the Frameset Tag.
  * @return this
  */
  public IFrameset setTitle(String paramVal);
  
  /**
  * This method sets the OnLoad Event of the Frameset to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnLoad Event of the Frameset
  * Tag.
  * @return this
  */
  public IFrameset setOnLoad(String paramVal);
  
  /**
  * This method sets the OnUnload Event of the Frameset to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnUnload Event of the Frameset
  * Tag.
  * @return this
  */
  public IFrameset setOnUnload(String paramVal);

  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnLoad Event of the Frameset Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnLoad Event of the Frameset Tag.
  * @return this
  */
  public IFrameset appendOnLoad(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnUnload Event of the Frameset Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnUnload Event of the Frameset Tag.
  * @return this
  */
  public IFrameset appendOnUnload(String paramVal); 
}//end IFrameset interface def