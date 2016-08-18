package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import java.sql.*;

/** Represents a collection of organizations.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IOrganizationColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IOrganizationColl extends IBusinessObjectColl
{
  /** Returns the next organization in the collection
  *
  * @return the next organization in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IOrganization nextOrganization() throws OculusException;
  
  /** Returns whether or not the collection contains any more organizations.
  *
  * @return true if there are more organizations, false otherwise
  */
  public boolean hasMoreOrganizations();
  
}