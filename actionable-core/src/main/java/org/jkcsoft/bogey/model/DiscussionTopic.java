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

import java.sql.*;

public class DiscussionTopic extends BusinessObject implements IDiscussionTopic
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * BUG00946        Cuihua Zhang    6/13/2000   added SQLUtil.primer for textfield
  * PRB00979        Cuihua Zhang    6/13/2000   added check for null for getParDiscussID
  *                                             in the getUpdateQuery method and the load method  
  */
  
  public static final String COL_PAROBJECTID   = "PAROBJECTID";
  public static final String COL_NUMCHILDREN   = "NUMCHILDREN";
  public static final String COL_EDITEDDATE    = "EDITEDDATE";
  public static final String COL_TOPICKIND     = "TOPICKIND";
  public static final String COL_ISROOT        = "ISROOT";
  public static final String COL_TREELEVEL     = "TREELEVEL";
  public static final String COL_SUBJECT       = "SUBJECT";
  public static final String COL_BODY          = "BODY";
  public static final String COL_PARDISCUSSID  = "PARDISCUSSIONID";
  public static final String COL_PAROBJECTTYPE = "PAROBJECTTYPE";
  
  private Oid       _parobjectiid;
  private Oid       _pardiscussiid;
  private IDCONST    _parobjecttype;
  
  private int        _numchildren;
  private BMProperty _subject;
  private BMProperty _editeddate;
  private TopicKind  _topickind;
  private boolean    _isroot;
  private int        _treelevel;
  private BMProperty _body;

  /**
  *
  */
  public DiscussionTopic() throws AppException
  {
    super();
    TABLE = "DISCUSSIONTOPIC";
    COL_Guid = "Guid";
    _subject     = new BMProperty(this);
    _editeddate  = new BMProperty(this);
    _body        = new BMProperty(this);

    _subject.setDefnObject(IDCONST.SUBJECT.getIIDValue());
    _editeddate.setDefnObject(IDCONST.EDIT_DATE.getIIDValue());
    _body.setDefnObject(IDCONST.BODY.getIIDValue());
  }//
  
  //-------------------------- Protected Methods -----------------------------
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
    return "SELECT * "+
           "FROM "+TABLE+" "+
           "WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
           //" AND "+COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue();
  }//
  
  private String getCreateQuery() throws AppException
  {
    return "INSERT INTO "+TABLE+
           " ("+COL_OBJECTID+", "
               +COL_Guid+", "
               +COL_NUMCHILDREN+", "
               +COL_EDITEDDATE+", "
               +COL_CREATIONDATE+", "
               +COL_CLASSID+", "
               +COL_STATEID+", "
               +COL_CREATORID+", "
               +COL_ACCESSID+", "
               +COL_TOPICKIND+", "
               +COL_ISROOT+", "
               +COL_PAROBJECTID+", "
               +(_pardiscussiid!=null?COL_PARDISCUSSID+", ":"")
               +COL_PAROBJECTTYPE+", "
               +COL_TREELEVEL+", "
               +COL_FILEATTACHED+", "
               +COL_LINKATTACHED+", "
               +COL_MESSAGEATTACHED+", "
               +COL_SUBJECT+", "
               +COL_DELETESTATE
               +") VALUES ("
               + getOid().getLongValue()+", '"
               +getGuid().toString()+"', "
               +getNumChildren()+", '"
               +getEditedDate()+"', '"
               +getCreationDate()+"', "
               +getDefnObject().getIID().getLongValue()+","
               +getStateMachine().getDefaultStartStateObject().getIID().getLongValue()+","
               +getCreatorIID().getLongValue()+", "
               +getAccessIID().getLongValue()+", "
               +getTopicKind().getIntValue()+", "
               +(isRoot()?"1":"0")+", "
               +getParObjectIID().getLongValue()+", "
               +(_pardiscussiid!=null?getParDiscussObjectIID().getLongValue()+", ":"")
               +getParObjectType().getLongValue()+", "
               +getTreeLevel()+", "
               +(hasFileAttached()?"1":"0")+", "
               +(hasLinkAttached()?"1":"0")+", "
               +(hasMessageAttached()?"1":"0")+", '"
               +SQLUtil.primer(getSubject())+"', "+
               +getDeleteState().getIntValue()+")";
  }
  
  private String getUpdateQuery() throws AppException
  {
    String parentdiscid = null;    
    if (getParDiscussObjectIID() != null)
       parentdiscid = getParDiscussObjectIID().getLongValue()+"";
       
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   "+COL_SUBJECT+"='"+SQLUtil.primer(getSubject())+"'"+
           " , "+COL_NUMCHILDREN+"="+getNumChildren()+
           " , "+COL_EDITEDDATE+"='"+getEditedDate()+"'"+
           " , "+COL_STATEID+"="+getStateObject().getIID().getLongValue()+
          // " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+
           " , "+COL_TOPICKIND+"="+getTopicKind().getIntValue()+
           " , "+COL_DELETESTATE+"="+getDeleteState().getIntValue()+
           " , "+COL_ISROOT+"="+(isRoot()?"1":"0")+
           (_pardiscussiid!=null?" , "+COL_PAROBJECTID+"= "+getParObjectIID().getLongValue():"")+
           " , "+COL_PARDISCUSSID+"="+parentdiscid+
           " , "+COL_PAROBJECTTYPE+"="+getParObjectType().getLongValue()+
           " , "+COL_TREELEVEL+"="+getTreeLevel()+
           " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+
           " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+
           " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+
           " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }//
  
  private String getDeleteQuery() throws AppException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }//
  
  private void load(IDataSet results) throws AppException
  {
    IRepository rep = getObjectContext().getRepository();
    setEditedDate(results.getTimestamp(COL_EDITEDDATE));
    setCreationDate(results.getTimestamp(COL_CREATIONDATE));
    _classIID = rep.makeReposID(results.getLong(COL_CLASSID));
    _stateIID = rep.makeReposID(results.getLong(COL_STATEID));
    setCreatorIID(rep.makeReposID(results.getLong(COL_CREATORID)));
    setAccessIID(rep.makeReposID(results.getLong(COL_ACCESSID)));
    setNumChildren(results.getInt(COL_NUMCHILDREN));
    setSubject(results.getString(COL_SUBJECT));
    setEditedDate(results.getTimestamp(COL_EDITEDDATE));
    setTopicKind(TopicKind.getInstance(results.getInt(COL_TOPICKIND)));
    setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE)));
    isRoot(results.getInt(COL_ISROOT) == 1);
    setFileAttached(results.getInt(COL_FILEATTACHED) == 1);
    setLinkAttached(results.getInt(COL_LINKATTACHED) == 1);
    setMessageAttached(results.getInt(COL_MESSAGEATTACHED) == 1);
    setTreeLevel(results.getInt(COL_TREELEVEL));
    setParObjectIID(rep.makeReposID(results.getLong(COL_PAROBJECTID)));
		String strParDiscID = results.getString(COL_PARDISCUSSID);
		if(strParDiscID != null)
      setParDiscussObjectIID(rep.makeReposID(Long.parseLong(strParDiscID)));
    setParObjectType(IDCONST.getInstance(rep.makeReposID(results.getLong(COL_PAROBJECTTYPE))));
    setBody(results.getString(COL_BODY));
  }//
  
  public IPersistable save()
    throws AppException
  {
    //delete the notifications
    if (getPersState().equals(PersState.DELETED))
    {
      INotificationColl nc = getNotifications(true);
      while(nc.hasNext())
        nc.nextNotification().delete();
    }//end if
    return super.save();    
  }//
  
  //----------------- ILink Methods ---------------------------------
  /** I don't know what this does */
  public ISet getBranches()
  { return null; }//
  
  //------------------------ MUTATORS -------------------------------
  
  public IDiscussionTopic setNumChildren(int num) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _numchildren = num;
    return this;
  }//
  
  public IDiscussionTopic setSubject(String sub) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _subject.setValue(sub);
    return this;
  }//
  
  public IDiscussionTopic setEditedDate(Timestamp date) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _editeddate.setValue(date);
    return this;
  }//
  
  public IDiscussionTopic setTopicKind(TopicKind tk) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _topickind = tk;
    return this;
  }//
  
  public IDiscussionTopic isRoot(boolean r) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _isroot = r;
    return this;
  }//
  
  public IDiscussionTopic setTreeLevel(int tl) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _treelevel = tl;
    return this;
  }//
  
  public IDiscussionTopic setParObjectIID(Oid piid) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parobjectiid = piid;
    return this;
  }//
  
  public IDiscussionTopic setParDiscussObjectIID(Oid diid) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _pardiscussiid = diid;
    return this;
  }//
  
  public IDiscussionTopic setParObjectType(IDCONST c) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parobjecttype = c;
    return this;
  }//
  
  public IDiscussionTopic setBody(String body) throws AppException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _body.setValue(body);
    return this;
  }//
  
  //------------------------ ACCESSORS ------------------------------
  
  public int getNumChildren() throws AppException
  { return _numchildren; }//
  public String getSubject() throws AppException
  { return (String)_subject.getValue(); }//
  public Timestamp getEditedDate() throws AppException
  { return (Timestamp)_editeddate.getValue(); }//
  public TopicKind getTopicKind() throws AppException
  { return _topickind; }//
  public boolean isRoot() throws AppException
  { return _isroot; }//
  public int getTreeLevel() throws AppException
  { return _treelevel; }//
  public Oid getParObjectIID() throws AppException
  { return _parobjectiid; }
  public Oid getParDiscussObjectIID() throws AppException
  { return _pardiscussiid; }
  public IDCONST getParObjectType() throws AppException
  { return _parobjecttype; }
  public String getBody() throws AppException
  { return (String)_body.getValue(); }
  
  
  
  
  public IDiscussionTopic createCopy()
    throws AppException
  {
    Oid classIID = IDCONST.DISCUSSIONTOPIC.getIIDValue();
    IDiscussionTopic newTopic = (IDiscussionTopic)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopic",(IDataSet)null,true);
    newTopic.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));

    newTopic.setSubject(getSubject());
    newTopic.setLinkAttached(hasLinkAttached());
    newTopic.setFileAttached(hasFileAttached());
    newTopic.setEditedDate(getEditedDate());
    newTopic.setTopicKind(getTopicKind());
    newTopic.setDeleteState(getDeleteState());
    newTopic.setNumChildren(getNumChildren());
    newTopic.isRoot(isRoot());
    newTopic.setTreeLevel(getTreeLevel());
    newTopic.setParObjectIID(getParObjectIID());
    newTopic.setParDiscussObjectIID(getParDiscussObjectIID());
    newTopic.setBody(getBody());


    return newTopic;
  }

  
  
  //----------------- IPoolable Methods -----------------------------
  /**
  *  Returns a copy of the current product object.  It does not copy the
  * ObjectContext.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws AppException
  {
      DiscussionTopic dt = null;
      dt = new DiscussionTopic();
      dt.setOid(getOid());
      dt.setObjectContext(getObjectContext());
      dt.setPersState(getPersState());
      dt._classIID = _classIID;
      dt._stateIID = _stateIID;
     
      if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
         dt.putAll(getProperties());

      dt._creatorIID = _creatorIID;
      dt._accessIID = _accessIID;
      dt.setCreationDate(getCreationDate());
      dt.setLinkAttached(hasLinkAttached());
      dt.setFileAttached(hasFileAttached());
      dt.setMessageAttached(hasMessageAttached());
      dt.setSubject(getSubject());
      dt.setEditedDate(getEditedDate());
      dt.setTopicKind(getTopicKind());
      dt.setDeleteState(getDeleteState());
      dt.setNumChildren(getNumChildren());
      dt.isRoot(isRoot());
      dt.setTreeLevel(getTreeLevel());
      dt.setParObjectIID(getParObjectIID());
      dt.setParDiscussObjectIID(getParDiscussObjectIID());
      dt.setParObjectType(getParObjectType());
      dt.setBody(getBody());
      return dt;
  }//
  
}//end class