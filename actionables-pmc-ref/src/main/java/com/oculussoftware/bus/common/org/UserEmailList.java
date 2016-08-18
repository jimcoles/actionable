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
*/

public class UserEmailList extends UserList implements  IPersistable, IUserList
{
	protected String COL_LASTNAME = "LASTNAME";
	protected String COL_ISINTERNAL = "ISINTERNAL";
	protected String COL_FIRSTNAME = "FIRSTNAME";
	protected String COL_EMAILADDR = "EMAILADDR";
	protected String _sortbyLastName = "";
	protected String _emailAddress = "";
	protected boolean _isInternal = false;
	protected String _sortbyFirstName = "";
	

  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the list */
  public UserEmailList() throws OculusException
  {
  super();
  TABLE = "APPUSER";
  }


//----------------------------- Protected Constructor -------------------------
/** Default constructor just initializes the list */
protected UserEmailList(Comparator sortCrit) throws OculusException
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

  if (args.containsKey("EmailAddress"))
  {
    _emailAddress = (String) args.get("EmailAddress");
  }

  if (args.containsKey("IsCustomer"))
  {
    _isInternal = ((String) args.get("IsCustomer")).equals("true")?false:true;
  }
    
  return this;
  }


//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
  UserEmailList catList = null;
    catList = new UserEmailList();
    catList.setIID(_iid);
    catList._items = this._items;
    catList.reset();
  return catList;
  }


  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "User"; }


protected String getLoadQuery() throws ORIOException
{
  return "SELECT *" + " FROM " + TABLE + " WHERE " + COL_EMAILADDR +" = '"+ _emailAddress +"' AND "
  + COL_ISINTERNAL+" = " + (_isInternal?"1":"0");
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
  UserEmailList sortedList = new UserEmailList(sortCrit);
  sortedList._items.addAll(this._items);
  return sortedList;
  }
}
