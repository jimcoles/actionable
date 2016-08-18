package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.sysi.OculusException;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IHeader.java $
* Description: Represents a generic Header interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic Header interface.
*
* @author Egan Royal
*/
public interface IHeader extends IGenericElement 
{
  /** 
  * Sets the String bounded by the Header tag.
  * @param value The String bounded by the Header tag.
  * @return this
  */
  public IHeader setStringValue(String value);
}//end IHeader interface def