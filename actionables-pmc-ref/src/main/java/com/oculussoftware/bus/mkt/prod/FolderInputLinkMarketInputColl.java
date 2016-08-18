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
* Filename:    ConceptColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2

Get all folderinputlinks for a specific marketinput

*/



public class FolderInputLinkMarketInputColl extends BusinessObjectColl implements IFolderInputLinkColl

{
  
  
  protected String TABLE = "FOLDERINPUTLINK";  
  protected String COL_DELETESTATE = "DELETESTATE";

  public FolderInputLinkMarketInputColl() throws OculusException
  {
    super();
  }

  protected FolderInputLinkMarketInputColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+           
           " WHERE MARKETINPUTID="+getIID().getLongValue()+" AND DELETESTATE=1";
           
  }

  protected String getClassName() { return "FolderInputLink"; }

  //----------------- IProductList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IFolderInputLink nextFolderInputLink()
    throws OculusException
  {
    return (IFolderInputLink)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreFolderInputLinks()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FolderInputLinkMarketInputColl sortedList = new FolderInputLinkMarketInputColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    FolderInputLinkMarketInputColl catList = null;
      catList = new FolderInputLinkMarketInputColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}