package com.oculussoftware.ui;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.service.log.LogService;

import java.sql.*;
import java.io.*;
import java.util.*;

/**
* Filename:    SettingsMgr.java
* Date:       	3-16-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class SettingsMgr implements Serializable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  private IUser _user;
  // OneSpec filters
  private boolean _active   = true,
                  _complete = true,
                  _notcomplete = true,
                  _review   = false,
                  _potfeat  = false,
                  _mycept   = false,
                  _baseline = false,
                  _archived = false,
                  _inputs   = true,
                  _ps       = true,
                  _forms    = true,  
                  _open     = true,
                  _closed   = false;
  private long _priority = 0;
  private long _state = 0;
  private String _type = "All";
                  
  private Timestamp _before = null,
                    _after  = null;
                      
  //Inbox filters
  private boolean _wf = true,
                  _cc = true,
                  _dt = true,
                  _mi = true;
                  
  //ReviewBoard filters
  private boolean _approval_accepted = true,
                  _approval_rejected = true,
                  _approval_neither  = true,
                  _state_review      = true,
                  _state_accepted    = true;
                  
  //Email
  private boolean _email_enabled     = false,
                  _added_to_vwf      = true,
                  _changed_profile   = true,
                  _feat_returned     = true,
                  _rev_inbox         = true,
                  _rev_assigned_feat = true,
                  _unacked           = true,
                  _rev_ver           = true;
  private int     _unacked_days      = 4;
  
  //Paging market inputs
  private int    _num_inputs  = 10;
  
  public SettingsMgr(IUser user)
  {
    _user = user;
    loadSettings();
  }
  
  public IUser getUserObject() { return _user; }
  
  //OneSpec
  public void setActive(boolean set) { _active = set; }
  public void setComplete(boolean set) { _complete = set; }
  public void setNotComplete(boolean set) { _notcomplete = set; }
  public void setReview(boolean set) { _review = set; }
  public void setPotFeat(boolean set) { _potfeat = set; }
  public void setMyCept(boolean set) { _mycept = set; }
  public void setBaseline(boolean set) { _baseline = set; }
  public void setArchived(boolean set) { _archived = set; }
  public void setInputs(boolean set) { _inputs = set; }
  public void setPS(boolean set) { _ps = set; }
  public void setForms(boolean set) { _forms = set; }
  public void setOpen(boolean set) { _open = set; }
  public void setClosed(boolean set) { _closed = set; }
  public void setInputNums(int set) { _num_inputs = set; }
  public void setBefore(Timestamp set) { _before = set; }
  public void setAfter(Timestamp set) { _after = set; }
  public void setPriority(long priority) { _priority = priority; }
  public void setState(long state) { _state = state; }
  public void setFeatureType(String type) { _type = type; }
  //Inbox
  public void setInboxWF(boolean set) { _wf = set; }
  public void setInboxCC(boolean set) { _cc = set; }
  public void setInboxDT(boolean set) { _dt = set; }
  public void setInboxMI(boolean set) { _mi = set; }
  //ReviewBoard
  public void setRBApprovalAccepted(boolean set) { _approval_accepted = set; }
  public void setRBApprovalRejected(boolean set) { _approval_rejected = set; }
  public void setRBApprovalNeither(boolean set)  { _approval_neither = set; }
  public void setRBStateReview(boolean set)      { _state_review = set; }
  public void setRBStateAccepted(boolean set)    { _state_accepted = set; }
  //Email
  public void setEmailEnabled(boolean set)          { _email_enabled = set; }
  public void setEmailAddedtoVWF(boolean set)       { _added_to_vwf = set; }
  public void setEmailChangedProfile(boolean set)   { _changed_profile = set; }
  public void setEmailFeatureReturned(boolean set)  { _feat_returned = set; }
  public void setEmailRevisionInbox(boolean set)    { _rev_inbox = set; }
  public void setEmailRevisionAssigned(boolean set) { _rev_assigned_feat = set; }
  public void setEmailRevisionVersion(boolean set)  { _rev_ver = set; }
  public void setEmailUnacked(boolean set)          { _unacked = set; }
  public void setEmailUnackedDays(int set)          { _unacked_days = set; }
  
  
  //OneSpec
  public boolean getActive() { return _active; }
  public boolean getComplete() { return _complete; }
  public boolean getNotComplete() { return _notcomplete; }
  public boolean getReview() { return _review; }
  public boolean getPotFeat() { return _potfeat; }
  public boolean getMyCept() { return _mycept; }
  public boolean getBaseline() { return _baseline; }
  public boolean getArchived() { return _archived; }
  public boolean getInputs() { return _inputs; }
  public boolean getPS() { return _ps; }
  public boolean getForms() { return _forms; }
  public boolean getOpen() { return _open; }
  public boolean getClosed() { return _closed; }
  public int getInputNums() { return _num_inputs; }
  public Timestamp getBefore() { return _before; }
  public Timestamp getAfter() { return _after; }
  public long getPriority() { return _priority; }
  public long getState() { return _state; }
  public String getFeatureType() { return _type; }
  //Inbox
  public boolean getInboxWF() { return _wf; }
  public boolean getInboxCC() { return _cc; }
  public boolean getInboxDT() { return _dt; }
  public boolean getInboxMI() { return _mi; }
  //ReviewBoard
  public boolean getRBApprovalAccepted() { return _approval_accepted; }
  public boolean getRBApprovalRejected() { return _approval_rejected; }
  public boolean getRBApprovalNeither()  { return _approval_neither; }
  public boolean getRBStateReview()      { return _state_review; }
  public boolean getRBStateAccepted()    { return _state_accepted; }
  //Email
  public boolean getEmailEnabled()          { return _email_enabled; }
  public boolean getEmailAddedtoVWF()       { return _added_to_vwf; }
  public boolean getEmailChangedProfile()   { return _changed_profile; }
  public boolean getEmailFeatureReturned()  { return _feat_returned; }
  public boolean getEmailRevisionInbox()    { return _rev_inbox; }
  public boolean getEmailRevisionAssigned() { return _rev_assigned_feat; }
  public boolean getEmailRevisionVersion()  { return _rev_ver; }
  public boolean getEmailUnacked()          { return _unacked; }
  public int     getEmailUnackedDays()      { return _unacked_days; }
  
  
  public void saveSettings()
  {
    try
    {
      String filename = "../users/user"+_user.getIID().getLongValue()+".ini";
      PrintWriter out = new PrintWriter(new FileWriter(filename));
      out.println("Tree.Filter.Active="+getActive());
      out.println("Tree.Filter.Complete="+getComplete());
      out.println("Tree.Filter.NotComplete="+getNotComplete());
      out.println("Tree.Filter.Review="+getReview());
      out.println("Tree.Filter.Deferred="+getPotFeat());
      out.println("Tree.Filter.Private="+getMyCept());
      out.println("Tree.Filter.Baseline="+getBaseline());
      out.println("Tree.Filter.Archived="+getArchived());
      out.println("Tree.Filter.Inputs="+getInputs());
      out.println("Tree.Filter.Forms="+getForms());
      out.println("Tree.Filter.PS="+getPS());
      out.println("Tree.Filter.Open="+getOpen());
      out.println("Tree.Filter.Closed="+getClosed());
      out.println("Tree.Filter.InputNums="+getInputNums());
      out.println("Tree.Filter.Before="+getBefore());
      out.println("Tree.Filter.After="+getAfter());
      out.println("Tree.Filter.Priority="+getPriority());
      out.println("Tree.Filter.State="+getState());
      out.println("Tree.Filter.FeatureType="+getFeatureType());
      //Inbox
      out.println("Inbox.Filter.WF="+getInboxWF());
      out.println("Inbox.Filter.CC="+getInboxCC());
      out.println("Inbox.Filter.DT="+getInboxDT());
      out.println("Inbox.Filter.MI="+getInboxMI());
      //ReviewBoard
      out.println("RB.Filter.ApprovalAccepted="+getRBApprovalAccepted());
      out.println("RB.Filter.ApprovalRejected="+getRBApprovalRejected());
      out.println("RB.Filter.ApprovalNeither="+getRBApprovalNeither());
      out.println("RB.Filter.StateReview="+getRBStateReview());
      out.println("RB.Filter.StateAccepted="+getRBStateAccepted());
      //Email
      out.println("Email.Enabled="+getEmailEnabled());
      out.println("Email.AddedtoVWF="+getEmailAddedtoVWF());
      out.println("Email.ChangedProfile="+getEmailChangedProfile());
      out.println("Email.FeatureReturned="+getEmailFeatureReturned());
      out.println("Email.RevisionInbox="+getEmailRevisionInbox());
      out.println("Email.RevisionAssigned="+getEmailRevisionAssigned());
      out.println("Email.RevisionVersion="+getEmailRevisionVersion());
      out.println("Email.Unacked="+getEmailUnacked());
      out.println("Email.UnackedDays="+getEmailUnackedDays());
      out.close();
    }//end try
    catch(Throwable exc) {LogService.getInstance().write(exc);}
  }

  public void loadSettings()
  {
    try
    {
      String filename = "../users/user"+_user.getIID().getLongValue()+".ini";
      InputStream in = new FileInputStream(filename);
      if (in != null)
      {
        PropertyResourceBundle prb = new PropertyResourceBundle(in);
        String value = null;
        try {
          value = prb.getString("Tree.Filter.Active");
          if (value != null && !value.equals(""))
            setActive(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Complete");
          if (value != null && !value.equals(""))
            setComplete(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.NotComplete");
          if (value != null && !value.equals(""))
            setNotComplete(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Review");
          if (value != null && !value.equals(""))
            setReview(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Deferred");
          if (value != null && !value.equals(""))
            setPotFeat(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Private");
          if (value != null && !value.equals(""))
            setMyCept(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Baseline");
          if (value != null && !value.equals(""))
            setBaseline(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Archived");
          if (value != null && !value.equals(""))
            setArchived(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Inputs");
          if (value != null && !value.equals(""))
            setInputs(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.PS");
          if (value != null && !value.equals(""))
            setPS(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Forms");
          if (value != null && !value.equals(""))
            setForms(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        
        try {
          value = prb.getString("Tree.Filter.Open");
          if (value != null && !value.equals(""))
            setOpen(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Closed");
          if (value != null && !value.equals(""))
            setClosed(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Before");
          if (value != null && !value.equals("") && !value.equals("null"))
            setBefore(null);
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.After");
          if (value != null && !value.equals("") && !value.equals("null"))
            setAfter(null);
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.Priority");
          if (value != null && !value.equals("") && !value.equals("null"))
            setPriority(Long.parseLong(value));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.State");
          if (value != null && !value.equals("") && !value.equals("null"))
            setState(Long.parseLong(value));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.FeatureType");
          if (value != null && !value.equals("") && !value.equals("null"))
            setFeatureType(value);
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        //Inbox
        try {
          value = prb.getString("Inbox.Filter.WF");
          if (value != null && !value.equals("") && !value.equals("null"))
            setInboxWF(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Inbox.Filter.CC");
          if (value != null && !value.equals("") && !value.equals("null"))
            setInboxCC(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Inbox.Filter.DT");
          if (value != null && !value.equals("") && !value.equals("null"))
            setInboxDT(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Inbox.Filter.MI");
          if (value != null && !value.equals("") && !value.equals("null"))
            setInboxMI(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        //ReviewBoard
        try {
          value = prb.getString("RB.Filter.ApprovalAccepted");
          if (value != null && !value.equals("") && !value.equals("null"))
            setRBApprovalAccepted(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("RB.Filter.ApprovalRejected");
          if (value != null && !value.equals("") && !value.equals("null"))
            setRBApprovalRejected(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("RB.Filter.ApprovalNeither");
          if (value != null && !value.equals("") && !value.equals("null"))
            setRBApprovalNeither(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("RB.Filter.StateReview");
          if (value != null && !value.equals("") && !value.equals("null"))
            setRBStateReview(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("RB.Filter.StateAccepted");
          if (value != null && !value.equals("") && !value.equals("null"))
            setRBStateAccepted(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        //Email
        try {
          value = prb.getString("Email.Enabled");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailEnabled(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.AddedtoVWF");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailAddedtoVWF(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.ChangedProfile");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailChangedProfile(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.FeatureReturned");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailFeatureReturned(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.RevisionInbox");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailRevisionInbox(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.RevisionAssigned");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailRevisionAssigned(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.RevisionVersion");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailRevisionVersion(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.Unacked");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailUnacked(value.equals("true"));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Email.UnackedDays");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailUnackedDays(Integer.parseInt(value));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        try {
          value = prb.getString("Tree.Filter.InputNums");
          if (value != null && !value.equals("") && !value.equals("null"))
            setEmailUnackedDays(Integer.parseInt(value));
        } catch (Throwable exc) {LogService.getInstance().write(exc);}
        
        
        in.close();
      }
    }
    catch(FileNotFoundException ignored) {}
    catch (Throwable exc) {LogService.getInstance().write(exc);}
  }
    
  
}//end class