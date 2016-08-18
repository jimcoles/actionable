package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Table.java
* Date:        02.22.00
* Description: Represents a generic Table element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Table extends GenericElement implements ITable
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
  public Table()
  {
    _elementType = "TABLE";
    setParam("WIDTH","*");//default the width to *
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
    
  public ICaption addCaption()
  {
    ICaption c = new Caption();
    addElement(c);
    return c;
  }//
  
  public ICaption addCaption(String sval)
  {
    return addCaption().setStringValue(sval);
  }//
  
  public IForm addForm()
  {
      IForm f = new Form();
      addElement(f);
      return f;    
  }
  
  public IForm addForm(String name, MethodKind method, String action)
  {
    return addForm().setName(name).setMethod(method).setAction(action);
  }
  
  public ITableRow addTableRow()
  {
    ITableRow tr = new TableRow();
    addElement(tr);
    return tr;
  }
  
  public ITableHeader addTableHeader()
  {
    ITableHeader th = new TableHeader();
    addElement(th);
    return th;
  }
  
  public ITable setAlign(AlignKind a) { setParam("ALIGN",a.getStringValue()); return this; }
  public ITable setBGColor(String paramVal) { setParam("BGCOLOR",paramVal); return this; }
  public ITable setBorder(int b) { setParam("BORDER",""+b); return this; }
  public ITable setCellPadding(int cp) { setParam("CELLPADDING",""+cp); return this; }
  public ITable setCellSpacing(int cs) { setParam("CELLSPACING",""+cs); return this; }
  public ITable setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public ITable setDataPageSize(int dps) { setParam("DATAPAGESIZE",""+dps); return this; }
  public ITable setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public ITable setFrame(FrameKind fk) { setParam("FRAME",fk.getStringValue()); return this; }
  public ITable setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ITable setLang(LangKind lk) { setParam("LANG",lk.getStringValue()); return this; }
  public ITable setRules(RulesKind rk) { setParam("RULES",rk.getStringValue()); return this; }
  public ITable setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ITable setSummary(String paramVal) { setParam("SUMMARY",paramVal); return this; }
  public ITable setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public ITable setHeightFixed(int wf) { setParam("HEIGHT",""+wf); return this; }
  public ITable setHeightRatio(int wr) { setParam("HEIGHT",""+wr+"%"); return this; }
  public ITable setWidthFixed(int wf) { setParam("WIDTH",""+wf); return this; }
  public ITable setWidthRatio(int wr) { setParam("WIDTH",""+wr+"%"); return this; }

  
  public ITable setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ITable appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ITable appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ITable setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ITable setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ITable setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITable appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITable appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ITable appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ITable setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITable appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end Table class def