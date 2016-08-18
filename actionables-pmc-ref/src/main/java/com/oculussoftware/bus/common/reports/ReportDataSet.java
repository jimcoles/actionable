package com.oculussoftware.bus.common.reports;

import com.oculussoftware.api.busi.common.reports.*;
import java.util.*;
/**
* Filename:    ReportDataSet.java
* Date:        
* Description: .
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ReportDataSet implements IReportDataSet
{
  /*
  * Change Activity
  *
  * Issue number  	Programmer    	Date      	Description
  */
  private Vector _v;
  private String _xaxistitle;
  private String _yaxistitle;
  private String _title;
  
  public ReportDataSet()
  {
    _v = new Vector();
  }
  
  
  public int size()
  { return _v.size(); }
  
  public IReportData addReportData()
  {
    IReportData rd = new ReportData();
    _v.addElement(rd);
    return rd;
  }
  
  public IReportData getReportData(int idx)
  {
    return (IReportData)_v.elementAt(idx);
  }
    
    
  public IReportDataSet setXAxisTitle(String title)
  { _xaxistitle = title; return this; }
  public IReportDataSet setYAxisTitle(String title)
  { _yaxistitle = title; return this; }
  public IReportDataSet setTitle(String title)
  { _title = title; return this; }
  
  public String getXAxisTitle()
  { return _xaxistitle; }
  public String getYAxisTitle()
  { return _yaxistitle; }
  public String getTitle()
  { return _title; }
  
}
