package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMGuardColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMGuardColl extends BMModelElementColl implements IRGuardColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMGuardColl() throws OculusException
  {
    super();
  }//
  
  //-------------------------- IRGuardColl ---------------------------
  
  public boolean hasMoreGuards()
  { return hasNext(); }
  
  public IRGuard nextGuard() throws OculusException
  { return (IRGuard)next(); }
  
  public String getClassName() { return "Guard"; }
  //-------------------------- IRModelElement -------------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMGuard.TABLE+
            " WHERE "+BMGuard.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
            " AND "+BMGuard.COL_TRANSITIONID+"="+_iid.getLongValue()+
						" ORDER BY NAME"; 
  }//
  
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMGuardColl gColl = new BMGuardColl();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;
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