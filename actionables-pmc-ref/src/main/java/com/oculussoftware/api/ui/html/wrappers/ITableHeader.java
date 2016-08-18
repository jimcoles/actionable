package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: ITableHeader.java $
* Description: Represents a generic TableHeader interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic TableHeader interface.
*
* @author Egan Royal
*/
public interface ITableHeader extends IGenericElement
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
  * Adds and returns an IAnchor setting the StringValue and the HRef.
  * @param sval The String Value of the IAnchor
  * @param href The HRef of the IAnchor
  * @return The IAnchor. 
  */
  public IAnchor addAnchor(String sval, String href);
  
  /**
  * Adds and returns an IHeader.
  * @param type The type/size of the Header
  * @return The IHeader. 
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHeader addHeader(int type)
    throws OculusException;
  
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
  * This method sets the HTML parameter value for Abbr of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for Abbr of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setAbbr(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Align of the 
  * TableHeader Tag.
  * @param a The value to be set for Align of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setAlign(AlignKind a);
  
  /**
  * This method sets the HTML parameter value for Axis of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for Axis of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setAxis(String paramVal);
  
  /**
  * This method sets the HTML parameter value for BGColor of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for BGColor of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setBGColor(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Char of the 
  * TableHeader Tag.
  * @param character The value to be set for Char of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setChar(char character);
  
  /**
  * This method sets the HTML parameter value for CharOff of the 
  * TableHeader Tag.
  * @param offset The value to be set for CharOff of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setCharOff(int offset);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * TableHeader Tag.
  * @param ck The value to be set for Class of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setClass(ClassKind ck);
  
  /**
  * This method sets the HTML parameter value for ColSpan of the 
  * TableHeader Tag.
  * @param colspan The value to be set for ColSpan of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setColSpan(int colspan);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * TableHeader Tag.
  * @param direction The value to be set for Dir of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setDir(DirKind direction);
  
  /**
  * This method sets the HTML parameter value for Headers of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for Headers of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setHeaders(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * TableHeader Tag.
  * @param pixels The value to be set for Height of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setHeight(int pixels);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for ID of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * TableHeader Tag.
  * @param lang The value to be set for Lang of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setLang(LangKind lang);
  
  /**
  * This method sets the HTML parameter value for Nowrap of the 
  * TableHeader Tag.
  * @return this
  */
  public ITableHeader setNowrap();
  
  /**
  * This method sets the HTML parameter value for RowSpan of the 
  * TableHeader Tag.
  * @param rowspan The value to be set for RowSpan of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setRowSpan(int rowspan);
  
  /**
  * This method sets the HTML parameter value for Scope of the 
  * TableHeader Tag.
  * @param scope The value to be set for Scope of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setScope(ScopeKind scope);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for Style of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * TableHeader Tag.
  * @param paramVal The value to be set for Title of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for VAlign of the 
  * TableHeader Tag.
  * @param valign The value to be set for VAlign of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setVAlign(VAlignKind valign);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * TableHeader Tag.
  * @param pixels The value to be set for Width of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setWidthFixed(int pixels);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * TableHeader Tag.
  * @param percentage The value to be set for Width of the TableHeader Tag.
  * @return this
  */
  public ITableHeader setWidthRatio(int percentage);
  
  /**
  * This method sets the OnClick Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the TableHeader to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the TableHeader
  * Tag.
  * @return this
  */
  public ITableHeader setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the TableHeader Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the TableHeader Tag.
  * @return this
  */
  public ITableHeader appendOnKeyUp(String paramVal);
}//end ITableHeader interface def