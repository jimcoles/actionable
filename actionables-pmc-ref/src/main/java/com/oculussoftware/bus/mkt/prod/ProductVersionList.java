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
* Filename:    ProductVersionList.java
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

public class ProductVersionList extends BusinessObjectList implements IProductVersionList, IPersistable
{
  protected String TABLE = "PRODUCTVERSION";
  protected String COL_PRODUCTID = "PRODUCTID";
  
  protected String COL_ORDERNUM = "ORDERNUM";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProductVersionList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected ProductVersionList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_PRODUCTID+"="+getIID().getLongValue()+
             " AND DELETESTATE <>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_ORDERNUM;
  }

  protected String getClassName()
  {
    return "ProductVersion";
  }

	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IProductVersion nextProductVersion()
		throws OculusException
	{
		return (IProductVersion)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreProductVersions()
	{
		return hasNext();
	}
  


  public void setOrder(IProductVersion ver, int order)
    throws OculusException
  {
    removeFromColl(ver);
    addToColl(ver,order);
  }
  
  public void addToColl(IProductVersion ver, int order)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int currentOrder = ver.getOrderNum();
      while (hasMoreProductVersions())
      {
        IProductVersion thisVer = nextProductVersion();
        int thisVerOrder = thisVer.getOrderNum();
        if (thisVerOrder >= order)
          thisVer.setOrderNum(thisVerOrder+1);
      }
      ver.setOrderNum(order);
    }
    else
      throw new OculusException("This collection is not locked.");
  }

  public void removeFromColl(IProductVersion ver)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int order = ver.getOrderNum();
      int currentOrder = ver.getOrderNum();
      while (hasMoreProductVersions())
      {
        IProductVersion thisVer = nextProductVersion();
        int thisVerOrder = thisVer.getOrderNum();
        if (thisVerOrder > order)
          thisVer.setOrderNum(thisVerOrder-1);
      }
    }
    else
      throw new OculusException("This collection is not locked.");
  }

	
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ProductVersionList sortedList = new ProductVersionList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		ProductVersionList verList = null;
			verList = new ProductVersionList();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}