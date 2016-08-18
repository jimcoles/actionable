package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.util.*;


import java.util.*;

/**
* Filename:    BMAction.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMAction extends BMModelElement implements IRAction
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  //CONSTANTS
  public static final String TABLE                = "TRANSITACTION";
  public static final String COL_ORDERNUM         = "ORDERNUM";
  public static final String COL_TRANSITIONID     = "TRANSITIONID";
  public static final String COL_ATTRIBUTEID      = "ATTRIBUTEID";
  public static final String COL_ACTIONKIND       = "ACTIONKIND";
  public static final String COL_SETVALUE         = "SETVALUE";
  public static final String COL_ACTIONEXPRESSION = "ACTIONEXPRESSION";
  
  
  protected int        _ordernum;
  protected IIID       _transitioniid;
  protected IIID       _attributeiid;
  protected ActionKind _actionkind;
  protected String     _setvalue;
  protected String     _actionexpr;
 
  /**
  *
  */
  public BMAction() throws OculusException
  {
    guid = new GUID();
  }//end constructor
  
  //ACCESSORS
  public int getOrderNum() throws ORIOException { return _ordernum; }
  public IIID getTransitionIID() throws ORIOException { return _transitioniid; }
  public IIID getAttributeIID() throws ORIOException { return _attributeiid; }
  public ActionKind getActionKind() throws ORIOException { return _actionkind; }
  public String getSetValue() throws ORIOException { return _setvalue; }
  public String getActionExpression() throws ORIOException { return _actionexpr; }
  
  
  //MUTATORS
  public IRAction setOrderNum(int o) throws ORIOException 
  { 
    _ordernum = o;   
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setOrderNum
  
  public IRAction setTransitionIID(IIID t) throws ORIOException 
  { 
    _transitioniid = t; 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setTransitionIID
  
  public IRAction setAttributeIID(IIID a) throws ORIOException 
  { 
    _attributeiid = a;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setAttributeIID
  
  public IRAction setActionKind(ActionKind a) throws ORIOException 
  { 
    _actionkind = a;    
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setActionKind
  
  public IRAction setSetValue(String s) throws ORIOException 
  { 
    _setvalue = s;      
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setSetValue
  
  public IRAction setActionExpression(String s) throws ORIOException 
  { 
    _actionexpr = s;      
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED); 
    return this; 
  }//end setActionExpression
  
  //FUNCTIONAL METHODS
  public IRAction execute(IBusinessObject bo) throws OculusException 
  { 
    throw new OculusException("Action.execute not implemented"); 
  }//end execute
  
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository rep = getObjectContext().getRepository();
    _ordernum       = rs.getInt(COL_ORDERNUM);
    _transitioniid  = rep.makeReposID(rs.getLong(COL_TRANSITIONID));
    _attributeiid   = rep.makeReposID(rs.getLong(COL_ATTRIBUTEID));
    _actionkind     = ActionKind.getInstance(rs.getInt(COL_ACTIONKIND));
    _setvalue       = rs.getString(COL_SETVALUE);
    _actionexpr     = rs.getString(COL_ACTIONEXPRESSION);
  }//end load
  
  /**
  *
  */
  public IPersistable load() throws OculusException
  {
    if(getPersState() != PersState.NEW)
    {
      if(iid == null)
        throw new OculusException("Null IID"); 
      IRConnection c = null; IQueryProcessor qp = null;
      try
      {
        c = getDatabaseConnection();
        qp = c.createProcessor();
        IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE+" WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        if(rs.next())
        {
          load(rs);
          setPersState(PersState.UNMODIFIED);
        }//end if
        else
          throw new ORIOException("Action object not found.");
      }//end try
      catch(Exception ex) { throw new OculusException(ex); }
      finally
      {
      if(qp!=null) qp.close();
//      if(c!=null) returnDatabaseConnection(c);
      }
    }//end if
    return this; 
  }//end load
  
  /**
  *
  */
  public IPersistable save() throws ORIOException
  {
    if (!isLocked() && !getPersState().equals(PersState.NEW))  // If the bo isn't locked, throw an exception
      throw new ORIOException("This object ("+this.getClass().getName()+":"+this.getIID().getLongValue()+") cannot be saved because it is not locked.");

    IRConnection c = null; IPreparedStatementProcessor psp = null; IQueryProcessor qp = null; 
    try
    {
      c = getDatabaseConnection();
      qp = c.createProcessor();
      if(getPersState() == PersState.NEW)
      {
        //INSERT
        qp.update("INSERT INTO "+TABLE+
                  " ("+COL_OBJECTID+", "+COL_BYTEGUID+", "+COL_NAME+", "+COL_DESCRIPTION+", "+
                       COL_ORDERNUM+", "+COL_TRANSITIONID+", "+COL_ATTRIBUTEID+", "+COL_CONFIGKIND+", "+
                       COL_ACTIONKIND+", "+COL_ISACTIVE+", "+COL_SETVALUE+", "+COL_DELETESTATE+") VALUES ("+
                       iid.getLongValue()+", '"+guid.toString()+"', '"+SQLUtil.primer(name)+"', '"+SQLUtil.primer(description)+"', "+
                       _ordernum+", "+_transitioniid.getLongValue()+", "+_attributeiid.getLongValue()+", "+
                       _configkind.getIntValue()+", "+_actionkind.getIntValue()+", "+(isactive?"1":"0")+", '"+_setvalue+"', "+_deletestate.getIntValue()+")");
        psp = c.prepareProcessor("UPDATE "+TABLE+
                                 " SET "+COL_ACTIONEXPRESSION+"= ?"+
                                 " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        if (_actionexpr != null && !_actionexpr.equals(""))
          psp.getStatement().setString(1, _actionexpr);
        else
          psp.getStatement().setString(1, " ");
        psp.update();
        setPersState(PersState.UNMODIFIED);
      }//end if
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        qp.update("UPDATE "+TABLE+" SET "+
                  COL_NAME+"='"+SQLUtil.primer(name)+"', "+
                  COL_ORDERNUM+"="+_ordernum+", "+
                  COL_TRANSITIONID+"="+_transitioniid.getLongValue()+", "+
                  COL_ATTRIBUTEID+"="+_attributeiid.getLongValue()+", "+
                  COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
                  COL_ACTIONKIND+"="+_actionkind.getIntValue()+", "+
                  COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
                  COL_SETVALUE+"='"+_setvalue+"', "+
                  COL_DESCRIPTION+"='"+SQLUtil.primer(description)+"' "+
                  "WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        psp = c.prepareProcessor("UPDATE "+TABLE+
                                 " SET "+COL_ACTIONEXPRESSION+"= ?"+
                                 " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        if (_actionexpr != null && !_actionexpr.equals(""))
          psp.getStatement().setString(1, _actionexpr);
        else
          psp.getStatement().setString(1, " ");
        psp.update();
        setPersState(PersState.UNMODIFIED);
      }//end else if
      else if(getPersState() == PersState.DELETED)
      {
        //DELETE 
        qp.update("UPDATE "+TABLE+" SET "+COL_DELETESTATE+"="+DeleteState.DELETED.getIntValue()+" WHERE "+COL_OBJECTID+"="+iid.getLongValue());
      }//end else if
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
    finally{if(qp!=null)qp.close(); if(psp!=null)psp.close();}
    return this;
  }//end add
  
  /**
  *
  */
  public Object dolly() throws OculusException
  {
    BMAction action = new BMAction();
    action.setIID(getIID());
    action.setObjectContext(getObjectContext());
    action.setPersState(getPersState());
    action.setName(getName());
    action.setDescription(getDescription());
    action.setOrderNum(getOrderNum());
    action.setTransitionIID(getTransitionIID());
    action.setAttributeIID(getAttributeIID());
    action.setActionKind(getActionKind());
    action.setConfigKind(getConfigKind());
    action.setDeleteState(getDeleteState());
    action.isActive(isActive());
    action.setSetValue(getSetValue());
    action.setActionExpression(getActionExpression());
    return action;
  }//
}