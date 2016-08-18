package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of standard-feature links.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Cuihua Zhang
*/

/*
* $Workfile: IStdFeatureLinkColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IStdFeatureLinkColl extends IBusinessObjectColl
{
  /** Returns the next standard-feature link in the collection
  *
  * @return the next standard-feature link in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStdFeatureLink nextStdFeatureLink()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more standard-feature links.
  *
  * @return true if there are more standard-feature links, false otherwise
  */
  public boolean hasMoreStdFeatureLink();
  
}