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
* @author Saleem Shafi
* @version 1.2
*/
public final class LogManager
{	
	/*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  private static LogManager _logManager;
  public boolean ERRORLOGGING = true;
  public boolean DATABASELOGGING = true;
  
  /**
  * private constructor
  */
  private LogManager()
  {
  }//
  
  /**
  * singleton
  */
  public static LogManager getInstance()
  {
    if(_logManager == null)
      _logManager = new LogManager();
    return _logManager;
  }//
  
}//end Utils