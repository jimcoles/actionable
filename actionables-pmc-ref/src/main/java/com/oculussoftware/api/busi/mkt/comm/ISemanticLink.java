package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/** This interface represents a generic relationship between two business object.  It does maintain
* order, so the relationship is not commutative.  The type of relationship is defined by the LinkType.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ISemanticLink.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface ISemanticLink extends IRObject
{
  /** Returns the IIID of the parent object for this link
  *
  * @return the IIID of the parent object for this link
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getSourceObjectIID()
    throws ORIOException;

  /** Returns the IIID of the child object for this link
  *
  * @return the IIID of the child object for this link
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getDestObjectIID()
    throws ORIOException;

  /** Returns the type of this link.
  *
  * @return the type of this link
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public LinkKind getLinkType()
    throws ORIOException;

  /** Returns the order number of this link.
  *
  * @return the order number of this link
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public int getOrderNum()
    throws ORIOException;

  /** Sets the parent object of this relationship to the object of the IIID given.
  *
  * @param iid the IIID of the parent object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISemanticLink setSourceObjectIID(IIID iid)
    throws ORIOException;

  /** Sets the child object of this relationship to the object of the IIID given.
  *
  * @param iid the IIID of the child object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISemanticLink setDestObjectIID(IIID iid)
    throws ORIOException;

  /** Sets the type of this link.
  *
  * @param link the type of this link
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISemanticLink setLinkType(LinkKind link)
    throws ORIOException;

  /** Sets the order number of the link.  This can be useful if there are multiple objects
  * with the same relationship to the same parent object and the list of child object should
  * be in a particular order.
  *
  * @param num the order of this link
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public ISemanticLink setOrderNum(int num)
    throws ORIOException;
  
}