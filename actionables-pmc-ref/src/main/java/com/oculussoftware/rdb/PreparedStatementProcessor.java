package com.oculussoftware.rdb;

import com.oculussoftware.api.repi.*;

import java.util.*;
import java.sql.*;
import java.text.*;
	
/**
* Filename:    PreparedStatementProcessor.java
* Date:        9-28-1999
* Description: wraps the JDBC prepared statement execution process.
*
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* BUG00010				Saleem Shafi		6/9/00			Added singleton logic to update.
*
*/

public class PreparedStatementProcessor extends QueryProcessor implements IPreparedStatementProcessor
{
  PreparedStatement _preparedStatement = null;
  
  /** Constructor: 
  *   Use for explicit queries
  */
  public PreparedStatementProcessor(Connection conn, String strStatement)
    throws ORIOException
  {
    super(conn);
    try
  	{
      com.oculussoftware.service.log.LogService.getInstance().write("Preparing PSP: "+strStatement);
    	_preparedStatement = _jdtConn.prepareStatement(strStatement);
  	}
  	catch (SQLException sqlExp)
  	{
  		throw new ORIOException(sqlExp.toString());
  	}
  }//end constructor
  
  public PreparedStatement getStatement()
  {
    return (PreparedStatement) _preparedStatement;
  }
  	    	
  /**
  * Assemble and run the query
  */
  public int update() throws ORIOException
  {
    int numRows = 0;
    int numTries = 0;
    boolean bQuerySucceeded = false;
    while ( !bQuerySucceeded ) {
      try {
        numTries++;
//        com.oculussoftware.service.log.LogService.getInstance().write("Updating PSP.");
        numRows = ((PreparedStatement) _preparedStatement).executeUpdate();
				if (isSingleton() && numRows > 1)
					throw new ORIOException("An update to the database attempted to affect more than one row.");
        bQuerySucceeded = true;
      }//end try
      catch (SQLException ex) {
        if ( SQLState.isRetryable(ex) ) {
          if ( numTries >= QUERY_TRY_LIMIT ) {
            throw new ORIOException(ex.toString());
          }
          else {  // sleep for a while then fall thru to try again
            try {
              Thread.sleep(QUERY_WAIT_MILLIS);
            } catch (InterruptedException ignore) {}
          }
        } else {
           throw new ORIOException(ex.toString());
        }
      }//end catch
    } // end while retry loop
    return numRows;
  }//end retrieve
    

  public void setString(int col, String value)
    throws ORIOException
  {
    try
    {
//      com.oculussoftware.service.log.LogService.getInstance().write("Setting PSP String: "+value);
      getStatement().setString(col,value);
    }
    catch (SQLException sqlExp)
    {
      throw new ORIOException(sqlExp);
    }
  }


  public void setBinaryStream(int col, java.io.InputStream input, int length)
    throws ORIOException
  {
    try
    {
//      com.oculussoftware.service.log.LogService.getInstance().write("Setting PSP InputStream: "+input+", "+length);
      getStatement().setBinaryStream(col,input,length);
    }
    catch (SQLException sqlExp)
    {
      throw new ORIOException(sqlExp);
    }
  }
      
  public void setInt(int col, int value)
    throws ORIOException
  {
    try
    {
//      com.oculussoftware.service.log.LogService.getInstance().write("Setting PSP Int: "+value);
      getStatement().setInt(col,value);
    }
    catch (SQLException sqlExp)
    {
      throw new ORIOException(sqlExp);
    }
  }

  /**
  * Closes the JDBC result set and statement
  */
  public void close() throws ORIOException
  {
    super.close();
    try { if(_preparedStatement != null) _preparedStatement.close();} 
    catch (Exception ignore) {}
  }//end close
	
}