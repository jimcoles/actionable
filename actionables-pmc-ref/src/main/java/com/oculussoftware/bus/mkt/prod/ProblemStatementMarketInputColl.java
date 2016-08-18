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
* Filename:    ConceptFolderColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the concepts for a given folder

*/


public class ProblemStatementMarketInputColl extends ProblemStatementColl implements IProblemStatementColl
{
  
  public ProblemStatementMarketInputColl() throws OculusException
  {
    super();
  }

  protected ProblemStatementMarketInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
 
  protected String getLoadQuery()
    throws ORIOException
  { 
    return " SELECT mi.* "+
           " FROM PROBLEMSTATEMENT mi LEFT OUTER JOIN SEMANTICLINK sl ON mi.OBJECTID=sl.SRCOBJECTID "+
           " WHERE sl.DESTOBJECTID="+getIID().getLongValue()+
             "  AND sl.LINKTYPE="+LinkKind.INPUT.getIntValue()+
             "  AND mi.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY sl.ORDERNUM";
  }
  
  
  public Object dolly() throws OculusException
  {
    ProblemStatementMarketInputColl featList = null;
      featList = new ProblemStatementMarketInputColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}