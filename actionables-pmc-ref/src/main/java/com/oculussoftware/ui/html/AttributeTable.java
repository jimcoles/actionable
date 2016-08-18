package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    ITable.java
* Date:        02.24.00
* Description: Represents a generic Table interface
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/
public class AttributeTable extends Table implements IAttributeTable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
	* BUG00863				Saleem Shafi		6/5/00			Checking for null pointer exceptions.
  */
    
  //-------------------------------- Mutator Methods ---------------------------    
    public AttributeTable()
    {
		super();
    }
	
    public void addHeader(String caption)
    	throws OculusException
    {
    	addTableRow().addTableHeader().setColSpan(2).addHeader(3).setStringValue(caption);
    }
    	

    public ITableData addSingleLabel(String label)
    {
    	ITableRow row = null;
    	row = addTableRow();
    	row.addTableData().setNowrap().addAnchor().setClass(ClassKind.LABEL).setStringValue(label);
    	return row.addTableData().setWidthRatio(100);
    }
    
    public ITableData addDoubleLabel(String label)
    {
    	ITableRow row = null;
    	row = addTableRow();
    	row.addTableData().setColSpan(2).addAnchor().setClass(ClassKind.LABEL).setStringValue(label);
    	row = addTableRow();
    	return row.addTableData().setColSpan(2);
    }


    public void addAttribute(String label, String value)
    {
    	if (label == null || value == null || (label.length() < 30 && value.length() < 70))
    		_addAttributeSingle(label, value);
    	else
    		_addAttributeDouble(label, value);	
    }
    
    public void addAttribute(String label, java.sql.Timestamp value)
    {
        addAttribute(label,com.oculussoftware.ui.DateFormatter.format(value));
//        data.addCalendar(value);
    }

    private void _addAttributeSingle(String label, String value)
    {
    	addSingleLabel(label).addAnchor().setStringValue(value);
    }

    private void _addAttributeDouble(String label, String value)
    {
    	addDoubleLabel(label).addAnchor().setClass(ClassKind.INDENT).setStringValue(value);
    }
    
    public void addSpace()
    {
      addAttribute("&nbsp;","&nbsp;");
    }
}
