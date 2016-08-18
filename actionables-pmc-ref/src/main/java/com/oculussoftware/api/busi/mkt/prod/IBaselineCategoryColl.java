package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of baseline categories.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: IBaselineCategoryColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IBaselineCategoryColl extends ICategoryColl
{
  /** Returns the next baseline category in the collection
  *
  * @return the next baseline category in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBaselineCategory nextBaselineCategory()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more baseline categories.
  *
  * @return true if there are more baseline categories, false otherwise
  */
  public boolean hasMoreBaselineCategories();
  
}