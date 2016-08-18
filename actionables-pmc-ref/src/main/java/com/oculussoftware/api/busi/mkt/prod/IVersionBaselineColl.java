package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of version baselines.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IVersionBaselineColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IVersionBaselineColl extends IBusinessObjectColl
{
  /** Returns the next version baseline in the collection
  *
  * @return the next version baseline in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IVersionBaseline nextVersionBaseline()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more version baselines.
  *
  * @return true if there are more version baselines, false otherwise
  */
  public boolean hasMoreVersionBaselines();
  
}