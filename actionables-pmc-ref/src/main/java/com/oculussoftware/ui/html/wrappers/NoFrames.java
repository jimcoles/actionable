package com.oculussoftware.ui.html.wrappers;

import java.lang.*;
import java.util.*;

import com.oculussoftware.ui.html.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    NoFrames.java
* Date:        09.05.00
* Description: Represents a generic NoFrames element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class NoFrames extends GenericElement implements INoFrames
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public NoFrames()
  {
    //set default attributes here
    _elementType = "NOFRAMES";
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
  
  /** Adds new HR element, returns a handle */
  public IHR addHR()
  {
      IHR h = new HR();
      addElement(h);
      return h;    
  } 
  
  /** Adds new Center element, returns a handle */
  public ICenter addCenter()
  {
      ICenter c = new Center();
      addElement(c);
      return c;    
  }  
  
  /** Adds new Center element with String value, returns a handle */
  public ICenter addCenter(String sval)
  {
      return addCenter().setStringValue(sval);    
  }   
  
  public IDiv addDiv()
  {
      IDiv d = new Div();
      addElement(d);
      return d;
  }  
    
  /** Adds new Table element, returns a handle */
  public ITable addTable()
  {
      ITable t = new Table();
      addElement(t);
      return t;    
  }        

  public IAttributeTable addAttributeTable()
  {
	  IAttributeTable t = new AttributeTable();
	  addElement(t);
	  return t;    
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
  
  /** Sets params */      
  public INoFrames setClass(String paramVal) {setParam("CLASS", paramVal); return this;}
  public INoFrames setDir(DirKind dk) {setParam("DIR", dk.getStringValue()); return this;}
  public INoFrames setID(String paramVal) {setParam("ID", paramVal); return this;} 
  public INoFrames setLang(String paramVal) {setParam("LANG", paramVal); return this;} 
  public INoFrames setStyle(String paramVal) {setParam("STYLE", paramVal); return this;} 
  public INoFrames setTitle(String paramVal) {setParam("TITLE", paramVal); return this;} 
  
  public INoFrames setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public INoFrames appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public INoFrames appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
    
  public INoFrames setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public INoFrames appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public INoFrames appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public INoFrames appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public INoFrames appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public INoFrames appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public INoFrames appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public INoFrames appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public INoFrames setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public INoFrames appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
}//end NoFrames class def