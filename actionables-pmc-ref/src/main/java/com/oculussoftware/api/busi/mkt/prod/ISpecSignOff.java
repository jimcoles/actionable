package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.common.org.IUser;
import java.sql.Timestamp;

/** This interface represents a specification sign-off.
*
* @author Egan Royal
*/

/*
* $Workfile: ISpecSignOff.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface ISpecSignOff extends IBusinessObject
{
  /** Sets the IIID of the feature-category link object being signed off.
  *
  * @param featcatiid the IIID of the feature-category link object being signed off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setFeatureCategoryLinkIID(IIID featcatiid)
    throws ORIOException;

  /** Sets the feature-category link object being signed off.
  *
  * @param featcat the feature-category link object being signed off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setFeatureCategoryLinkObject(IFeatureCategoryLink featcat)
    throws ORIOException; 
    
  /** Sets the IIID of the user who is signing off.
  *
  * @param useriid the IIID of the user signing off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setUserIID(IIID useriid)
    throws ORIOException;
    
  /** Sets the user who is signing off.
  *
  * @param user the user signing off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setUserObject(IUser user)
    throws ORIOException;
    
  /** Sets the comment for this sign-off.
  *
  * @param comment the comment about this sign-off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setComment(String comment)
    throws ORIOException;
    
  /** Sets the acceptance state of this sign-off.
  *
  * @param astate the acceptance state of this sign-off
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setAcceptState(AcceptState astate)
    throws ORIOException;

  /** Sets the date of the last edit to this sign-off.
  *
  * @param date the date of the last edit
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISpecSignOff setEditDate(Timestamp date)
    throws ORIOException;
  
  /** Returns the IIID of the feature-category link object associated with this sign-off.
  *
  * @return the IIID of the feature-cateogry link being signed off.
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getFeatureCategoryLinkIID()
    throws ORIOException;
    
  /** Returns the feature-category link object associated with this sign-off.
  *
  * @return the feature-cateogry link being signed off.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink getFeatureCategoryLinkObject()
    throws OculusException;
    
  /** Returns the IIID of the user who is associated with this sign-off.
  *
  * @return the IIID of the user who is signing off.
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getUserIID()
    throws ORIOException;
    
  /** Returns the user who is associated with this sign-off.
  *
  * @return the user who is signing off.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser getUserObject()
    throws OculusException;
  
  /** Returns the comment given about this sign-off.
  *
  * @return the comment about this sign-off
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getComment()
    throws OculusException;
    
  /** Returns the acceptance state of this sign-off.
  *
  * @return the acceptance state of this sign-off
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public AcceptState getAcceptState()
    throws ORIOException;
    
  /** Returns the date that this sign-off was last edited.
  *
  * @return the date of the last edit
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getEditDate()
    throws OculusException;
}