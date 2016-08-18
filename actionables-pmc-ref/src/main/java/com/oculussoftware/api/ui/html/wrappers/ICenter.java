package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.OculusException;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ICenter.java $
* Description: Represents a generic Center interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Center interface.
*
* @author Egan Royal
*/
public interface ICenter extends IGenericElement
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
  * Adds and returns an IHR.
  * @return The IHR. 
  */
  public IHR addHR();
  
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
  public IHeader addHeader(int headerSize, String contentVal) 
    throws OculusException;
    
  /**
  * Adds and returns an ITable.
  * @return The ITable. 
  */
  public ITable addTable();
    
  /**
  * Adds and returns an IImg.
  * @return The IImg. 
  */
  public IImg addImg();
  
  /**
  * Adds and returns an IImg and then sets the passed in
  * values.
  * @param width The width of the IImg in pixels.
  * @param height The height of the IImg in pixels.
  * @param src The source of the IImg.
  * @param alt The alt text of the IImg
  * @return An instance of IImg with the passed in values set.
  */  
  public IImg addImg(int width, int height, String src, String alt);
  
  /**
  * Adds and returns an IInput.
  * @return The IInput. 
  */
  public IInput addInput();
  
  /**
  * Adds and returns an IInput and then sets the passed in values.
  * @param i The InputKind.
  * @param value The Value parameter of the IInput.
  * @return The IInput. 
  */
  public IInput addInput(InputKind i, String value);//Buttons
  
  /**
  * Adds and returns an IInput and then sets the passed in values.
  * @param i The InputKind.
  * @param name The Name parameter of the IInput.
  * @param value The Value parameter of the IInput.
  * @return The IInput. 
  */
  public IInput addInput(InputKind i, String name, String value);
  
  /**
  * Adds and returns an ISelect.
  * @return The ISelect. 
  */
  public ISelect addSelect();
  
  /**
  * Adds and returns an ISelect and sets the Name of the ISelect.
  * @param name The Name of the ISelect.
  * @return The ISelect.
  */
  public ISelect addSelect(String name);
  
  /**
  * Adds and returns an ISelect and sets the Name and the size of the ISelect.
  * @param name The Name of the ISelect.
  * @param size The Size of the ISelect.
  * @return The ISelect.
  */
  public ISelect addSelect(String name, int size);
  
  /**
  * Adds and returns an ITextArea.
  * @return The ITextArea. 
  */
  public ITextArea addTextArea();
  
  /**
  * Adds and returns an ITextArea and sets the Name of the ITextarea.
  * @param name The Name of the ITextarea.
  * @return The ITextArea. 
  */
  public ITextArea addTextArea(String name);
  
  /**
  * Adds and returns an ITextArea and sets the Name and the String Value of the ITextarea.
  * @param name The Name of the ITextarea.
  * @param value The String Value of the ITextarea
  * @return The ITextArea. 
  */
  public ITextArea addTextArea(String name, String value);
  
  /** 
  * Sets the String bounded by the Center tag.
  * @param value The String bounded by the Center tag.
  * @return this
  */
  public ICenter setStringValue(String value);

  /**
  * This method sets the HTML parameter value for Class of the 
  * Center Tag.
  * @param paramVal The value to be set for Class of the Center Tag.
  * @return this
  */
  public ICenter setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Center Tag.
  * @param d The value to be set for Dir of the Center Tag.
  * @return this
  */
  public ICenter setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Center Tag.
  * @param paramVal The value to be set for ID of the Center Tag.
  * @return this
  */
  public ICenter setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Center Tag.
  * @param l The value to be set for Lang of the Center Tag.
  * @return this
  */
  public ICenter setLang(LangKind l);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Center Tag.
  * @param paramVal The value to be set for Style of the Center Tag.
  * @return this
  */
  public ICenter setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Center Tag.
  * @param paramVal The value to be set for Title of the Center Tag.
  * @return this
  */
  public ICenter setTitle(String paramVal);

  /**
  * This method sets the OnClick Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Center to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Center
  * Tag.
  * @return this
  */
  public ICenter setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Center Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Center Tag.
  * @return this
  */
  public ICenter appendOnKeyUp(String paramVal);
}//end ICenter interface def