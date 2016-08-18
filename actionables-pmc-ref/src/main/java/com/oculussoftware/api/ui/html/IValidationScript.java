package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IValidationScript.java $
* Description: Registers the Inputs/Textareas for validation.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Registers the Inputs/Textareas for validation.
*
* @author Saleem Shafi
*/
public interface IValidationScript extends IScript
{   
  /**
  * This method takes an Input and registers it for the given ValidationKind.
  * @param input The input.
  * @param type The ValidationKind to validate against. 
  */
  public void registerInput(String input, ValidationKind type);
  
  /**
  * This method takes an Input and registers it for the given ValidationKind.
  * @param input The input.
  * @param type The ValidationKind to validate against. 
  * @parma name An alias for the error message.
  */
  public void registerInput(String input, ValidationKind type, String name);
}