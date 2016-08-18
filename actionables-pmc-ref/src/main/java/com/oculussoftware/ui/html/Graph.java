package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;

import java.net.URLEncoder;

import com.oculussoftware.api.busi.common.reports.*;


/****STYLEREPORT code****/
/*
import inetsoft.report.*;
import inetsoft.report.io.*;
import inetsoft.report.style.*;
import inetsoft.report.lens.*;
import inetsoft.report.lens.swing.*;
import javax.swing.*;
import javax.swing.table.*;

import java.io.*;
import java.awt.*;
*/
/****STYLEREPORT code(end)****/


/**
* Filename:    Graph.java
* Date:        09.12.00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Ismail Syed
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

/****To use KAWACHART use the KAWACHART code and comment out the code for STYLEREPORT
and to use STYLEREPORT use STYLEREPORT code and comment out the code for KAWACHART****/





/****KAWACHART code****/

public class Graph extends com.oculussoftware.ui.html.wrappers.Img implements IGraph
{
  private static final String READ_DIRECTORY = "/images";
  
  private static final String FONT = "Arial,9,0";
  
  private static final String COLORCYLE0 = "C42842";
  private static final String COLORCYLE1 = "53749A";
  private static final String COLORCYLE2 = "B69B2D";
  private static final String COLORCYLE3 = "61907F";
  private static final String COLORCYLE4 = "BD6D3F";
  private static final String COLORCYLE5 = "755A94";
  private static final String COLORCYLE6 = "F5B757";
  private static final String COLORCYLE7 = "2B9069";
  private static final String COLORCYLE8 = "EC6B76";
  private static final String COLORCYLE9 = "8C6636";
  
  private static final String GRAYSCALE0 = "CCCCCC";
  private static final String GRAYSCALE1 = "404040";
  private static final String GRAYSCALE2 = "A8A8A8";
  private static final String GRAYSCALE3 = "8C8C8C";
  private static final String GRAYSCALE4 = "D9D9D9";
  private static final String GRAYSCALE5 = "000000";
  private static final String GRAYSCALE6 = "A4A4A4";
  private static final String GRAYSCALE7 = "666666";
  private static final String GRAYSCALE8 = "BFBFBF";
  private static final String GRAYSCALE9 = "808080";
  
  
  private static final String[] COLORS = 	{COLORCYLE0,COLORCYLE1,COLORCYLE2,COLORCYLE3,COLORCYLE4,COLORCYLE5,COLORCYLE6,COLORCYLE7,COLORCYLE8,COLORCYLE9};
  private static final String[] GRAYSCALE = {GRAYSCALE0,GRAYSCALE1,GRAYSCALE2,GRAYSCALE3,GRAYSCALE4,GRAYSCALE5,GRAYSCALE6,GRAYSCALE7,GRAYSCALE8,GRAYSCALE9};
  
  private IReportFormatter _rf;
  private IReportDataSet _rds;
  private String _srcurl;
  private String _title;
  private String _width;
  private String _height;
  private String _dim = "3D";
  private String _type = "indColumnApp";
  private int _numdatasets;
  
  
  
  
  public Graph(IReportFormatter rf, IReportDataSet rds)
  {
    super();
    _rf = rf;
    _rds = rds;
    buildSrcURL();
  }

