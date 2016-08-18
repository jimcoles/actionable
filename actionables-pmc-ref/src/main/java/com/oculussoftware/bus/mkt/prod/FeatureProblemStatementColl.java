package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    FeatureConceptColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the features associated with the market input. Association between feature
& marketinput is stored in the sematic link table with the SRCOBJECTID as (Marketinput)
having a funcitonla dependency on Feature DESTOBJECTID

*/


public class FeatureProblemStatementColl extends FeatureColl implements IFeatureColl
{
  
  /** Default constructor just initializes the product list */
  public FeatureProblemStatementColl() throws OculusException
  {
    super();
  }

  protected FeatureProblemStatementColl (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  
  
   protected String getLoadQuery()
    throws ORIOException
  { 
    return " SELECT feat.* "+
           " FROM FEATURE feat LEFT OUTER JOIN SEMANTICLINK sl ON feat.OBJECTID=sl.SRCOBJECTID "+
           " WHERE sl.DESTOBJECTID="+getIID().getLongValue()+
             "  AND sl.LINKTYPE="+LinkKind.PROBLEMSTATEMENT.getIntValue()+
             "  AND feat.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY sl.ORDERNUM";
  }
  
  
  public Object dolly() throws OculusException
  {
    FeatureProblemStatementColl featList = null;
      featList = new FeatureProblemStatementColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }

  
}