package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectList;

/** This interface represents any list of discussion topics.  It contains methods that
* maintain the order within the collection.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ICategoryList.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface ICategoryList extends IBusinessObjectList, ICategoryColl
{
  /** Sets the order number of the given category object.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param cat the category whose order is changing
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void setOrder(ICategory cat, int order)
    throws OculusException;
    
  /** Adds the given category object to the collection in the given
  * order.  It also adjusts the other objects in this collection around the change.
  *
  * @param cat the category being added
  * @param order the new order of the object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void addToColl(ICategory cat, int order)
    throws OculusException;

  /** Removes the given category object from the collection.  It also
  * adjusts the other objects in this collection around the change.
  *
  * @param cat the category being removed
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeFromColl(ICategory cat)
    throws OculusException;
 
}