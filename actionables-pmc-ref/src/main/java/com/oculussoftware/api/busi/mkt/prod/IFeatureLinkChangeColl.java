package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of feature link changes.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureLinkChangeColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IFeatureLinkChangeColl extends IBusinessObjectColl
{
  /** Returns the next change in the collection
  *
  * @return the next change in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange nextChange()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more changes.
  *
  * @return true if there are more changes, false otherwise
  */
  public boolean hasMoreChanges();
  
}