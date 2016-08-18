package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: IBody.java $
* Description: Represents a generic Body interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Body interface.
*
* @author Egan Royal
*/
public interface IBody extends IGenericElement
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
  * Adds and returns an IHeader setting the size of the IHeader.
  * @param headerSize The size of the IHeader.
  * @return The IHeader. 
  */
  public IHeader addHeader(int headerSize) 
    throws OculusException;    

  /**
  * Adds and returns an IHeader setting the size and the String Value.
  * @param headerSize The size of the IHeader.
  * @param stringValue The String Value of the IHeader
  * @return The IHeader. 
  */
  public IHeader addHeader(int headerSize, String stringValue) 
    throws OculusException;
  
  /**
  * Adds and returns an IScript.
  * @return The IScript. 
  */
  public IScript addScript();
  
  /**
  * Adds and returns an IScript setting the String Value.
  * @param stringValue The String Value of the IScript
  * @return The IScript. 
  */       
  public IScript addScript(String stringValue);
  
  /**
  * Adds and returns an INoScript.
  * @return The INoScript. 
  */
  public INoScript addNoScript();
  
  /**
  * Adds and returns an INoFrames.
  * @return The INoFrames. 
  */
  public INoFrames addNoFrames(); 
  
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
  * This method sets the HTML parameter value for ALink of the 
  * Body Tag.
  * @param paramVal The value to be set for ALink of the Body Tag.
  * @return this
  */      
  public IBody setALink(String paramVal);
  
  /**
  * This method sets the HTML parameter value for BackGround of the 
  * Body Tag.
  * @param paramVal The value to be set for BackGround of the Body Tag.
  * @return this
  */
  public IBody setBackGround(String paramVal);
  
  /**
  * This method sets the HTML parameter value for BGColor of the 
  * Body Tag.
  * @param paramVal The value to be set for BGColor of the Body Tag.
  * @return this
  */
  public IBody setBGColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Body Tag.
  * @param paramVal The value to be set for Class of the Body Tag.
  * @return this
  */
  public IBody setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Body Tag.
  * @param d The value to be set for Dir of the Body Tag.
  * @return this
  */
  public IBody setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Body Tag.
  * @param paramVal The value to be set for ID of the Body Tag.
  * @return this
  */
  public IBody setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Link of the 
  * Body Tag.
  * @param paramVal The value to be set for Link of the Body Tag.
  * @return this
  */
  public IBody setLink(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Body Tag.
  * @param paramVal The value to be set for Style of the Body Tag.
  * @return this
  */
  public IBody setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Text of the 
  * Body Tag.
  * @param paramVal The value to be set for Text of the Body Tag.
  * @return this
  */
  public IBody setText(String paramVal); 
  
  /**
  * This method sets the HTML parameter value for VLink of the 
  * Body Tag.
  * @param paramVal The value to be set for VLink of the Body Tag.
  * @return this
  */
  public IBody setVLink(String paramVal);
  
  /**
  * This method sets the HTML parameter value for MarginWidth of the 
  * Body Tag.
  * @param paramVal The value to be set for MarginWidth of the Body Tag.
  * @return this
  */
  public IBody setMarginWidth(int paramVal);
  
  /**
  * This method sets the HTML parameter value for MarginHeight of the 
  * Body Tag.
  * @param paramVal The value to be set for MarginHeight of the Body Tag.
  * @return this
  */
	public IBody setMarginHeight(int paramVal);
  
  /**
  * This method sets the HTML parameter value for LeftMargin of the 
  * Body Tag.
  * @param paramVal The value to be set for LeftMargin of the Body Tag.
  * @return this
  */
  public IBody setLeftMargin(int paramVal);
  
  /**
  * This method sets the HTML parameter value for TopMargin of the 
  * Body Tag.
  * @param paramVal The value to be set for TopMargin of the Body Tag.
  * @return this
  */
  public IBody setTopMargin(int paramVal);
  
  /**
  * This method sets the OnLoad Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnLoad Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnLoad(String paramVal);
  
  /**
  * This method sets the OnUnLoad Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnUnLoad Event of the Body
  * Tag.
  * @return this
  */ 
  public IBody setOnUnload(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnLoad Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnLoad Event of the Body Tag.
  * @return this
  */
  public IBody appendOnLoad(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnUnLoad Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnUnLoad Event of the Body Tag.
  * @return this
  */
  public IBody appendOnUnload(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Body Tag.
  * @return this
  */
  public IBody appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Body Tag.
  * @return this
  */
  public IBody appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Body Tag.
  * @return this
  */
  public IBody appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Body Tag.
  * @return this
  */
  public IBody appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Body Tag.
  * @return this
  */
  public IBody appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Body Tag.
  * @return this
  */
  public IBody appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Body Tag.
  * @return this
  */
  public IBody appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Body Tag.
  * @return this
  */
  public IBody appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Body Tag.
  * @return this
  */
  public IBody appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Body
  * Tag.
  * @return this
  */
  public IBody setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Body Tag.
  * @return this
  */
  public IBody appendOnKeyUp(String paramVal); 
	
  /**
  * This method sets the OnResize Event of the Body to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnResize Event of the Body
  * Tag.
  * @return this
  */
	public IBody setOnResize(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnResize Event of the Body Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnResize Event of the Body Tag.
  * @return this
  */
  public IBody appendOnResize(String paramVal); 
}//end IBody interface def