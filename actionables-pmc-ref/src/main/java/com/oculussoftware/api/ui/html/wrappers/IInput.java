package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IInput.java $
* Description: Represents a generic Input interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Input interface.
*
* @author Egan Royal
*/
public interface IInput extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Accept of the 
  * Input Tag.
  * @param paramVal The value to be set for Accept of the Input Tag.
  * @return this
  */ 
  public IInput setAccept(String paramVal);
  
  /**
  * This method sets the HTML parameter value for AccessKey of the 
  * Input Tag.
  * @param paramVal The value to be set for AccessKey of the Input Tag.
  * @return this
  */
  public IInput setAccessKey(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Align of the 
  * Input Tag.
  * @param paramVal The value to be set for Align of the Input Tag.
  * @return this
  */
  public IInput setAlign(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Alt of the 
  * Input Tag.
  * @param paramVal The value to be set for Alt of the Input Tag.
  * @return this
  */
  public IInput setAlt(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Checked of the 
  * Input Tag.
  * @return this
  */
  public IInput setChecked();
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Input Tag.
  * @param paramVal The value to be set for Class of the Input Tag.
  * @return this
  */
  public IInput setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Input Tag.
  * @param dir The value to be set for Dir of the Input Tag.
  * @return this
  */
  public IInput setDir(DirKind dir);
  
  /**
  * This method sets the HTML parameter value for Disabled of the 
  * Input Tag.
  * @return this
  */
  public IInput setDisabled();
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Input Tag.
  * @param paramVal The value to be set for ID of the Input Tag.
  * @return this
  */
  public IInput setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for MaxLength of the 
  * Input Tag.
  * @param max The value to be set for MaxLength of the Input Tag.
  * @return this
  */
  public IInput setMaxLength(int max);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Input Tag.
  * @param paramVal The value to be set for Name of the Input Tag.
  * @return this
  */
  public IInput setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ReadOnly of the 
  * Input Tag.
  * @return this
  */
  public IInput setReadOnly();
  
  /**
  * This method sets the HTML parameter value for Size of the 
  * Input Tag.
  * @param size The value to be set for Size of the Input Tag.
  * @return this
  */
  public IInput setSize(int size);
  
  /**
  * This method sets the HTML parameter value for Src of the 
  * Input Tag.
  * @param paramVal The value to be set for Src of the Input Tag.
  * @return this
  */
  public IInput setSrc(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Input Tag.
  * @param paramVal The value to be set for Style of the Input Tag.
  * @return this
  */
  public IInput setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for TabIndex of the 
  * Input Tag.
  * @param index The value to be set for TabIndex of the Input Tag.
  * @return this
  */
  public IInput setTabIndex(int index);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Input Tag.
  * @param paramVal The value to be set for Title of the Input Tag.
  * @return this
  */
  public IInput setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Type of the 
  * Input Tag.
  * @param i The value to be set for Type of the Input Tag.
  * @return this
  */
  public IInput setType(InputKind i);
  
  /**
  * This method sets the HTML parameter value for UseMap of the 
  * Input Tag.
  * @param paramVal The value to be set for UseMap of the Input Tag.
  * @return this
  */
  public IInput setUseMap(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Value of the 
  * Input Tag.
  * @param paramVal The value to be set for Value of the Input Tag.
  * @return this
  */
  public IInput setValue(String paramVal);
  
  /**
  * Sets the alias that may be used by the formvalidation error message,
  * spell checker,etc...
  * @param alias The alias.
  * @return this
  */
  public IInput setAlias(String alias);
  
  /**
  * Returns the alias set for this Input.
  * @return The alias set for this Input.
  */
  public String getAlias();
  
  /**
  * This method sets the OnBlur Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnBlur Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnBlur(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnBlur Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnBlur Event of the Input Tag.
  * @return this
  */
  public IInput appendOnBlur(String paramVal);
  
  /**
  * This method sets the OnChange Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnChange Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnChange(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnChange Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnChange Event of the Input Tag.
  * @return this
  */
  public IInput appendOnChange(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Input Tag.
  * @return this
  */
  public IInput appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Input Tag.
  * @return this
  */
  public IInput appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnFocus Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnFocus Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnFocus(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnFocus Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnFocus Event of the Input Tag.
  * @return this
  */
  public IInput appendOnFocus(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Input Tag.
  * @return this
  */
  public IInput appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Input Tag.
  * @return this
  */
  public IInput appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Input Tag.
  * @return this
  */
  public IInput appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Input Tag.
  * @return this
  */
  public IInput appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Input Tag.
  * @return this
  */
  public IInput appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Input Tag.
  * @return this
  */
  public IInput appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Input Tag.
  * @return this
  */
  public IInput appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Input Tag.
  * @return this
  */
  public IInput appendOnKeyUp(String paramVal);
  
  /**
  * This method sets the OnSelect Event of the Input to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnSelect Event of the Input
  * Tag.
  * @return this
  */
  public IInput setOnSelect(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnSelect Event of the Input Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnSelect Event of the Input Tag.
  * @return this
  */
  public IInput appendOnSelect(String paramVal);
  
  /**
  * Sets up the validation for this Input.
  * @param type The ValidationKind.
  * @return this
  */
  public IInput setValidation(ValidationKind type);
  
  /**
  * Sets up the validation for this Input and applies the given alias.
  * @param type The ValidationKind.
  * @param alias The alias for the validation error message.
  * @return this
  */
  public IInput setValidation(ValidationKind type, String alias);
  
  /**
  * Sets the value that excludes this Input from spell checking.
  * @return this
  */
	public IInput excludeSpellCheck();
}//end IInput interface def