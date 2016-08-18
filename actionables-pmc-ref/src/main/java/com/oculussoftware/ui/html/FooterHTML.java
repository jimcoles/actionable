package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    FooterHTML.java
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

public class FooterHTML extends com.oculussoftware.ui.html.wrappers.HTML implements IFooterHTML
{
  protected IForm _form = null;
  protected IHead _head = null;
  protected boolean isFirst = true;
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public FooterHTML(BrowserKind bk)
	throws OculusException
  {
	//  SpellCheck.js
	super(bk);
	_head = this.addHead();
	IBody body = this.addBody().setBGColor("#C6C3C6");
	ICenter center = body.addCenter();
	ITable table = center.addTable();
	_form = table.addTableRow().addTableData().setHeight(25).addForm().setOnSubmit("return false;");
  }  
  public IFooterHTML addButton(String label, String method)
  {
	if (isFirst)
	  isFirst = false;
	else
	  _form.addAnchor().setStringValue("&nbsp;&nbsp;&nbsp;");
	_form.addInput().setType(InputKind.SUBMIT).setValue(label).setOnClick("parent.Body."+method);
	return this;
  }  
  public IFooterHTML addCancelButton()
  {
	return addCancelButton("Cancel");
  }    
  public IFooterHTML addCancelButton(String label)
  {
	return addButton(label,"cancelPage();");
  }    
  public IFooterHTML addPreviousButton()
  {
	return addPreviousButton("Previous");
  }    
  public IFooterHTML addPreviousButton(String label)
  {
	return addButton(label,"previousPage();");
  }    
  public IFooterHTML addResetButton()
  {
	return addButton("Reset","resetPage();");
  }  
  public IFooterHTML addResetButton(String label)
  {
	return addButton(label,"resetPage();");
  }  
  public IFooterHTML addSpellCheckerButton()
  {
    return addSpellCheckerButton(false);
  }  
  public IFooterHTML addSpellCheckerButton(boolean external)
  {
	if (isFirst)
	  isFirst = false;
	else
	  _form.addAnchor().setStringValue("&nbsp;&nbsp;&nbsp;");

	_head.addScript().setSrc("/system/OculusJS/SpellCheck.js");
	_form.addInput().setType(InputKind.BUTTON).setValue("Check Spelling").setOnClick("openJSpell("+external+");");
	return this;
  }  
  public IFooterHTML addSubmitButton()
  {
	return addSubmitButton("Submit");
  }  
  public IFooterHTML addSubmitButton(String label)
  {
	return addButton(label,"document; if (parent && parent.Body && parent.Body.document && parent.Body.alreadyBeenSubmitted) alert('Your request is processing.'); else parent.Body.submitPage();");
  }  
}
