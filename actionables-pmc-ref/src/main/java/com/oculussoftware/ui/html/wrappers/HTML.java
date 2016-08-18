package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Html.java
* Date:        02.11.00
* Description: Represents an HTML element
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
* ----            Jachin Cheng    02.23.00    Added addHead(), addBody(), and
*                                             addFrameset()
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*/

public class HTML extends GenericElement implements IHTML
{
  protected IHead _head = null;
  protected IBody _body = null;
  protected IFrameset _frameset = null;
	protected IPageFrameset _pageframeset = null;
  
  protected int _numImages = 0;

  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
	public HTML(BrowserKind bk)
	{
	  //set default attributes here
    _elementType = "HTML";                        // sets element name
    setHTMLObject(this);
	  setBrowserKind(bk);
		
		//Saleem I put this code in because it was breaking when the 
		//first item was a disabled radio button
		//also, it was scrolling to the textarea at the bottom of the page.
		//Egan
	  addHead().addScript().setStringValue(
		  "function firstElementFocus() { "+
		  "  if (document.forms[0] && document.forms[0].elements) "+
		  "  { "+
		  "    var ems = document.forms[0].elements; "+
		  "    for (var i = 0; i < ems.length; i++) "+
		  "    {"+
		  "      if (ems[i].type == 'text' || ems[i].type == 'textarea') "+
		  "      { "+
		  "        window.focus();"+
		  "        ems[i].focus();"+
		  "        break; "+
		  "      } "+
		  "      else if(ems[i].type == 'hidden') ;"+//continue
		  "      else break;"+
		  "    }"+
		  "  } "+
		  "}"
		  );
	}
	
  //-------------------------------- Mutator Methods ---------------------------
  
  /** Adds new Head element, returns a handle */
  public IHead addHead()
  {
    if (_head == null)
    {
      _head = new Head();
      addElement(_head);
      _head.addLink().setHRef("/system/OculusSS/oculus.css").setRel("stylesheet").setType("text/css");
			_head.killEnter();
      //<LINK href=\"/system/EclatStyleSheets/oculus.css\" rel=\"stylesheet\" type=\"text/css\">
    }
    return _head;    
  }
 
  /** Adds new Body element, returns a handle */       
  public IBody addBody()
  {
    if (_body == null)
    {
      _body = new Body();
      addElement(_body);
      if (_body.getBrowserKind().equals(BrowserKind.NETSCAPE))
	    {
        _body.setOnResize("if (document.layers) window.location.reload();");
	      _body.setMarginWidth(0);
				_body.setMarginHeight(0);
        _body.setLeftMargin(0);
				_body.setTopMargin(0);				
			}//end if
    }
    return _body;
  }
	
	public IPageFrameset addPageFrameset()
	{ return addPageFrameset(true,false); }

	public IPageFrameset addPageFrameset(boolean hasFooter)
	{ return addPageFrameset(hasFooter,false); }

	public IPageFrameset addPageFrameset(boolean hasFooter, boolean hasToolbar)
	{ return addPageFrameset(hasFooter,hasToolbar,false); }

	public IPageFrameset addPageFrameset(boolean hasFooter, boolean hasToolbar, boolean hasSubTab)
	{
	  if (_pageframeset == null)
    {
      _pageframeset = new PageFrameset(this,hasFooter,hasToolbar,hasSubTab);
      addElement(_pageframeset);
    }
    return _pageframeset;
	}
	
  /** Adds new Frameset element, returns a handle */
  public IFrameset addFrameset()
  {
    if (_frameset == null)
    {
      _frameset = new Frameset();
      addElement(_frameset);
    }
    return _frameset;
  }
  
  public IHead getHead() { return _head; }
  public IBody getBody() { return _body; }
  public IFrameset getFrameset() { return _frameset; }
  
  
  public void added(IGenericElement ge)
  {
    if (ge instanceof IImg)
    {
      ((IImg)ge).setIndex(_numImages++);
    }
  }
}//end Html class def