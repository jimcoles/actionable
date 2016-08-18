package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMStateMachineColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMStateMachineColl extends BMModelElementColl implements IRStateMachineColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMStateMachineColl() throws OculusException
  {
    super();
  }//
  
  //-------------------------- IRStateMachineColl --------------------
  
  public boolean hasMoreStateMachines()
  { return hasNext(); }
  
  public IRStateMachine nextStateMachine() throws OculusException
  { return (IRStateMachine)next(); }
  
  public String getClassName() { return "StateMachine"; }
  //-------------------------- IRModelElementColl --------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMStateMachine.TABLE+
           " WHERE DELETESTATE="+DeleteState.NOT_DELETED.getIntValue()+
					 " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMStateMachineColl smColl = new BMStateMachineColl();
    smColl.setIID(_iid);
    smColl._coll.addAll(this._coll);
    smColl.reset();
    return smColl;
  }//end clone
  
  //////////////////////////////////////////////////////////////////
  
  public IRCollection refresh()
    throws OculusException
  { throw new OculusException("refresh not implemented"); }
    
  public IRCollection setFilter()
    throws OculusException
  { throw new OculusException("setFilter not implemented"); }
  
  public IRCollection setSort(java.util.Comparator sortCrit)
    throws OculusException
  { throw new OculusException("setSort not implemented"); }

  public IRCollection setGroupBy()
    throws OculusException
  { throw new OculusException("setGroupBy not implemented"); }
}//end class