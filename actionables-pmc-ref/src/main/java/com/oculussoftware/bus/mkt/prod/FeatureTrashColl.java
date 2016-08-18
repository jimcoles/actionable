package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**
* Filename:    FeatureTrashColl.java
* Date:        4/20/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class FeatureTrashColl extends CategoryFeatureList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureTrashColl() throws OculusException
	{
    super();
	}

  protected FeatureTrashColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return 
    " SELECT  feat.OBJECTID AS FEAT_OBJECTID, feat.GUID AS FEAT_GUID, feat.CLASSID AS FEAT_CLASSID, feat.STATEID AS FEAT_STATEID, "+
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

    " FROM (((((FEATURE feat LEFT OUTER JOIN CATFEATURELINK link ON link.FEATUREID = feat.OBJECTID)"+
             " LEFT OUTER JOIN FEATUREREVISION rev ON "+
						      "    (link.PINNEDREVID IS NULL AND feat.LATESTREVISIONID=rev.OBJECTID) OR "+
                  "    (link.PINNEDREVID IS NOT NULL AND link.PINNEDREVID=feat.OBJECTID)) "+
						 " LEFT OUTER JOIN CATEGORY ON link.CATEGORYID = CATEGORY.OBJECTID)"+
             " LEFT OUTER JOIN PRODUCTVERSION ON CATEGORY.VERSIONID = PRODUCTVERSION.OBJECTID)"+
             " LEFT OUTER JOIN PRODUCT ON PRODUCT.OBJECTID = PRODUCTVERSION.PRODUCTID)"+
    " WHERE PRODUCT.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+ 
    " AND PRODUCTVERSION.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
    " AND CATEGORY.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
    " AND link.DELETESTATE = "+DeleteState.DELETED.getIntValue();
  }
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureTrashColl sortedList = new FeatureTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object clone()
	{
	  FeatureTrashColl featList = null;
		try
		{
			featList = new FeatureTrashColl();
			featList.setIID(_iid);
			featList._items = this._items;
			featList.reset();
		}
		catch (OculusException orioExp) {}
		return featList;
	}
}