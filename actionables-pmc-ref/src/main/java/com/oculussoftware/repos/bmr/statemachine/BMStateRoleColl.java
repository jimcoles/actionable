package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMStateRoleColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMStateRoleColl extends BMStateColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMStateRoleColl() throws OculusException
  {
    super();
  }//
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMState.TABLE+
           " WHERE "+BMState.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+BMState.COL_ROLEID+"="+_iid.getLongValue()+
					 " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMStateRoleColl sColl = new BMStateRoleColl();
    sColl.setIID(_iid);
    sColl._coll.addAll(this._coll);
    sColl.reset();
    return sColl;
  }//end clone
}//end class