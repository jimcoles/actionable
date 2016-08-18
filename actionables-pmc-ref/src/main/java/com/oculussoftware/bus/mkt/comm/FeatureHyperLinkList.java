package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    FeatureHyperLinkList.java
* Date:        2/14/00
* Description: Handles a list of IHyperLink objects
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

public class FeatureHyperLinkList extends HyperLinkList
{
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FeatureHyperLinkList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected FeatureHyperLinkList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT LINK."+COL_OBJECTID+", LINK.LABEL, LINK.URL, LINK.CREATIONDATE, APP.LASTNAME, APP.FIRSTNAME" +
           " FROM (EXTHYPERLINK AS LINK LEFT OUTER JOIN APPUSER AS APP ON APP.OBJECTID=LINK.CREATORID) "+
           "       LEFT OUTER JOIN CATFEATURELINK AS CAT ON LINK.PAROBJECTID=CAT.OBJECTID "+
           " WHERE CAT.FEATUREID="+getIID().getLongValue()+" "+
           "       AND LINK."+COL_TYPE+" <> "+HyperLinkType.ENGRSPEC.getIntValue()+
         
           " UNION" + 
           
           " SELECT HYPERLINK."+COL_OBJECTID+", HYPERLINK.LABEL, HYPERLINK.URL, HYPERLINK.CREATIONDATE, APPP.LASTNAME, APPP.FIRSTNAME" +
           " FROM (EXTHYPERLINK AS HYPERLINK LEFT OUTER JOIN APPUSER AS APPP ON APPP.OBJECTID=HYPERLINK.CREATORID) "+
           "      LEFT OUTER JOIN STDFEATURELINK AS STD ON HYPERLINK.PAROBJECTID=STD.OBJECTID "+
           " WHERE STD.FEATUREID="+getIID().getLongValue()+" "+
           "      AND HYPERLINK."+COL_TYPE+" <> "+HyperLinkType.ENGRSPEC.getIntValue()+
           " ORDER BY "+_sortby;

  }

  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FeatureHyperLinkList sortedList = new FeatureHyperLinkList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  

//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  FeatureHyperLinkList linkList = null;
			linkList = new FeatureHyperLinkList();
			linkList.setIID(_iid);
			linkList._items = this._items;
			linkList.reset();
		return linkList;
	}
}