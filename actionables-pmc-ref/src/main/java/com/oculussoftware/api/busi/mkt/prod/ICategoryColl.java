package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of categories.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ICategoryColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface ICategoryColl extends IBusinessObjectColl
{
  /** Returns the next category in the collection
  *
  * @return the next category in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory nextCategory()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more categories.
  *
  * @return true if there are more categories, false otherwise
  */
  public boolean hasMoreCategories();
  
}