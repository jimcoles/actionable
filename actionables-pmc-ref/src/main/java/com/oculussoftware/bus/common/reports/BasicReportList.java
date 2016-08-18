package com.oculussoftware.bus.common.reports;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.reports.*;

/**
* Filename:    BasicReportList.java
* Date:        7/14/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BasicReportList extends BMModelElementList implements IBasicReportList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public BasicReportList() throws OculusException
  {
    super();
    _isOrdered = true;
  }//
  
  //-------------------------- IRActionSequence ----------------------
  
  public boolean hasMoreBasicReports()
  { return hasNext(); }
  
  public IBasicReport nextBasicReport() throws OculusException
  { return (IBasicReport)next(); }
  
  public String getClassName() { return "BasicReport"; }
  //--------------------------- IRModelElementColl -------------------
  
  public String getLoadQuery()
  { 
    return "SELECT * FROM DATAVIEW "+
           " WHERE VIEWTYPE ="+ViewType.CUSTOMREPORT+
           " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    BasicReportList aSeq = new BasicReportList();
    aSeq.setIID(_iid);
    aSeq._coll.addAll(this._coll);
    aSeq.reset();
    return aSeq;
  }//end clone
}//end BasicReportList