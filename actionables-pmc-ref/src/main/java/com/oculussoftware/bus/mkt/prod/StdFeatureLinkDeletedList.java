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
* Filename:    StandardsCollectionList.java
* Date:        
* Description: Handles a list of IStandardsCollection objects
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

public class StdFeatureLinkDeletedList extends StdFeatureLinkList
{
  protected String TABLE = "STDFEATURELINK";
  protected String COL_NAME = "NAME";
  protected String COL_STDCOLLID = "STDCOLLECTIONID";
  
  protected String COL_DELETESTATE = "DELETESTATE";


  
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the StandardsCollection list */
  public StdFeatureLinkDeletedList() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the StandardsCollection list */
  protected StdFeatureLinkDeletedList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
  
    return " SELECT * "+
           " FROM "+TABLE+  
           " WHERE "+COL_STDCOLLID + " = "+getIID().getLongValue()+ 
           " AND  "+COL_DELETESTATE+"="+DeleteState.DELETED.getIntValue();       
          // " ORDER BY "+COL_NAME;
 }


 //------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StdFeatureLinkDeletedList sortedList = new StdFeatureLinkDeletedList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    StdFeatureLinkDeletedList stdcList = null;
      stdcList = new StdFeatureLinkDeletedList();
      stdcList.setIID(_iid);
      stdcList._items = this._items;
      stdcList.reset();
    return stdcList;
  }
}