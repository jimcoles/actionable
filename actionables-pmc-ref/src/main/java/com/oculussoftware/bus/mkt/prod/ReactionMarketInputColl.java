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
* Filename:    ConceptMarketInputColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the concepts for a given folder

*/


public class ReactionMarketInputColl extends ReactionColl implements IReactionColl
{
  
  public ReactionMarketInputColl() throws OculusException
  {
    super();
  }

  protected ReactionMarketInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
 
  protected String getLoadQuery()
    throws ORIOException
  { 
    
    return "SELECT react.* FROM \"REACTION\" react LEFT OUTER JOIN \"FOLDERINPUTLINK\" inplink ON inplink.REACTIONID=react.OBJECTID LEFT OUTER JOIN \"MARKETINPUT\" input ON input.OBJECTID=inplink.MARKETINPUTID WHERE input.OBJECTID="+getIID().getLongValue();    
  }
  
  
  public Object dolly() throws OculusException
  {
    ReactionMarketInputColl featList = null;
      featList = new ReactionMarketInputColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}