package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of spec sign-offs.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: ISpecSignOffColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface ISpecSignOffColl extends IBusinessObjectColl
{
  /** Returns the next spec sign-off in the collection
  *
  * @return the next spec sign-off in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISpecSignOff nextSpecSignOff()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more spec sign-offs.
  *
  * @return true if there are more spec sign-offs, false otherwise
  */
  public boolean hasMoreSpecSignOffs();
  
}