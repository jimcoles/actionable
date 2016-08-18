package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Link.java
* Date:        02.22.00
* Description: Represents a generic Link element
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
*/

public class Link extends GenericElement implements ILink
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Link()
  {
    _elementType = "LINK";
  }
  
  //-------------------------------- Mutator Methods ---------------------------     
  
  
  public ILink setCharSet(String paramVal) {setParam("CHARSET",paramVal); return this;}
  public ILink setClass(String paramVal) {setParam("CLASS",paramVal); return this;}
  public ILink setDir(DirKind d) {setParam("DIR",d.getStringValue()); return this;}
  public ILink setHRef(String paramVal)
  {
  	if (true)
  	{
  		getHTMLObject().getHead().addStyle().setStringValue(ScriptMgr.getInstance().getScript(paramVal));
  	}
  	else
  		setParam("HREF",paramVal);
  	return this;
  }


  public ILink setHRefLang(LangKind l) {setParam("HREFLANG",l.getStringValue()); return this;}
  public ILink setID(String paramVal) {setParam("ID",paramVal); return this;}
  public ILink setLang(LangKind l) {setParam("LANG",l.getStringValue()); return this;}
  public ILink setRel(String paramVal) {setParam("REL",paramVal); return this;}
  public ILink setRev(String paramVal) {setParam("REV",paramVal); return this;}
  public ILink setStyle(String paramVal) {setParam("STYLE",paramVal); return this;}
  public ILink setTarget(String paramVal) {setParam("TARGET",paramVal); return this;}
  public ILink setTitle(String paramVal) {setParam("TITLE",paramVal); return this;}
  public ILink setType(String paramVal) {setParam("TYPE",paramVal); return this;}
  
  
 
  
  public ILink setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ILink appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ILink appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ILink setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ILink setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ILink setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ILink appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ILink appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ILink appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ILink setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ILink appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
}//end Anchor class def