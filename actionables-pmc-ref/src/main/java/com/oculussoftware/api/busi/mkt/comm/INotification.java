package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.IUser;

/** This interface represents a notification to a user of a business object.  An INotification
* object manifests in a users inbox.
*
* @author Saleem Shafi
*/

/*
* $Workfile: INotification.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface INotification extends IBusinessObject
{
  /** Sets the acknowledgement state of this notification to that values in the given bit-mask.
  * com.oculussoftware.api.busi.common.process.AckKind contains the definition of each bit in 
  * the mask.
  * @param mask the bit-mask of values representing this notifications acknowledgement state
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  * @see com.oculussoftware.api.busi.common.process.AckKind
  */
  public INotification setAckMask(int mask) throws ORIOException;

  /** Sets the object for which this notification is being made.
  *
  * @param iid the IIID of the object that this notification is for
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public INotification setParObjectIID(IIID iid) throws ORIOException;

  /** Sets the recipient of this notification to the user for the given IIID object.
  *
  * @param iid the IIID of the user to recieve this notification
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public INotification setRecipientIID(IIID iid) throws ORIOException;

  /** Sets the recipient of this notification to the given IUser object.
  *
  * @param user the user to recieve this notification
  * @return this object
  * @throws com.oculussoftware.api.sysi.OculusException
  */
  public INotification setRecipientObject(IUser user) throws OculusException;

  /** Sets the body of this notification.
  * Note: This method is not being used.
  *
  * @param body the body of this notification
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public INotification setBody(String body) throws ORIOException;

  /** Sets the subject of this notification.
  * Note: This method is not being used.
  *
  * @param subject the subject of this notification
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public INotification setSubject(String subject) throws ORIOException;

  /** Sets the type of this notification.
  *
  * @param nk the type of this notificaiton
  * @return this object
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public INotification setNotificationKind(NotificationKind nk) throws ORIOException;

  /** Returns the integer bit-mask representing the acknowledgment state of this
  * notification.  com.oculussoftware.api.busi.common.process.AckKind contains the
  * definition of each bit in the mask.
  *
  * @return the bit-mask representing the acknowledgement state
  * @throws com.oculussoftware.api.sysi.OculusException
  * @see com.oculussoftware.api.busi.common.process.AckKind
  */
  public int getAckMask() throws OculusException;

  /** Returns the IIID of the object that this notification is about.
  *
  * @return the IIID of the object of the notification
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParObjectIID() throws ORIOException;

  /** Returns the IIID of the user that is the recipient of this notification.
  *
  * @return the IIID of the user being notified
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public IIID getRecipientIID() throws ORIOException;

  /** Returns the user object that is the recipient of this notification.
  *
  * @return the user being notified
  * @throws com.oculussoftware.api.sysi.OculusException
  */
  public IUser getRecipientObject() throws OculusException;

  /** Returns the body of this notification.
  * Note: This method is not being used.
  *
  * @return the body of this notification
  * @throws com.oculussoftware.api.sysi.OculusException
  */
  public String getBody() throws OculusException;

  /** Returns the subject of this notification.
  * Note: This method is not being used.
  *
  * @return the subject of this notification
  * @throws com.oculussoftware.api.sysi.OculusException
  */
  public String getSubject() throws OculusException;
  
  /** Returns the type of this notification.
  *
  * @return the type of this notification
  * @throws com.oculussoftware.api.repi.ORIOException
  */
  public NotificationKind getNotificationKind() throws ORIOException;
}