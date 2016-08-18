package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import java.sql.Timestamp;

/** This interface represents a version of a product.  It can also be mapped to an MRD, or
* a deliverable of a complete marketing cylce.  The version represents what is being made or
* what can be sold individually.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IProductVersion.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
* ---             Saleem Shafi    2/24/00     Added createNewBaseline() method.
* ---             Saleem Shafi    3/21/00     added pinAllFeatureLinks().
*/

public interface IProductVersion extends IBusinessObject, IProjectParent
{
  /** This value should be used to retrieve the order number property through IRPropertyMap.get(); */
  public static final String LABEL_ORDERNUM = "prop"+IDCONST.ORDERNUM.getIIDValue();
  /** This value should be used to retrieve the target release date property through IRPropertyMap.get(); */
  public static final String LABEL_TARGETRELEASEDATE = "prop"+IDCONST.TARGET_RELEASE_DATE.getIIDValue();
  /** This value should be used to retrieve the estimated release date property through IRPropertyMap.get(); */
  public static final String LABEL_ESTIMATEDRELEASEDATE = "prop"+IDCONST.ESTIMATED_RELEASE_DATE.getIIDValue();
  /** This value should be used to retrieve the actual release date property through IRPropertyMap.get(); */
  public static final String LABEL_ACTUALRELEASEDATE = "prop"+IDCONST.ACTUAL_RELEASE_DATE.getIIDValue();


  /** Creates, associates and returns a new category for this version.
  *
  * @return the newly created category
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public ICategory createNewCategory()
    throws OculusException;  
  
  /** Creates the category alias for this version.
  *
  * @return the newly created default category for this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public ICategory createDefaultCategory()
    throws OculusException;  

  /** Creates a shallow copy of this version.
  *
  * @return the newly created copy of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion createCopy()
    throws OculusException;

  /** Copies the categories and feature associations from the given version to this version.
  *
  * @param source the version whose structure is being copied
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion copyStructureOf(IProductVersion source)
    throws OculusException;
    
  /** Copies the version team assignments from the given version to this version.
  *
  * @param source the version whose team is being copied
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion copyTeamOf(IProductVersion source)
    throws OculusException;

  /** Associates this version with the given version.
  *
  * @param oldVer the version to be associated
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion associateVersion(IProductVersion newVer)
    throws OculusException;  
  
  /** Disassociates this version with the given version.
  *
  * @param oldVer the version to be disassociated
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion disAssociateVersion(IProductVersion oldVer)
    throws OculusException;  
  
  /** Returns whether or not this version is associated with any other versions.
  *
  * @return true if this version is associated with another version, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public boolean isAssociatedWithAVersion()
    throws OculusException;
    
  /** Creates and returns a new baseline for this version.  This method does not define the
  * baseline, it just creates the shell.
  *
  * @return the new version baseline
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IVersionBaseline createNewBaseline()
    throws OculusException;
    
  /** Creates and returns an alert configuration for this version.
  *
  * @return the newly created alert configuration
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IAlertConfig createAlertConfig()
    throws OculusException;

  /** Pins all of the feature links in this version.
  *
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public void pinAllFeatureLinks()
    throws OculusException;
    
  /** Moves the version to Accolades.
  *
  * @param strComment the comment for the feature that get moved from Compass to Accolades
  * @param recurse true if the child objects should be moved too
  * @param transIID the IIID of the transition to use to move the features
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException;  
  
  /** Moves the version to Compass.
  *
  * @param strComment the comment for the feature that get moved from Accolades to Compass
  * @param recurse true if the child objects should be moved too
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersion moveToCompass(String strComment, boolean recurse)
    throws OculusException;

  /** Sets the product of that this object is a version of.
  *
  * @param product the product of this version
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOExceptoin
  */
  public IProductVersion setProductObject(IProduct product)
    throws ORIOException;
    
  /** Sets the order number of this version with respect to the other version of this product.
  *
  * @param order the order number of this version
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOExceptoin
  */
  public IProductVersion setOrderNum(int order)
    throws ORIOException;
    
  /** Sets the target release date of this version.
  *
  * @param target the target release date
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOExceptoin
  */
  public IProductVersion setTargetReleaseDate(Timestamp target)
    throws ORIOException;
    
  /** Sets the estimated release date of this version.
  *
  * @param estimated the estimated release date
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOExceptoin
  */
  public IProductVersion setEstimatedReleaseDate(Timestamp estimated)
    throws ORIOException;
    
  /** Sets the actual release date of this version.
  *
  * @param actual the actual release date
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOExceptoin
  */
  public IProductVersion setActualReleaseDate(Timestamp actual)
    throws ORIOException;
    
