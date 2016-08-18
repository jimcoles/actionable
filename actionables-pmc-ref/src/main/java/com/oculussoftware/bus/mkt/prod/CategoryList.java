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
* Filename:    CategoryList.java
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

public class CategoryList extends BusinessObjectList implements ICategoryList, IPersistable
{
  protected String TABLE = "CATEGORY";
  protected String COL_PARENTCATID = "PARENTCATID";
  protected String COL_VERSIONID = "VERSIONID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_DELETESTATE = "DELETESTATE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public CategoryList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected CategoryList(Comparator sortCrit) throws OculusException
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


  public void setOrder(ICategory cat, int order)
    throws OculusException
  {
    removeFromColl(cat);
    addToColl(cat,order);
//    if (isLocked())
//    {
//      reset();
//      int currentOrder = cat.getOrderNum();
//      while (hasMoreCategories())
//      {
//        ICategory thisCat = nextCategory();
//        int thisCatOrder = thisCat.getOrderNum();
//        if (thisCatOrder >= order && thisCatOrder < currentOrder)
//          thisCat.setOrderNum(thisCatOrder+1);
//        if (thisCatOrder > currentOrder && thisCatOrder <= order)
//          thisCat.setOrderNum(thisCatOrder-1);
//      }
//      cat.setOrderNum(order);
//    }
//    else
//      throw new OculusException("This collection is not locked.");
  }
  
  public void addToColl(ICategory cat, int order)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int currentOrder = cat.getOrderNum();
      while (hasMoreCategories())
      {
        ICategory thisCat = nextCategory();
        int thisCatOrder = thisCat.getOrderNum();
        if (thisCatOrder >= order)
          thisCat.setOrderNum(thisCatOrder+1);
      }
      cat.setOrderNum(order);
    }
    else
      throw new OculusException("This collection is not locked.");
  }

  public void removeFromColl(ICategory cat)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int order = cat.getOrderNum();
      int currentOrder = cat.getOrderNum();
      while (hasMoreCategories())
      {
        ICategory thisCat = nextCategory();
        int thisCatOrder = thisCat.getOrderNum();
        if (thisCatOrder > order)
          thisCat.setOrderNum(thisCatOrder-1);
      }
    }
    else
      throw new OculusException("This collection is not locked.");
  }



  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    CategoryList sortedList = new CategoryList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  CategoryList catList = null;
			catList = new CategoryList();
			catList.setIID(_iid);
			catList._items = this._items;
			catList.reset();
		return catList;
	}
}