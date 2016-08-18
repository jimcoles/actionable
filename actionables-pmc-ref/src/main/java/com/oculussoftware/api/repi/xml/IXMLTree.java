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
* $Workfile: IXMLTree.java $
* Description: The methods necessary to XMLize an object.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The methods necessary to XMLize an object.
*
* @author Zain Nemazie
*/
public interface IXMLTree
{
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML String.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * @exception ParserConfigurationException 
  */
  public String toXML()
    throws ParserConfigurationException,OculusException;

  /**
  * This method takes an InputStream and populates the XML hierarchy.
  * @param is The InputStream.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * @exception ParserConfigurationException 
  * @exception java.io.IOException 
  * @exception SAXException 
  * @exception ParseException
  */
  public void parseXML(InputStream is)
    throws ParserConfigurationException, IOException, SAXException, OculusException, ParseException;
}
