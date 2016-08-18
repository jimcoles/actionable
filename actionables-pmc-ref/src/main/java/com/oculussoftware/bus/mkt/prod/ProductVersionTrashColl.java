package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.bus.*;

import java.util.*;

/**
* Filename:    ProductVersionTrashColl.java
* Date:        
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProductVersionTrashColl extends ProductVersionColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProductVersionTrashColl() throws OculusException
	{
    super();
	}

  protected ProductVersionTrashColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return 
      "SELECT "+TABLE+".*"+
      " FROM ("+TABLE+" LEFT OUTER JOIN PRODUCT ON PRODUCT.OBJECTID = "+TABLE+"."+COL_PRODUCTID+")"+
      " WHERE PRODUCT."+COL_DELETESTATE+" = "+DeleteState.NOT_DELETED.getIntValue()+
      " AND "+TABLE+"."+COL_DELETESTATE+" = "+DeleteState.DELETED.getIntValue();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ProductVersionTrashColl sortedList = new ProductVersionTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object clone()
	{
		ProductVersionTrashColl verList = null;
		try
		{
			verList = new ProductVersionTrashColl();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		}
		catch (OculusException orioExp) {}
		return verList;
	}
}