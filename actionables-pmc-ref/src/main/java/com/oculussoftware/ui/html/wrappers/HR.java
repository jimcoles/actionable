package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    HR.java
* Date:        02.22.00
* Description: Represents a generic Horizontal rule element: <HR>
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/
public class HR extends GenericElement implements IHR
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
  *                                             interface, JSEventKind
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public HR()
  {
    _elementType = "HR";
    _hasElements = false;
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
  
  public IHR setAlign(AlignKind ak)    { setParam("ALIGN", ak.getStringValue()); return this; }
  public IHR setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IHR setID(String paramVal)    { setParam("ID",paramVal); return this; }
  public IHR setNoShade()              { setParam("NOSHADE", ""); return this; }
  public IHR setSize(String paramVal)  { setParam("SIZE", paramVal); return this; }
  public IHR setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IHR setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public IHR setWidth(String paramVal) { setParam("WIDTH", paramVal); return this; }
  
  public IHR setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IHR appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IHR appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public IHR setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IHR appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public IHR setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IHR appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IHR appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IHR appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IHR appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IHR appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IHR appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IHR setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IHR appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end Hr class def