package com.oculussoftware.ui;

import java.util.*;
import java.text.*;

/**
* Filename:     DateFormatter.java
* Date:       	3-16-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class DateFormatter
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  public static final DateFormat FORMATTER_LONG = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
  public static final DateFormat FORMATTER_SHORT = new java.text.SimpleDateFormat("yyyy-MM-dd");
  public static final DateFormat FORMATTER_LONGER = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
  public static final DateFormat FORMATTER_SHORTSLASHES = new java.text.SimpleDateFormat("yyyy/MM/dd");
  
  /**
  *
  */
  public static String format(String strDate)
  {
    if (strDate == null || strDate.equals("null") || strDate.length() < 10)
      return "";
    return strDate.substring(0,10);
  }
	
  /**
  *
  */
  public static String format(java.sql.Timestamp date)
  {
    String retVal = "";
    if (date != null)
      retVal = FORMATTER_SHORT.format(date);
    return retVal;
  }

  public static String formatLong(java.sql.Timestamp date)
  {
    return FORMATTER_LONG.format(date);
  }  
  
  public static long getDateLong(String date)
  {
    if (date != null && !date.equals(""))
    {
      date = date.trim();
      try
      {
        DateFormat formatter = null;
        if (date.length() > 10)
          formatter = FORMATTER_LONGER;
        else
        {
          if (date.indexOf("/") > -1)
            formatter = FORMATTER_SHORTSLASHES;
          else
            formatter = FORMATTER_SHORT;
        }
        Date d = formatter.parse(date);
        return d.getTime();
      }
      catch (Exception exp)
      {
        return -1;
      }
    }
    else
      return -1;
  }

  public static java.sql.Timestamp getDateTimestamp(String date)
  {
    long millis = getDateLong(date);
    if (millis == -1)
      return null;
    else
      return new java.sql.Timestamp(millis);
  }  


}//end class