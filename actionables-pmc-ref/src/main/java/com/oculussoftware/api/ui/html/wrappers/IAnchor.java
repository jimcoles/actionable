package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

/*
* $Workfile: IAnchor.java $
* Description: Represents a generic Anchor interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Anchor interface.
*
* @author Egan Royal
*/
public interface IAnchor extends IGenericElement
{
  /** 
  * Sets the String bounded by the Anchor tag.
  * @param value The String bounded by the Anchor tag.
  * @return this
  */
  public IAnchor setStringValue(String value);

  /**
  * Adds and returns an IImg to the IAnchor.
  * @return An instance of IImg.
  */
  public IImg addImg();
  
  /**
  * Adds and returns an IImg to the IAnchor and then sets the passed in
  * values.
  * @param width The width of the IImg in pixels.
  * @param height The height of the IImg in pixels.
  * @param src The source of the IImg.
  * @param alt The alt text of the IImg
  * @return An instance of IImg with the passed in values set.
  */
  public IImg addImg(int width, int height, String src, String alt);
 
  /**
  * This method works with the image select javascript.  It makes the
  * IImg at the given index (of the javascript images array) selectable.
  * @param index The index of the image.
  * @return this
  */
  public IAnchor makeSelectable(int index);
 
  /**
  * This method sets the HTML parameter value for AccessKey of the 
  * Anchor Tag.
  * @param paramVal The value to be set for AccessKey of the Anchor Tag.
  * @return this
  */
  public IAnchor setAccessKey(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Anchor Tag.
  * @param ck The value to be set for Class of the Anchor Tag.
  * @return this
  */
  public IAnchor setClass(ClassKind ck);
  
  /**
  * This method sets the HTML parameter value for HRef of the 
  * Anchor Tag.
  * @param paramVal The value to be set for HRef of the Anchor Tag.
  * @return this
  */
  public IAnchor setHRef(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Anchor Tag.
  * @param paramVal The value to be set for ID of the Anchor Tag.
  * @return this
  */
  public IAnchor setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Anchor Tag.
  * @param paramVal The value to be set for Name of the Anchor Tag.
  * @return this
  */
  public IAnchor setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Anchor Tag.
  * @param paramVal The value to be set for Style of the Anchor Tag.
  * @return this
  */
  public IAnchor setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for TabIndex of the 
  * Anchor Tag.
  * @param paramVal The value to be set for TabIndex of the Anchor Tag.
  * @return this
  */
  public IAnchor setTabIndex(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Target of the 
  * Anchor Tag.
  * @param paramVal The value to be set for Target of the Anchor Tag.
  * @return this
  */
  public IAnchor setTarget(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Anchor Tag.
  * @param paramVal The value to be set for Title of the Anchor Tag.
  * @return this
  */
  public IAnchor setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Type of the 
  * Anchor Tag.
  * @param paramVal The value to be set for Type of the Anchor Tag.
  * @return this
  */
  public IAnchor setType(String paramVal);
	
  /**
  * This method sets the Font of the Anchor.  The method puts a Font Tag
  * around the StringValue of the Anchor setting the Face paramater value
  * the the Font Tag to the passed in paramVal.
  * @param paramVal The value of the Face parameter for the Font Tag.
  * @return this
  */
	public IAnchor setFont(String paramVal);
  
  /**
  * This method sets the OnBlur Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnBlur Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnBlur(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnBlur Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnBlur Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnBlur(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnFocus Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnFocus Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnFocus(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnFocus Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnFocus Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnFocus(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnKeyUp(String paramVal);
  
  /**
  * This method sets the OnHelp Event of the Anchor to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnHelp Event of the Anchor
  * Tag.
  * @return this
  */
  public IAnchor setOnhelp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnHelp Event of the Anchor Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnHelp Event of the Anchor Tag.
  * @return this
  */
  public IAnchor appendOnhelp(String paramVal);
   
}//end IAnchor interface def