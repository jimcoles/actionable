package com.oculussoftware.util;
/**
* Holds date utility methods used by application objects
* to ensure consistency, code reuse, etc.
*
* @author Jim Coles
*/
import java.text.*;
import java.util.*;
import java.sql.Timestamp;

public class DateUtil
{
  //----------------------------------------------------------------------
  // Static variables and such
  //----------------------------------------------------------------------
  public static final String PMC_SHORTDATEFORMAT = "yyyy-MM-dd";
//  public static final String PMC_LONGDATEFORMAT = "yyyy-MM-dd 'at' hh:mm a";
//  get standard date formatter ready for use
// private static final SimpleDateFormat _formatter1
//     = new SimpleDateFormat ("dd/MM/yyyy G 'at' hh:mm:ss a zzz");
// NOTE: MM is month, mm is minutes
  public static final DateFormat PMC_SHORTDATEFORMATTER
     = new SimpleDateFormat (PMC_SHORTDATEFORMAT);
//  public static final DateFormat PMC_LONGDATEFORMATTER
//     = new SimpleDateFormat (PMC_LONGDATEFORMAT);
  private static final DateFormat _defaultFormatter = PMC_SHORTDATEFORMATTER;
     

  //----------------------------------------------------------------------
  // Static public methods
  //----------------------------------------------------------------------
  /** Convert a string with of format mm-dd-yyyy to a java.lang.Date.getTime()
  */
  public static long toDateLong(String strDate, DateFormat formatter)
  {
    Date date = toDate(strDate, formatter);
    if ( date != null )
      return date.getTime();
    else
      return -1;
  }
  public static long toDateLong(String strDate)
  {
    return toDateLong(strDate, _defaultFormatter);
  }
  
  public static Date toDate(String strDate, DateFormat formatter)
  {
    Date date = null;
    if (strDate != null && !strDate.equals(""))
    {
      ParsePosition pos = new ParsePosition(0);
      date = formatter.parse(strDate, pos);
    }
    return date;
  }
  /** Convert a string with of format mm-dd-yyyy to a java.lang.Date
  */
  public static Date toDate(String strDate)
  {
    return toDate(strDate, _defaultFormatter);
  }
  
  public static Timestamp toTimestamp(String strDate, DateFormat formatter)
  {
    Timestamp retVal = null;
    Date date = toDate(strDate, formatter);
    if ( date != null )
      retVal = new Timestamp(date.getTime());
    return retVal;
	}
  public static Timestamp toTimestamp(String strDate)
  {
    return toTimestamp(strDate, _defaultFormatter);
  }
	
	/**
	*
	*/
	public static String toDateString(Timestamp ts, DateFormat formatter) 
	{
    String retVal = "";
		if(ts != null) {
			retVal = formatter.format(ts); 
    }
    return retVal;  
	}
	public static String toDateString(Timestamp ts) 
	{
    return toDateString(ts, _defaultFormatter);
  }
	
	/**
	*
	*/
	public static String toDateString(long lngDate, DateFormat formatter) 
	{ 
		return toDateString(new Timestamp(lngDate), formatter); 
	}
  
	public static String toDateString(long lngDate) 
	{ 
		return toDateString(new Timestamp(lngDate)); 
	}
  
  public static Timestamp truncToDayStart(Timestamp timestamp)
  {
    return toTimestamp(toDateString(timestamp, PMC_SHORTDATEFORMATTER), PMC_SHORTDATEFORMATTER);
  }
  
  public static Timestamp add(Timestamp ts, int field, int amount)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(ts);
    cal.add(field, amount);
    Timestamp ts2 = new Timestamp(cal.getTime().getTime());
    return ts2;
  }
  
}