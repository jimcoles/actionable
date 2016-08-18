package com.oculussoftware.api.sysi.license;

import com.oculussoftware.api.sysi.*;
import java.sql.Timestamp;

/*
* $Workfile: IModule.java $
* Description: Wraps up the information for the Module.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* This class wraps up the license information for the Module (Accolades 
* or Compass).  The class is also responsible for maintaining the
* information in the database.
*
* @author Egan Royal
*/
public interface IModule
{
  /**
  * This method returns the Key.
  * @return The Key.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String     getKey() throws OculusException;
  
  /**
  * This method retrieves and returns the Customer ID from the Key. 
  * @return The Customer ID.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public long       getCustomerID() throws OculusException;
  
  /**
  * This method retrieves and returns the ModuleKind from the Key.
  * @return The ModuleKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */  
  public ModuleKind getModuleKind() throws OculusException;    
  
  /**
  * This method retrieves and returns the number of licensed users from the Key.
  * @return The number of licensed users.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int        getNumUsers() throws OculusException; 
  
  /**
  * This method retrieves and returns the Expiration Date from the Key.
  * @return The Expiration Date.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public Timestamp  getExpirationDate() throws OculusException;
  
  /**
  * This method retrieves and returns the Expiration Month from the Key.
  * @return The Expiration Month.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int        getExpirationMonth() throws OculusException;
  
  /**
  * This method retrieves and returns the Expiration Day from the Key.
  * @return The Expiration Day.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int        getExpirationDay() throws OculusException;
  
  /**
  * This method retrieves and returns the Expiration Year from the Key.
  * @return The Expiration Year.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int        getExpirationYear() throws OculusException;
  
  /**
  * This method retrieves and returns the Version number from the Key.
  * @return The Version number.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int        getModuleVersion() throws OculusException;
  
  /**
  * This method returns true iff the Module is Expired.
  * @return true iff the Module is Expired.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean    isExpired() throws OculusException;
  
  /**
  * This method returns true iff the Module has no Expiration Date.
  * @return true iff the Module has no Expiration Date.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean    noExpirationDate() throws OculusException;
  
  /**
  * This method inserts the Module row into the database.
  * @param systemcontext The system context.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule insert(IObjectContext systemcontext) throws OculusException;
  
  /**
  * This method updates the Module row into the database.
  * @param systemcontext The system context.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule update(IObjectContext systemcontext) throws OculusException;
  
  /**
  * This method deletes the Module row from the database.
  * @param systemcontext The system context.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IModule delete(IObjectContext systemcontext) throws OculusException;
}//