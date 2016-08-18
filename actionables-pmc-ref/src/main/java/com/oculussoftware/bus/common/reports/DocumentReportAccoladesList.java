package com.oculussoftware.bus.common.reports;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.license.ModuleKind;
import com.oculussoftware.api.busi.common.reports.*;

/**
* Filename:    DocumentReportAccoladesList.java
* Date:        8/16/00
* Description: Returns a list of Accolades reports that are public or 
*              belong to the user.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DocumentReportAccoladesList extends DocumentReportList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  /**
  *
  */
  public DocumentReportAccoladesList() throws OculusException
  {
    super();
  }//
  
  //--------------------------- IRModelElementColl -------------------
  
  public String getLoadQuery()
  { 
    return "SELECT * FROM DATAVIEW "+
           " WHERE VIEWTYPE ="+ViewType.DOCUMENTREPORT+
           " AND (OWNERID = "+_iid+" OR ACCESSID ="+IDCONST.ACCESS_PUBLIC+")"+
           " AND MODULECODE = "+ModuleKind.ACCOLADES+
           " ORDER BY NAME";
  }//
      
  //------------------------------ IPoolable --------------------------
  
  public Object dolly() throws OculusException
  { 
    DocumentReportAccoladesList aSeq = new DocumentReportAccoladesList();
    aSeq.setIID(_iid);
    aSeq._coll.addAll(this._coll);
    aSeq.reset();
    return aSeq;
  }//end clone
}//end DocumentReportAccoladesList