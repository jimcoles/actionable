package com.oculussoftware.bus.common.search;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.sysi.license.*;

import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.repos.query.*;

import com.oculussoftware.system.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.bus.xmeta.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.repos.*;
import java.util.*;
import java.sql.*;

/**
* Filename:    SearchUtil.java
* Date:        7-28-00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public final class SearchUtil
{	
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
  
  public static boolean isMarketInput(long classid) throws OculusException
  {
    return (classid == IDCONST.STANDARDINPUT.getLongValue()
        || classid == IDCONST.ARTICLEINPUT.getLongValue()
        || classid == IDCONST.WINLOSSINPUT.getLongValue()
        || classid == IDCONST.SUMMARYINPUT.getLongValue()
        || classid == IDCONST.IMPORTEDINPUT.getLongValue()
        || classid == IDCONST.QUESTIONINPUT.getLongValue());
  }
    
  public static IXClassAttr getObjectIDAttr(IXClass xcls) throws OculusException
  {
    if(xcls == null) return null;
    else if(xcls.getIID().equals(IDCONST.PRODUCT.getIIDValue())) return XMen.ATTR_PROD_OID;
    else if(xcls.getIID().equals(IDCONST.PRODUCTVERSION.getIIDValue())) return XMen.ATTR_VER_OID;
    else if(xcls.getIID().equals(IDCONST.CATEGORY.getIIDValue())) return XMen.ATTR_CAT_OID;
    else if(xcls.getIID().equals(IDCONST.FEATURECATEGORYLINK.getIIDValue())) return XMen.ATTR_FEATLINK_OID;
    else if(xcls.getIID().equals(IDCONST.FEATURE.getIIDValue())) return XMen.ATTR_FEAT_OID;
    else if(xcls.getIID().equals(IDCONST.FEATUREREVISION.getIIDValue())) return XMen.ATTR_FEATREV_OID;
    else if(xcls.getIID().equals(IDCONST.DISCUSSIONTOPIC.getIIDValue())) return XMen.ATTR_DISCTOPIC_OID;
    else if(xcls.getIID().equals(IDCONST.STANDARDSCOLLECTION.getIIDValue())) return XMen.ATTR_STDCOLL_OID;
    else if(xcls.getIID().equals(IDCONST.STANDARDINPUT.getIIDValue())) return XMen.ATTR_SMI_OID;
    else if(xcls.getIID().equals(IDCONST.ARTICLEINPUT.getIIDValue())) return XMen.ATTR_AMI_OID;
    else if(xcls.getIID().equals(IDCONST.QUESTIONINPUT.getIIDValue())) return XMen.ATTR_QMI_OID;
    else if(xcls.getIID().equals(IDCONST.WINLOSSINPUT.getIIDValue())) return XMen.ATTR_WMI_OID;
    else if(xcls.getIID().equals(IDCONST.SUMMARYINPUT.getIIDValue())) return XMen.ATTR_SUMMI_OID;
    else if(xcls.getIID().equals(IDCONST.PROBLEMSTATEMENT.getIIDValue())) return XMen.ATTR_PS_OID;
    else if(xcls.getIID().equals(IDCONST.USER.getIIDValue())) return XMen.ATTR_USER_OID;
    else if(xcls.getIID().equals(IDCONST.ORGANIZATION.getIIDValue())) return XMen.ATTR_ORG_OID;
    else if(xcls.getIID().equals(IDCONST.REACTION.getIIDValue())) return XMen.ATTR_REACT_OID;
    else if(xcls.getIID().equals(IDCONST.MCLS_STATE.getIIDValue())) return XMen.ATTR_STATE_OID;
    else if(xcls.getIID().equals(IDCONST.MCLS_CLASS.getIIDValue())) return XMen.ATTR_CLASS_OID;
   
    throw new OculusException("No Object ID defined ("+xcls.getDisplayName()+")("+xcls.getIID()+").");
  }//
  
  
}//