  private IGraph buildSrcURL()
  {
    ReportKind rk = _rf.getReportKind();
    GraphDimensions gd = _rf.getGraphDimensions();
    GraphSize gs = _rf.getGraphSize();
    GraphKind gk = _rf.getGraphKind();
    GraphColor gc = _rf.getGraphColor();
  
    if(_rds.getTitle() != null)
      _title = _rds.getTitle();
    else  
      _title = URLEncoder.encode(rk.getName());
    
    _height = ""+gs.getHeight();
    setHeight(gs.getHeight());
    _width = ""+gs.getWidth();
    setWidth(gs.getWidth());
    
    if(gd.equals(GraphDimensions.TWO_D))
      _dim = "2D";
    
    if(gk.equals(GraphKind.HORIZONTAL_BAR))
      _type = "indBarApp";
    else if(gk.equals(GraphKind.PIE))
      _type = "pieApp";  

    _srcurl = "/servlet/javachart.servlet."+_type+"?byteStream=true&readDirectory="+READ_DIRECTORY+"&defaultFont="+FONT+"&titleFont="+FONT+"&titleString="+_title+"&height="+_height+"&width="+_width+"&"+_dim+"=1"+(gk.equals(GraphKind.PIE)?"&legendOn=1":"");
    
    if(_rds.getXAxisTitle() != null)
      _srcurl += "&xAxisTitle="+_rds.getXAxisTitle()+"&xAxisOptions=rotateTitle";
    
    if(_rds.getYAxisTitle() != null)
      _srcurl += "&yAxisTitle="+_rds.getYAxisTitle()+"&yAxisOptions=rotateTitle";  
    
    int j = 0;
    for(int i = 0; i < _rds.size(); i++)
    {
      IReportData rd = _rds.getReportData(i);
      if(rd.getYValues() != null)
        _srcurl += "&dataset"+i+"yValues="+URLEncoder.encode(rd.getYValues());
      if(rd.getXValues() != null)
        _srcurl += "&dataset"+i+"xValues="+URLEncoder.encode(rd.getXValues());
      //no more legends unless pie
      if(gk.equals(GraphKind.PIE) && rd.getLegendLabels() != null)
        _srcurl += "&dataset"+i+"Name="+URLEncoder.encode(rd.getLegendLabels());
      if(rd.getNames() != null)
        _srcurl += "&dataset"+i+"Labels="+URLEncoder.encode(rd.getNames()); 
        
      StringTokenizer st = new StringTokenizer(rd.getYValues(),",");
      int numbars = st.countTokens();
      if(numbars == 0)
      {
        st = new StringTokenizer(rd.getXValues(),",");
        numbars = st.countTokens();
      }//end if
      
      if(gc.equals(GraphColor.COLOR))
      {
        String strColor = COLORS[0];
        for(int k = 1; k < numbars; k++)
          strColor += ","+COLORS[k%COLORS.length];
        _srcurl += "&dataset"+i+"Colors="+strColor;  
      }//end if
      else
      {
        String strColor = GRAYSCALE[0];
        for(int k = 1; k < numbars; k++)
          strColor += ","+GRAYSCALE[k%GRAYSCALE.length];
        _srcurl += "&dataset"+i+"Colors="+strColor;
      }//end else
    }//end for
      
    setSrc(_srcurl);
    return this;
  }
  


}
/****KAWACHART code(end)****/






