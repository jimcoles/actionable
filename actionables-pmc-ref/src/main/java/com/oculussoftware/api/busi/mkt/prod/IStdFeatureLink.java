package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;

import java.sql.*;

/** This interface represents a link between a feature and a standards collection.  There isn't
* any data involved with the association, so all the information in this interface is delegated
* to the respective objects.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IStdFeatureLink.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IStdFeatureLink extends IBusinessObject
{
  /** Sets the feature object for this standards-feature link.
  *
  * @param the feature for this standards-feature link
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IStdFeatureLink setFeatureObject(IFeature feature)
    throws OculusException;
    
  /** Sets the standards collection object for this standards-feature link.
  *
  * @param the standards collection for this standards-feature link
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IStdFeatureLink setStdCollectionObject(IStandardsCollection stdc)
    throws ORIOException;

  /** Returns the feature for this standards-feature link.
  *
  * @return the feature in this standards-feature link
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IFeature getFeatureObject()
    throws OculusException;
  
  /** Returns the standards collection for this standards-feature link.
  *
  * @return the standards collection in this standards-feature link
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IStandardsCollection getStdCollectionObject()
    throws OculusException;
    
  /** Returns the name of the feature for this standards-feature link.
  *
  * @return the feature's name
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getFeatureName()
    throws OculusException;
    
  /** Returns the name of the standards collection for this standards-feature link.
  *
  * @return the standards collection's name
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getStdCollectionName()
    throws OculusException;
 
  /** Returns the description of the standards collection for this standards-feature link.
  *
  * @return the standards collection's description
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getStdCollectionDescription()
    throws OculusException;
    
  /** Returns the description of the feature for this standards-feature link.
  *
  * @return the feature's description
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getFeatureDescription()
    throws OculusException;
}