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
* BUG01799        Cuihua Zhang    8.11.00     added checking for DELETESTATE in the query
* BUG01798        Cuihua Zhang    8.23.00     added sort by feature visible id
* BUG02291        Cuihua Zhang    9.07.00     added _where and check for compass in the construct method
*/

public class VersionFeatureList extends com.oculussoftware.bus.BusinessObjectList implements IFeatureList, IPersistable
{
  protected String COL_CATEGORYID = "CATEGORYID";
  protected String COL_ORDERNUM = "ORDERNUM";
    protected String COL_DELETESTATE = "DELETESTATE";
  protected String _where = "";
  protected String _sortby = "rev.NAME";
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public VersionFeatureList() throws OculusException
	{
    super();
    COL_OBJECTID = "LINK_OBJECTID";
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected VersionFeatureList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
    COL_OBJECTID = "LINK_OBJECTID";
  }

  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "FeatureCategoryLink"; }

  protected String getLoadQuery()
    throws ORIOException
  {
    return "SELECT  feat.OBJECTID AS FEAT_OBJECTID, feat.GUID AS FEAT_GUID, feat.CLASSID AS FEAT_CLASSID, feat.STATEID AS FEAT_STATEID, "+
										"feat.ACCESSID AS FEAT_ACCESSID, feat.CREATORID AS FEAT_CREATORID, feat.CREATIONDATE AS FEAT_CREATIONDATE, feat.DELETESTATE AS FEAT_DELETESTATE, "+
										"feat.FILEATTACHED AS FEAT_FILEATTACHED, feat.LINKATTACHED AS FEAT_LINKATTACHED, feat.DISCUSSATTACHED AS FEAT_DISCUSSATTACHED,"+
									"feat.DEPENDENCIES, feat.ISSTANDARD, feat.LATESTREVISIONID, feat.VISIBLEID AS FEAT_VISIBLE, feat.FEATURETYPEID, feat.ENGRSPECATTACHED,"+
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

           " FROM CATFEATURELINK link LEFT OUTER JOIN FEATURE feat ON link.FEATUREID = feat.OBJECTID "+
           "     LEFT OUTER JOIN FEATUREREVISION rev ON rev.FEATUREID = feat.OBJECTID AND "+
           "       ((link.PINNEDREVID IS NULL AND rev.OBJECTID = feat.LATESTREVISIONID) OR (link.PINNEDREVID IS NOT NULL AND rev.OBJECTID = link.PINNEDREVID)) "+
           "     LEFT OUTER JOIN CATEGORY cat ON link.CATEGORYID = cat.OBJECTID "+
           " WHERE cat.VERSIONID = "+getIID()+" "+
             _where+
           " AND link."+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue() +       
           " ORDER BY "+_sortby;
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
    IFeatureCategoryLink nextCatLink = null;
    while (nextFeat == null && hasNext())          // as long as we need to and can
    {
      IIID catlinkIID = (IIID)_ids.next();
      nextCatLink = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",catlinkIID,isLocked());
      nextFeat = nextCatLink.getFeatureObject();
    }
    return nextFeat;
  }


  public void setOrder(IFeatureCategoryLink featCatLink, int order)
    throws OculusException
  {
    removeFromColl(featCatLink);
    addToColl(featCatLink,order);
  }
  
  public void addToColl(IFeatureCategoryLink featCatLink, int order)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int currentOrder = featCatLink.getOrderNum();
      while (hasMoreFeatures())
      {
        IFeature thisFeat = nextFeature();
        int thisFeatOrder = thisFeat.getOrderNum();
        if (thisFeatOrder >= order)
          thisFeat.setOrderNum(thisFeatOrder+1);
      }
      featCatLink.setOrderNum(order);
    }
    else
      throw new OculusException("This collection is not locked.");
  }

  public void removeFromColl(IFeatureCategoryLink featCatLink)
    throws OculusException
  {
    if (isLocked())
    {
      reset();
      int order = featCatLink.getOrderNum();
      int currentOrder = featCatLink.getOrderNum();
      while (hasMoreFeatures())
      {
        IFeature thisFeat = nextFeature();
        int thisFeatOrder = thisFeat.getOrderNum();
        if (thisFeatOrder > order)
          thisFeat.setOrderNum(thisFeatOrder-1);
      }
    }
    else
      throw new OculusException("This collection is not locked.");
  }


//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    VersionFeatureList sortedList = new VersionFeatureList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    VersionFeatureList catList = null;
      catList = new VersionFeatureList();
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
    
    if (args.containsKey(SortKind.KEY))
    {
      SortKind sort = (SortKind)args.get(SortKind.KEY);
      if (sort.equals(SortKind.BYNAME))
        _sortby = "FEATUREREVISION.NAME";
      else if (sort.equals(SortKind.BYSTATE))
        _sortby = "link.STATEID";
      else if (sort.equals(SortKind.BYPRIORITY))
        _sortby = "link.PRIORITYID";
      else if (sort.equals(SortKind.BYFEATID))
        _sortby = "feat.VISIBLEID";
    }
    
    if (args.containsKey("Tab"))
    {
      String tab = (String)args.get("Tab");
      if (tab.equals("Compass"))
        _where += " AND link.STATEID = "+IDCONST.COMPASS.getIIDValue();
      else
        _where += " AND link.STATEID <> "+IDCONST.COMPASS.getIIDValue();

    }
    return this;
  }
}