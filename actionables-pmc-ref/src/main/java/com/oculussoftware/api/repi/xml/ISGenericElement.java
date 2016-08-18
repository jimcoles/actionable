package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.ui.html.*;
/*
* $Workfile: ISGenericElement.java $
* Description: The XML object used to persist an IQuery.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist a Generic UI Element.
*
* @author Zain Nemazie
*/
public interface ISGenericElement extends IXMLable
{
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;

  /**
  * This method returns the AlignKind
  * @return The AlignKind.
  */
  public AlignKind getAlign();

  /**
  * This method returns the VAlignKind
  * @return The VAlignKind.
  */
  public VAlignKind getVAlign();

  /**
  * This method sets the AlignKind.
  * @param ak The AlignKind.
  */
  public ISGenericElement setAlign(AlignKind ak);

  /**
  * This method sets the VAlignKind.
  * @param vak The VAlignKind.
  */
  public ISGenericElement setVAlign(VAlignKind vak);
}
