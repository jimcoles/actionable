package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    PMHeader.java
* Date:        03.22.00
* Description: Represents an HTML element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class PageFrameset extends com.oculussoftware.ui.html.wrappers.Frameset implements IPageFrameset
{
  protected IFrame _header = null;
  protected IFrame _body = null;
  protected IFrame _footer = null;
  protected IFrameset _page = null;

	protected boolean _needHourGlass = true;
  protected int _headersize = 30;
  protected int _footersize = 35;
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public PageFrameset(IHTML doc, boolean hasFooter, boolean hasToolbar, boolean subtabs)
  {
	  super();
    doc.addHead();
//    _page = doc.addFrameset();
    setPageSizes();

    if (!hasFooter)
      setFooterSize(0);
    int headersize = 30;
    if (hasToolbar) headersize += 20;
    if (subtabs) headersize += 20;
    setHeaderSize(headersize);
  }
  
  private IPageFrameset setPageSizes()
  {
    setRows(_headersize+",*"+(_footersize > 0?","+_footersize:""));
    return this;
  }

  public int getHeaderSize() { return _headersize; }
  public int getFooterSize() { return _footersize; }

  public IFrame setHeaderSize(int size)
  {
    _headersize = size;
    setPageSizes();
    return _header;
  }
  
  public IFrame setFooterSize(int size)
  {
    _footersize = size;
    setPageSizes();
    return _footer;
  }
  
  public IFrame getHeaderFrame()
  {
    if (_header == null)
	    _header = addFrame().setName("Header").setNoResize().setScrolling(ScrollingKind.NO).setFrameBorder(false).setBorderColor("#C6C3C6");
    return _header;
  }
  
  public IFrame getBodyFrame()
  {
    if (_body == null)
	    _body = addFrame().setName("Body").setNoResize().setFrameBorder(true).setBorderColor("#C6C3C6");
    return _body;
  }
  
  public IFrame getFooterFrame()
  {
    if (_footer == null)
      _footer = addFrame().setName("Footer").setNoResize().setScrolling(ScrollingKind.NO).setFrameBorder(false).setBorderColor("#C6C3C6").setMarginHeight(0);
    return _footer;
  }

  public boolean needsHourGlass()
  {
		return _needHourGlass;
  }
	
  public IPageFrameset needsHourGlass(boolean need)
  {
		_needHourGlass = need;
		return this;
  }


	public String toString()
	{
		if (needsHourGlass())
		{
			IFrame bodyFrame = getBodyFrame();
			String url = bodyFrame.getSrc();
			bodyFrame.setSrc("/system/OculusImages/animation/HourGlass.gif");
			appendOnLoad("Body.location='"+url+"'; if (document.layers) { window.onresize = onload; }");
			needsHourGlass(false);
		}
		return super.toString();
	}

  //--------------------- IFrameset methods ------------------------
//	
//  public IFrame addFrame()
//  { 
//	  if(_page != null)
//	    return _page.addFrame(); 
//		return null;
//	}
//	
//  public IFrameset addFrameset()
//  { 
//	  if(_page != null)
//	    return _page.addFrameset(); 
//		return null;
//	}
//	
//  public IFrameset setBorder(int pixel)
//  { 
//    if(_page != null)
//      return _page.setBorder(pixel); 
//  	return null;
//  }
//	
//  public IFrameset setBorderColor(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setBorderColor(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setFrameBorder(boolean border)
//  { 
//    if(_page != null)
//      return _page.setFrameBorder(border); 
//  	return null;
//  }
//	
//  public IFrameset setClass(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setClass(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setCols(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setCols(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setID(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setID(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setRows(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setRows(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setStyle(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setStyle(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setTitle(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setTitle(paramVal); 
//  	return null;
//  }
//  
//  public IFrameset setOnLoad(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setOnLoad(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset setOnUnload(String paramVal)
//  { 
//    if(_page != null)
//      return _page.setOnUnload(paramVal); 
//  	return null;
//  }
//
//  public IFrameset appendOnLoad(String paramVal)
//  { 
//    if(_page != null)
//      return _page.appendOnLoad(paramVal); 
//  	return null;
//  }
//	
//  public IFrameset appendOnUnload(String paramVal) 
//  { 
//    if(_page != null)
//      return _page.appendOnUnload(paramVal); 
//  	return null;
//  }
	
}