package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.IUser;
import com.oculussoftware.bus.mkt.comm.*;

import java.sql.*;
/**
* Filename:    InboxRow.java
* Date:        3/14/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class InboxRow extends ReposObject implements IInboxRow
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  public static final String COL_NOTIFICATIONID    = "OBJECTID";
  public static final String COL_PRODUCTNAME       = "PRODUCTNAME";
  public static final String COL_VERSIONNAME       = "VERSIONNAME";
  public static final String COL_ENTITYNAME        = "ENTITYNAME";
  public static final String COL_STATENAME         = "STATENAME";
  public static final String COL_PARENTITYID       = "PARENTITYID";
  public static final String COL_DATE              = "CREATIONDATE";
  public static final String COL_PRIORITYNUM       = "PRIORITYORDERNUM";
  public static final String COL_DISCUSSPAROBJECTTYPE = "DISCUSSPAROBJECTTYPE";
  public static final String COL_DISCUSSPAROBJECTID   = "DISCUSSPAROBJECTID";
  public static final String COL_MM                   = "MKTMGRID";
  public static final String COL_EM                   = "ENGMGRID";
 
  
  public static final String LITERAL_Z = "ZZZZZZZZZZZZZ";
  public static final long   LITERAL_9 = 999999999;
  
  
  protected NotificationKind _notificationkind;
  protected String           _firstname;
  protected String           _lastname;
  protected String           _productname;
  protected String           _versionname;
  protected String           _name;
  protected String           _priority;
  protected String           _statename;
  protected int              _priorityordernum;
  protected IIID             _parobjectiid;
  protected Timestamp        _date;
  protected long             _discussparobjecttype;
  protected IIID             _discussparobjectiid;
  protected boolean          _discussiscompass = false;
  protected int              _ackmask;
  protected IIID             _mmiid;
  protected IIID             _emiid;
  
  
  public InboxRow() throws OculusException
  {
    super();
  }
  
  //---------------------------- ReposObject -----------------------
  
  protected void load(IDataSet results)
    throws OculusException
  {  
    setPersState(PersState.PARTIAL);
    IRepository repos = getObjectContext().getRepository();
    _iid = repos.makeReposID(results.getLong(COL_NOTIFICATIONID));
    _notificationkind = NotificationKind.getInstance(results.getInt(Notification.COL_NOTIFICATIONKIND));
    _firstname = results.getString("FIRSTNAME");
    _lastname = results.getString("LASTNAME");
    _productname = results.getString(COL_PRODUCTNAME);
    _versionname = results.getString(COL_VERSIONNAME);
    _name = results.getString(COL_ENTITYNAME);
    _statename = results.getString(COL_STATENAME);
    _priorityordernum = results.getInt(COL_PRIORITYNUM);
    _parobjectiid = repos.makeReposID(results.getLong(COL_PARENTITYID));
    _discussparobjecttype = results.getLong(COL_DISCUSSPAROBJECTTYPE);
    _discussparobjectiid = repos.makeReposID(results.getLong(COL_DISCUSSPAROBJECTID));
    _date = results.getTimestamp(COL_DATE);
    _ackmask = results.getInt("ACKMASK");
    _priority = results.getString("PRIORITY");
    
    long lngState = 0;
    if(_discussparobjecttype == IDCONST.PRODUCT.getLongValue())
      lngState = results.getLong("DISCUSSPARPRODSTATE");
    else if(_discussparobjecttype == IDCONST.PRODUCTVERSION.getLongValue())
      lngState = results.getLong("DISCUSSPARVERSTATE");
    else if(_discussparobjecttype == IDCONST.CATEGORY.getLongValue())
      lngState = results.getLong("DISCUSSPARCATSTATE");
    else if(_discussparobjecttype == IDCONST.FEATURECATEGORYLINK.getLongValue())
      lngState = results.getLong("DISCUSSPARFEATSTATE");
    //if the state of the parent is compass
    if(lngState != 0 && (lngState == IDCONST.PRODUCTCOMPASS.getLongValue() || lngState == IDCONST.VERSIONCOMPASS.getLongValue()
      || lngState == IDCONST.CATEGORYCOMPASS.getLongValue() || lngState == IDCONST.COMPASS.getLongValue()))
        _discussiscompass = true;
    //if the state is not compass but the parent is i.e. marketinput
    if(!_discussiscompass && (_discussparobjecttype != IDCONST.PRODUCT.getLongValue() && _discussparobjecttype != IDCONST.PRODUCTVERSION.getLongValue()
          && _discussparobjecttype != IDCONST.CATEGORY.getLongValue() && _discussparobjecttype != IDCONST.FEATURECATEGORYLINK.getLongValue()))
      _discussiscompass = true;
    
    long em = results.getLong(COL_EM); 
    if(em != 0)
      _emiid = repos.makeReposID(em);
       
    long mm = results.getLong(COL_MM); 
    if(mm != 0)
      _mmiid = repos.makeReposID(mm);   
    
    setPersState(PersState.UNMODIFIED);
  }
  
  protected String getLoadQuery()
    throws OculusException
  { 
    return // get the notification 
    "SELECT OBJECTID, NOTIFICATIONKIND, CREATIONDATE, ACKMASK, "+
      "NULL AS FIRSTNAME, NULL AS LASTNAME, "+ 
      "NULL AS PRODUCTNAME, "+ 
      "NULL AS VERSIONNAME, "+ 
      "NULL AS ENTITYNAME, "+ 
      "NULL AS STATENAME, "+ 
      "NULL AS PRIORITYORDERNUM, NULL AS PRIORITY,"+ 
      "NULL AS PARENTITYID, NULL AS DISCUSSPAROBJECTTYPE, NULL AS DISCUSSPAROBJECTID, "+
      "NULL AS DISCUSSPARPRODSTATE,  "+
      "NULL AS DISCUSSPARVERSTATE, "+
      "NULL AS DISCUSSPARCATSTATE, "+
      "NULL AS DISCUSSPARFEATSTATE, "+
      "NULL AS MKTMGRID, "+
      "NULL AS ENGMGRID "+
    "FROM NOTIFICATION WHERE OBJECTID = "+_iid.getLongValue();
  }
      
  protected String getLoadPropertiesQuery()
    throws OculusException
  { return null; }

  protected String getUpdateQuery()
    throws OculusException
  { 
    return  //update the notification ackmask 
    "UPDATE NOTIFICATION SET ACKMASK = "+_ackmask+
    " WHERE OBJECTID = "+_iid.getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  { 
    return null;//no create
  }

  protected String getDeleteQuery()
    throws OculusException
  { 
    return //delete the notification 
      "DELETE FROM NOTIFICATION"+
      " WHERE OBJECTID = "+_iid.getLongValue();
  }

  public Object dolly() throws OculusException
  {
    InboxRow ir = null;
    ir = new InboxRow();
    ir.setIID(getIID());
    ir.setObjectContext(getObjectContext());
    ir.setPersState(getPersState());
    ir.setNotificationKind(getNotificationKind());
    ir.setFirstName(getFirstName());
    ir.setLastName(getLastName());
    ir.setProductName(getProductName());
    ir.setVersionName(getVersionName());
    ir.setName(getName());
    ir.setStateName(getStateName());
    ir.setPriorityOrderNum(getPriorityOrderNum());
    ir.setParObjectIID(getParObjectIID());
    ir.setDiscussParObjectType(getDiscussParObjectType());
    ir.setDiscussParObjectIID(getDiscussParObjectIID());
    ir.isDiscussCompass(isDiscussCompass());
    ir.setDate(getDate());
    ir.setAckMask(getAckMask());
    ir.setPriority(getPriority());
    ir.setMMIID(getMMIID());
    ir.setEMIID(getEMIID());
    return ir; 
  }//
  
  //------------------------------ IRElement -----------------------
  
  public String getName()  
    throws OculusException
  { return _name; }

  public String getDescription()
    throws OculusException
  { return null; }

  public IRElement setName(String n)
    throws OculusException
  { _name = n; return this; }
 
  public IRElement setDescription(String d)
    throws OculusException
  { return null; }
  
  //----------------------------------------------------------------
  
  public IPoolable construct(IObjectContext context, IDataSet args) throws OculusException
  {
    IIID iid = null;
    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);

    setPersState(PersState.UNINITED);
    iid = args.getIID();
    
    setIID(iid);
    if (args != null)
      load(args);
    return this; 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  //-------------------------------- ACCESSORS ---------------------
    
  public NotificationKind getNotificationKind() throws ORIOException
  { return _notificationkind; }
  public String getUserName() throws ORIOException
  { return getLastName()+", "+getFirstName(); }
  public String getProductName() throws ORIOException
  { 
    if(_productname != null && _productname.equals(LITERAL_Z))
      return null;
    return _productname; 
  }
  public String getVersionName() throws ORIOException
  { 
    if(_versionname != null && _versionname.equals(LITERAL_Z))
      return null;
    return _versionname; 
  }
  public String getStateName() throws ORIOException
  { return _statename; }
  public int getPriorityOrderNum() throws ORIOException
  { return _priorityordernum; }
  public String getPriority() throws ORIOException
  { return _priority; }
  public IIID getParObjectIID() throws ORIOException
  { return _parobjectiid; }
  public Timestamp getDate() throws ORIOException
  { return _date; }
  public IDCONST getDiscussParObjectType() throws OculusException
  { 
    if(_discussparobjecttype != 0)
      return IDCONST.getInstance(_discussparobjecttype); 
    else
      return null;
  }
  public IIID getDiscussParObjectIID() throws ORIOException
  { return _discussparobjectiid; }
  public boolean isDiscussCompass() throws OculusException
  { return _discussiscompass; }
  public int getAckMask() throws ORIOException
  { return _ackmask; }
  protected String getFirstName() throws ORIOException
  { return _firstname; }
  protected String getLastName() throws ORIOException
  { return _lastname; }
  protected IIID getMMIID() throws ORIOException
  { return _mmiid; }
  protected IIID getEMIID() throws ORIOException
  { return _emiid; }
  public boolean isAcknowledged(AckKind ak) throws ORIOException
  {
    return com.oculussoftware.util.BitMaskUtil.getBitValue(getAckMask(), ak.getIntValue());
  }
  
  public boolean canInterrupt(IUser user) throws OculusException
  {
    if(user == null) return false;
    IIID iid = user.getIID();
    return (user.getIID().equals(_emiid) || user.getIID().equals(_mmiid));
  }
  
  //-------------------------------- MUTATORS ----------------------
  
  public IInboxRow acknowledge(AckKind ak) throws ORIOException
  {
    return setAckMask(com.oculussoftware.util.BitMaskUtil.getIntValue(getAckMask(), ak.getIntValue(), true));
  }
  
  public IInboxRow unacknowledge(AckKind ak) throws ORIOException
  {
    return setAckMask(com.oculussoftware.util.BitMaskUtil.getIntValue(getAckMask(), ak.getIntValue(), false));
  }
  
  protected IInboxRow setNotificationKind(NotificationKind nk) throws ORIOException
  { _notificationkind = nk; return this; }
  protected IInboxRow setFirstName(String name) throws ORIOException
  { _firstname = name; return this; }
  protected IInboxRow setLastName(String name) throws ORIOException
  { _lastname = name; return this; }
  protected IInboxRow setProductName(String name) throws ORIOException
  { _productname = name; return this; }
  protected IInboxRow setVersionName(String name) throws ORIOException
  { _versionname = name; return this; }
  protected IInboxRow setStateName(String name) throws ORIOException
  { _statename = name; return this; }
  protected IInboxRow setPriorityOrderNum(int num) throws ORIOException
  { _priorityordernum = num; return this; }
  protected IInboxRow setPriority(String p) throws ORIOException
  { _priority = p; return this; }
  protected IInboxRow setParObjectIID(IIID iid) throws ORIOException
  { _parobjectiid = iid; return this; }
  protected IInboxRow setDiscussParObjectType(IDCONST id) throws ORIOException
  { 
    if(id != null)
      _discussparobjecttype = id.getLongValue(); 
    return this;
  }
  protected IInboxRow setDiscussParObjectIID(IIID iid) throws ORIOException
  { _discussparobjectiid = iid; return this; }
  protected IInboxRow isDiscussCompass(boolean val) throws OculusException
  { _discussiscompass = val; return this; }
  public IInboxRow setAckMask(int a) throws ORIOException
  { 
    _ackmask = a;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  protected IInboxRow setDate(Timestamp ts) throws ORIOException
  { _date = ts; return this; }
  protected IInboxRow setMMIID(IIID iid) throws ORIOException
  { _mmiid = iid; return this; }
  protected IInboxRow setEMIID(IIID iid) throws ORIOException
  { _emiid = iid; return this; }
}