  /** Returns the product that this is object is a version of.
  *
  * @return the product of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProduct getProductObject()
    throws OculusException;
  
  /** Returns the product that this is object is a version of.
  *
  * @param edit true if the product should be locked, false otherwise
  * @return the product of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProduct getProductObject(boolean edit)
    throws OculusException;
    
  /** Returns the order number of this version with respect to the other version of this product.
  *
  * @return the order number of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public int getOrderNum()
    throws OculusException;
    
  /** Returns the target release date of this version.
  *
  * @return the target release date of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public Timestamp getTargetReleaseDate()
    throws OculusException;
    
  /** Returns the estimated release date of this version.
  *
  * @return the estimated release date of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public Timestamp getEstimatedReleaseDate()
    throws OculusException;
    
  /** Returns the actual release date of this version.
  *
  * @return the actual release date of this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public Timestamp getActualReleaseDate()
    throws OculusException;
 
  /** Returns the list of baselines created on this version.
  *
  * @param args the parameters used to create the list
  * @return the collection of baselines for this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IVersionBaselineColl getBaselines()
    throws OculusException;

  /** Returns the list of baselines created on this version.
  *
  * @param args the parameters used to create the list
  * @return the collection of baselines for this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IVersionBaselineColl getBaselines(IDataSet args)
    throws OculusException;

  /** Returns the category that serves as the alias for this version.
  *
  * @return the default category for this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public ICategory getDefaultCategory()
    throws OculusException;
     
  /** Returns the list of categories that are directly associated with this version.  This method
  * will not return sub-categories in this version.
  *
  * @return the collection of categories in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public ICategoryColl getCategories()
    throws OculusException;   

  /** Returns the list of categories that are directly associated with this version.  This method
  * will not return sub-categories in this version.
  *
  * @param editable true if the categories should be locked, false otherwise
  * @return the collection of categories in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public ICategoryColl getCategories(boolean editable)
    throws OculusException;   

  /** Returns whether or not this version is a parent of the given version.
  *
  * @param child the child version
  * @return true if this version is a parent of the given version, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public boolean isParentOf(IProductVersion child)
    throws OculusException;

  /** Returns whether or not this version is a sub-version of the given version.
  *
  * @param parent the parent version
  * @return true if this version is a sub-version of the given version, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public boolean isChildOf(IProductVersion parent)
    throws OculusException;
    
  /** Returns a list of versions that this version considers sub-versions.
  *
  * @param editable true if the features should be locked, false otherwise
  * @return the list of versions that are included in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersionColl getVersions(boolean editable)
    throws OculusException; 

  /** Returns a list of versions that consider this version a sub-version.
  *
  * @param editable true if the features should be locked, false otherwise
  * @return the list of versions that include this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IProductVersionColl getParentVersions(boolean editable)
    throws OculusException; 

  /** Returns the list of features that are directly associated with this version.  This method
  * will not return features that are associated with categories in this version.
  *
  * @return the collection of features in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IFeatureList getFeatures()
    throws OculusException;

  /** Returns the list of features that are directly associated with this version.  This method
  * will not return features that are associated with categories in this version.
  *
  * @param args the parameters used to get the list
  * @return the collection of features in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IFeatureList getFeatures(IDataSet args)
    throws OculusException;

  /** Returns the list of features that are directly associated with this version.  This method
  * will not return features that are associated with categories in this version.
  *
  * @param editable true if the features should be locked, false otherwise
  * @return the collection of features in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IFeatureList getFeatures(boolean editable)
    throws OculusException;   

  /** Returns the list of features that are directly associated with this version.  This method
  * will not return features that are associated with categories in this version.
  *
  * @param args the parameters used to get the list
  * @param editable true if the features should be locked, false otherwise
  * @return the collection of features in this version
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IFeatureList getFeatures(IDataSet args, boolean editable)
    throws OculusException;   

  /** Returns the alerts that are associated with this version.
  *
  * @return the collection of alert configurations
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IAlertConfigColl getAlerts()
    throws OculusException;
    
  /** Returns the alerts that are associated with this version.
  *
  * @param edit true if the alerts should be locked, false otherwise
  * @return the collection of alert configurations
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IAlertConfigColl getAlerts(boolean edit)
    throws OculusException;
    
  /** Returns the disposition object for this product version.  The disposition object is a
  * means of relating market inputs to product versions.
  *
  * @return the disposition object
  * @exception com.oculussoftware.api.sysi.OculusExceptoin
  */
  public IRDisposition getDispositionObject()
    throws OculusException;  
}