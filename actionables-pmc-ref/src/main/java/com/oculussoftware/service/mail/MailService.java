package com.oculussoftware.service.mail;

import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.service.log.LogService;
import com.oculussoftware.service.alert.AlertThread;
import com.oculussoftware.ui.SettingsMgr;

/**
* Filename:    MailService.java
* Date:        4-13-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class MailService implements IMailService
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  private static long _sleeptime = 10800000; // 3 hrs  
  private static Sleeper _sleeperthread; 
  private static MailService _mailservice;
  public static String URL = null;
	
  /**
  *
  */
  private MailService()
  {
	startService();
  }//  
  /**
  *
  */
  public static MailService getInstance()
  {
	if (_mailservice == null)
	  _mailservice = new MailService();
	return _mailservice;
  }//  
  
  public static void setURL(String strURL)
  {
    URL = strURL;
  }
  //----------------------------- Accessors ------------------------
  
  public long getSleepTime()
  { return _sleeptime; }  
  /**
  * package level
  */
  IObjectContext getSystemContext() throws Exception
  {
	IObjectContext context = new ObjectContext();
	ICRMConnection conn = context.getCRM().connect("system","system");//throws Exception?
	context.setConnection(conn);
	return context;
  }  
  /**
  *
  */
  private boolean isEnabled(EmailKind ek, IUser user)
  {
	boolean blnRV = false;
	SettingsMgr settings = new SettingsMgr(user);
	if(settings.getEmailEnabled())
	{
	  if(EmailKind.GENERAL.equals(ek))
		blnRV = settings.getEmailEnabled();
	  else if(EmailKind.VERSIONWORKFORCE.equals(ek))
		blnRV = settings.getEmailAddedtoVWF();
	  else if(EmailKind.CHANGEDPROFILE.equals(ek))
		blnRV = settings.getEmailChangedProfile();
	  else if(EmailKind.FEATURERETURNED.equals(ek))
		blnRV = settings.getEmailFeatureReturned();
	  else if(EmailKind.REVISIONINBOX.equals(ek))
		blnRV = settings.getEmailRevisionInbox();
	  else if(EmailKind.REVISIONASSIGNED.equals(ek))
		blnRV = settings.getEmailRevisionAssigned();
	  else if(EmailKind.REVISIONVERSION.equals(ek))
		blnRV = settings.getEmailRevisionVersion();
	  else if(EmailKind.UNACKED.equals(ek))
		blnRV = settings.getEmailUnacked();
	}//end if
	return blnRV;
  }  
  /**
  *
  */
  public void sendMail(EmailKind ek, IUser to, IUser from, String message)
  {
	try
	{
	  if(isEnabled(ek,to))
		sendMail(to.getEmailAddr(),from.getEmailAddr(),message);
	}
	catch(OculusException exc) { LogService.getInstance().write(exc); }
  }  
  /**
  *
  */
  public void sendMail(EmailKind ek, IUser to, IUser from, String message, String subject)
  {
	try
	{
	  if(isEnabled(ek,to))
		sendMail(to.getEmailAddr(),from.getEmailAddr(),message,subject);
	}
	catch(OculusException exc) { LogService.getInstance().write(exc); }
  }  
//  /**
//  *
//  */
//  public void sendAllMail() throws Exception
//  {
//    //get a system context
//    IObjectContext context = getSystemContext();
//    IUserColl allusers = (IUserColl)context.getCRM().getCompObject(context,"UserColl",IDCONST.USERCOLL.getIIDValue());
//    while(allusers.hasNext())
//    {
//      IUser user = allusers.nextUser();
//      INotificationColl messages = (INotificationColl)context.getCRM().getCompObject(context,"EmailNotificationColl",user.getIID(),true);
//      while(messages.hasNext())
//      {
//        INotification message = messages.nextNotification();
//        //spin off a new thread
//        new MailerThread(context,user.getEmailAddr(),user.getEmailAddr(),message.getSubject()+"\n\n"+message.getBody());
//        message.delete();
//      }//end while
//    }//end while
//  }//end sendAllMail
  
  /**
  *
  */
  public void sendMail(String to, String from, String message)
  {
	sendMail(to,from,message,"productmarketing.com Event Notifier");
  }  
  /**
  *
  */
  public void sendMail(String to, String from, String message, String subject)
  {
	try
	{
	  //get a system context
	  IObjectContext context = getSystemContext();
	  new MailerThread(context,to,from,message,subject); 
	}
	catch(Throwable exc) { LogService.getInstance().write(exc); }
  }  
  /**
  *
  */
  public void sendMail(String to, String from, String message, String subject, String contentType)
  {
	try
	{
	  //get a system context
	  IObjectContext context = getSystemContext();
	  new MailerThread(context,to,from,message,subject,contentType); 
	}
	catch(Throwable exc) { LogService.getInstance().write(exc); }
  }    
  //----------------------------- Mutators -------------------------

  public void setSleepTime(long time)
  { _sleeptime = time; }  
  //----------------------------- Functional Methods ---------------
  
  /**
  * start the thread
  */
  public void startService() 
  {
	if(_sleeperthread == null)
	  _sleeperthread = new Sleeper();
	if(_sleeperthread != null && !_sleeperthread.isAlive())
	   _sleeperthread.start();
  }//end start  
  /**
  * stop the thread
  */
  public void stopService() 
  {
	if(_sleeperthread != null && _sleeperthread.isAlive())
	  _sleeperthread = null;
  }//end stop  
}//end class MailService
