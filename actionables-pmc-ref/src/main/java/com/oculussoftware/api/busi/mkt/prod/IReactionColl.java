package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;


/** Represents a collection of reactions.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Alok Pota
*/

/*
* $Workfile: IReactionColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IReactionColl extends IBusinessObjectColl
{
  /** Returns the next reaction in the collection
  *
  * @return the next reaction in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IReaction nextReaction()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more reactions.
  *
  * @return true if there are more reactions, false otherwise
  */
  public boolean hasMoreReactions();
  

}