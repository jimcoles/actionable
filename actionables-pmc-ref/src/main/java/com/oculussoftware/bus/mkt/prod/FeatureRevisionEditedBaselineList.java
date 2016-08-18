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

public class FeatureRevisionEditedBaselineList extends BusinessObjectList implements IFeatureRevisionList
{
  protected String TABLE = "FEATUREREVISION";
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_ORDERNUM = "CREATIONDATE DESC";

  private String _firstID;
  private String _secondID;

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureRevisionEditedBaselineList() throws OculusException
	{
    super();
	}

  protected FeatureRevisionEditedBaselineList (Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return  " SELECT rev.* "+
            " FROM FEATURE feat LEFT OUTER JOIN FEATUREREVISION rev ON feat.OBJECTID=rev.FEATUREID "+
            "   LEFT OUTER JOIN ((FEATUREREVISION revOld LEFT OUTER JOIN BCATFEATURELINK bcfl1 ON revOld.OBJECTID=bcfl1.REVISIONID) LEFT OUTER JOIN BCATEGORY bc1 ON bcfl1.CATEGORYID=bc1.OBJECTID) ON feat.OBJECTID=revOld.FEATUREID and rev.OBJECTID > revOld.OBJECTID  LEFT OUTER JOIN ((FEATUREREVISION revNew LEFT OUTER JOIN BCATFEATURELINK bcfl2 ON revNew.OBJECTID=bcfl2.REVISIONID) LEFT OUTER JOIN BCATEGORY bc2 ON bcfl2.CATEGORYID=bc2.OBJECTID) ON feat.OBJECTID=revNew.FEATUREID and rev.OBJECTID <= revNew.OBJECTID "+
            " WHERE bc2.BASELINEID="+_firstID+" AND bc1.BASELINEID="+_secondID+" AND feat.OBJECTID IN "+
            " (SELECT feat1.OBJECTID "+
            " FROM ((FEATURE feat1 LEFT OUTER JOIN FEATUREREVISION rev1 ON feat1.OBJECTID=rev1.FEATUREID) "+
            "   LEFT OUTER JOIN BCATFEATURELINK bcatfeatlink ON rev1.OBJECTID=bcatfeatlink.REVISIONID) "+
            "   LEFT OUTER JOIN BCATEGORY bcat ON bcatfeatlink.CATEGORYID=bcat.OBJECTID "+
            " WHERE bcat.BASELINEID="+_firstID+" AND feat1.OBJECTID IN "+
            "   (SELECT feat2.OBJECTID "+
            "   FROM ((FEATURE feat2 LEFT OUTER JOIN FEATUREREVISION rev2 ON feat2.OBJECTID=rev2.FEATUREID) "+
            "     LEFT OUTER JOIN BCATFEATURELINK bcatfeatlink2 ON rev2.OBJECTID=bcatfeatlink2.REVISIONID) "+
            "     LEFT OUTER JOIN BCATEGORY bcat2 ON bcatfeatlink2.CATEGORYID=bcat2.OBJECTID "+
            "   WHERE bcat2.BASELINEID="+_secondID+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.COMPASS.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.DEFERRED.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.MYCONCEPTS.getIIDValue()+" "+
            "    AND bcatfeatlink2.STATEID <> "+IDCONST.DEFINPROGRESSREVIEW.getIIDValue()+" "+
            "   ) "+
            "   AND bcatfeatlink.STATEID <> "+IDCONST.COMPASS.getIIDValue()+" "+
            "   AND bcatfeatlink.STATEID <> "+IDCONST.DEFERRED.getIIDValue()+" "+
            "   AND bcatfeatlink.STATEID <> "+IDCONST.MYCONCEPTS.getIIDValue()+" "+
            "   AND bcatfeatlink.STATEID <> "+IDCONST.DEFINPROGRESSREVIEW.getIIDValue()+") "+
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
    FeatureRevisionEditedBaselineList sortedList = new FeatureRevisionEditedBaselineList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureRevisionEditedBaselineList featRevList = null;
			featRevList = new FeatureRevisionEditedBaselineList();
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