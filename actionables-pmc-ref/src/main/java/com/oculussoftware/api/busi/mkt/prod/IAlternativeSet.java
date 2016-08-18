package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;

/** This interface represents a set of alternative for a suggested feature.  Each alternative
* is a part of an alternative set.  The alternative set has a collection of alternatives, zero
* or one of which is the chosen alternative.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IAlternativeSet.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IAlternativeSet extends IBusinessObject {

  /** Returns the collection of alternatives in this set.
  *
  * @return collection of alternative in this set
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IAlternativeColl getAlternatives()
    throws OculusException;

  /** Returns the alternative that has been chosen in this set.
  *
  * @return the chosen alternative
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IAlternative getChosenAlternative()
    throws OculusException;


  /** Returns the IIID of the feature-category link object that this alternative
  * set is for.  Note: This method is a misnomer.  It returns the IIID, not the object.
  *
  * @return the IIID of the feature-category link that this alternative set is for
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IIID getFeatureCategoryLinkObject() throws OculusException;

  /** Returns the IIID of the feature object that this alternative is for.
  *
  * @return the IIID of this feature object that this alternative is for
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IIID getFeatureIID() throws OculusException;


  /** Returns the comment made for choosing the chosen alternative.
  *
  * @return the comment made about choosing the alternative
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public String getSelectionComment()
  	throws OculusException;


  /** Sets the given alternative as the chosen alternative.
  *
  * @param alt the chosen alternative
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IAlternativeSet setChosenAlterative(IAlternative alt) throws OculusException;


  /** Sets the given alternative as the chosen alternative.
  *
  * @param alt the chosen alternative
  * @param selectionComment the comment about choosing that given alternative
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IAlternativeSet setChosenAlterative(IAlternative alt, String selectionComment) throws OculusException;

  /** Sets the feature-category link object that this alternative set is for.
  *
  * @param cl the feature-category link object that this alternative set is for
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IAlternativeSet setFeatureCategoryLinkObject(IFeatureCategoryLink cl) throws OculusException;
}