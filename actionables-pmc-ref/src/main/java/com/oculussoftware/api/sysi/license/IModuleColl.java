package com.oculussoftware.api.sysi.license;

import com.oculussoftware.api.sysi.*;
/*
* $Workfile: IModuleColl.java $
* Description: A Collection of IModules.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* A Collection of IModules.
*
* @author Egan Royal
*/
public interface IModuleColl
{
  /**
  * This method returns the next IModule in the List (iterator).  It simply
  * calls the next() method and casts the result to an IModule.
  * @return The next IModule.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule nextModule() throws OculusException;
  
  /**
  * This method returns the next element in the List (iterator).  
  * @return The next element.
  */
  public Object next();
  
  /**
  * This method returns true iff the List (iterator) has more elements.  This
  * method delegates to the hasNext() method.
  * @return true iff the List (iterator) has more elements.
  */
  public boolean hasMoreModules();
  
  /**
  * This method returns true iff the List (iterator) has more elements.  
  * @return true iff the List (iterator) has more elements.
  */
  public boolean hasNext();
  
  /**
  * This method resets the iterator to the beginning of the List.
  * @return this
  */
  public IModuleColl reset();
  
  /**
  * This method takes a system context and retrieves all of the Modules
  * from the database and populates the Collection.
  * @param systemcontext The system context.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModuleColl putAll(IObjectContext systemcontext) throws OculusException;
  
  /**
  * This method returns true iff there is an IModule in the Collection that corresponds to 
  * the given ModuleKind.  The method delegates to the containsKey method on the Collection. 
  * @param mk The ModuleKind.
  * @return true iff there is an IModule in the Collection that corresponds to 
  * the given ModuleKind.
  */
  public boolean containsModule(ModuleKind mk);
  
  /**
  * This method returns an IModule given a ModuleKind.  The method delegates to the get
  * method on the Collection.
  * @param mk The ModuleKind.
  * @return The IModule.
  */
  public IModule getModule(ModuleKind mk);
  
  /**
  * This method adds an IModule to the Collection.
  * @param systemcontext The system context.
  * @param mk The ModuleKind.
  * @param key The Key.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule add(IObjectContext systemcontext, ModuleKind mk, String key) 
    throws OculusException;
  
  /**
  * This method removes an IModule from the Collection.
  * @param systemcontext The system context.
  * @param mk The ModuleKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule remove(IObjectContext systemcontext, ModuleKind mk) 
    throws OculusException;
  
  /**
  * This method updates an IModule in the Collection.
  * @param systemcontext The system context.
  * @param mk The ModuleKind.
  * @param key The Key.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */  
  public IModule update(IObjectContext systemcontext, ModuleKind mk, String key) 
    throws OculusException;
}//