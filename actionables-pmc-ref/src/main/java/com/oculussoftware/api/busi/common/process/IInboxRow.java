package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.NotificationKind;
import com.oculussoftware.api.busi.common.org.IUser;
import java.sql.Timestamp;

/*
* $Workfile: IInboxRow.java $
* Description: Wraps up the information needed for a row in the
* Inbox.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Contains all of the information needed to populate a row in the Inbox.
* This interface is necessary to improve scaleablity and performance.
*
* @author Egan Royal
*/
public interface IInboxRow extends IRObject
{
  /**
  * Accessor method that returns the NotificationKind or the enumerated
  * type of the Notification.
  * @return The NotificationKind.
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public NotificationKind getNotificationKind() throws ORIOException;
  
  /**
  * Accessor method that returns the display value for the User name.
  * Currently formatted: Lastname, Firstname.
  * @return The display value of the User name.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getUserName() throws ORIOException;
  
  /**
  * Accessor method that returns the display value for the Product name.
  * @return The display value of the Product name.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getProductName() throws ORIOException;
  
  /**
  * Accessor method that returns the display value for the ProductVersion
  * name.
  * @return The display value of the ProductVersion name.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getVersionName() throws ORIOException;
  
  /**
  * Accessor method that returns the display value for the State name.
  * @return The display value of the State name.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getStateName() throws ORIOException;
  
  /**
  * Accessor method that returns the order number (weight) for the priority.
  * @return The priority order number.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public int getPriorityOrderNum() throws ORIOException;

  /**
  * Accessor method that returns the IIID of the Parent Object.
  * @return The IIID of the Parent Object.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getParObjectIID() throws ORIOException;
  
  /**
  * Accessor method that returns a Timestamp for the creation date of the 
  * Notification.
  * @return The creation date of the Notification.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public Timestamp getDate() throws ORIOException;

  /**
  * Accessor method that returns the IDCONST that corresponds to the
  * type of the Parent Object of a DiscussionTopic.  Returns null if 
  * the long value is 0.
  * @return The IDCONST for the type of the Parent Object of a
  * DiscussionTopic.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDCONST getDiscussParObjectType() throws OculusException;
  
  /**
  * Accessor method that returns IIID of the Parent Object of a 
  * DiscussionTopic.  Returns null if the long value is 0.
  * @return The IIID of the Parent Object of a DiscussionTopic.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getDiscussParObjectIID() throws ORIOException;
  
  /**
  * Accessor method that returns true if the InboxRow corresponds to a
  * DiscussionTopic and the Parent Object of the DiscussionTopic is
  * in the Compass State.
  * @return true -- iff the InboxRow corresponds to a DiscussionTopic
  * and the Parent Object of the DiscussionTopic is in the Compass State.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean isDiscussCompass() throws OculusException;
  
  /**
  * Accessor method that returns the int value of the AckMask for the 
  * Notification.
  * @return the int value for the AckMask for the Notification.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public int getAckMask() throws ORIOException;
  
  /**
  * Accessor method that returns the int value of the AckMask for the 
  * Notification.
  * @return the int value for the AckMask for the Notification.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getPriority() throws ORIOException;
  
  /**
  * Takes an AckKind and returns whether or not the AckMask has been 
  * acknowledged.
  * @return true - iff for this AckKind(bit index) the field is set.
  * @param ak The AckKind(index) of the bit in question.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public boolean isAcknowledged(AckKind ak) throws ORIOException;
  
  /**
  * Takes an IUser and returns true iff this IUser may interrupt the 
  * process for the Parent Object of this Notification.
  * @return true - iff this IUser may interrupt the process.
  * @param user The IUser in question.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean canInterrupt(IUser user) throws OculusException;
  
  /**
  * Mutator method that sets the current ackmask value.
  * @return this
  * @param a The int value that is desired for the value of the ackmask.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IInboxRow setAckMask(int a) throws ORIOException;
  
  /**
  * Takes an AckKind and sets the bit to 1 for the current ackmask.
  * @return this
  * @param ak The AckKind(index) for the bit to be set.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IInboxRow acknowledge(AckKind ak) throws ORIOException;
  
  /**
  * Takes an AckKind and clears the bit to 0 for the current ackmask.
  * @return this
  * @param ak The AckKind(index) for the bit to be clearred.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IInboxRow unacknowledge(AckKind ak) throws ORIOException;
}