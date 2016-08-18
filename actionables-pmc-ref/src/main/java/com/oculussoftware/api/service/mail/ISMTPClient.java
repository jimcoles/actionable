package com.oculussoftware.api.service.mail;

import java.util.*;

/*
* $Workfile: ISMTPClient.java $
* Description: An SMTP Client.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An SMTP Client.   
*
* @author Egan Royal
*/
public interface ISMTPClient
{
  /**
  * Connect to the SMTP Server. 
  * @param strHost The URL of the Mail Server to connect to.
  * @param intPort The port to connect to.
  * @exception java.lang.Exception
  */
	public void connect(String strHost, int intPort) throws Exception;
	
  /**
  * Sends the SMTP DATA message.
  * @exception java.lang.Exception;
  */
  public void data() throws Exception;
  
  /**
  * Sends the SMTP end of message marker \r\n.\r\n.
  * @exception java.lang.Exception;
  */
	public void endOfMessage() throws Exception;
  
  /**
  * Sends the SMTP HELO message.
  * @exception java.lang.Exception;
  */
	public void helo() throws Exception;
  
  /**
  * Sends the SMTP MAILFROM message.
  * @param strFrom The From address.
  * @exception java.lang.Exception;
  */
	public void mailFrom(String strFrom) throws Exception;
  
  /**
  * Sends the SMTP QUIT message.
  * @exception java.lang.Exception;
  */
	public void quit() throws Exception;
  
  /**
  * Sends the SMTP RCPTTO message.
  * @param jdtV A Vector of To addresses.
  * @exception java.lang.Exception;
  */
	public void rcptTo(Vector jdtV) throws Exception;
  
  /**
  * This method takes an ISMTPMessage and sends it.  This method does everything.
  * @param udtMsg The complete message.
  * @exception java.lang.Exception
  */
	public void sendCompleteMessage(ISMTPMessage udtMsg) throws Exception;
  
  /**
  * Sends only the body of the message.
  * @param udtMsg The message.
  * @exception java.lang.Exception;
  */
	public void sendMessage(ISMTPMessage udtMsg) throws Exception;
  
  /**
  * This method creates an empty instance of ISMTPMessage.
  * @return An empty ISMTPMessage
  */
  public ISMTPMessage createSMTPMessage();
}//end ISMTPClient