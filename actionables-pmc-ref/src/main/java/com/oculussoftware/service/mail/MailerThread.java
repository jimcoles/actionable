package com.oculussoftware.service.mail;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.api.service.*;
import com.oculussoftware.service.log.LogService;


/**
* Filename:    MailerThread.java
* Date:        4-6-00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class MailerThread extends Thread
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  private String         _to;
  private String         _from;
  private String         _message;
  private String         _subject;
	private String         _contentType;
  private IObjectContext _context;
  
	/**
	*
	*/
  public MailerThread(IObjectContext context, String to, String from, String message, String subject)
  {
	  this(context, to, from, message, subject, "text/text");
  }//    
	    
  /**
	*
	*/
  public MailerThread(IObjectContext context, String to, String from, String message, String subject, String contentType)
  {
	  _to = to;
	  _from = from;
	  _message = message;
	  _subject = subject;
	  _context = context;
	  _contentType = contentType;
	  start();
  }//    
	
	/**
	*
	*/
	public void run()
  {
	  try
	  {      
	    ICommConfig commconfig = (ICommConfig)_context.getCRM().getCompObject(_context,"CommConfig",IDCONST.COMMCONFIG.getIIDValue());
	    if (commconfig.isEnabled())
	    {
		    ISMTPClient emailclient = new SMTPClient(commconfig.getMailServer(),commconfig.getMailServerPort());
	      ISMTPMessage emailmessage = emailclient.createSMTPMessage();
	      emailmessage.setTo(_to);
	      emailmessage.setFrom(_from);
	      emailmessage.setSubject(_subject);
	      emailmessage.setBody(_message);
	      emailmessage.setContentType(_contentType);
	      emailclient.sendCompleteMessage(emailmessage);
	    }//end if
	  }//end try
	  catch(Throwable exc) { LogService.getInstance().write((Exception)exc);}
  }//end run    
}//end MailerThread
