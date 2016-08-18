package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.bus.common.org.UserColl;
import java.sql.Timestamp;
import java.util.*;

/**
* Filename:    AlertConfig.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class AlertConfig extends BusinessObject implements IAlertConfig
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  protected static final String COL_PAROBJECTID    = "PAROBJECTID";
  protected static final String COL_ALERTTYPE      = "ALERTTYPE";
  protected static final String COL_LASTNOTIFYDATE = "LASTNOTIFYDATE";
  protected static final String COL_ISACTIVE       = "ISACTIVE";
  
  protected static final String TABLE_RECIPIENT    = "ALERTRECIPIENT";
  protected static final String COL_USERID         = "USERID";
  protected static final String TABLE_PARAMVALUE   = "ALERTPARAMVALUE";
  protected static final String COL_CONFIGID       = "ALERTCONFIGID";
  protected static final String COL_PARAMID        = "PARAMID";
  protected static final String COL_PARAMVALUE     = "PARAMVALUE";
  
  protected AlertType _alerttype;
  protected IIID      _parobjectiid;
  protected Timestamp _lastnotifydate;
  protected boolean   _isactive = true;
  protected IUserColl _recipients;
  protected Map       _parammap;
  
  /**
  *
  */
  public AlertConfig() throws OculusException
  {
    TABLE = "ALERTCONFIG";
    _lastnotifydate = new Timestamp(System.currentTimeMillis());
    _parammap = new HashMap();
  }//
  
  protected PSPKind needsPreparedStatement()
  {
    return PSPKind.NONE;
  }
  
  protected String getLoadQuery() throws OculusException
  {
    return "SELECT * FROM "+TABLE+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }//
  
  protected String getCreateQuery() throws OculusException
  {
    return null;
  }//
  
  protected String getUpdateQuery() throws OculusException
  {
    return null;
  }//
  
  protected String getDeleteQuery() throws OculusException
  {
    return null;
  }//    
  
  public Object dolly() throws OculusException
  {
    AlertConfig alert = null;
      alert = new AlertConfig();
      alert.setIID(getIID());
      alert.setObjectContext(getObjectContext());
      alert.setPersState(getPersState());
      alert.setParObjectIID(getParObjectIID());
      alert.setAlertType(getAlertType());
      alert.setLastNotifyDate(getLastNotifyDate());
      alert.isActive(isActive());
			_recipients.setObjectContext(getObjectContext());
      alert.setRecipientColl(_recipients);
      alert.setParams(_parammap);
    return alert; 
  }//
  
  //------------------------- IAlertConfig ------------------------
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    try
    {
      IRepository rep = getObjectContext().getRepository();
      _parobjectiid   = rep.makeReposID(rs.getLong(COL_PAROBJECTID));
      _alerttype      = AlertType.getInstance(rs.getInt(COL_ALERTTYPE));
      _lastnotifydate = rs.getTimestamp(COL_LASTNOTIFYDATE);
      _isactive       = rs.getBoolean(COL_ISACTIVE);
      _recipients     = (IUserColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlertConfigUserColl",getIID());
      _parammap       = loadParamMap();
      setPersState(PersState.UNMODIFIED);
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
  }//
  
  protected void setRecipientColl(IUserColl recips) throws OculusException
  {
    _recipients = (IUserColl)recips.dolly();
  }
  
  protected void setParams(Map params)
  {
    _parammap = params; 
  }
  
  protected Map loadParamMap() throws OculusException
  {
    if(_iid == null)
      throw new OculusException("Null IID"); 
    IRConnection c = null; IQueryProcessor qp = null;
    IRepository repos = getObjectContext().getRepository();
    Map params = new HashMap();
    try
    {
      c = CRM.getInstance().getDatabaseConnection(_context);
      qp = c.createProcessor();
      IDataSet rs = qp.retrieve("SELECT "+COL_PARAMID+", "+COL_PARAMVALUE+" FROM "+TABLE_PARAMVALUE+" WHERE "+COL_CONFIGID+"="+_iid.getLongValue());
      while(rs.next())
        params.put(AlertParamType.getInstance(rs.getInt(COL_PARAMID)), rs.getString(COL_PARAMVALUE));
    }//end try
    finally
    {
      if(qp!=null) qp.close();
//      if(c!=null) CRM.getInstance().returnDatabaseConnection(c);
    }
    return params; 
  }//
  
  protected IUserColl loadRecipientColl() throws OculusException
  {
    return (IUserColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlertConfigUserColl",getIID()); 
  }//
  
  //------------------------- MUTATORS -----------------------------
  
  public IAlertConfig setParObjectIID(IIID iid) throws ORIOException
  { 
    _parobjectiid = iid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public IAlertConfig setAlertType(AlertType at) throws ORIOException
  { 
    _alerttype = at;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public IAlertConfig setLastNotifyDate(Timestamp date) throws ORIOException
  { 
    _lastnotifydate = date;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public IAlertConfig isActive(boolean a) throws ORIOException
  { 
    _isactive = a;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public IAlertConfig addRecipient(IUser user) throws OculusException
  {
    //need to check to see if the user is already there
    if(_recipients.getObjectContext() == null)
      _recipients.setObjectContext(_context);
    _recipients.add(user.getIID());
    _recipients.reset();
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public IAlertConfig addRecipientColl(IUserColl userColl) throws OculusException
  {
    while(userColl.hasNext())
      addRecipient(userColl.nextUser());
    return this;
  }//
  
  public IAlertConfig addParam(AlertParamType apt, String paramValue) throws OculusException
  { 
    _parammap.put(apt,paramValue);
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public IAlertConfig addParamMap(Map paramMap) throws OculusException
  {
    Iterator itr = paramMap.keySet().iterator();
    while(itr.hasNext())
    {
      AlertParamType apt = (AlertParamType)itr.next();
      addParam(apt,(String)paramMap.get(apt));
    }//end while
    return this;
  }//
  
  //------------------------- ACCESSORS ----------------------------

  public IIID getParObjectIID() throws ORIOException
  { return _parobjectiid; }
  
  public AlertType getAlertType() throws ORIOException
  { return _alerttype; }
  
  public Timestamp getLastNotifyDate() throws ORIOException
  { return _lastnotifydate; }
  
  public boolean isActive() throws ORIOException
  { return _isactive; }
  
  
  public IUserColl getRecipientColl() throws OculusException
  { 
	  _recipients.setObjectContext(getObjectContext());  
	  return _recipients; 
	}
  
  public Map getParamMap() throws OculusException
  { return _parammap; }
  
  public String getParamValue(AlertParamType apt) throws OculusException
  {
    return (String)_parammap.get(apt);
  }//
  
  
  public IPersistable save()
    throws OculusException
  {
    if (!isLocked())  // If the bo isn't locked, throw an exception
      throw new ORIOException("This object cannot be saved because it is not locked.");
    
    IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      qp = repConn.createProcessor();
      // NEEDS TO BE SAVED
      if (getPersState().equals(PersState.MODIFIED))
      {
        qp.update("UPDATE "+TABLE+" SET "+
                   COL_LASTNOTIFYDATE+"='"+getLastNotifyDate()+"', "+
                   COL_PAROBJECTID+"="+_parobjectiid.getLongValue()+", "+
                   COL_ALERTTYPE+"="+_alerttype.getIntValue()+", "+
                   COL_ISACTIVE+"="+(isActive()?"1":"0")+
                   " WHERE "+COL_OBJECTID+"="+_iid.getLongValue());
        //recipients
        while(_recipients.hasNext())
        {
          IUser user = _recipients.nextUser();
          IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE_RECIPIENT+
                                    " WHERE "+COL_USERID+"="+user.getIID().getLongValue()+
                                    " AND "+COL_CONFIGID+"="+_iid.getLongValue());
          if(!rs.next())
          {
            qp.update("INSERT INTO "+TABLE_RECIPIENT+
                      " ("+COL_USERID+", "+COL_CONFIGID+") VALUES ("+
                      user.getIID().getLongValue()+", "+_iid.getLongValue()+")");
          }//end if
        }//end while
        //params
        Iterator itr = _parammap.keySet().iterator();
        while(itr.hasNext())
        {
          AlertParamType apt = (AlertParamType)itr.next();
          String paramVal = (String)_parammap.get(apt);
          IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE_PARAMVALUE+
                                    " WHERE "+COL_PARAMID+"="+apt.getIntValue()+
                                    " AND "+COL_CONFIGID+"="+_iid.getLongValue());
          if(!rs.next())
          {
            qp.update("INSERT INTO "+TABLE_PARAMVALUE+
                      " ("+COL_PARAMID+", "+COL_CONFIGID+", "+COL_PARAMVALUE+") VALUES ("+
                      apt.getIntValue()+", "+_iid.getLongValue()+", '"+SQLUtil.primer(paramVal)+"')");
          }//end if
          else
          {
            qp.update("UPDATE "+TABLE_PARAMVALUE+" SET "+
                       COL_PARAMVALUE+"='"+SQLUtil.primer(paramVal)+"'"+
                       " WHERE "+COL_CONFIGID+"="+_iid.getLongValue()+
                       " AND "+COL_PARAMID+"="+apt.getIntValue());
          }//end else
        }//end while
        setPersState(PersState.UNMODIFIED);
      }//end if
      // NEEDS TO BE CREATED
      if (getPersState().equals(PersState.NEW))
      {
        qp.update("INSERT INTO "+TABLE+
                   " ("+COL_OBJECTID+", "
                     +COL_PAROBJECTID+", "
                     +COL_ALERTTYPE+", "
                     +COL_LASTNOTIFYDATE+", "
                     +COL_ISACTIVE
                     +") VALUES ("
                     +_iid.getLongValue()+", "
                     +_parobjectiid.getLongValue()+", "
                     +_alerttype.getIntValue()+", "
                     +"'"+getLastNotifyDate()+"', "
                     +(isActive()?"1":"0")
                     +")");
        //recipients
        _recipients.reset();
        while(_recipients.hasNext())
        {
          IUser user = _recipients.nextUser();
          IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE_RECIPIENT+
                                    " WHERE "+COL_USERID+"="+user.getIID().getLongValue()+
                                    " AND "+COL_CONFIGID+"="+_iid.getLongValue());
          if(!rs.next())
          {
            qp.update("INSERT INTO "+TABLE_RECIPIENT+
                      " ("+COL_USERID+", "+COL_CONFIGID+") VALUES ("+
                      user.getIID().getLongValue()+", "+_iid.getLongValue()+")");
          }//end if
        }//end while
        //params
        Iterator itr = _parammap.keySet().iterator();
        while(itr.hasNext())
        {
          AlertParamType apt = (AlertParamType)itr.next();
          String paramVal = (String)_parammap.get(apt);
          IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE_PARAMVALUE+
                                    " WHERE "+COL_PARAMID+"="+apt.getIntValue()+
                                    " AND "+COL_CONFIGID+"="+_iid.getLongValue());
          if(!rs.next())
          {
            qp.update("INSERT INTO "+TABLE_PARAMVALUE+
                      " ("+COL_PARAMID+", "+COL_CONFIGID+", "+COL_PARAMVALUE+") VALUES ("+
                      apt.getIntValue()+", "+_iid.getLongValue()+", '"+SQLUtil.primer(paramVal)+"')");
          }//end if
        }//end while
        setPersState(PersState.UNMODIFIED);
      }//end if
      // NEEDS TO BE DELETED
      if (getPersState().equals(PersState.DELETED))
      {
        qp.update("DELETE FROM "+TABLE+
                   " WHERE "+COL_OBJECTID+"="+_iid.getLongValue());
        qp.update("DELETE FROM "+TABLE_RECIPIENT+
                   " WHERE "+COL_CONFIGID+"="+_iid.getLongValue());
        qp.update("DELETE FROM "+TABLE_PARAMVALUE+
                   " WHERE "+COL_CONFIGID+"="+_iid.getLongValue());
      }//end if
    }//end try
    finally
    { if(qp != null) qp.close();}
    return this;
  }//
  
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = null;
    
    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);

    if (args == null)
    {
      iid = getObjectContext().getRepository().genReposID();
      setPersState(PersState.NEW);
    }
    else
    {
      setPersState(PersState.UNINITED);
      iid = args.getIID();
    }
    setIID(iid);

    if (args != null && args.containsKey(COL_NAME))
      load(args);
      
    if(_recipients == null)
      _recipients = (IUserColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlertConfigUserColl",getIID());

    return this;
  }
}//end class