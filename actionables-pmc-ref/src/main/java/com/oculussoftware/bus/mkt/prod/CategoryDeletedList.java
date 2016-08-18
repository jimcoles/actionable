package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    CategoryDeletedList.java
* Date:        2/14/00
* Description: Handles a list of ICategory objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class CategoryDeletedList extends CategoryList
{

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public CategoryDeletedList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected CategoryDeletedList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+
           " WHERE "+COL_PARENTCATID+"="+getIID().getLongValue()+
             " AND "+COL_OBJECTID+"<>"+COL_PARENTCATID+
             " AND "+COL_DELETESTATE+"="+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_ORDERNUM;
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    CategoryDeletedList sortedList = new CategoryDeletedList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  CategoryDeletedList catList = null;
			catList = new CategoryDeletedList();
			catList.setIID(_iid);
			catList._items = this._items;
			catList.reset();
		return catList;
	}
}