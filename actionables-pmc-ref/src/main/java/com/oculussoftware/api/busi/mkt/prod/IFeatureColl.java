package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of features.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IFeatureColl extends IBusinessObjectColl
{
  /** Returns the next feature in the collection
  *
  * @return the next feature in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature nextFeature()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more features.
  *
  * @return true if there are more features, false otherwise
  */
  public boolean hasMoreFeatures();
  
}