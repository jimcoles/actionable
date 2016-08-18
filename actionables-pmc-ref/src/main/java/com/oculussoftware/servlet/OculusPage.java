package com.oculussoftware.servlet;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.ui.*;
import com.oculussoftware.ui.html.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.*;

/**
* Filename:    EclatServlet.java
* Date:        2/28/00
* Description: Base class for all of the standard servlets in the Oculus empire.
*              It provides some basic functionality as well as restricting what
*              each servlet implementation can do to enforce good coding.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ---             Saleem Shafi    3/22/00     Converted base class to merge multiple frames into a single logical page.
* EXTRANET        Saleem Shafi    8/22/00     Modified getFooter
*/

public abstract class OculusPage extends EclatServlet
{
  /** Preprocessor for any GET method requests */
	public IHTML buildPage(SessionSrvc session, IHTML doc, IObjectContext context, String pageType)
		throws Exception
	{
	if (pageType == null)
	  doc = getMain(session, doc, context);
	else if (pageType.equals("Header"))
	  doc = getHeader(session, doc, context);
	else if (pageType.equals("Footer"))
	  doc = getFooter(session, doc, context);
	else if (pageType.equals("Body"))
	  doc = getBody(session, doc, context);
	return doc;
  }  
  public IHTML getBody(SessionSrvc session, IHTML doc, IObjectContext context)
	throws Exception
  {
	throw new OculusException("Missing Body.");
  }  
  public IHTML getFooter(SessionSrvc session, IHTML doc, IObjectContext context)
	throws Exception
  {
	IFooterHTML page = new FooterHTML(doc.getBrowserKind());
	String buttons = (String)session.getLocalValue("Buttons");
	if (buttons.indexOf("Previous") > -1)
	{
	  String previous = (String)session.getLocalValue("Previous");
	  if (previous != null)
		page.addPreviousButton(previous);
	  else
		page.addPreviousButton();
	}
	if (buttons.indexOf("Submit") > -1)
	{
	  String submit = (String)session.getLocalValue("Submit");
	  if (submit != null)
		page.addSubmitButton(submit);
	  else
		page.addSubmitButton();
	}
	if (buttons.indexOf("Reset") > -1)
	{
	  String reset = (String)session.getLocalValue("Reset");
	  if (reset != null)
		page.addResetButton(reset);
	  else
		page.addResetButton();
	}
	if (buttons.indexOf("Cancel") > -1)
	{
	  String cancel = (String)session.getLocalValue("Cancel");
	  if (cancel != null)
		page.addCancelButton(cancel);
	  else
		page.addCancelButton();
	}
	if (buttons.indexOf("SpellCheck") > -1)
  {
    boolean isExternal = false;
    isExternal = (session.getGlobalValue("EXTRANET_REQUEST") != null);
	  page.addSpellCheckerButton(isExternal);
  }
	return page;
  }            
  public IHTML getHeader(SessionSrvc session, IHTML doc, IObjectContext context)
	throws Exception
  {
	IHeaderHTML page = new HeaderHTML(doc.getBrowserKind());
	String menus = (String)session.getLocalValueDestroy("Menus");
	String toolbar = (String)session.getLocalValueDestroy("ToolbarIcons");
	boolean isToolbar=false;
	if (toolbar != null && !toolbar.equals("0"))
		isToolbar=true;

	if (isToolbar)
	{
	  int numIcons = Integer.parseInt(toolbar);
	  page.addToolbar(numIcons);
	}
		
	if (menus != null)
	{
//	  page.getHead().addScript().setSrc("/system/OculusJS/menu.js");
	  StringTokenizer tokens = new StringTokenizer(menus,",");
	  while (tokens.hasMoreTokens())
		page.addNewMenu(tokens.nextToken(), isToolbar);
	}
	
	String caption = (String)session.getLocalValueDestroy("Caption");
	page.setCaption(caption);

  IDiv Tdiv = page.getBody().addDiv().setID("overDiv").setStyle("position:absolute; visibility:hide; z-index:1;");
  page.getBody().addScript().setSrc("/system/OculusJS/overlib.js");

	return page;
  }  
  // Force each page to produce some output
	abstract public IHTML getMain(SessionSrvc session, IHTML doc, IObjectContext context)
		throws Exception;
  protected void getValue(SessionSrvc session, IRPropertyMap props, IIID attribID)
	throws OculusException
  {
	getValue(session, props, "prop"+attribID);
  }  
  protected void getValue(SessionSrvc session, IRPropertyMap props, String key)
	throws OculusException
  {
	IRProperty newValue = (IRProperty)props.get(key);
	if (newValue != null)
	{
	  key = key.replace('-','_');
	  Object value = session.getLocalValueDestroy(key);
	  if (value != null) newValue.setValue(value);
	}
  }  
  protected void getValues(SessionSrvc session, IBusinessObject obj)
	throws OculusException
  {
	  IRPropertyMap props = obj.getProperties();

	  IRModelElementList customs = obj.getDefnObject().getEntryForm().getEditableAttributeList();
	  customs.reset();
	  while (customs.hasNext())
	  {
	    IRAttribute custom = (IRAttribute)customs.next();
	  getValue(session,props,custom.getIID());
	  }
  }  
}
