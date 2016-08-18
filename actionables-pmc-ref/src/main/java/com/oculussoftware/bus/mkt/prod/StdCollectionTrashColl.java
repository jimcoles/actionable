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
* Filename:    StCollectionTrashColl.java
* Date:        6/10/00
* Description: Handles a collection of IStandardsCollection objects
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

public class StdCollectionTrashColl extends StandardsCollectionColl
{
  protected String TABLE = "STANDARDSCOLLECTION";
  protected String COL_NAME = "NAME";
  protected String COL_PARENT = "PARENTCOLLID";
  protected String COL_DELETESTATE = "DELETESTATE";
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public StdCollectionTrashColl() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected StdCollectionTrashColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT std.*" + 
           " FROM ( STANDARDSCOLLECTION std LEFT OUTER JOIN STANDARDSCOLLECTION stdc" + 
           " ON stdc.OBJECTID =std.PARENTCOLLID) "+ 
           
           " WHERE (std.DELETESTATE = " + DeleteState.DELETED+
           " AND stdc.DELETESTATE = "+DeleteState.NOT_DELETED+
           ") OR (std.DELETESTATE = "+DeleteState.DELETED+
           "AND std.OBJECTID = stdc.PARENTCOLLID)";
  }

  

  //----------------- IStandardsCollectionList Methods ------------------------------------
  /**
  * Returns the next available IStandardsCollection in the list.  If the next IStandardsCollection turns
  * out to be null, just go to the next one.
   */
  
  /**
  *  Returns true if there are more IProducts in the list
  */
 
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StdCollectionTrashColl sortedList = new StdCollectionTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IStandardsCollectionList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    StdCollectionTrashColl StandardsCollectionList = null;
      StandardsCollectionList = new StdCollectionTrashColl();
      StandardsCollectionList.setIID(_iid);
      StandardsCollectionList._items.addAll(this._items);
      StandardsCollectionList.reset();
    return StandardsCollectionList;
  }
}