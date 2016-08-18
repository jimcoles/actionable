package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import java.sql.*;

/** Represents a collection of groups.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IGroupColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IGroupColl extends IBusinessObjectColl
{
  /** Returns the next group in the collection
  *
  * @return the next group in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IGroup nextGroup() throws OculusException;
  
  /** Returns whether or not the collection contains any more groups.
  *
  * @return true if there are more groups, false otherwise
  */
  public boolean hasMoreGroups();
}