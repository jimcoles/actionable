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
* ---             Saleem Shafi    3/20/00     Redesigned so it can extend CategoryFeatureColl
*/

public class BaselineCategoryFeatureColl extends CategoryFeatureColl implements IFeatureColl, IPersistable
{
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public BaselineCategoryFeatureColl() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected BaselineCategoryFeatureColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
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
									" link.REVISIONID AS PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
									"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID, "+
										"rev.OBJECTID AS REV_OBJECTID, rev.GUID AS REV_GUID, rev.CLASSID AS REV_CLASSID, rev.STATEID AS REV_STATEID, "+
										"rev.ACCESSID AS REV_ACCESSID, rev.CREATORID AS REV_CREATORID, rev.CREATIONDATE AS REV_CREATIONDATE, rev.DELETESTATE AS REV_DELETESTATE, "+
										"rev.FILEATTACHED AS REV_FILEATTACHED, rev.LINKATTACHED AS REV_LINKATTACHED, rev.DISCUSSATTACHED AS REV_DISCUSSATTACHED, "+
										"rev.FEATUREID AS FEATUREID,"+
									"rev.ISCHANGEABLE, rev.EDITDATE, rev.NAME, rev.REVISIONNAME, rev.AUTOREVISIONLABEL, rev.USERCOMMENT, rev.DESCRIPTION "+
					 " FROM (BCATFEATURELINK link LEFT OUTER JOIN FEATUREREVISION rev ON "+
           "    link.REVISIONID=rev.OBJECTID) "+
           "    LEFT OUTER JOIN FEATURE feat ON rev.FEATUREID=feat.OBJECTID "+
           " WHERE link."+COL_CATEGORYID+"="+getIID()+
            " AND link.STATEID <> "+IDCONST.COMPASS.getIIDValue()+" "+
            " AND link.STATEID <> "+IDCONST.DEFERRED.getIIDValue()+" "+
            " AND link.STATEID <> "+IDCONST.MYCONCEPTS.getIIDValue()+" "+
            " AND link.STATEID <> "+IDCONST.DEFINPROGRESSREVIEW.getIIDValue()+" "+
           " ORDER BY link."+COL_ORDERNUM;
  }

  protected String getClassName() { return "BaselineFeatureCategoryLink"; }


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
        getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineFeatureCategoryLink",results);
        
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


	/** sets the sort that this ProductList will use */
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    return this;
  }
	
  public Object next()
    throws OculusException
  {
    IFeature nextFeat = null;
    IFeatureCategoryLink nextCatLink = null;
    while (nextFeat == null && hasNext())          // as long as we need to and can
    {
      IIID catlinkIID = (IIID)_ids.next();
      nextCatLink = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineFeatureCategoryLink",catlinkIID,isLocked());
      nextFeat = nextCatLink.getFeatureObject();
    }
    return nextFeat;
  }
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    BaselineCategoryFeatureColl catList = null;
      catList = new BaselineCategoryFeatureColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }

}