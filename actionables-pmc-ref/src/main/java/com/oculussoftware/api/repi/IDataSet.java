package com.oculussoftware.api.repi;

import java.util.*;
import java.sql.*;
	
public interface IDataSet extends Map
{
  public IDataSet setIID(IIID iid)
    throws ORIOException;
    
  public IDataSet setIID(long iid)
    throws ORIOException;
    
  public IIID getIID()
    throws ORIOException;
  
  public boolean next()
    throws ORIOException;    
    
  public int getInt(String key)throws ORIOException; 
  public int getInt(int key)throws ORIOException; 
  public Clob getClob(String key)throws ORIOException; 
  public Clob getClob(int key)throws ORIOException; 
  public java.io.Reader getCharacterStream(String key)throws ORIOException; 
  public java.io.Reader getCharacterStream(int key)throws ORIOException; 
  public long getLong(String key)throws ORIOException; 
  public long getLong(int key)throws ORIOException; 
  public float getFloat(String key)throws ORIOException;
  public float getFloat(int key)throws ORIOException;
  public String getString(String key)throws ORIOException;
  public String getString(int key)throws ORIOException;
  public boolean getBoolean(String key)throws ORIOException;
  public boolean getBoolean(int key)throws ORIOException;
  public java.sql.Timestamp getTimestamp(String key)throws ORIOException;
  public java.sql.Timestamp getTimestamp(int key)throws ORIOException;
  public java.io.InputStream getBinaryStream(String key)throws ORIOException;
  public java.io.InputStream getBinaryStream(int key)throws ORIOException;
}