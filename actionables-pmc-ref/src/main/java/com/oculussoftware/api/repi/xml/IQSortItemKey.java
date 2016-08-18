package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQSortItemKey.java $
* Description: The XML object used to persist the Sorting Attributes for
* an IQuery.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist the Sorting Attributes for
* an IQuery.
*
* @author Zain Nemazie
*/
public interface IQSortItemKey extends IXMLable
{
  /**
  * This method returns the sort direction for this sort item.
  * @return The SortDir.
  */
	public SortDir getDir();

  /**
  * This method returns the IQAttrRefKey on which the sorting will take
  * place.
  * @return The IQAttrRefKey
  */
	public IQAttrRefKey getAttrRef();

  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;  
}