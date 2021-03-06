package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of product versions.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IProductVersionColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IProductVersionColl extends IBusinessObjectColl
{
  /** Returns the next product version in the collection
  *
  * @return the next product version in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersion nextProductVersion()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more product versions.
  *
  * @return true if there are more product versions, false otherwise
  */
  public boolean hasMoreProductVersions();
  
}