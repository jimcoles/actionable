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
* Filename:    FeatureRevisionColl.java
* Date:        2/23/00
* Description: Handles a list of IFeature objects
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

public class FeatureRevisionEditedVersionList extends BusinessObjectList implements IFeatureRevisionList
{
  protected String TABLE = "FEATUREREVISION";
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_ORDERNUM = "CREATIONDATE DESC";

  private String _firstID;
  private String _secondID;

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureRevisionEditedVersionList() throws OculusException
	{
    super();
	}

  protected FeatureRevisionEditedVersionList (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
   return   " SELECT rev.* "+
            " FROM FEATURE feat LEFT OUTER JOIN "+
            "     FEATUREREVISION rev ON feat.OBJECTID = rev.FEATUREID "+
            "     LEFT OUTER JOIN "+
            "   ((FEATUREREVISION revOld LEFT OUTER JOIN CATFEATURELINK cfl1 ON cfl1.FEATUREID = revOld.FEATUREID) LEFT OUTER JOIN CATEGORY c1 ON cfl1.CATEGORYID = c1.OBJECTID) "+
            "     ON feat.OBJECTID = revOld.FEATUREID AND rev.OBJECTID > revOld.OBJECTID AND ((cfl1.PINNEDREVID IS NULL AND revOld.OBJECTID = feat.LATESTREVISIONID) OR (cfl1.PINNEDREVID IS NOT NULL AND revOld.OBJECTID = cfl1.PINNEDREVID)) "+
            "     LEFT OUTER JOIN "+
            "   ((FEATUREREVISION revNew LEFT OUTER JOIN CATFEATURELINK cfl2 ON cfl2.FEATUREID = revNew.FEATUREID) LEFT OUTER JOIN CATEGORY c2 ON cfl2.CATEGORYID = c2.OBJECTID) "+
            "     ON feat.OBJECTID = revOld.FEATUREID AND rev.OBJECTID <= revNew.OBJECTID AND ((cfl2.PINNEDREVID IS NULL AND revNew.OBJECTID = feat.LATESTREVISIONID) OR (cfl2.PINNEDREVID IS NOT NULL AND revNew.OBJECTID = cfl2.PINNEDREVID)) "+
            " WHERE c2.VERSIONID = "+_firstID+" AND c1.VERSIONID = "+_secondID+" AND feat.OBJECTID IN "+
            "         (SELECT feat1.OBJECTID "+
            "       FROM ((FEATURE feat1 LEFT OUTER JOIN FEATUREREVISION rev1 ON feat1.OBJECTID = rev1.FEATUREID) "+
            "            LEFT OUTER JOIN CATFEATURELINK catfeatlink ON catfeatlink.FEATUREID = rev1.FEATUREID AND ((catfeatlink.PINNEDREVID IS NULL AND rev1.OBJECTID = feat1.LATESTREVISIONID) OR (catfeatlink.PINNEDREVID IS NOT NULL AND rev1.OBJECTID = catfeatlink.PINNEDREVID))) "+
            "            LEFT OUTER JOIN CATEGORY cat ON catfeatlink.CATEGORYID = cat.OBJECTID "+
            "       WHERE cat.VERSIONID = "+_firstID+" AND feat1.OBJECTID IN "+
            "                (SELECT feat2.OBJECTID "+
            "                 FROM ((FEATURE feat2 LEFT OUTER JOIN FEATUREREVISION rev2 ON feat2.OBJECTID = rev2.FEATUREID) "+
            "                   LEFT OUTER JOIN CATFEATURELINK catfeatlink2 ON catfeatlink2.FEATUREID = rev2.FEATUREID AND ((catfeatlink2.PINNEDREVID IS NULL AND rev2.OBJECTID = feat2.LATESTREVISIONID) OR (catfeatlink2.PINNEDREVID IS NOT NULL AND rev2.OBJECTID = catfeatlink2.PINNEDREVID))) "+
            "                   LEFT OUTER JOIN CATEGORY cat2 ON catfeatlink2.CATEGORYID = cat2.OBJECTID "+
            "                 WHERE cat2.VERSIONID = "+_secondID+")) "+
            " ORDER BY feat.OBJECTID, rev.OBJECTID DESC ";

  }

  protected String getClassName() { return "FeatureRevision"; }
	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IFeatureRevision nextFeatureRevision()
		throws OculusException
	{
		return (IFeatureRevision)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreFeatureRevisions()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureRevisionEditedVersionList sortedList = new FeatureRevisionEditedVersionList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureRevisionEditedVersionList featRevList = null;
			featRevList = new FeatureRevisionEditedVersionList();
			featRevList.setIID(_iid);
			featRevList._items = this._items;
			featRevList.reset();
		return featRevList;
	}

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    super.construct(context,args);
    
    _firstID = ((IIID)args.get("FirstID")).getLongValue()+"";
    _secondID = ((IIID)args.get("SecondID")).getLongValue()+"";

    return this;
  }

  
}