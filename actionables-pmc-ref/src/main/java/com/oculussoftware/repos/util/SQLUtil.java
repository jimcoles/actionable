package com.oculussoftware.repos.util;

/**
* Filename:    SQLUtil.java
* Date:        5-8-00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public final class SQLUtil
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
	/**
	*
	*/
	public static String primer(String str)
	{
		return fixSingleQuotes(str);
	}//end sqlPrimer
	
	/**
	*
	*/	
	public static String primer(String str, int length)
	{
		return fixSingleQuotes(trunc(str, length));
	}//end sqlPrimer
  
  /**
  *
  */
  private static String fixQuotes(String str)
  {
    if (str == null)
      return null;
    StringBuffer jdtSB = new StringBuffer(str);
    for(int i = jdtSB.length()-1; i >= 0; --i)
      if(jdtSB.charAt(i) == '"')
        jdtSB.insert(i, '\\');
      else if(jdtSB.charAt(i) == '\'')
        jdtSB.insert(i, '\'');
    return jdtSB.toString();
  }//end fixQuotes
  
  /**
  * fixDoubleQuotes takes a String and if it has a double quote in it, inserts a \
  */
  private static String fixDoubleQuotes(String str)
  {
    if (str == null)
      return null;
    StringBuffer jdtSB = new StringBuffer(str);
    for(int i = jdtSB.length()-1; i >= 0; --i)
      if(jdtSB.charAt(i) == '"')
        jdtSB.insert(i, '\\');
    return jdtSB.toString();
  }//end fixDoubleQuotes
	
	/**
	* fixSingleQuotes takes a String and if it has an apostrophe in it, inserts another
	* apostrophe.  It doubles all apostrophes.
	*/
	private static String fixSingleQuotes(String str)
	{
    if (str == null)
      return null;
		StringBuffer jdtSB = new StringBuffer(str);
		for(int i = jdtSB.length()-1; i >= 0; --i)
			if(jdtSB.charAt(i) == '\'')
				jdtSB.insert(i, '\'');
		return jdtSB.toString();
	}//end fixSingleQuotes
	
  /**
  * truncate a String to the desired length
  */
  private static String trunc(String str, int length)
  {
    if(str == null) str = "Error";
    else if(str.length() >= length) str = str.substring(0, length);
    return str;
  }//end trunc
}//end SQLUtil