package com.oculussoftware.util;

/**
* Filename:    ArrayUtil.java
* Date:        5-19-00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public final class ArrayUtil
{	
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  

  /**
  * Takes two arrays and returns an array with all of the 
  * longs that are in the first and not in the second 
  * this method does not guarantee the order of the results,
  * it will always put the -1 at the end of the array if needed.
  */
  public static long[] diff(long[] first, long[] second)
  {
    if(first == null) return null;
    long[] firstcopy = copy(first);
    if(second == null) return firstcopy;
    long[] secondcopy = copy(second);
    boolean found = false, blnNeg1 = hasNeg1(firstcopy,secondcopy);
    for(int i = 0; i < firstcopy.length; i++)
    {
      found = false;
      for(int j = 0; j < secondcopy.length; j++)
      {
        if(firstcopy[i] == secondcopy[j] && firstcopy[i] != -1)
        {
          found = true;
          secondcopy[j] = -1;
          //kill the rest of the duplicates
          for(int k = i+1; k < firstcopy.length; k++)
            if(firstcopy[i] == firstcopy[k] && firstcopy[k] != -1)
              firstcopy[k] = -1;
        }//end if
      }//end for
      if(found) firstcopy[i] = -1;
    }//end for
    return getResultsArray(firstcopy,blnNeg1);
  }//
  
  public static long[] noDuplicates(long[] array)
  {
    if(array == null) return null;
    long[] copy = copy(array);
    boolean hasNeg1 = hasNeg1(copy);
    for(int i = 0; i < copy.length; i++)
    {
      for(int j = i+1; j < copy.length; j++)
        if(copy[i] == copy[j] && copy[j] != -1)
          copy[j] = -1;
    }//end for
    return getResultsArray(copy,hasNeg1);
  }
  
  public static long[] copy(long array[])
  {
    if(array == null) return null;
    long[] results = new long[array.length];
    for(int i = 0; i < array.length; i++)
      results[i] = array[i];
    return results;
  }
  
  //these are private for a reason!
  //they do not deal with copies
  
  private static long[] getResultsArray(long[] array, boolean hasNeg1)
  {
    int newlength = numNonNeg1(array);
    if(hasNeg1) newlength++;
    long[] results = new long[newlength]; 
    int j = 0;
    for(int i = 0; i < array.length; i++)
    {
      if(array[i] != -1)
      {
        results[j] = array[i];
        j++;
      }//end if
    }//end for
    if(hasNeg1) results[results.length-1] = -1;
    return results;
  }
  
  private static int numNonNeg1(long[] array)
  {
    int num = 0;
    for(int i = 0; i < array.length; i++)
      if(array[i] != -1) num++;
    return num;
  }
  
  private static boolean hasNeg1(long[] array)
  {
    for(int i = 0; i < array.length; i++)
      if(array[i] == -1) return true;
    return false;
  }
  
  private static boolean hasNeg1(long[] first, long[] second)
  {
    return (hasNeg1(first) && !hasNeg1(second));
  }
}//