package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectList;

/**
* Filename:    IProductVersionList.java
* Date:        2/14/00
* Description: Defines the interface that needs to be implemented by any class that
* represents a list of IProductVersion objects.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public interface IProductVersionList extends IBusinessObjectList, IProductVersionColl
{
  /** Sets the order number of the given version object.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param cat the version whose order is changing
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void setOrder(IProductVersion cat, int order)
    throws OculusException;
    
  /** Adds the given version object to the collection in the given
  * order.  It also adjusts the other objects in this collection around the change.
  *
  * @param cat the version being added
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void addToColl(IProductVersion cat, int order)
    throws OculusException;

  /** Removes the given version object from the collection.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param cat the version being removed
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeFromColl(IProductVersion cat)
    throws OculusException;

}