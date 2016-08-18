package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;

import java.sql.Timestamp;

/** This interface represents a baseline of a version.  The baseline is a snapshot of a version
* at a particular point in time.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IVersionBaseline.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IVersionBaseline extends IProductVersion
{
  /** Creates the baseline for set version.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void define()
    throws OculusException;

  /** Creates the baseline for the given version.
  *
  * @param version the version to make a baseline of
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void define(IProductVersion version)
    throws OculusException;

  /** Sets the product-version object which this baseline was made for.
  *
  * @param version the version of this baseline
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IVersionBaseline setProductVersionObject(IProductVersion version)
    throws OculusException;

  /** Sets the name of the version at the time of the baseline.
  *
  * @param vername name of the version at the time of the baseline.
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IVersionBaseline setVersionName(String vername)
    throws ORIOException;
    
  /** Returns the product-version that this baseline was created for.
  *
  * @return the version for this baseline
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersion getProductVersionObject()
    throws OculusException;

  /** Returns the name of the version for which this baseline was created.
  *
  * @return name of the version at the time of the baseline.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getVersionName()
    throws OculusException;    
}