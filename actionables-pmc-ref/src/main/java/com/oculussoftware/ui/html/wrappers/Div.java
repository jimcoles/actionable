package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Div.java
* Date:        02.22.00
* Description: Represents a generic Div element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*
*/

public class Div extends GenericElement implements IDiv
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Div()
  {
    _elementType = "DIV";
    setVPIgnore(true);
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
    IImg i = new Img();
    addElement(i);
    return i;
  }//
  
  public IImg addImg(int width, int height, String src, String alt)
  {
    return addImg().setWidth(width).setHeight(height).setSrc(src).setAlt(alt);
  }//
  
  public IInput addInput()
  {
    IInput i = new Input();
    addElement(i);
    return i;
  }//
  
  public IInput addInput(InputKind i, String value)
  {
    return addInput().setType(i).setValue(value);
  }//
  
  public IInput addInput(InputKind i, String name, String value)
  {
    return addInput(i,value).setName(name);
  }//
  
  public ISelect addSelect()
  {
    ISelect s = new Select();
    addElement(s);
    return s; 
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
    ITable t = new Table();
    addElement(t);
    return t;
  }//
  
  public ITextArea addTextArea()
  {
    ITextArea t = new TextArea();
    addElement(t);
    return t; 
  }//
  
  public ITextArea addTextArea(String name)
  {
    return addTextArea().setName(name);
  }//
  
  public ITextArea addTextArea(String name, String stringvalue)
  {
    return addTextArea(name).setStringValue(stringvalue);
  }//
    
  
  public IDiv setAlign(AlignKind a) {setParam("ALIGN",a.getStringValue()); return this;}
  public IDiv setClass(String paramVal) {setParam("CLASS",paramVal); return this;}
  public IDiv setDataSrc(String paramVal) {setParam("DATASRC",paramVal); return this;}
  public IDiv setDir(DirKind d) {setParam("DIR",d.getStringValue()); return this;}
  public IDiv setID(String paramVal) {setParam("ID",paramVal); return this;}
  public IDiv setName(String paramVal) {setParam("NAME",paramVal); return this;}
  public IDiv setLang(LangKind l) {setParam("LANG",l.getStringValue()); return this;}
  public IDiv setStyle(String paramVal) {setParam("STYLE",paramVal); return this;}
  public IDiv setTitle(String paramVal) {setParam("TITLE",paramVal); return this;}
  public IDiv setVPIgnore(boolean val) {setParam("VPIGNORE",(val?"TRUE":"FALSE")); return this;}

  public IDiv setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IDiv appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IDiv appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IDiv appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IDiv appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IDiv appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IDiv setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }

  public IDiv setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal);
    return this;
  }
  
  public IDiv appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
}//end Div class def