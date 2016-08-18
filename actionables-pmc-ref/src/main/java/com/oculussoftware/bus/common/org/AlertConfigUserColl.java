package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import java.sql.*;
import java.util.*;

/**
* Filename:    AlertConfigUserColl.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a UserColl.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class AlertConfigUserColl extends UserColl
{
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public AlertConfigUserColl() throws OculusException
  {
     super();
     TABLE = "ALERTRECIPIENT";
  }

  protected AlertConfigUserColl (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT USERID AS OBJECTID" +
           " FROM ALERTRECIPIENT"+ 
           " WHERE ALERTCONFIGID=" +getIID().getLongValue();
  }
  
 //------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    AlertConfigUserColl sortedList = new AlertConfigUserColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  } 
  //----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current product object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object clone()
  {
    AlertConfigUserColl usrColl = null;
    try
    {
      usrColl = new AlertConfigUserColl();
      usrColl.setIID(_iid);
      usrColl._items = this._items;
      usrColl.reset();
    }
    catch (OculusException orioExp) {}
    return usrColl;
  }
   
}