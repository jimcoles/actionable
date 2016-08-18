package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.common.org.*;
import java.sql.*;


import com.oculussoftware.api.busi.common.process.*;

/**
* Filename:    ProcessChange.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessChange extends ReposObject implements IProcessChange
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  public static final String TABLE               = "PROCESSCHANGE";
  public static final String COL_PROCESSCHANGEID = "OBJECTID";
  public static final String COL_CHGOBJECTID     = "CHGOBJECTID";
  public static final String COL_REVISIONID      = "REVISIONID";
  public static final String COL_FROMUSERID      = "FROMUSERID";
  public static final String COL_TRANSITIONID    = "TRANSITIONID";
  public static final String COL_CHANGEDATE      = "CHANGEDATE";
  public static final String COL_ACKMASK         = "ACKMASK";
  public static final String COL_COMMENT         = "USERCOMMENT";
  
  protected IIID      _chgobjectiid;
  protected IIID      _reviid;
  protected IIID      _fromuseriid;
  protected IIID      _transitioniid;
  protected Timestamp _changedate;
  protected int       _ackmask;
  protected String    _comment;
  protected IUserList _users;
  
  /**
  *
  */
  public ProcessChange() throws OculusException
  {
    _guid = new GUID();
  }//end Constructor
  
  public Object dolly() throws OculusException 
  { 
    ProcessChange pc = null;
      pc = new ProcessChange();
      pc.setIID(getIID());
      pc.setObjectContext(getObjectContext());
      pc.setPersState(getPersState());
      pc.setChangeObjectIID(getChangeObjectIID());
      pc.setRevisionIID(getRevisionIID());
      pc.setFromUserIID(getFromUserIID());
      pc.setTransitionIID(getTransitionIID());
      pc.setComment(getComment());
      pc.setAckMask(getAckMask());
      pc.setChangeDate(getChangeDate());
      pc._users = (IUserList)getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChangeUserList", getIID());
    return pc; 
  }//
  
  //Accessors
  public IIID getChangeObjectIID() throws ORIOException { return _chgobjectiid; }
  public IIID getRevisionIID() throws ORIOException { return _reviid; }
  public IIID getFromUserIID() throws ORIOException { return _fromuseriid; }
  public IUser getFromUserObject() throws OculusException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",getFromUserIID());
  }
  public IIID getTransitionIID() throws ORIOException { return _transitioniid; }
  public IRTransition getTransitionObject() throws OculusException
  {
    if(getTransitionIID() == null || getTransitionIID().getLongValue() <= 1)
      return null;
    return (IRTransition)getObjectContext().getCRM().getCompObject(getObjectContext(),"Transition",getTransitionIID());
  }
  public String getComment() throws ORIOException { return _comment; }
  public Timestamp getChangeDate() throws ORIOException { return _changedate; }
  public int getAckMask() throws ORIOException { return _ackmask; }
  public IUserList getReceivers() throws OculusException
  {
    if(_users != null)
      _users.reset();
    return _users;
  }
