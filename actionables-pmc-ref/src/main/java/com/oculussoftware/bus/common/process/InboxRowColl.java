package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;

import java.util.*;

/**
* Filename:    InboxRowColl.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class InboxRowColl extends ReposObjectColl implements IInboxRowColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */


	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public InboxRowColl() throws OculusException
	{
    super();
	}//

  protected InboxRowColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    throw new ORIOException("Use InboxRowList");
//      "SELECT NOTIFICATION.NOTIFICATIONKIND, NOTIFICATION.OBJECTID, NOTIFICATION.CREATIONDATE, "+ 
//      "APPUSER.FIRSTNAME, APPUSER.LASTNAME, "+ 
//      "PRODUCT.NAME AS PRODUCTNAME, "+ 
//      "PRODUCTVERSION.NAME AS VERSIONNAME, "+ 
//      "FEATUREREVISION.NAME AS FEATURENAME, "+ 
//      "STATE.NAME AS STATENAME, "+
//      "CATFEATURELINK.PRIORITYID, CATFEATURELINK.OBJECTID AS CATFEATURELINKID, "+
//      "DISCUSSIONTOPIC.SUBJECT, DISCUSSIONTOPIC.OBJECTID AS DISCUSSIONTOPICID "+
//      "FROM (((((((((NOTIFICATION LEFT OUTER JOIN DISCUSSIONTOPIC ON NOTIFICATION.PAROBJECTID = DISCUSSIONTOPIC.OBJECTID) "+
//      "LEFT OUTER JOIN CATFEATURELINK ON NOTIFICATION.PAROBJECTID = CATFEATURELINK.OBJECTID "+
//                                     "OR DISCUSSIONTOPIC.PAROBJECTID = CATFEATURELINK.OBJECTID) "+
//      "LEFT OUTER JOIN FEATURE ON CATFEATURELINK.FEATUREID = FEATURE.OBJECTID) "+
//      "LEFT OUTER JOIN FEATUREREVISION ON FEATURE.OBJECTID = FEATUREREVISION.FEATUREID AND CATFEATURELINK.FEATUREID = FEATUREREVISION.FEATUREID) "+
//      "LEFT OUTER JOIN CATEGORY ON CATFEATURELINK.CATEGORYID = CATEGORY.OBJECTID "+
//                               "OR DISCUSSIONTOPIC.PAROBJECTID = CATEGORY.OBJECTID) "+
//      "LEFT OUTER JOIN PRODUCTVERSION ON CATEGORY.VERSIONID = PRODUCTVERSION.OBJECTID "+
//                                     "OR DISCUSSIONTOPIC.PAROBJECTID = PRODUCTVERSION.OBJECTID) "+
//      "LEFT OUTER JOIN PRODUCT ON DISCUSSIONTOPIC.PAROBJECTID = PRODUCT.OBJECTID "+ 
//                              "OR PRODUCTVERSION.PRODUCTID = PRODUCT.OBJECTID) "+
//      "LEFT OUTER JOIN STATE ON CATFEATURELINK.STATEID = STATE.OBJECTID) "+
//      "LEFT OUTER JOIN APPUSER ON NOTIFICATION.CREATORID = APPUSER.OBJECTID) "+
//      "WHERE NOTIFICATION.RECIPIENTID = "+getIID().getLongValue()+" "+
//      "ORDER BY NOTIFICATION.CREATIONDATE DESC";
  }//

  protected String getClassName () { return "InboxRow"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public IInboxRow nextInboxRow() throws OculusException
	{
		return (IInboxRow)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreInboxRows()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    InboxRowColl sortedList = new InboxRowColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  InboxRowColl inboxRowList = null;
			inboxRowList = new InboxRowColl();
			inboxRowList.setIID(_iid);
			inboxRowList._items = this._items;
			inboxRowList.reset();
		return inboxRowList;
	}//
}//end class