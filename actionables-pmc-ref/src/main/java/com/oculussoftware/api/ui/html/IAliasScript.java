package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IAliasScript.java $
* Description: Defines the necessary methods to alias inputs.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Defines the necessary methods to alias inputs.  Allows renaming 
* Inputs for javascript.
*
* @author Saleem Shafi
*/
public interface IAliasScript extends IScript
{   
  /**
  * This method takes the Input name and an alias.  This method assumes 
  * that you are using document.forms[0].   The method will effectively 
  * alias the name of the Input so that when another javascript element needs 
  * to display the name of the element, the alias is used instead.  An example
  * of how this is used is the form validation alert boxes.
  * @param input The input name.
  * @param alias The alias.
  */
  public void alias(String input, String alias);
}