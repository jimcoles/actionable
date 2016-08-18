/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

import com.oculussoftware.api.busi.common.org.IUser;

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

  private Oid   _parobjectiid;
  private Oid   _recipientiid;
  
  private BMProperty _ackmask;
  private BMProperty _body;
  private BMProperty _subject;
  private BMProperty _notekind;
  
  /**
  *
  */
  public Notification() throws AppException
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
  
  private PSPKind needsPreparedStatement()
  {
    return PSPKind.STRING;
  }
  
  private String getPSPUpdateQuery()
  {
    return "UPDATE "+TABLE+
           " SET "+COL_BODY+"= ?"+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }
  
  private Object getPSPValue() throws AppException
  {
    return _body.getValue();
  }
  
  private String getLoadQuery() throws AppException
  {
    return "SELECT * FROM "+TABLE+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//
  
  private String getCreateQuery() throws AppException
  {
    return "INSERT INTO "+TABLE+
           " ("+COL_OBJECTID+", "
             +COL_Guid+", "
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
             +"'"+getGuid().toString()+"', "
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
  
  private String getUpdateQuery() throws AppException
  {
    return "UPDATE "+TABLE+" SET "+
           COL_ACKMASK+"="+getAckMask()+", "+
           COL_PAROBJECTID+"="+_parobjectiid.getLongValue()+", "+
           COL_RECIPIENTID+"="+_recipientiid.getLongValue()+", "+
           COL_DELETESTATE+"="+getDeleteState().getIntValue()+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//
  
  private String getDeleteQuery() throws AppException
  {
    return "DELETE FROM "+TABLE+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//    
  
  public Object dolly() throws AppException
  {
    Notification notif = new Notification();
    notif.setOid(getOid());
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
  
  private void load(IDataSet rs) throws AppException
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
    catch(Exception ex) { throw new AppException(ex); }
  }//
  
  //------------------------- MUTATORS -----------------------------
  
  public INotification setAckMask(int mask) throws AppException
  { 
    _ackmask.setValue(new Integer(mask)); 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public INotification setParObjectIID(Oid iid) throws AppException
  { 
    _parobjectiid = iid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public INotification setRecipientIID(Oid iid) throws AppException
  { 
    _recipientiid = iid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setRecipientObject(IUser user) throws AppException
  {
    return setRecipientIID(user.getIID());
  }
  
  public INotification setBody(String body) throws AppException
  { 
    _body.setValue(body);
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setSubject(String subject) throws AppException
  { 
    _subject.setValue(subject);
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public INotification setNotificationKind(NotificationKind nk) throws AppException
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
  
  public int getAckMask() throws AppException
  { if(_ackmask.getValue() != null)
      return ((Integer)_ackmask.getValue()).intValue(); 
    else
      return 0;
  }
  
  public Oid getParObjectIID() throws AppException
  { return _parobjectiid; }
  
  public Oid getRecipientIID() throws AppException
  { return _recipientiid; }
  
  public IUser getRecipientObject() throws AppException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_recipientiid);
  }
  
  public String getBody() throws AppException
  { return (String)_body.getValue(); }
  
  public String getSubject() throws AppException
  { return (String)_subject.getValue(); }
  
  public NotificationKind getNotificationKind() throws AppException
  { 
    try
    {
    Integer j = (Integer)_notekind.getValue();
    if (j == null) return null;    
    return NotificationKind.getInstance(j.intValue()); 
    }
    catch(Exception ex) { throw new AppException(ex);}
  }
  
}//end class