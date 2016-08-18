package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.bus.common.process.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.util.*;
import java.util.*;
import com.oculussoftware.api.busi.*;
/**
* Filename:    BMTransition.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMTransition extends BMModelElement implements IRTransition
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  public static final String TABLE           = "TRANSITION";
  public static final String COL_FROMSTATEID = "FROMSTATEID";
  public static final String COL_TOSTATEID   = "TOSTATEID";
  
  protected IIID             _fromstateiid;
  protected IIID             _tostateiid;
  protected IIID             _statemachineiid;

  /**
  *
  */
  public BMTransition() throws OculusException
  {
    guid = new GUID();
  }//end constructor

  public IRActionSequence getActionSequence() throws OculusException
  { return getActionSequence(false); }

  /**
  *
  */
  public IRActionSequence getActionSequence(boolean edit) throws OculusException
  {
    return (IRActionSequence)context.getCRM().getCompObject(context,"ActionSequence",iid,edit);
  }//end getActionSequence

  public IRGuardColl getGuardColl() throws OculusException
  { return getGuardColl(false); }  
  
  /**
  *
  */
  public IRGuardColl getGuardColl(boolean edit) throws OculusException
  {
    return (IRGuardColl)context.getCRM().getCompObject(context,"GuardColl",iid,edit);
  }//end getGuardColl
  
  //ACCESSORS
  public IIID getFromStateIID() throws ORIOException { return _fromstateiid; }
  public IIID getToStateIID() throws ORIOException { return _tostateiid; }
  
  public IRState getFromStateObject() throws OculusException 
  { return getFromStateObject(false); }
  
  public IRState getFromStateObject(boolean edit) throws OculusException 
  { 
    return (IRState)context.getCRM().getCompObject(context,"State",_fromstateiid,edit); 
  }
  
  public IRState getToStateObject() throws OculusException 
  { return getToStateObject(false); }
  
  public IRState getToStateObject(boolean edit) throws OculusException 
  { 
    return (IRState)context.getCRM().getCompObject(context,"State",_tostateiid,edit); 
  }
  
  public IRAction getActionObject(IIID aIID) throws OculusException
  { return getActionObject(aIID,false); }
  
  public IRAction getActionObject(IIID aIID, boolean edit) throws OculusException
  {
    return (IRAction)context.getCRM().getCompObject(context,"Action",aIID,edit);
  }//end getActionObject
  
  public IRGuard getGuardObject(IIID gIID) throws OculusException
  { return getGuardObject(gIID,false); }
  
  public IRGuard getGuardObject(IIID gIID, boolean edit) throws OculusException
  {
    return (IRGuard)context.getCRM().getCompObject(context,"Guard",gIID,edit);
  }//end getGuardObject
  
  //MUTATORS
  public IRTransition setFromStateIID(IIID f) throws ORIOException 
  { 
    _fromstateiid = f;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setFromStateIID
  
  public IRTransition setStateMachineIID(IIID smiid) throws ORIOException
  {
    _statemachineiid = smiid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this;
  }//end setStateMachineIID
  
  public IRTransition setToStateIID(IIID t) throws ORIOException 
  { 
    _tostateiid = t; 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setToStateIID
  
  //FUNCTIONAL METHODS
  /**
  * returns true iff the transition isEnabled
  */
  public boolean isEnabled(IBusinessObject bo) throws OculusException
  { 
    return (isEnabledIIID(bo).getLongValue() == -1); 
  }//end isEnabled
  
  /**
  * returns -1 if isEnabled
  */
  private IIID isEnabledIIID(IBusinessObject bo) throws OculusException
  {
    IIID rv = new SequentialIID(-1);
    IRGuardColl guards = getGuardColl();
    try
    {
      while(guards.hasMoreGuards())
      {
        rv = guards.nextGuard().evaluate(bo);
        if(rv.getLongValue() != -1)
          break; 
      }//end while
    }//end try
    catch(OculusException ex) { throw ex; }
    return rv;
  }//end isEnabled

  public IRAction createAction() throws OculusException
  {
    IRAction action = (IRAction)context.getCRM().getCompObject(context,"Action",(IDataSet)null,true);
    action.setTransitionIID(getIID());
    return action;
  }
  
  public IRGuard createGuard() throws OculusException
  {
    IRGuard guard = (IRGuard)context.getCRM().getCompObject(context,"Guard",(IDataSet)null,true);
    guard.setTransitionIID(getIID());
    return guard;
  }
  
  /**
  *
  */
  public IIID execute(IBusinessObject bo, String processChangeComment) throws OculusException 
  { 
    IIID rv = isEnabledIIID(bo);
    IRActionSequence actions = getActionSequence();
    if(rv.getLongValue() == -1)
    {
      while(actions.hasMoreActions())
        actions.nextAction().execute(bo);
      //change the state of the object
      try
      {
        IRepository rep = getObjectContext().getRepository();
        bo.setStateObject(getToStateObject(false));
        bo.addProcessChange(this,processChangeComment);
      }//end try
      catch(Exception ex) { throw new OculusException(ex); }
    }//end if
    else
      throw new OculusException("Transition not enabled");
    return rv;
  }//end execute
    
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository rep = getObjectContext().getRepository();
    _fromstateiid   = rep.makeReposID(rs.getLong(COL_FROMSTATEID));
    _tostateiid     = rep.makeReposID(rs.getLong(COL_TOSTATEID));
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
          throw new ORIOException("Transition object not found.");
      }//end try
      catch(Exception e) { throw new ORIOException(e); }
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

    IRConnection c = null; IQueryProcessor qp = null; 
    try
    {
      c = getDatabaseConnection();
      qp = c.createProcessor();
      if(getPersState() == PersState.NEW)
      {
        qp.update("INSERT INTO "+TABLE+
                  " ("+COL_OBJECTID+", "+COL_BYTEGUID+", "+COL_NAME+", "+
                       COL_FROMSTATEID+", "+COL_TOSTATEID+", "+COL_ISACTIVE+", "+COL_CONFIGKIND+", "+COL_DELETESTATE+", "+COL_DESCRIPTION+") VALUES ("+
                       iid.getLongValue()+", '"+guid.toString()+"', '"+SQLUtil.primer(name)+"', "+
                       _fromstateiid.getLongValue()+", "+_tostateiid.getLongValue()+", "+(isactive?"1":"0")+", "+
                       _configkind.getIntValue()+", "+_deletestate.getIntValue()+", '"+SQLUtil.primer(description)+"')");
        setPersState(PersState.UNMODIFIED);
      }//end if
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        qp.update("UPDATE "+TABLE+" SET "+
                  COL_NAME+"='"+SQLUtil.primer(name)+"', "+
                  COL_FROMSTATEID+"="+_fromstateiid.getLongValue()+", "+
                  COL_TOSTATEID+"="+_tostateiid.getLongValue()+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
                  COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
                  COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
                  COL_DESCRIPTION+"='"+SQLUtil.primer(description)+"'"+
                  " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        setPersState(PersState.UNMODIFIED);
      }//end else if
      else if(getPersState() == PersState.DELETED)
      {
        //DELETE
        qp.update("UPDATE "+TABLE+" SET "+COL_DELETESTATE+"="+DeleteState.DELETED.getIntValue()+" WHERE "+COL_OBJECTID+"="+iid.getLongValue());
      }//end else if
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
    finally{if(qp!=null)qp.close();}
    return this; 
  }//end add
  
  /**
  *
  */
  public Object dolly() throws OculusException
  {
    BMTransition transition = new BMTransition();
    transition.setIID(getIID());
    transition.setObjectContext(getObjectContext());
    transition.setPersState(getPersState());
    transition.setName(getName());
    transition.setDescription(getDescription());
    transition.setDeleteState(getDeleteState());
    transition.setConfigKind(getConfigKind());
    transition.isActive(isActive());
    transition.setFromStateIID(getFromStateIID());
    transition.setToStateIID(getToStateIID());
    return transition;
  }//
}