package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ReportFormatKind.java $
* Description: Integer Enumeration of FooterKinds for Custom MRD
* Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* -----           Cuihua Zhang    7/20/00     added SUMMARY, ALL_TEXT_ANSWERS,ROLLUP for QASummaryReport
*/

/** 
* Integer Enumeration of Report Formats for all Reports.
* The ReportFormatKind is the mechanism used by the UI to control
* the format of the printed Report.  This class also has 
* a name field that is used as the display name for the format.
*
* @author Egan Royal
*/
public final class ReportFormatKind extends IntEnum
{
  private String _name;
  
	/**
  * Text Only Report int value 0.
  */
  public final static ReportFormatKind TEXT_ONLY      = new ReportFormatKind(0,"Text Only");
  
  /**
  * Graph Only Report int value 1.
  */ 
  public final static ReportFormatKind GRAPH_ONLY     = new ReportFormatKind(1,"Graph Only");
  
  /**
  * Graph and Text Report int value 2.
  */ 
  public final static ReportFormatKind GRAPH_AND_TEXT = new ReportFormatKind(2,"Graph and Text");

  /**
  * Summary Report int value 3.
  */
  public final static ReportFormatKind SUMMARY      = new ReportFormatKind(3,"Summary");
  
  /**
  * All Text Answers Report int value 4.
  */ 
  public final static ReportFormatKind ALL_TEXT_ANSWERS     = new ReportFormatKind(4,"All Text Answers");
  
  /**
  * Rollup Report int value 5.
  */ 
  public final static ReportFormatKind ROLLUP = new ReportFormatKind(5,"Roll Up");

  /**
  * Takes an int and returns a ReportFormatKind iff the int is valid.
  *
  * @param d the int value
  * @return The ReportFormatKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid ReportFormatKind
  */
  public static ReportFormatKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return TEXT_ONLY;
    else if(d == 1)
      return GRAPH_ONLY;
    else if(d == 2)
      return GRAPH_AND_TEXT;
    else if(d == 3)
      return SUMMARY;
    else if(d == 4)
      return ALL_TEXT_ANSWERS;
    else if(d == 5)
      return ROLLUP;
    else
      throw new OculusException("Invalid ReportFormatKind.");
  }//end getInstance
  
  /** Private constructor */
  private ReportFormatKind(int s,String name)
  {
    super(s); 
    _name = name;
  }
  
  /**
  * This accessor method returns the name (display String) of the ReportFormatKind.
  * @return The name of the ReportFormatKind.  
  */
  public String getName()
  { return _name; }
}
