package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.sysi.OculusException;
import org.w3c.dom.*;
/*
* $Workfile: IStateAttr.java $
* Description: The XML object used to persist the ID of an IRState.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist the ID of an IRState.
*
* @author Zain Nemazie
*/
public interface IStateAttr extends IXMLable
{
  /**
  * This method returns the long ID of the IRState.
  * @return The long ID of the IRState.
  */
  public long getObjectID();
  
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;  
  
}

