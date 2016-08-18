package com.oculussoftware.service;

import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.api.service.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.comm.*;

/**
* Filename:    CommConfig.java
* Date:        4-12-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ---             Zain Nemazie    7/17/00     Added docnetworkpath
*/
public class CommConfig extends com.oculussoftware.repos.ReposObject implements ICommConfig
{
  public static final String TABLE              = "COMMCONFIG";
  public static final String COL_OBJECTID       = "OBJECTID";
  public static final String COL_MAILSERVERPORT = "MAILSERVERPORT";
  public static final String COL_MAILSERVER     = "MAILSERVER";
  public static final String COL_ENABLED        = "ISENABLED";
  public static final String COL_DOCNETWORKPATH = "DOCNETWORKPATH";
  public static final String COL_EXTRANETURL    = "EXTRANETADDRESS";
    
  protected int            _mailserverport;
  protected String         _mailserver;
  protected String         _docnetworkpath;  
  protected boolean        _enabled;
  protected String         _extraneturl;
  
  public CommConfig() throws OculusException
  {
    super();
  }//
  
  // ---------------------------- Accessors ------------------------
  
  public int getMailServerPort() throws ORIOException
  { return _mailserverport; }
  
  public String getMailServer() throws ORIOException
  { return _mailserver; }
  
  public boolean isEnabled() throws ORIOException
  { return _enabled; }
  
  public String getDocNetworkPath() throws ORIOException
  { return _docnetworkpath; }

  public String getExtranetURL() throws ORIOException
  { return _extraneturl; }
  
  // ---------------------------- Mutators -------------------------
  
  public ICommConfig setMailServerPort(int port) throws ORIOException
  {
    _mailserverport = port;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public ICommConfig setMailServer(String server) throws ORIOException
  {
    _mailserver = server;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public ICommConfig isEnabled(boolean enabled) throws ORIOException
  {
    _enabled = enabled;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }//

  public ICommConfig setDocNetworkPath(String path) throws ORIOException
  {
    _docnetworkpath = path;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }//
  
  public ICommConfig setExtranetURL(String url) throws ORIOException
  {
    _extraneturl = url;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;
  }//
  // ----------------------- Functional Methods --------------------
  
  protected String getLoadQuery()
    throws OculusException
  { 
    return 
      "SELECT * FROM "+TABLE+
      " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }
      
  protected String getLoadPropertiesQuery()
    throws OculusException
  { return null; }

  protected String getUpdateQuery()
    throws OculusException
  { 
    return 
    "UPDATE "+TABLE+" SET "+
           COL_MAILSERVERPORT+"="+_mailserverport+", "+
           COL_ENABLED+"="+(_enabled?"1":"0")+", "+
           COL_MAILSERVER+"='"+_mailserver+"',"+
           COL_DOCNETWORKPATH+"='"+_docnetworkpath+"',"+
           COL_EXTRANETURL+"='"+_extraneturl+"'"+           
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  { 
    return 
      "INSERT INTO "+TABLE+" ("+
          COL_OBJECTID+", "+
          COL_MAILSERVERPORT+", "+
          COL_ENABLED+", "+
          COL_MAILSERVER+ ", " +
          COL_DOCNETWORKPATH+ ", " +
          COL_EXTRANETURL+
          ") VALUES ("+
          _iid.getLongValue()+", "+
          _mailserverport+", "+
          (_enabled?"1":"0")+", "+
          "'"+_mailserver+"', " + 
          "'"+_docnetworkpath+"', " +
          "'"+_extraneturl+"')";
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
    setPersState(PersState.PARTIAL);
    _mailserverport = results.getInt(COL_MAILSERVERPORT);
    _mailserver     = results.getString(COL_MAILSERVER);
    _enabled        = results.getBoolean(COL_ENABLED);
    _docnetworkpath = results.getString(COL_DOCNETWORKPATH);
    _extraneturl    = results.getString(COL_EXTRANETURL);
    setPersState(PersState.UNMODIFIED);
  }
  
  public Object dolly() throws OculusException
  {
    CommConfig cc = new CommConfig();
    cc.setIID(getIID());
    cc.setObjectContext(getObjectContext());
    cc.setPersState(getPersState());
    cc.setMailServerPort(getMailServerPort());
    cc.setMailServer(getMailServer());
    cc.isEnabled(isEnabled());
    cc.setDocNetworkPath(getDocNetworkPath());
    cc.setExtranetURL(getExtranetURL());
    return cc; 
  }  
  
  public IPoolable construct(IObjectContext context, IDataSet args) throws OculusException
  {
    IIID iid = null;
    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);
    if (args == null)
    {
      iid = IDCONST.COMMCONFIG.getIIDValue();
      setPersState(PersState.NEW);
    }
    else
    {
      setPersState(PersState.UNINITED);
      iid = args.getIID();
    }
    setIID(iid);
    if (args != null && args.containsKey(COL_MAILSERVERPORT))
      load(args);
    return this; 
  }//
  
  public IRPropertyMap getProperties()
  { return null; }
  
  public IPersistable softdelete()
    throws OculusException
  { return null; }
  
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

  
}//CommConfig