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
public abstract class SGenericElement implements ISGenericElement 
{
	AlignKind _align = null;
	VAlignKind _valign = null;

  /**
   * SGenericElement constructor comment.
   */
  public SGenericElement() 
  {
    super();
  }

  /**
   * SGenericElement constructor comment.
   */
  public SGenericElement(Node nd) 
  {
    super();
    Node align = nd.getAttributes().getNamedItem("Align");
    if (align != null)
    {
      
      
    }
    
    //construct from nd
  }

  /**
   * getAlign method comment.
   */
  public AlignKind getAlign() 
  {
    return _align;
  }

  /**
   * getVAlign method comment.
   */
  public VAlignKind getVAlign() 
  {
    return _valign;
  }

  /**
   * setAlign method comment.
   */
  public ISGenericElement setAlign(AlignKind ak) 
  {
    _align = ak;
    return this;
  }

  /**
   * setVAlign method comment.
   */
  public ISGenericElement setVAlign(VAlignKind ak) 
  {
    _valign = ak;
    return this;
  }

  /**
   * toXML method comment. 
   */
  abstract public Element toXML(Document doc) throws OculusException;
}
