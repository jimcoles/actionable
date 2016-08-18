package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ITable.java $
* Description: Represents a generic Table interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Table interface.
*
* @author Egan Royal
*/
public interface ITable extends IGenericElement
{
  /**
  * Adds and returns an ICaption.
  * @return The ICaption. 
  */  
  public ICaption addCaption();
  
  /**
  * Adds and returns an ICaption setting the StringValue.
  * @param s The String Value of the ICaption
  * @return The ICaption. 
  */
  public ICaption addCaption(String s); 
  
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
  * Adds and returns an ITableRow.
  * @return The ITableRow. 
  */
  public ITableRow addTableRow();
  
  /**
  * Adds and returns an ITableHeader.
  * @return The ITableHeader. 
  */ 
  public ITableHeader addTableHeader();
  
  /**
  * This method sets the HTML parameter value for Align of the 
  * Table Tag.
  * @param a The value to be set for Align of the Table Tag.
  * @return this
  */
  public ITable setAlign(AlignKind a);
  
  /**
  * This method sets the HTML parameter value for BGColor of the 
  * Table Tag.
  * @param paramVal The value to be set for BGColor of the Table Tag.
  * @return this
  */
  public ITable setBGColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Border of the 
  * Table Tag.
  * @param pixels The value to be set for Border of the Table Tag.
  * @return this
  */
  public ITable setBorder(int pixels);
  
  /**
  * This method sets the HTML parameter value for CellPadding of the 
  * Table Tag.
  * @param pixels The value to be set for CellPadding of the Table Tag.
  * @return this
  */
  public ITable setCellPadding(int pixels);
  
  /**
  * This method sets the HTML parameter value for CellSpacing of the 
  * Table Tag.
  * @param pixels The value to be set for CellSpacing of the Table Tag.
  * @return this
  */
  public ITable setCellSpacing(int pixels);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Table Tag.
  * @param paramVal The value to be set for Class of the Table Tag.
  * @return this
  */
  public ITable setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for DataPageSize of the 
  * Table Tag.
  * @param numRecs The value to be set for DataPageSize of the Table Tag.
  * @return this
  */
  public ITable setDataPageSize(int numRecs);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Table Tag.
  * @param direction The value to be set for Dir of the Table Tag.
  * @return this
  */
  public ITable setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for Frame of the 
  * Table Tag.
  * @param frame The value to be set for Frame of the Table Tag.
  * @return this
  */
  public ITable setFrame(FrameKind frame);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Table Tag.
  * @param paramVal The value to be set for ID of the Table Tag.
  * @return this
  */
  public ITable setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Table Tag.
  * @param lang The value to be set for Lang of the Table Tag.
  * @return this
  */
  public ITable setLang(LangKind lang);
  
  /**
  * This method sets the HTML parameter value for Rules of the 
  * Table Tag.
  * @param rules The value to be set for Rules of the Table Tag.
  * @return this
  */
  public ITable setRules(RulesKind rules);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Table Tag.
  * @param paramVal The value to be set for Style of the Table Tag.
  * @return this
  */
  public ITable setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Summary of the 
  * Table Tag.
  * @param paramVal The value to be set for Summary of the Table Tag.
  * @return this
  */
  public ITable setSummary(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Table Tag.
  * @param paramVal The value to be set for Title of the Table Tag.
  * @return this
  */
  public ITable setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * Table Tag.
  * @param pixels The value to be set for Height of the Table Tag.
  * @return this
  */
  public ITable setHeightFixed(int pixels);
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * Table Tag.
  * @param percentage The value to be set for Height of the Table Tag.
  * @return this
  */
  public ITable setHeightRatio(int percentage);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * Table Tag.
  * @param pixels The value to be set for Width of the Table Tag.
  * @return this
  */
  public ITable setWidthFixed(int pixels);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * Table Tag.
  * @param percentage The value to be set for Width of the Table Tag.
  * @return this
  */
  public ITable setWidthRatio(int percentage);
  
  /**
  * This method sets the OnClick Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Table Tag.
  * @return this
  */
  public ITable appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Table Tag.
  * @return this
  */
  public ITable appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Table Tag.
  * @return this
  */
  public ITable appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Table Tag.
  * @return this
  */
  public ITable appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Table Tag.
  * @return this
  */
  public ITable appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Table Tag.
  * @return this
  */
  public ITable appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Table Tag.
  * @return this
  */
  public ITable appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Table Tag.
  * @return this
  */
  public ITable appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Table Tag.
  * @return this
  */
  public ITable appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Table to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Table
  * Tag.
  * @return this
  */
  public ITable setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Table Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Table Tag.
  * @return this
  */
  public ITable appendOnKeyUp(String paramVal);
}//end ITable interface def