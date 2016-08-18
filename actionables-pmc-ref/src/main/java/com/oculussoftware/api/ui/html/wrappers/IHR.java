package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IHR.java $
* Description: Represents a generic HR interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic HR interface.
*
* @author Egan Royal
*/
public interface IHR extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Align of the 
  * HR Tag.
  * @param align The value to be set for Align of the HR Tag.
  * @return this
  */
  public IHR setAlign(AlignKind align);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * HR Tag.
  * @param paramVal The value to be set for Class of the HR Tag.
  * @return this
  */
  public IHR setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * HR Tag.
  * @param paramVal The value to be set for ID of the HR Tag.
  * @return this
  */
  public IHR setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for NoShade of the 
  * HR Tag.
  * @return this
  */
  public IHR setNoShade();
  
  /**
  * This method sets the HTML parameter value for Size of the 
  * HR Tag.
  * @param paramVal The value to be set for Size of the HR Tag.
  * @return this
  */
  public IHR setSize(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * HR Tag.
  * @param paramVal The value to be set for Style of the HR Tag.
  * @return this
  */
  public IHR setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * HR Tag.
  * @param paramVal The value to be set for Title of the HR Tag.
  * @return this
  */
  public IHR setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * HR Tag.
  * @param paramVal The value to be set for Width of the HR Tag.
  * @return this
  */
  public IHR setWidth(String paramVal);
  
  /**
  * This method sets the OnClick Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the HR Tag.
  * @return this
  */
  public IHR appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the HR Tag.
  * @return this
  */
  public IHR appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the HR Tag.
  * @return this
  */
  public IHR appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the HR Tag.
  * @return this
  */
  public IHR appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the HR Tag.
  * @return this
  */
  public IHR appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the HR Tag.
  * @return this
  */
  public IHR appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the HR Tag.
  * @return this
  */
  public IHR appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the HR Tag.
  * @return this
  */
  public IHR appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the HR Tag.
  * @return this
  */
  public IHR appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the HR to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the HR
  * Tag.
  * @return this
  */
  public IHR setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the HR Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the HR Tag.
  * @return this
  */
  public IHR appendOnKeyUp(String paramVal);
}//end IHr interface def