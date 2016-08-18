package com.oculussoftware.rdb;

import java.sql.*;


/**
* Filename:    SQLState.java
* Date:        8-21-1999
* Description: his class is package-level because we don't want consumers knowing
* anything about SQL.  It is designed to abstract out SQL state information
* and state in vendor-independent terms. 
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*
*/
class SQLState
{
  private static final String CLIENT_LIMIT = "S1000";
  

  /** Determines if exception is one that we can possilbe retry
   * @param   ex  
   * @return     
   */
  static boolean isRetryable(SQLException ex)
  {
    return false;
//    return isUserLimitExceeded(ex);
  }
  

  /**
   * @param   ex  
   * @return     
   */
  static boolean isUserLimitExceeded(SQLException ex)
  {
    boolean retVal = false;
    if ( ex.getSQLState().trim().equals(CLIENT_LIMIT)) {
      retVal = true;
    }
    return retVal;
  }
}