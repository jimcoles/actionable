package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.*;

/** This interface represents an object that can be persisted to a repository.  The PoolMgr
* uses this as a flag to identify how to load and save the object.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IPersistable.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Made all of the void methods return an IPersistable.
* ---             Alok Pota       2/4/00      Added a wipe method to make things consistent with 1.1 notion of wipe & delete
*	---							Saleem Shafi		2/4/00			Made IPersistable extend IObject and IPoolable
*																							Removed wipe() because delete() signals a repository delete.
* ---             Saleem Shafi    2/16/00     Made delete() throw OculusException instead of ORIOException
*                                             because it will have to delete children.
*/


public interface IPersistable extends IObject, IPoolable
{
  /** Sets the persistent state of the object.
  *
  * @param state the persistent state of the object
  * @return this object
  */
  public IPersistable setPersState(PersState state);
  
  /** Returns the persistent state of the object.
  *
  * @return the persistent state of the object
  */
  public PersState getPersState();
  
  /** Sets the unique IIID of the object.
  *
  * @param objIID the IIID of the object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IPersistable setIID(IIID objIID) throws ORIOException;
  
  /** Returns the IIID of the object.
  *
  * @return objIID the IIID of the object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getIID() throws ORIOException; 
  
  /** Loads the object from the repository.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPersistable load( )
		throws OculusException;
    
  /** Saves the object to the repository.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPersistable save( )
    throws OculusException;
    
  /** Deletes the object from the repository.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IPersistable delete( )
    throws OculusException;
}