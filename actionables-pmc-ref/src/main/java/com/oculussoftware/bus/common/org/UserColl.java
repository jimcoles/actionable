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
* Filename:    UserColl.java
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

public class UserColl extends BusinessObjectColl implements IUserColl
{
  
  //protected String 
  protected String COL_NAME = "NAME";
  protected String COL_ORGID = "ORGID";
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public UserColl() throws OculusException
  {
	 super();
	 TABLE = "APPUSER";
  }  
  protected UserColl (Comparator sortCrit) throws OculusException
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
	UserColl usrColl = null;
	usrColl = new UserColl();
	usrColl.setIID(_iid);
	usrColl._items = this._items;
	usrColl.reset();
	return usrColl;
}
  protected String getClassName() { return "User"; }  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
	throws ORIOException
  {
	return " SELECT OBJECTID" +
		   " FROM "+TABLE + 
		   " WHERE " + COL_DELETESTATE+" <> " + DeleteState.DELETED.getIntValue() +
      " AND LOGINID IS NOT NULL ";
  }  
  public boolean hasMoreUsers()
  {
	 return hasNext(); 
  }  
  //----------------------------Iterator Methods------------------------------
  
  public IUser nextUser() throws OculusException
  {
	return (IUser)next();
  }  
 //------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
	throws OculusException
  {
	UserColl sortedList = new UserColl(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }  
}
