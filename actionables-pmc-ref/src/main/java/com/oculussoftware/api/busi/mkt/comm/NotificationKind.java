package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of notifications.
*
* @author Egan Royal
*/

/*
* $Workfile: NotificationKind.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class NotificationKind extends IntEnum
{
  /** This value (0) represents a feature notification for which the recipient has control. */
  public final static NotificationKind WORKFLOW = new NotificationKind(0);
  /** This value (1) represents a discussion topic. */
  public final static NotificationKind TOPIC    = new NotificationKind(1);
  /** This value (2) represents a feature notification for which the recipient does not have control. */
  public final static NotificationKind CC       = new NotificationKind(2);
  /** This value (4) represents a market input. */
  public final static NotificationKind INPUT    = new NotificationKind(4);
  
	/** Returns the NotificationKind instance that corresponds to the given parameter.
  *
  * @param i int value of the desired NotificationKind
  * @return the NotificationKind corresponding to the given parameter
  * @exception com.oculussoftware.api.sysi.OculusException if the parameter does not
  *            corespond to a valid NotificationKind
  */
  public static NotificationKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return WORKFLOW;
    else if(d == 1)
      return TOPIC;
    else if(d == 2)
      return CC;
    else if(d == 4)
      return INPUT;
    else
      throw new OculusException("Invalid NotificationKind.");
  }
  
  /** Private constructor */
  private NotificationKind(int s) { super(s); }
}