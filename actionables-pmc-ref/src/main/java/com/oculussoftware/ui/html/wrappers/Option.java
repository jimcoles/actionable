package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Option.java
* Date:        02.22.00
* Description: Represents a generic Option element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Option extends GenericElement implements IOption
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
  *                                             interface, JSEventKind, DirKind
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Option()
  {
    _hasStringValue = true;
    _elementType = "OPTION";
    _hasElements = false;
  }

  //-------------------------------- Mutator Methods ---------------------------    
    
  public IOption setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IOption setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public IOption setDisabled() { setParam("DISABLED",""); return this; }
  public IOption setLabel(String paramVal) { setParam("LABEL", paramVal); return this; }
  public IOption setSelected() { setParam("SELECTED", ""); return this; }
  public IOption setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IOption setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public IOption setValue(String paramVal) { setParam("VALUE", paramVal); return this; }
  public IOption setStringValue(String val) { _stringvalue = new StringBuffer(""+val); return this; }
  
  public IOption setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IOption appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IOption appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public IOption setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IOption appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public IOption setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IOption appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IOption appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IOption appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IOption appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IOption appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IOption appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IOption setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IOption appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
}//end Option class def