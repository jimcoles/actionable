package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;

import java.sql.*;

/** This interface represents a feature-category link within a baseline.  It acts just
* as any other IFeatureCategoryLink, except that it always knows exactly which
* IFeatureRevision object that its connected to.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IBaselineFeatureCategoryLink.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
* ---             Saleem Shafi    3/13/00     Added getPinnedFeatureRevisionObject()
*/

public interface IBaselineFeatureCategoryLink extends IFeatureCategoryLink
{
  /** Defines this baseline feature-category link by making a copy of the current state
  * of the given category.
  * 
  * @param category the category to take a baseline of
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void define(IFeatureCategoryLink featcatlink)
    throws OculusException;

  /** Returns the IFeatureRevision object referenced by this baseline feature-category
  * link object.
  *
  * @return the feature revision for this baseline-feature category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision getFeatureRevisionObject()
    throws OculusException;
}