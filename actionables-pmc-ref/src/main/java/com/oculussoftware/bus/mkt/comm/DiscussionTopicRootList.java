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
public class DiscussionTopicRootList extends DiscussionTopicList
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
  public DiscussionTopicRootList() throws OculusException
  {
    super();
  }//

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND "+DiscussionTopic.COL_TOPICKIND+"<>"+TopicKind.CON.getIntValue()+
           " AND "+DiscussionTopic.COL_TOPICKIND+"<>"+TopicKind.PRO.getIntValue()+          
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " "+
           " ORDER BY CREATIONDATE DESC";
  }//

  
//----------------- IPoolable Methods ------------------------------------
  
  public Object dolly() throws OculusException
  {
    DiscussionTopicRootList topicList = null;
      topicList = new DiscussionTopicRootList();
      topicList.setIID(_iid);
      topicList._items = this._items;
      topicList.reset();
    return topicList;
  }//
}//end class DiscussionTopic
           