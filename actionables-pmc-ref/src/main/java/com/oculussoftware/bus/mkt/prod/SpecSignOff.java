package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    SpecSignOff.java
* Date:        4/3/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class SpecSignOff extends BusinessObject implements ISpecSignOff
{
  public static final String COL_FEATCATLINKID = "FEATURELINKID";
  public static final String COL_USERID        = "SIGNUSERID";
  public static final String COL_ACCEPTSTATE   = "ACCEPTSTATE";
  public static final String COL_EDITDATE      = "EDITDATE";
  public static final String COL_COMMENT       = "CONDITION";
  
  protected IIID _featcatlinkiid;
  protected IIID _useriid;
  
  protected AcceptState _astate;
  protected IRProperty _editdate;
  protected IRProperty _comment;
  
  public SpecSignOff() throws OculusException
  {
    super();
    COL_GUID = "GUID";
    TABLE = "SPECSIGNOFF";
    _editdate = new BMProperty(this);  
    _comment  = new BMProperty(this);
    setAcceptState(AcceptState.NOT_ENTERED);
    
    
    _editdate.setDefnObject(IDCONST.EDIT_DATE.getIIDValue());
    _comment.setDefnObject(IDCONST.COMMENT.getIIDValue());
  }
  
  //-------------------------- protected methods -------------------
  
  protected PSPKind needsPreparedStatement()
  {
    return PSPKind.STRING;
  }
  
  protected String getPSPUpdateQuery()
  {
    return "UPDATE "+TABLE+
           " SET "+COL_COMMENT+"= ?"+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }
  
  protected Object getPSPValue() throws OculusException
  {
    return _comment.getValue();
  }
  
  protected String getCreateQuery()
  throws OculusException
  {
    setEditDate(new Timestamp(System.currentTimeMillis()));
    return "INSERT INTO "+TABLE+" "+
           "( "+
           COL_OBJECTID+", "+
           COL_GUID+", "+
           COL_FEATCATLINKID+", "+
           COL_USERID+", "+
           COL_ACCEPTSTATE+", "+
           COL_EDITDATE+
           " ) "+
           " VALUES ( "+
           getIID().getLongValue()+", "+
           "'"+getGUID().toString()+"', "+
           getFeatureCategoryLinkIID().getLongValue()+", "+
           getUserIID().getLongValue()+", "+
           getAcceptState().getIntValue()+", "+
           "'"+getEditDate()+"'"+
           " )";
  }  
  
  protected String getDeleteQuery() throws ORIOException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  protected String getLoadQuery() throws ORIOException
  {
    return "SELECT * "+
           "FROM "+TABLE+" "+
           "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }
  
  protected String getUpdateQuery() throws OculusException
  {
    setEditDate(new Timestamp(System.currentTimeMillis()));
    return "UPDATE "+TABLE+" SET "+
           COL_ACCEPTSTATE+"="+getAcceptState().getIntValue()+", "+
           COL_EDITDATE+"='"+getEditDate()+"'"+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue(); 
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
                                " WHERE "+COL_FEATCATLINKID+"="+getFeatureCategoryLinkIID().getLongValue()+
                                " AND "+COL_USERID+"="+getUserIID().getLongValue());
      while (rs.next())
	    {
			  IIID ssoiid = getObjectContext().getRepository().makeReposID(rs.getLong(COL_OBJECTID));
				ISpecSignOff oldsso = (ISpecSignOff)getObjectContext().getCRM().getCompObject(getObjectContext(),"SpecSignOff",ssoiid,true);
				oldsso.delete();
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
                                " WHERE "+COL_FEATCATLINKID+"="+getFeatureCategoryLinkIID().getLongValue()+
                                " AND "+COL_USERID+"="+getUserIID().getLongValue());
      blnRV = rs.next();
    }//end try
    finally{if(qp!=null)qp.close();}
    return blnRV;
  }//
    
  public Object dolly() throws OculusException
  {
    SpecSignOff sso = null;
    sso = new SpecSignOff();
    sso.setIID(getIID());
    sso.setObjectContext(getObjectContext());
    sso.setPersState(getPersState());
    sso.setFeatureCategoryLinkIID(getFeatureCategoryLinkIID());
    sso.setUserIID(getUserIID());
    sso.setAcceptState(getAcceptState());
    sso.setComment(getComment());
    sso.setEditDate(getEditDate());
    return sso; 
  }
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    try
    {
      if (rs.getIID() == null)
        rs.setIID(rs.getLong(COL_OBJECTID));
//      super.load(rs);
      IRepository rep = getObjectContext().getRepository();
      _guid = new GUID(rs.getString(COL_GUID).trim());
      _featcatlinkiid = rep.makeReposID(rs.getLong(COL_FEATCATLINKID));
      _useriid = rep.makeReposID(rs.getLong(COL_USERID));
      _editdate.setValue(rs.getTimestamp(COL_EDITDATE));
      _comment.setValue(rs.getString(COL_COMMENT));
      _astate = AcceptState.getInstance(rs.getInt(COL_ACCEPTSTATE));
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
  }//
  
  public IRClass getDefnObject() throws OculusException
  {
    return getFeatureCategoryLinkObject().getDefnObject();
  }
  
  // ------------------------- MUTATORS ----------------------------
  
  public ISpecSignOff setFeatureCategoryLinkIID(IIID featcatiid) throws ORIOException
  {
     _featcatlinkiid = featcatiid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public ISpecSignOff setFeatureCategoryLinkObject(IFeatureCategoryLink featcat) throws ORIOException
  {
     _featcatlinkiid = featcat.getIID();
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public ISpecSignOff setUserIID(IIID useriid) throws ORIOException
  {
    _useriid = useriid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public ISpecSignOff setUserObject(IUser user) throws ORIOException
  {
    _useriid = user.getIID();
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }
  
  public ISpecSignOff setComment(String comment) throws ORIOException
  { 
    _comment.setValue(comment); 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public ISpecSignOff setAcceptState(AcceptState astate) throws ORIOException
  { 
    _astate = astate; 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  public ISpecSignOff setEditDate(Timestamp date) throws ORIOException
  { 
    _editdate.setValue(date); 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//
  
  
  // ------------------------- ACCESSORS ---------------------------
  
  public IIID getFeatureCategoryLinkIID() throws ORIOException
  {
    return _featcatlinkiid;
  }
  
  public IFeatureCategoryLink getFeatureCategoryLinkObject() throws OculusException
  {
    return (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",_featcatlinkiid); 
  }
  
  public IIID getUserIID() throws ORIOException
  {
    return _useriid;
  }
  
  public IUser getUserObject() throws OculusException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_useriid);
  }
  
  public String getComment() throws OculusException
  { 
    return (String)_comment.getValue(); 
  }
  
  public AcceptState getAcceptState() throws ORIOException
  { 
    return _astate; 
  }
  
  public Timestamp getEditDate() throws OculusException
  {
    return (Timestamp)_editdate.getValue(); 
  }
}