package com.oculussoftware.repos.xml;

import java.util.*;
import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QSortKey.java
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

public class QSortKey implements IQSortKey
{

	private List _items = new Vector();
	
	
  public QSortKey()
  {
  }

  public QSortKey(Node nd)
  throws OculusException
  {
    NodeList nl = nd.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++)
    {
      Node newNd = nl.item(i);
      if (newNd.getNodeName().equals("IQSortItemKey"))
      {
        addSortItem(new QSortItemKey(newNd));
      }
      
    }
  }

  public IQSortItemKey addSortItem(IQAttrRefKey attr, SortDir dir)
  {
    IQSortItemKey item =  new QSortItemKey(attr, dir);
    _items.add(item);
    return item;
  }

  public void addSortItem(IQSortItemKey iq)
  {
    _items.add(iq);
  }

  public List getSortItems()
  {
    return _items;
  }

  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQSortKey");    
    ListIterator it = _items.listIterator();
    while (it.hasNext())
    {
      IQSortItemKey refkey = (IQSortItemKey) it.next();
      ndIQ.appendChild(refkey.toXML(doc));
    }
    return ndIQ;
  }

  /**
   * clear method comment.
   */
  public void clear() 
  {
    _items.clear();  
  }
}
