package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of discussion topics.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: IDiscussionTopicColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IDiscussionTopicColl extends IBusinessObjectColl
{
  /** Returns the next discussion topic in the collection
  *
  * @return the next discussion topic in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopic nextDiscussionTopic()
    throws OculusException;

  /** Returns whether or not the collection contains any more discussion topics.
  *
  * @return true if there are more discussion topics, false otherwise
  */
  public boolean hasMoreDiscussionTopics();
}