package com.oculussoftware.api.sysi.license;

import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: IKeyGen.java $
* Description: The methods necessary to implement a Key Generator.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Key Generator/Decryptor.   
*
* @author Egan Royal
*/
public interface IKeyGen
{   
  /**
  * This accessor method takes a key and returns the ModuleKind.
  * @param key The key.
  * @return The ModulueKind for the key.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public ModuleKind  getModuleKind(String key) throws OculusException;
  
  /**
  * This accessor method takes a key and returns the Version number.
  * @param key The key.
  * @return The Version number.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public int  getVersion(String key) throws InvalidKeyException;
  
  /**
  * This accessor method takes a key and returns the Customer ID.
  * @param key The key.
  * @return The Customer ID.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public long getCustomerID(String key) throws InvalidKeyException;
  
  /**
  * This accessor method takes a key and returns the number of licensed users.
  * @param key The key.
  * @return The number of licensed users.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public int  getNumberOfUsers(String key) throws InvalidKeyException;
  
  /**
  * This accessor method takes a key and returns the expiration month.
  * @param key The key.
  * @return The expiration month.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public int  getExpirationMonth(String key) throws InvalidKeyException;
  
  /**
  * This accessor method takes a key and returns the expiration day.
  * @param key The key.
  * @return The expiration day.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public int  getExpirationDay(String key) throws InvalidKeyException;
  
  /**
  * This accessor method takes a key and returns the expiration year.
  * @param key The key.
  * @return The expiration year.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public int  getExpirationYear(String key) throws InvalidKeyException;
  //Validation
  
  /**
  * This method returns true iff the key is a valid key, otherwise the method
  * will throw an InvalidKeyException.
  * @param key The key.
  * @return true iff the key is a valid key.
  * @exception com.oculussoftware.api.sysi.license.InvalidKeyException  
  */
  public boolean validate(String key) throws InvalidKeyException;
  
  //Creation
  
  /**
  * This method creates and returns a new key.
  * @param mk The ModulueKind 
  * @param intVersion The Version number
  */
  public String createKey(ModuleKind mk, int intVersion, int intShifter, long lngCustID, int intNumUsers, int intMonth, int intDay, int intYear)
     throws InvalidKeyException;
}//