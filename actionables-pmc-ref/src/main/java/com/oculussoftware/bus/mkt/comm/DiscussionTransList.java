package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;

/**
* Filename:    DiscussionTopicProsList.java
* Date:        5/4/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/
public class DiscussionTransList extends DiscussionTopicList
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
  public DiscussionTransList() throws OculusException
  {
    super();
  }//

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
   
    
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+         
           " AND "+DiscussionTopic.COL_TOPICKIND+"="+TopicKind.TRANSACTIONCOMMENT.getIntValue()+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " "+
           " ORDER BY CREATIONDATE DESC";
  }//

  
//----------------- IPoolable Methods ------------------------------------
  
  public Object dolly() throws OculusException
  {
    DiscussionTransList topicList = null;
      topicList = new DiscussionTransList();
      topicList.setIID(_iid);
      topicList._items = this._items;
      topicList.reset();
    return topicList;
  }//
}//end class DiscussionTopic
           