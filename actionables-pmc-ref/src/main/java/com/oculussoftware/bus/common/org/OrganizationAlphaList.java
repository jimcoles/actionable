package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.util.*;

/**
* Filename:    OrganizationAlphaList.java
* Date:        
* Description: Handles a list of Organization objects
* sorts name
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
* PRF00269				Zain Nemazie		5/23/00			Load query is now specific to INTERNAL ORGS (Created through admin)
*	BUG00326				Zain Nemazie		5/24/00			Making delete a soft delete, so now query must check deltestate
*/

public class OrganizationAlphaList extends com.oculussoftware.bus.BusinessObjectList implements  IPersistable, IOrganizationList
{
	protected String COL_NAME = "NAME";  


  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the list */
  public OrganizationAlphaList() throws OculusException
  {
  super();
  TABLE = "ORGANIZATION";
    _sortby = "";
  }


//----------------------------- Protected Constructor -------------------------
/** Default constructor just initializes the list */
protected OrganizationAlphaList(Comparator sortCrit) throws OculusException
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
  
  if (args.containsKey("SortBy"))
  {
    _sortby = com.oculussoftware.repos.util.SQLUtil.primer((String)args.get("SortBy"));
  }

  
    
  return this;
  }


//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
  OrganizationAlphaList catList = null;
    catList = new OrganizationAlphaList();
    catList.setIID(_iid);
    catList._items = this._items;
    catList.reset();
  return catList;
  }


  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "Organization"; }


protected String getLoadQuery() throws ORIOException
{
  return "SELECT *" + " FROM " + TABLE + " WHERE ISINSTALLOWNER = 1 AND DELETESTATE = "+ DeleteState.NOT_DELETED.getIntValue() + " AND " + COL_NAME+" like '"+ _sortby +  "%' ORDER BY LOWER(" + COL_NAME+")";
}


  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreOrganizations()
  {
  return hasNext();
  }


  //----------------- IFeatureColl Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IOrganization nextOrganization()
  throws OculusException
  {
  return (IOrganization)next();
  }


//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
  throws OculusException
  {
  OrganizationAlphaList sortedList = new OrganizationAlphaList(sortCrit);
  sortedList._items.addAll(this._items);
  return sortedList;
  }
}
