package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    TableHeader.java
* Date:        02.22.00
* Description: Represents a generic TableData element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class TableHeader extends GenericElement implements ITableHeader
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
  public TableHeader()
  {
    _hasStringValue = true;
    _elementType = "TH";
    setParam("WIDTH","*");//default the width to *
  }
  
  //-------------------------------- Mutator Methods ---------------------------  

  public IAnchor addAnchor()
  {
    IAnchor a = new Anchor();
    addElement(a);
    return a;
  }//
  
  public IAnchor addAnchor(String sval)
  {
    return addAnchor().setStringValue(sval);
  }//
  
  public IAnchor addAnchor(String sval, String href)
  {
    return addAnchor(sval).setHRef(href);
  }//

  public IHeader addHeader(int type)
    throws OculusException
  {
    IHeader header = new Header(type);
    addElement(header);
    return header;
  }  

  public IImg addImg()
  {
    IImg img = new Img();
    addElement(img);
    return img;
  }//
  
  public IImg addImg(int width, int height, String src, String alt)
  {
    return addImg().setWidth(width).setHeight(height).setSrc(src).setAlt(alt);
  }//
      
  public ITableHeader setAbbr(String paramVal) { setParam("ABBR",paramVal); return this; }
  public ITableHeader setAlign(AlignKind a) { setParam("ALIGN",a.getStringValue()); return this; }
  public ITableHeader setAxis(String paramVal) { setParam("AXIS",paramVal); return this; }
  public ITableHeader setBGColor(String paramVal) { setParam("BGCOLOR",paramVal); return this; }
  public ITableHeader setChar(char c) { setParam("CHAR",""+c); return this; }
  public ITableHeader setCharOff(int offset) { setParam("CHAROFF",""+offset); return this; }
  public ITableHeader setClass(ClassKind ck) { setParam("CLASS",ck.getStringValue()); return this; }
  public ITableHeader setColSpan(int span) { setParam("COLSPAN",""+span); return this; }
  public ITableHeader setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public ITableHeader setHeaders(String paramVal) { setParam("HEADERS",paramVal); return this; }
  public ITableHeader setHeight(int h) { setParam("HEIGHT",""+h); return this; }
  public ITableHeader setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ITableHeader setLang(LangKind lk) { setParam("LANG",lk.getStringValue()); return this; }
  public ITableHeader setNowrap() { setParam("NOWRAP", ""); return this; }
  public ITableHeader setRowSpan(int span) { setParam("ROWSPAN",""+span); return this; }
  public ITableHeader setScope(ScopeKind sk) { setParam("SCOPE",sk.getStringValue()); return this; }
  public ITableHeader setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ITableHeader setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public ITableHeader setVAlign(VAlignKind v) { setParam("VALIGN",v.getStringValue()); return this; }
  public ITableHeader setWidthFixed(int w) { setParam("WIDTH",""+w); return this; }
  public ITableHeader setWidthRatio(int w) { setParam("WIDTH",""+w+"%"); return this; }
  
  public ITableHeader setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableHeader appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ITableHeader appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ITableHeader setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ITableHeader setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ITableHeader setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableHeader appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableHeader appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableHeader appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableHeader setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableHeader appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end TableHeader class def