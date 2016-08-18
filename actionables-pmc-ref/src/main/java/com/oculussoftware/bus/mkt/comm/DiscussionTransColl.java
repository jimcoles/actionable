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
* Filename:    DiscussionTransColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DiscussionTransColl extends DiscussionTopicColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  //----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public DiscussionTransColl() throws OculusException
	{
    super();
	}//

  protected DiscussionTransColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+
           
           " AND "+DiscussionTopic.COL_TOPICKIND+"="+TopicKind.TRANSACTIONCOMMENT.getIntValue()+

           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " ";

  }//
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly()
	{
	  DiscussionTransColl topicList = null;
		try
		{
			topicList = new DiscussionTransColl();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		}//end try
		catch (OculusException orioExp) {}
		return topicList;
	}//
}//end class