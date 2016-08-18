package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IOption.java $
* Description: Represents a generic Option interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Option interface.
*
* @author Egan Royal
*/
public interface IOption extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Class of the 
  * Option Tag.
  * @param paramVal The value to be set for Class of the Option Tag.
  * @return this
  */ 
  public IOption setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Option Tag.
  * @param direction The value to be set for Dir of the Option Tag.
  * @return this
  */
  public IOption setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for Disabled of the 
  * Option Tag.
  * @return this
  */
  public IOption setDisabled();
  
  /**
  * This method sets the HTML parameter value for Label of the 
  * Option Tag.
  * @param direction The value to be set for Label of the Option Tag.
  * @return this
  */
  public IOption setLabel(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Selected of the 
  * Option Tag.
  * @return this
  */
  public IOption setSelected();
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Option Tag.
  * @param direction The value to be set for Style of the Option Tag.
  * @return this
  */
  public IOption setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Option Tag.
  * @param direction The value to be set for Title of the Option Tag.
  * @return this
  */
  public IOption setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Value of the 
  * Option Tag.
  * @param direction The value to be set for Value of the Option Tag.
  * @return this
  */
  public IOption setValue(String paramVal);
  
  /** 
  * Sets the String bounded by the Option tag.
  * @param val The String bounded by the Option tag.
  * @return this
  */
  public IOption setStringValue(String val);
  
  
  /**
  * This method sets the OnClick Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Option Tag.
  * @return this
  */
  public IOption appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Option Tag.
  * @return this
  */
  public IOption appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Option Tag.
  * @return this
  */
  public IOption appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Option Tag.
  * @return this
  */
  public IOption appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Option Tag.
  * @return this
  */
  public IOption appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Option Tag.
  * @return this
  */
  public IOption appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Option Tag.
  * @return this
  */
  public IOption appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Option Tag.
  * @return this
  */
  public IOption appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Option Tag.
  * @return this
  */
  public IOption appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Option to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Option
  * Tag.
  * @return this
  */
  public IOption setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Option Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Option Tag.
  * @return this
  */
  public IOption appendOnKeyUp(String paramVal);
  
}//end IOption interface def