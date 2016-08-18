package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    StdFeatureColl.java
* Date:        
* Description: Handles a list of IProductVersion objects
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

public class StdFeatureColl extends FeatureColl implements IFeatureColl, IPersistable
{
  protected String TABLE = "FEATURE";
 
  protected String COL_DELETESTATE = "DELETESTATE";

  private String _firstID;
  private String _secondID;
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public StdFeatureColl() throws OculusException
  {
    super();
    COL_OBJECTID = "STDFEATLINKID";
  }

  protected StdFeatureColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
    COL_OBJECTID = "STDFEATLINKID";
  }

  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "StdFeatureLink"; }

  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT feat.*, stdfl.OBJECTID AS STDFEATLINKID, stdfl.* " +
           " FROM ((FEATURE feat LEFT OUTER JOIN STDFEATURELINK stdfl ON feat.OBJECTID = stdfl.FEATUREID) "+
           "   LEFT OUTER JOIN STANDARDSCOLLECTION stdc ON stdfl.STDCOLLECTIONID=stdc.OBJECTID) "+
           "   WHERE stdc.OBJECTID= "+getIID().getLongValue()+
           " AND stdfl.DELETESTATE <> " +DeleteState.DELETED.getIntValue();
           

  }


  //----------------- IFeatureColl Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IFeature nextFeature()
    throws OculusException
  {
    return (IFeature)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreFeatures()
  {
    return hasNext();
  }
  

  public Object next()
    throws OculusException
  {
    IFeature nextFeat = null;
    IStdFeatureLink nextCatLink = null;
    while (nextFeat == null && hasNext())          // as long as we need to and can
    {
      IIID catlinkIID = (IIID)_ids.next();
      nextCatLink = (IStdFeatureLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLink",catlinkIID,isLocked());
      nextFeat = nextCatLink.getFeatureObject();
    }
    return nextFeat;
  }



  public IPersistable load()
    throws OculusException
  {
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      IQueryProcessor stmt = repConn.createProcessor();
      String query = getLoadQuery();
      IDataSet results = stmt.retrieve(query);
      while (results.next())
      {
        long featID = results.getLong("OBJECTID");
        IIID iid = new SequentialIID(featID);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",results);
     

        long catlinkID = results.getLong("STDFEATLINKID");
        iid = new SequentialIID(catlinkID);
        _items.add(iid);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLink",results);
        
      }
      stmt.close();
//      CRM.getInstance().returnDatabaseConnection(repConn);
      reset();
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error retrieving data from the database."+sqlExp.toString());
    }
    return this;
  }



//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StdFeatureColl sortedList = new StdFeatureColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
      StdFeatureColl verList = null;
      verList = new StdFeatureColl();
      verList.setIID(_iid);
      verList._items = this._items;
      verList.reset();
    return verList;
  }

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
 /* public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    super.construct(context,args);
    
    _firstID = ((IIID)args.get("FirstID")).getLongValue()+"";
    _secondID = ((IIID)args.get("SecondID")).getLongValue()+"";

    return this;
  }
*/

}