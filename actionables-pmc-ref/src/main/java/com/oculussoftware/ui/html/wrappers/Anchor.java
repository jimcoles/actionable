package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Anchor.java
* Date:        02.22.00
* Description: Represents a generic Anchor element
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
* ----            Jachin Cheng    02.23.00    Added setStringValue()
*                                             Removed JS event handlers
* ----            Jachin Cheng    02.24.00    Changed to implement interface
* ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
*                                             interface, JSEventKind
*/

public class Anchor extends GenericElement implements IAnchor
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Anchor()
  {
    _hasStringValue = true;
    _elementType = "A";
  }
  
  //-------------------------------- Mutator Methods ---------------------------     
  
  
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
    
  public IAnchor makeSelectable(int index)
  {
    appendOnClick("checkMods(event); selectImg("+index+");");
    appendOnMouseOver("rollOver("+index+");");
    appendOnMouseOut("rollOut("+index+");");
    return this;
  }
  
  /** Sets the string bounded by the anchor tag */
  public IAnchor setStringValue(String value) { _stringvalue = new StringBuffer(""+HtmlFormatter.toHTML(value)); return this;}

  /** Sets the parameter values */
  public IAnchor setAccessKey(String paramVal) {setParam("ACCESSKEY", paramVal); return this;}
  public IAnchor setClass(ClassKind ck)        {setParam("CLASS",ck.getStringValue()); return this;}
  public IAnchor setHRef(String paramVal)      {setParam("HREF",paramVal); return this;}
  public IAnchor setID(String paramVal)        {setParam("ID",paramVal); return this;}
  public IAnchor setName(String paramVal)      {setParam("NAME",paramVal); return this;}
  public IAnchor setStyle(String paramVal)     {setParam("STYLE",paramVal); return this;}
  public IAnchor setTabIndex(String paramVal)  {setParam("TABINDEX",paramVal); return this;}
  public IAnchor setTarget(String paramVal)    {setParam("TARGET",paramVal); return this;}
  public IAnchor setTitle(String paramVal)     {setParam("TITLE",paramVal); return this;}
  public IAnchor setType(String paramVal)      {setParam("TYPE",paramVal); return this;}
   
  public IAnchor setFont(String paramVal) 
  {
	  _stringvalue = new StringBuffer("<FONT FACE="+paramVal+">"+_stringvalue+"</FONT");
		return this;
  }//
	
	public IAnchor setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IAnchor appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IAnchor appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IAnchor setOnhelp(String paramVal)
  {
    setParam(JSEventKind.ONHELP.getStringValue(),paramVal); 
    return this;
  }
  
  public IAnchor appendOnhelp(String paramVal)
  {
    setParam(JSEventKind.ONHELP.getStringValue(), paramVal); 
    return this;
  }
}//end Anchor class def