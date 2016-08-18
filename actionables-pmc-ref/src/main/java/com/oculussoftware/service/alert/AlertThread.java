package com.oculussoftware.service.alert;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.service.log.LogService;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.ui.SettingsMgr;
import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.service.mail.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.sql.Timestamp;

/**
* Filename:    AlertThread.java
* Date:        4-25-00
* Description: .
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class AlertThread extends Thread
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  private IObjectContext _context;
  
  public AlertThread(IObjectContext context)
  {
    _context = context;
    start();
  }//
  
	public void run()
  {
    try
    {    
      IUserColl users = (IUserColl)_context.getCRM().getCompObject(_context,"UserColl",IDCONST.USERCOLL.getIIDValue());
      checkUnacked(users);
      users.reset();
      checkFeatureReturned(users);
      
      IProductColl products = (IProductColl)_context.getCRM().getCompObject(_context,"ProductList",IDCONST.PRODUCTCOLL.getIIDValue());
      while(products.hasNext())
      {
        IProductVersionColl versions = products.nextProduct().getVersions();
        while(versions.hasNext())
        {
          IProductVersion ver = versions.nextProductVersion();
          TransactionMgr.getInstance().getTransaction(_context).startTransaction();
          IAlertConfigColl alerts = ver.getAlerts(true);
          while(alerts.hasNext())
          {
            IAlertConfig alert = alerts.nextAlertConfig();
            if(alert.getAlertType().equals(AlertType.RATIO_1) 
               || alert.getAlertType().equals(AlertType.RATIO_2))
              checkPriority(alert,ver);
            else if(alert.getAlertType().equals(AlertType.BASELINE_ADDED) 
                    || alert.getAlertType().equals(AlertType.BASELINE_REMOVED) 
                    || alert.getAlertType().equals(AlertType.BASELINE_EDITED))
              checkBaseline(alert,ver);
            else if(alert.getAlertType().equals(AlertType.NEGOTIATION))
              checkNegotiation(alert,ver);
              
            _context.getCRM().commitTransaction(_context);
          }//end while
        }//end while
      }//end while
      
    }//end try
    catch(Throwable exc) { LogService.getInstance().write(exc);}
  }//end run
  
  /**
  *
  */
  private void checkPriority(IAlertConfig alert, IProductVersion ver) throws OculusException
  {
    if(timeForEmail(alert))
    {
      float ratiohigh = Float.parseFloat(alert.getParamValue(AlertParamType.RATIO_HIGH));
      float ratiolow  = Float.parseFloat(alert.getParamValue(AlertParamType.RATIO_LOW));
      
      float highest = 0, high = 0, low = 0, lowest = 0;
      
      IFeatureColl features = (IFeatureColl)_context.getCRM().getCompObject(_context,"FeatureProductVersionColl",ver.getIID());
      while(features.hasNext())
      {
        IFeature feature = features.nextFeature();
        IFeatureCategoryLink featcat = feature.getFeatureCategoryLinkObject();
        if(featcat.getStateObject().getIID().equals(IDCONST.DEVCOMPLETE.getIIDValue()))
        {
          IIID enumiid = IDCONST.FEAT_PRIORITY_ENUM.getIIDValue();
          IREnumeration renum = (IREnumeration)_context.getCRM().getCompObject(_context,"Enumeration",enumiid);
          
          IREnumliteral featcatpriority = (IREnumliteral)_context.getCRM().getCompObject(_context,"Enumliteral",featcat.getPriorityIID()); 
          
          IRModelElementList priorities = renum.getEnumLiterals();
          int size = priorities.size();
          
          if(featcatpriority.getOrderNum() == size-1)
            highest++;
          else if(featcatpriority.getOrderNum() == size-2)
            high++;
          else if(featcatpriority.getOrderNum() == 1)
            lowest++;
          else if(featcatpriority.getOrderNum() == 2)
            low++;
        }//end if
      }//end while
      String strMsg = "";
      if(alert.getAlertType().equals(AlertType.RATIO_1))
      {
        if(highest/lowest > ratiohigh/ratiolow)
        {
          strMsg =
          "The Ratio of Highest to Lowest Priority Features complete is "+highest+":"+lowest+" exceeding the "+ratiohigh+":"+ratiolow+" threshold.";
        }//end if
      }//end if
      else if(alert.getAlertType().equals(AlertType.RATIO_2))
      {
        if((highest+high)/(lowest+low) > ratiohigh/ratiolow)
        {
          strMsg =
          "The Ratio of Highest and High to Lowest and Low Priority Features complete is "+(highest+high)+":"+(lowest+low)+" exceeding the "+ratiohigh+":"+ratiolow+" threshold.";
        }//end if
      }//end else if
      if(strMsg.equals(""))
        sendMail(alert,strMsg);
      alert.setLastNotifyDate(new Timestamp(System.currentTimeMillis()));
    }//end if
  }//
  
  /**
  *
  */
  private void checkBaseline(IAlertConfig alert, IProductVersion ver) throws OculusException
  {
	  String strMsg = "";
    if(timeForEmail(alert))
    {
      IRepository repos = _context.getRepository();
      IIID baselineiid = repos.makeReposID(Long.parseLong(alert.getParamValue(AlertParamType.BASELINE)));
      if(baselineiid != null)
      {
        int threshold = Integer.parseInt(alert.getParamValue(AlertParamType.THRESHOLD));
        IVersionBaseline baseline = (IVersionBaseline)_context.getCRM().getCompObject(_context,"VersionBaseline",baselineiid);
        IBusinessObjectColl coll = null; 
				IDataSet args = new DataSet();
				args.setIID(ver.getIID());
				args.put("FirstID",ver.getIID());
				args.put("SecondID",baseline.getIID());
				if(alert.getAlertType().equals(AlertType.BASELINE_ADDED))
        {
				  coll = (IBusinessObjectColl)_context.getCRM().getCompObject(_context,"FeatureVersionBaselineAddedColl",args);
          if (coll.size() > threshold)
		      {
					  strMsg = "There has been more than "+threshold+" features added since baseline "+baseline.getName()+".";
            sendMail(alert,strMsg);
		      }//end if
				}//end if
				else if(alert.getAlertType().equals(AlertType.BASELINE_REMOVED))
        {
				  coll = (IBusinessObjectColl)_context.getCRM().getCompObject(_context,"FeatureVersionBaselineRemovedColl",args);
          if (coll.size() > threshold)
		      {
					  strMsg = "There has been more than "+threshold+" features removed since baseline "+baseline.getName()+".";
            sendMail(alert,strMsg);
		      }//end if
        }//end else if
				else if(alert.getAlertType().equals(AlertType.BASELINE_EDITED))
        {
				  coll = (IBusinessObjectColl)_context.getCRM().getCompObject(_context,"FeatureRevisionEditedVersionBaselineList",args);
          if (coll.size() > threshold)
		      {
					  strMsg = "There has been more than "+threshold+" features edited since baseline "+baseline.getName()+".";
            sendMail(alert,strMsg);
		      }//end if
        }//end else if
      }//end if
    }//end if
  }//
  
  /**
  *
  */
  private void checkNegotiation(IAlertConfig alert, IProductVersion ver) throws OculusException
  {
    if(timeForEmail(alert))
    {
      int threshold = Integer.parseInt(alert.getParamValue(AlertParamType.THRESHOLD));
      IFeatureColl features = (IFeatureColl)_context.getCRM().getCompObject(_context,"FeatureProductVersionColl",ver.getIID());
      while(features.hasNext())
      {
        IFeature feature = features.nextFeature();
        IFeatureCategoryLink featcat = feature.getFeatureCategoryLinkObject();
        IUser mm = null;
				
				IDataSet args = new DataSet();
        args.setIID(featcat.getIID());
        args.put("roleiid",IDCONST.MKTMGRROLE.getIIDValue());
        IRoleAssignmentColl rac = (IRoleAssignmentColl)_context.getCRM().getCompObject(_context,"RoleAssignmentRoleColl",args);
        if(rac.hasNext())
          mm = rac.nextRoleAssignment().getUserObject();
				
				
        IUser em = null;
	      args = new DataSet();
        args.setIID(featcat.getIID());
        args.put("roleiid",IDCONST.ENGMGRROLE.getIIDValue());
        rac = (IRoleAssignmentColl)_context.getCRM().getCompObject(_context,"RoleAssignmentRoleColl",args);
        if(rac.hasNext())
          em = rac.nextRoleAssignment().getUserObject();
					
        IIID mmiid = mm.getIID();
        IIID emiid = em.getIID();
        int emCt = 0, mmCt = 0;
        IProcessChangeList transactions = (IProcessChangeList)_context.getCRM().getCompObject(_context,"ProcessChangeList",featcat.getIID());
        while(transactions.hasNext())
        {
          IProcessChange pc = transactions.nextProcessChange();
          if(pc.getFromUserIID().equals(mmiid))
            mmCt++;
          else if(pc.getFromUserIID().equals(emiid))
            emCt++;
        }//end while
        String strMsg = "";
        if(mmCt > threshold)
        {
          strMsg = "The Feature ("+feature.getName()+") has been through the Product Manager's Inbox ("+mm.getName()+") "+mmCt+" times.";
          sendMail(alert,strMsg);
        }
        if(emCt > threshold)
        {
          strMsg = "The Feature ("+feature.getName()+") has been through the Engineering Manager's Inbox ("+em.getName()+") "+emCt+" times.";
          sendMail(alert,strMsg);
        }
        alert.setLastNotifyDate(new Timestamp(System.currentTimeMillis()));
      }//end while
    }//end if
  }//
  
  /**
  *
  */
  private void sendMail(IAlertConfig alert, String strMsg) throws OculusException
  {
    IUserColl users = alert.getRecipientColl();
    while(users.hasNext())
    {
      IUser user = users.nextUser();
      MailService.getInstance().sendMail(EmailKind.GENERAL,user,user,strMsg);
    }//end while
  }
  
  /**
  *
  */
  private boolean timeForEmail(IAlertConfig alert) throws OculusException
  {
    return (System.currentTimeMillis() - alert.getLastNotifyDate().getTime() > 24*60*60*1000);
  }
  
  /**
  *
  */
  private void checkUnacked(IUserColl users) throws OculusException
  {
    while(users.hasNext())
    {
      IUser user = users.nextUser(); 
      SettingsMgr settings = new SettingsMgr(user);
      if(settings.getEmailEnabled() && settings.getEmailUnacked())
      {
        int unackeddays = settings.getEmailUnackedDays();
        long millidays = unackeddays*24*60*60*1000;
        long currenttime = System.currentTimeMillis();
        TransactionMgr.getInstance().getTransaction(_context).startTransaction();
        IInboxRowList list = (IInboxRowList)_context.getCRM().getCompObject(_context,"InboxRowList",user.getIID(),true);
        while(list.hasNext())
        {
          IInboxRow row = list.nextInboxRow();
          long sentdate = row.getDate().getTime();
          if(currenttime - millidays > sentdate && !row.isAcknowledged(AckKind.INBOX) && !row.isAcknowledged(AckKind.EMAIL_SENT))
          {
            String strMsg =
            "The Feature ("+row.getName()+") has been in your Inbox unacknowledged for "+unackeddays+" days.\n";
            if(MailService.URL != null)
              strMsg += MailService.URL+"ID="+row.getIID()+"\n";
            MailService.getInstance().sendMail(EmailKind.UNACKED,user,user,strMsg);
            row.acknowledge(AckKind.EMAIL_SENT);
            _context.getCRM().commitTransaction(_context);
          }//end if
        }//end while
        _context.getCRM().commitTransaction(_context);
      }//end if
    }//end while
  }//
  
  /**
  *
  */
  private void checkFeatureReturned(IUserColl users) throws OculusException
  {
    //hack...   redo this later
    IRState em = (IRState)_context.getCRM().getCompObject(_context,"State",IDCONST.ENGTOEM.getIIDValue());
    IRState mm = (IRState)_context.getCRM().getCompObject(_context,"State",IDCONST.EMTOMM.getIIDValue());
    IRState qm = (IRState)_context.getCRM().getCompObject(_context,"State",IDCONST.TESTTOQAM.getIIDValue());
  
    while(users.hasNext())
    {
      IUser user = users.nextUser(); 
      SettingsMgr settings = new SettingsMgr(user);
      if(settings.getEmailEnabled() && settings.getEmailFeatureReturned())
      {
        TransactionMgr.getInstance().getTransaction(_context).startTransaction();
        IInboxRowList list = (IInboxRowList)_context.getCRM().getCompObject(_context,"InboxRowList",user.getIID(),true);
        while(list.hasNext())
        {
          IInboxRow row = list.nextInboxRow();
          String name = row.getName();
          if(!row.isAcknowledged(AckKind.EMAIL_SENT) && (em.getName().equals(name) || mm.getName().equals(name) || qm.getName().equals(name)))
          {
            String strMsg =
            "The Feature ("+row.getName()+") has been returned to your Inbox.\n";
            if(MailService.URL != null)
              strMsg += MailService.URL+"ID="+row.getIID()+"\n";
            MailService.getInstance().sendMail(EmailKind.FEATURERETURNED,user,user,strMsg);
            row.acknowledge(AckKind.EMAIL_SENT);
          }//end if
        }//end while
        _context.getCRM().commitTransaction(_context);
      }//end if
    }//end while
  }//
}//end MailerThread