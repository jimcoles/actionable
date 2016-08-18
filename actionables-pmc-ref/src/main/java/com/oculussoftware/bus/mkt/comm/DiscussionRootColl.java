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
public class DiscussionRootColl extends DiscussionTopicColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  //----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public DiscussionRootColl() throws OculusException
	{
    super();
	}//

  protected DiscussionRootColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND "+DiscussionTopic.COL_TOPICKIND+"<>"+TopicKind.CON.getIntValue()+
           " AND "+DiscussionTopic.COL_TOPICKIND+"<>"+TopicKind.PRO.getIntValue()+
           
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " ";

  }//
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly()
	{
	  DiscussionRootColl topicList = null;
		try
		{
			topicList = new DiscussionRootColl();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		}//end try
		catch (OculusException orioExp) {}
		return topicList;
	}//
}//end class