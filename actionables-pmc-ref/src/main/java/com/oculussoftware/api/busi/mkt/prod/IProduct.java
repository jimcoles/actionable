package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;


import java.sql.Timestamp;

/** This interface represents a product.  It has knowledge of the different versions of the
* product.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IProduct.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/7/00      Added getEnabledTransitions()
* ---             Saleem Shafi    2/9/00      Moved getEnabledTransitions() to IBusinessObject
* ---             Saleem Shafi    2/10/00     Added accessors and mutators to reflect
*                                             the new database.
* ---             Saleem Shafi    2/11/00     Moved creationDate, creator, access, and attachment
*                                             accessors and mutators to IBusinessObject
* ---             Saleem Shafi    2/14/00     Added getVersions(), createNewVersion()
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
*/

public interface IProduct extends IBusinessObject
{
  /** This is the value to use to retrieve the auto-revision property of IRPropertyMap.get(). */
  final String LABEL_AUTOREVISION = "Auto-Revision";

  /** Creates a new version of this product.
  *
  * @return the newly created version
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersion createNewVersion()
    throws OculusException;  
    
  /** Moves this product to Accolades.
  *
  * @param strComment the comment for the features that get moved from Compass to Accolades
  * @param recurse true if all child objects should be moved to
  * @param transIID the IIID of the transition to use to move the features
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProduct moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException;
    
  /** Moves this product to Compass.
  *
  * @param strComment the comment for the features that get moved from Accolades to Compass
  * @param recurse true if all child objects should be moved to
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProduct moveToCompass(String strComment, boolean recurse)
    throws OculusException;
	
  /** Returns whether or not the name of this product is already being used by another product.
  *
  * @return true if the name already exists, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public boolean nameExists()
    throws OculusException;	

  /** Sets whether or not this product users automatic revision names.  Note: This method
  * is not being used.
  *
  * @param true if this product uses auto-revision names, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProduct setAutoRevision(boolean autoRev)
    throws ORIOException;
    
  /** Returns whether or not this product uses automatic revision names.  Note: This method
  * is not being used.
  *
  * @return true if this product uses auto-revision names, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean getAutoRevision()
    throws OculusException;

  /** Returns the versions of this product.
  *
  * @return collection of version of this product
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersionColl getVersions()
    throws OculusException;

  /** Returns the versions of this product.
  *
  * @param editable true if the version should be locked, false otherwise
  * @return collection of version of this product
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersionColl getVersions(boolean editable)
    throws OculusException;
}