package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of alert configurations.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: IAlertConfigColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IAlertConfigColl extends IBusinessObjectColl
{
  /** Returns the next alert configuration in the collection
  *
  * @return the next alert configuration in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlertConfig nextAlertConfig()
    throws OculusException;

  /** Returns whether or not the collection contains any more alert configurations.
  *
  * @return true if there are more alert configurations, false otherwise
  */
  public boolean hasMoreAlertConfigs();
  
}