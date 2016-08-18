package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;

import java.util.*;

/**
* Filename:    InboxRowFeatureList.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class InboxRowFeatureList extends InboxRowList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public InboxRowFeatureList() throws OculusException
	{
    super();
	}//

  protected InboxRowFeatureList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return 
     "SELECT note.OBJECTID, note.NOTIFICATIONKIND, note.CREATIONDATE, note.ACKMASK, "+
 "usr.FIRSTNAME, usr.LASTNAME, "+ 
  "PRODUCT.NAME AS PRODUCTNAME, "+ 
  "PRODUCTVERSION.NAME AS VERSIONNAME, "+ 
  "ENTITYNAME=FEATUREREVISION.NAME + ' [ID:'+LTRIM(STR(FEATURE.VISIBLEID))+']', "+ 
  "STATE.NAME AS STATENAME, "+ 
  "ENUMLITERAL.ORDERNUM AS PRIORITYORDERNUM, ENUMLITERAL.NAME AS PRIORITY,"+ 
  "CATFEATURELINK.OBJECTID AS PARENTITYID, NULL AS DISCUSSPAROBJECTTYPE, NULL AS DISCUSSPAROBJECTID, "+ 
  "NULL AS DISCUSSPARPRODSTATE,  "+
  "NULL AS DISCUSSPARVERSTATE, "+
  "NULL AS DISCUSSPARCATSTATE, "+
  "NULL AS DISCUSSPARFEATSTATE, "+
  "mm.USERID AS MKTMGRID, "+
  "em.USERID AS ENGMGRID "+
  "FROM (((((((((((NOTIFICATION note LEFT OUTER JOIN CATFEATURELINK ON note.PAROBJECTID = CATFEATURELINK.OBJECTID) "+ 
    "LEFT OUTER JOIN ENUMLITERAL ON CATFEATURELINK.PRIORITYID = ENUMLITERAL.OBJECTID) "+ 
    "LEFT OUTER JOIN FEATURE ON CATFEATURELINK.FEATUREID = FEATURE.OBJECTID) "+ 
    "LEFT OUTER JOIN FEATUREREVISION ON FEATURE.OBJECTID = FEATUREREVISION.FEATUREID "+ 
              "AND CATFEATURELINK.FEATUREID = FEATUREREVISION.FEATUREID "+ 
    "AND ((CATFEATURELINK.PINNEDREVID IS NOT NULL AND FEATUREREVISION.OBJECTID = CATFEATURELINK.PINNEDREVID) "+ 
      "OR (CATFEATURELINK.PINNEDREVID IS NULL AND FEATUREREVISION.OBJECTID = FEATURE.LATESTREVISIONID))) "+ 
    "LEFT OUTER JOIN CATEGORY ON CATFEATURELINK.CATEGORYID = CATEGORY.OBJECTID) "+ 
    "LEFT OUTER JOIN PRODUCTVERSION ON CATEGORY.VERSIONID = PRODUCTVERSION.OBJECTID) "+ 
    "LEFT OUTER JOIN PRODUCT ON PRODUCTVERSION.PRODUCTID = PRODUCT.OBJECTID) "+ 
    "LEFT OUTER JOIN STATE ON CATFEATURELINK.STATEID = STATE.OBJECTID) "+ 
    "LEFT OUTER JOIN APPUSER usr ON note.CREATORID = usr.OBJECTID) "+ 
    "LEFT OUTER JOIN OBJECTROLEASSIGN mm ON CATFEATURELINK.OBJECTID = mm.PAROBJECTID AND mm.ROLEID = "+IDCONST.MKTMGRROLE+") "+
    "LEFT OUTER JOIN OBJECTROLEASSIGN em ON CATFEATURELINK.OBJECTID = em.PAROBJECTID AND em.ROLEID = "+IDCONST.ENGMGRROLE+") "+
  "WHERE FEATURE.OBJECTID = "+getIID().getLongValue()+
  " AND (note.NOTIFICATIONKIND = 0 OR note.NOTIFICATIONKIND = 2) "+
  " AND FEATURE.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
	" AND CATFEATURELINK.DELETESTATE = "+DeleteState.NOT_DELETED.getIntValue()+
"ORDER BY "+_sortby;
  }//  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  InboxRowFeatureList inboxRowList = null;
    inboxRowList = new InboxRowFeatureList();
    inboxRowList.setIID(_iid);
    inboxRowList._items = this._items;
    inboxRowList.reset();
		return inboxRowList;
	}//
  
}//end class