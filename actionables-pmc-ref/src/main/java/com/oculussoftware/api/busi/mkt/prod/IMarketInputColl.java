package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;

/** Represents a collection of market inputs.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Alok Pota
*/

/*
* $Workfile: IMarketInputColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IMarketInputColl extends IBusinessObjectColl
{
  /** Returns the next market input in the collection
  *
  * @return the next market input in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IMarketInput nextMarketInput()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more market inputs.
  *
  * @return true if there are more market inputs, false otherwise
  */
  public boolean hasMoreMarketInputs();
  

}
