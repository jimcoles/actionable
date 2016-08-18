package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMStateColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMStateColl extends BMModelElementColl implements IRStateColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMStateColl() throws OculusException
  {
    super();
  }//
  
  //-------------------------- IRStateColl ---------------------------
  
  public boolean hasMoreStates()
  { return hasNext(); }
  
  public IRState nextState() throws OculusException
  { return (IRState)next(); }
  
  public String getClassName() { return "State"; }
  //--------------------------- IRModelElementColl -------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMState.TABLE+
           " WHERE "+BMState.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+BMState.COL_STATEMACHINEID+"="+_iid.getLongValue()+
					 " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMStateColl sColl = new BMStateColl();
    sColl.setIID(_iid);
    sColl._coll.addAll(this._coll);
    sColl.reset();
    return sColl;
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