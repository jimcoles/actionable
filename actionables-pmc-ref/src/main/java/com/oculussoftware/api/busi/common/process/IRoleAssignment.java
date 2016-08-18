package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.IUser;

/*
* $Workfile: IRoleAssignment.java $
* Description: Defines the methods needed for adding a RoleAssignment.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* The ObjectRoleAssignment table contains a list of IUser that are assigned 
* to a certain IProcessRole for a given IBusinessObject.  IRoleAssignment
* is responsible for maintaining the list. 
*
* @author Egan Royal
*/
public interface IRoleAssignment extends IRObject
{
  //Accessors
  
  /**
  * This accessor method returns the IIID for the Parent Object (the
  * IBusinessObject).
  * @return The IIID for the Parent Object.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getParObjectIID() throws ORIOException;
  
  /**
  * This accessor method returns the IIID for the IProcessRole Object (the
  * IProcessRole).
  * @return The IIID for the Parent Object.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getRoleIID() throws ORIOException;
  
  /**
  * This accessor method returns the IUser Object.
  * @return The IUser.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IUser getUserObject() throws OculusException;
  
  /**
  * This accessor method returns the IIID for the IUser Object.
  * @return The IIID for the IUser Object.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getUserIID() throws ORIOException;
  
  /**
  * This accessor method returns the int order number.  This method 
  * is not currently being used.  
  * @return The order number.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public int getOrderNum() throws ORIOException;
  
  /**
  * This accessor method returns true iff the IUser is a controlling 
  * user.  This method is not currently used.
  * @return The IIID for the Parent Object.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public boolean isController() throws ORIOException;
  
  //Mutators
  
  /**
  * This mutator method sets the Parent Object IIID.
  * @param iid The IIID of the Parent Object.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRoleAssignment setParObjectIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the ProcessRole Object IIID.
  * @param iid The IIID of the ProcessRole Object.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRoleAssignment setRoleIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the User Object IIID.
  * @param iid The IIID of the User Object.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRoleAssignment setUserIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the order number.  This method is not
  * currently being used.
  * @param num The order number.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRoleAssignment setOrderNum(int num) throws ORIOException;
  
  /**
  * This mutator method sets whether the RoleAssignment is a controller
  * or not.  This method is not currently being used.
  * @param c The order number.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRoleAssignment isController(boolean c) throws ORIOException;
  
}