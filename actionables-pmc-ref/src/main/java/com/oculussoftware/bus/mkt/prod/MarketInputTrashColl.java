package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**
* Filename:    MarketInputTrashColl.java
* Date:        4-20-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class MarketInputTrashColl extends MarketInputColl implements IMarketInputColl
{
  protected String COL_NAME = "SUBJECT";  
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
    
    BUG00419       apota                        Compass trash fix effort.
                                                Currently there is no trash for
                                                compass.
      
  */
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public MarketInputTrashColl() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected MarketInputTrashColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return "SELECT * FROM MARKETINPUT WHERE DELETESTATE=0";           
  }  
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    MarketInputTrashColl sortedList = new MarketInputTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductColl object, but without the ObjectContext */
 public Object dolly() throws OculusException
  {
    MarketInputTrashColl catList = null;
      catList = new MarketInputTrashColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}