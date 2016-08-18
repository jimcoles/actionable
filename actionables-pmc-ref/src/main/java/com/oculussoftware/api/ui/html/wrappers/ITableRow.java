package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ITableRow.java $
* Description: Represents a generic TableRow interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic TableRow interface.
*
* @author Egan Royal
*/
public interface ITableRow extends IGenericElement
{
  /**
  * Adds and returns an ITableData.
  * @return The ITableData. 
  */
  public ITableData addTableData();
  
  /**
  * Adds and returns an ITableData adding an IAnchor with a set String Value.
  * @param sval The String Value.
  * @return The ITableData. 
  */
  public ITableData addTableData(String sval);
  
  /**
  * Adds and returns an ITableHeader.
  * @return The ITableHeader. 
  */
  public ITableHeader addTableHeader();
  
  /**
  * Adds and returns an ITableHeader adding an IAnchor with a set String Value.
  * @param sval The String Value.
  * @return The ITableData. 
  */
  public ITableHeader addTableHeader(String sval);
  
  /**
  * This method sets the HTML parameter value for Align of the 
  * TableRow Tag.
  * @param a The value to be set for Align of the TableRow Tag.
  * @return this
  */
  public ITableRow setAlign(AlignKind a);
  
  /**
  * This method sets the HTML parameter value for BGColor of the 
  * TableRow Tag.
  * @param paramVal The value to be set for BGColor of the TableRow Tag.
  * @return this
  */
  public ITableRow setBGColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Char of the 
  * TableRow Tag.
  * @param character The value to be set for Char of the TableRow Tag.
  * @return this
  */
  public ITableRow setChar(char character);
  
  /**
  * This method sets the HTML parameter value for CharOff of the 
  * TableRow Tag.
  * @param offset The value to be set for CharOff of the TableRow Tag.
  * @return this
  */
  public ITableRow setCharOff(int offset);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * TableRow Tag.
  * @param paramVal The value to be set for Class of the TableRow Tag.
  * @return this
  */
  public ITableRow setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * TableRow Tag.
  * @param direction The value to be set for Dir of the TableRow Tag.
  * @return this
  */
  public ITableRow setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * TableRow Tag.
  * @param paramVal The value to be set for ID of the TableRow Tag.
  * @return this
  */
  public ITableRow setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * TableRow Tag.
  * @param lang The value to be set for Lang of the TableRow Tag.
  * @return this
  */
  public ITableRow setLang(LangKind lang);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * TableRow Tag.
  * @param paramVal The value to be set for Style of the TableRow Tag.
  * @return this
  */
  public ITableRow setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * TableRow Tag.
  * @param paramVal The value to be set for Title of the TableRow Tag.
  * @return this
  */
  public ITableRow setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for VAlign of the 
  * TableRow Tag.
  * @param valign The value to be set for VAlign of the TableRow Tag.
  * @return this
  */
  public ITableRow setVAlign(VAlignKind valign);
  
  /**
  * This method sets the OnClick Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the TableRow to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the TableRow
  * Tag.
  * @return this
  */
  public ITableRow setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the TableRow Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the TableRow Tag.
  * @return this
  */
  public ITableRow appendOnKeyUp(String paramVal);
}//end ITableRow interface def