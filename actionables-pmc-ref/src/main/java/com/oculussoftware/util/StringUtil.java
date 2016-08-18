package com.oculussoftware.util;

import java.util.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;

/**
* Filename:    StringUtil.java
* Date:        8-11-1999
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.1
*/
public class StringUtil
{
	/*
	* Change Activity
	*
	* Issue number    Programmer      Date        Description
	*
	*/
	
	//
	private static final String COMMA = ",";
	
  public static String buildCommaDelList(Collection items, Lister lister)
  {
    if (items!=null)
      return _buildCommaDelList(items.iterator(), items.size(), lister);
    else
      return "";  
  }
  /** buildCommaDelList() builds a comma delimited list
  from an Vector of Objects.
  */
  public static String buildCommaDelList(Collection items)
  {
    if (items!=null)
      return _buildCommaDelList(items.iterator(), items.size(), ObjectToStringLister.INSTANCE);
    else
      return "";  
  }
  
  private static String _buildCommaDelList(Iterator items, int size, Lister lister)
  {
    StringBuffer retVal = new StringBuffer();
    int count = 0;
    Object obj = null;
    if (items != null) {
      while (items.hasNext()) {
        obj = items.next();
        count++;
        if (obj != null) {
          retVal.append(lister.getListString(obj));
          if ( count < size ) {
            retVal.append(COMMA + " ");
          }
        }
      }
    }
    return retVal.toString();
  }

  /**
  * renamed for my sanity
  */
  public static String buildCommaDelList(long[] items)
  { return getArray2CSV(items); }

  public static String getArray2CSV(long[] _assocs)
  {
    StringBuffer buffer = new StringBuffer();
    for(int i = 0; i < _assocs.length; i++)
      buffer = buffer.append(_assocs[i]+(i==_assocs.length-1?"":","));    
    return buffer.toString();
  }

  /** trimRightSide() trims the input string starting at the rightmost occurence of the
  *   specified substring.
  */
  public static String trimRightSide(String input, String sub)
  {
    String retString = null;
    if (input != null) {
      int lastIdx = input.lastIndexOf(sub);
      if (lastIdx != -1 )
        retString = input.substring(0, lastIdx );
      else
        retString = input;
    }
    return retString;
  }

  /** Returns everything to the right of the specified string.
  */
  public static String tailOfString(String input, String sub)
  {
    String retString = null;
    if (input != null) {
      int lastIdx = input.indexOf(sub);
      if (lastIdx != -1 )
        retString = input.substring(lastIdx + sub.length());
      else
        retString = input;
    }
    return retString;
  }

  /**
  * takes a comma delimited list of longs (String) and returns the last long
  */
  public static long getLastLong(String strIDs) throws NumberFormatException
  {
    return getLastLong(strIDs,',');
  }//end getLastLong

  public static long getLastLong(String strIDs, char delim) throws NumberFormatException
  {
    long lngID = -1;
    if(strIDs == null) return lngID;
    if(strIDs.equals("")) return lngID;
    if(strIDs.lastIndexOf(delim) != -1)
      lngID = Long.parseLong(strIDs.substring(strIDs.lastIndexOf(delim)+1, strIDs.length()));
    else
      lngID = Long.parseLong(strIDs);
    return lngID;
  }//end getLastLong

  /**
  * takes a comma separated list of longs and returns an array of longs
  * this method at this point assumes correctly formatted input
  */
  public static long[] getLongArray(String strIDs) throws NumberFormatException
  {
    return getLongArray(strIDs,',');
  }//end getLongArray

  public static long[] getLongArray(String strIDs, char delim) throws NumberFormatException
  {
    if(strIDs == null) return new long[0];
    if(strIDs.equals("")) return new long[0];
    int intCount = 1;//start at 1 because I need a count of IDs not commas
    for(int i = strIDs.length()-1; i >= 0; --i)
      if(strIDs.charAt(i) == delim)
        intCount++; //count the commas to get the number of IDs in the list
    long[] lngIDs = new long[intCount]; int j = 0;
    for(int i = lngIDs.length-1; i >= 0 ; --i)
    {
      j = strIDs.lastIndexOf(delim);//find the last comma
      if(j != -1)
      {
        lngIDs[i] = Long.parseLong(strIDs.substring(j+1,strIDs.length()));//get the long
        strIDs = strIDs.substring(0,j);//truncate the string
      }//end if
      else
        lngIDs[i] = Long.parseLong(strIDs);//get the first long
    }//end for
    return lngIDs;
  }//end getLongArray

  public static String[] getStringArray(String strIDs) throws NumberFormatException
  {
    return getStringArray(strIDs,',');
  }//end getStringArray

  public static String[] getStringArray(String strIDs, char delim) throws NumberFormatException
  {
    if(strIDs == null) return new String[0];
    if(strIDs.equals("")) return new String[0];
    int intCount = 1;//start at 1 because I need a count of IDs not commas
    for(int i = strIDs.length()-1; i >= 0; --i)
      if(strIDs.charAt(i) == delim)
        intCount++; //count the commas to get the number of IDs in the list
    String[] IDs = new String[intCount]; int j = 0;
    for(int i = IDs.length-1; i >= 0 ; --i)
    {
      j = strIDs.lastIndexOf(delim);//find the last comma
      if(j != -1)
      {
        IDs[i] = strIDs.substring(j+1,strIDs.length());
        strIDs = strIDs.substring(0,j);//truncate the string
      }//end if
      else
        IDs[i] = strIDs;//get the first
    }//end for
    return IDs;
  }//end getStringArray

