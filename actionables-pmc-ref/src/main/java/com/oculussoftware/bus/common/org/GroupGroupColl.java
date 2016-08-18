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
* Filename:    GroupColl.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a GroupColl.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.1
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class GroupGroupColl extends BusinessObjectColl implements IGroupColl
{
  
  //protected String 
  protected String COL_NAME = "NAME";
  protected String COL_PARGROUPID = "PARGROUPID";
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public GroupGroupColl() throws OculusException
  {
	 super();
	 TABLE = "USERGROUP";
  }  
  protected GroupGroupColl (Comparator sortCrit) throws OculusException
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
	GroupGroupColl grpColl = null;
	grpColl = new GroupGroupColl();
	grpColl.setIID(_iid);
	grpColl._items = this._items;
	grpColl.reset();
	return grpColl;
}
  protected String getClassName() { return "Group"; }  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
	throws ORIOException
  {
	  return " SELECT * FROM "+TABLE +
	  " WHERE "+COL_PARGROUPID+"=" + getIID().getLongValue() +
		" AND " + COL_DELETESTATE+" <> " + DeleteState.DELETED.getIntValue();
  }  
  public boolean hasMoreGroups()
  {
	 return hasNext(); 
  }  
  //----------------------------Iterator Methods------------------------------
  
  public IGroup nextGroup() throws OculusException
  {
	return (IGroup)next();
  }  
 //------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
	throws OculusException
  {
	GroupGroupColl sortedList = new GroupGroupColl(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }  
}
