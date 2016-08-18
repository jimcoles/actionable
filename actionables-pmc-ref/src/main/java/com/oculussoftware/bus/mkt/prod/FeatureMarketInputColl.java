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
* Filename:    ProductVersionColl.java
* Date:        
* Description: Handles a list of IProductVersion objects
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

public class FeatureMarketInputColl extends FeatureColl implements IFeatureColl, IPersistable
{
  protected String TABLE = "FEATURE";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_DELETESTATE = "DELETESTATE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureMarketInputColl() throws OculusException
	{
    super();
	}

  protected FeatureMarketInputColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT feat.* "+
           " FROM FEATURE feat LEFT OUTER JOIN SEMANTICLINK sl ON feat.OBJECTID=sl.SRCOBJECTID "+
           " WHERE sl.DESTOBJECTID="+getIID().getLongValue()+
             "  AND sl.LINKTYPE="+LinkKind.INPUT.getIntValue()+
             "  AND feat.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY sl."+COL_ORDERNUM;
  }
  
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureMarketInputColl sortedList = new FeatureMarketInputColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		FeatureMarketInputColl verList = null;
			verList = new FeatureMarketInputColl();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}