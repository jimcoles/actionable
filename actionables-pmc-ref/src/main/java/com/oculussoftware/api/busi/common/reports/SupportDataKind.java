package com.oculussoftware.api.busi.common.reports; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: SupportDataKind.java $
* Description: Integer Enumeration of Support Data Kinds for the 
* Custom MRD Report.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Integer Enumeration of Support Data for Custom MRD Reports.  Support Data
* is the data that may either be printed in the body or the appendix of the
* Report.
*
* @author Egan Royal
*/
public final class SupportDataKind extends IntEnum
{
	/**
  * File int value 0.
  */
  public final static SupportDataKind FILE  = new SupportDataKind(0);
  
  /**
  * Link int value 1.
  */ 
  public final static SupportDataKind LINK = new SupportDataKind(1);
  
  /**
  * DiscussionTopic int value 2.
  */ 
  public final static SupportDataKind DISCUSSIONTOPIC = new SupportDataKind(2);
  
  /**
  * Image int value 3.
  */ 
  public final static SupportDataKind IMAGE = new SupportDataKind(3);
  
  /**
  * EngSpec int value 4.
  */ 
  public final static SupportDataKind ENGSPEC = new SupportDataKind(4);
  
  /**
  * Approval int value 5.
  */ 
  public final static SupportDataKind APPROVAL = new SupportDataKind(5);
  
  /**
  * Alternative int value 6.
  */ 
  public final static SupportDataKind ALTERNATIVE = new SupportDataKind(6);
  
  /**
  * Pros and Cons int value 7.
  */ 
  public final static SupportDataKind PROSANDCONS = new SupportDataKind(7);
  
  /**
  * Takes an int and returns a SupportDataKind iff the int is valid.
  *
  * @param d the int value
  * @return The SupportDataKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid SupportDataKind
  */
  public static SupportDataKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return FILE;
    else if(d == 1)
      return LINK;
    else if(d == 2)
      return DISCUSSIONTOPIC;
    else if(d == 3)
      return IMAGE;
    else if(d == 4)
      return ENGSPEC;
    else if(d == 5)
      return APPROVAL;
    else if(d == 6)
      return ALTERNATIVE;
    else if(d == 7)
      return PROSANDCONS;  
    else
      throw new OculusException("Invalid SupportDataKind.");
  }//end getInstance
  
  /** Private constructor */
  private SupportDataKind(int s) { super(s); }
}