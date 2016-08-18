package com.oculussoftware.service.log;

import com.oculussoftware.api.service.log.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
* Filename:    UserLogService.java
* Date:        4-21-00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* BUG00455        Zain Nemazie    7/05/00     Added clear log 
* PRB01326        Zain Nemazie    7/11/00     Set singleton to false 
*
*/


public final class UserLogService implements IUserLogService
{
  private static UserLogService _logservice;
  private String _fullpath;
  /**
  * private constructor
  */
  private UserLogService()
  {
    _fullpath = "../pmlog.log";
  }//    
  /**
  * addEntry method comment.
  */
  public IUserLogService addEntry(IObjectContext context, IUser usr, java.sql.Timestamp dt) throws OculusException
  {
    return addEntry(context, usr.getIID(), dt);
  }
  /**
  * addEntry method comment.
  */
  public synchronized IUserLogService addEntry(IObjectContext context, IIID userID, java.sql.Timestamp dt) throws OculusException
  {
    IRConnection conn = context.getRepository().getDataConnection(context);
    IQueryProcessor qp = conn.createProcessor();
    qp.update("INSERT INTO USERLOG (USERID, DATE) VALUES ("+userID.getLongValue()+",'"+dt+"') ");
    conn.commit();
    return this;
  }
  
  /**
  * Clear log
  */
  public synchronized IUserLogService clearLog(IObjectContext context) throws OculusException
  {
    IRConnection conn = context.getRepository().getDataConnection(context);
    IQueryProcessor qp = conn.createProcessor();
    qp.setSingleton(false);
    qp.update("DELETE FROM USERLOG");
    conn.commit();
    return this;
  }
  public synchronized IUserLogEntryList getEntryList(IObjectContext context) throws OculusException
  {
    return new UserLogEntryList(context);
  }//          
  /**
  * singleton
  */
  public static IUserLogService getInstance()
  {
    if(_logservice == null)
      _logservice = new UserLogService();
    return _logservice;
  }//          
} //end Utils
