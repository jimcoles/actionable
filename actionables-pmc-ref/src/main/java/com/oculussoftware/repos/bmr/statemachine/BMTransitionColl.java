package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMTransitionColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMTransitionColl extends BMModelElementColl implements IRTransitionColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMTransitionColl() throws OculusException
  {
    super();
  }//
  
  //-------------------------- IRTransitionColl ----------------------
  
  public boolean hasMoreTransitions()
  { return hasNext(); }
  
  public IRTransition nextTransition() throws OculusException
  { return (IRTransition)next(); }
  
  protected String getClassName() { return "Transition"; }
  //-------------------------- IRModelElementColl --------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM "+BMTransition.TABLE+
           " WHERE "+BMTransition.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+BMTransition.COL_FROMSTATEID+"="+_iid.getLongValue()+
					 " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMTransitionColl tColl = new BMTransitionColl();
    tColl.setIID(_iid);
    tColl._coll.addAll(this._coll);
    tColl.reset();
    return tColl;
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