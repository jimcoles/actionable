package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ReportConfigType.java $
* Description: Integer Enumeration of FooterKinds for Custom MRD
* Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* PRB01789        Cuihua Zhang    8/9/00      added VERSION_CHANGES
*/

/** 
* Integer Enumeration of Report Configuration Types for all Reports.
* The ReportConfigType is the mechanism used to tell the UI what 
* information that it needs to obtain in order to run the Report.
*
* @author Egan Royal
*/
public final class ReportConfigType extends IntEnum
{  
	/**
  * Baseline Report int value 0.
  */
  public final static ReportConfigType BASELINE      = new ReportConfigType(0);
  
  /**
  * Baseline Comparison Report int value 1.
  */ 
  public final static ReportConfigType BASELINE_COMP   = new ReportConfigType(1);
  
  /**
  * Feature Report int value 2.
  */ 
  public final static ReportConfigType FEATURE   = new ReportConfigType(2);
  
  /**
  * Feature Comparison Report int value 3.
  */
  public final static ReportConfigType FEATURE_COMP = new ReportConfigType(3);
  
  /**
  * ProblemStatement Report int value 4.
  */
  public final static ReportConfigType PROBLEM_STATEMENT = new ReportConfigType(4);
  
  /**
  * Q&A Report int value 5.
  */
  public final static ReportConfigType Q_AND_A = new ReportConfigType(5);
  
  /**
  * Folder Report int value 6.
  */
  public final static ReportConfigType FOLDER = new ReportConfigType(6);
  
  /**
  * Search Report int value 8.
  */
  public final static ReportConfigType SEARCH = new ReportConfigType(8);
  
  /**
  * Version Report int value 9.
  */
  public final static ReportConfigType VERSION = new ReportConfigType(9);
  
  /**
  * Version Comparison Report int value 10.
  */
  public final static ReportConfigType VERSION_COMP = new ReportConfigType(10);
  
  /**
  * Version Options Report int value 11.
  */ 
  public final static ReportConfigType VERSION_OPTIONS = new ReportConfigType(11);
  
  /**
  * Version Single Options Report int value 12.
  */ 
  public final static ReportConfigType VERSION_SINGLE_OPTIONS = new ReportConfigType(12);

  /**
  * Version Feature Report int value 13.
  */
  public final static ReportConfigType VERSION_FEATURE = new ReportConfigType(13);

  /**
  * Version Folder Report int value 14.
  */
  public final static ReportConfigType VERSION_FOLDER = new ReportConfigType(14);
  
  /**
  * Feature Level Status Report int value 15.
  */
  public final static ReportConfigType FEATURE_LEVEL_STATUS = new ReportConfigType(15);
  
  /**
  * Trace Report int value 16.
  */
  public final static ReportConfigType TRACE = new ReportConfigType(16);
  
  /**
  * MRD Report int value 17.
  */
  public final static ReportConfigType MRD = new ReportConfigType(17);
  
  /**
  * Custom Product Version Report int value 18.
  */
  public final static ReportConfigType CUSTOM_PRODUCT_VERSION = new ReportConfigType(18);
  
  /**
  * Custom Folder Report int value 19.
  */
  public final static ReportConfigType CUSTOM_FOLDER = new ReportConfigType(19);
  
  /**
  * Custom Product Report int value 20.
  */
  public final static ReportConfigType CUSTOM_PRODUCT = new ReportConfigType(20);
  
  /**
  * Custom Feature Report int value 21.
  */
  public final static ReportConfigType CUSTOM_FEATURE = new ReportConfigType(21);
  
  /**
  * Custom Global Report int value 22.
  */
  public final static ReportConfigType CUSTOM_GLOBAL = new ReportConfigType(22);
  
  /**
  * Version Changes Report int value 23.
  */
  public final static ReportConfigType VERSIONCHANGES = new ReportConfigType(23);
  
  
  /**
  * Takes an int and returns a ReportConfigType iff the int is valid.
  *
  * @param d the int value
  * @return The ReportConfigType that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid ReportConfigType
  */
  public static ReportConfigType getInstance(int d) throws OculusException
  {
    if(d == 0)
      return BASELINE;
    else if(d == 1)
      return BASELINE_COMP;
    else if(d == 2)
      return FEATURE;
    else if(d == 3)
      return FEATURE_COMP;
    else if(d == 4)
      return PROBLEM_STATEMENT;
    else if(d == 5)
      return Q_AND_A;
    else if(d == 6)
      return FOLDER;
    else if(d == 8)
      return SEARCH;
    else if(d == 9)
      return VERSION;
    else if(d == 10)
      return VERSION_COMP;
    else if(d == 11)
      return VERSION_OPTIONS;
    else if(d == 12)
      return VERSION_SINGLE_OPTIONS;  
    else if(d == 13)
      return VERSION_FEATURE; 
    else if(d == 14)
      return VERSION_FOLDER;
    else if(d == 15)
      return FEATURE_LEVEL_STATUS;
	  else if(d == 16)
      return TRACE;
    else if(d == 17)
      return MRD; 
    else if(d == 18)
      return CUSTOM_PRODUCT_VERSION;
    else if(d == 19)
      return CUSTOM_FOLDER; 
    else if(d == 20)
      return CUSTOM_PRODUCT;
    else if(d == 21)
      return CUSTOM_FEATURE;
    else if(d == 22)
      return CUSTOM_GLOBAL;
    else if(d == 23)
      return VERSIONCHANGES;
    else   
      throw new OculusException("Invalid ReportConfigType.");
  }//end getInstance
  
  /** Private constructor */
  private ReportConfigType(int s) { super(s); }
}