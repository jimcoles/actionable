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

//------------------------------------------------------------------
//------------------------------- Sleeper --------------------------
//------------------------------------------------------------------

/**
* 
*/  
final class Sleeper extends Thread
{   
  /**
  *
  */
  public Sleeper()
  { super(); }  
  /**
  * 
  */ 
  public void run()
  {
	while(true)
	{
//      try 
//      { 
//        //send the mail
//        MailService.getInstance().sendAllMail();
//      }//end try
//      catch(Throwable exc) { LogService.getInstance().write(exc); }
	  try
	  {
		//start the alert thread
		new AlertThread(MailService.getInstance().getSystemContext());
	  }//end try
	  catch(Throwable exc) { LogService.getInstance().write(exc); }
	  try 
	  { 
		//sleep
		sleep(MailService.getInstance().getSleepTime()); 
	  }//end try
	  catch(InterruptedException ignored) {}         
	}//end while
  }//end run  
}//Sleeper
