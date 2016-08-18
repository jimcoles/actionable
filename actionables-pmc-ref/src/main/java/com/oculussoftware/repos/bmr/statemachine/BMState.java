package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* Filename:    BMState.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMState extends BMModelElement implements IRState
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  //CONSTANTS
  public static final String TABLE              = "STATE";
  public static final String COL_STATEMACHINEID = "STATEMACHINEID";
  public static final String COL_ROLEID         = "ROLEID";
  public static final String COL_STATEKIND      = "STATEKIND";

  
  protected IIID             _statemachineiid;
  protected IIID             _roleiid;
  protected StateKind        _statekind;
  
  /**
  *
  */
  public BMState() throws OculusException
  {
    guid = new GUID();
  }//end constructor
  
  public IRTransitionColl getTransitionColl() throws OculusException
  { return getTransitionColl(false); }
  
  /**
  *
  */
  public IRTransitionColl getTransitionColl(boolean edit) throws OculusException
  {
    return (IRTransitionColl)context.getCRM().getCompObject(context,"TransitionColl",iid, edit);
  }//end getTransitionList
  
  //ACCESSORS
  public IIID getRoleIID() throws ORIOException { return _roleiid; }
  public IProcessRole     getRoleObject() throws OculusException
  { return getRoleObject(false); }
  public IProcessRole     getRoleObject(boolean edit) throws OculusException
  {
    return (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(),"Role",_roleiid,edit);
  }
  public IIID getStateMachineIID() throws ORIOException { return _statemachineiid; }
  public StateKind getStateKind() throws ORIOException { return _statekind; }
  public IRStateMachine getStateMachineObject() throws OculusException
  { return getStateMachineObject(false); }
  public IRStateMachine getStateMachineObject(boolean edit) throws OculusException 
  { return (IRStateMachine)context.getCRM().getCompObject(context,"StateMachine",_statemachineiid,edit); }

  public IRTransition getTransitionObject(IIID transIID) throws OculusException
  { return getTransitionObject(transIID, false); }
  
  public IRTransition getTransitionObject(IIID transIID, boolean edit) throws OculusException
  {
    if(transIID == null)
      return (IRTransition)context.getCRM().getCompObject(context,"Transition",(IDataSet)null,edit);
    else
      return (IRTransition)context.getCRM().getCompObject(context,"Transition",transIID,edit);
  }//end getTransitionObject
  
  //MUTATORS
  public IRState setRoleIID(IIID r) throws ORIOException 
  { 
    _roleiid = r;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setRoleIID
  
  public IRState setStateMachineIID(IIID s) throws ORIOException 
  { 
    _statemachineiid = s;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setStateMachineIID
  
  public IRState setStateKind(StateKind s) throws ORIOException 
  { 
    _statekind = s; 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setStateKind
  
  //FUNCTIONAL METHODS
  public IRTransitionColl getEnabledTransitions(IBusinessObject o) throws OculusException
  { return getEnabledTransitions(o,false); }
    
  public IRTransitionColl getEnabledTransitions(IBusinessObject o, boolean edit) throws OculusException
  { 
    IRTransitionColl trans = getTransitionColl(edit);
    while(trans.hasMoreTransitions())
    {
      IRTransition t = trans.nextTransition();
      if(!t.isEnabled(o))
        trans.remove(t.getIID());
    }//end while
    trans.reset();
    return trans; 
  }//end getEnabledTransitions
  public IRTransitionColl getReferenceTransitionColl() throws OculusException
  { return getReferenceTransitionColl(false); }
  public IRTransitionColl getReferenceTransitionColl(boolean edit) throws OculusException
  {
    return (IRTransitionColl)context.getCRM().getCompObject(context,"ReferenceTransitionColl",iid,edit);
  }//
  
  public boolean isFinal() throws ORIOException
  { return (_statekind.getIntValue() == StateKind.FINAL.getIntValue() || _statekind.getIntValue() == StateKind.START_FINAL.getIntValue()); }
    
  public boolean isStart() throws ORIOException
  { return (_statekind.getIntValue() == StateKind.START.getIntValue() || _statekind.getIntValue() == StateKind.START_FINAL.getIntValue()); }
  
  public IRTransition createTransition() throws OculusException
  {
    IRTransition trans = (IRTransition)context.getCRM().getCompObject(context,"Transition",(IDataSet)null,true);
    trans.setFromStateIID(getIID());
    return trans;
  }
  
  
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository rep = context.getRepository();
    _statemachineiid = rep.makeReposID(rs.getLong(COL_STATEMACHINEID));
    _roleiid         = rep.makeReposID(rs.getLong(COL_ROLEID));
    _statekind       = StateKind.getInstance(rs.getInt(COL_STATEKIND));
  }//end Constructor
  
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
          throw new ORIOException("State object not found.");
      }//end try
      catch(Exception e) { throw new OculusException(e); }
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
        //INSERT
        qp.update("INSERT INTO "+TABLE+
                  " ("+COL_OBJECTID+", "+COL_BYTEGUID+", "+COL_NAME+", "+COL_STATEMACHINEID+", "+
                  COL_ROLEID+", "+COL_STATEKIND+", "+COL_DELETESTATE+", "+COL_ISACTIVE+", "+COL_CONFIGKIND+", "+COL_DESCRIPTION+") VALUES ("+
                  iid.getLongValue()+", '"+guid.toString()+"', '"+SQLUtil.primer(name)+"', "+_statemachineiid.getLongValue()+", "+
                  _roleiid.getLongValue()+", "+_statekind.getIntValue()+", "+_deletestate.getIntValue()+", "+(isactive?"1":"0")+", "+
                  _configkind.getIntValue()+", '"+SQLUtil.primer(description)+"')");
        setPersState(PersState.UNMODIFIED);
      }//end if
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        qp.update("UPDATE "+TABLE+" SET "+
                  COL_NAME+"='"+SQLUtil.primer(name)+"', "+
                  COL_STATEMACHINEID+"="+_statemachineiid.getLongValue()+", "+
                  COL_ROLEID+"="+_roleiid.getLongValue()+", "+
                  COL_STATEKIND+"="+_statekind.getIntValue()+", "+
                  COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
                  COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
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
    BMState state = new BMState();
    state.setIID(getIID());
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());
    state.setStateMachineIID(getStateMachineIID());
    state.setRoleIID(getRoleIID());
    state.setStateKind(getStateKind());
    return state;
  }//
}