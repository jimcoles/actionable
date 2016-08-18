package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;


/** Represents a collection of notifications.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: INotificationColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface INotificationColl extends IBusinessObjectColl
{
  /** Returns whether or not the collection contains any more notifications.
  *
  * @return true if there are more notifications, false otherwise
  */
  public boolean hasMoreNotifications();
  
  /** Returns the next notification in the collection
  *
  * @return the next notification in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public INotification nextNotification() throws OculusException;
}