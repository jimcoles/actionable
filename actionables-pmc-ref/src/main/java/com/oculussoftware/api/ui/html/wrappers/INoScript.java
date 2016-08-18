package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: INoScript.java $
* Description: Represents a generic NoScript interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic NoScript interface.
*
* @author Egan Royal
*/
public interface INoScript extends IGenericElement
{
  /**
  * Adds and returns an IAnchor.
  * @return The IAnchor. 
  */
  public IAnchor addAnchor();
  
  /**
  * Adds and returns an IAnchor setting the StringValue.
  * @param anchorStringValue The String Value of the IAnchor
  * @return The IAnchor. 
  */
  public IAnchor addAnchor(String anchorStringVal);
    
  /**
  * Adds and returns an IBR.
  * @return The IBR. 
  */
  public IBR addBR();  
  
  /**
  * Adds and returns an IHR.
  * @return The IHR. 
  */
  public IHR addHR();
    
  /**
  * Adds and returns an ICenter.
  * @return The ICenter. 
  */
  public ICenter addCenter();  
  
  /**
  * Adds and returns an ICenter setting the String Value.
  * @param stringValue The String Value of the ICenter
  * @return The ICenter. 
  */
  public ICenter addCenter(String stringValue);   
  
  /**
  * Adds and returns an IDiv.
  * @return The IDiv. 
  */
  public IDiv addDiv();   
  
  /**
  * Adds and returns an ITable.
  * @return The ITable. 
  */
  public ITable addTable();
  
  /**
  * Adds and returns an IAttributeTable.
  * @return The IAttributeTable. 
  */
  public IAttributeTable addAttributeTable();
    
  /**
  * Adds and returns an IForm.
  * @return The IForm. 
  */
  public IForm addForm();
  
  /**
  * Adds and returns an IForm and sets the Name, Method, and Action of the IForm.
  * @param name The Name of the IForm.
  * @param method The MethodKind (GET or POST) of the IForm.
  * @param action The Action of the IForm.
  * @return The IForm. 
  */
  public IForm addForm(String name, MethodKind method, String action);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * NoScript Tag.
  * @param paramVal The value to be set for Class of the NoScript Tag.
  * @return this
  */      
  public INoScript setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * NoScript Tag.
  * @param d The value to be set for Dir of the NoScript Tag.
  * @return this
  */
  public INoScript setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * NoScript Tag.
  * @param paramVal The value to be set for ID of the NoScript Tag.
  * @return this
  */
  public INoScript setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * NoScript Tag.
  * @param paramVal The value to be set for Lang of the NoScript Tag.
  * @return this
  */
  public INoScript setLang(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * NoScript Tag.
  * @param paramVal The value to be set for Style of the NoScript Tag.
  * @return this
  */
  public INoScript setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * NoScript Tag.
  * @param paramVal The value to be set for Title of the NoScript Tag.
  * @return this
  */
  public INoScript setTitle(String paramVal);

  /**
  * This method sets the OnClick Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the NoScript to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the NoScript
  * Tag.
  * @return this
  */
  public INoScript setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the NoScript Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the NoScript Tag.
  * @return this
  */
  public INoScript appendOnKeyUp(String paramVal); 
}//end INoScript interface def