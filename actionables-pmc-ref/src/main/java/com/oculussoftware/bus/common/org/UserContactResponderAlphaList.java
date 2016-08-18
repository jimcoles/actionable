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

public class UserContactResponderAlphaList extends com.oculussoftware.bus.BusinessObjectList implements  IPersistable, IUserList
{
	protected String COL_LASTNAME = "LASTNAME";
	protected String COL_FIRSTNAME = "FIRSTNAME";
	protected String _sortbyLastName = "";
	protected String _sortbyFirstName = "";
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public UserContactResponderAlphaList() throws OculusException
	{
	super();
	TABLE = "APPUSER";
	}
//----------------------------- Protected Constructor -------------------------
/** Default constructor just initializes the list */
protected UserContactResponderAlphaList(Comparator sortCrit) throws OculusException
{
	super(sortCrit);
}
  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
	throws OculusException
  {
	IIID iid = args.getIID();

	if (iid == null)
	  throw new OculusException("Object ID expected.");
	setIID(iid);

	if (context == null)
	  throw new OculusException("Object Context expected.");
	setObjectContext(context);
	
	if (args.containsKey("SortByLast"))
	{
	  _sortbyLastName = (String)args.get("SortByLast");
	}

	if (args.containsKey("SortByFirst"))
	{
	  _sortbyFirstName = (String)args.get("SortByFirst");
	}
	
		
	return this;
  }  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
	UserContactResponderAlphaList catList = null;
	  catList = new UserContactResponderAlphaList();
	  catList.setIID(_iid);
	  catList._items = this._items;
	  catList.reset();
	return catList;
  }  
  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "User"; }  
protected String getLoadQuery() throws ORIOException
{
	return "SELECT DISTINCT "+TABLE+".OBJECTID, LOWER("+COL_LASTNAME+"), LOWER("+COL_FIRSTNAME+") " + 
        " FROM MARKETINPUT LEFT OUTER JOIN "+TABLE+" ON "+TABLE+".OBJECTID=MARKETINPUT.CREATORID "+ 
        " WHERE " + COL_LASTNAME+" like '"+ _sortbyLastName +"%' AND " + COL_FIRSTNAME+" like '"+ _sortbyFirstName +"%'  AND "+TABLE+".DELETESTATE <> " + DeleteState.DELETED.getIntValue() + 
	      " ORDER BY LOWER(" + COL_LASTNAME+"), LOWER("+COL_FIRSTNAME+")";
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
	UserContactResponderAlphaList sortedList = new UserContactResponderAlphaList(sortCrit);
	sortedList._items.addAll(this._items);
	return sortedList;
  }  
}
