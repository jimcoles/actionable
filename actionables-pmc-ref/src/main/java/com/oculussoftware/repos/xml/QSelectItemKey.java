package com.oculussoftware.repos.xml;

import java.util.*;
import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.sysi.OculusException;

/** Implements IQSelectItem for the PMC query subsystem. */
public class QSelectItemKey implements IQSelectItemKey
{
	//-----------------------------------------------------------------
	// Public instance methods
	//-----------------------------------------------------------------
	private IXMLable _attr = null;
	private String _alias = null;

  //-----------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------

  public Object getAttr(){ return _attr; }

  public String getAlias(){ return _alias; }

  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQSelectItemKey");    

    if (_alias != null) 
      ndIQ.setAttribute("alias", _alias);


     ndIQ.appendChild(_attr.toXML(doc));


    return ndIQ;
  }

  //----------------------------------------------------------------------
  // Private Methods
  //----------------------------------------------------------------------
  
  private Object processOperand(Node child)
  throws OculusException
  {
    Object obj = null;
    NodeList nl = child.getChildNodes();
    for (int i=0; i < nl.getLength(); i++)
    {
      Node curr = nl.item(i);
      if (curr.getNodeName().equals("IQAttrRefKey"))
      {
        obj = new QAttrRefKey(curr);
      }
      else if (curr.getNodeName().equals("IQObjectKey"))
      {
        obj = new QObjectKey(curr);
      }      
    }
    return obj;
  }

  //-----------------------------------------------------------------
  // Constructor(s)
  //-----------------------------------------------------------------
  QSelectItemKey(IQAttrRefKey attr, String alias)
  {
    _attr = attr;
    _alias = alias;
  }

  QSelectItemKey(IQObjectKey attr, String alias)
  {
    _attr = attr;
    _alias = alias;
  }

  public QSelectItemKey(Node nd) throws OculusException
  {
    Node ndAlias = nd.getAttributes().getNamedItem("alias");
    if (ndAlias != null)
      _alias = ndAlias.getNodeValue();
    NodeList nl = nd.getChildNodes();
    for (int i = 0; i < nl.getLength(); i++)
    {
      Node newNd = nl.item(i);
      _attr = (IXMLable) processOperand(nd);
    }
  }
}
