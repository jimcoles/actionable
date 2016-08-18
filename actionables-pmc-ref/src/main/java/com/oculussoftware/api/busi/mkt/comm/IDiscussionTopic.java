package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

import java.sql.Timestamp;

/** This interface represents a "message" about a particular business object.  The concept
* mimics the functionality of a newsgroup message.  Discussion threads can be spawned
*
* @author Egan Royal
*/

/*
* $Workfile: IDiscussionTopic.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IDiscussionTopic extends IBusinessObject
{
  /** Sets the number of replies that this discussion topic has.
  *
  * param num the number of replies to this discussion topic
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setNumChildren(int num) throws ORIOException;

  /** Sets the subject of this discussion topic.
  *
  * param sub the subject of this discussion topic
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setSubject(String sub) throws ORIOException;

  /** Sets the date/time of the last edit to this discussion topic.
  *
  * param date the date/time of the last edit
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setEditedDate(Timestamp date) throws ORIOException;

  /** Sets the type of this discussion topic.
  *
  * param tk the type of this discussion topic
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopic setTopicKind(TopicKind tk) throws OculusException;

  /** Sets whether or not this message is the root of a thread.
  *
  * param r true if this is a root topic, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic isRoot(boolean r) throws ORIOException;

  /** Sets the depth of this discussion topic in the list of replies.l
  *
  * param the depth of this discussion topic
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setTreeLevel(int tl) throws ORIOException;

  /** Sets the parent business object of this discussion topic to the bo of the given IIID.
  *
  * param piid the IIID of the parent business object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setParObjectIID(IIID piid) throws ORIOException;

  /** Sets the text of the discussion topic to the given String.
  *
  * param body the body of this discussion topic
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setBody(String body) throws ORIOException;

  /** Sets the parent discussion topic for this message to the topic of the given IIID.
  *
  * param diid the IIID of the parent discussion topic
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setParDiscussObjectIID(IIID diid) throws ORIOException;

  /** Sets the type of the parent object for this discussion topic.
  *
  * param c the IDCONST of the CLASS of the parent object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDiscussionTopic setParObjectType(IDCONST c) throws ORIOException;

  /** Creates an exact copy of this discussion topic.
  *
  * @return the new copy of this discussion topic
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopic createCopy() throws OculusException;

  /** Returns the number of replies that this discussion topic has.
  *
  * @return the number of replies to this message
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public int getNumChildren() throws ORIOException;

  /** Returns the subject line of the discussion topic.
  *
  * @return the subject of the message
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public String getSubject() throws OculusException;

  /** Returns the date that this discussion topic was last edited.
  *
  * @return the date of the last edit
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getEditedDate() throws OculusException;

  /** Returns the type of this discussion topic.
  *
  * @return the type of this discussion topic
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public TopicKind getTopicKind() throws ORIOException;

  /** Returns whether or not this discussion topic is the root of a thread or not.
  *
  * @return true if this is the root of a thread, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isRoot() throws ORIOException;

  /** Returns the depth of this message in a discussion thread.  This value makes it easier to
  * display the entire list of discussions in a tree format.
  *
  * @return the int value of the depth of the message in a thread
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public int getTreeLevel() throws ORIOException;

  /** Returns the IIID of the business object that this discussion topic is connected to.
  *
  * @return the IIID of the parent business object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParObjectIID() throws ORIOException;

  /** Returns the text of the discussion topic.
  *
  * @return the body of the message
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getBody() throws OculusException;

  /** Returns the IIID of the parent discussion topic object.  This is valid when this discussion
  * topic is a reply to another discussion topic.
  *
  * @return the IIID of the parent discussion topic
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParDiscussObjectIID()
    throws ORIOException;
  
  /** Returns the type of the parent object.  The returned value is the IDCONST object that
  * refers to the CLASS of the object.
  *
  * @return the IDCONST of the CLASS of the parent
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IDCONST getParObjectType()
    throws ORIOException;
}