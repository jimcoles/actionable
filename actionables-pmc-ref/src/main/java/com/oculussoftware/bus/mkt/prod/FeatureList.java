package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    FeatureList.java
* Date:        2/14/00
* Description: Handles a list of IFeature objects
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

public class FeatureList extends BusinessObjectList implements IFeatureList, IPersistable
{
  protected String TABLE = "FEATURE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected FeatureList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE;
  }

  protected String getClassName()
  {
    return "Feature";
  }


	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IFeature nextFeature()
		throws OculusException
	{
		return (IFeature)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreFeatures()
	{
		return hasNext();
	}


  public void setOrder(IFeatureCategoryLink featCatLink, int order)
    throws OculusException
  {
    removeFromColl(featCatLink);
    addToColl(featCatLink,order);
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
  
  public void addToColl(IFeatureCategoryLink featCatLink, int order)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int currentOrder = featCatLink.getOrderNum();
      while (hasMoreFeatures())
      {
        IFeature thisFeat = nextFeature();
        int thisFeatOrder = thisFeat.getOrderNum();
        if (thisFeatOrder >= order)
          thisFeat.setOrderNum(thisFeatOrder+1);
      }
      featCatLink.setOrderNum(order);
    }
    else
      throw new OculusException("This collection is not locked.");
  }

  public void removeFromColl(IFeatureCategoryLink featCatLink)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int order = featCatLink.getOrderNum();
      int currentOrder = featCatLink.getOrderNum();
      while (hasMoreFeatures())
      {
        IFeature thisFeat = nextFeature();
        int thisFeatOrder = thisFeat.getOrderNum();
        if (thisFeatOrder > order)
          thisFeat.setOrderNum(thisFeatOrder-1);
      }
    }
    else
      throw new OculusException("This collection is not locked.");
  }


  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureList sortedList = new FeatureList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureList featList = null;
			featList = new FeatureList();
			featList.setIID(_iid);
			featList._items = this._items;
			featList.reset();
		return featList;
	}
}