  //Alok added this method. Don't want to notify the same user twice 
  public static long[] getNoDuplicateLongArray(String strIDs) throws NumberFormatException
  {
    if(strIDs == null) return null;
    long[] lngIDs = getLongArray(strIDs);
    
    HashMap map = new HashMap();    
    int size = lngIDs.length;        
    for(int i=0; i < size ; ++i)
    {
      Long lo = new Long(lngIDs[i]);
      map.put(lo,lo);
      lo = null;
    }
    size = map.size();    
    long[] lngID1s = new long[size];
    int j = 0;
    for (Iterator mkeys = map.keySet().iterator(); mkeys.hasNext();)
          {
            Long key = (Long) mkeys.next();
            lngID1s[j]=key.longValue();
            ++j;
          } 
    map = null;      
    return lngID1s;
  }//end getLongArray

  static public IIID getIID(String s) 
  {
    IIID id = null;
    if (s != null)
    {
      long lo = Long.parseLong(s);
      return new SequentialIID(lo);
    }
    else
      return null;
  }

  static public String replaceDashByUnderScore(String s) 
  {
    StringBuffer sbf = new StringBuffer();
    char[] carr = s.toCharArray();
    int len = carr.length;
    for(int i =0; i < len; ++i)
      {
        if (carr[i] == '-') 
          sbf.append('_');
        else  
          sbf.append(carr[i]);
      }
    return sbf.toString();  
  }

  static public boolean isValidIntOrLong(String s) 
  {          
      if (!isNumeric(s)) 
        return false;      
      else
    {
      if(s.indexOf('.') < 0) 
        return true;
      else
        return false;  
    }
    
  }

  static public boolean isValidFloat(String s) 
  {
    return isNumeric(s);
  }

  static public boolean isNumeric(String s) 
  {
    if (s == null || s.equals("") || s.equals("null") || s.startsWith(" ")) 
      return false;
      
    char[] carr = s.toCharArray();
    int size = carr.length;
    int k =0;
    
    
    for(int i =0; i < size; ++i)
    {      
     
     if (!Character.isDigit(carr[i])) 
      {
        if (i == 0 && carr[i]=='-') continue;
                  
        if (carr[i] == '.') 
        {
          ++k;
          if (k > 1) return false;
          else
            continue;
        }
        else
        return false;
      }
     else
       continue;  
     
    }
   return true; 
    }

 public static boolean isPrintable(String s)
  {
    
    if (s == null || s.equals("") || s.equals("null") || s.equals("")) 
      return false;
    else 
      return true;
  }

  /**
  * fixDoubleQuotes takes a String and if it has a double quote in it, inserts a \
  */
  public static String escapeQuotes(String str)
  {
    if (str == null)
      return null;
    StringBuffer jdtSB = new StringBuffer(str);
    for(int i = jdtSB.length()-1; i >= 0; --i)
      if(jdtSB.charAt(i) == '\'' || jdtSB.charAt(i) == '\"')
        jdtSB.insert(i, '\\');
    return jdtSB.toString();
  }//end fixDoubleQuotes

  /**
  * same thing only ints
  */
  public static int[] getIntArray(String strIDs) throws NumberFormatException
  {
    int intCount = 1;//start at 1 because I need a count of IDs not commas
    for(int i = strIDs.length()-1; i >= 0; --i)
      if(strIDs.charAt(i) == ',')
        intCount++; //count the commas to get the number of IDs in the list
    int[] intIDs = new int[intCount]; int j = 0;
    for(int i = intIDs.length-1; i >= 0 ; --i)
    {
      j = strIDs.lastIndexOf(',');//find the last comma
      if(j != -1)
      {
        intIDs[i] = Integer.parseInt(strIDs.substring(j+1,strIDs.length()));//get the long
        strIDs = strIDs.substring(0,j);//truncate the string
      }//end if
      else
        intIDs[i] = Integer.parseInt(strIDs);//get the first long
    }//end for
    return intIDs;
  }//end getIntArray    

  /*****
  Our calendar date-textbox for some reason escapes form validation when a user puts in
  more than one leading spaces in the textbox. This method is a fail safe way of ensuring
  that the date string entered is not corrupt for processing.  
  **/  
  static public String removeLeadingSpaces(String s)
  {
    if (s == null) return null;
    return s.trim();
//    
//   char arr[] = s.toCharArray();
//   int jj = arr.length;
//   StringBuffer sbf = new StringBuffer();
//   for(int i =0; i < jj; ++i)    
//     if (arr[i] != ' ') 
//       sbf.append(arr[i]);
//    return sbf.toString();
  }

  /**
  * takes a comma separated list of longs and returns a list of Longs
  * this method at this point assumes correctly formatted input
  */
  public static List getList(String strIDs) throws NumberFormatException
  {
    long[] lng =  getLongArray(strIDs,',');
    Vector v = new Vector();
    for (int i=0; i<lng.length; i++)
    {
      v.addElement(new Long(lng[i]));
    }
    return v;
  }//end getLongArray
    
    
     public static String getDate(String str) throws NumberFormatException
  {
     return str.substring(0,10);     
  }//end getLongArray
  
  public static boolean isValidDate(String strDate)
  {
    if(com.oculussoftware.ui.DateFormatter.getDateTimestamp(strDate) == null)
      return false;
    return true;  
  }
}