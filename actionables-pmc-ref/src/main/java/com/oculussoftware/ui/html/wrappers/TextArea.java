package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    TextArea.java
* Date:        02.22.00
* Description: Represents a generic TextArea element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class TextArea extends GenericElement implements ITextArea
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  *
  */
  //----------------------------- Public Constructors -------------------------
  
  protected String alias = null;
  
  /** Basic default constructor */
  public TextArea()
  {
    _hasStringValue = true;
    _elementType = "TEXTAREA";
    _hasElements = false;
    setParam("WRAP","SOFT");
    setRows(12);
  }
  
  public void init()
  {
    if(getBrowserKind().equals(BrowserKind.IE))
      setCols(89);
    else if (getBrowserKind().equals(BrowserKind.NETSCAPE))
	{
      setCols(60);
	  setStyle("font-family:Arial; color:black; font-size:9pt; vertical-align: top;");
	}
	else 
      setCols(60);  
  }
  
  public ITextArea setAlias(String alias)
  {
    this.alias = alias;
		getHTMLObject().getHead().addAliasScript().alias("document.forms[0]."+this.getParam("NAME"),alias);
    return this;
  }
  
  public String getAlias()
  {
    return this.alias;
  }
  
  
  //-------------------------------- Mutator Methods ---------------------------    
    
  public ITextArea setAccessKey(String paramVal) { setParam("ACCESSKEY", paramVal); return this; }
  public ITextArea setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public ITextArea setCols(int cols) { setParam("COLS", ""+cols); return this; }  
  public ITextArea setDir(DirKind direction) { setParam("DIR", direction.getStringValue()); return this; }
  public ITextArea setDisabled() { setParam("DISABLED",""); return this; }
  public ITextArea setID(String paramVal) { setParam("ID",paramVal); return this; }
  public ITextArea setName(String paramVal) { setParam("NAME",paramVal); return this; }
  public ITextArea setReadOnly() { setParam("READONLY",""); return this; }
  public ITextArea setRows(int rows) { setParam("ROWS", ""+rows); return this; }
  public ITextArea setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public ITextArea setTabIndex(int index) { setParam("TABINDEX",""+index); return this; }
  public ITextArea setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public ITextArea setStringValue(String sval) { _stringvalue = new StringBuffer(""+sval); return this; }
  
  public ITextArea setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnChange(String paramVal)
  {
    setParam(JSEventKind.ONCHANGE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnChange(String paramVal)
  {
    appendParam(JSEventKind.ONCHANGE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public ITextArea appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public ITextArea setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public ITextArea setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public ITextArea setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public ITextArea appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
  public ITextArea setOnSelect(String paramVal)
  {
    setParam(JSEventKind.ONSELECT.getStringValue(),paramVal); 
    return this;
  }
  
  public ITextArea appendOnSelect(String paramVal)
  {
    appendParam(JSEventKind.ONSELECT.getStringValue(), paramVal); 
    return this;
  }

  public ITextArea setValidation(ValidationKind type)
  {
    String alias = getAlias();
    if (alias == null)
      alias = this.getParam("NAME");
  return setValidation(type, alias);
  }

  public ITextArea setValidation(ValidationKind type, String name)
  {
    getHTMLObject().getHead().getValidationScript().registerInput(this.getParam("NAME"),type,name);
    return this;
  }
  
	public ITextArea excludeSpellCheck()
	{
		getHTMLObject().getHead().addSpellCheckExcludeScript().exclude("document.forms[0]."+this.getParam("NAME"));
		return this;
	}
  
  /** Returns entire HTML end tag */     
  public String getEndTag()
  {
    StringBuffer sbuff = new StringBuffer();
    sbuff.append("</"); 
    sbuff.append(_elementType); 
    sbuff.append(">"); 
    return sbuff.toString();
  }// end getEndTag()
  
  
}//end TextArea class def