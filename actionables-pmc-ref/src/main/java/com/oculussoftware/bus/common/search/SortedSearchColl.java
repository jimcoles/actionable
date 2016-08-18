package com.oculussoftware.bus.common.search;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.rdb.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    SortedSearchColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/



public class SortedSearchColl extends ReposObjectColl
{
	protected String TABLE = "DATAVIEW";  
	protected String COL_DELETESTATE = "DELETESTATE";

  public SortedSearchColl() throws OculusException
  {
    super();
  }

  protected SortedSearchColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+           
           " WHERE "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue() + " AND VIEWTYPE = " + ViewType.SORTTREEQUERY;
           
  }

  protected String getClassName() { return "SortedSearch"; }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    SortedSearchColl sortedList = new SortedSearchColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }

//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    SortedSearchColl catList = null;
      catList = new SortedSearchColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}
