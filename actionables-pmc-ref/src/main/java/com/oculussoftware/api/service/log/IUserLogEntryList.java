package com.oculussoftware.api.service.log;

import com.oculussoftware.api.busi.common.org.*;
import java.sql.*;
/*
* $Workfile: IUserLogEntryList.java $
* Description: Contains a List of IUserLogEntries.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Contains a List of IUserLogEntries.
*
* @author Zain Nemazie
*/
public interface IUserLogEntryList 
{
  /**
  * This method returns true iff the List(iterator) has more elements.
  * @return true iff the List(iterator) has more elements.
  */
  public boolean hasMoreEntries();
  
  /**
  * This method returns the next IUserLogEntry in the List(iterator).
  * @return The IUserLogEntry.
  */
  public IUserLogEntry nextEntry();
  
  /**
  * This method resets the List iterator.
  * @return this
  */
  public IUserLogEntryList reset();
  
  /**
  * This method sets the value for sorting.
  * @param str The sort by String.
  * @return this
  */
  public IUserLogEntryList setSortBy(String str);
}
