package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.util.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QAttrRefKey.java
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

public class QAttrRefKey implements IQAttrRefKey
{
	private long _attr = 0;
	private long[] _assocs;
	
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------

  
  public QAttrRefKey(long[] lAssocs,  long  attr)
  {
    _assocs = lAssocs;
    _attr = attr;

  }

  public QAttrRefKey(Node nd)
  {
    NamedNodeMap attrList = nd.getAttributes();
    Node attrNode;
    attrNode = attrList.getNamedItem("IXClassAttr");
    _attr = Long.parseLong(attrNode.getNodeValue());
    attrNode = attrList.getNamedItem("IXAssocs");
    _assocs = getAssocsFromCSV(attrNode.getNodeValue());
    
  }

  //-------------------------------------------------------------------
  // Public instance methods
  //-------------------------------------------------------------------
  public long getAttr()
  { 
    return _attr;
  }

  public long[] getAssocs()
  { 
    return _assocs;
  }

  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQAttrRefKey");    
    ndIQ.setAttribute("IXClassAttr", getAttr()+"");
    ndIQ.setAttribute("IXAssocs", getArray2CSV());
    return ndIQ;
  }

  private String getArray2CSV()
  {
    if ((_assocs == null) || (_assocs.length == 0))
      return ""; 
    StringBuffer buffer = new StringBuffer();
    for (int i=0; i < _assocs.length - 1 ; i++)
    {
      buffer = buffer.append( _assocs[i]+",");
    }
    buffer.append( _assocs[_assocs.length - 1]);
    
    return buffer.toString();
  }

  public long[] getAssocsFromCSV(String csv)
  {
    if (csv==null || csv.length()==0)
      return null;         
    else 
      return StringUtil.getLongArray(csv);       
  }
}
