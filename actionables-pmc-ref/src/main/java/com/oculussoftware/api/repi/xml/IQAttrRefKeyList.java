package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQAttrRefKeyList.java $
* Description: The XML object used to persist a List of IQAttrRefs.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning a List of IQAttrRefs 
* persisted in a Custom MRD Report, or a Custom Report.
*
* @author Zain Nemazie
*/
public interface IQAttrRefKeyList extends IXMLable
{
  /**
  * This method adds an IQAttrRefKey to the List.
  * @param ak The IQAttrRefKey.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void addAttr(IQAttrRefKey ak) throws OculusException;

  /**
  * This method clears the List.
  */
  public void clear();

  /**
  * This method returns true iff the list has a next element (IQAttrRefKey).
  * @return true iff there is another element in the list (iterator).
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean hasMoreAttrRefs() throws OculusException;

  /**
  * This method returns the next IQAttrRefKey in the List.
  * @return The next IQAttrRefKey in the List (iterator).
  */
  public IQAttrRefKey nextAttr();

  /**
  * This method removes the given IQAttrRefKey from the List.
  * @param ak The IQAttrRefKey to be removed.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void removeAttr(IQAttrRefKey ak) throws OculusException;

  /**
  * This method resets the List iterator.
  */
  public void reset();
  
  /**
  * This method returns the size of the List.
  * @return The size of the List.
  */
  public int size();
}