/****STYLEREPORT code****/
/****Image stored as chart1.jpg(and styleImage.htm) at JawaWebServer2.0/public_html/files ****/
/*public class Graph extends com.oculussoftware.ui.html.wrappers.Img implements IGraph 

{
  private IReportFormatter _rf;
  private IReportDataSet _rds;
  
 
  private static final Color[] COLORS = 
  {
  Color.red,
  Color.black, 
  Color.blue,
  Color.cyan,
  Color.darkGray,
  Color.gray,
  Color.green,
  Color.lightGray,
  Color.magenta,
  Color.orange,
  Color.pink,
  Color.yellow,
  };
  
  
  public Graph(IReportFormatter rf, IReportDataSet rds)throws Exception

  {
    super();
    _rf = rf;
    _rds = rds;
    buildSrcURL();
  }

  private IGraph buildSrcURL()	throws Exception
  
  {
    ReportKind rk = _rf.getReportKind();
    GraphDimensions gd = _rf.getGraphDimensions();
    GraphSize gs = _rf.getGraphSize();
    GraphKind gk = _rf.getGraphKind();
    GraphColor gc = _rf.getGraphColor();
  
    setHeight(gs.getHeight());
    setWidth(gs.getWidth());
    
	
    StyleSheet report = new StyleSheet();
	
	IReportData temprd;
	int size = 0;
	if ( _rds.getReportData(0) != null)
	{
		temprd = _rds.getReportData(0);
		StringTokenizer tempnames = new StringTokenizer(temprd.getYValues(),",");
		
    	while (tempnames.hasMoreTokens())
   		{
		    tempnames.nextToken();
			size++;
    	}
	}
	  
	
	DefaultChartLens chart = new DefaultChartLens(_rds.size(), size);
	 
    for(int i = 0; i < _rds.size(); i++)
    {
      IReportData rd = _rds.getReportData(i);
	  
      if (rd.getXValues() != null)
	  {
	  	StringTokenizer names = new StringTokenizer(rd.getXValues(),",");
		int j = 0;
      	while (names.hasMoreTokens())
      	{
        	String name = names.nextToken();
			chart.setData(i, j, new Double(Float.parseFloat(name)));
			j++;
      	}

	  }
      
	  if (rd.getYValues() != null)
	  {
        StringTokenizer names = new StringTokenizer(rd.getYValues(),",");
		int j = 0;
      	while (names.hasMoreTokens())
      	{
        	String name = names.nextToken();
			chart.setData(i, j, new Double(Float.parseFloat(name)));
			j++;
      	}
 
	  }
	  
	  if (rd.getLegendLabels() != null)
	  {
      	StringTokenizer names = new StringTokenizer(rd.getLegendLabels(),",");
		int j = 0;
		
      	while (names.hasMoreTokens())
      	{
        	Random rand = new Random();
			String name = names.nextToken();
			chart.setLabel(j, name);
			//chart.setColor(j, COLORS[j % COLORS.length]);
			j++;
      	}
	  }
     
	  if (rd.getNames() != null)
	  {
      	StringTokenizer names = new StringTokenizer(rd.getNames(),",");
		int j = 0;
      	while (names.hasMoreTokens())
      	{
        	String name = names.nextToken();
			chart.setLabel(j, name);
			//chart.setColor(j, COLORS[j % COLORS.length]);
			j++;
      	}
	  }
	 
    }
	
	AttributeChartLens achart = new AttributeChartLens(chart);
	
	if(gd.equals(GraphDimensions.TWO_D))
      achart.setStyle(StyleConstants.CHART_BAR);
	else if(gd.equals(GraphDimensions.THREE_D))  
	  achart.setStyle(StyleConstants.CHART_AREA);
    
    if(gk.equals(GraphKind.HORIZONTAL_BAR))
	  achart.setStyle(StyleConstants.CHART_BAR);
	else if(gk.equals(GraphKind.VERTICAL_BAR)) 
	  achart.setStyle(StyleConstants.CHART_INVERTED_BAR);
    else if(gk.equals(GraphKind.PIE))
	  achart.setStyle(StyleConstants.CHART_PIE);
	
	 if(_rds.getXAxisTitle() != null)
      achart.setXTitle(_rds.getXAxisTitle() + "" + size);
    
    if(_rds.getYAxisTitle() != null)
      achart.setYTitle(_rds.getYAxisTitle()); 
	  
	if(!gc.equals(GraphColor.COLOR))
      	achart.setBlackWhite(true);
		
	achart.setGridStyle(StyleConstants.NONE);   
	achart.setLegendPosition(StyleConstants.NONE);
	achart.setGap(8);
	
	report.addChart(achart, gs.getHeight(), gs.getWidth());
	
	setSrc("/files/chart1.jpg");
	
	FileOutputStream os = new FileOutputStream("./public_html/files/styleImage.htm");
	HTMLFormatter fmt = new HTMLFormatter(os, "./public_html/files");
	fmt.setJPEGPrefix("chart");
	Builder builder = new Builder(fmt);
	
	builder.write(report);
	if (os != null)
		os.close();
		
    return this;
  }
  
}*/
/****STYLEREPORT code(end)****/







