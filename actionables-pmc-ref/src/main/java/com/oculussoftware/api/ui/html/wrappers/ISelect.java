package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ISelect.java $
* Description: Represents a generic Select interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Select interface.
*
* @author Egan Royal
*/
public interface ISelect extends IGenericElement
{
  /**
  * Adds and returns an IOption.
  * @return The IOption. 
  */
  public IOption addOption();
    
  /**
  * Adds and returns an IOption setting the name(String Value) and Value.
  * @param name The name (String Value) of the IOption.
  * @param value The Value of the IOption.
  * @return The IOption. 
  */
  public IOption addOption(String name, String value);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Select Tag.
  * @param paramVal The value to be set for Class of the Select Tag.
  * @return this
  */
  public ISelect setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Select Tag.
  * @param direction The value to be set for Dir of the Select Tag.
  * @return this
  */
  public ISelect setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for Disabled of the 
  * Select Tag.
  * @return this
  */
  public ISelect setDisabled();
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Select Tag.
  * @param paramVal The value to be set for ID of the Select Tag.
  * @return this
  */
  public ISelect setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Multiple of the 
  * Select Tag.
  * @return this
  */
  public ISelect setMultiple();
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Select Tag.
  * @param paramVal The value to be set for Name of the Select Tag.
  * @return this
  */
  public ISelect setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Size of the 
  * Select Tag.
  * @param size The value to be set for Size of the Select Tag.
  * @return this
  */
  public ISelect setSize(int size);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * Select Tag.
  * @param width The value to be set for Width of the Select Tag.
  * @return this
  */
	public ISelect setWidth(int width);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Select Tag.
  * @param paramVal The value to be set for Style of the Select Tag.
  * @return this
  */
  public ISelect setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for TabIndex of the 
  * Select Tag.
  * @param index The value to be set for TabIndex of the Select Tag.
  * @return this
  */
  public ISelect setTabIndex(int index);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Select Tag.
  * @param paramVal The value to be set for Title of the Select Tag.
  * @return this
  */
  public ISelect setTitle(String paramVal);
  
  /**
  * This method sets the OnBlur Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnBlur Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnBlur(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnBlur Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnBlur Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnBlur(String paramVal);
  
  /**
  * This method sets the OnChange Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnChange Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnChange(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnChange Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnChange Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnChange(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnFocus Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnFocus Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnFocus(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnFocus Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnFocus Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnFocus(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Select to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Select
  * Tag.
  * @return this
  */
  public ISelect setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Select Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Select Tag.
  * @return this
  */
  public ISelect appendOnKeyUp(String paramVal);
  
  /**
  * Sets up the validation for this Select.
  * @param type The ValidationKind.
  * @return this
  */
  public ISelect setValidation(ValidationKind type);
  
  /**
  * Sets up the validation for this Select and applies the given alias.
  * @param type The ValidationKind.
  * @param alias The alias for the validation error message.
  * @return this
  */
  public ISelect setValidation(ValidationKind type, String alias);
}//end ISelect interface def