package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;

import java.util.*;

/**
* Filename:    ProductVersionDeletedList.java
* Date:        6-5-00
* Description: Handles a list of IProductVersion objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class ProductVersionDeletedList extends ProductVersionList
{
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProductVersionDeletedList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected ProductVersionDeletedList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_PRODUCTID+"="+getIID().getLongValue()+
             " AND DELETESTATE ="+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_ORDERNUM;
  }
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ProductVersionDeletedList sortedList = new ProductVersionDeletedList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		ProductVersionDeletedList verList = null;
			verList = new ProductVersionDeletedList();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}