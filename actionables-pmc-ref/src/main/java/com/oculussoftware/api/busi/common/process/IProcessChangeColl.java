package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;

/*
* $Workfile: IProcessChangeColl.java $
* Description: Defines the methods needed for an unordered collection
* of IProcessChanges.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An extension of IRCollection, defines the convenience methods 
* necessary for iterating through an unordered collection of
* IProcessChanges.
*
* @author Egan Royal
*/
public interface IProcessChangeColl extends IRCollection
{
  /**
  * Calls the next() method of the superclass, casts 
  * it to an IProcessChange, and returns it.
  * @return The next IProcessChange in the collection.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IProcessChange nextProcessChange() throws OculusException;
  
  /**
  * Delegates to the superclass hasNext() method.
  * @return true - iff there is a next element.  
  */
  public boolean hasMoreProcessChanges();
}