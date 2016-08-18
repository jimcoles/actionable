package com.oculussoftware.repos.xml;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.sysi.OculusException;
/**
 * Insert the type's description here.
 * Creation date: (8/11/00 2:30:07 PM)
 * @author: 
 */
public class SPageFooter extends SGenericElement 
{

  /**
   * SPageFooter constructor comment.
   */
  public SPageFooter() 
  {
    super();
  }

  /**
   * SPageFooter constructor comment.
   */
  public SPageFooter(Node nd) 
  {
    super();
  }

  /**
   * toXML method comment. 
   */
  public Element toXML(Document doc) throws OculusException
  {
    Element ndIQ = doc.createElement("ISPageFooter");    
    ndIQ.setAttribute("Align", _align.getStringValue());
    ndIQ.setAttribute("VAlign", _valign.getStringValue());
    return ndIQ;
  }
}
