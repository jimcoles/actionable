package com.oculussoftware.rdb;

import com.oculussoftware.api.repi.*;

import java.util.*;
import java.sql.*;
import java.text.*;
	
/**
* Filename:    QueryProcessor.java
* Date:        8-11-1999
* Description: basic query processor to wrap the JDBC query execution process.
* SubClasses: CustomQueryProcessor.java
* Business object designers can also sub-class this class to create a data access layer 
* class that is in turn used by business objects.  Eventually, this class and its
* sub-classes should be used to hide the physical data storage from the business
* object layer.  
*
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.1
*/
public class QueryProcessor implements IQueryProcessor
{
  /*
* Change Activity
*
* Issue number    Programmer      Date        Description
* BUG00010				Saleem Shafi		6/9/00			Added singleton logic.
*
*/
	// class vars
	protected static final String sql_SEL 		= "SELECT";
	protected static final String sql_FROM 		= "FROM";
	protected static final String sql_WHERE 	= "WHERE";
	protected static final String sql_ORDERBY = "ORDER BY";
  protected static final String sql_GROUPBY = "GROUP BY";
  protected static final String sql_AS      = "AS";
  protected static final int QUERY_TRY_LIMIT    = 5;
  protected static final int QUERY_WAIT_MILLIS  = 500;
  //
	// instance vars and methods
	protected Connection 			_jdtConn 				= null;
  protected Statement       _queryStatement	= null;
  protected ResultSet       _queryResult    = null;
  protected String          _strQuery       = null;
	protected boolean					_singleton 			= true;
  
  /** Constructor: 
  *   Use for explicit queries
  */
  public QueryProcessor(Connection conn, String querystring)
  {
    _jdtConn = conn;
    _strQuery = querystring;
  }//end constructor
  
  /** Constructor: 
  *   Use for specifying queries later with setCompleteSQL()
  */
  public QueryProcessor(Connection conn) { _jdtConn = conn; }
  
  /**
  *
  */
  public synchronized int update(String strQuery) throws ORIOException
  {
  	setCompleteSQL(strQuery);
    return update();
  }//end update    
  
  /**
  *
  */	
  public synchronized int update() throws ORIOException
	{
		int numRows = 0;
    int numTries = 0;
    boolean bQuerySucceeded = false;
    
    while ( !bQuerySucceeded ) {
      try {
        numTries++;
        close();  // close resultset and statement just to be sure
        _queryStatement = _jdtConn.createStatement();
        String query = getCompleteSQL();
        if (query == null)
          throw new ORIOException("Attempt to execute null query.");
        com.oculussoftware.service.log.DatabaseLogService.getInstance().write(query);
        numRows = _queryStatement.executeUpdate(query);
				if (isSingleton() && numRows > 1)
					throw new ORIOException("An update to the database was trying to change "+numRows+" rows.");
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
	}//end update
  
  /**
  * Assemble and run the query
  */
  public synchronized IDataSet retrieve(String strQuery) throws ORIOException
  {
  	setCompleteSQL(strQuery);
    return retrieve();
  }//end retrieve
	
  
  /**
  * Assemble and run the query
  */
  public synchronized IDataSet retrieve() throws ORIOException
  {
    ResultSet retVal = null;
    int numTries = 0;
    boolean bQuerySucceeded = false;
    while ( !bQuerySucceeded ) {
      try {
        numTries++;
        close();  // close resultset and statement just to be sure
        _queryStatement = _jdtConn.createStatement();
        String query = getCompleteSQL();
        if (query == null)
          throw new ORIOException("Attempt to execute null query.");
        com.oculussoftware.service.log.DatabaseLogService.getInstance().write(query);
        _queryResult = _queryStatement.executeQuery(query);
        bQuerySucceeded = true;
        retVal = _queryResult;
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
    return new JDBCDataSet(retVal);
  }//end retrieve

  /**
  * Closes the JDBC result set and statement
  */
  public void close() throws ORIOException
  {
  	try
  	{
	    if(_queryResult != null) {
	      _queryResult.close();
	      _queryResult = null;
	    } 
	    if(_queryStatement != null) {
	      _queryStatement.close();
	      _queryStatement = null;
	    }
  	}
  	catch (SQLException ex)
  	{
  		throw new ORIOException(ex.toString());
  	}
  }//end close
  
  /**
  *
  */
  private ResultSet getResultSet() { return _queryResult; }
  
  /**
  *
  */
  private Statement getQueryStatement() { return _queryStatement; }
	
	/**
	*	Return the assembled SQL string
	*/
	public String getCompleteSQL() throws ORIOException
  { return (String) _strQuery; } 


  public IQueryProcessor setSingleton(boolean singleton)
  {
		_singleton = singleton;
		return this;
  }
  
  public boolean isSingleton()
  {
		return _singleton;
  }



	/**
	*
	*/
	public void setCompleteSQL(String strQuery) { _strQuery = strQuery; }
  
}