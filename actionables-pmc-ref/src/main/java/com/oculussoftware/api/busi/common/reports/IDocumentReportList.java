package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.repi.*;

/*
* $Workfile: IDocumentReportList.java $
* Description: Defines the methods needed for an ordered collection
* of IDocumentReports.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An extension of IRList, defines the convenience methods 
* necessary for iterating through an unordered collection of
* IDocumentReports.
*
* @author Egan Royal
*/
public interface IDocumentReportList extends IRList
{
  /**
  * Calls the next() method of the superclass, casts 
  * it to an IDocumentReport, and returns it.
  * @return The next IDocumentReport in the collection.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport nextDocumentReport() throws OculusException;

  /**
  * Delegates to the superclass hasNext() method.
  * @return true - iff there is a next element.  
  */
  public boolean hasMoreDocumentReports();
}