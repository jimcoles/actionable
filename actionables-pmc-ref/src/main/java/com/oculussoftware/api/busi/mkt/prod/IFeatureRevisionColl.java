package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of feature revisions.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureRevisionColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFeatureRevisionColl extends IBusinessObjectColl
{
  /** Returns the next feature revision in the collection
  *
  * @return the next feature revision in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision nextFeatureRevision()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more feature revisions.
  *
  * @return true if there are more feature revisions, false otherwise
  */
  public boolean hasMoreFeatureRevisions();
  
}