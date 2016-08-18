package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.util.*;

/**
* Filename:    BusinessObjectList.java
* Date:        
* Description: Handles a list of business objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ---             Saleem Shafi    2/17/00     Changed COL_OBJECTID to 1 in the load() method to make it more generic.
* ---             Egan Royal      2/29/00     reset returns an IRCollection
*/

public class CategoryFeatureColl extends com.oculussoftware.bus.BusinessObjectColl implements IFeatureColl, IPersistable
{
  protected String COL_CATEGORYID = "CATEGORYID";
  protected String COL_ORDERNUM = "ORDERNUM";
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public CategoryFeatureColl() throws OculusException
	{
    super();
    COL_OBJECTID = "FEATURECATEGORYLINKID";
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected CategoryFeatureColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
    COL_OBJECTID = "FEATURECATEGORYLINKID";
  }

  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "FeatureCategoryLink"; }

  protected String getLoadQuery()
    throws ORIOException
  {
           return " SELECT  feat.OBJECTID AS FEAT_OBJECTID, feat.GUID AS FEAT_GUID, feat.CLASSID AS FEAT_CLASSID, feat.STATEID AS FEAT_STATEID, "+
           								"feat.ACCESSID AS FEAT_ACCESSID, feat.CREATORID AS FEAT_CREATORID, feat.CREATIONDATE AS FEAT_CREATIONDATE, feat.DELETESTATE AS FEAT_DELETESTATE, "+
           								"feat.FILEATTACHED AS FEAT_FILEATTACHED, feat.LINKATTACHED AS FEAT_LINKATTACHED, feat.DISCUSSATTACHED AS FEAT_DISCUSSATTACHED,"+
           							"feat.DEPENDENCIES, feat.ISSTANDARD, feat.LATESTREVISIONID, feat.VISIBLEID, feat.FEATURETYPEID, feat.ENGRSPECATTACHED,"+
           								"link.OBJECTID AS LINK_OBJECTID, link.GUID AS LINK_GUID, link.CLASSID AS LINK_CLASSID, link.STATEID AS LINK_STATEID, "+
           								"link.ACCESSID AS LINK_ACCESSID, link.CREATORID AS LINK_CREATORID, link.CREATIONDATE AS LINK_CREATIONDATE, link.DELETESTATE AS LINK_DELETESTATE, "+
           								"link.FILEATTACHED AS LINK_FILEATTACHED, link.LINKATTACHED AS LINK_LINKATTACHED, link.DISCUSSATTACHED AS LINK_DISCUSSATTACHED, "+
           								"link.FEATUREID AS LINK_FEATUREID, "+
           							" link.PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
           							"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID, "+
           								"rev.OBJECTID AS REV_OBJECTID, rev.GUID AS REV_GUID, rev.CLASSID AS REV_CLASSID, rev.STATEID AS REV_STATEID, "+
           								"rev.ACCESSID AS REV_ACCESSID, rev.CREATORID AS REV_CREATORID, rev.CREATIONDATE AS REV_CREATIONDATE, rev.DELETESTATE AS REV_DELETESTATE, "+
           								"rev.FILEATTACHED AS REV_FILEATTACHED, rev.LINKATTACHED AS REV_LINKATTACHED, rev.DISCUSSATTACHED AS REV_DISCUSSATTACHED, "+
           								"rev.FEATUREID AS FEATUREID,"+
           							"rev.ISCHANGEABLE, rev.EDITDATE, rev.NAME, rev.REVISIONNAME, rev.AUTOREVISIONLABEL, rev.USERCOMMENT, rev.DESCRIPTION "+
                  " FROM (OcuRepos.FEATURE feat LEFT OUTER JOIN OcuRepos.CATFEATURELINK link ON "+
                  "    link.FEATUREID=feat.OBJECTID) LEFT OUTER JOIN OcuRepos.FEATUREREVISION rev ON "+
                  "    (link.PINNEDREVID IS NULL AND feat.LATESTREVISIONID=rev.OBJECTID) OR "+
                  "    (link.PINNEDREVID IS NOT NULL AND link.PINNEDREVID=rev.OBJECTID) "+
           " WHERE CATFEATURELINK."+COL_CATEGORYID+"="+getIID().getLongValue()+
           " ORDER BY CATFEATURELINK."+COL_ORDERNUM;
  }

  //----------------- IFeatureColl Methods ------------------------------------

  public IPersistable load()
    throws OculusException
  {
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      IQueryProcessor stmt = repConn.createProcessor();
      String query = getLoadQuery();
      IDataSet results = stmt.retrieve(query);
      while (results.next())
      {
        long featID = results.getLong("FEAT_OBJECTID");
        IIID iid = new SequentialIID(featID);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",results);

        long revID = results.getLong("REV_OBJECTID");
        iid = new SequentialIID(featID);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision",results);


        long catlinkID = results.getLong("LINK_OBJECTID");
        iid = new SequentialIID(catlinkID);
        _items.add(iid);
        results.setIID(iid);
        getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",results);
        
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
    IFeatureCategoryLink nextCatLink = null;
    while (nextFeat == null && hasNext())          // as long as we need to and can
    {
      IIID catlinkIID = (IIID)_ids.next();
      nextCatLink = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",catlinkIID,isLocked());
      nextFeat = nextCatLink.getFeatureObject();
    }
    return nextFeat;
  }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    CategoryFeatureColl sortedList = new CategoryFeatureColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    CategoryFeatureColl catList = null;
 
      catList = new CategoryFeatureColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);
    
    return this;
  }
}