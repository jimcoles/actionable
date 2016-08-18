package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.ui.html.*;

import java.lang.*;
import java.util.*;
import java.sql.Timestamp;

/**
* Filename:    TableData.java
* Date:        02.22.00
* Description: Represents a generic TableData element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class TableData extends GenericElement implements ITableData
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
  public TableData()
  {
    _hasStringValue = true;
    _elementType = "TD";
    setVAlign(VAlignKind.TOP);
  }
  
  
  //-------------------------------- Mutator Methods --------------------------- 
  
  public ITableData addCalendar()
  {
    return addCalendar((String)null);
  }
  
  public ITableData addCalendar(Timestamp date)
  {
    return addCalendar(null,date);
  }
  
  public ITableData addCalendar(String input)
  {
    Timestamp date = new Timestamp(System.currentTimeMillis());
    return this.addCalendar(input, date);
  }
  
  public ITableData addCalendar(String input, Timestamp date)
  {
    if (date == null)
      return addCalendar(input);
    
    getHTMLObject().getHead().addCalendarScript();  
    Calendar temp = Calendar.getInstance();
    temp.setTime(date);
    int month = (temp.get(Calendar.MONTH)+1);
    int year = temp.get(Calendar.YEAR);
    int day = temp.get(Calendar.DATE);


    IAnchor anchor = this.addAnchor();
    anchor.setHRef("javascript:openCalendar("+input+","+day+","+month+","+year+");");
    anchor.addImg().setSrc("/system/OculusImages/common/imgCal.gif");
    return this;
  }
  
  public IGraph addGraph(IReportFormatter rf, com.oculussoftware.api.busi.common.reports.IReportDataSet rds)
  {
    IGraph g = new com.oculussoftware.ui.html.Graph(rf,rds);
    addElement(g);
    return g; 
  }
  
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid) throws com.oculussoftware.api.sysi.OculusException
  {
    return addDisplayAttrTable(context,classid,null);
  }
  
  public IDisplayAttrTable addDisplayAttrTable(com.oculussoftware.api.sysi.IObjectContext context, long classid, com.oculussoftware.api.busi.common.reports.IBasicReport report)
     throws com.oculussoftware.api.sysi.OculusException
  {
    IDisplayAttrTable t = new DisplayAttrTable(context,classid,report);
    addElement(t);
    t.buildTable();
    return t;
  }
  
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
  
  public IBR addBR()
  {
      IBR b = new BR();
      addElement(b);
      return b;    
  }
  
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
  
  public IImg addImg()
  {
    IImg img = new Img();
    addElement(img);
    return img;
  }//

  public IHeader addHeader(int type)
    throws OculusException
  {
    IHeader header = new Header(type);
    addElement(header);
    return header;
  }
  
  public IHR addHR()
  {
      IHR h = new HR();
      addElement(h);
      return h;    
  }
  
  public IImg addImg(int width, int height, String src, String alt)
  {
    return addImg().setWidth(width).setHeight(height).setSrc(src).setAlt(alt);
  }//
  
  public IInput addInput()
  {
    IInput input = new Input();
    addElement(input);
    return input;
  }//
  
  public IInput addInput(InputKind i, String value)//Buttons
  {
    return addInput().setType(i).setValue(value);
  }//
  
  public IInput addInput(InputKind i, String name, String value)
  {
    return addInput(i,value).setName(name);
  }//
  
  public ISelect addSelect()
  {
    ISelect select = new Select();
    addElement(select);
    return select;
  }//
  
  public ISelect addSelect(String name)
  {
    return addSelect().setName(name);
  }//
  
  public ISelect addSelect(String name, int size)
  {
    return addSelect(name).setSize(size);
  }//
  
  public ITable addTable()
  {
    ITable table = new Table();
    addElement(table);
    return table;
  }//
  
  public ITextArea addTextArea()
  {
    ITextArea textarea = new TextArea();
    addElement(textarea);
    return textarea;
  }//
  
  public ITextArea addTextArea(String name)
  {
    return addTextArea().setName(name);
  }//
    
  public ITextArea addTextArea(String name, String sval)
  {
    return addTextArea(name).setStringValue(sval);
  }//
  
  public ITableData setAbbr(String paramVal) { setParam("ABBR",paramVal); return this; }
  public ITableData setAlign(AlignKind a) { setParam("ALIGN",a.getStringValue()); return this; }
  public ITableData setAxis(String paramVal) { setParam("AXIS",paramVal); return this; }
  public ITableData setBGColor(String paramVal) { setParam("BGCOLOR",paramVal); return this; }
  public ITableData setChar(String paramVal) { setParam("CHAR",paramVal); return this; }
  public ITableData setCharOff(int offset) { setParam("CHAROFF",""+offset); return this; }
  public ITableData setClass(ClassKind ck) { setParam("CLASS",ck.getStringValue()); return this; }
  public ITableData setColSpan(int span) { setParam("COLSPAN",""+span); return this; }
  public ITableData setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public ITableData setHeaders(String paramVal) { setParam("HEADERS",paramVal); return this; }
  public ITableData setHeight(int h) { setParam("HEIGHT",""+h); return this; }
  public ITableData setHeightRatio(int h) { setParam("HEIGHT",h+"%"); return this; }
  public ITableData setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ITableData setLang(LangKind lk) { setParam("LANG",lk.getStringValue()); return this; }
  public ITableData setNowrap() { setParam("NOWRAP", ""); return this; }
  public ITableData setRowSpan(int span) { setParam("ROWSPAN",""+span); return this; }
  public ITableData setScope(ScopeKind sk) { setParam("SCOPE",sk.getStringValue()); return this; }
  public ITableData setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ITableData setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public ITableData setVAlign(VAlignKind vak) { setParam("VALIGN",vak.getStringValue()); return this; }
  public ITableData setWidthFixed(int wf) { setParam("WIDTH",""+wf); return this; }
  public ITableData setWidthRatio(int wr) { setParam("WIDTH",wr+"%"); return this; }

  
  public ITableData setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableData appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ITableData appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ITableData setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ITableData setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ITableData setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableData appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableData appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ITableData appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ITableData setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITableData appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end TableData class def