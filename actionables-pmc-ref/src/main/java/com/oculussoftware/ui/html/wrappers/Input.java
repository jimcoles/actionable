package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    Input.java
* Date:        02.22.00
* Description: Represents a generic Input element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Input extends GenericElement implements IInput
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
  *                                             interface, DirKind, LangKind
	* BUG00930				Saleem Shafi		6/9/00			Added exclusive logic to trapping [Enter] key   
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  
  protected String alias = null;
  
  public Input()
  {
    _elementType = "INPUT";
    _hasElements = false;
    _isClosable = false;
    setSize(50);
	setOnKeyDown("if (this.type != 'submit' && this.type != 'reset' && this.type != 'button') return killEnter();");
  }
  
  public void init()
  {
    if(getBrowserKind().equals(BrowserKind.IE))
      setSize(60);
    else if(getBrowserKind().equals(BrowserKind.NETSCAPE))
      setSize(30);
    else 
      setSize(50);  
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
    
  public IInput setAlias(String alias)
  {
    this.alias = alias;
		getHTMLObject().getHead().addAliasScript().alias("document.forms[0]."+this.getParam("NAME"),alias);
    return this;
  }
  
  public String getAlias()
  {
    return this.alias;
  }


  public IInput setAccept(String paramVal) { setParam("ACCEPT", paramVal); return this; }
  public IInput setAccessKey(String paramVal) { setParam("ACCESSKEY", paramVal); return this; }
  public IInput setAlign(String paramVal) { setParam("ALIGN", paramVal); return this; }
  public IInput setAlt(String paramVal) { setParam("ALT", paramVal); return this; }
  public IInput setChecked() { setParam("CHECKED", ""); return this; }
  public IInput setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IInput setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public IInput setDisabled() { setParam("DISABLED",""); return this; }
  public IInput setID(String paramVal) { setParam("ID",paramVal); return this; }
  public IInput setMaxLength(int max) { setParam("MAXLENGTH", ""+max); return this; }
  public IInput setName(String paramVal) { setParam("NAME",paramVal); return this; }
  public IInput setReadOnly() { setParam("READONLY",""); return this; }
  public IInput setSize(int size) { setSize(""+size); return this; }
  public IInput setSize(String paramVal) { setParam("SIZE", paramVal); return this; }
  public IInput setSrc(String paramVal) { setParam("SRC", paramVal); return this; }
  public IInput setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IInput setTabIndex(int ti) { setParam("TABINDEX",""+ti); return this; }
  public IInput setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public IInput setType(InputKind i) { setParam("TYPE", i.getStringValue()); return this; }
  public IInput setUseMap(String paramVal) { setParam("USEMAP", paramVal); return this; }
  public IInput setValue(String paramVal) { setParam("VALUE", paramVal); return this; }
  
  public IInput setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnChange(String paramVal)
  {
    setParam(JSEventKind.ONCHANGE.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnChange(String paramVal)
  {
    appendParam(JSEventKind.ONCHANGE.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IInput appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public IInput setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public IInput setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public IInput setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IInput appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IInput setOnSelect(String paramVal)
  {
    setParam(JSEventKind.ONSELECT.getStringValue(),paramVal); 
    return this;
  }
  
  public IInput appendOnSelect(String paramVal)
  {
    appendParam(JSEventKind.ONSELECT.getStringValue(), paramVal); 
    return this;
  }

  public IInput setValidation(ValidationKind type)
  {
    String alias = getAlias();
    if (alias == null)
      alias = this.getParam("NAME");
   	return setValidation(type, alias);
  }

  public IInput setValidation(ValidationKind type, String name)
  {
    getHTMLObject().getHead().getValidationScript().registerInput(this.getParam("NAME"),type,name);
    return this;
  }
	
  public IInput excludeSpellCheck()
  {
  	getHTMLObject().getHead().addSpellCheckExcludeScript().exclude("document.forms[0]."+this.getParam("NAME"));
  	return this;
  }
	
}//end Input class def