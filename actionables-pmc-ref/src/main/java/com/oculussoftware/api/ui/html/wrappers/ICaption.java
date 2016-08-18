package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: ICaption.java $
* Description: Represents a generic Caption interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Caption interface.
*
* @author Egan Royal
*/
public interface ICaption extends IGenericElement
{
  /** 
  * Sets the String bounded by the Caption tag.
  * @param value The String bounded by the Caption tag.
  * @return this
  */
  public ICaption setStringValue(String value); 
    
  /**
  * This method sets the HTML parameter value for Align of the 
  * Caption Tag.
  * @param paramVal The value to be set for Align of the Caption Tag.
  * @return this
  */
  public ICaption setAlign(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Caption Tag.
  * @param paramVal The value to be set for Class of the Caption Tag.
  * @return this
  */
  public ICaption setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Caption Tag.
  * @param d The value to be set for Dir of the Caption Tag.
  * @return this
  */
  public ICaption setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Caption Tag.
  * @param paramVal The value to be set for ID of the Caption Tag.
  * @return this
  */
  public ICaption setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Caption Tag.
  * @param l The value to be set for Lang of the Caption Tag.
  * @return this
  */
  public ICaption setLang(LangKind l);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Caption Tag.
  * @param paramVal The value to be set for Style of the Caption Tag.
  * @return this
  */
  public ICaption setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Caption Tag.
  * @param paramVal The value to be set for Title of the Caption Tag.
  * @return this
  */
  public ICaption setTitle(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Caption to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Caption
  * Tag.
  * @return this
  */
  public ICaption setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Caption Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Caption Tag.
  * @return this
  */
  public ICaption appendOnKeyUp(String paramVal);
  
}//end Caption interface def