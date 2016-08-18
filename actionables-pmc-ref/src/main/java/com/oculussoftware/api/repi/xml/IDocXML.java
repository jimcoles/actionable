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
* $Workfile: IDocXML.java $
* Description: The XML object used for the Custom MRD Reports.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning the Display Attributes 
* persisted in a Custom MRD Report.
*
* @author Zain Nemazie
*/
public interface IDocXML extends IXMLTree
{
  /**
  * This method returns an IQAttrRefKeyList of Category Display Attributes.
  * @return An IQAttrRefKeyList of Category Display Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRefKeyList getCategoryAttrList() throws OculusException;

  /**
  * This method returns an IQAttrRefKeyList of FeatureCategoryLink Display Attributes.
  * @return An IQAttrRefKeyList of FeatureCategoryLink Display Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRefKeyList getFeatureAttrList() throws OculusException;

  /**
  * This method returns an IQAttrRefKeyList of Product Display Attributes.
  * @return An IQAttrRefKeyList of Product Display Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRefKeyList getProductAttrList() throws OculusException;

  /**
  * This method returns an IQAttrRefKeyList of ProductVersion Display Attributes.
  * @return An IQAttrRefKeyList of ProductVersion Display Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IQAttrRefKeyList getVersionAttrList() throws OculusException;
}
