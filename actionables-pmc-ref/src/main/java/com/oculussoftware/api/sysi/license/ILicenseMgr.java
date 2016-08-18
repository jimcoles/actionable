package com.oculussoftware.api.sysi.license;

import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ILicenseMgr.java $
* Description: The License Manager interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* The License Manager interface.  Defines the methods necessary for 
* maintaining user licenses.  The License Manager is a Singleton that
* contains the LicenseKeys
*
* @author Egan Royal
*/
public interface ILicenseMgr
{
  /**
  * This method adds an IModule to the ModuleCollection maintained by this
  * object. 
  * @param mk The ModuleKind.
  * @param key The License Key.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public ILicenseMgr addModule(ModuleKind mk, String key) throws OculusException;  
  
  /**
  * This method takes a ModuleKind and returns the number of licensed
  * users in the database for that Module.
  * @param mk The ModuleKind.
  * @return The number of licensed users.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int getActualNumUsers(ModuleKind mk) throws OculusException; 
  
  /**
  * This method returns a reference to the Key Generator.
  * @return The Key Generator.
  */   
  public IKeyGen getKeyGen();
  
  /**
  * This method takes a ModuleKind and returns the IModule from the Module Collection
  * @param mk The ModuleKind.
  * @return The IModule for this ModuleKind, null if it does not exist.
  */  
  public IModule getModule(ModuleKind mk);
  
  /**
  * This method returns the Module Collection that is maintained by this object.
  * @return The Module Collection.
  */  
  public IModuleColl getModuleColl(); 
   
  /**
  * This method takes a ModuleKind and returns the number of user licenses for
  * the Module.
  * @param mk The ModuleKind.
  * @return The number of user licenses. 
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int getNumUsers(ModuleKind mk) throws OculusException;
  
  /**
  * This method returns true iff there is at least one Module in the ModuleCollection.
  * @return true iff there is at least one Module in the ModuleCollection
  */  
  public boolean hasModules();  
  
  /**
  * This method takes a ModuleKind and returns true iff the system is licensed
  * for the Module.
  * @param mk The Modulekind.
  * @return true iff the system is licensed for the Module
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean isLicensed(ModuleKind mk) throws OculusException;
  
  /**
  * This method takes a ModuleKind and removes the IModule from the
  * ModuleCollection.
  * @param mk The ModuleKind.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */  
  public ILicenseMgr removeModule(ModuleKind mk) throws OculusException;
  
  /**
  * This method takes a ModuleKind and a key and updates the IModule in the
  * ModuleCollection.
  * @param mk The ModuleKind.
  * @param key The Key.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */  
  public ILicenseMgr updateModule(ModuleKind mk, String key) throws OculusException;  
} //
