package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**
* Filename:    CategoryFeatureStateColl.java
* Date:        
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal  
* @version 1.2
*/
public class CategoryFeatureStateColl extends CategoryFeatureColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *                 Cuihua Zhang    7/28/2000   Changed CATFEATURELINK to link in getLoadQuery()
  */
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public CategoryFeatureStateColl() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected CategoryFeatureStateColl(Comparator sortCrit) throws OculusException
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
         " WHERE link.STATEID="+getIID().getLongValue()+
         " ORDER BY link."+COL_ORDERNUM;
}
 
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    CategoryFeatureStateColl catList = null;
 
      catList = new CategoryFeatureStateColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }

}