package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.html.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Head.java
* Date:        02.22.00
* Description: Represents a Document Head element
*          e.g. <HEAD> ... </HEAD>
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
* ----            Jachin Cheng    02.23.00    Added addTitle(), addScript(),
*                                             and addMeta().
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*/

public class Head extends GenericElement implements IHead
{
  IImageRegisterScript _imgRegScript = null;
  IValidationScript _validScript = null;
	ISpellCheckExcludeScript _excludeScript = null;
  IAliasScript _aliasScript = null;
  boolean _calendarscript = false;
	boolean _killenterscript = false;
  boolean _alreadyBrowsed = false;

  //----------------------------- Public Constructors -------------------------
  
  /** Basic default constructor */
  public Head()
  {    
    _elementType = "HEAD"; 
		
  }
  
  //-------------------------------- Mutator Methods --------------------------- 
  
	public IHead addHourGlassScript()
	{
	  addScript().setStringValue("function hourGlass() "+
  						"{ "+
  						"  if (document.all)"+
  						"    document.all['hourGlass'].style.display='block'; "+
  						"  if (document.layers)"+
  						"    document.hourGlass.visibility='show'; "+
  						"}");
		IDiv hourGlass = getHTMLObject().addBody().addDiv().setName("hourGlass").setID("hourGlass").setVPIgnore(true);
		if (getHTMLObject().getBrowserKind().equals(BrowserKind.NETSCAPE))
			hourGlass.setStyle("position:absolute; visibility:hide; z-index:1;");
		else
			hourGlass.setStyle("position:absolute; display:none; z-index:1;");
			
		hourGlass.addImg().setSrc("/system/OculusImages/animation/HourGlass.gif");
		return this;
	}

	public IHead killEnter()
	{
    /*
    Saleem: You will notice I have commented out some code here. Look at BUG1320.
    Netscape seems to give this error for every key stroke.
    
    
    */
		if (!_killenterscript)
		{
			addScript().setStringValue("function killEnter(e) "+
							"{ "+
							"  var enterKey = false; "+
							"    if (document.all)"+
						  "    { if (window.event.keyCode == 13) enterKey = true; }   "+
							//"    if (document.layers)"+
							//"    { if (e.which == 13) enterKey = true; }   "+             
							"  return !enterKey; "+
							"}");
			_killenterscript = false;
		}
		return this;
	}

  public void addBrowseScript()
  {
    if (!_alreadyBrowsed)
    {
      this.addScript().setSrc("/system/OculusJS/browseview.js");
      getHTMLObject().addBody().appendOnUnload("closeBrowseView();");
      _alreadyBrowsed = true;
    }
  }

  /** Adds new Title element, returns a handle */
  public ITitle addTitle()
  {
      ITitle t = new Title();
      addElement(t);
      return t;    
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
  
  /** Adds new Meta element, returns a handle */
  public IMeta addMeta()
  {
      IMeta m = new Meta();
      addElement(m);
      return m;
  }
  
  public ILink addLink()
  {
    ILink l = new Link();
    addElement(l);
    return l;
  }
  
  /** Adds new Script element, returns a handle */       
  public IStyle addStyle()
  {
      IStyle s = new Style();
      addElement(s);
      return s;
  }
  
  /** Adds new Script element, returns a handle */       
  public IStyle addStyle(String style)
  {
      return addStyle().setStringValue(style);
  }  
  

  
  public IHead setClass(String paramVal) {setParam("CLASS", paramVal); return this;}
  public IHead setID(String paramVal) {setParam("ID", paramVal); return this;} 
  public IHead setTitle(String paramVal) {setParam("TITLE", paramVal); return this;}      



  public IImageRegisterScript addImageRegisterScript()
  {
    addScript().setSrc("/system/OculusJS/imgselect2.js");
    _imgRegScript = new ImageRegisterScript();
    addElement(_imgRegScript);
    
    getHTMLObject().addBody().appendOnLoad("registerImages();");
    
    return _imgRegScript;
  }
  
  public IImageRegisterScript getImageRegisterScript() 
  { return _imgRegScript; }
  
  public IValidationScript addValidationScript()
  {
    addScript().setSrc("/system/OculusJS/formvalidation.js");
    _validScript = new ValidationScript();
    addElement(_validScript);
    
    getHTMLObject().addBody().appendOnLoad("registerInputs();");

    return _validScript;
  }

  public ISpellCheckExcludeScript addSpellCheckExcludeScript()
  {
		if (_excludeScript == null)
		{
	    _excludeScript = new SpellCheckExcludeScript();
	    addElement(_excludeScript);
			getHTMLObject().addBody().appendOnLoad("spellCheckExclude();");
		}
    
    return _excludeScript;
  }

  public IAliasScript addAliasScript()
  {
		if (_aliasScript == null)
		{
	    _aliasScript = new AliasScript();
	    addElement(_aliasScript);
			getHTMLObject().addBody().appendOnLoad("alias();");
		}
    
    return _aliasScript;
  }

  
  public IValidationScript getValidationScript() 
  { return _validScript; }

  public void addCalendarScript()
  {
    if (!_calendarscript)
    {
      _calendarscript = true;
      this.addScript().setSrc("/system/OculusJS/calendar.js");
      getHTMLObject().getBody().appendOnUnload("closeCalendar();");
    }
  }

  public void addMenuFile(String filename)
  {
    this.addScript().setSrc("/system/OculusJS/testmenu.js");
    this.addScript().setSrc(filename);
    IBody body = this.getHTMLObject().addBody();
    body.addDiv().setID("overDiv").setStyle("position:absolute; visibility:hide; z-index:1;");
		body.appendOnLoad("initOverLib();");
    this.addScript().setSrc("/system/OculusHtml/overLib_Instruction_Folder/overlib.js");

    if (this.getBrowserKind().equals(BrowserKind.NETSCAPE))
    {
      this.getHTMLObject().addBody().appendOnLoad("if (window.tryToolbarLoad) window.tryToolbarLoad(); ");
    }
    else
    {
      this.getHTMLObject().addBody().addScript().setStringValue("if (window.tryToolbarLoad) window.tryToolbarLoad(); ");
    }
  }

  
}//end Head class def