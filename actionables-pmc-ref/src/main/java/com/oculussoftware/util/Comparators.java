package com.oculussoftware.util;

/**
* Filename:    ArrayUtil.java
* Date:        5-19-00
* Description: Conveniece class with common Comparitors as nexted classes.
*
* Copyright 7-01-2000 productmarketing.com.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/
import java.util.*;

/**
* 
*/
public final class Comparators
{	
  public static final Comparator COMP_LONGS = new Longs();
  
  private static class Longs implements Comparator
  {
    // -1 ==> o1 is less than o2
    // =  equal
    // 1  ==> o1 is greater than o2
    public int compare(Object o1, Object o2)
    {
      int retVal = 0;
      long diff = ((Long) o1).longValue() - ((Long) o2).longValue();
      if(diff < 0)
        retVal = -1;
      else if(diff == 0)
        retVal = 0;
      else
        retVal = 1;  
      return retVal;
    }
  }
}
