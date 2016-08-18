package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.*;

import com.oculussoftware.api.busi.mkt.comm.*;

/**
* Filename:    SemanticLink.java
* Date:        3/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class SemanticLink extends ReposObject implements ISemanticLink
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public final static String TABLE            = "SEMANTICLINK";
  public final static String COL_OBJECTID     = "OBJECTID";
  public final static String COL_GUID         = "GUID";
  public final static String COL_SRCOBJECTID  = "SRCOBJECTID";
  public final static String COL_DESTOBJECTID = "DESTOBJECTID";
  public final static String COL_LINKTYPE     = "LINKTYPE";
  public final static String COL_ORDERNUM     = "ORDERNUM";
  
  protected IIID    _srcobjectiid, _destobjectiid;
  protected LinkKind _linktype;
  protected int     _ordernum;
  
  public SemanticLink() throws OculusException
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
           COL_ORDERNUM+"="+_ordernum+" "+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  protected String getCreateQuery()
    throws OculusException
  { 
    return "INSERT INTO "+TABLE+
      "( "+
            COL_OBJECTID+", "+
            COL_GUID+", "+
            COL_SRCOBJECTID+", "+
            COL_DESTOBJECTID+", "+
            COL_LINKTYPE+", "+
            COL_ORDERNUM+" "+
            ") VALUES ("+
            _iid.getLongValue()+", "+
            "'"+_guid.toString()+"', "+
            _srcobjectiid.getLongValue()+", "+
            _destobjectiid.getLongValue()+", "+
            _linktype.getIntValue()+", "+
            _ordernum+" "+
            ")";
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
    _guid = new GUID(results.getString(COL_GUID).trim()); 
    _iid = results.getIID();
    _srcobjectiid = repos.makeReposID(results.getLong(COL_SRCOBJECTID));
    _destobjectiid = repos.makeReposID(results.getLong(COL_DESTOBJECTID));
    _linktype = LinkKind.getInstance(results.getInt(COL_LINKTYPE));
    _ordernum = results.getInt(COL_ORDERNUM);
    setPersState(PersState.UNMODIFIED);
  }
  
  public Object dolly() throws OculusException
  {
    SemanticLink ra = null;
      ra = new SemanticLink();
      ra.setIID(getIID());
      ra.setObjectContext(getObjectContext());
      ra.setPersState(getPersState());
      ra.setSourceObjectIID(getSourceObjectIID());
      ra.setDestObjectIID(getDestObjectIID());
      ra.setLinkType(getLinkType());
      ra.setOrderNum(getOrderNum());
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
    if (args != null && args.containsKey(COL_LINKTYPE))
      load(args);
    return this; 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  //--------------------------- IRoleAssignment --------------------
  
  //Accessors
  public IIID getSourceObjectIID() throws ORIOException
  { return _srcobjectiid; }
  
  public IIID getDestObjectIID() throws ORIOException
  { return _destobjectiid; }
  
  public LinkKind getLinkType() throws ORIOException
  { return _linktype; }
  
  public int getOrderNum() throws ORIOException
  { return _ordernum; }
  
  //Mutators
  public ISemanticLink setSourceObjectIID(IIID iid) throws ORIOException
  {
    _srcobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setDestObjectIID(IIID iid) throws ORIOException
  {
    _destobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setLinkType(LinkKind linktype) throws ORIOException
  {
    _linktype = linktype;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setOrderNum(int num) throws ORIOException
  {
    _ordernum = num;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
}