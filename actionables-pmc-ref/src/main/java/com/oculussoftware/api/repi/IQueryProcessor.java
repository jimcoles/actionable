package com.oculussoftware.api.repi;

import java.util.*;
import java.sql.*;
import java.text.*;
	
public interface IQueryProcessor
{
  public int update(String strQuery)
  	throws ORIOException;

  public int update()
  	throws ORIOException;

  public IDataSet retrieve(String strQuery)
  	throws ORIOException;

  public IDataSet retrieve()
  	throws ORIOException;

  public void close()
  	throws ORIOException;

	public String getCompleteSQL()
		throws ORIOException;

	public IQueryProcessor setSingleton(boolean singleton);
	
	public boolean isSingleton();

	public void setCompleteSQL(String strQuery);
}