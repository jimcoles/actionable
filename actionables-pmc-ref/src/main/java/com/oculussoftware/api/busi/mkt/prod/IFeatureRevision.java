package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;

import java.sql.*;

/** This interface represents the link between a feature and a category and all of
* the data that goes along with it.  It has knowledge of all version-specific feature
* information, such as priority, percent complete, etc.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureRevision.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
*/
public interface IFeatureRevision extends IBusinessObject
{
  public static final String LABEL_COMMENT = "prop"+IDCONST.COMMENT.getIIDValue();
  public static final String LABEL_REVISIONNAME = "prop"+IDCONST.REVISION_LABEL.getIIDValue();

  /** Sets the changeability of this feature revision. Note: this method is not being used.
  *
  * @param changeable true if the revision is changeable, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision setIsChangeable(boolean changeable)
    throws OculusException;

  /** Sets the comment for the creation of this revision.
  *
  * @param comment the comment for the creation of this revision
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision setComment(String comment)
    throws OculusException;

  /** Sets the name of this revision.
  *
  * @param revName the name of this revision
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision setRevisionLabel(String revName)
    throws OculusException;

  /** Sets the auto-generated name of this revision.
  *
  * @param autoRevLabel the auto-generated name of this revision
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision setAutoRevisionLabel(String autRevLabel)
    throws OculusException;

  /** Sets the feature object that this object is a revision of.
  *
  * @param feature the feature object that this object is a revision of
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setFeatureObject(IFeature feature)
    throws OculusException;
    
  /** Returns whether or not this revision is changeable. Note: this method is not being used.
  *
  * @return true if the revision is changeable, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isChangeable()
    throws OculusException;

  /** Returns the comment made about the creation of this revision.
  *
  * @return comment behind the creation of this revision
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getComment()
    throws OculusException;

  /** Returns the name of this revision.
  *
  * @return the name of this revision
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getRevisionLabel()
    throws OculusException;

  /** Returns the auto-generated name of this revision.
  *
  * @return the auto-generated name of this revision
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getAutoRevisionLabel()
    throws OculusException;

  /** Returns the feature object for this revision.
  *
  * @return the feature object for this revision
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature getFeatureObject()
    throws OculusException;

  /** Returns the feature object for this revision.
  *
  * @param editable true if the feature object should be locked, false otherwise
  * @return the feature object for this revision
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature getFeatureObject(boolean editable)
    throws OculusException;

  /** Returns the collection of versions that this feature revision is referenced by.
  *
  * @return the collection of version this feature revision is referenced by.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersionColl getAssociatedVersions()
    throws OculusException;
}