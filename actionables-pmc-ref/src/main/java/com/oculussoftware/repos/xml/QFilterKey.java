package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QFilterKey.java
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

public class QFilterKey implements IQFilterKey
{
	//----------------------------------------------------------------------
	// Private class vars
	//----------------------------------------------------------------------
	
	//----------------------------------------------------------------------
	// Private instance vars
	//----------------------------------------------------------------------
	private IQFilterExprKey _expr = null;
	

  //----------------------------------------------------------------------
  // Constructor(s)
  //----------------------------------------------------------------------
  public QFilterKey()
  {
  }


  public QFilterKey(Node nd)
  throws OculusException
  {
    NodeList nl = nd.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++)
    {
      Node newNd = nl.item(i);
      if (newNd.getNodeName().equals("IQFilterExprKey"))
      {
        _expr = new QFilterExprKey(newNd);
      }
    }
  }


  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  public void setExpr(IQFilterExprKey expr){ _expr = expr; }


  public IQFilterExprKey getExpr(){ return _expr; }


  public Element toXML(Document doc)
  throws OculusException
  {
    if (_expr!=null)
    {
      Element ndIQ = doc.createElement("IQFilterKey");    
      ndIQ.appendChild(_expr.toXML(doc));
      return ndIQ;
    }
    else
    {
      return null;
    }
  }
}
