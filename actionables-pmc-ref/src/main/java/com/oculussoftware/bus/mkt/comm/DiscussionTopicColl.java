package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    DiscussionTopicColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DiscussionTopicColl extends BusinessObjectColl implements IDiscussionTopicColl, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "DISCUSSIONTOPIC";
  protected String COL_PAROBJECTID = "PAROBJECTID";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public DiscussionTopicColl() throws OculusException
	{
    super();
	}//

  protected DiscussionTopicColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+  
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " ";

  }//

  protected String getClassName () { return "DiscussionTopic"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public IDiscussionTopic nextDiscussionTopic() throws OculusException
	{
		return (IDiscussionTopic)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreDiscussionTopics()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    DiscussionTopicColl sortedList = new DiscussionTopicColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  DiscussionTopicColl topicList = null;
			topicList = new DiscussionTopicColl();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		return topicList;
	}//
}//end class