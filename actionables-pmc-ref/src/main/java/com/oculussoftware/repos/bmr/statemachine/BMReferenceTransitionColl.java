package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMReferenceTransitionColl.java
* Date:        4/4/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMReferenceTransitionColl extends BMTransitionColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMReferenceTransitionColl() throws OculusException
  {
    super();
  }//
  
  //-------------------------- IRModelElementColl --------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMTransition.TABLE+
           " WHERE "+BMTransition.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+BMTransition.COL_TOSTATEID+"="+_iid.getLongValue()+
					 " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMReferenceTransitionColl tColl = new BMReferenceTransitionColl();
    tColl.setIID(_iid);
    tColl._coll.addAll(this._coll);
    tColl.reset();
    return tColl;
  }//end clone
  
  
  
}//end class