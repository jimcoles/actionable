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

public class FeatureRevisionProductVersionColl extends ProductVersionColl implements IProductVersionColl, IPersistable
{
  protected String TABLE = "PRODUCTVERSION";
  protected String COL_PRODUCTID = "PRODUCTID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_DELETESTATE = "DELETESTATE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureRevisionProductVersionColl() throws OculusException
	{
    super();
	}

  protected FeatureRevisionProductVersionColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT pv.* "+
           " FROM (((PRODUCTVERSION pv LEFT OUTER JOIN CATEGORY c ON pv.OBJECTID=c.VERSIONID) "+
           " LEFT OUTER JOIN CATFEATURELINK cfl ON c.OBJECTID=cfl.CATEGORYID) "+
           " LEFT OUTER JOIN FEATURE f ON cfl.FEATUREID=f.OBJECTID) "+
           " LEFT OUTER JOIN FEATUREREVISION fr ON fr.FEATUREID = f.OBJECTID AND "+
           "      ((cfl.PINNEDREVID IS NULL AND fr.OBJECTID=f.LATESTREVISIONID) OR "+
           "       (cfl.PINNEDREVID=fr.OBJECTID)) "+
           " WHERE fr.OBJECTID="+getIID().getLongValue();
  }
  
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureRevisionProductVersionColl sortedList = new FeatureRevisionProductVersionColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		FeatureRevisionProductVersionColl verList = null;
			verList = new FeatureRevisionProductVersionColl();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}