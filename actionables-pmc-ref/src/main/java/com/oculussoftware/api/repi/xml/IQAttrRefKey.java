package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: IQAttrRefKey.java $
* Description: The XML object used to persist an IQAttrRef.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning an IQAttrRef 
* persisted in a Custom MRD Report, or a Custom Report.
*
* @author Zain Nemazie
*/
public interface IQAttrRefKey extends IXMLable
{
  /**
  * This method returns the long ID of the persisted IXClassAttr for the IQAttrRef.
  * @return The long ID of the persisted IXClassAttr for the IQAttrRef.
  */
	public long getAttr();

  /**
  * This method returns an array of longs that are the IDs of the persisted
  * IXAssocs for the IQAttrRef.
  * @return The long ID of the persisted IQAttrRef.
  */
	public long[] getAssocs();

  /**
  * This method XMLizes and returns this element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;  
  

}