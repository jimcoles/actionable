package com.oculussoftware.api.busi.mkt.prod;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;


import java.sql.Timestamp;

/** 
*
* @author Saleem Shafi
*/

/*
* $Workfile: IStandardsCollection.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Cuihua Zhang    6/16/2000   added getStdFeatureLinks()
*/

public interface IStandardsCollection extends IBusinessObject
{
  /** Sets the parent standards collection for this standards collection.
  *
  * @param stdcoll the parent standards collection
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IStandardsCollection setParentObject(IStandardsCollection stdcoll)
  	throws ORIOException;

  /** Sets the IIID of the parent standards collection for this standards collection.
  *
  * @param piid the IIID ofthe parent standards collection
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IStandardsCollection setParObjectIID(IIID piid)
    throws ORIOException;
  
  /** Returns the IIID of the parent standards collection for this standards collection.
  *
  * @return the IIID of the parent standards collection
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParObjectIID()
    throws ORIOException;
  
  /** Creates and returns a new standards collection that is associated with this standards
  * collection.
  *
  * @return the newly created standards collection
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IStandardsCollection createNewStdCollection()
	  throws OculusException;
  
  /** Associates the given feature with this standards collection.
  *
  * @param the feature to associate
  * @return the associated feature
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature associateFeature(IFeature feature)
    throws OculusException;

  /** Creates and returns a new feature that is associated with this standards collection.
  *
  * @param the parent standards collection
  * @return the newly created feature
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature createNewFeature()
    throws OculusException;

  /** Returns the parent standards collection for this standards collection.
  *
  * @param editable true if the parent should be locked, false otherwise
  * @return the parent standards collection
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IRObject getParentObject(boolean editable)
	  throws OculusException;

  /** Sets the parent standards collection for this standards collection.
  *
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IStandardsCollection setParentObject()
  	throws ORIOException;
  
  /** Returns all of the features associated with this standards collection.
  *
  * @return the collection of features for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures()
    throws OculusException;
  
  /** Returns all of the features associated with this standards collection.
  *
  * @param args the parameters to use to get the list
  * @return the collection of features for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(IDataSet args)
    throws OculusException;
    
  /** Returns all of the features associated with this standards collection.
  *
  * @param editable true if the features should be locked
  * @return the collection of features for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(boolean editable)
    throws OculusException;
 
  /** Returns all of the features associated with this standards collection.
  *
  * @param args the parameters to use to get the list
  * @param editable true if the features should be locked
  * @return the collection of features for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFeatures(IDataSet args, boolean editable)
    throws OculusException;
 
  /** Returns all of the standards-feature links associated with this standards collection.
  *
  * @return the collection of standards-feature links for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStdFeatureLinkColl getStdFeatureLinks()
    throws OculusException;
 
  /** Returns all of the standards-feature links associated with this standards collection.
  *
  * @param editable true if the links should be locked
  * @return the collection of standards-feature links for this standards collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStdFeatureLinkColl getStdFeatureLinks(boolean editable)
    throws OculusException;
}