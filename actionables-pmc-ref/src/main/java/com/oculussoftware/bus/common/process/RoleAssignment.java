package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.*;

import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.IUser;

/**
* Filename:    RoleAssignment.java
* Date:        3/14/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class RoleAssignment extends ReposObject implements IRoleAssignment
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public final static String TABLE            = "OBJECTROLEASSIGN";
  public final static String COL_OBJECTID     = "OBJECTID";
  public final static String COL_GUID         = "GUID";
  public final static String COL_PAROBJECTID  = "PAROBJECTID";
  public final static String COL_ROLEID       = "ROLEID";
  public final static String COL_USERID       = "USERID";
  public final static String COL_ORDERNUM     = "ORDERNUM";
  public final static String COL_ISCONTROLLER = "ISCONTROLLER";
  
  protected IIID    _parobjectiid;
  protected IIID    _useriid;
  protected IIID    _roleiid;
  protected int     _ordernum;
  protected boolean _iscontroller = true;
  
  public RoleAssignment() throws OculusException
  {
    super();
  }
  
  //---------------------------- ReposObject -----------------------
  
  protected String getLoadQuery()
    throws OculusException
  { 
    return "SELECT * FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }
      
  protected String getLoadPropertiesQuery()
    throws OculusException
  { return null; }

  protected String getUpdateQuery()
    throws OculusException
  { 
    return "UPDATE "+TABLE+" SET "+
           COL_ORDERNUM+"="+_ordernum+", "+
           COL_ISCONTROLLER+"="+(_iscontroller?"1":"0")+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  protected String getCreateQuery()
    throws OculusException
  { 
	  IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(), "Role", _roleiid);
    return (!role.isMultiUser()?"DELETE FROM "+TABLE+" WHERE "+COL_PAROBJECTID+"="+_parobjectiid+" AND "+COL_ROLEID+"="+_roleiid+"; ":"")+"INSERT INTO "+TABLE+"( "+
            COL_OBJECTID+", "+
          //  COL_GUID+", "+
            COL_PAROBJECTID+", "+
            COL_ROLEID+", "+
            COL_USERID+", "+
            COL_ORDERNUM+", "+
            COL_ISCONTROLLER+
            ") VALUES ("+
            _iid.getLongValue()+", "+
      //      "'"+_guid.toString()+"', "+
            _parobjectiid.getLongValue()+", "+
            _roleiid.getLongValue()+", "+
            _useriid.getLongValue()+", "+
            _ordernum+", "+
            (_iscontroller?"1":"0")+")";
  }

  protected String getDeleteQuery()
    throws OculusException
  { 
    return "DELETE FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  protected void load(IDataSet results)
    throws OculusException
  {  
    IRepository repos = getObjectContext().getRepository();
    _parobjectiid = repos.makeReposID(results.getLong(COL_PAROBJECTID));
    _useriid      = repos.makeReposID(results.getLong(COL_USERID));
    _roleiid      = repos.makeReposID(results.getLong(COL_ROLEID));
    _ordernum     = results.getInt(COL_ORDERNUM);
    _iscontroller = results.getBoolean(COL_ISCONTROLLER);
  }
  
  public Object dolly() throws OculusException
  {
    RoleAssignment ra = null;
      ra = new RoleAssignment();
      ra.setIID(getIID());
      ra.setObjectContext(getObjectContext());
      ra.setPersState(getPersState());
      ra.setParObjectIID(getParObjectIID());
      ra.setRoleIID(getRoleIID());
      ra.setUserIID(getUserIID());
      ra.setOrderNum(getOrderNum());
      ra.isController(isController());
    return ra; 
  }//
  
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
  
  //----------------------------------------------------------------
  
  public IPoolable construct(IObjectContext context, IDataSet args) throws OculusException
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
    if (args != null && args.containsKey(COL_USERID))
      load(args);
    return this; 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  public IPersistable save()
    throws OculusException
  {
    if (getPersState().equals(PersState.NEW))
	  {
		  deleteOld();
      return super.save();
	  }//end if
    else
      return super.save();
  }
	
	private void deleteOld() throws OculusException
	{
		//delete the old one
		IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = repConn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT "+COL_OBJECTID+" FROM "+TABLE+
                              " WHERE "+COL_PAROBJECTID+"="+getParObjectIID().getLongValue()+
                              " AND "+COL_USERID+"="+getUserIID().getLongValue()+
                              " AND "+COL_ROLEID+"="+getRoleIID().getLongValue());
      while (rs.next())
	    {
		    IIID roleassiid = getObjectContext().getRepository().makeReposID(rs.getLong(COL_OBJECTID));
				IRoleAssignment oldroleass = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",roleassiid,true);
				oldroleass.delete();
	    }//end if
    }//end try
    finally{if(qp!=null)qp.close();}
	}
  
  public boolean isDuplicate() throws OculusException
  {
    boolean blnRV = false;
    IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = repConn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT "+COL_OBJECTID+" FROM "+TABLE+
                                " WHERE "+COL_PAROBJECTID+"="+getParObjectIID().getLongValue()+
                                " AND "+COL_USERID+"="+getUserIID().getLongValue()+
                                " AND "+COL_ROLEID+"="+getRoleIID().getLongValue());
      blnRV = rs.next();
    }//end try
    finally{if(qp!=null)qp.close();}
    return blnRV;
  }//
  
  //--------------------------- IRoleAssignment --------------------
  
  //Accessors
  public IIID getParObjectIID() throws ORIOException
  { return _parobjectiid; }
  
  public IIID getRoleIID() throws ORIOException
  { return _roleiid; }
  
  public IUser getUserObject() throws OculusException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_useriid);
  }//
  
  public IIID getUserIID() throws ORIOException
  { return _useriid; }
  
  public int getOrderNum() throws ORIOException
  { return _ordernum; }
  
  public boolean isController() throws ORIOException
  { return _iscontroller; }
  
  //Mutators
  public IRoleAssignment setParObjectIID(IIID iid) throws ORIOException
  {
    _parobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setRoleIID(IIID iid) throws ORIOException
  {
    _roleiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setUserIID(IIID iid) throws ORIOException
  {
    _useriid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setOrderNum(int num) throws ORIOException
  {
    _ordernum = num;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment isController(boolean c) throws ORIOException
  {
    _iscontroller = c;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
}