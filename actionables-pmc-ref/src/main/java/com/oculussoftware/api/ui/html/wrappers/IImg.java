package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IImg.java $
* Description: Represents a generic Img interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Img interface.
*
* @author Egan Royal
*/
public interface IImg extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Align of the 
  * Img Tag.
  * @param align The value to be set for Align of the Img Tag.
  * @return this
  */ 
  public IImg setAlign(AlignKind align);
  
  /**
  * This method sets the HTML parameter value for Alt of the 
  * Img Tag.
  * @param paramVal The value to be set for Alt of the Img Tag.
  * @return this
  */
  public IImg setAlt(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Border of the 
  * Img Tag.
  * @param pixels The value to be set for Border of the Img Tag.
  * @return this
  */
  public IImg setBorder(int pixels);
  
  /**
  * This method sets the HTML parameter value for Class of the 
  * Img Tag.
  * @param paramVal The value to be set for Class of the Img Tag.
  * @return this
  */
  public IImg setClass(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Img Tag.
  * @param dir The value to be set for Dir of the Img Tag.
  * @return this
  */
  public IImg setDir(DirKind dir);
  
  /**
  * This method sets the HTML parameter value for Height of the 
  * Img Tag.
  * @param pixels The value to be set for Height of the Img Tag.
  * @return this
  */
  public IImg setHeight(int pixels);
  
  /**
  * This method sets the HTML parameter value for HSpace of the 
  * Img Tag.
  * @param pixels The value to be set for HSpace of the Img Tag.
  * @return this
  */
  public IImg setHSpace(int pixels);
  
  /**
  * This method sets the HTML parameter value for ID of the 
  * Img Tag.
  * @param paramVal The value to be set for ID of the Img Tag.
  * @return this
  */
  public IImg setID(String paramVal);
  
  /**
  * This method sets the HTML parameter value for IsMap of the 
  * Img Tag.
  * @return this
  */
  public IImg setIsMap();
  
  /**
  * This method sets the HTML parameter value for LongDesc of the 
  * Img Tag.
  * @param paramVal The value to be set for LongDesc of the Img Tag.
  * @return this
  */
  public IImg setLongDesc(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Img Tag.
  * @param paramVal The value to be set for Name of the Img Tag.
  * @return this
  */
  public IImg setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Src of the 
  * Img Tag.
  * @param paramVal The value to be set for Src of the Img Tag.
  * @return this
  */
  public IImg setSrc(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Style of the 
  * Img Tag.
  * @param paramVal The value to be set for Style of the Img Tag.
  * @return this
  */
  public IImg setStyle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Title of the 
  * Img Tag.
  * @param paramVal The value to be set for Title of the Img Tag.
  * @return this
  */
  public IImg setTitle(String paramVal);
  
  /**
  * This method sets the HTML parameter value for UseMap of the 
  * Img Tag.
  * @param paramVal The value to be set for UseMap of the Img Tag.
  * @return this
  */
  public IImg setUseMap(String paramVal);
  
  /**
  * This method sets the HTML parameter value for VSpace of the 
  * Img Tag.
  * @param pixels The value to be set for VSpace of the Img Tag.
  * @return this
  */
  public IImg setVSpace(int pixels);
  
  /**
  * This method sets the HTML parameter value for Width of the 
  * Img Tag.
  * @param pixels The value to be set for Width of the Img Tag.
  * @return this
  */
  public IImg setWidth(int pixels);
  
  /**
  * Uses the ImageRegisterScript to make this Img selectable.
  * @return The index of the Img.
  */
  public int makeSelectable();
  
  /**
  * Uses the ImageRegisterScript to make the Img at the given index selectable.
  * @param index The index of the Img.
  * @return The index of the Img.
  */
  public int makeSelectable(int index);
  
  /**
  * Uses the ImageRegisterScript to make this Img selectable.
  * @param unselectName The path to the unselected Img.
  * @param selectName The path to the selected Img.
  * @param rollOverName The path to the rollover Img.
  * @return The index of the Img.
  */
  public int makeSelectable(String unselectName, String selectName, String rollOverName);
  
  /**
  * Sets the index of this Img.  This index does not necessarily line up
  * with the index in the javascript images array.
  * @param The index to be set.
  */
  public void setIndex(int index);
  
  /**
  * Returns the index of the Img.  This index does not necessarily line up
  * with the index in the javascript images array.
  * @return The index of the Img.
  */
  public int getIndex();
  
  /**
  * This method sets the OnBlur Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnBlur Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnBlur(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnBlur Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnBlur Event of the Img Tag.
  * @return this
  */
  public IImg appendOnBlur(String paramVal);
  
  /**
  * This method sets the OnClick Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnClick Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnClick Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnClick Event of the Img Tag.
  * @return this
  */
  public IImg appendOnClick(String paramVal);
  
  /**
  * This method sets the OnDblClick Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnDblClick Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnDblClick(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnDblClick Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnDblClick Event of the Img Tag.
  * @return this
  */
  public IImg appendOnDblClick(String paramVal);
  
  /**
  * This method sets the OnFocus Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnFocus Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnFocus(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnFocus Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnFocus Event of the Img Tag.
  * @return this
  */
  public IImg appendOnFocus(String paramVal);
  
  /**
  * This method sets the OnMouseDown Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseDown Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnMouseDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseDown Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseDown Event of the Img Tag.
  * @return this
  */
  public IImg appendOnMouseDown(String paramVal);
  
  /**
  * This method sets the OnMouseUp Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseUp Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnMouseUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseUp Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseUp Event of the Img Tag.
  * @return this
  */
  public IImg appendOnMouseUp(String paramVal);
  
  /**
  * This method sets the OnMouseMove Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseMove Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnMouseMove(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseMove Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseMove Event of the Img Tag.
  * @return this
  */
  public IImg appendOnMouseMove(String paramVal);
  
  /**
  * This method sets the OnMouseOut Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOut Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnMouseOut(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOut Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOut Event of the Img Tag.
  * @return this
  */
  public IImg appendOnMouseOut(String paramVal);
  
  /**
  * This method sets the OnMouseOver Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnMouseOver Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnMouseOver(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnMouseOver Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnMouseOver Event of the Img Tag.
  * @return this
  */
  public IImg appendOnMouseOver(String paramVal);
  
  /**
  * This method sets the OnKeyDown Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyDown Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnKeyDown(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyDown Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyDown Event of the Img Tag.
  * @return this
  */
  public IImg appendOnKeyDown(String paramVal);
  
  /**
  * This method sets the OnKeyPress Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyPress Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnKeyPress(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyPress Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyPress Event of the Img Tag.
  * @return this
  */
  public IImg appendOnKeyPress(String paramVal);
  
  /**
  * This method sets the OnKeyUp Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnKeyUp Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnKeyUp(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnKeyUp Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnKeyUp Event of the Img Tag.
  * @return this
  */
  public IImg appendOnKeyUp(String paramVal);
  
  /**
  * This method sets the OnSelect Event of the Img to the passed in
  * paramVal.  The method over-writes any previous values set for this
  * Event.
  * @param paramVal The value to be set for the OnSelect Event of the Img
  * Tag.
  * @return this
  */
  public IImg setOnSelect(String paramVal);
  
  /**
  * This method appends the passed in paramVal to the value already
  * set for the OnSelect Event of the Img Tag. If no value has been set, 
  * the passed in paramVal will be set.
  * @param paramVal The value to be appended to the value already set
  * for the OnSelect Event of the Img Tag.
  * @return this
  */
  public IImg appendOnSelect(String paramVal);
}//end IImg interface def