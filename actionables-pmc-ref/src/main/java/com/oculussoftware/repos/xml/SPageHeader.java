package com.oculussoftware.repos.xml;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.sysi.OculusException;
/**
 * Insert the type's description here.
 * Creation date: (8/11/00 11:45:29 AM)
 * @author: 
 */
public class SPageHeader extends SGenericElement
{

  public SPageHeader()
  {
    super();
  }

  public SPageHeader(Node nd)
  {
    super(nd);
  }

  /**
   * toXML method comment. 
   */
  public Element toXML(Document doc) throws OculusException
  {
    Element ndIQ = doc.createElement("ISPageHeader");    
    ndIQ.setAttribute("Align", _align.getStringValue());
    ndIQ.setAttribute("VAlign", _valign.getStringValue());
    return ndIQ;
  }
}
