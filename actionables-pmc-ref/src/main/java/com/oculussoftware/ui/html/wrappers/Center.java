package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.OculusException;
import java.lang.*;
import java.util.*;

/**
* Filename:    Center.java
* Date:        02.23.00
* Description: Represents a generic Center tag element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/
public class Center extends GenericElement implements ICenter
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
  public Center()
  {
    _hasStringValue = true;
    _elementType = "CENTER";
  }
  
  //-------------------------------- Mutator Methods ---------------------------  
  
  /** Adds new Anchor element, returns a handle */
  public IAnchor addAnchor()
  {
      IAnchor a = new Anchor();
      addElement(a);
      return a;    
  }
  
  /** Adds new Anchor element w/ string value, returns a handle */
  public IAnchor addAnchor(String sval)
  {
      return addAnchor().setStringValue(sval);    
  }
  
  /** Adds new BR element, returns a handle */
  public IBR addBR()
  {
      IBR b = new BR();
      addElement(b);
      return b;    
  }  
  
  
  /** Adds new Form element, returns a handle */
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
  
  /** Adds new HR element, returns a handle */
  public IHR addHR()
  {
      IHR h = new HR();
      addElement(h);
      return h;    
  }
  
  /** Adds new Header element with headerSize, returns a handle */
  public IHeader addHeader(int headerSize) throws OculusException
  {
    try 
    {
      IHeader h = new Header(headerSize);
      addElement(h);
      return h;    
    } catch (OculusException oe) {throw oe;}
  }    

  /** Adds new Header element with headerSize & sval, returns a handle */
  public IHeader addHeader(int headerSize, String sval) throws OculusException
  {
    return addHeader(headerSize).setStringValue(sval);   
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
  
  /** Adds new Table element, returns a handle */
  public ITable addTable()
  {
      ITable t = new Table();
      addElement(t);
      return t;    
  }
  
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
  
  /** Sets the string bounded by the Center tag */
  public ICenter setStringValue(String value) { _stringvalue = new StringBuffer(""+value); return this; }

  public ICenter setClass(String paramVal) { setParam("CLASS", paramVal); return this; }
  public ICenter setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public ICenter setID(String paramVal) { setParam("ID", paramVal); return this; }
  public ICenter setLang(LangKind lk) { setParam("LANG", lk.getStringValue()); return this; }
  public ICenter setStyle(String paramVal) { setParam("STYLE", paramVal); return this; }
  public ICenter setTitle(String paramVal) { setParam("TITLE", paramVal); return this; }

  public ICenter setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ICenter appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ICenter appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ICenter appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ICenter appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ICenter appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ICenter setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ICenter appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end Center class def