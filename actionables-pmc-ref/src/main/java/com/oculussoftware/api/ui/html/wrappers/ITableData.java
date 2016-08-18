package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: ITableData.java $
* Description: Represents a generic TableData interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic TableData interface.
*
* @author Egan Royal
*/
public interface ITableData extends IGenericElement
{
  /**
  * Adds the image with the appropriate link to pull up the calendar set to the 
  * current date.
  * return this
  */
  public ITableData addCalendar();
  
  /**
  * Adds the image with the appropriate link to pull up the calendar set to the 
  * passed in date.
  * @param date The date to set the calendar to.
  * return this
  */
  public ITableData addCalendar(java.sql.Timestamp date);
  
  /**
  * Adds the image with the appropriate link to pull up the calendar set to the 
  * current date and bound to the passed in input.
  * @param input The path to the input for the calendar to be bound to.
  * return this
  */
  public ITableData addCalendar(String input);
  
  /**
  * Adds the image with the appropriate link to pull up the calendar set to the 
  * passed in date and bound to the passed in input.
  * @param date The date to set the calendar to.
  * @param input The path to the input for the calendar to be bound to.
  * return this
  */
  public ITableData addCalendar(String input, java.sql.Timestamp date);
  
  /**
  * Adds and returns an IGraph.
  * @param rf The ReportFormatter.
  * @param rds The ReportDataSet.
  * @return The IGraph. 
  */
  public IGraph addGraph(IReportFormatter rf,com.oculussoftware.api.busi.common.reports.IReportDataSet rds);
  
  /**
  * Adds and returns an IDisplayAttrTable.
  * @param context The current context.
  * @param classid The class for which the Display Attributes are desired.
  * @return The IDisplayAttrTable.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid)
     throws com.oculussoftware.api.sysi.OculusException;
  
  /**
  * Adds and returns an IDisplayAttrTable.
  * @param context The current context.
  * @param classid The class for which the Display Attributes are desired.
  * @param report The report used to select defaults in the select boxes.
  * @return The IDisplayAttrTable. 
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid, com.oculussoftware.api.busi.common.reports.IBasicReport report)
     throws com.oculussoftware.api.sysi.OculusException;
  
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
  * Adds and returns an IAnchor setting the StringValue and the HRef.
  * @param sval The String Value of the IAnchor
  * @param href The HRef of the IAnchor
  * @return The IAnchor. 
  */
  public IAnchor addAnchor(String sval, String href);
  
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
  * Adds and returns an IHeader.
  * @param type The type/size of the Header
  * @return The IHeader. 
  * @exception com.oculussoftware.api.sysi.OculusException
  */  
  public IHeader addHeader(int type) throws OculusException; 
  
  /**
  * Adds and returns an IHR.
  * @return The IHR. 
  */ 
  public IHR addHR(); 
  
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
  * Adds and returns an ISelect setting the Name.
  * @param name The Name of the ISelect.
  * @return The ISelect. 
  */  
  public ISelect addSelect(String name);
  
  /**
  * Adds and returns an ISelect setting the Name and the Size.
  * @param name The Name of the ISelect.
  * @param size The Size of the ISelect.
  * @return The ISelect. 
  */  
  public ISelect addSelect(String name, int size);
  
  /**
  * Adds and returns an ITable.
  * @return The ITable. 
  */  
  public ITable addTable(); 
  
  /**
  * Adds and returns an ITextArea.
  * @return The ITextArea. 
  */ 
  public ITextArea addTextArea(); 
  
  /**
  * Adds and returns an ITextArea setting the Name.
  * @param name The Name of the ITextArea.
  * @return The ITextArea. 
  */
  public ITextArea addTextArea(String name);
  
  /**
  * Adds and returns an ITextArea setting the Name.
  * @param name The Name of the ITextArea.
  * @param value The value (String Value) of the ITextArea.
  * @return The ITextArea. 
  */  
  public ITextArea addTextArea(String name, String value);  
  
  /**
  * This method sets the HTML parameter value for Abbr of the 
  * TableData Tag.
  * @param paramVal The value to be set for Abbr of the TableData Tag.
  * @return this
  */
  public ITableData setAbbr(String paramVal);  
  
  /**
  * This method sets the HTML parameter value for Align of the 
  * TableData Tag.
  * @param a The value to be set for Align of the TableData Tag.
  * @return this
  */
  public ITableData setAlign(AlignKind a);
  
  /**
  * This method sets the HTML parameter value for Axis of the 
  * TableData Tag.
  * @param paramVal The value to be set for Axis of the TableData Tag.
  * @return this
  */  
  public ITableData setAxis(String paramVal); 
  
