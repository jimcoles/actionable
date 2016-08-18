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
public class DiscussionList extends DiscussionTopicList
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
  public DiscussionList() throws OculusException
  {
    super();
  }//

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
   
    
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+          
           " AND "+DiscussionTopic.COL_TOPICKIND+"="+TopicKind.NORMAL.getIntValue()+
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " "+
           " ORDER BY CREATIONDATE DESC";
  }//

  
//----------------- IPoolable Methods ------------------------------------
  
  public Object dolly() throws OculusException
  {
    DiscussionList topicList = null;
      topicList = new DiscussionList();
      topicList.setIID(_iid);
      topicList._items = this._items;
      topicList.reset();
    return topicList;
  }//
}//end class DiscussionTopic
           