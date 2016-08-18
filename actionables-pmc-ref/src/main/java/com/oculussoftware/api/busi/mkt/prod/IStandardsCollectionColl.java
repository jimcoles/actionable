package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of standards collections.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Cuihua Zhang
*/

/*
* $Workfile: IStandardsCollectionColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IStandardsCollectionColl extends IBusinessObjectColl
{
  /** Returns the next standards collection in the collection
  *
  * @return the next standards collection in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStandardsCollection nextStandardsCollection()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more standards collections.
  *
  * @return true if there are more standards collections, false otherwise
  */
  public boolean hasMoreStandardsCollection();
  
}