package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of URL attachments.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IHyperLinkColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IHyperLinkColl extends IBusinessObjectColl
{
  /** Returns the next URL attachment in the collection
  *
  * @return the next URL attachment in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLink nextHyperLink()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more URL attachments.
  *
  * @return true if there are more URL attachments, false otherwise
  */
  public boolean hasMoreHyperLinks();
  
}