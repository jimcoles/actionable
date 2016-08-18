package com.oculussoftware.service.log;

import com.oculussoftware.api.service.log.*;

import java.io.*;
import java.text.*;
import java.util.*;

/**
* Filename:    LogService.java
* Date:        4-21-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public final class DatabaseLogService implements ILogService
{	
	/*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  private static DatabaseLogService _logservice;
  
  private String _fullpath;
    
  /**
  * private constructor
  */
  private DatabaseLogService()
  {
    _fullpath = "../dblog.log";
  }//
  
  /**
  * singleton
  */
  public static DatabaseLogService getInstance()
  {
    if(_logservice == null)
      _logservice = new DatabaseLogService();
    return _logservice;
  }//
  
  public ILogService setFullPath(String path)
  { _fullpath = path; return this; }
  
  public String getFullPath()
  { return _fullpath; }
  
  
  /**
  *
  */
  public ILogService write(Throwable exc)
  {
    return this;
  }//
  
  /**
  *
  */
  public ILogService write(String str)
  {
    if (LogManager.getInstance().DATABASELOGGING)
    {
      PrintWriter out = getWriter();
      out.println(str+" GO \r\n");
      out.flush();
      out.close();
    }
    return this;
  }
  
  /**
  *
  */
  private PrintWriter getWriter()
  {
    PrintWriter out = null;
    try
    {
      out = new PrintWriter(new FileWriter(_fullpath,true));
    }//end try
    catch(IOException ignored) {}
    return out;
  }//
}//end Utils
