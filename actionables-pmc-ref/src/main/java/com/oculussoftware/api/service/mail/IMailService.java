package com.oculussoftware.api.service.mail;

import java.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.IUser;

/*
* $Workfile: IMailService.java $
* Description: The MailService.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* IMailService provides an easy interface for sending email messages.  
* The service is a singleton that spins off new threads for each email
* message.   
*
* @author Egan Royal
*/
public interface IMailService
{
  //----------------------------- Accessors ------------------------
  /**
  * This method is not currently being used.  It has been replaced by the
  * AlertThread.  
  * @return The sleeptime.
  */
  public long getSleepTime();
  
  /**
  * This method creates a new instance of a MailerThread that sends 
  * the email message.  This method filters email messages based on 
  * the recipient's user settings (EmailKind).
  * @param ek The EmailKind of the message.
  * @param to The To User.
  * @param from The From User.
  * @param message The body of the email message.
  */  
  public void sendMail(EmailKind ek, IUser to, IUser from, String message);  
  
  /**
  * This method creates a new instance of a MailerThread that sends 
  * the email message.  This method filters email messages based on 
  * the recipient's user settings (EmailKind).
  * @param ek The EmailKind of the message.
  * @param to The To User.
  * @param from The From User.
  * @param message The body of the email message.
  * @param subject The subject of the email message.
  */
  public void sendMail(EmailKind ek, IUser to, IUser from, String message, String subject);  
  
  /**
  * This method creates a new instance of a MailerThread that sends 
  * the email message.  This method does not filter email messages.
  * @param to The To User's email address.
  * @param to The From User's email address.
  * @param message The body of the email message.
  */
  public void sendMail(String to, String from, String message);  
  
  /**
  * This method creates a new instance of a MailerThread that sends 
  * the email message.  This method does not filter email messages.
  * @param to The To User's email address.
  * @param to The From User's email address.
  * @param message The body of the email message.
  * @param subject The subject of the email message.
  */
  public void sendMail(String to, String from, String message, String subject);  
  
  /**
  * This method creates a new instance of a MailerThread that sends 
  * the email message.  This method does not filter email messages.
  * @param to The To User's email address.
  * @param to The From User's email address.
  * @param message The body of the email message.
  * @param subject The subject of the email message.
  * @param contentType The content type of the email message.
  */
  public void sendMail(String to, String from, String message, String subject, String contentType);   

  //----------------------------- Mutators -------------------------
  
  /**
  * This method is not currently being used.  It has been replaced by the 
  * AlertThread.
  * @param time The sleep time
  */
  public void setSleepTime(long time);
  
  /**
  * This method is not currently being used. It has been replaced by the 
  * AlertThread. This method starts the MailService.
  */  
  public void startService();  
  
  /**
  * This method is not currently being used. It has been replaced by the 
  * AlertThread. This method stops the MailService.
  */
  public void stopService();  
}//end IMailService
