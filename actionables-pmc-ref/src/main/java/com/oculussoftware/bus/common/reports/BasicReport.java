package com.oculussoftware.bus.common.reports;

import com.oculussoftware.api.busi.common.reports.*;
import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import java.util.*;
import java.sql.Timestamp;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.repos.query.*;
import com.oculussoftware.repos.xml.*;
import com.oculussoftware.bus.xmeta.XMen;
import com.oculussoftware.util.*;
import com.oculussoftware.bus.common.search.*;

/**
* Filename:    BasicReport.java
* Date:        7/14/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BasicReport extends Search implements IBasicReport
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
 
  /** 
  * Public Constructor 
  */
  public BasicReport() throws OculusException
  { super(); }
  
  public ViewType getViewType() throws OculusException
  { return ViewType.CUSTOMREPORT; }  
  
  /**
  * get the TargetClass and then decide which RCT
  */
  public ReportConfigType getReportConfigType() throws OculusException
  {
    IIID iid = getTargetClassIID();
    if(IDCONST.PRODUCT.equals(iid))
      return ReportConfigType.CUSTOM_GLOBAL;
    else if(IDCONST.PRODUCTVERSION.equals(iid))
      return ReportConfigType.CUSTOM_PRODUCT; 
    else if(IDCONST.CATEGORY.equals(iid))
      return ReportConfigType.CUSTOM_PRODUCT_VERSION;
    else if(IDCONST.FEATURECATEGORYLINK.equals(iid))
      return ReportConfigType.CUSTOM_FEATURE;
    else if(IDCONST.DISCUSSIONTOPIC.equals(iid))
      return ReportConfigType.CUSTOM_PRODUCT_VERSION;
    else if(IDCONST.STANDARDSCOLLECTION.equals(iid))
      return ReportConfigType.CUSTOM_GLOBAL;
    else if(IDCONST.STANDARDINPUT.equals(iid))
      return ReportConfigType.CUSTOM_FOLDER;
    else if(IDCONST.ARTICLEINPUT.equals(iid))
      return ReportConfigType.CUSTOM_FOLDER;
    else if(IDCONST.QUESTIONINPUT.equals(iid))
      return ReportConfigType.CUSTOM_FOLDER;
    else if(IDCONST.WINLOSSINPUT.equals(iid))
      return ReportConfigType.CUSTOM_FOLDER;
    else if(IDCONST.PROBLEMSTATEMENT.equals(iid))
      return ReportConfigType.CUSTOM_FOLDER;  
    return null;
  }
    
  public Object dolly() throws OculusException
  {
    IBasicReport obj = new BasicReport();
    obj.setIID(getIID());
    obj.setObjectContext(getObjectContext());
    obj.setPersState(getPersState());
    obj.setName(getName());
    obj.setDescription(getDescription());
    obj.setDeleteState(getDeleteState());
    obj.setConfigKind(getConfigKind());
    obj.isActive(isActive());
    obj.setOwnerIID(getOwnerIID());
    obj.setAccessKind(getAccessKind());
    obj.setModuleKind(getModuleKind());
    //dolly the xml
    if(getPersState().equals(PersState.NEW))
      obj.setXML(getXML());
    else
    {
      try
      {
        obj.setXML(new QueryXML(new java.io.ByteArrayInputStream(_xml.toXML().getBytes())));
      }
      catch(Exception e)
      {
        throw new OculusException(e);
      }
    }//end else
    return obj;
  }
}