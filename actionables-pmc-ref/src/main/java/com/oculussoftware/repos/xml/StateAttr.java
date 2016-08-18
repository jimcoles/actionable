package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;


public class StateAttr implements IStateAttr
{
  private long lObjectID;
  
  public StateAttr(long lObjectID)
  {
    this.lObjectID = lObjectID;
  }
  
  public long getObjectID()
  {
    return lObjectID;
  }
  
  public Element toXML(Document doc)
  {
    Element ndElem = doc.createElement("StateAttr");
    ndElem.appendChild(doc.createTextNode(getObjectID()+""));
    return ndElem;
  }
}

