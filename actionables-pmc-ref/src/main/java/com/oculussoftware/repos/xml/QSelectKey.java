package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;
/**
* Filename:    QSelectKey.java
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

public class QSelectKey implements IQSelectKey
{

	private List _items = new Vector();
	

  public QSelectKey()
  {
  }


  public QSelectKey(Node nd)
  {
    NodeList nl = nd.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++)
    {
      Node newNd = nl.item(i);
      //get child IQSelectItemKey then get
      newNd = newNd.getChildNodes().item(0);
      if (newNd.getNodeName().equals("IQAttrRefKey"))
      {
        addAttr(new QAttrRefKey(newNd));
      }
      
    }
  }


  public void addAttr(IQAttrRefKey attr)
  {
    _items.add(new QSelectItemKey(attr, null));
  }


  public void addAttr(IQAttrRefKey attr, String alias)
  {
    _items.add(new QSelectItemKey(attr, alias));
  }


  public void addLiteral(IQObjectKey value, String alias)
  {
    _items.add(new QSelectItemKey(value, alias));
  }


  /**
   * clear method comment.
   */
  public void clear() 
  {
    _items.clear();  
  }


  public List getAttrs()
  {
    return _items;
  }


  public List getDisplayAttrs()
  {
    Vector v = new Vector();
    ListIterator li =  _items.listIterator();
    while (li.hasNext())
    {
      IQSelectItemKey o = (IQSelectItemKey) li.next();
      if (o.getAttr() instanceof IQAttrRefKey)
      {
        v.addElement(o.getAttr());
      }  
    }
    return v;
  }


  public List getLiterals()
  {
    Vector v = new Vector();
    ListIterator li =  _items.listIterator();
    while (li.hasNext())
    {
      IQSelectItemKey o = (IQSelectItemKey) li.next();
      if (!(o.getAttr() instanceof IQAttrRefKey))
      {
        v.addElement(o);
      }  
    }
    return v;
  }


  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQSelectKey");    
    ListIterator it = _items.listIterator();
    while (it.hasNext())
    {
      IXMLable refkey = (IXMLable) it.next();
      ndIQ.appendChild(refkey.toXML(doc));
    }
    return ndIQ;
  }
}
