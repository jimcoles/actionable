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
* Filename:    ProductVersionColl.java
* Date:        
* Description: Handles a list of IProductVersion objects
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
*/

public class FeatureAllFunctionalDependentsColl extends FeatureColl implements IFeatureColl, IPersistable
{
  protected String TABLE = "FEATURE";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_DELETESTATE = "DELETESTATE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureAllFunctionalDependentsColl() throws OculusException
	{
    super();
    COL_OBJECTID = "LINK_OBJECTID";
	}

  protected FeatureAllFunctionalDependentsColl(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
      COL_OBJECTID = "LINK_OBJECTID";
}
  protected String getClassName() { return "FeatureCategoryLink"; }

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
  								"link.FEATUREID AS LINK_FEATUREID, "+
  							" link.PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
  							"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID, "+
  								"rev.OBJECTID AS REV_OBJECTID, rev.GUID AS REV_GUID, rev.CLASSID AS REV_CLASSID, rev.STATEID AS REV_STATEID, "+
  								"rev.ACCESSID AS REV_ACCESSID, rev.CREATORID AS REV_CREATORID, rev.CREATIONDATE AS REV_CREATIONDATE, rev.DELETESTATE AS REV_DELETESTATE, "+
  								"rev.FILEATTACHED AS REV_FILEATTACHED, rev.LINKATTACHED AS REV_LINKATTACHED, rev.DISCUSSATTACHED AS REV_DISCUSSATTACHED, "+
  								"rev.FEATUREID AS FEATUREID,"+
  							"rev.ISCHANGEABLE, rev.EDITDATE, rev.NAME, rev.REVISIONNAME, rev.AUTOREVISIONLABEL, rev.USERCOMMENT, rev.DESCRIPTION "+

         " FROM ((FEATURE feat LEFT OUTER JOIN CATFEATURELINK link ON "+
         "    link.FEATUREID=feat.OBJECTID) LEFT OUTER JOIN FEATUREREVISION rev ON "+
         "    (link.PINNEDREVID IS NULL AND feat.LATESTREVISIONID=rev.OBJECTID) OR "+
         "    (link.PINNEDREVID IS NOT NULL AND link.PINNEDREVID=feat.OBJECTID)) "+
         "    LEFT OUTER JOIN SEMANTICLINK sl ON link.OBJECTID=sl.DESTOBJECTID "+
         " WHERE sl.SRCOBJECTID="+getIID().getLongValue()+
           "  AND sl.LINKTYPE="+LinkKind.FUNCTIONAL_DEP.getIntValue()+
           "  AND link.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+
         " ORDER BY sl."+COL_ORDERNUM;
  }
  
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureAllFunctionalDependentsColl sortedList = new FeatureAllFunctionalDependentsColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
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
	

  /**
  *  Reads the list of products from the database.
  */
  public IPersistable load()
    throws OculusException
  {
    Map list = new HashMap();
    IFeatureCategoryLink link = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",getIID());
    list.put(getIID(), link);
    _loadAll(list, link);
    
    for (Iterator i = list.keySet().iterator(); i.hasNext(); )
      _items.add(((IFeatureCategoryLink)list.get(i.next())).getIID());
      
    return this;
  }
  
  private void _loadAll(Map list, IFeatureCategoryLink link)
    throws OculusException
  {
    Map thisList = new HashMap();
    IFeatureColl deps = link.getFunctionalDependents();
    while (deps.hasMoreFeatures())
    {
      IFeatureCategoryLink dep = deps.nextFeature().getFeatureCategoryLinkObject();
      if (!list.containsKey(dep.getIID()))
      {
        list.put(dep.getIID(), dep);
        thisList.put(dep.getIID(), dep);
      }
      for (Iterator i = thisList.keySet().iterator(); i.hasNext(); )
        _loadAll(list,(IFeatureCategoryLink)thisList.get(i.next()));
    }
  }

//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		FeatureAllFunctionalDependentsColl verList = null;
			verList = new FeatureAllFunctionalDependentsColl();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}