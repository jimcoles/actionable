package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    Frameset.java
* Date:        02.11.00
* Description: Represents a generic Frameset element
*              Subclasses of this class may add Oculus specific
*              frame styles/layouts.
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
* ----            Jachin Cheng    02.23.00    Added addFrame()
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*/

public class Frameset extends GenericElement implements IFrameset
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Frameset()
  {
    //set default attributes here
    _elementType = "FRAMESET";
  }
  
  //-------------------------------- Mutator Methods ---------------------------
  
  /** Adds new Frame element, returns a handle */
  public IFrame addFrame()
  {
      IFrame f = new Frame();
      addElement(f);
      return f;    
  }
  
  public IFrameset addFrameset()
  {
      IFrameset fs = new Frameset();
      addElement(fs);
      return fs;    
  }
  
  public IFrameset setBorder(int border) {setParam("BORDER", ""+border); return this;} 
  public IFrameset setBorderColor(String paramVal) {setParam("BORDERCOLOR", paramVal); return this;} 
  public IFrameset setClass(String paramVal) {setParam("CLASS", paramVal); return this;} 
  public IFrameset setCols(String paramVal) {setParam("COLS", paramVal); return this;} 
  public IFrameset setID(String paramVal) {setParam("ID", paramVal); return this;} 
  public IFrameset setRows(String paramVal) {setParam("ROWS", paramVal); return this;} 
  public IFrameset setStyle(String paramVal) {setParam("STYLE", paramVal); return this;} 
  public IFrameset setTitle(String paramVal) {setParam("TITLE", paramVal); return this;} 
  public IFrameset setOnLoad(String paramVal) {setParam(JSEventKind.ONLOAD.getStringValue(), paramVal); return this;} 
  public IFrameset setOnUnload(String paramVal) {setParam(JSEventKind.ONUNLOAD.getStringValue(), paramVal); return this;} 

  public IFrameset setFrameBorder(boolean border) 
  {
    if (border)
	  {
      setParam("FRAMEBORDER", "1");
			setBorder(1);
	  }
    else
	  {
      setParam("FRAMEBORDER", "0");
			setBorder(0);
	  }
    return this;
  } 
  
  public IFrameset appendOnLoad(String paramVal)
  {
    appendParam(JSEventKind.ONLOAD.getStringValue(), paramVal);
    return this;
  }  
  
  public IFrameset appendOnUnload(String paramVal)
  {
    appendParam(JSEventKind.ONUNLOAD.getStringValue(), paramVal);
    return this;
  }         
    
  
}//end Frameset class def