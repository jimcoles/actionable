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

Return all question input based marketinputs

*/



public class ArticleInputColl extends BusinessObjectColl implements IMarketInputColl

{
  
  
  protected String TABLE = "MARKETINPUT";  
  protected String COL_DELETESTATE = "DELETESTATE";

  public ArticleInputColl() throws OculusException
  {
    super();
  }

  protected ArticleInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    
    StringBuffer sbf = new StringBuffer();
    sbf.append(" SELECT mkt.* ");
    sbf.append(" FROM \"MARKETINPUT\" mkt ");
    sbf.append(" LEFT OUTER JOIN \"CLASS\" cls ON cls.OBJECTID= mkt.CLASSID ");
    sbf.append(" LEFT OUTER JOIN \"INTERFACE\" int ON int.OBJECTID= cls.DEFINTERFACEID ");
    sbf.append(" LEFT OUTER JOIN \"EXTENDSASC\" ext ON ext.SRCINTERFACEID= int.OBJECTID ");
    sbf.append(" WHERE mkt.DELETESTATE=1 AND ext.DESTINTERFACEID="+IDCONST.IARTICLEINPUT.getLongValue());
    return sbf.toString();       
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
    ArticleInputColl sortedList = new ArticleInputColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    ArticleInputColl catList = null;
      catList = new ArticleInputColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}