//  private IUserList loadReceivers() throws OculusException
//  {
//    _users = (IUserList)getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChangeUserList", getIID());
//    return getReceivers();
//  }
  
  //Mutators  
  public IProcessChange setChangeObjectIID(IIID i) throws ORIOException 
  { _chgobjectiid = i; return this; }
  
  public IProcessChange setRevisionIID(IIID i) throws ORIOException 
  { _reviid = i; return this; }
  
  public IProcessChange setFromUserIID(IIID i) throws ORIOException 
  { _fromuseriid = i; return this; }
  
  public IProcessChange setFromUserObject(IUser user) throws OculusException
  { return setFromUserIID(user.getIID()); }
  
  public IProcessChange setTransitionIID(IIID i) throws ORIOException 
  { _transitioniid = i; return this; }
  
  public IProcessChange setTransitionObject(IRTransition trans) throws OculusException
  { return setTransitionIID(trans.getIID()); }
  
  public IProcessChange setComment(String c) throws ORIOException 
  { _comment = c; return this; }
  
  public IProcessChange setAckMask(int a) throws ORIOException 
  { _ackmask = a; return this; }
  
  public IProcessChange setChangeDate(Timestamp t) throws ORIOException
  { _changedate = t; return this; }
  
  public IProcessChange addReceiver(IUser user) throws OculusException
  {
    _users.add(user.getIID());
//    //make sure that the user is not already there
//    boolean isThere = false;
//    IUserList users = getRecievers();
//    while(users.hasNext())
//      if(users.nextUser().getIID().equals(user.getIID()))
//        isThere = true;
//    if(!isThere)
//    {
//      IRConnection c = null; IQueryProcessor qp = null;
//      try
//      {
//        c = CRM.getInstance().getDatabaseConnection(_context);
//        qp = c.createProcessor();
//        qp.update("INSERT INTO PROCESSRECEIVER ("
//                  +"CHANGEID, USERID) VALUES ("
//                  +getIID()+", "+user.getIID()+")");
//      }//end try
//      catch(Exception ex) { throw new ORIOException(ex); }
//      finally{if(qp!=null)qp.close();}
//    }//end if  
    return this;
  }
  
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

    if (args != null && args.containsKey(COL_FROMUSERID))
      load(args);

    return this;
    
  }
  
  //---------------------------- ReposObject -----------------------
  
  protected String getLoadQuery()
    throws OculusException
  { 
    return "SELECT * FROM "+TABLE+
            " WHERE "+COL_PROCESSCHANGEID+"="+_iid.getLongValue(); 
  }
      
  protected String getLoadPropertiesQuery()
    throws OculusException
  { return null; }

  protected String getUpdateQuery()
    throws OculusException
  { 
    return null; 
  }

  protected String getCreateQuery()
    throws OculusException
  { 
    return null;
  }

  protected String getDeleteQuery()
    throws OculusException
  { 
    return "DELETE FROM "+TABLE+
            " WHERE "+COL_PROCESSCHANGEID+"="+_iid.getLongValue(); 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    IRepository rep = getObjectContext().getRepository();
    _iid            = rep.makeReposID(rs.getLong(COL_PROCESSCHANGEID));
    _chgobjectiid   = rep.makeReposID(rs.getLong(COL_CHGOBJECTID));
    _reviid         = rep.makeReposID(rs.getLong(COL_REVISIONID));
    _fromuseriid    = rep.makeReposID(rs.getLong(COL_FROMUSERID));
    _transitioniid  = rep.makeReposID(rs.getLong(COL_TRANSITIONID));
    _changedate     = rs.getTimestamp(COL_CHANGEDATE);
    _ackmask        = rs.getInt(COL_ACKMASK);
    _comment        = rs.getString(COL_COMMENT);
    _users = (IUserList)getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChangeUserList", _iid);
    
//    loadReceivers();
  }//end load
  
  public IPersistable delete()
    throws OculusException
	{
		if (!isLocked())	// If the bo isn't locked, throw an exception
			throw new ORIOException("This object cannot be deleted because it is not locked.");
		
    setPersState(PersState.DELETED);						// set the persistant state to deleted
    IQueryProcessor stmt = null;
		try
    {
		  IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      stmt = repConn.createProcessor();
			String query = getDeleteQuery();                        // get bo specific delete query
      stmt.update("DELETE FROM PROCESSRECEIVER WHERE CHANGEID="+_iid);
      stmt.update("DELETE FROM "+TABLE+" WHERE "+COL_PROCESSCHANGEID+"="+_iid);
    }//end try
		finally
    { if (stmt != null) stmt.close(); }
		return this;
	}
      
  public IPersistable save() throws ORIOException
  {
    IRConnection c = null; IQueryProcessor qp = null; IPreparedStatementProcessor psp = null;
    try
    {
      c = CRM.getInstance().getDatabaseConnection(_context);
      qp = c.createProcessor();
      if(getPersState() == PersState.NEW)
      {
        //INSERT
        _changedate = new Timestamp(System.currentTimeMillis());
        qp.update("INSERT INTO "+TABLE+" ("
                  +COL_PROCESSCHANGEID+", "
                  +COL_CHGOBJECTID+", "
                  +COL_REVISIONID+", "
                  +COL_FROMUSERID+", "
                  +(_transitioniid==null?"":COL_TRANSITIONID+", ")
                  +COL_CHANGEDATE+", "
                  +COL_ACKMASK
                  +") VALUES ("
                  +_iid.getLongValue()+", "
                  +_chgobjectiid.getLongValue()+", "
                  +(_reviid!=null?""+_reviid.getLongValue():"-1")+", "
                  +_fromuseriid.getLongValue()+", "
                  +(_transitioniid==null?"":_transitioniid.getLongValue()+", ")
                  +"'"+_changedate+"', "
                  +_ackmask
                  +")");
        _users.reset();
        while(_users.hasNext())
        {          
          qp.update("INSERT INTO PROCESSRECEIVER ("
                    +"CHANGEID, USERID) VALUES ("
                    +getIID()+", "+_users.nextUser().getIID()+")");
        }
        psp = c.prepareProcessor("UPDATE "+TABLE+
                                 " SET "+COL_COMMENT+"= ?"+
                                 " WHERE "+COL_PROCESSCHANGEID+"="+_iid.getLongValue());
        if (_comment != null && !_comment.equals(""))
          psp.getStatement().setString(1, _comment);
        else
          psp.getStatement().setString(1, " ");
        psp.update();
        setPersState(PersState.UNMODIFIED);
      }//end if
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        qp.update("UPDATE "+TABLE+" SET "+
                  COL_ACKMASK+"="+_ackmask+", "+
                  "WHERE "+COL_PROCESSCHANGEID+"="+_iid.getLongValue());
        setPersState(PersState.UNMODIFIED);
      }//end else if
      else if(getPersState() == PersState.DELETED)
      {
        //DELETE 
        qp.update("DELETE FROM PROCESSRECEIVER WHERE CHANGEID="+_iid);
        qp.update("DELETE FROM "+TABLE+" WHERE "+COL_PROCESSCHANGEID+"="+_iid);
      }//end else if
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
    finally{if(qp!=null)qp.close();}
    return this;
  }
  
  //------------------------------ IRElement -----------------------
  
  public String getName()  
    throws OculusException
  { return null; }

  public String getDescription()
    throws OculusException
  { return null; }

  public IRElement setName(String n)
    throws OculusException
  { return null; }
 
  public IRElement setDescription(String d)
    throws OculusException
  { return null; }

}