package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Select.java
* Date:        02.22.00
* Description: Represents a generic Select element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Select extends GenericElement implements ISelect
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.23.00    Added addOption()
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
  *                                             interface, DirKind, JSEVentKind   
  * BUG00822				Saleem Shafi		06/05/00		Added concept of width (default to 15)
  */
	
	private int _width = 15;
	
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Select()
  {
    _elementType = "SELECT";

	  setOnKeyDown("return killEnter();");
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
    
  /** Adds new Option element, returns a handle */
  public IOption addOption()
  {
      IOption o = new Option();
      addElement(o);
      return o;    
  }//    
  
  /** Adds new Option element with value and content, returns a handle */
  public IOption addOption(String stringvalue, String value)
  {
      return addOption().setStringValue(stringvalue).setValue(value);    
  }//    
  
	public ISelect setWidth(int width) { _width = width; return this; }
  public ISelect setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public ISelect setDir(DirKind dir) { setParam("DIR", dir.getStringValue()); return this; }
  public ISelect setDisabled() { setParam("DISABLED",""); return this; }
  public ISelect setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ISelect setMultiple() { setParam("MULTIPLE", ""); return this; }
  public ISelect setName(String paramVal) { setParam("NAME",paramVal); return this; }
  public ISelect setSize(int size) { setSize(""+size); return this; }
  public ISelect setSize(String paramVal) { setParam("SIZE", paramVal); return this; }
  public ISelect setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ISelect setTabIndex(int index) { setParam("TABINDEX",""+index); return this; }
  public ISelect setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  
  public ISelect setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnChange(String paramVal)
  {
    setParam(JSEventKind.ONCHANGE.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnChange(String paramVal)
  {
    appendParam(JSEventKind.ONCHANGE.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ISelect appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ISelect setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ISelect setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ISelect setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ISelect appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ISelect setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ISelect appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }

  public ISelect setValidation(ValidationKind type)
  {
  return setValidation(type, this.getParam("NAME"));
  }

  public ISelect setValidation(ValidationKind type, String name)
  {
    getHTMLObject().getHead().getValidationScript().registerInput(this.getParam("NAME"),type,name);
    return this;
  }

	public String toString()
	{
		String blank = "&nbsp;";
		for (int i = 0; i < _width; i++)
			blank += "&nbsp;";
		addOption().setValue("-1").setStringValue(blank);
		return super.toString();
	}

}//end Select class def