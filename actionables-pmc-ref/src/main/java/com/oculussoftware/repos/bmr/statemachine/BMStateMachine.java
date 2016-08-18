package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;
import java.math.*;
import java.sql.*;
import java.util.*;

/**
* Filename:    BMStateMachine.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMStateMachine extends BMModelElement implements IRStateMachine
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  public static final String TABLE       = "STATEMACHINE";
  public static final String COL_CLASSID = "CONTROLCLASSID";
  
  protected IIID _classiid;

  /**
  *
  */
  public BMStateMachine() throws OculusException
  {
    guid = new GUID();
  }//end constructor
  
  public IRStateColl getStateColl() throws OculusException
  { return getStateColl(false); }  

  /**
  *
  */
  public IRStateColl getStateColl(boolean edit) throws OculusException
  {
    return (IRStateColl)context.getCRM().getCompObject(context,"StateColl",iid, edit);
  }//end getStateColl

  //ACCESSORS
  public IIID getClassIID() throws ORIOException { return _classiid; }
 
  public IRState getStateObject(IIID id) throws OculusException
  { return getStateObject(id, false); }
  
  public IRState getStateObject(IIID id, boolean edit) throws OculusException
  {
    if(id == null)
      return (IRState)context.getCRM().getCompObject(context,"State",(IDataSet)null, edit);   
    else
      return (IRState)context.getCRM().getCompObject(context,"State",id, edit);
  }//end getStateObject
  
  //MUTATORS
  public IRStateMachine setClassIID(IIID c) throws ORIOException 
  { 
    _classiid = c; 
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }//end setClassIID
  
  public IRState getDefaultStartStateObject() throws OculusException
  { return getDefaultStartStateObject(false); }
  
  public IRState getDefaultStartStateObject(boolean edit) throws OculusException
  {
    IRState s = null;
    IRStateColl states = getStateColl(edit);
    while(states.hasMoreStates())
    {
      s = states.nextState();
      if(s.getStateKind() == StateKind.START)
        break;
    }//end while
    return s;
  }//end getDefaultStartState
  
  //FUNCTIONAL METHODS
  public IRTransitionColl getEnabledTransitions(IBusinessObject o) throws OculusException
  { return getEnabledTransitions(o, false); }
   
  public IRTransitionColl getEnabledTransitions(IBusinessObject o,boolean edit) throws OculusException
  { return o.getStateObject().getEnabledTransitions(o,edit); }
  
  public IIID doTransition(IBusinessObject o, IRTransition t, String processChangeComment) throws OculusException
  {
    if(t.getFromStateIID().getLongValue() != o.getStateObject().getIID().getLongValue())
      throw new OInvalidTransitionException("This transition does not go with this state.");
    else
      return t.execute(o,processChangeComment);
  }//end doTransition
  
  public IRState createState() throws OculusException
  {
    IRState state = (IRState)context.getCRM().getCompObject(context,"State",(IDataSet)null, true);
    state.setStateMachineIID(getIID());
    return state;
  }
  
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository rep = getObjectContext().getRepository();
    _classiid    = rep.makeReposID(rs.getLong(COL_CLASSID));
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
          throw new ORIOException("StateMachine object not found.");
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
                   " ("+COL_OBJECTID+", "+COL_BYTEGUID+", "+COL_NAME+", "+COL_CLASSID+", "+
                   COL_DELETESTATE+", "+COL_ISACTIVE+", "+COL_CONFIGKIND+", "+COL_DESCRIPTION+") VALUES ("+
                   iid.getLongValue()+", '"+guid.toString()+"', '"+SQLUtil.primer(name)+"', "+_classiid.getLongValue()+", "+
                   _deletestate.getIntValue()+", "+(isactive?"1":"0")+", "+_configkind.getIntValue()+", '"+SQLUtil.primer(description)+"')");
        setPersState(PersState.UNMODIFIED);
      }//end if
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        qp.update("UPDATE "+TABLE+" SET "+
                  COL_NAME+"='"+SQLUtil.primer(name)+"', "+
                  COL_CLASSID+"="+_classiid.getLongValue()+", "+
                  COL_DELETESTATE+"="+_deletestate.getIntValue()+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
                  COL_CONFIGKIND+"="+_configkind.getIntValue()+", "+
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
    finally{ if(qp!=null) qp.close(); }
    return this; 
  }//end add
  
  /**
  *
  */
  public Object dolly() throws OculusException
  {
    BMStateMachine statemachine = new BMStateMachine();
    statemachine.setIID(getIID());
    statemachine.setObjectContext(getObjectContext());
    statemachine.setPersState(getPersState());
    statemachine.setName(getName());
    statemachine.setDescription(getDescription());
    statemachine.setDeleteState(getDeleteState());
    statemachine.setConfigKind(getConfigKind());
    statemachine.isActive(isActive());
    statemachine.setClassIID(getClassIID());
    return statemachine;
  }//
}