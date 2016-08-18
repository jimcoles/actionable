package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/** Represents a collection of alternatives.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IAlternativeColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IAlternativeColl extends IBusinessObjectColl {
  /** Returns the next alternative in the collection
  *
  * @return the next alternative in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasMoreAlternatives()	
    throws OculusException;
  
  /** Returns whether or not the collection contains any more alternatives.
  *
  * @return true if there are more alternatives, false otherwise
  */
  public IAlternative nextAlternative()
  	throws OculusException;
}
