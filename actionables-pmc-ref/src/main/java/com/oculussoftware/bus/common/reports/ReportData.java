package com.oculussoftware.bus.common.reports;

import com.oculussoftware.api.busi.common.reports.*;

/**
* Filename:    ReportData.java
* Date:        
* Description: .
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ReportData implements IReportData
{
  /*
  * Change Activity
  *
  * Issue number  	Programmer    	Date      	Description
  */
  private String _labels;
  private String _yvalues;
  private String _xvalues;
  private String _names;
  
  /**
  * package level
  */
  ReportData() {}
  
  public IReportData setLegendLabels(String labels)
  { _labels = labels; return this; }
  
  public IReportData setYValues(String yValues)
  { _yvalues = yValues; return this; }
  
  public IReportData setXValues(String xValues)
  { _xvalues = xValues; return this; }
  
  public IReportData setNames(String names)
  { _names = names; return this; }
  
  
  
  public String getLegendLabels()
  { return _labels; }
  
  public String getYValues()
  { return _yvalues; }
  
  public String getXValues()
  { return _xvalues; }
  
  public String getNames()
  { return _names; }
  
  
  
  
}
