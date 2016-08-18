package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    TableRow.java
* Date:        02.22.00
* Description: Represents a generic TableRow element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class TableRow extends GenericElement implements ITableRow
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public TableRow()
  {
    _elementType = "TR";
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
  
  public ITableData addTableData()
  {
    ITableData td = new TableData();
    addElement(td);
    return td;
  }//
  
  public ITableData addTableData(String sval)
  {
  	ITableData td;
  	td = addTableData();
    td.addAnchor().setStringValue(sval);
    return td;
  }//
  
  public ITableHeader addTableHeader()
  {
    ITableHeader th = new TableHeader();
    addElement(th);
    return th;
  }//
  
  public ITableHeader addTableHeader(String sval)
  {
    ITableHeader th = addTableHeader();
    th.addAnchor(sval);
    return th;
  }//
  
  public ITableRow setAlign(AlignKind a) { setParam("ALIGN",a.getStringValue()); return this; }
  public ITableRow setBGColor(String paramVal) { setParam("BGCOLOR",paramVal); return this; }
  public ITableRow setChar(char c) { setParam("CHAR",""+c); return this; }
  public ITableRow setCharOff(int offset) { setParam("CHAROFF",""+offset); return this; }
  public ITableRow setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public ITableRow setDir(DirKind d) { setParam("DIR", d.getStringValue()); return this; }
  public ITableRow setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ITableRow setLang(LangKind l) { setParam("LANG",l.getStringValue()); return this; }
  public ITableRow setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ITableRow setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public ITableRow setVAlign(VAlignKind v) { setParam("VALIGN",v.getStringValue()); return this; }
  
  public ITableRow setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableRow appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ITableRow appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ITableRow setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ITableRow setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ITableRow setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableRow appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableRow appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableRow appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableRow setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableRow appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end TableRow class def