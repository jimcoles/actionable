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

public class SubProductVersionColl extends ProductVersionColl implements IProductVersionColl, IPersistable
{
  protected String TABLE = "PRODUCTVERSION";
  protected String COL_PARENTID = "PARVERSIONID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_DELETESTATE = "DELETESTATE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public SubProductVersionColl() throws OculusException
	{
    super();
	}

  protected SubProductVersionColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM VERSSHARELINK LEFT OUTER JOIN "+TABLE+" ON CHILDVERSIONID="+TABLE+".OBJECTID "+
           " WHERE VERSSHARELINK."+COL_PARENTID+"="+getIID().getLongValue()+
             " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY PRODUCTVERSION."+COL_ORDERNUM;
  }
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		SubProductVersionColl verList = null;
			verList = new SubProductVersionColl();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}