package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.busi.common.reports.*;

/*
* $Workfile: IReportFormatter.java $
* Description: Maintains formatting information for Standard Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Maintains formatting information for Standard Reports.
*
* @author Egan Royal
*/
public interface IReportFormatter
{  
  /**
  * Returns the ReportKind object.
  * @return The ReportKind object.
  */ 
  public ReportKind getReportKind();
  
  /**
  * Returns the ReportFormatKind object.
  * @return The ReportFormatKind object.
  */
  public ReportFormatKind getReportFormatKind();
  
  /**
  * Returns the GraphKind object.
  * @return The GraphKind object.
  */
  public GraphKind getGraphKind();
  
  /**
  * Returns the GraphColor object.
  * @return The GraphColor object.
  */
  public GraphColor getGraphColor();
  
  /**
  * Returns the GraphSize object.
  * @return The GraphSize object.
  */
  public GraphSize getGraphSize();
  
  /**
  * Returns the GraphDimensions object.
  * @return The GraphDimensions object.
  */
  public GraphDimensions getGraphDimensions();
  
  /**
  * Sets the ReportFormatKind object.
  * @param The ReportFormatKind.
  * @return this
  */
  public IReportFormatter setReportFormatKind(ReportFormatKind rfk);
  
  /**
  * Sets the GraphKind object.
  * @param The GraphKind.
  * @return this
  */
  public IReportFormatter setGraphKind(GraphKind gk);
  
  /**
  * Sets the GraphColor object.
  * @param The GraphColor.
  * @return this
  */
  public IReportFormatter setGraphColor(GraphColor gc);
  
  /**
  * Sets the GraphSize object.
  * @param The GraphSize.
  * @return this
  */
  public IReportFormatter setGraphSize(GraphSize gs); 
  
  /**
  * Sets the GraphDimensions object.
  * @param The GraphDimensions.
  * @return this
  */
  public IReportFormatter setGraphDimensions(GraphDimensions gd);
}