package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.util.expr.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* Filename:    BMGuard.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMGuard extends BMModelElement implements IRGuard
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  //CONSTANTS
  public static final String TABLE               = "GUARD";
  public static final String COL_TRANSITIONID    = "TRANSITIONID";
  public static final String COL_GUARDKIND       = "GUARDKIND";
  public static final String COL_GUARDEXPRESSION = "GUARDEXPRESSION";
  
  protected IIID      _transitioniid;
  protected GuardKind _guardkind;
  protected String    _guardexpr;

  /**
  *
  */
  public BMGuard() throws OculusException
  {
    guid = new GUID();
  }//end constructor
  
  //ACCESSORS 
  public IIID getTransitionIID() throws ORIOException { return _transitioniid; }
  public GuardKind getGuardKind() throws ORIOException { return _guardkind; }
  public String getGuardExpression() throws ORIOException { return _guardexpr; }
  
  //MUTATORS
  public IRGuard setTransitionIID(IIID t) throws ORIOException 
  { 
    _transitioniid = t;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setTransitionIID
  
  public IRGuard setGuardKind(GuardKind g) throws ORIOException 
  { 
    _guardkind = g;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//end setGuardKind
  
  public IRGuard setGuardExpression(String s) throws ORIOException 
  { 
    _guardexpr = s;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//end setGuardExpression
  
  //FUNCTIONAL METHODS
  public IIID evaluate(IBusinessObject bo) throws OculusException 
  { 
    ExprEvaluator e = new ExprEvaluator(bo, _guardexpr);
    float flt = e.evaluate();
    if(flt != ExprEvaluator.FALSE)
      return new SequentialIID(-1); //true
    else
      return getIID();
  }//end evaluate
  
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository rep = getObjectContext().getRepository();
    _transitioniid  = rep.makeReposID(rs.getLong(COL_TRANSITIONID));
    _guardkind      = GuardKind.getInstance(rs.getInt(COL_GUARDKIND));
    _guardexpr      = rs.getString(COL_GUARDEXPRESSION);
  }//end load
  
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
          throw new ORIOException("Guard object not found.");
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
        //ADD  
        qp.update("INSERT INTO "+TABLE+
                  " ("+COL_OBJECTID+", "+COL_BYTEGUID+", "+COL_NAME+", "+COL_DESCRIPTION+", "+
                       COL_TRANSITIONID+", "+COL_CONFIGKIND+", "+COL_DELETESTATE+", "+
                       COL_GUARDKIND+", "+COL_ISACTIVE+") VALUES ("+
                       iid.getLongValue()+", '"+guid.toString()+"', '"+SQLUtil.primer(name)+"', '"+SQLUtil.primer(description)+"', "+
                       _transitioniid.getLongValue()+", "+
                       _configkind.getIntValue()+", "+_deletestate.getIntValue()+", "+_guardkind.getIntValue()+", "+(isactive?"1":"0")+")");
        psp = c.prepareProcessor("UPDATE "+TABLE+
                                 " SET "+COL_GUARDEXPRESSION+"= ?"+
                                 " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        if (_guardexpr != null && !_guardexpr.equals(""))
          psp.getStatement().setString(1, _guardexpr);
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
                  COL_TRANSITIONID+"="+_transitioniid.getLongValue()+", "+
                  COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
                  COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
                  COL_GUARDKIND+"="+_guardkind.getIntValue()+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
                  COL_DESCRIPTION+"='"+SQLUtil.primer(description)+"' "+
                  "WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        psp = c.prepareProcessor("UPDATE "+TABLE+
                                 " SET "+COL_GUARDEXPRESSION+"= ?"+
                                 " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        if (_guardexpr != null && !_guardexpr.equals(""))
          psp.getStatement().setString(1, _guardexpr);
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
    BMGuard guard = new BMGuard();
    guard.setIID(getIID());
    guard.setObjectContext(getObjectContext());
    guard.setPersState(getPersState());
    guard.setName(getName());
    guard.setDescription(getDescription());
    guard.setDeleteState(getDeleteState());
    guard.setConfigKind(getConfigKind());
    guard.isActive(isActive());
    guard.setTransitionIID(getTransitionIID());
    guard.setGuardKind(getGuardKind());
    guard.setGuardExpression(getGuardExpression());
    return guard;
  }//
}