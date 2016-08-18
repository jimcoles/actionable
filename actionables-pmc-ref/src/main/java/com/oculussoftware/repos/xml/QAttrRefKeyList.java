package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.util.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import java.util.*;
/**
 * Insert the type's description here.
 * Creation date: (8/10/00 2:07:03 PM)
 * @author: 
 */
public class QAttrRefKeyList implements IQAttrRefKeyList, IXMLable
{
	private List _lst = new Vector();
	private Iterator _ids = null;
	
  /**
   * QAttrRefKeyList constructor comment.
   */
  public QAttrRefKeyList() 
  {
    super();
    reset();
  }

  /**
   * QAttrRefKeyList constructor comment.
   */
  public QAttrRefKeyList(Node nd) throws OculusException
  {
    super();
    NodeList nl = nd.getChildNodes();
    IQAttrRefKey qak;
    for (int i=0; i < nl.getLength(); i++)
    {
      Node curr = nl.item(i);
      if (curr.getNodeName().equals("IQAttrRefList"))
      {
        //get list of children
        NodeList refs = curr.getChildNodes();
        for (int j=0; j < refs.getLength(); j++)
        {
          Node refkey = refs.item(j);
          qak = new QAttrRefKey(refkey);
          addAttr(qak);
        }
      }
    }    
    reset();
  }

  /**
   * addAttr method comment.
   */
  public void addAttr(IQAttrRefKey ak) throws com.oculussoftware.api.sysi.OculusException 
  {
    _lst.add(ak);
  }

  /**
   * clear method comment.
   */
  public void clear() 
  {
    _lst.clear();
  }

  public int size()
  {
    return _lst.size();
  }

  /**
   * hasMoreAttrRefs method comment.
   */
  public boolean hasMoreAttrRefs() throws OculusException 
  {
    return _ids.hasNext();
  }

  /**
   * nextAttr method comment.
   */
  public IQAttrRefKey nextAttr() 
  {
    return (IQAttrRefKey) _ids.next();
  }

  /**
   * removeAttr method comment.
   */
  public void removeAttr(IQAttrRefKey ak) throws com.oculussoftware.api.sysi.OculusException 
  {
    _lst.remove(ak);
  }

  /**
   * reset method comment.
   */
  public void reset() 
  {
    _ids = _lst.iterator();
  }

  /**
   * toXML method comment.
   */
  public org.w3c.dom.Element toXML(org.w3c.dom.Document doc) throws com.oculussoftware.api.sysi.OculusException 
  {
    Element ndIQ = doc.createElement("IQAttrRefList");    
    reset();
    while (hasMoreAttrRefs())
    {
      ndIQ.appendChild(nextAttr().toXML(doc));
    }
    return ndIQ;
  }
}
