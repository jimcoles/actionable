package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;
/*
* $Workfile: IQSelectKey.java $
* Description: The XML object used to persist the Display Attributes for
* an IQuery.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The XML object used to persist the Display Attributes for
* an IQuery.
*
* @author Zain Nemazie
*/
public interface IQSelectKey extends IXMLable
{
  /**
  * This method returns a List of IQAttrRefKeys and IQObjectKeys that are the persisted
  * values of the Display Attributes for an IQuery object.
  * @return The List of IQAttrRefKeys.
  */
  public List getAttrs();

  /**
  * This method takes an IQAttrRefKey and adds it to the List.
  * @param An IQAttrRefKey.
  */
  public void addAttr(IQAttrRefKey attr);

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

  /**
  * This method takes an IQAttrRefKey and an alias and adds it to the List.
  * @param An IQAttrRefKey.
  * @param An alias.
  */
  public void addAttr(IQAttrRefKey attr, String alias);

  /**
  * This method takes an IQObjectKey and an alias and adds it to the List.
  * @param An IQObjectKey.
  * @param An alias.
  */
  public void addLiteral(IQObjectKey value, String alias);

  /**
  * This method iterates through the List and returns a List
  * of only IQAttrRefKeys.
  * @return A List of only IQAttrRefKeys.
  */
  public List getDisplayAttrs();

  /**
  * This method iterates through the List and returns a List
  * of only objects that are not instances of IQAttrRefKey.
  * @return A List of only objects that are not instances of IQAttrRefKey.
  */
  public List getLiterals();
}
