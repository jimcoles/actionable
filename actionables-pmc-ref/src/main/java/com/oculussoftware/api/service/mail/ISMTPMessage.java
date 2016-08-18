package com.oculussoftware.api.service.mail;

import java.util.*;

/*
* $Workfile: ISMTPMessage.java $
* Description: An SMTP Message.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An SMTP Message.   
*
* @author Egan Royal
*/
public interface ISMTPMessage
{
	
  //----------------------- Accessors ------------------------------
  /**
  * This accessor method returns the body of the message.
  * @return The body.
  */
  public String getBody();  
  
  /**
  * This accessor method returns the Vector of CCs.
  * @return The Vector of CCs addresses.
  */
  public Vector getCc();  
  
  /**
  * This accessor method returns the content type of the message.
  * @return The content type.
  */
	public String getContentType(); 
  
  /**
  * This accessor method returns the date of the message.
  * @return Tha date.
  */
  public String getDate();
  
  /**
  * This accessor method returns the From address of the message.
  * @return The from address.
  */  
  public String getFrom();
  
  /**
  * This accessor method returns the Subject of the message.
  * @return The subject.
  */  
  public String getSubject();  
  
  /**
  * This accessor method returns a Vector of To addresses.
  * @return The Vector of To addresses.
  */
  public Vector getTo();  
  //----------------------- Mutators -------------------------------
  /**
  * This mutator method sets the body of the message.
  * @param strBody The body.
  * @return this
  */
  public ISMTPMessage setBody(String strBody);
  
  /**
  * This mutator method takes a comma separated list of email 
  * addresses and builds the Vector of CCs.
  * @param strCc A comma serarated list of email addresses. 
  * @return this
  */  
  public ISMTPMessage setCc(String strCc);    
  
  /**
  * This mutator method sets the content type of the message.
  * @param contentType The content type of the message.
  * @return this
  */
  public ISMTPMessage setContentType(String contentType); 
  
  /**
  * This mutator method sets the date of the message to the current date.
  * @return this
  */ 
  public ISMTPMessage setDate();
  
  /**
  * This mutator method sets the From address of the message.
  * @param strFrom The From address.
  * @return this
  */  
  public ISMTPMessage setFrom(String strFrom);
  
  /**
  * This mutator method sets the subject of the message.
  * @param strSubject The subject.
  */  
  public ISMTPMessage setSubject(String strSubject); 
  
  /**
  * This mutator method takes a comma separated String of email addresses
  * and builds a Vector of To addresses.
  * @param strTo The To addresses.
  */ 
  public ISMTPMessage setTo(String strTo);  
}//end ISMTPMessage
