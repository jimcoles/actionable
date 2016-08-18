package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: AckKind.java $
* Description: Integer Enumeration of Acknowledgement Kinds for Notifications.
* Corresponds to the index of the bit in a bit-field.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of Acknowledgement Kinds for Notifications.  
* The integer value corresponds to the index of the bit in a 
* bit-field.
*
* @author Egan Royal
*/
public final class AckKind extends IntEnum
{
	/**
  * The index (0) of the bit that tells whether the new FeatureCategoryLink
  * Notification has/has not been acknowledged in the Inbox. 
  */
  public final static AckKind INBOX      = new AckKind(0);
  
  /**
  * The index (1) of the bit that tells whether the Notification of
  * the revised FeatureCategoryLink has/has not been acknowledged 
  * in the Inbox. 
  */ 
  public final static AckKind REVISION   = new AckKind(1);
  
  /**
  * The index (3) of the bit that tells whether the email was/was 
  * not sent for the unacknowledged Notification  
  */ 
  public final static AckKind EMAIL_SENT = new AckKind(3);
  
  /**
  * Takes an int and returns an AckKind iff the int is valid.
  *
  * @param val the int value
  * @return The AckKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid AckKind
  */   
  public static AckKind getInstance(int val) throws OculusException
  {
    if(val == 0)
      return INBOX;
    else if(val == 1)
      return REVISION;
    else if(val == 3)
      return EMAIL_SENT;
    else
      throw new OculusException("Invalid AckKind.");
  }//end getInstance
  
  /** 
  * Private constructor 
  */
  private AckKind(int s) { super(s); }
}