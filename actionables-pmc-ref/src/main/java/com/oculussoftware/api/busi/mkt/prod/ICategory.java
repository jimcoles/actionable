package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.Timestamp;

/** This interface represents a category of features.  A category is simply a collection
* of features and has little functionality/data of its own.  Features are always associated
* with categories.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ICategory.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
* ---             Saleem Shafi    3/21/00     added pinAllFeatureLinks().
* ---             Saleem Shafi    3/24/00     Added createCopy.
*/

public interface ICategory extends IBusinessObject
{
  /** This value is the value to be used when trying to retrieve the order number property 
  from the IRProperty.get() method. */
  public static final String LABEL_ORDERNUM = "prop"+IDCONST.ORDERNUM.getIIDValue();

  /** Creates a new sub-category and associates it with this category.
  *
  * @return the category created
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory createNewCategory()
    throws OculusException;

  /** Creates a copy of this category.
  *
  * @return the newly created category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory createCopy()
    throws OculusException;
  
  /** Creates a copy of this category and associates it with the given version.
  *
  * @param ver the version to create the category for
  * @return the newly created category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory createCopyFor(IProductVersion ver)
    throws OculusException;
    
  /** Creates a new feature and associates it with this category.
  *
  * @return the feature created
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature createNewFeature()
    throws OculusException;

  /** Adds the given feature to this category.
  *
  * @param feature the feature object to add to this category
  * @return the feature added
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature associateFeature(IFeature feature)
    throws OculusException;
    
  /** Removes the given feature from this category.
  *
  * @param feature the feature object to remove from this category
  * @return the feature removed
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature disAssociateFeature(IFeature feature)
    throws OculusException;
    
  /** Pins all of the features in this category to the current revision.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void pinAllFeatureLinks()
    throws OculusException;

  /** Moves this category (and its features) to Accolades.
  *
  * @param strComment the comment for the features within this category that are moved
  *        from Compass to Accolades.
  * @param recurse true if the sub-categories should be moved too, false otherwise
  * @param transIID the IIID of the transition to perform to move the features
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException;
    
  /** Moves this category (and its features) to Compass.
  *
  * @param strComment the comment for the features within this category that are moved
  *        from Accolades to Compass.
  * @param recurse true if the sub-categories should be moved too, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory moveToCompass(String strComment, boolean recurse)
    throws OculusException;
    
  /** Returns whether or not this category is the default category for its parent version.
  * The default category is never shown to the user, but acts as the method for a version
  * to have features directly within it.
  *
  * @param version the parent version of this category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isDefaultCategory()
    throws OculusException;
    
  /** Sets the order of this category with respect to its sibling categories.
  *
  * @param order the order number of this category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory setOrderNum(int order)
    throws ORIOException;
    
  /** Sets the parent object of this category.
  *
  * @param parentCat the parent category or version of this category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory setParentObject(IBusinessObject parentCat)
    throws ORIOException;
    
  /** Sets the version that this category is a part of.
  *
  * @param version the parent version of this category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory setVersionObject(IProductVersion version)
    throws OculusException;
        
  /** Sets the single-user roles for this category to the same users as its parent object.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory setSingleRoles()
    throws OculusException; 

  /** Sets the visible id of this category.  The IIID value for this object is
  * too "ugly" for the user to have to see and remember, so this id should be 
  * used for the user.  It needs to be unique for all categories.
  *
  * @param id the visible id for this category
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory setVisibleID(long id)
    throws ORIOException;
    
  /** Returns the name of this category the way it should be displayed to the user.
  * The category's visible id should probably be a part of the resulting String.
  *
  * @return the display name for this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getDisplayName()
    throws OculusException;
    
  /** Returns the order number of this category with respect to its siblings.
  *
  * @return the order number of this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getOrderNum()
    throws OculusException;
    
  /** Returns the id of this category that is visible to the user.  The IIID value
  * for this object is too "ugly" for the user to have to see and remember, so this
  * id should be used for the user.  It needs to be unique for all categories.
  *
  * @return the visible id of this category
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public long getVisibleID()
    throws ORIOException;
    
  /** Returns the version that this category is in.
  *
  * @return the version object for this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersion getVersionObject()
    throws OculusException;
    
  /** Returns the version that this category is in.
  *
  * @param editable true if the version object should be locked and editable
  * @return the version object for this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersion getVersionObject(boolean editable)
    throws OculusException;

  /** Returns the sub-categories in this category.
  *
  * @return the list of sub-categories in this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategoryColl getCategories()
    throws OculusException;

  /** Returns the sub-categories in this category.
  *
  * @param editable true if the categories in the list should be locked, false otherwise
  * @return the list of sub-categories in this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategoryColl getCategories(boolean editable)
    throws OculusException;   

  /** Returns the features in this category.
  *
  * @return the list of features in this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures()
    throws OculusException;

  /** Returns the features in this category.
  *
  * @param editable true if the features in the list should be locked, false otherwise
  * @return the list of features in this category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(boolean editable)
    throws OculusException;   

  /** Returns the features in this category.
  *
  * @param args the set of arguments used to parameterize the list being returned
  * @return the list of features in this category matching the parameters in args
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(IDataSet args)
    throws OculusException;

  /** Returns the features in this category.
  *
  * @param args the set of arguments used to parameterize the list being returned
  * @param editable true if the features in the list should be locked, false otherwise
  * @return the list of features in this category matching the parameters in args
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(IDataSet args, boolean editable)
    throws OculusException;   
    
  /** Returns the number of features within this category that are in the state for
  * for the given IIID.
  *
  * @param stateiid the IIID of the state
  * @return the number of feature in the given state
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int numberFeaturesInState(IIID stateiid)
    throws OculusException;
  
  /** Returns the number of features within this category that are not in the state for
  * for the given IIID.
  *
  * @param stateiid the IIID of the state
  * @return the number of feature not in the given state
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int numberFeaturesNotInState(IIID stateiid)
    throws OculusException;
}