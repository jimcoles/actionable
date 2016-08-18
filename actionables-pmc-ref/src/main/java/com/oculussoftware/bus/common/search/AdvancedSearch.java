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
* $Workfile: AdvancedSearch.java $
* Create Date:  7/27/2000
* Description: Represents a simple but general business object query.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author Egan Royal
* Version 1.2
*
* $History: AdvancedSearch.java $
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 9/14/00    Time: 3:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 2544
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 8/07/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * clean up
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 8/04/00    Time: 3:47p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * advsrch and custom reports
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 8/02/00    Time: 6:44p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * added more functionality to Custom Report and Adv Srch
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/28/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * adv srch
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/28/00    Time: 11:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * added functionality to Advanced search.
 * made IBasicReport and BasicReport implement IAdvancedSearch
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 7/27/00    Time: 7:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * added more functionality to custom reports and added functionality to
 * advanced search
*/
public class AdvancedSearch extends Search implements IAdvancedSearch
{

  public AdvancedSearch(IObjectContext c, long targetclass)
    throws OculusException
  {
    super();
    if(targetclass == -1)
      throw new OculusException("-1 Target Class.");
    setObjectContext(c);  
    setTargetClass(targetclass);  
    IXClass target = getTargetClass();
    List selectAttrs = target.getKeywordSelectAttrs();
    for (Iterator j = selectAttrs.iterator(); j.hasNext(); )
    {
      Object attr = j.next();
      if (attr instanceof IXClassAttr)
      {
        IXClass newTarget = target;
        long[] assoc = new long[] {};
        if (target.equals(XMen.CLS_ARTICLEINPUT) ||
            target.equals(XMen.CLS_QUESTIONINPUT) ||
            target.equals(XMen.CLS_STANDARDINPUT) ||
            target.equals(XMen.CLS_SUMMARYINPUT) ||
            target.equals(XMen.CLS_WINLOSSINPUT))
        {
          newTarget = XMen.CLS_FOLDERINPUTLINK;
          if (target.equals(XMen.CLS_ARTICLEINPUT))
            assoc = new long[] {XMen.ASC_FLINK_AMI.getIID().getLongValue()};
          else if (target.equals(XMen.CLS_QUESTIONINPUT))
            assoc = new long[] {XMen.ASC_FLINK_QMI.getIID().getLongValue()};
          else if (target.equals(XMen.CLS_STANDARDINPUT))
            assoc = new long[] {XMen.ASC_FLINK_SMI.getIID().getLongValue()};
          else if (target.equals(XMen.CLS_SUMMARYINPUT))
            assoc = new long[] {XMen.ASC_FLINK_SUMMI.getIID().getLongValue()};
          else if (target.equals(XMen.CLS_WINLOSSINPUT))
            assoc = new long[] {XMen.ASC_FLINK_WMI.getIID().getLongValue()};
        }
        addDisplayAttr(assoc, ((IXClassAttr)attr).getIID().getLongValue());
      }
      else
        addDisplayLiteral(attr.toString(),"CLASSID");
    }//end for
  }
    
  public IGenericObjectCollection getObjects() throws OculusException
  {
    IGenericObjectCollection objs = new GenericObjectSet(getObjectContext());
    objs.load(retrieve(),"OBJECTID","CLASSID");
    return objs;
  }
  
  public Object dolly() throws OculusException
  { return null; }
  
  public ViewType getViewType() throws OculusException
  { return ViewType.ADVANCEDSEARCH; } 
  
  
}