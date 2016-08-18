package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    ProductList.java
* Date:        
* Description: Handles a list of IProduct objects
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
*	---							Saleem Shafi		2/4/00			Change load() to use new db
*	---							Saleem Shafi		2/5/00			Made changes to reflect the fact that IProductList
*																							is NOT an IBusinessObject, but is an IRCollection.
* ---             Saleem Shafi    2/14/00     Add IRPersistable to the concrete class
*/

public class ProductList extends BusinessObjectList implements IProductList, IPersistable
{
  protected String TABLE = "PRODUCT";
  protected String COL_NAME = "NAME";
  protected String COL_PROJECTID = "PROJECTID";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProductList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected ProductList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+      
           " WHERE "+COL_PROJECTID+"="+getIID().getLongValue()+
           " ORDER BY "+COL_NAME;
  }

  protected String getClassName()
  {
    return "Product";
  }

	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IProduct nextProduct()
		throws OculusException
	{
		IProduct nextProd = null;
		
		while (nextProd == null && hasMoreProducts())					// as long as we need to and can
		{
			IIID prodIID = (IIID)next();									// get the next IProduct
			nextProd = (IProduct)CRM.getInstance().getCompObject(getObjectContext(),"Product",prodIID,isLocked());
		}
		
		return nextProd;
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreProducts()
	{
		return hasNext();
	}
  
	
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ProductList sortedList = new ProductList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		ProductList prodList = null;
			prodList = new ProductList();
			prodList.setIID(_iid);
			prodList._items.addAll(this._items);
			prodList.reset();
		return prodList;
	}
}