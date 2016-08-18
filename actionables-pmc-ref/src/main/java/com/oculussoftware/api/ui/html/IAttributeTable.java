package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IAttributeTable.java $
* Description: Smart ITable object that knows how to display Attributes.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart ITable object that knows how to display Attributes.
*
* @author Saleem Shafi
*/
public interface IAttributeTable extends ITable
{
  /**
  * Adds a Table Header to the Table
  * @param caption The caption for the Table Header.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */ 
	public void addHeader(String caption)
		throws OculusException;
	
  /**
  * Adds an Attribute to the Table.
  * @param label The label for the Attribute.
  * @param value The value of the Attribute.
  */
	public void addAttribute(String label, String value);
  
  /**
  * Adds an Attribute to the Table.
  * @param label The label for the Attribute.
  * @param value The value of the Attribute.
  */
	public void addAttribute(String label, java.sql.Timestamp value);
  
  /**
  * Adds an Attribute where both the label and the value are spaces.
  */
  public void addSpace();
  
  /**
  * Adds a single row with two cells to the Table.  The method puts the 
  * label in the first cell and returns the second cell.
  * @param label The label.
  */
  public ITableData addSingleLabel(String label);
  
  /**
  * Adds two rows to the Table.  The method puts the label in the cell
  * on the first row and returns the cell on the second row.
  * @param label The label.
  */
  public ITableData addDoubleLabel(String label);

}//end IAttributeTable