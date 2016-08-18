package com.oculussoftware.bus.mkt.prod;


import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.rdb.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    MarketInputColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/



public class MarketInputColl extends BusinessObjectColl implements IMarketInputColl

{
  
  
  protected String TABLE = "MARKETINPUT";  
  protected String COL_DELETESTATE = "DELETESTATE";

  public MarketInputColl() throws OculusException
  {
    super();
  }

  protected MarketInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+           
           " WHERE "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();
           
  }

  protected String getClassName() { return "MarketInput"; }

  //----------------- IProductList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IMarketInput nextMarketInput()
    throws OculusException
  {
    return (IMarketInput)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreMarketInputs()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    MarketInputColl sortedList = new MarketInputColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    MarketInputColl catList = null;
      catList = new MarketInputColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}