package com.oculussoftware.api.busi.common.reports;

/*
* $Workfile: IReportData.java $
* Description: An element in a ReportDataSet.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An instance of IReportData is an element in a ReportDataSet.  The
* interface is responsible for maintaining the data values for a chart
* or graph in a report.
*
* @author Egan Royal
*/
public interface IReportData
{
  /**
  * This mutator method sets the Legend Labels.
  * @param labels A comma seperated list of Legend Labels.
  * @return this  
  */
  public IReportData setLegendLabels(String labels);
  
  /**
  * This mutator method sets the Y-values of the graph or chart.
  * @param yValues A comma seperated list of the Y-values.
  * @return this  
  */
  public IReportData setYValues(String yValues);
  
  /**
  * This mutator method sets the X-values of the graph or chart.
  * @param xValues A comma seperated list of the X-values.
  * @return this  
  */
  public IReportData setXValues(String xValues);
  
  /**
  * This mutator method sets the names or labels to be printed on the
  * graph or chart.
  * @param names A comma seperated list of names.
  * @return this  
  */
  public IReportData setNames(String names);
  
  /**
  * This accessor method returns the comma seperated list of Legend Labels.
  * @return A comma seperated list of Legend Labels  
  */
  public String getLegendLabels();
  
  /**
  * This accessor method returns the comma seperated list of Y-values.
  * @return A comma seperated list of Y-values.  
  */
  public String getYValues();
  
  /**
  * This accessor method returns the comma seperated list of X-values.
  * @return A comma seperated list of X-values.  
  */
  public String getXValues();
  
  /**
  * This accessor method returns the comma seperated list of names.
  * @return A comma seperated list of names.  
  */
  public String getNames();
}
