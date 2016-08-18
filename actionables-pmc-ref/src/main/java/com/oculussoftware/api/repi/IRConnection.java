package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/**
* Filename:    IRConnection.java
* Date:        1/31/00
* Description: Defines the interface of a connection to a repository.  This interface
* is based on a database repository and is borrowed heavily from the java.sql.Connection
* interface.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*
*/


public interface IRConnection extends IPoolable
{
	/** closes the connection to the repository. */
	public void close()
		throws ORIOException;
	
	/** commits the changes to the repository */
	public void commit()
		throws ORIOException;
	
	/** rolls back the changes to the repository */
	public void rollback()
		throws ORIOException;

	/** returns an IQueryProcessor that uses this IRConnection object */
	public IQueryProcessor createProcessor()
		throws ORIOException;
	
	/** returns an IPreparedStatementProcessor that uses this IRConnection object */	
	public IPreparedStatementProcessor prepareProcessor(String sql)
		throws ORIOException;
	
}