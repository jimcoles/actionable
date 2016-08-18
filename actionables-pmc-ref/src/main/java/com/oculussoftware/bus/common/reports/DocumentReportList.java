package com.oculussoftware.bus.common.reports;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.reports.*;

/**
* Filename:    DocumentReportList.java
* Date:        8/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DocumentReportList extends BMModelElementList implements IDocumentReportList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public DocumentReportList() throws OculusException
  {
    super();
    _isOrdered = true;
  }//
  
  //-------------------------- IRActionSequence ----------------------
  
  public boolean hasMoreDocumentReports()
  { return hasNext(); }
  
  public IDocumentReport nextDocumentReport() throws OculusException
  { return (IDocumentReport)next(); }
  
  public String getClassName() { return "DocumentReport"; }
  //--------------------------- IRModelElementColl -------------------
  
  public String getLoadQuery()
  { 
    return "SELECT * FROM DATAVIEW "+
           " WHERE VIEWTYPE ="+ViewType.DOCUMENTREPORT+
           " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    DocumentReportList aSeq = new DocumentReportList();
    aSeq.setIID(_iid);
    aSeq._coll.addAll(this._coll);
    aSeq.reset();
    return aSeq;
  }//end clone
}//end DocumentReportList