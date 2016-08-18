package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.*;

import com.oculussoftware.api.busi.common.process.*;

/**
* Filename:    ProcessRole.java
* Date:        3/14/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessRole extends ReposObject implements IProcessRole
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public final static String TABLE           = "PROCESSROLE";
  public final static String COL_OBJECTID    = "OBJECTID";
  public final static String COL_GUID        = "GUID";
  public final static String COL_ISACTIVE    = "ISACTIVE";
  public final static String COL_DELETESTATE = "DELETESTATE";
  public final static String COL_CONFIGKIND  = "CONFIGUREKIND";
  public final static String COL_NAME        = "NAME";
  public final static String COL_DESCRIPTION = "SHORTDESCRIPTION";
  public final static String COL_MULTIPLE    = "ISMULTIUSER";
  public final static String COL_INHRPARENT  = "INHRPARENTASSIGN"; 
  
  protected DeleteState _deletestate = DeleteState.NOT_DELETED;
  protected boolean     _isactive    = true;
  protected ConfigKind  _configkind  = ConfigKind.READ_ONLY;
  protected String      _name;
  protected String      _description;
  protected boolean     _multiple    = true;
  protected boolean     _inhrparent  = true;
  
  
  public ProcessRole() throws OculusException
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
           COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
           COL_ISACTIVE+"="+(_isactive?"1":"0")+", "+
           COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
           COL_MULTIPLE+"="+(_multiple?"1":"0")+", "+
           COL_INHRPARENT+"="+(_inhrparent?"1":"0")+", "+
           COL_NAME+"='"+SQLUtil.primer(_name)+"', "+
           COL_DESCRIPTION+"='"+SQLUtil.primer(_description)+"'"+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  { 
    return "INSERT INTO "+TABLE+" ("+
            COL_OBJECTID+", "+
            COL_GUID+", "+
            COL_DELETESTATE+", "+
            COL_ISACTIVE+", "+
            COL_CONFIGKIND+", "+
            COL_MULTIPLE+", "+
            COL_INHRPARENT+", "+
            COL_NAME+", "+
            COL_DESCRIPTION+
            ") VALUES ("+
            _iid.getLongValue()+", "+
            "'"+_guid.toString()+"', "+
            _deletestate.getIntValue()+", "+
            (_isactive?"1":"0")+", "+
            _configkind.getIntValue()+", "+
            (_multiple?"1":"0")+", "+
            (_inhrparent?"1":"0")+", "+
            "'"+SQLUtil.primer(_name)+"', "+
            "'"+SQLUtil.primer(_description)+"')";
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
    _deletestate = DeleteState.getInstance(results.getInt(COL_DELETESTATE));
    _isactive    = results.getBoolean(COL_ISACTIVE);
    _configkind  = ConfigKind.getInstance(results.getInt(COL_CONFIGKIND));
    _name        = results.getString(COL_NAME);
    _description = results.getString(COL_DESCRIPTION);
    _multiple    = results.getBoolean(COL_MULTIPLE);
    _inhrparent  = results.getBoolean(COL_INHRPARENT);
  }
  
  public Object dolly() throws OculusException
  {
    ProcessRole pr = null;
      pr = new ProcessRole();
      pr.setIID(getIID());
      pr.setObjectContext(getObjectContext());
      pr.setPersState(getPersState());
      pr.setDeleteState(getDeleteState());
      pr.isActive(isActive());
      pr.setConfigKind(getConfigKind());
      pr.setName(getName());
      pr.isMultiUser(isMultiUser());
      pr.inheritParent(inheritParent());
      pr.setDescription(getDescription());
    return pr; 
  }
  
  //---------------------------- IRModelElement --------------------
  
  public DeleteState getDeleteState()
    throws ORIOException
  { return _deletestate; }
  
  public boolean isActive()
    throws ORIOException
  { return _isactive; }
  
  public ConfigKind getConfigKind()
    throws ORIOException
  { return _configkind; }
  
  
  public IRModelElement setDeleteState(DeleteState d)
    throws ORIOException
  { 
    _deletestate = d;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
  public IRModelElement isActive(boolean a)
    throws ORIOException
  { 
    _isactive = a;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public IRModelElement setConfigKind(ConfigKind c)
    throws ORIOException
  { 
    _configkind = c;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  //------------------------------ IRElement -----------------------
  
  public String getName()  
    throws OculusException
  { return _name; }

  public String getDescription()
    throws OculusException
  { return _description; }

  public IRElement setName(String n)
    throws OculusException
  { 
    _name = n;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
 
  public IRElement setDescription(String d)
    throws OculusException
  { 
    _description = d;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
  //-------------------------- IProcessRole ------------------------
  
  public IRModelElement isMultiUser(boolean a)
    throws ORIOException
  { 
    _multiple = a;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public IRModelElement inheritParent(boolean a)
    throws ORIOException
  { 
    _inhrparent = a;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public boolean isMultiUser()
    throws ORIOException
  { return _multiple; }
  
  public boolean inheritParent()
    throws ORIOException
  { return _inhrparent; }
  
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
    if (args != null && args.containsKey(COL_NAME))
      load(args);
    return this; 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  public IPersistable softdelete()
    throws OculusException
  { return null; }
  
  public boolean isUsed() throws ORIOException { return false;}
  public IPersistable recover() throws OculusException { return null;}
}