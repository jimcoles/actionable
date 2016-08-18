package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.*;

/** This interface represents the link between a feature and a category and all of
* the data that goes along with it.  It has knowledge of all version-specific feature
* information, such as priority, percent complete, etc.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureLinkChange.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFeatureLinkChange extends IRObject
{
  /** Records a change to an attribute on the feature-category link object.  It records
  * which attribute was changed plus the old and new value of the attribute.
  *
  * @param attribID the IIID of the attribute that is being changed
  * @param oldvalue the old value of the property
  * @param newvalue the new value of the property
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void record(IIID attribID, Object oldvalue, Object newvalue)
    throws OculusException;
  
  /** Returns a list of changes that occured in this change.  It is possible to have
  * multiple attribute changes for a single edit.
  *
  * @return list of attribute change for this edit
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public java.util.List getChanges()
    throws OculusException;
  
  /** Returns the feature-category link object that this change is referring to.
  *
  * @return the feature-category link object for this change
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink getFeatureCategoryLinkObject()
    throws OculusException;

  /** Returns the comment made about this change.
  *
  * @return the comment behind this change
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getChangeComment()
    throws OculusException;

  /** Returns the date on which this change was made.
  *
  * @return the date of this change
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getChangeDate()
    throws OculusException;

  /** Returns the user who made this change.
  *
  * @return the user who made this change
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser getChangeUserObject()
    throws OculusException;

  /** Sets the feature-category link object for this change.
  *
  * @param featcatlink the feature-category link object for this change
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange setFeatureCategoryLinkObject(IFeatureCategoryLink featcatlink)
    throws ORIOException;

  /** Sets the comment for this change.
  *
  * @param comment the comment for this change
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange setChangeComment(String comment)
    throws ORIOException;

  /** Sets the date that this change was made.
  *
  * @param time the date of this change
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange setChangeDate(Timestamp time)
    throws ORIOException;

  /** Sets the user who made this change.
  *
  * @param user the user who made the change
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange setChangeUserObject(IUser user)
    throws ORIOException;
  
}