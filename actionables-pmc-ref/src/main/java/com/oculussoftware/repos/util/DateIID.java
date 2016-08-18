package com.oculussoftware.repos.util;

import com.oculussoftware.api.repi.IIID;

import java.util.*;

/** Generates a system unique long by shifting the current
*   date in millis left BIT_SHIFT bits then adding a continuously rolling number.
*   This algorithm provides protection for cases where UID generation
*   occurs within the same millisecond as in consecutive lines of code.
*/
public class DateIID implements IIID
{
  //---------------------------------
  // Private Class constants
  //---------------------------------
  private static final int  BIT_SHIFT  = 16;
  private static final int  LONG_BITS  = 64;
  private static final long ROLL_RANGE = (long) Math.pow(2, BIT_SHIFT);
  private static final long MAX_RIGHT = (long) Math.pow(2, (LONG_BITS - BIT_SHIFT)) - 1;
    
//  private static final int ROLL_LIMIT = 128;
  
  //---------------------------------
  // Private Class vars
  //---------------------------------
  private static int _offset = 0;
  
  //---------------------------------
  // Private instance variables
  //---------------------------------
  private long _id = 0;
  
  //---------------------------------
  // Public constructors
  //---------------------------------
  public DateIID( )
  {
    long time = System.currentTimeMillis();
    _id = _genFastUID(time);
  }
  
  public DateIID(long id)
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
  
  public String toString()
  {
    return Long.toString(_id);
  }
  
  //---------------------------------
  // Private Class methods
  //---------------------------------
  public static synchronized long _genFastUID(long time)
  {
    long guid = time;
    guid = guid << BIT_SHIFT;
    _offset = _offset + (int) (Math.random() * ROLL_RANGE);
//    _offset = (++_offset > ROLL_LIMIT) ? 0 : _offset;
    guid += _offset;
    return guid;
  }
}