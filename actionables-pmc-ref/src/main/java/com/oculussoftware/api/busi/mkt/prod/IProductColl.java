package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of products.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IProductColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IProductColl extends IBusinessObjectColl
{
  /** Returns the next product in the collection
  *
  * @return the next product in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProduct nextProduct()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more products.
  *
  * @return true if there are more products, false otherwise
  */
  public boolean hasMoreProducts();
  
}