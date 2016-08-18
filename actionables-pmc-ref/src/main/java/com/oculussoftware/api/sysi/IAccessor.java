package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** This interface represents an object that can be thought of as accessing another
* object.  It is used for access control purposes.
*
* @author Jim Coles
*/

/*
* $Workfile: IAccessor.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ----            J. Coles        4/4/2000    Added getIID() to allow generic access control logic
*                                             without downcasting to IUser, etc.
*/
public interface IAccessor
{
  /** Returns the unique IIID of this object.  getIID() could not be used because of
  * the inability of VisualAge to resolve the conflict with IPersistable.getIID().
  *
  * @return the IIID of this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getAccessorIID() throws ORIOException; 
}