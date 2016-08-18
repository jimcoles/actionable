package com.oculussoftware.service.mail;

import java.io.*;
import java.text.*;
import java.util.*;

import com.oculussoftware.api.service.mail.*;

/**
* Filename:    SMTPMessage.java
* Date:        8-11-1999
* Description: SMTPMessage builds an SMTP message.  The class contains
* two nested classes Header and Body which form the header
* and the body of the message.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class SMTPMessage implements ISMTPMessage
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */

	//private vars
	private Header udtH;
	private Body udtB;
	
	///////////////////////////////////////////////////
	/**
	* Header contains the SMTP header for the current message.
	* SMTPMessage has access to the private variables of this
	* class.
	*/
	public class Header
	{
		//private vars
		private Vector jdtVTo;
		private Vector jdtVCc;
		private String strSubject;
		private String strContentType;
		private String strFrom;
		private String strDate;
		
		/**
		* default constructor
		*/
		private Header()
		{
			jdtVTo = new Vector();
			jdtVCc = new Vector();
			strSubject = new String();
			strFrom = new String();
			strDate = new String();
		}//end Header	
	}//end class Header
	
	///////////////////////////////////////////////////
	/**
	* Body contains the SMTP body of the current message.
	* SMTPMessage has access to the private variables of this
	* class.
	*/
	public class Body
	{
		//private vars
		private String strBody;
		
		/**
		* default constructor
		*/
		private Body()
		{
			strBody = new String();
		}//end Body
	}//end class Body
	/**
	* default constructor
	*/
	public SMTPMessage() 
	{
		udtH = new Header();
		udtB = new Body();
	}//end SMTPMessage
	/**
	* getBody returns the body of the current message.
	* @return Body.strBody
	*/
	public String getBody()
	{
		return udtB.strBody;
	}//end getBody
	/**
	* getCc returns the Cc field of the current message header
	* @return Header.jdtVCc vector of Strings containing the Cc addresses
	*/
	public Vector getCc()
	{
		return udtH.jdtVCc;
	}//end getCc
	/**
	* getContentType returns the ContentType field of the current message header.
	* @return Header.strContentType
	*/
	public String getContentType()
	{
		return udtH.strContentType;
	}//end getFrom
	/**
	* getDate returns the date field of the current message header.
	* @return Header.strDate 
	*/
	public String getDate()
	{
		return udtH.strDate;
	}//end getDate
	/**
	* getFrom returns the from field of the current message header.
	* @return Header.strFrom
	*/
	public String getFrom()
	{
		return udtH.strFrom;
	}//end getFrom
	/**
	* getSubject returns the subject field of the current message header.
	* @return Header.strSubject
	*/
	public String getSubject()
	{
		return udtH.strSubject;	
	}//end getSubject
	/**
	* getTo returns the to field of the current message header.
	* @return Header.jdtVTo a Vector of Strings containing the to addresses
	*/
	public Vector getTo()
	{
		return udtH.jdtVTo;	
	}//end getTo
	/**
	* parse takes a String and returns a Vector.  The method uses a comma
	* as a delimiter.  Everything between the commas becomes an elememt 
	* in the vector
	*/
	private Vector parse(String str)
	{
		String strTemp = new String();
		Vector jdtVTemp = new Vector();
		int j = 0;
		str = str.toLowerCase();
		str = str.trim();
		for(int i = 0; i < str.length(); ++i)
		{
			if(str.charAt(i) == ',')
			{
				strTemp = str.substring(j, i);
				strTemp = strTemp.trim();
				j = i + 1;
				jdtVTemp.addElement(strTemp);
			}//end if
			else continue;
		}//end for	
		strTemp = str.substring(j, str.length());
		strTemp = strTemp.trim();
		jdtVTemp.addElement(strTemp);
		return jdtVTemp;
	}//end parse
	/**
	* setBody sets the body of the current message.
	* @param strBody the body of the message
	*/
	public ISMTPMessage setBody(String strBody)
	{
		udtB.strBody = strBody+"\n\n";
	return this;
	}//end setBody
	/**
	* setCc sets the carbon copy field of the current message.
	* @param strCc the Cc field
	*/
	public ISMTPMessage setCc(String strCc)
	{
		udtH.jdtVCc = parse(strCc);
	return this; 
	}
/**
* setContentType sets the ContentType field of the current message.
* @param strFrom the from field
*/
public ISMTPMessage setContentType(String contentType)
{
	udtH.strContentType = contentType;
	return this;
} //end setContentType
	/**
	* setDate sets the date for the current message.  The method uses the 
	* getDateTimeInstance of the DateFormat class.
	*/
	public ISMTPMessage setDate()
	{
		Date jdtD = new Date();
		DateFormat jdtDF = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);	
		udtH.strDate = jdtDF.format(jdtD);
	return this; 
	}//end setDate
	/**
	* setFrom sets the from field of the current message.
	* @param strFrom the from field
	*/
	public ISMTPMessage setFrom(String strFrom)
	{
		udtH.strFrom = strFrom;
	return this;
	}//end setFrom
	/**
	* setSubject sets the subject field of the current message.
	* @param strSubject the subject of the current message
	*/
	public ISMTPMessage setSubject(String strSubject)
	{
		udtH.strSubject = strSubject;
	return this;
	}//end setSubject
	/**
	* setTo sets the To field of the current message
	* setTo also calls setDate();
	*/
	public ISMTPMessage setTo(String strTo)
	{
		setDate();
		udtH.jdtVTo = parse(strTo);
	return this;
	}//end setTo
}//end SMTPMessage
