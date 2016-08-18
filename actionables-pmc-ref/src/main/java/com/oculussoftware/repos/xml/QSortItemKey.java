package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QSortItemKey.java
* Date:        7/18/00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class QSortItemKey implements IQSortItemKey
{
  private SortDir _dir = null;
  private IQAttrRefKey _attr = null;
  
  public QSortItemKey(IQAttrRefKey attr, SortDir dir)
  {
    _attr = attr;
    _dir = dir;
  }
  
  public QSortItemKey(Node nd) throws OculusException
  {
    _dir = SortDir.getXMLInstance(nd.getAttributes().getNamedItem("SortDir").getNodeValue());
    NodeList nl = nd.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++)
    {
      Node newNd = nl.item(i);
      if (newNd.getNodeName().equals("IQAttrRefKey"))
      {
        _attr = new QAttrRefKey(newNd);
      }
    }
  }
  

	public SortDir getDir(){ return _dir; }

	public IQAttrRefKey getAttrRef(){ return _attr; }
  
  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQSortItemKey");    
    ndIQ.appendChild(_attr.toXML(doc));
    ndIQ.setAttribute("SortDir", SortDir.getStringValue(_dir));
    return ndIQ;
  }
}