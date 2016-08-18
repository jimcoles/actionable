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
* Filename:    ObjectUserNotificationColl.java
* Date:        3/22/00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ObjectUserNotificationColl extends NotificationColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected IIID _useriid;
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ObjectUserNotificationColl() throws OculusException
	{
    super();
	}//

  protected ObjectUserNotificationColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return "SELECT *"+
           " FROM "+TABLE+
           " WHERE PAROBJECTID="+_iid.getLongValue()+
           " AND RECIPIENTID="+_useriid.getLongValue();
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  ObjectUserNotificationColl notifColl = null;
			notifColl = new ObjectUserNotificationColl();
			notifColl.setIID(_iid);
			notifColl._items = this._items;
			notifColl.reset();
		return notifColl;
	}//
  
  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);
    
    if(args.containsKey("useriid"))
      _useriid = (IIID)args.get("useriid");
      
    return this;
  }
}//end class