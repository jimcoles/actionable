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
public final class LogService implements ILogService
{	
	/*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  private static LogService _logservice;
  
  private String _fullpath;
    
  /**
  * private constructor
  */
  private LogService()
  {
    _fullpath = "../pmlog.log";
  }//
  
  /**
  * singleton
  */
  public static LogService getInstance()
  {
    if(_logservice == null)
      _logservice = new LogService();
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
    if (LogManager.getInstance().ERRORLOGGING)
    {
      PrintWriter out = getWriter();
      writeHeader(out);
      exc.printStackTrace(out);
      writeFooter(out);
      out.flush();
      out.close();
    }
    return this;
  }//
  
  /**
  *
  */
  public ILogService write(String str)
  {
    if (LogManager.getInstance().ERRORLOGGING)
    {
      PrintWriter out = getWriter();
      writeHeader(out);
      out.println(str);
      writeFooter(out);
      out.flush();
      out.close();
    }
    return this;
  }
  
  /**
  *
  */
  private void writeHeader(PrintWriter out)
  {
    Date date = new Date();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);  
    out.println(df.format(date));
    out.println();
  }
  
  /**
  *
  */
  private void writeFooter(PrintWriter out)
  {
    out.println();
    out.println("/////////////////////////////////////////////////////////////");
    out.println();
  }//
  
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