package com.oculussoftware.api.repi.xml;

import java.util.*;
import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQSortKey.java $
* Description: The XML object used to persist the List of Sorting Attributes for
* an IQuery.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist the List of Sorting Attributes for
* an IQuery.
*
* @author Zain Nemazie
*/
public interface IQSortKey extends IXMLable
{
  /**
  * This method takes an IQAttrRefKey and a SortDir and adds them to the 
  * List of IQSortItemKeys.
  * @param attr The IQAttrRefKey.
  * @param dir The SortDir.
  */
  public IQSortItemKey addSortItem(IQAttrRefKey attr, SortDir dir);

  /**
  * This method returns the List of IQSortItemKeys.
  * @return The List of IQSortItemKeys.
  */
  public List getSortItems();

  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;

  /**
  * This method removes all of the elements in the List.
  */
  public void clear();
}
