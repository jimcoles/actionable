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
* Filename:    ProductColl.java
* Date:        2/23/00
* Description: 
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

public class SpecSignOffColl extends BusinessObjectColl implements ISpecSignOffColl
{
  public static final String TABLE             = "SPECSIGNOFF";
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public SpecSignOffColl() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected SpecSignOffColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+
           " WHERE "+SpecSignOff.COL_FEATCATLINKID+"="+getIID().getLongValue();
  }

  protected String getClassName()
  {
    return "SpecSignOff";
  }

	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public ISpecSignOff nextSpecSignOff()
		throws OculusException
	{
		return (ISpecSignOff)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreSpecSignOffs()
	{
		return hasNext();
	}
  
	
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    SpecSignOffColl sortedList = new SpecSignOffColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/**  */
	public Object dolly() throws OculusException
	{
		SpecSignOffColl specList = null;
			specList = new SpecSignOffColl();
			specList.setIID(_iid);
			specList._items.addAll(this._items);
			specList.reset();
		return specList;
	}
}