package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import java.sql.Timestamp;

/*
* $Workfile: IProcessRole.java $
* Description: Defines the methods needed to maintain ProcessRoles.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/**
* The ProcessRole table contains all of the roles for the system. i.e.
* Enigineering Manager.  IProcessRole defines the methods necessary for 
* ProcessRole maintanence.
*
* @author Egan Royal
*/
public interface IProcessRole extends IRObject, IRModelElement
{
  /**
  * This mutator method sets the IProcessRole as a multi-user role or not. 
  * @param bln .
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRModelElement isMultiUser(boolean bln)
    throws ORIOException;
  
  /**
  * This mutator method sets the whether or not the IprocessRole inherits 
  * from its Parent.  This method is not currently being used. 
  * @param bln .
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IRModelElement inheritParent(boolean bln)
    throws ORIOException;
    
  /**
  * This method returns true iff the IProcessRole is a multi-user role. 
  * @return true -- iff the IProcessRole may have more than one IUser
  * assigned a given BusinessObject
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public boolean isMultiUser()
    throws ORIOException;
  
  /**
  * This method returns true iff the IProcessRole inherits from its
  * parent.  This method is not currently being used 
  * @return true -- iff the IProcessRole inherits from its parent
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public boolean inheritParent()
    throws ORIOException;
  
  
}