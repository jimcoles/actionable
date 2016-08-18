package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.ui.html.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IMeta.java $
* Description: Represents a generic Meta interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Meta interface.
*
* @author Egan Royal
*/
public interface IMeta extends IGenericElement
{
  /**
  * This method sets the HTML parameter value for Content of the 
  * Meta Tag.
  * @param paramVal The value to be set for Content of the Meta Tag.
  * @return this
  */ 
  public IMeta setContent(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Dir of the 
  * Meta Tag.
  * @param paramVal The value to be set for Dir of the Meta Tag.
  * @return this
  */
  public IMeta setDir(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Httpequiv of the 
  * Meta Tag.
  * @param paramVal The value to be set for Httpequiv of the Meta Tag.
  * @return this
  */
  public IMeta setHttpequiv(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Lang of the 
  * Meta Tag.
  * @param language The value to be set for Lang of the Meta Tag.
  * @return this
  */
  public IMeta setLang(LangKind language);
  
  /**
  * This method sets the HTML parameter value for Name of the 
  * Meta Tag.
  * @param paramVal The value to be set for Name of the Meta Tag.
  * @return this
  */
  public IMeta setName(String paramVal);
  
  /**
  * This method sets the HTML parameter value for Scheme of the 
  * Meta Tag.
  * @param paramVal The value to be set for Scheme of the Meta Tag.
  * @return this
  */
  public IMeta setScheme(String paramVal);
  
  /**
  * This method sets the HTML parameter value for URL of the 
  * Meta Tag.
  * @param paramVal The value to be set for URL of the Meta Tag.
  * @return this
  */
  public IMeta setURL(String paramVal);
  
  /**
  * This method appends the HTML parameter value for Content of the 
  * Meta Tag.
  * @param paramVal The value to be appeded for Content of the Meta Tag.
  * @return this
  */
  public IMeta appendContent(String paramVal);
}//end IMeta interface def