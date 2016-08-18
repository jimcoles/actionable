package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: ILink.java $
* Description: Represents a generic Link interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Link interface.
*
* @author Egan Royal
*/
public interface ILink extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for CharSet of the 
  * Link Tag.
  * @param paramVal The value to be set for CharSet of the Link Tag.
  * @return this
  */  
  public ILink setCharSet(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Link Tag.
  * @param paramVal The value to be set for Class of the Link Tag.
  * @return this
  */
  public ILink setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Link Tag.
  * @param d The value to be set for Dir of the Link Tag.
  * @return this
  */
  public ILink setDir(DirKind d);
  
  /**
  * This method sets the HTML parameter value for HRef of the 
  * Link Tag.
  * @param paramVal The value to be set for HRef of the Link Tag.
  * @return this
  */
  public ILink setHRef(String paramVal);
  
  /**
  * This method sets the HTML parameter value for HRefLang of the 
  * Link Tag.
  * @param l The value to be set for HRefLang of the Link Tag.
  * @return this
  */
  public ILink setHRefLang(LangKind l);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Link Tag.
  * @param paramVal The value to be set for ID of the Link Tag.
  * @return this
  */
  public ILink setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Link Tag.
  * @param l The value to be set for Lang of the Link Tag.
  * @return this
  */
  public ILink setLang(LangKind l);
  
  /**
  * This method sets the HTML parameter value for Rel of the 
  * Link Tag.
  * @param paramVal The value to be set for Rel of the Link Tag.
  * @return this
  */
  public ILink setRel(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Rev of the 
  * Link Tag.
  * @param paramVal The value to be set for Rev of the Link Tag.
  * @return this
  */
  public ILink setRev(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Link Tag.
  * @param paramVal The value to be set for Style of the Link Tag.
  * @return this
  */
  public ILink setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Target of the 
  * Link Tag.
  * @param paramVal The value to be set for Target of the Link Tag.
  * @return this
  */
  public ILink setTarget(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Link Tag.
  * @param paramVal The value to be set for Title of the Link Tag.
  * @return this
  */
  public ILink setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Type of the 
  * Link Tag.
  * @param paramVal The value to be set for Type of the Link Tag.
  * @return this
  */
  public ILink setType(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Link Tag.
  * @return this
  */
  public ILink appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Link Tag.
  * @return this
  */
  public ILink appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Link Tag.
  * @return this
  */
  public ILink appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Link Tag.
  * @return this
  */
  public ILink appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Link Tag.
  * @return this
  */
  public ILink appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Link Tag.
  * @return this
  */
  public ILink appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Link Tag.
  * @return this
  */
  public ILink appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Link Tag.
  * @return this
  */
  public ILink appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Link Tag.
  * @return this
  */
  public ILink appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Link to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Link
  * Tag.
  * @return this
  */
  public ILink setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Link Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Link Tag.
  * @return this
  */
  public ILink appendOnKeyUp(String paramVal);
}//end ILink interface def