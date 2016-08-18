package com.oculussoftware.ui;

/**
* Filename:    HtmlFormatter.java
* Date:       	3-16-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class HtmlFormatter
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
	public final static String toHTML(String str)
  {
    if(str == null)
      return null;
		StringBuffer jdtSB = new StringBuffer(str);
		for(int i = jdtSB.length()-1; i >= 0; --i)
		{
			if(jdtSB.charAt(i) == '\n')
				jdtSB.replace(i, i+1, "<BR>");
			else if(i > 0 && jdtSB.charAt(i) == ' ' && jdtSB.charAt(i-1) == ' ')
				jdtSB.replace(i-1, i+1, "&nbsp; ");	
			else if(jdtSB.charAt(i) == '\t')
				jdtSB.replace(i, i+1, "&nbsp;&nbsp;&nbsp;");
		}//end for
		return jdtSB.toString();
	}//end toHTML
}//end class