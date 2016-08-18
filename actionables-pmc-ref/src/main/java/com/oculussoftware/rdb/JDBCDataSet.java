package com.oculussoftware.rdb;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;
import java.sql.*;
import java.util.*;
	
public class JDBCDataSet implements IDataSet
{
  private ResultSet _rs;
  private IIID _iid;
  
  public JDBCDataSet(ResultSet rs)
  {
    _rs = rs;
 
  }
  
  public IDataSet setIID(IIID iid)
    throws ORIOException
  {
    _iid = iid;
    return this;
  }
    
  public IDataSet setIID(long iid)
    throws ORIOException
  {
    _iid = new SequentialIID(iid);
    return this;
  }
    
  public IIID getIID()
    throws ORIOException
  {
    return _iid;
  }
  
  public boolean next()
    throws ORIOException
  {
    try
    {
      return _rs.next();
    }
    catch (SQLException sqlExp)
    {
      throw new ORIOException(sqlExp);
    }
  }
  
  public Object get(Object key)
  {
    try
    {
      return _rs.getObject((String)key);
    }
    catch (SQLException sqlExp)
    {
      throw new NullPointerException("Database access error."+sqlExp);
    }
  }
  
  
  
  public boolean containsKey(Object key)
  {
    try
    {
      return _rs.findColumn((String)key) >= 0;
    }
    catch (SQLException sqlExp)
    {
      return false;
    }
  }
  
  
  
  public void putAll(Map map) {}
  public Set keySet() { return null; }
  public Collection values() { return null; }
  public boolean containsValue(Object key) { return false; }
  public Object remove(Object key) { return this; }
  public Object put(Object key, Object value) { return this; }
  public void clear() {}
  public Set entrySet() { return null; }
  public boolean isEmpty() { return false; }
  public int size() { return -1; }
 
  //----- Egan & Alok decided that for the repository classes it would be
  // best to use specific accessor methods as we know what data to expect.
  //Howevere, at the business object level we will use the generic get(Object) method
  
  public int getInt(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getInt(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public int getInt(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getInt(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  
  public long getLong(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getLong(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public long getLong(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getLong(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  
   public java.sql.Clob getClob(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getClob(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
  
   public java.sql.Clob getClob(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getClob(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
   public java.io.Reader getCharacterStream(String key)  throws ORIOException 
    { 
      try
      {
      return (java.io.BufferedReader)_rs.getCharacterStream(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
   public java.io.Reader getCharacterStream(int key)  throws ORIOException 
    { 
      try
      {
      return (java.io.BufferedReader)_rs.getCharacterStream(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
  
  
  public String getString(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getString(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public String getString(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getString(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  
  
  public java.sql.Timestamp getTimestamp(String key)  throws ORIOException 
    { 
      try
      {
        String time = _rs.getString(key);
        return com.oculussoftware.ui.DateFormatter.getDateTimestamp(time);
//      return _rs.getTimestamp(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public java.sql.Timestamp getTimestamp(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getTimestamp(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  
  
  public boolean getBoolean(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getBoolean(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public boolean getBoolean(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getBoolean(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  
  public java.io.InputStream getBinaryStream(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getBinaryStream(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
  public java.io.InputStream getBinaryStream(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getBinaryStream(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }
     
   public float getFloat(String key)  throws ORIOException 
    { 
      try
      {
      return _rs.getFloat(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
   public float getFloat(int key)  throws ORIOException 
    { 
      try
      {
      return _rs.getFloat(key);
      }
      catch(SQLException ex)
      { throw new ORIOException(ex);
      }
     }   
}