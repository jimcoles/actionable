package com.oculussoftware.api.sysi;

/** The base object type for all objects retrieve from the CRM.  Most
* methods are inherited from IObjectInfo since many system objects
* are not updateable.  Sub-interfaces of IObject will declare mutator
*  methods as appropriate.
*
* @author Jim Coles
*/

/*
* $Workfile: IObject.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Changed setConnection method to return an IObjectContext
* ---             Saleem Shafi    2/7/00      Added OculusException to getCRM()
* ---             Saleem Shafi    3/3/00      Added getRepository()
*/
public interface IObject
{
  /** Returns globally unique ID of resource.
  *
  * @return this object's GUID
  */
  public IGUID getGUID();
  
  /** Returns this object's context.
  *
  * @return this object's context
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IObjectContext getObjectContext()
    throws com.oculussoftware.api.repi.ORIOException;
  
  /** Returns this object's context.
  *
  * @param this object's context
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IObject setObjectContext(IObjectContext context)
    throws com.oculussoftware.api.repi.ORIOException;
}