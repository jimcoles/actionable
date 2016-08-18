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
* Filename:    ObjectNotificationColl.java
* Date:        3/22/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ObjectNotificationColl extends NotificationColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */


	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ObjectNotificationColl() throws OculusException
	{
    super();
	}//

  protected ObjectNotificationColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return "SELECT *"+
           " FROM "+TABLE+
           " WHERE PAROBJECTID="+_iid.getLongValue();
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  ObjectNotificationColl notifColl = null;
			notifColl = new ObjectNotificationColl();
			notifColl.setIID(_iid);
			notifColl._items = this._items;
			notifColl.reset();
		return notifColl;
	}//
}//end class