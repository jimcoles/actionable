package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    EngrSpecColl.java
* Date:        2/15/00
* Description: 
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
* PRB01232         Cuihua Zhang    6/28/2000   added the _sortby items to the select list
*/

public class EngrSpecHyperLinkList extends FeatureHyperLinkList
{
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public EngrSpecHyperLinkList() throws OculusException
  {
    super();
  }

  protected EngrSpecHyperLinkList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT LINK."+COL_OBJECTID+", LINK.LABEL, LINK.URL, LINK.CREATIONDATE, APP.LASTNAME, APP.FIRSTNAME" +
           " FROM (EXTHYPERLINK AS LINK LEFT OUTER JOIN APPUSER AS APP ON APP.OBJECTID=LINK.CREATORID) "+
           "      LEFT OUTER JOIN CATFEATURELINK ON LINK.PAROBJECTID=CATFEATURELINK.OBJECTID "+
           " WHERE CATFEATURELINK.FEATUREID="+getIID().getLongValue()+" "+
           "   AND LINK."+COL_TYPE+" = "+HyperLinkType.ENGRSPEC.getIntValue()+
           " ORDER BY "+_sortby;
  }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    EngrSpecHyperLinkList sortedList = new EngrSpecHyperLinkList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    EngrSpecHyperLinkList linkList = null;
      linkList = new EngrSpecHyperLinkList();
      linkList.setIID(_iid);
      linkList._items = this._items;
      linkList.reset();
    return linkList;
  }
}