package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.mkt.comm.*;
import java.sql.Timestamp;

/** This interface represents a category within a baseline.  It acts just as any other
* ICategory, except that it knows about the baseline it connected to.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IBaselineCategory.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IBaselineCategory extends ICategory
{
  /** Defines this baseline category by making a copy of the current state of the given
  * category.
  * 
  * @param category the category to take a baseline of
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void define(ICategory category)
    throws OculusException;
    
  /** Sets the baseline object that this baseline category is a part of.
  * 
  * @param the parent baseline object for this baseline category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBaselineCategory setVersionBaselineObject(IVersionBaseline baseline)
    throws ORIOException;

  /** Returns the baseline object that this baseline category is a part of.
  * 
  * @return the parent baseline object for this baseline category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IVersionBaseline getVersionBaselineObject()
    throws OculusException;
}