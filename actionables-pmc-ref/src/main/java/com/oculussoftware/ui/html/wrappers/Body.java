package com.oculussoftware.ui.html.wrappers;

import java.lang.*;
import java.util.*;

import com.oculussoftware.ui.html.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    Body.java
* Date:        02.22.00
* Description: Represents a generic Body element
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
* ----            Jachin Cheng    02.23.00    Added mutator methods that add 
*                                              new elements
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*/

public class Body extends GenericElement implements IBody
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Body()
  {
    //set default attributes here
    _elementType = "BODY";
	   appendOnLoad("if (window.firstElementFocus) firstElementFocus(); ");
		 setBGColor("WHITE");
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
  
  /** Adds new Script element, returns a handle */       
  public IScript addScript()
  {
      IScript s = new Script();
      addElement(s);
      return s;
  }
  
  /** Adds new Script element, returns a handle */       
  public IScript addScript(String script)
  {
      return addScript().setStringValue(script);
  }
  
  /** Adds new NoScript element, returns a handle */       
  public INoScript addNoScript()
  {
      INoScript s = new NoScript();
      addElement(s);
      return s;
  }
  
  /** Adds new NoFrames element, returns a handle */       
  public INoFrames addNoFrames()
  {
      INoFrames s = new NoFrames();
      addElement(s);
      return s;
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
  public IBody setALink(String paramVal) {setParam("ALINK", paramVal); return this;}
  public IBody setBackGround(String paramVal) {setParam("BACKGROUND", paramVal); return this;}
  public IBody setBGColor(String paramVal) {setParam("BGCOLOR", paramVal); return this;}
  public IBody setClass(String paramVal) {setParam("CLASS", paramVal); return this;}
  public IBody setDir(DirKind dk) {setParam("DIR", dk.getStringValue()); return this;}
  public IBody setID(String paramVal) {setParam("ID", paramVal); return this;} 
  public IBody setLink(String paramVal) {setParam("LINK", paramVal); return this;} 
  public IBody setStyle(String paramVal) {setParam("STYLE", paramVal); return this;} 
  public IBody setText(String paramVal) {setParam("TEXT", paramVal); return this;} 
  public IBody setVLink(String paramVal) {setParam("VLINK", paramVal); return this;} 
  public IBody setOnLoad(String paramVal) {setParam("ONLOAD", paramVal); return this;} 
  public IBody setOnUnload(String paramVal) {setParam("ONUNLOAD", paramVal); return this;} 
  public IBody setMarginWidth(int paramVal) {setParam("MARGINWIDTH", ""+paramVal); return this;}
	public IBody setMarginHeight(int paramVal) {setParam("MARGINHEIGHT", ""+paramVal); return this;}
  public IBody setLeftMargin(int paramVal) {setParam("LEFTMARGIN", ""+paramVal); return this;}
	public IBody setTopMargin(int paramVal) {setParam("TOPMARGIN", ""+paramVal); return this;}

  public IBody appendOnLoad(String paramVal)
  {
    appendParam(JSEventKind.ONLOAD.getStringValue(), paramVal);
    return this;
  }  
  
  public IBody appendOnUnload(String paramVal)
  {
    appendParam(JSEventKind.ONUNLOAD.getStringValue(), paramVal);
    return this;
  }         
  
  public IBody setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IBody appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IBody appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IBody appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IBody appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IBody appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IBody setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
	
	public IBody setOnResize(String paramVal)
  {
    setParam(JSEventKind.ONRESIZE.getStringValue(),paramVal);
    return this;
  }
  
  public IBody appendOnResize(String paramVal)
  {
    appendParam(JSEventKind.ONRESIZE.getStringValue(), paramVal); 
    return this;
  }
}//end Body class def