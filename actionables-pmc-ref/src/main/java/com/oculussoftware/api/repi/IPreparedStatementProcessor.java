package com.oculussoftware.api.repi;

import java.util.*;
import java.sql.*;
import java.text.*;
	
public interface IPreparedStatementProcessor extends IQueryProcessor
{
	public PreparedStatement getStatement();
  
  public void setString(int col, String value)
    throws ORIOException;
    
  public void setBinaryStream(int col, java.io.InputStream input, int lenght)
    throws ORIOException;
  
  public void setInt(int col, int value)
    throws ORIOException;
}