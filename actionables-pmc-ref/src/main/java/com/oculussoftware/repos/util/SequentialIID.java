package com.oculussoftware.repos.util;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.*;

import java.util.*;

/** Generates an id 
*/
public class SequentialIID implements IIID, Comparable
{
  //---------------------------------
  // Private Class constants
  //---------------------------------
  private long _topId = 0;
    
  //---------------------------------
  // Private instance variables
  //---------------------------------
  private long _id = 0;
  private IRepository _repos = null;
  
  //---------------------------------
  // Public constructors
  //---------------------------------
  public SequentialIID(IRepository repos)
  {
    
  }
  
  public SequentialIID(long id)
  {
    _id = id;
  }
  
  //---------------------------------
  // Public instance methods
  //---------------------------------
  public long getLongValue()
  {
    return _id;
  }
    
  public int compareTo(Object other)
  {
    if (getLongValue() < ((SequentialIID)other).getLongValue())
      return -1;
    if (getLongValue() > ((SequentialIID)other).getLongValue())
      return 1;
    return 0;
 }
 
 public boolean equals(Object other)
 {
   if (!(other instanceof IIID))
     return false;
   return ((IIID)other).getLongValue() == getLongValue();
 }
 
 //override Object hashcode
 public int hashCode()
 {
   Long l = new Long(_id);
   return l.hashCode();
 }
  //---------------------------------
  // Private Class methods
  //---------------------------------
  public static synchronized long _genUID()
  {
    return 0;
  }
  
  static public IIID genIID()
	{
		Random rnd = new Random(System.currentTimeMillis());
		return new SequentialIID(rnd.nextLong());
	}    
  
  public String toString()
  {
    return ""+getLongValue();
  }
}