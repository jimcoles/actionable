package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    CategoryColl.java
* Date:        2/23/00
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

public class CategoryColl extends BusinessObjectColl implements ICategoryColl, IPersistable
{
  protected String TABLE = "CATEGORY";
  protected String COL_PARENTCATID = "PARENTCATID";
  protected String COL_VERSIONID = "VERSIONID";
  protected String COL_ORDERNUM = "ORDERNUM DESC";
  protected String COL_DELETESTATE = "DELETESTATE";
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public CategoryColl() throws OculusException
	{
    super();
	}

  protected CategoryColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+
           " WHERE "+COL_PARENTCATID+"="+getIID().getLongValue()+
             " AND "+COL_OBJECTID+"<>"+COL_PARENTCATID+
             " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_ORDERNUM;
  }

  protected String getClassName() { return "Category"; }
	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public ICategory nextCategory()
		throws OculusException
	{
		return (ICategory)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreCategories()
	{
		return hasNext();
	}
  
  

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    CategoryColl sortedList = new CategoryColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  CategoryColl catList = null;
			catList = new CategoryColl();
			catList.setIID(_iid);
			catList._items = this._items;
			catList.reset();
		return catList;
	}
}