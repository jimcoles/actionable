package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** Description: Provides business objects with a handle into the context of their
* existence such as user, transaction, and environment info.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IObjectContext.java $
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

public interface IObjectContext extends ILockHolder, IObject, java.io.Serializable
{
	/** Returns the IEnvironment object associated with this context. Note: This method
  * is not being used.
  *
  * @return the environment of this context
  */
	public IEnvironment getEnvironment();
	
	/** Returns this context's connection to the CRM.  This connection is used as a way
  * of validating the identity of the user.
  *
  * @return the connection to the CRM
  */
  public ICRMConnection getConnection();

	/** Returns the reference to the ICRM object associated with this context.
  *
  * @return the reference to the CRM
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICRM getCRM()
    throws OculusException;

	/** Returns the reference to the repository object associated with this context.
  *
  * @return the reference to the repository
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRepository getRepository()
    throws OculusException;
    
	/** Sets this context's connection to the CRM.  This connection is used as a way
  * of validating the identity of the user.
  *
  * @param conn the connection to the CRM
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IObjectContext setConnection(ICRMConnection conn)
    throws ORIOException;

}