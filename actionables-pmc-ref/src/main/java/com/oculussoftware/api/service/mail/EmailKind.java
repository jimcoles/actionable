package com.oculussoftware.api.service.mail;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: EmailKind.java $
* Description: Integer Enumeration of EmailKinds
* Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of EmailKinds or types of email messages
*
* @author Egan Royal
*/
public final class EmailKind extends IntEnum
{
  /**
  * General Email type value 0.
  */
  public final static EmailKind GENERAL          = new EmailKind(0);
  
  /**
  * Email me when I am added to a Version Work Force value 1.
  */
  public final static EmailKind VERSIONWORKFORCE = new EmailKind(1);
  
  /**
  * Email me when my User Profile is changed value 2
  */
  public final static EmailKind CHANGEDPROFILE   = new EmailKind(2);
  
  /**
  * Email me when a Feature is returned to my Inbox value 3.
  */
  public final static EmailKind FEATURERETURNED  = new EmailKind(3);
  
  /**
  * Email me when a Revision is made to a Feature that is in my Inbox value 4.
  */
  public final static EmailKind REVISIONINBOX    = new EmailKind(4);
  
  /**
  * Email me when a Revision is made to a Feature that I am assigned to value 5.
  */
  public final static EmailKind REVISIONASSIGNED = new EmailKind(5);
  
  /**
  * Email me when a Revision is made to a Feature that I am on the Version Work Force for value 6.
  */
  public final static EmailKind REVISIONVERSION  = new EmailKind(6);
  
  /**
  * Email me when a Feature has remained unacknowledged in my inbox for x days value 7.
  */
  public final static EmailKind UNACKED          = new EmailKind(7);
  
  
  /** Private constructor */
  private EmailKind(int d) 
  { 
    super(d); 
  }
  
  /**
  * Takes an int and returns a EmailKind iff the int is valid.
  *
  * @param val the int value
  * @return The EmailKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid EmailKind
  */
  public static EmailKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return GENERAL;
    else if(d == 1)
      return VERSIONWORKFORCE;
    else if(d == 2)
      return CHANGEDPROFILE;
    else if(d == 3)
      return FEATURERETURNED;
    else if(d == 4)
      return REVISIONINBOX;
    else if(d == 5)
      return REVISIONASSIGNED;
    else if(d == 6)
      return REVISIONVERSION;
    else if(d == 7)
      return UNACKED;  
    else
      throw new OculusException("Invalid EmailKind.");
  }//end getInstance
  
  
}