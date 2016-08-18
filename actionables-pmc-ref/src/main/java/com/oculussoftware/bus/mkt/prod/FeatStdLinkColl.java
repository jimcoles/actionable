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
* Filename:    FeatStdLinkColl.java
* Date:        5/2/00
* Description: Handles a collection of IStdFeatureColl objects through a given feature object
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

public class FeatStdLinkColl extends StdFeatureLinkColl 
{
  protected String TABLE = "STDFEATURELINK";
  
  protected String COL_FEATID = "FEATUREID";
  protected String COL_DELETESTATE = "DELETESTATE";
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public FeatStdLinkColl() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected FeatStdLinkColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+  
           " WHERE "+COL_FEATID + " = "+getIID().getLongValue()+ " AND  "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();       
  }

  protected String getClassName()
  {
    return "StdFeatureLink";
  }

  //----------------- IStandardsCollectionList Methods ------------------------------------
  /**
  * Returns the next available IStandardsCollection in the list.  If the next IStandardsCollection turns
  * out to be null, just go to the next one.
   */
  public IStdFeatureLink nextStdFeatureLink()
    throws OculusException
  {
    return (IStdFeatureLink)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreStdFeatureLink()
  {
    return hasNext();
  }
  
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatStdLinkColl sortedList = new FeatStdLinkColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IStandardsCollectionList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    FeatStdLinkColl StdFeatureLinkList = new FeatStdLinkColl();
    StdFeatureLinkList.setIID(_iid);
    StdFeatureLinkList._items.addAll(this._items);
    StdFeatureLinkList.reset();
    return StdFeatureLinkList;
  }
}