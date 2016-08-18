package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Img.java
* Date:        02.22.00
* Description: Represents a generic Img element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Img extends GenericElement implements IImg
{
  protected int _index = -1;
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Img()
  {
    _elementType = "IMG";
    _hasElements = false;
    setBorder(0); //default the border to zero
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
   
  public IImg setAlign(AlignKind ak) { setParam("ALIGN", ak.getStringValue()); return this; }
  public IImg setAlt(String paramVal) { setParam("ALT", paramVal); return this; }
  public IImg setBorder(int b) { setParam("BORDER", ""+b); return this; }
  public IImg setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IImg setDir(DirKind dk) { setParam("DIR", dk.getStringValue()); return this; }
  public IImg setHeight(int h) { setParam("HEIGHT", ""+h); return this; }
  public IImg setHSpace(int hs) { setParam("HSPACE", ""+hs); return this; }
  public IImg setID(String paramVal) { setParam("ID",paramVal); return this; }
  public IImg setIsMap() { setParam("ISMAP", ""); return this; }
  public IImg setLongDesc(String paramVal) { setParam("LONGDESC", paramVal); return this; }
  public IImg setName(String paramVal) { setParam("NAME",paramVal); return this; }
  public IImg setSrc(String paramVal) { setParam("SRC", paramVal); return this; }
  public IImg setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IImg setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  public IImg setUseMap(String paramVal) { setParam("USEMAP", paramVal); return this; }
  public IImg setVSpace(int vs) { setParam("VSPACE", ""+vs); return this; }
  public IImg setWidth(int w) { setParam("WIDTH", ""+w); return this; }
  
  public int makeSelectable(String unselectName, String selectName, String rolloverName)
  {
    int selectableImgNumber = getHTMLObject().getHead().getImageRegisterScript().registerImage(this,unselectName,selectName,rolloverName);
    return makeSelectable(selectableImgNumber);
  }

  public int makeSelectable(int index)
  {
    ((IAnchor)getParentObject()).appendOnClick("checkMods(event); selectImg("+index+");");
    ((IAnchor)getParentObject()).appendOnMouseOver("rollOver("+index+");");
    ((IAnchor)getParentObject()).appendOnMouseOut("rollOut("+index+");");
    return index;
  }
  
  public int makeSelectable()
  {
    return makeSelectable(null,null,null);
  }
  
  public int getIndex() {  return _index; }
  public void setIndex(int index) { _index = index; }
  
  public IImg setOnBlur(String paramVal)
  {
    setParam(JSEventKind.ONBLUR.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnBlur(String paramVal)
  {
    appendParam(JSEventKind.ONBLUR.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnClick(String paramVal)
  {
    setParam(JSEventKind.ONCLICK.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnClick(String paramVal)
  {
    appendParam(JSEventKind.ONCLICK.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnDblClick(String paramVal)
  {
    setParam(JSEventKind.ONDBLCLICK.getStringValue(),paramVal); 
    return this; 
  }
  
  public IImg appendOnDblClick(String paramVal)
  {
    appendParam(JSEventKind.ONDBLCLICK.getStringValue(), paramVal);
    return this;
  }
  
  public IImg setOnFocus(String paramVal)
  {
    setParam(JSEventKind.ONFOCUS.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnFocus(String paramVal)
  {
    appendParam(JSEventKind.ONFOCUS.getStringValue(), paramVal);
    return this;
  }
  
  public IImg setOnMouseDown(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnMouseDown(String paramVal)
  {  
    appendParam(JSEventKind.ONMOUSEDOWN.getStringValue(), paramVal);
    return this;
  }
  
  public IImg setOnMouseUp(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEUP.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnMouseUp(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnMouseMove(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEMOVE.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnMouseMove(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEMOVE.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnMouseOut(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOUT.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnMouseOut(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOUT.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnMouseOver(String paramVal)
  {
    setParam(JSEventKind.ONMOUSEOVER.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnMouseOver(String paramVal)
  {
    appendParam(JSEventKind.ONMOUSEOVER.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnKeyDown(String paramVal)
  {
    setParam(JSEventKind.ONKEYDOWN.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnKeyDown(String paramVal)
  {
    appendParam(JSEventKind.ONKEYDOWN.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnKeyPress(String paramVal)
  {
    setParam(JSEventKind.ONKEYPRESS.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnKeyPress(String paramVal)
  {
    appendParam(JSEventKind.ONKEYPRESS.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnKeyUp(String paramVal)
  {
    setParam(JSEventKind.ONKEYUP.getStringValue(),paramVal);
    return this;
  }
  
  public IImg appendOnKeyUp(String paramVal)
  {
    appendParam(JSEventKind.ONKEYUP.getStringValue(), paramVal); 
    return this;
  }
  
  public IImg setOnSelect(String paramVal)
  {
    setParam(JSEventKind.ONSELECT.getStringValue(),paramVal); 
    return this;
  }
  
  public IImg appendOnSelect(String paramVal)
  {
    appendParam(JSEventKind.ONSELECT.getStringValue(), paramVal); 
    return this;
  }
  
}//end Img class def