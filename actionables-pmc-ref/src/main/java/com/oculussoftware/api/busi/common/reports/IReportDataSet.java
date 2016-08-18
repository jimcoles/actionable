package com.oculussoftware.api.busi.common.reports;

/*
* $Workfile: IReportDataSet.java $
* Description: Wraps up all of the data for a graph or chart.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Wraps up and maintains the data necessary to generate a chart 
* or graph for a Report.
*
* @author Egan Royal
*/
public interface IReportDataSet
{
  /**
  * This accessor method returns the number of IReportData elements in the 
  * set.
  * @return The size of the set.  
  */
  public int size();
  
  /**
  * This method adds and returns an empty instance of IReportData.
  * @return An empty instance of IReportData. 
  */
  public IReportData addReportData();
  
  /**
  * This method returns the instance of IReportData at the given index.
  * @param idx The index of the element.
  * @return The ReportData at the given index. 
  */
  public IReportData getReportData(int idx);
  
  /**
  * This mutator method sets the X-axis Title of the chart or graph.
  * @param title The Title of the X-axis.
  * @return this  
  */
  public IReportDataSet setXAxisTitle(String title);
  
  /**
  * This mutator method sets the Y-axis Title of the chart or graph.
  * @param title The Title of the Y-axis.
  * @return this  
  */
  public IReportDataSet setYAxisTitle(String title);
  
  /**
  * This mutator method sets the Title of the chart or graph.
  * @param title The Title of the graph or chart.
  * @return this  
  */
  public IReportDataSet setTitle(String title);
  
  /**
  * This accessor method returns the X-axis Title of the chart or graph.
  * @return The X-axis Title of the chart or graph.  
  */
  public String getXAxisTitle();
  
  /**
  * This accessor method returns the Y-axis Title of the chart or graph.
  * @return The Y-axis Title of the chart or graph.  
  */
  public String getYAxisTitle();
  
  /**
  * This accessor method returns the Title of the chart or graph.
  * @return The Title of the chart or graph.  
  */
  public String getTitle();
  
}
