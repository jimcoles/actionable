package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: ISpellCheckExcludeScript.java $
* Description: Supplies the functionality of excluding IInputs from 
* the spell checker.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Supplies the functionality of excluding IInputs from 
* the spell checker.
*
* @author Saleem Shafi
*/
public interface ISpellCheckExcludeScript extends IScript
{   
  /**
  * Excludes the given input from spell checking.
  * @param input The input.
  */
  public void exclude(String input);
}