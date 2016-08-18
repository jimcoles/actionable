package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;


import java.util.*;

/**
* Filename:    NotificationColl.java
* Date:        3/6/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class NotificationColl extends BusinessObjectColl implements INotificationColl, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "NOTIFICATION";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public NotificationColl() throws OculusException
	{
    super();
	}//

  protected NotificationColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+Notification.COL_RECIPIENTID+"="+getIID().getLongValue();
  }//

  protected String getClassName () { return "Notification"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public INotification nextNotification() throws OculusException
	{
		return (INotification)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreNotifications()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    NotificationColl sortedList = new NotificationColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  NotificationColl notifColl = null;
			notifColl = new NotificationColl();
			notifColl.setIID(_iid);
			notifColl._items = this._items;
			notifColl.reset();
		return notifColl;
	}//
}//end class