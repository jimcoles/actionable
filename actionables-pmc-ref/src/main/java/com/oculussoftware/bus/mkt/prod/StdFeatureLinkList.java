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

public class StdFeatureLinkList extends BusinessObjectList implements IStdFeatureLinkList, IPersistable
{
  protected String TABLE = "STDFEATURELINK";
  //protected String COL_NAME = "NAME";
  protected String COL_STDCOLLID = "STDCOLLECTIONID";
  
  protected String COL_DELETESTATE = "DELETESTATE";


  
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the StandardsCollection list */
  public StdFeatureLinkList() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the StandardsCollection list */
  protected StdFeatureLinkList(Comparator sortCrit) throws OculusException
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
           " AND  "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();       
    //       " ORDER BY "+COL_NAME;
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
    //IStdFeatureLink nextstdcoll = null;
    
    //while (nextstdcoll == null && hasMoreStdFeatureLink())  // as long as we need to and can
    //{
      //  System.out.println("got here in the list");  
      //IIID stdcollIID = (IIID)next();
      //System.out.println("got here in the list");      // get the next IProduct
      //nextstdcoll = (IStdFeatureLink)CRM.getInstance().getCompObject(getObjectContext(),"StdFeatureLink",stdcollIID,isLocked());
     // System.out.println("got here in the list again");
    //}
    
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
    StdFeatureLinkList sortedList = new StdFeatureLinkList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IStandardsCollectionList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    StdFeatureLinkList stdcollList = null;
      stdcollList = new StdFeatureLinkList();
      stdcollList.setIID(_iid);
      stdcollList._items.addAll(this._items);
      stdcollList.reset();
    return stdcollList;
  }
}