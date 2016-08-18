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
* Filename:    DiscussionReplyColl.java
* Date:        4/11/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/
public class DiscussionReplyColl extends DiscussionTopicColl 
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "DISCUSSIONTOPIC";
  protected String COL_PARDISCUSSIONID = "PARDISCUSSIONID";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public DiscussionReplyColl() throws OculusException
	{
    super();
	}//

  protected DiscussionReplyColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PARDISCUSSIONID+"="+getIID().getLongValue()+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " ";

  }//

  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    DiscussionReplyColl sortedList = new DiscussionReplyColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly()
	{
	  DiscussionReplyColl topicList = null;
		try
		{
			topicList = new DiscussionReplyColl();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		}//end try
		catch (OculusException orioExp) {}
		return topicList;
	}//
}//end class