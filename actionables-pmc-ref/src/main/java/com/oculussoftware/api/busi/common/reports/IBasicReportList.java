package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.repi.*;

/*
* $Workfile: IBasicReportList.java $
* Description: Defines the methods needed for an ordered collection
* of IBasicReports.
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
* IBasicReports.
*
* @author Egan Royal
*/
public interface IBasicReportList extends IRList
{
  /**
  * Calls the next() method of the superclass, casts 
  * it to an IBasicReport, and returns it.
  * @return The next IBasicReport in the collection.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IBasicReport nextBasicReport() throws OculusException;

  /**
  * Delegates to the superclass hasNext() method.
  * @return true - iff there is a next element.  
  */
  public boolean hasMoreBasicReports();
}