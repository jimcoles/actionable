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
* Filename:    BaselineCategoryColl.java
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

public class BaselineCategoryColl extends CategoryColl implements IBaselineCategoryColl, IPersistable
{
  protected String COL_BASELINEID = "BASELINEID";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public BaselineCategoryColl() throws OculusException
	{
    super();
    TABLE = "BCATEGORY";
	}

  protected BaselineCategoryColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "BaselineCategory"; }

	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IBaselineCategory nextBaselineCategory()
		throws OculusException
	{
		return (IBaselineCategory)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreBaselineCategories()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    BaselineCategoryColl sortedList = new BaselineCategoryColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  BaselineCategoryColl catList = null;
			catList = new BaselineCategoryColl();
			catList.setIID(_iid);
			catList._items = this._items;
			catList.reset();
		return catList;
	}
}