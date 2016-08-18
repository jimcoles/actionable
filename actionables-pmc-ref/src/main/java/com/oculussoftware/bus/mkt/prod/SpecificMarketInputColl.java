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
* Filename:    SpecificMarketInputColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/



public class SpecificMarketInputColl extends BusinessObjectColl implements IMarketInputColl

{
  
  
  protected String TABLE = "MARKETINPUT";  
  protected String COL_DELETESTATE = "DELETESTATE";

  public SpecificMarketInputColl() throws OculusException
  {
    super();
  }

  protected SpecificMarketInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    StringBuffer sbf = new StringBuffer();
    sbf.append("SELECT * FROM MARKETINPUT WHERE CLASSID=");
    sbf.append(getIID().toString());
    sbf.append(" AND DELETESTATE <> ");
    String s = Integer.toString(DeleteState.DELETED.getIntValue());
    sbf.append(s);
    
    return sbf.toString();
    /*
    return " SELECT * "+
           " FROM "+TABLE+           
           " 
           " WHERE "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();
    */       
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
    SpecificMarketInputColl sortedList = new SpecificMarketInputColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    SpecificMarketInputColl catList = null;
      catList = new SpecificMarketInputColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}