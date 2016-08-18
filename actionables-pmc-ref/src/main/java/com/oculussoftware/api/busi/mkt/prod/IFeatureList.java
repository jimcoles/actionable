package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectList;

/** This interface represents any list of discussion topics.  It contains methods that
* maintain the order within the collection.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureList.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFeatureList extends IBusinessObjectList, IFeatureColl
{
  /** Sets the order number of the given feature-category link object.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param featCatLink the feature-category link whose order is changing
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void setOrder(IFeatureCategoryLink featCatLink, int order)
    throws OculusException;
    
  /** Adds the given feature-category link object to the collection in the given
  * order.  It also adjusts the other objects in this collection around the change.
  *
  * @param featCatLink the feature-category link being added
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void addToColl(IFeatureCategoryLink featCatLink, int order)
    throws OculusException;

  /** Removes the given feature-category link object from the collection.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param featCatLink the feature-category link being removed
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeFromColl(IFeatureCategoryLink featCatLink)
    throws OculusException;
}