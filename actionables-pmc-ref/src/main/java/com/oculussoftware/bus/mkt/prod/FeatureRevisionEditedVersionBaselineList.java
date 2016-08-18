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

public class FeatureRevisionEditedVersionBaselineList extends BusinessObjectList implements IFeatureRevisionList
{
  protected String TABLE = "FEATUREREVISION";
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_ORDERNUM = "CREATIONDATE DESC";

  private String _firstID;
  private String _secondID;

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureRevisionEditedVersionBaselineList() throws OculusException
	{
    super();
	}

  protected FeatureRevisionEditedVersionBaselineList (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {

    return  " SELECT rev.* "+
            " FROM FEATURE feat LEFT OUTER JOIN FEATUREREVISION rev ON feat.OBJECTID = rev.FEATUREID, "+
            "     (FEATUREFEATUREREVISION revOld LEFT OUTER JOIN CATFEATURELINK cfl1 ON cfl1.FEATUREID=revOld.OBJECTID AND ((cfl1.PINNEDREVID IS NULL AND revOld.REVISIONID = revOld.LATESTREVISIONID) OR (cfl1.PINNEDREVID IS NOT NULL AND revOld.REVISIONID = cfl1.PINNEDREVID))) "+
            "      LEFT OUTER JOIN CATEGORY c1 ON cfl1.CATEGORYID = c1.OBJECTID, "+
            "     (FEATUREREVISION revNew LEFT OUTER JOIN BCATFEATURELINK bcfl2 ON revNew.OBJECTID = bcfl2.REVISIONID) "+
            "      LEFT OUTER JOIN BCATEGORY bc2 ON bcfl2.CATEGORYID = bc2.OBJECTID "+
            " WHERE feat.OBJECTID = revOld.OBJECTID AND "+
            "    feat.OBJECTID = revNew.FEATUREID AND "+
            "    feat.OBJECTID = revOld.OBJECTID AND "+
            "    rev.OBJECTID <= revOld.REVISIONID AND "+
            "    rev.OBJECTID > revNew.OBJECTID AND "+
            "    bc2.BASELINEID = "+_secondID+" AND c1.VERSIONID = "+_firstID+" AND "+
            "   feat.OBJECTID IN "+
            " (SELECT featrev1.OBJECTID "+
            " FROM (FEATUREFEATUREREVISION featrev1 "+
            "   LEFT OUTER JOIN CATFEATURELINK catfeatlink ON (catfeatlink.PINNEDREVID IS NULL AND featrev1.LATESTREVISIONID=featrev1.REVISIONID) OR (catfeatlink.PINNEDREVID IS NOT NULL AND featrev1.REVISIONID=catfeatlink.PINNEDREVID)) "+
            "   LEFT OUTER JOIN CATEGORY cat ON catfeatlink.CATEGORYID=cat.OBJECTID "+
            " WHERE cat.VERSIONID="+_firstID+" AND featrev1.OBJECTID IN "+
            "   (SELECT featrev2.OBJECTID "+
            "   FROM (FEATUREFEATUREREVISION featrev2 "+
            "     LEFT OUTER JOIN BCATFEATURELINK bcatfeatlink2 ON featrev2.REVISIONID=bcatfeatlink2.REVISIONID) "+
            "     LEFT OUTER JOIN BCATEGORY bcat2 ON bcatfeatlink2.CATEGORYID=bcat2.OBJECTID "+
            "   WHERE bcat2.BASELINEID="+_secondID+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.COMPASS.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.DEFERRED.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.MYCONCEPTS.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.DEFINPROGRESSREVIEW.getIIDValue()+" "+
            "   ) "+
            "   AND catfeatlink.STATEID <> "+IDCONST.COMPASS.getIIDValue()+" "+
            "   AND catfeatlink.STATEID <> "+IDCONST.DEFERRED.getIIDValue()+" "+
            "   AND catfeatlink.STATEID <> "+IDCONST.MYCONCEPTS.getIIDValue()+" "+
            "   AND catfeatlink.STATEID <> "+IDCONST.DEFINPROGRESSREVIEW.getIIDValue()+") "+
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
    FeatureRevisionEditedVersionBaselineList sortedList = new FeatureRevisionEditedVersionBaselineList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureRevisionEditedVersionBaselineList featRevList = null;
			featRevList = new FeatureRevisionEditedVersionBaselineList();
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