package com.oculussoftware.api.service.log;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.common.org.*;
import java.sql.Timestamp;

/*
* $Workfile: IUserLogService.java $
* Description: .
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Enables logging User logons.  This object writes to the UserLog table
* in the database.
*
* @author Zain Nemazie
*/
public interface IUserLogService
{
  /**
  * This method adds an entry into the UserLog.
  * @param context The current user's context.
  * @param usr The current user.
  * @param dt The date.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IUserLogService addEntry(IObjectContext context, IUser usr, Timestamp dt) throws OculusException;                  

  /**
  * This method adds an entry into the UserLog.
  * @param context The current user's context.
  * @param UserID The IIID of the current user.
  * @param dt The date.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IUserLogService addEntry(IObjectContext context, IIID UserID, Timestamp dt) throws OculusException;              
  
  /**
  * This method returns the IUserLogEntryList.
  * @param context The current user's context.
  * @return The IUserLogEntryList.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IUserLogEntryList getEntryList(IObjectContext context) throws OculusException;    
  
  /**
  * This method clears the log in the database.
  * @param context The current user's context.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IUserLogService clearLog(IObjectContext context) throws OculusException;

}//end IUserLogService
