package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IForm.java $
* Description: Represents a generic Form interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Form interface.
*
* @author Egan Royal
*/
public interface IForm extends IGenericElement
{
  /**
  * Adds and returns an IAnchor.
  * @return The IAnchor. 
  */
  public IAnchor addAnchor();
  
  /**
  * Adds and returns an IAnchor setting the StringValue.
  * @param sval The String Value of the IAnchor
  * @return The IAnchor. 
  */
  public IAnchor addAnchor(String sval);
  
  /**
  * Adds and returns an IAnchor setting the String Value.
  * @param sval The String Value of the IAnchor
  * @param href The HRef value of the IAnchor
  * @return The IAnchor. 
  */
  public IAnchor addAnchor(String sval, String href);
  
  /**
  * Adds and returns an ICenter.
  * @return The ICenter. 
  */
  public ICenter addCenter();
  
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
  * Adds and returns an IDisplayAttrTable.
  * @param context The current IObjectContext
  * @param classid The Target Class ID.
  * @return The IDisplayAttrTable. 
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid)
     throws com.oculussoftware.api.sysi.OculusException;
   
  /**
  * Adds and returns an IDisplayAttrTable.
  * @param context The current IObjectContext
  * @param classid The Target Class ID.
  * @param report The BasicReport used to repopulate the Table.
  * @return The IDisplayAttrTable. 
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid, com.oculussoftware.api.busi.common.reports.IBasicReport report)
     throws com.oculussoftware.api.sysi.OculusException;

  /**
  * Adds and returns an ITextArea.
  * @return The ITextArea. 
  */
  public ITextArea addTextArea();
  
  /**
  * Adds and returns an ITextArea and sets the Name and the String Value of the ITextarea.
  * @param name The Name of the ITextarea.
  * @param value The String Value of the ITextarea
  * @return The ITextArea. 
  */
  public ITextArea addTextArea(String name, String value);
  
  /**
  * Adds and returns an IBR.
  * @return The IBR. 
  */
  public IBR addBR();
    
  /**
  * This method sets the HTML parameter value for Acceptcharset of the 
  * Form Tag.
  * @param paramVal The value to be set for Acceptcharset of the Form Tag.
  * @return this
  */
  public IForm setAcceptcharset(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Action of the 
  * Form Tag.
  * @param paramVal The value to be set for Action of the Form Tag.
  * @return this
  */
  public IForm setAction(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Form Tag.
  * @param paramVal The value to be set for Class of the Form Tag.
  * @return this
  */
  public IForm setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Form Tag.
  * @param d The value to be set for Dir of the Form Tag.
  * @return this
  */
  public IForm setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for EncType of the 
  * Form Tag.
  * @param paramVal The value to be set for EncType of the Form Tag.
  * @return this
  */
  public IForm setEncType(String paramVal);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Form Tag.
  * @param paramVal The value to be set for ID of the Form Tag.
  * @return this
  */
  public IForm setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Form Tag.
  * @param l The value to be set for Lang of the Form Tag.
  * @return this
  */
  public IForm setLang(LangKind l);
  
  /**
  * This method sets the HTML parameter value for Method of the 
  * Form Tag.
  * @param m The value to be set for Method of the Form Tag.
  * @return this
  */
  public IForm setMethod(MethodKind m);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Form Tag.
  * @param paramVal The value to be set for Name of the Form Tag.
  * @return this
  */
  public IForm setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Form Tag.
  * @param paramVal The value to be set for Style of the Form Tag.
  * @return this
  */
  public IForm setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Target of the 
  * Form Tag.
  * @param paramVal The value to be set for Target of the Form Tag.
  * @return this
  */
  public IForm setTarget(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Form Tag.
  * @param paramVal The value to be set for Title of the Form Tag.
  * @return this
  */
  public IForm setTitle(String paramVal);
  
  /**
  * This method sets the OnReset Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnReset Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnReset(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnReset Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnReset Event of the Form Tag.
  * @return this
  */
  public IForm appendOnReset(String paramVal);
  
  /**
  * This method sets the OnSubmit Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnSubmit Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnSubmit(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnSubmit Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnSubmit Event of the Form Tag.
  * @return this
  */
  public IForm appendOnSubmit(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Form Tag.
  * @return this
  */
  public IForm appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Form Tag.
  * @return this
  */
  public IForm appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Form Tag.
  * @return this
  */
  public IForm appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Form Tag.
  * @return this
  */
  public IForm appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Form Tag.
  * @return this
  */
  public IForm appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Form Tag.
  * @return this
  */
  public IForm appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Form Tag.
  * @return this
  */
  public IForm appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Form Tag.
  * @return this
  */
  public IForm appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Form Tag.
  * @return this
  */
  public IForm appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Form to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Form
  * Tag.
  * @return this
  */
  public IForm setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Form Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Form Tag.
  * @return this
  */
  public IForm appendOnKeyUp(String paramVal);
}//end IForm interface def