package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Frame.java
* Date:        02.11.00
* Description: Represents a Generic Frame element
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
* ----            Jachin Cheng    02.24.00    Changed to implement interface
* ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
*                                             interface, ScrollingKind
*
*/

public class Frame extends GenericElement implements IFrame
{
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor
   *  Caller MUST set SRC attribute of all Frames
   */ 
  public Frame()
  {
    _elementType = "FRAME";
    _hasElements = false;
  }// end Constructor    
  
  //-------------------------------- Mutator Methods ---------------------------
  public IFrame setBorder(int pixels) {setParam("BORDER", ""+pixels); return this;}
	public IFrame setClass(String paramVal) {setParam("CLASS", paramVal); return this;} 
  public IFrame setBorderColor(String paramVal) {setParam("BORDERCOLOR", paramVal); return this;}  
  public IFrame setID(String paramVal) {setParam("ID", paramVal); return this;} 
  public IFrame setMarginHeight(int mh) {setParam("MARGINHEIGHT", ""+mh); return this;} 
  public IFrame setMarginWidth(int mw) {setParam("MARGINWIDTH", ""+mw); return this;} 
  public IFrame setName(String paramVal) {setParam("NAME", paramVal); return this;} 
  public IFrame setNoResize() {setParam("NORESIZE",""); return this;} 
  public IFrame setScrolling(ScrollingKind sk) {setParam("SCROLLING", sk.getStringValue()); return this;} 
  public IFrame setSrc(String paramVal) {setParam("SRC", paramVal); return this;}   
  public IFrame setStyle(String paramVal) {setParam("STYLE", paramVal); return this;} 
  public IFrame setTitle(String paramVal) {setParam("TITLE", paramVal); return this;}  
  
  public IFrame setFrameBorder(boolean border) 
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

	public String getSrc() 
	{
		return (String)getParam("SRC");
	}

}// end Frame class def