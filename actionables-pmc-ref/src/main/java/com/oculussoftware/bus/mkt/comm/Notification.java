package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;

import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.repos.util.*;

import com.oculussoftware.bus.*;

import com.oculussoftware.api.busi.common.org.IUser;
import com.oculussoftware.repos.bmr.*;

/**
* Filename:    Notification.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class Notification extends BusinessObject implements INotification
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */  
  public static final String COL_OBJECTID         = "OBJECTID";
  public static final String COL_ACKMASK          = "ACKMASK";
  public static final String COL_PAROBJECTID      = "PAROBJECTID";
  public static final String COL_RECIPIENTID      = "RECIPIENTID";
  public static final String COL_SUBJECT          = "SUBJECT";
  public static final String COL_BODY             = "BODY";
  public static final String COL_NOTIFICATIONKIND = "NOTIFICATIONKIND";

  protected IIID   _parobjectiid;
  protected IIID   _recipientiid;
  
  protected IRProperty _ackmask;
  protected IRProperty _body;
  protected IRProperty _subject;
  protected IRProperty _notekind;
  
  /**
  *
  */
  public Notification() throws OculusException
  {
    TABLE = "NOTIFICATION";
    setPersState(PersState.NEW);
    _ackmask  = new BMProperty(this);
    _subject  = new BMProperty(this);  
    _body     = new BMProperty(this);
    _notekind = new BMProperty(this);    
    
    _ackmask.setDefnObject(IDCONST.ACKMASK.getIIDValue());
    _subject.setDefnObject(IDCONST.SUBJECT.getIIDValue());
    _body.setDefnObject(IDCONST.BODY.getIIDValue());
    _notekind.setDefnObject(IDCONST.NOTEKIND.getIIDValue());
    
    
    
  }//
  
  protected PSPKind needsPreparedStatement()
  {
    return PSPKind.STRING;
  }
  
  protected String getPSPUpdateQuery()
  {
    return "UPDATE "+TABLE+
           " SET "+COL_BODY+"= ?"+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }
  
  protected Object getPSPValue() throws OculusException
  {
    return _body.getValue();
  }
  
  protected String getLoadQuery() throws OculusException
  {
    return "SELECT * FROM "+TABLE+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//
  
  protected String getCreateQuery() throws OculusException
  {
    return "INSERT INTO "+TABLE+
           " ("+COL_OBJECTID+", "
             +COL_GUID+", "
             +COL_ACKMASK+", "
             +COL_PAROBJECTID+", "
             +COL_RECIPIENTID+", "
             +COL_CLASSID+", "
             +COL_STATEID+", "
             +COL_CREATORID+", "
             +COL_ACCESSID+", "
             +COL_CREATIONDATE+", "
             +COL_SUBJECT+", "
             +COL_FILEATTACHED+", "
             +COL_LINKATTACHED+", "
             +COL_MESSAGEATTACHED+", "
             +COL_DELETESTATE+", "
             +COL_NOTIFICATIONKIND
             +") VALUES ("
             +_iid.getLongValue()+", "
             +"'"+getGUID().toString()+"', "
             +getAckMask()+", "
             +_parobjectiid.getLongValue()+", "
             +_recipientiid.getLongValue()+", "
             +getDefnObject().getIID().getLongValue()+", "
             +getStateMachine().getDefaultStartStateObject().getIID().getLongValue()+", "
             +getCreatorIID().getLongValue()+", "
             +getAccessIID().getLongValue()+","
             +"'"+getCreationDate()+"', "
             +"'"+SQLUtil.primer(getSubject())+"', "
             +(hasFileAttached()?"1":"0")+", "
             +(hasLinkAttached()?"1":"0")+", "
             +(hasMessageAttached()?"1":"0")+", "
             +getDeleteState().getIntValue()+", "
             +getNotificationKind().getIntValue()
             +")";
  }//
  
  protected String getUpdateQuery() throws OculusException
  {
    return "UPDATE "+TABLE+" SET "+
           COL_ACKMASK+"="+getAckMask()+", "+
           COL_PAROBJECTID+"="+_parobjectiid.getLongValue()+", "+
           COL_RECIPIENTID+"="+_recipientiid.getLongValue()+", "+
           COL_DELETESTATE+"="+getDeleteState().getIntValue()+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//
  
  protected String getDeleteQuery() throws OculusException
  {
    return "DELETE FROM "+TABLE+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//    
  
  public Object dolly() throws OculusException
  {
    Notification notif = new Notification();
    notif.setIID(getIID());
    notif.setObjectContext(getObjectContext());
    notif.setPersState(getPersState());
    
    notif._classIID = _classIID;
    notif._stateIID = _stateIID;
    notif.setDeleteState(getDeleteState());
    notif._creatorIID = _creatorIID;
    notif._accessIID = _accessIID;
    notif.setCreationDate(getCreationDate());
    notif.setMessageAttached(hasMessageAttached());
    notif.setLinkAttached(hasLinkAttached());
    notif.setFileAttached(hasFileAttached());
    
    notif.setParObjectIID(getParObjectIID());
    notif.setAckMask(getAckMask());
    notif.setRecipientIID(getRecipientIID());
    notif.setBody(getBody());
    notif.setSubject(getSubject());
    notif.setNotificationKind(getNotificationKind());

    return notif; 
  }//
  
  
  //------------------------- INotification ------------------------
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    try
    {
      if (rs.getIID() == null)
        rs.setIID(rs.getLong(COL_OBJECTID));
      super.load(rs);
      IRepository rep = getObjectContext().getRepository();
      _ackmask.setValue(new Integer(rs.getInt(COL_ACKMASK)));
      _parobjectiid = rep.makeReposID(rs.getLong(COL_PAROBJECTID));
      _recipientiid = rep.makeReposID(rs.getLong(COL_RECIPIENTID));
      _body.setValue(rs.getString(COL_BODY));
      _subject.setValue(rs.getString(COL_SUBJECT));
      _notekind.setValue(new Integer(rs.getInt(COL_NOTIFICATIONKIND)));
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
  }//
  
  //------------------------- MUTATORS -----------------------------
  
  public INotification setAckMask(int mask) throws ORIOException
  { 
    _ackmask.setValue(new Integer(mask)); 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public INotification setParObjectIID(IIID iid) throws ORIOException
  { 
    _parobjectiid = iid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public INotification setRecipientIID(IIID iid) throws ORIOException
  { 
    _recipientiid = iid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setRecipientObject(IUser user) throws OculusException
  {
    return setRecipientIID(user.getIID());
  }
  
  public INotification setBody(String body) throws ORIOException
  { 
    _body.setValue(body);
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setSubject(String subject) throws ORIOException
  { 
    _subject.setValue(subject);
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setNotificationKind(NotificationKind nk) throws ORIOException
  { 
    /*******
    Please set the value of the IRProperty to a java Object and not to our
    custom classes.
    
    Alok
    ****/
    
    //_notekind.setValue(nk);
    if (nk != null)
    {
      _notekind.setValue(new Integer(nk.getIntValue()));
      if(getPersState() == PersState.UNMODIFIED)
        setPersState(PersState.MODIFIED);
    }
    return this;
  }//
  
  //------------------------- ACCESSORS ----------------------------
  
  public int getAckMask() throws OculusException
  { if(_ackmask.getValue() != null)
      return ((Integer)_ackmask.getValue()).intValue(); 
    else
      return 0;
  }
  
  public IIID getParObjectIID() throws ORIOException
  { return _parobjectiid; }
  
  public IIID getRecipientIID() throws ORIOException
  { return _recipientiid; }
  
  public IUser getRecipientObject() throws OculusException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_recipientiid);
  }
  
  public String getBody() throws OculusException
  { return (String)_body.getValue(); }
  
  public String getSubject() throws OculusException
  { return (String)_subject.getValue(); }
  
  public NotificationKind getNotificationKind() throws ORIOException
  { 
    try
    {
    Integer j = (Integer)_notekind.getValue();
    if (j == null) return null;    
    return NotificationKind.getInstance(j.intValue()); 
    }
    catch(Exception ex) { throw new ORIOException(ex);}
  }
  
}//end class