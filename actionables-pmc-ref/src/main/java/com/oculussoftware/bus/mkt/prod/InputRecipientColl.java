package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.common.org.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    InputRecipientColl.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a InputRecipientColl.
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

public class InputRecipientColl extends UserColl implements IUserColl
{
  
  //protected String 
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public InputRecipientColl() throws OculusException
  {
   super();
   TABLE = "APPUSER";
  }  
  protected InputRecipientColl (Comparator sortCrit) throws OculusException
  {
  super(sortCrit);
  }  
//----------------- IPoolable Methods ------------------------------------
/**
  *  Returns a copy of the current product object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
public Object dolly() throws OculusException
{
  InputRecipientColl usrColl = null;
  usrColl = new InputRecipientColl();
  usrColl.setIID(_iid);
  usrColl._items = this._items;
  usrColl.reset();
  return usrColl;
}
  protected String getClassName() { return "User"; }  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException  
  {
  return " SELECT appuser.* " +
       " FROM APPUSER appuser LEFT OUTER JOIN INPUTRECIPIENT inp ON inp.RECIPIENTID=appuser.OBJECTID WHERE inp.DISPOSITIONID="+getIID()+" AND appuser.DELETESTATE=1 "+
       " ORDER BY appuser.LASTNAME, appuser.FIRSTNAME";       
  }  
  
}