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
* Filename:    FeatureRevisionColl.java
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

public class FeatureRevisionColl extends BusinessObjectColl implements IFeatureRevisionColl, IPersistable
{
  protected String TABLE = "FEATUREREVISION";
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_ORDERNUM = "CREATIONDATE DESC";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureRevisionColl() throws OculusException
	{
    super();
	}

  protected FeatureRevisionColl (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+
           " WHERE "+COL_FEATUREID+"="+getIID().getLongValue()+
           " ORDER BY "+COL_ORDERNUM;
  }

  protected String getClassName() { return "FeatureRevision"; }
	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IFeatureRevision nextFeatureRevision()
		throws OculusException
	{
		return (IFeatureRevision)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreFeatureRevisions()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureRevisionColl sortedList = new FeatureRevisionColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureRevisionColl featRevList = null;
			featRevList = new FeatureRevisionColl();
			featRevList.setIID(_iid);
			featRevList._items = this._items;
			featRevList.reset();
		return featRevList;
	}
}