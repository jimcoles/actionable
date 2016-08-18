package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**
* Filename:    StdFeatureTrashColl.java
* Date:        7/17/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/
public class StdFeatureTrashColl extends StdFeatureColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  
  protected String COL_STDFEATLINKID = "STDFEATURELINKID";
  public StdFeatureTrashColl() throws OculusException
  {
    super();
  }

  protected StdFeatureTrashColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return 
    " SELECT  feat.OBJECTID, feat.GUID AS FEAT_GUID, feat.CLASSID AS FEAT_CLASSID, feat.STATEID AS FEAT_STATEID, "+
                  "feat.ACCESSID AS FEAT_ACCESSID, feat.CREATORID AS FEAT_CREATORID, feat.CREATIONDATE AS FEAT_CREATIONDATE, feat.DELETESTATE AS FEAT_DELETESTATE, "+
                  "feat.FILEATTACHED AS FEAT_FILEATTACHED, feat.LINKATTACHED AS FEAT_LINKATTACHED, feat.DISCUSSATTACHED AS FEAT_DISCUSSATTACHED,"+
                  "feat.DEPENDENCIES, feat.ISSTANDARD, feat.LATESTREVISIONID, feat.VISIBLEID, feat.FEATURETYPEID, feat.ENGRSPECATTACHED,"+
                           
                  "link.OBJECTID AS STDFEATLINKID, link.GUID AS LINK_GUID, link.CLASSID AS LINK_CLASSID, link.STATEID AS LINK_STATEID, "+
                  "link.ACCESSID AS LINK_ACCESSID, link.CREATORID AS LINK_CREATORID, link.CREATIONDATE AS LINK_CREATIONDATE, link.DELETESTATE, "+
                  "link.FILEATTACHED AS LINK_FILEATTACHED, link.LINKATTACHED AS LINK_LINKATTACHED, link.DISCUSSATTACHED AS LINK_DISCUSSATTACHED, "+
                  "link.FEATUREID AS LINK_FEATUREID,link.STDCOLLECTIONID,"+
                  
                  "stdc.OBJECTID AS STDCID, stdc.GUID AS STDC_GUID, stdc.CLASSID AS STDC_CLASSID, stdc.STATEID AS STDC_STATEID, "+
                  "stdc.ACCESSID AS STDC_ACCESSID, stdc.CREATORID AS STDC_CREATORID, stdc.CREATIONDATE AS STDC_CREATIONDATE, stdc.DELETESTATE, "+
                  "stdc.FILEATTACHED AS STDC_FILEATTACHED, stdc.LINKATTACHED AS STDC_LINKATTACHED, stdc.DISCUSSATTACHED AS STDC_DISCUSSATTACHED, "+
                  "stdc.PARENTCOLLID AS STDC_PARENTID" +

    

    " FROM ((FEATURE feat LEFT OUTER JOIN STDFEATURELINK link ON feat.OBJECTID = link.FEATUREID) "+
           "   LEFT OUTER JOIN STANDARDSCOLLECTION stdc ON link.STDCOLLECTIONID = stdc.OBJECTID) "+
           "   WHERE stdc.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
           " AND link.DELETESTATE = " +DeleteState.DELETED.getIntValue();
    
  }
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StdFeatureTrashColl sortedList = new StdFeatureTrashColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
    
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object clone()
  {
    StdFeatureTrashColl featList = null;
    try
    {
      featList = new StdFeatureTrashColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    }
    catch (OculusException orioExp) {}
    return featList;
  }
}