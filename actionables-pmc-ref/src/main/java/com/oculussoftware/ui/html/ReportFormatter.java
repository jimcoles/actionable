package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;
import com.oculussoftware.api.busi.common.reports.*;

/**
* Filename:    ReportFormatter.java
* Date:        06.29.00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class ReportFormatter implements IReportFormatter
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  private ReportKind       _rk;
  private ReportFormatKind _rfk = ReportFormatKind.TEXT_ONLY;
  private GraphKind        _gk  = GraphKind.VERTICAL_BAR;
  private GraphColor       _gc  = GraphColor.COLOR;
  private GraphSize        _gs  = GraphSize.MEDIUM;
  private GraphDimensions  _gd  = GraphDimensions.THREE_D;
  
  public ReportFormatter(ReportKind rk) 
  { _rk = rk; }
  
  public ReportKind getReportKind()
  { return _rk; }
  
  public ReportFormatKind getReportFormatKind()
  { return _rfk; }
  
  public GraphKind getGraphKind()
  { return _gk; }
  
  public GraphColor getGraphColor()
  { return _gc; }
  
  public GraphSize getGraphSize()
  { return _gs; }
  
  public GraphDimensions getGraphDimensions()
  { return _gd; }
  
  public IReportFormatter setReportFormatKind(ReportFormatKind rfk)
  { _rfk = rfk; return this; }
  
  public IReportFormatter setGraphKind(GraphKind gk)
  { _gk = gk; return this; }
  
  public IReportFormatter setGraphColor(GraphColor gc)
  { _gc = gc; return this; }
  
  public IReportFormatter setGraphSize(GraphSize gs)
  { _gs = gs; return this; }
  
  public IReportFormatter setGraphDimensions(GraphDimensions gd)
  { _gd = gd; return this; }

}