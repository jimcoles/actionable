package com.oculussoftware.util;

/**
I needed some kind of a placeholder for storing the string NAME and long ID associated
with our objects. This is ideal for showing lists of objects without having to load
the object partially/fully.
 
*/

import java.util.*;
public class Tuple
  {
    private String _s =null;
    private long _idx = -1;
    private long _eidx = -1;
    private long _classidx = -1;
    
    private long _ref = -1;
    private String _mode = null;
    private String _t = null;
    private int _configkind = 1; //Default is full (user added)
    private boolean _isreq; //Default is full (user added)
    
    public Tuple(){};
    
    //Used in manipulating ENUMERATION list'
    
    public Tuple(long idx, String s, int configkind, String mode) 
    {
      _s = s;
      _idx = idx;      
      _mode = mode;
      _configkind = configkind;      
    }        
    
    
    //Good for manipulating associations (Attribute-AttribuetGroup) 
    
    public Tuple(long sidx, long eidx, String s, String t) 
    {
      _s = s;
      _t = t;
      _idx = sidx;      
      _eidx = eidx;
      _mode = "Loaded";            
      
    }        
    
    
    
    //Good for manipulating association (Interface-Attribute)
    public Tuple(long sidx, long eidx,int ck) 
    {
      _configkind = ck;
      _idx = sidx;
      _eidx = eidx;
      _mode = "Loaded";            
      
    }     
    
    public Tuple(long sidx, long eidx) 
    {      
      _idx = sidx;
      _eidx = eidx;
      _mode = "Loaded";            
      
    }        
    
    public String getStartString()
    {
       return getString();
    }
    
    public String getEndString()
    {
       return _t;
    }
    
    public long getStartIndex()
    {
       return getIndex();
    }
    
    public long getEndIndex()
    {
       return _eidx;
    }
    
    
    public String getString() { return _s;}
    public long getClazz() { return _classidx;}
    public long getIndex() { return _idx;}    
    public void setString(String s) { _s = s;}
    public void setIndex(long index) { _idx = index;}
    public void setStartIndex(long index) { _idx = index;}
    public void setEndIndex(long index) { _eidx = index;}
    public void setStartString(String index) { _s = index;}
    public void setEndString(String index) { _t = index;}
    public String setMode(String s) { return _mode=s;}
    public String getMode() { return _mode;}
    public int getConfigKind() { return _configkind;}
    public boolean isRequired() { return _isreq;}
    public void setConfigKind(int k) { _configkind=k;}
    public long getRef() { return _ref;}
    public void setRef(long k) { _ref=k;}
    public void setClazz(long k) { _classidx=k;}
    public void isRequired(boolean b) { _isreq=b;}
    
    public boolean equals(Tuple tup)
    {
      
      if (tup.getStartIndex() == getStartIndex() && tup.getEndIndex() == getEndIndex() && tup.getMode().equals(getMode()))
        return true;
      else
        return false;  
    }
    
    
    static long[] getStartIndexList(List list)
    {
     
     if (list == null) return null;
      
     int size = list.size(); 
     long[] arr = new long[size];
     for(int i =0; i < size; ++i)
      {
        Tuple tup = (Tuple)list.get(i);
        arr[i] = tup.getStartIndex(); 
      }
      return arr;
    }
     
     public static int findTuple(List list, Tuple tup)
    {
      
     if (list == null) return -1; 
     long[] arr = Tuple.getStartIndexList(list);
     Arrays.sort(arr);
     return Arrays.binarySearch(arr,tup.getStartIndex());
      
    }
      
  }