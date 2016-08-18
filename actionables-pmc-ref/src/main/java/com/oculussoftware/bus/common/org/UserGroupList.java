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
* Filename:    UserGroupColl.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a UserGroupColl.
*   Its expected parent is an group 
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

public class UserGroupList extends BusinessObjectList implements  IPersistable, IUserList
{
  
  //protected String 
  protected String COL_USERID = "USERID";
  protected String COL_GROUPID = "GROUPID";
  protected String COL_ACTIVE = "ACTIVE";
  protected String TABLE_USER = "APPUSER";
  protected String COL_DELETESTATE = "DELETESTATE";
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public UserGroupList() throws OculusException
  {
	 super();
	 TABLE = "USERGROUPASC";
  }  
  protected UserGroupList (Comparator sortCrit) throws OculusException
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
	UserGroupList usrColl = null;
	usrColl = new UserGroupList();
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
	return " SELECT UID.OBJECTID FROM "+TABLE_USER+" UID, " + TABLE + " UGA " +
		   "WHERE UID." + COL_OBJECTID + " = " + "UGA." + COL_USERID +
		   " AND UID."+ COL_DELETESTATE+" <> " + DeleteState.DELETED.getIntValue() +
		   " AND UGA." + COL_GROUPID + "="+getIID().getLongValue() + " ORDER BY LOWER(LASTNAME), LOWER(FIRSTNAME)";
		   //" AS OBJECTID FROM "+TABLE+ 
		   //" WHERE ACTIVE>0 AND "+COL_GROUPID+"="+getIID().getLongValue();
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
	UserGroupList sortedList = new UserGroupList(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }  
}
