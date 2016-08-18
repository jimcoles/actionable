/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.metamodel;

import com.oculussoftware.api.busi.common.org.IUser;
import org.jkcsoft.bogey.system.AppException;

public class RoleAssignment extends ReposObject implements IRoleAssignment
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public final static String TABLE            = "OBJECTROLEASSIGN";
  public final static String COL_OBJECTID     = "OBJECTID";
  public final static String COL_Guid         = "Guid";
  public final static String COL_PAROBJECTID  = "PAROBJECTID";
  public final static String COL_ROLEID       = "ROLEID";
  public final static String COL_USERID       = "USERID";
  public final static String COL_ORDERNUM     = "ORDERNUM";
  public final static String COL_ISCONTROLLER = "ISCONTROLLER";
  
  private Oid    _parobjectiid;
  private Oid    _useriid;
  private Oid    _roleiid;
  private int     _ordernum;
  private boolean _iscontroller = true;
  
  public RoleAssignment() throws AppException
  {
    super();
  }
  
  //---------------------------- ReposObject -----------------------
  
  private String getLoadQuery()
    throws AppException
  { 
    return "SELECT * FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }
      
  private String getLoadPropertiesQuery()
    throws AppException
  { return null; }

  private String getUpdateQuery()
    throws AppException
  { 
    return "UPDATE "+TABLE+" SET "+
           COL_ORDERNUM+"="+_ordernum+", "+
           COL_ISCONTROLLER+"="+(_iscontroller?"1":"0")+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  private String getCreateQuery()
    throws AppException
  { 
	  IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(), "Role", _roleiid);
    return (!role.isMultiUser()?"DELETE FROM "+TABLE+" WHERE "+COL_PAROBJECTID+"="+_parobjectiid+" AND "+COL_ROLEID+"="+_roleiid+"; ":"")+"INSERT INTO "+TABLE+"( "+
            COL_OBJECTID+", "+
          //  COL_Guid+", "+
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

  private String getDeleteQuery()
    throws AppException
  { 
    return "DELETE FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  private void load(IDataSet results)
    throws AppException
  {  
    IRepository repos = getObjectContext().getRepository();
    _parobjectiid = repos.makeReposID(results.getLong(COL_PAROBJECTID));
    _useriid      = repos.makeReposID(results.getLong(COL_USERID));
    _roleiid      = repos.makeReposID(results.getLong(COL_ROLEID));
    _ordernum     = results.getInt(COL_ORDERNUM);
    _iscontroller = results.getBoolean(COL_ISCONTROLLER);
  }
  
  public Object dolly() throws AppException
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
    throws AppException
  { return null; }

  public String getDescription()
    throws AppException
  { return null; }

  public IRElement setName(String n)
    throws AppException
  { return null; }
 
  public IRElement setDescription(String d)
    throws AppException
  { return null; }
  
  //----------------------------------------------------------------
  
  public IPoolable construct(ObjectContext context, IDataSet args) throws AppException
  {
    Oid iid = null;
    if (context == null)
      throw new AppException("Context Argument expected.");
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
    throws AppException
  {
    if (getPersState().equals(PersState.NEW))
	  {
		  deleteOld();
      return super.save();
	  }//end if
    else
      return super.save();
  }
	
	private void deleteOld() throws AppException
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
		    Oid roleassiid = getObjectContext().getRepository().makeReposID(rs.getLong(COL_OBJECTID));
				IRoleAssignment oldroleass = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",roleassiid,true);
				oldroleass.delete();
	    }//end if
    }//end try
    finally{if(qp!=null)qp.close();}
	}
  
  public boolean isDuplicate() throws AppException
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
  public Oid getParObjectIID() throws AppException
  { return _parobjectiid; }
  
  public Oid getRoleIID() throws AppException
  { return _roleiid; }
  
  public IUser getUserObject() throws AppException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_useriid);
  }//
  
  public Oid getUserIID() throws AppException
  { return _useriid; }
  
  public int getOrderNum() throws AppException
  { return _ordernum; }
  
  public boolean isController() throws AppException
  { return _iscontroller; }
  
  //Mutators
  public IRoleAssignment setParObjectIID(Oid iid) throws AppException
  {
    _parobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setRoleIID(Oid iid) throws AppException
  {
    _roleiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setUserIID(Oid iid) throws AppException
  {
    _useriid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment setOrderNum(int num) throws AppException
  {
    _ordernum = num;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public IRoleAssignment isController(boolean c) throws AppException
  {
    _iscontroller = c;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
}