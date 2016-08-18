package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IFooterHTML.java $
* Description: Smart IHTML object that knows how to display buttons.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart IHTML object that knows how to display buttons.
*
* @author Saleem Shafi
*/
public interface IFooterHTML extends IHTML
{   
  /**
  * Adds a generic button to the IHTML document.  The method appends "parent.Body."
  * to the passed in javascript method.
  * @param label The label for the button.
  * @param method The javascript code to be executed when the button is clicked.
  * @return this
  */
  public IFooterHTML addButton(String label, String method);
  
  /**
  * Adds a Cancel Button the the IHTML document.  The method calls addButton with
  * "Cancel" as the label and "cancelPage();" as the method.
  * @return this
  */  
  public IFooterHTML addCancelButton();
  
  /**
  * Adds a Cancel Button the the IHTML document.  The method calls addButton with
  * "cancelPage();" as the method.
  * @param label The new label for the Cancel Button.
  * @return this
  */ 
  public IFooterHTML addCancelButton(String label); 
  
  /**
  * Adds a Previous Button the the IHTML document.  The method calls addButton with
  * "Previous" as the label and "previousPage();" as the method.
  * @return this
  */   
  public IFooterHTML addPreviousButton(); 
  
  /**
  * Adds a Previous Button the the IHTML document.  The method calls addButton with
  * "previousPage();" as the method.
  * @param label The new label for the Previous Button.
  * @return this
  */ 
  public IFooterHTML addPreviousButton(String label);
  
  /**
  * Adds a Reset Button the the IHTML document.  The method calls addButton with
  * "Reset" as the label and "resetPage();" as the method.
  * @return this
  */
  public IFooterHTML addResetButton();  
  
  /**
  * Adds a Reset Button the the IHTML document.  The method calls addButton with
  * "resetPage();" as the method.
  * @param label The new label for the Reset Button.
  * @return this
  */
  public IFooterHTML addResetButton(String label);
  
  /**
  * Adds a SpellChecker Button the the IHTML document.  The method calls addButton with
  * "Check Spelling" as the label and "openJSpell(false);" as the method.
  * @return this
  */  
  public IFooterHTML addSpellCheckerButton(); 
  
  /**
  * Adds a SpellChecker Button the the IHTML document.  The method calls addButton with
  * "Check Spelling" as the label and "openJSpell("+external+");" as the method.
  * @param external true if the request came from the extranet.
  * @return this
  */ 
  public IFooterHTML addSpellCheckerButton(boolean external);  
  
  /**
  * Adds a Submit Button the the IHTML document.  The method calls addButton with
  * "Submit" as the label and "submitPage();" as the method.
  * @return this
  */
  public IFooterHTML addSubmitButton();
  
  /**
  * Adds a Submit Button the the IHTML document.  The method calls addButton with
  * "submitPage();" as the method.
  * @param label The new label for the Submit Button.
  * @return this
  */  
  public IFooterHTML addSubmitButton(String label);  
}