  /**
  * This method sets the HTML parameter value for BGColor of the 
  * TableData Tag.
  * @param paramVal The value to be set for BGColor of the TableData Tag.
  * @return this
  */ 
  public ITableData setBGColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Char of the 
  * TableData Tag.
  * @param paramVal The value to be set for Char of the TableData Tag.
  * @return this
  */  
  public ITableData setChar(String paramVal); 
  
  /**
  * This method sets the HTML parameter value for CharOff of the 
  * TableData Tag.
  * @param offset The value to be set for CharOff of the TableData Tag.
  * @return this
  */ 
  public ITableData setCharOff(int offset); 
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * TableData Tag.
  * @param ck The value to be set for Class of the TableData Tag.
  * @return this
  */ 
  public ITableData setClass(ClassKind ck); 
  
  /**
  * This method sets the HTML parameter value for ColSpan of the 
  * TableData Tag.
  * @param colspan The value to be set for ColSpan of the TableData Tag.
  * @return this
  */ 
  public ITableData setColSpan(int colspan); 
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * TableData Tag.
  * @param direction The value to be set for Dir of the TableData Tag.
  * @return this
  */ 
  public ITableData setDir(DirKind direction); 
  
  /**
  * This method sets the HTML parameter value for Headers of the 
  * TableData Tag.
  * @param paramVal The value to be set for Headers of the TableData Tag.
  * @return this
  */ 
  public ITableData setHeaders(String paramVal);  
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * TableData Tag.
  * @param pixels The value to be set for Height of the TableData Tag.
  * @return this
  */
  public ITableData setHeight(int pixels);
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * TableData Tag.
  * @param percentage The value to be set for Height of the TableData Tag.
  * @return this
  */  
  public ITableData setHeightRatio(int percentage); 
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * TableData Tag.
  * @param paramVal The value to be set for ID of the TableData Tag.
  * @return this
  */ 
  public ITableData setID(String paramVal);  
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * TableData Tag.
  * @param lang The value to be set for Lang of the TableData Tag.
  * @return this
  */
  public ITableData setLang(LangKind lang);  
  
  /**
  * This method sets the HTML parameter value for Nowrap of the 
  * TableData Tag.
  * @return this
  */
  public ITableData setNowrap(); 
  
  /**
  * This method sets the HTML parameter value for RowSpan of the 
  * TableData Tag.
  * @param rowspan The value to be set for RowSpan of the TableData Tag.
  * @return this
  */
  public ITableData setRowSpan(int rowspan);
  
  /**
  * This method sets the HTML parameter value for Scope of the 
  * TableData Tag.
  * @param scope The value to be set for Scope of the TableData Tag.
  * @return this
  */  
  public ITableData setScope(ScopeKind scope); 
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * TableData Tag.
  * @param paramVal The value to be set for Style of the TableData Tag.
  * @return this
  */ 
  public ITableData setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * TableData Tag.
  * @param paramVal The value to be set for Title of the TableData Tag.
  * @return this
  */  
  public ITableData setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for VAlign of the 
  * TableData Tag.
  * @param valign The value to be set for VAlign of the TableData Tag.
  * @return this
  */  
  public ITableData setVAlign(VAlignKind valign); 
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * TableData Tag.
  * @param pixels The value to be set for Width of the TableData Tag.
  * @return this
  */ 
  public ITableData setWidthFixed(int pixels); 
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * TableData Tag.
  * @param percentage The value to be set for Width of the TableData Tag.
  * @return this
  */ 
  public ITableData setWidthRatio(int percentage); 
  
  /**
  * This method sets the OnClick Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the TableData
  * Tag.
  * @return this
  */
  public ITableData setOnClick(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the TableData Tag.
  * @return this
  */ 
  public ITableData appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the TableData
  * Tag.
  * @return this
  */
  public ITableData setOnDblClick(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the TableData Tag.
  * @return this
  */ 
  public ITableData appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnKeyDown(String paramVal);  
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the TableData Tag.
  * @return this
  */
  public ITableData appendOnKeyDown(String paramVal);  
  
  /**
  * This method sets the OnKeyPress Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the TableData
  * Tag.
  * @return this
  */
  public ITableData setOnKeyPress(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the TableData Tag.
  * @return this
  */ 
  public ITableData appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the TableData Tag.
  * @return this
  */  
  public ITableData appendOnKeyUp(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnMouseDown(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the TableData Tag.
  * @return this
  */ 
  public ITableData appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the TableData Tag.
  * @return this
  */  
  public ITableData appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnMouseOut(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the TableData Tag.
  * @return this
  */ 
  public ITableData appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the TableData Tag.
  * @return this
  */  
  public ITableData appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the TableData to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the TableData
  * Tag.
  * @return this
  */  
  public ITableData setOnMouseUp(String paramVal); 
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the TableData Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the TableData Tag.
  * @return this
  */  
  public ITableData appendOnMouseUp(String paramVal);
}//end ITableData interface def