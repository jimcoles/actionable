package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import java.sql.*;

/** Represents a collection of users.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IUserColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IUserColl extends IBusinessObjectColl
{
  /** Returns the next user in the collection
  *
  * @return the next user in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser nextUser() throws OculusException;
  
  /** Returns whether or not the collection contains any more users.
  *
  * @return true if there are more users, false otherwise
  */
  public boolean hasMoreUsers();
  
}