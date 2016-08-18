package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**
* Filename:    CategoryTrashColl.java
* Date:        4/20/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class CategoryTrashColl extends CategoryColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public CategoryTrashColl() throws OculusException
	{
    super();
	}

  protected CategoryTrashColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    //this returns all of the deleted categories whose products, versions
		//and parent categories are not deleted
    return 
		"SELECT cat.* "+
     "FROM (((CATEGORY cat LEFT OUTER JOIN PRODUCTVERSION ON cat.VERSIONID = PRODUCTVERSION.OBJECTID) "+
            "LEFT OUTER JOIN PRODUCT ON PRODUCT.OBJECTID = PRODUCTVERSION.PRODUCTID) "+
	    "LEFT OUTER JOIN CATEGORY cat2 ON cat.PARENTCATID = cat2.OBJECTID) "+
     "WHERE PRODUCT.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
     " AND PRODUCTVERSION.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
     " AND cat2.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
     " AND cat.DELETESTATE = "+DeleteState.DELETED.getIntValue();
  }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    CategoryTrashColl sortedList = new CategoryTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object clone()
	{
	  CategoryTrashColl catList = null;
		try
		{
			catList = new CategoryTrashColl();
			catList.setIID(_iid);
			catList._items = this._items;
			catList.reset();
		}
		catch (OculusException orioExp) {}
		return catList;
	}
}