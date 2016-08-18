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
* Filename:    UserNoGroupColl.java
* Date:        5/11/00
* Description: A collection of users who belong to NO group(s) at all
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class UserNoGroupColl extends UserColl implements IUserColl
{
  
  //protected String 
  protected String COL_NAME = "NAME";
  protected String COL_ORGID = "ORGID";
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public UserNoGroupColl() throws OculusException
  {
	 super();
	 TABLE = "APPUSER";
  }    
  protected UserNoGroupColl (Comparator sortCrit) throws OculusException
  {
	super(sortCrit);
  }    
  public Object dolly() throws OculusException
{
	UserNoGroupColl usrColl = null;
	usrColl = new UserNoGroupColl();
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

	  return " SELECT OBJECTID " +
		   " FROM "+TABLE + 
		   " WHERE OBJECTID NOT IN (SELECT USERID FROM USERGROUPASC)";
		   
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
	UserNoGroupColl sortedList = new UserNoGroupColl(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }      
}
