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
* BUG00318		  Saleem Shafi    5/25/00     Overloaded registerInput() to take an explicit display name.
*/

public class ValidationScript extends com.oculussoftware.ui.html.wrappers.Script implements IValidationScript
{
  protected String _script = null;
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public ValidationScript()
  {
    _script = "function registerInputs() { }";
    setStringValue(_script);
  }

  public void registerInput(String input, ValidationKind type)
  {
  	registerInput(input, type, input);
  }
  
  public void registerInput(String input, ValidationKind type, String name)
  {
    int insertPoint = _script.length()-2;
    String newLine = "register(document.forms[0]."+input+","+type.getStringValue()+",'"+name+"'); ";
    _script = _script.substring(0,insertPoint)+newLine+" }";
    setStringValue(_script);
  }


}