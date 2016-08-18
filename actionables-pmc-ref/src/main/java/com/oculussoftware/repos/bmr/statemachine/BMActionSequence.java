package com.oculussoftware.repos.bmr.statemachine;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/**
* Filename:    BMActionSequence.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMActionSequence extends BMModelElementList implements IRActionSequence
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BMActionSequence() throws OculusException
  {
    super();
    _isOrdered = true;
  }//
  
  //-------------------------- IRActionSequence ----------------------
  
  public boolean hasMoreActions()
  { return hasNext(); }
  
  public IRAction nextAction() throws OculusException
  { return (IRAction)next(); }
  
  public String getClassName() { return "Action"; }
  //--------------------------- IRModelElementColl -------------------
  
  public String getLoadQuery()
  { 
    return "SELECT * FROM "+BMAction.TABLE+
           " WHERE "+BMAction.COL_DELETESTATE+"="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+BMAction.COL_TRANSITIONID+"="+_iid.getLongValue()+
           " ORDER BY "+BMAction.COL_ORDERNUM;
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BMActionSequence aSeq = new BMActionSequence();
    aSeq.setIID(_iid);
    aSeq._coll.addAll(this._coll);
    aSeq.reset();
    return aSeq;
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
  
}//end BMActionSequence