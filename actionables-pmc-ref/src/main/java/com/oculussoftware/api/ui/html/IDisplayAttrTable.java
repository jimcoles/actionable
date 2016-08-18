package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IDisplayAttrTable.java $
* Description: Smart ITable object that builds the grid of Display
* Attributes for Custom Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart ITable object that builds the grid of Display
* Attributes for Custom Reports.
*
* @author Egan Royal
*/
public interface IDisplayAttrTable extends ITable
{
  /**
  * Prefix for the select box names.
  */
  public static final String SELECT_NAME = "Attr_";
  
  /**
  * The maximum number of display attributes.
  */
  public static final int MAX_NUM_ATTRS  = 6;
  
  
  /**
  * Returns the number of Display Attributes.
  * @return MAX_NUM_ATTRS
  */
  public int getNumDisplayAttrs();
  
  /**
  * This method builds the Table.  It is also responsible fro populating the 
  * select boxes.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IDisplayAttrTable buildTable() throws OculusException;
  
  /**
  * This method returns the ISelect at the given index.
  * @param idx The index.
  * @return The ISelect at the given index.
  */
  public ISelect getSelect(int idx);
  
  /**
  * This method adds and returns an IOption to the ISelct at the given index.
  * @param idx The index.
  * @return The IOption.
  */
  public IOption addOption(int idx);
  
  /**
  * This method adds and returns an IOption to the ISelct at the given index.
  * @param idx The index.
  * @param name The option name.
  * @param value The option value.
  * @return The IOption.
  */
  public IOption addOption(int idx, String name, String value);

}//end IDisplayAttrTable