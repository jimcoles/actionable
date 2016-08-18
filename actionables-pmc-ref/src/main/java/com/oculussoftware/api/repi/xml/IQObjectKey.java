package com.oculussoftware.api.repi.xml;

import java.util.*;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQObjectKey.java $
* Description: The XML object used to persist a Primitive type.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning a Primitive type 
* persisted in a Custom MRD Report, or a Custom Report.
*
* @author Zain Nemazie
*/
public interface IQObjectKey extends IXMLable
{
  /**
  * This method returns the Primitive type for the object stored.
  * @return The Primitive for the object stored.
  */
  public Primitive getPrimitive();
  
  /**
  * This method returns the value of the object stored.  Since a valid primitive 
  * is an enumeration, the method may return an array.
  */
  public Object getValues();
  
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;    
}