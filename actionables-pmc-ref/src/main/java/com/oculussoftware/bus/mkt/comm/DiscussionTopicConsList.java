package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;


/**
* Filename:    DiscussionTopicConsList.java
* Date:        5/3/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DiscussionTopicConsList extends DiscussionTopicList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

	//----------------------------- Public Constructor -------------------------
	/**
  *
  */
	public DiscussionTopicConsList() throws OculusException
	{
    super();
	}//

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT *"+
           " FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue() +
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " AND TOPICKIND = "+TopicKind.CON.getIntValue()+
           " ORDER BY CREATIONDATE DESC";
  }//

	
//----------------- IPoolable Methods ------------------------------------
	
	public Object dolly() throws OculusException
	{
	  DiscussionTopicConsList topicList = null;
			topicList = new DiscussionTopicConsList();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		return topicList;
	}//
}//end class DiscussionTopic