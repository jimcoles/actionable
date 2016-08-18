package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ITextArea.java $
* Description: Represents a generic TextArea interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic TextArea interface.
*
* @author Egan Royal
*/
public interface ITextArea extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for AccessKey of the 
  * Textarea Tag.
  * @param paramVal The value to be set for AccessKey of the Textarea Tag.
  * @return this
  */ 
  public ITextArea setAccessKey(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Textarea Tag.
  * @param paramVal The value to be set for Class of the Textarea Tag.
  * @return this
  */
  public ITextArea setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Cols of the 
  * Textarea Tag.
  * @param cols The value to be set for Cols of the Textarea Tag.
  * @return this
  */
  public ITextArea setCols(int cols);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Textarea Tag.
  * @param direction The value to be set for Dir of the Textarea Tag.
  * @return this
  */
  public ITextArea setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for Disabled of the 
  * Textarea Tag.
  * @return this
  */
  public ITextArea setDisabled();
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Textarea Tag.
  * @param paramVal The value to be set for ID of the Textarea Tag.
  * @return this
  */
  public ITextArea setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Textarea Tag.
  * @param paramVal The value to be set for Name of the Textarea Tag.
  * @return this
  */
  public ITextArea setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ReadOnly of the 
  * Textarea Tag.
  * @return this
  */
  public ITextArea setReadOnly();
  
  /**
  * This method sets the HTML parameter value for Rows of the 
  * Textarea Tag.
  * @param rows The value to be set for Rows of the Textarea Tag.
  * @return this
  */
  public ITextArea setRows(int rows);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Textarea Tag.
  * @param paramVal The value to be set for Style of the Textarea Tag.
  * @return this
  */
  public ITextArea setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for TabIndex of the 
  * Textarea Tag.
  * @param index The value to be set for TabIndex of the Textarea Tag.
  * @return this
  */
  public ITextArea setTabIndex(int index);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Textarea Tag.
  * @param paramVal The value to be set for Title of the Textarea Tag.
  * @return this
  */
  public ITextArea setTitle(String paramVal);
  
  /** 
  * Sets the String bounded by the TextArea tag.
  * @param value The String bounded by the TextArea tag.
  * @return this
  */
  public ITextArea setStringValue(String sval);
  
  /** 
  * Sets an alias for the formvalidation.
  * @param alias The alias for the formvalidation.
  * @return this
  */
  public ITextArea setAlias(String alias);
  
  /**
  * Returns the alias.
  * @return The alias.
  */
  public String getAlias();
  
  /**
  * This method sets the OnBlur Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnBlur Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnBlur(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnBlur Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnBlur Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnBlur(String paramVal);
  
  /**
  * This method sets the OnChange Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnChange Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnChange(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnChange Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnChange Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnChange(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnFocus Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnFocus Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnFocus(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnFocus Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnFocus Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnFocus(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnKeyUp(String paramVal);
  
  /**
  * This method sets the OnSelect Event of the Textarea to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnSelect Event of the Textarea
  * Tag.
  * @return this
  */
  public ITextArea setOnSelect(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnSelect Event of the Textarea Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnSelect Event of the Textarea Tag.
  * @return this
  */
  public ITextArea appendOnSelect(String paramVal);

  /**
  * Sets up the validation for this Textarea.
  * @param type The ValidationKind.
  * @return this
  */
  public ITextArea setValidation(ValidationKind type);
  
  /**
  * Sets up the validation for this Textarea and applies the given alias.
  * @param type The ValidationKind.
  * @param alias The alias for the validation error message.
  * @return this
  */
  public ITextArea setValidation(ValidationKind type, String name);

  /**
  * Sets the value that determines whether or not to spell check this Textarea.
  * @return this
  */
  public ITextArea excludeSpellCheck();

}//end ITextArea interface def