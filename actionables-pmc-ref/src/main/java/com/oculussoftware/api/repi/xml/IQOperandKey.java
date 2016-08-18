package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQOperandKey.java $
* Description: The XML object used to persist an operand.  I do not think
* that this class is still being used.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning an operand 
* persisted in a Custom MRD Report, or a Custom Report.  I
* do not think that this class is still being used.
*
* @author Zain Nemazie
*/
public interface IQOperandKey extends IXMLable
{
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;  
}