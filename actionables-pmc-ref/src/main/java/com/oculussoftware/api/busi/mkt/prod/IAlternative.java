package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;


/** This interface represents an alternative for a suggested feature.  Each alternative
* is a part of an alternative set.  The only meaningful data for an alternative is a 
* name and description.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IAlternative.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IAlternative extends IBusinessObject {

  /** Returns the alternative set that this alternative belongs to.
  * 
  * @return the alternative set that this alternative belongs to.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlternativeSet getAlternativeSet()
    throws OculusException;

  /** Returns the alternative set that this alternative belongs to.
  * 
  * @param checkout true if the set should be locked and checked out for edit
  * @return the alternative set that this alternative belongs to.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlternativeSet getAlternativeSet(boolean checkout)
    throws OculusException;

  /** Sets the alternative set that this alternative belongs to.
  * 
  * @param the alternative set that this alternative belongs to.
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlternative setAlternativeSet(IAlternativeSet altSet)
    throws OculusException;
}
