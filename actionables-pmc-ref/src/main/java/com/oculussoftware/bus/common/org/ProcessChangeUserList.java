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
* Filename:    ProcessChangeUserList.java
* Date:        7/7/00
* Description: Provides the functionality for a basic version for a UserColl.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessChangeUserList extends UserList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public ProcessChangeUserList() throws OculusException
  {
     super();
     TABLE = "PROCESSRECEIVER";
  }

  protected ProcessChangeUserList (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT USERID AS OBJECTID" +
           " FROM "+TABLE+ 
           " WHERE CHANGEID=" +getIID();
  }
  
 //------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ProcessChangeUserList sortedList = new ProcessChangeUserList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  } 
  //----------------- IPoolable Methods ------------------------------------

  public Object dolly() throws OculusException
  {
    ProcessChangeUserList usrColl = new ProcessChangeUserList();
    usrColl.setIID(_iid);
    usrColl._items = this._items;
    usrColl.reset();
    return usrColl;
  }
   
}