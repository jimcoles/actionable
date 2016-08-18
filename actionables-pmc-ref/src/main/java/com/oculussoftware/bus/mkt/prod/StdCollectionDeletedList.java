package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    stdcollectionDeletedList.java
* Date:        6/9/2000
* Description: Handles a list of IStandardCollection objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class StdCollectionDeletedList extends StandardsCollectionList
{

  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public StdCollectionDeletedList() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected StdCollectionDeletedList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+
           " WHERE "+COL_PARENTID+"="+getIID().getLongValue()+
             //" AND "+COL_OBJECTID+"<>"+COL_PARENTCATID+
             " AND "+COL_DELETESTATE+"="+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_NAME;
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StdCollectionDeletedList sortedList = new StdCollectionDeletedList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    StdCollectionDeletedList stdcList = null;
      stdcList = new StdCollectionDeletedList();
      stdcList.setIID(_iid);
      stdcList._items = this._items;
      stdcList.reset();
    return stdcList;
  }
}