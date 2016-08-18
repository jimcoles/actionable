/*
* $Workfile: IRAttrAccessGroup.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An IRAttrAccessGroup is a repository representation of the attribute access group
* 
* 
* 
* @author Alok Pota
* @see com.oculussoftware.repos.bmr.BMAttrAccessGroup
*	@see com.oculussoftware.repos.bmr.BMModelElement
* @see com.oculussoftware.api.repi.IRModelElement
* @see com.oculussoftware.api.repi.IRAttrAccessGroup
*/

package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;


public interface IRAttrAccessGroup extends IRModelElement
{
  
  /** 
  * Set the order of the AttributeAccessGroup.
  * @return com.oculussoftware.api.repi.IRAttrAccessGroup 
  * @exception OculusException - There was an error accessing the default attribute group
  * 
  */
  public IRAttrAccessGroup setOrder(int i); 
  
  
  /** 
  * Get the order of the AttributeAccessGroup.
  * @return int
  * @exception OculusException - There was an error accessing the default attribute group
  * 
  */public int getOrder();
  
  /** 
  *  Get the maximum order available for storage.
  * @return int
  * @exception OculusException - There was an error accessing the default attribute group
  * 
  */
  public int getMaxOrder() throws OculusException;
}