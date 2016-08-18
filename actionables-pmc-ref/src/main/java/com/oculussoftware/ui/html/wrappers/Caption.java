package com.oculussoftware.ui.html.wrappers;


import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Caption.java
* Date:        02.22.00
* Description: Represents a generic Caption element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Caption extends GenericElement implements ICaption
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.23.00    Changed setValue() to 
  *                                             setStringValue()
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
  *                                             interface, DirKind, LangKind 
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Caption()
  {
    _hasStringValue = true;
    _elementType = "CAPTION";
    _hasElements = false;
  }
  
  //-------------------------------- Mutator Methods --------------------------- 
  
  /** Sets the string bounded by the Caption tag */
  public ICaption setStringValue(String value) { _stringvalue = new StringBuffer(""+value); return this; }     
    
  public ICaption setAlign(String paramVal) { setParam("ALIGN", paramVal); return this; }
  public ICaption setClass(String paramVal) { setParam("CLASS", paramVal); return this; }
  public ICaption setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public ICaption setID(String paramVal) { setParam("ID", paramVal); return this; }
  public ICaption setLang(LangKind lk) {setParam("LANG", lk.getStringValue()); return this;} 
  public ICaption setStyle(String paramVal) { setParam("STYLE", paramVal); return this; }
  public ICaption setTitle(String paramVal) { setParam("TITLE", paramVal); return this; }
  
  public ICaption setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ICaption appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ICaption appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ICaption appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ICaption appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ICaption appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ICaption setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ICaption appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end Caption class def