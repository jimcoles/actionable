package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    FeatureColl.java
* Date:        2/23/00
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

public class FeatureColl extends BusinessObjectColl implements IFeatureColl, IPersistable
{
  protected String TABLE = "FEATURE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureColl() throws OculusException
	{
    super();
	}

  protected FeatureColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE;
  }

  protected String getClassName() { return "Feature"; }
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
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureColl sortedList = new FeatureColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureColl featList = null;
			featList = new FeatureColl();
			featList.setIID(_iid);
			featList._items = this._items;
			featList.reset();
		return featList;
	}
}