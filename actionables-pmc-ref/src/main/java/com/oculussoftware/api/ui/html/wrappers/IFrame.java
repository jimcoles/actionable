package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IFrame.java $
* Description: Represents a generic Frame interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Frame interface.
*
* @author Egan Royal
*/
public interface IFrame extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Border of the 
  * Frame Tag.
  * @param pixels The value to be set for Border of the Frame Tag.
  * @return this
  */
  public IFrame setBorder(int pixels);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Frame Tag.
  * @param paramVal The value to be set for Class of the Frame Tag.
  * @return this
  */
  public IFrame setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for BorderColor of the 
  * Frame Tag.
  * @param paramVal The value to be set for BorderColor of the Frame Tag.
  * @return this
  */
  public IFrame setBorderColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for FrameBorder of the 
  * Frame Tag.
  * @param border The value to be set for FrameBorder of the Frame Tag.
  * @return this
  */
  public IFrame setFrameBorder(boolean border);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Frame Tag.
  * @param paramVal The value to be set for ID of the Frame Tag.
  * @return this
  */
  public IFrame setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for MarginHeight of the 
  * Frame Tag.
  * @param height The value to be set for MarginHeight of the Frame Tag.
  * @return this
  */
  public IFrame setMarginHeight(int height);
  
  /**
  * This method sets the HTML parameter value for MarginWidth of the 
  * Frame Tag.
  * @param width The value to be set for MarginWidth of the Frame Tag.
  * @return this
  */
  public IFrame setMarginWidth(int width);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Frame Tag.
  * @param paramVal The value to be set for Name of the Frame Tag.
  * @return this
  */
  public IFrame setName(String paramVal);
  
  /**
  * Adds the NoResize parameter to the Frame Tag.
  * @return this
  */
  public IFrame setNoResize();
  
  /**
  * This method sets the HTML parameter value for Scrolling of the 
  * Frame Tag.
  * @param s The value to be set for Scrolling of the Frame Tag.
  * @return this
  */
  public IFrame setScrolling(ScrollingKind s);
  
  /**
  * This method sets the HTML parameter value for Src of the 
  * Frame Tag.
  * @param paramVal The value to be set for Src of the Frame Tag.
  * @return this
  */
  public IFrame setSrc(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Frame Tag.
  * @param paramVal The value to be set for Style of the Frame Tag.
  * @return this
  */
  public IFrame setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Frame Tag.
  * @param paramVal The value to be set for Title of the Frame Tag.
  * @return this
  */
  public IFrame setTitle(String paramVal);

  /**
  * Returns the Src for the Frame tag.
  * @return The Src for the Frame tag.
  */
	public String getSrc();
}// end IFrame interface def