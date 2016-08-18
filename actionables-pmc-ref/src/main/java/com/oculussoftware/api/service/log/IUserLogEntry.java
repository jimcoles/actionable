package com.oculussoftware.api.service.log;

import com.oculussoftware.api.busi.common.org.*;
import java.sql.*;
/*
* $Workfile: IUserLogEntry.java $
* Description: Wraps the information for an entry in the
* user log.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Wraps the information for an entry in the
* user log.
*
* @author Zain Nemazie
*/
public interface IUserLogEntry 
{
  /**
  * This method returns the creation date for this UserLogEntry.
  * @return The creation date.
  */
  public Timestamp getCreationDate();
  
  /**
  * This method returns the date for this UserLogEntry.
  * @return The date.
  */
  public Timestamp getDate();
  
  /**
  * This method returns the user for this UserLogEntry.
  * @return The user.
  */
  public IUser getUser();
  
  /**
  * This method sets the date.
  * @param dt The date.
  */
  public IUserLogEntry setDate(Timestamp dt);
  
  /**
  * This method sets the user.
  * @param usr The user.
  */
  public IUserLogEntry setUser(IUser usr);
}
