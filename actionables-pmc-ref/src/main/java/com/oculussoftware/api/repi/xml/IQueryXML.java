package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;
import java.sql.Timestamp;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.text.ParseException;


/*
* $Workfile: IQueryXML.java $
* Description: The XML object used to persist an IQuery.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist an IQuery.
*
* @author Zain Nemazie
*/
public interface IQueryXML extends IXMLTree
{
  /**
  * This method returns the IQSelectKey that can be translated into
  * Display Attributes for an IQuery.
  * @return The IQSelectKey.
  */
  public IQSelectKey getSelect( );

  /**
  * This method take a long ID of an IXClass and sets it as the Target Class
  * of the IQueryXML object.
  * @param l the long ID of the Target IXClass.
  */
  public void setTargetClass(long l);

  /**
  * This method returns the long ID of the Target IXClass for this IQueryXML object.
  * return The long ID of the Target IXClass.
  */
  public long  getTargetClass( );

  /**
  * This method returns the IQFilterKey object for this IQueryXML object.
  * @return The IQFilterKey.
  */
  public IQFilterKey getFilter( );

  /**
  * This method returns the IQSortKey for this IQueryXML object.
  * @return The IQSortKey.
  */
  public IQSortKey   getSort( );

  /**
  * This method returns a List of Long IDs of States.
  * @return The list of Long State IDs.
  */
  public List getStates();

  /**
  * This method sets the From Date of the IQueryXML object.
  * @param date The From Date.
  */
  public void setFromDate(Timestamp date);

  /**
  * This method returns the From Date of the IQueryXML object.
  * @return The From Date.
  */
  public Timestamp getFromDate();

  /**
  * This method sets the To Date of the IQueryXML object.
  * @param date The To Date.
  */
  public void setToDate(Timestamp date);

  /**
  * This method returns the To Date of the IQueryXML object.
  * @return The To Date.
  */
  public Timestamp getToDate();

  /**
  * This method takes a long ID and adds it to the List of State IDs.
  * @param l The long State ID.
  */
  public void setState(long l);
}
