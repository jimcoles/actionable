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


public class MarketInputFolderColl extends MarketInputColl implements IMarketInputColl
{
  
  public MarketInputFolderColl() throws OculusException
  {
    super();
  }

  protected MarketInputFolderColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
 
  protected String getClassName() { return "FolderInputLink"; }


  protected String getLoadQuery()
    throws ORIOException
  { 
    return "SELECT fil.* "+
           "FROM MARKETINPUT mi LEFT OUTER JOIN FOLDERINPUTLINK fil ON fil.MARKETINPUTID=mi.OBJECTID "+
           "WHERE fil.FOLDERID="+getIID().getLongValue()+
             " AND fil.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+" AND mi.DELETESTATE<>"+DeleteState.DELETED.getIntValue();
  }

  public Object next()
    throws OculusException
  {
    IMarketInput nextFeat = null;
    IFolderInputLink nextCatLink = null;
    while (nextFeat == null && hasNext())          // as long as we need to and can
    {
      IIID catlinkIID = (IIID)_ids.next();
      nextCatLink = (IFolderInputLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLink",catlinkIID,isLocked());
      nextFeat = nextCatLink.getInput();      
      nextFeat.setFolderInputLink(nextCatLink);
    }
    return nextFeat;
  }
  
  
  public Object dolly() throws OculusException
  {
    MarketInputFolderColl featList = null;
      featList = new MarketInputFolderColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}