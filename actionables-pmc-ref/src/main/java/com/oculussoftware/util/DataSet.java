package com.oculussoftware.util;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;
import java.sql.*;
import java.util.*;
	
public class DataSet implements IDataSet
{
  private Map _map;
  private IIID _iid;
  
  public DataSet()
  {
    _map = new HashMap();
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
  
  public java.io.Reader getCharacterStream(String key)
    throws ORIOException
  {
    return (java.io.Reader)_map.get(key);
  }
  
  public Clob getClob(String key)
    throws ORIOException
  {
    return (Clob)_map.get(key);
  }
  
  public boolean next()
    throws ORIOException
  {
    return true;
  }
  
  public Object get(Object key)
  {
    return _map.get(key);
  }
  
  
  
  public boolean containsKey(Object key)
  {
    return _map.containsKey(key);
  }
  
  
  
  public void putAll(Map map) {}
  public Set keySet() { return null; }
  public Collection values() { return null; }
  public boolean containsValue(Object key) { return false; }
  public Object remove(Object key) { return this; }
  public Object put(Object key, Object value) { _map.put(key,value); return this; }
  public void clear() {}
  public Set entrySet() { return null; }
  public boolean isEmpty() { return false; }
  public int size() { return -1; }
 
  //----- Egan & Alok decided that for the repository classes it would be
  // best to use specific accessor methods as we know what data to expect.
  //Howevere, at the business object level we will use the generic get(Object) method
  
  public int getInt(String key)  throws ORIOException 
    { 
      return Integer.parseInt(_map.get(key).toString());
     }
  
  public long getLong(String key)  throws ORIOException 
    { 
      return Long.parseLong(_map.get(key).toString());
    }
  
  
  public String getString(String key)  throws ORIOException 
    { 
      return _map.get(key).toString();
     }
  
  
  public java.sql.Timestamp getTimestamp(String key)  throws ORIOException 
    { 
      return (Timestamp)_map.get(key);
     }
  
  
  public boolean getBoolean(String key)  throws ORIOException 
    { 
      return ((Boolean)_map.get(key)).booleanValue();
     }
  
  
  public java.io.InputStream getBinaryStream(String key)  throws ORIOException 
    { 
      return (java.io.InputStream)_map.get(key);
     }
     
   public float getFloat(String key)  throws ORIOException 
    { 
      return Float.parseFloat(_map.get(key).toString());
    }   
  
  // Overloads that take an integer key.  For this implementation,
  // throw exceptions because don't make sense.
  private static final String msg = "Integer keys not supported in this implementation of IDataSet.";
  
	public java.sql.Timestamp getTimestamp(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public String getString(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public long getLong(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public int getInt(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public float getFloat(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public Clob getClob(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public java.io.Reader getCharacterStream(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public boolean getBoolean(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }

	public java.io.InputStream getBinaryStream(int key) throws ORIOException
  { 
    throw new ORIOException(msg);
  }
}