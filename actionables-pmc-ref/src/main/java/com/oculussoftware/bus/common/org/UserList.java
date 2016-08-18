package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.common.org.*;
import java.util.*;

/**
* Filename:    UserAlphaList.java
* Date:        
* Description: Handles a list of user objects
* sorts by last and then first name
*
* Copyright 1-31-2000 Productmarketing.com Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* BUG00220 			  Zain Nemazie	  5/17/00     UserList not checking deleteState
* BUG00287        Zain Nemazie		5/23/00			checking loginid for null since this is only users created through ADMIN 
*/

public class UserList extends com.oculussoftware.bus.BusinessObjectList implements  IPersistable, IUserList
{
	protected String COL_LASTNAME = "LASTNAME";
	protected String COL_FIRSTNAME = "FIRSTNAME";
	protected String _sortbyLastName = "''";
	protected String _sortbyFirstName = "''";
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public UserList() throws OculusException
	{
	super();
	TABLE = "APPUSER";
	}
//----------------------------- Protected Constructor -------------------------
/** Default constructor just initializes the list */
protected UserList(Comparator sortCrit) throws OculusException
{
	super(sortCrit);
}

//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
	UserList catList = null;
	  catList = new UserList();
	  catList.setIID(_iid);
	  catList._items = this._items;
	  catList.reset();
	return catList;
  }  
  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "User"; }  


 protected String getLoadQuery() throws ORIOException
	{
		return "SELECT OBJECTID" + " FROM " + TABLE + " WHERE DELETESTATE <> " + DeleteState.DELETED.getIntValue() + 
		" AND LOGINID IS NOT NULL "+
		" ORDER BY LASTNAME, FIRSTNAME";
	 //return "SELECT * FROM APPUSER WHERE lastname >= 'a' AND firstname >= '' ORDER BY LOWER(lastname), LOWER(firstname)";
	}
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreUsers()
  {
	return hasNext();
  }  
  //----------------- IFeatureColl Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IUser nextUser()
	throws OculusException
  {
	return (IUser)next();
  }  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
	throws OculusException
  {
	UserList sortedList = new UserList(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }  
}
