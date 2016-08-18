package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.html.*;
import java.lang.*;
import java.util.*;


/**
* Filename:    Form.java
* Date:        02.22.00
* Description: Represents a generic Form element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Form extends GenericElement implements IForm
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.23.00    Added mutator methods that
  *                                              add new elements
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Form()
  {
    _elementType = "FORM";
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
  
  public ICenter addCenter()
  {
    ICenter center = new Center();
    addElement(center);
    return center;
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
  
  public IBR addBR()
  {
      IBR br = new BR();
      addElement(br);
      return br;    
  }
  
  /** Adds new Input element, returns a handle */
  public IInput addInput()
  {
      IInput i = new Input();
      addElement(i);
      return i;    
  }
  
  /** Adds new Input element with params, returns a handle */
  public IInput addInput(InputKind ik, String name, String value)
  {
      return addInput().setType(ik).setName(name).setValue(value);    
  }  
  
  /** Adds new Select element, returns a handle */
  public ISelect addSelect()
  {
      ISelect s = new Select();
      addElement(s);
      return s;    
  }
  
  public ITable addTable()
  {
    ITable t = new Table();
    addElement(t);
    return t;
  }//
  
  public IAttributeTable addAttributeTable()
  {
    IAttributeTable t = new AttributeTable();
    addElement(t);
    return t;
  }//
  
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
  

  /** Adds new TextArea element, returns a handle */
  public ITextArea addTextArea()
  {
      ITextArea ta = new TextArea();
      addElement(ta);
      return ta;    
  }  
  
  /** Adds new TextArea element with a string value, returns a handle */
  public ITextArea addTextArea(String name, String stringValue)
  {
      return addTextArea().setName(name).setStringValue(stringValue);    
  }        
    
  public IForm setAcceptcharset(String paramVal) { setParam("ACCEPT-CHARSET",paramVal); return this; }
  public IForm setAction(String paramVal) { setParam("ACTION",paramVal); return this; }
  public IForm setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IForm setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public IForm setEncType(String paramVal) { setParam("ENCTYPE",paramVal); return this; }
  public IForm setID(String paramVal) { setParam("ID",paramVal); return this; }
  public IForm setLang(LangKind lk) { setParam("LANG",lk.getStringValue()); return this; }
  public IForm setMethod(MethodKind m) { setParam("METHOD",m.getStringValue()); return this; }
  public IForm setName(String paramVal) { setParam("NAME",paramVal); return this; }
  public IForm setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IForm setTarget(String paramVal) { setParam("TARGET",paramVal); return this; }
  public IForm setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  
  public IForm setOnReset(String paramVal) 
  { 
    setParam(JSEventKind.ONRESET.getStringValue(),paramVal); 
    return this; 
  }
  
  public IForm appendOnReset(String paramVal)
  {
    appendParam(JSEventKind.ONRESET.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnSubmit(String paramVal) 
  { 
    setParam(JSEventKind.ONSUBMIT.getStringValue(),paramVal); 
    return this; 
  }
  
  public IForm appendOnSubmit(String paramVal)
  {
    appendParam(JSEventKind.ONSUBMIT.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IForm appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IForm appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public IForm setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public IForm setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IForm appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IForm appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IForm appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IForm setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IForm appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end Form class def