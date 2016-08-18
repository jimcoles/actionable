package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import java.sql.*;

/** IGroup represents a logical group of users.  This grouping is used primarily to
* implement permissions on a wide-scale.  Users, who are part of a group that has a
* particular permission, inherit that permission as well.  Therefore, you can create
* logical groups, such as administrators, marketers, engineers, customers, contractors,
* etc. and defined permissions to the groups rather than to each individual user.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IGroup.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IGroup extends IBusinessObject, IAccessor
{
  /** Returns the name of this group.
  *
  * @return the name of the group
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public String getName()
    throws OculusException;
  
  /** Returns the IIID of the parent group.  Note: Hierarchical groups is a concept
  * that never really got implemented, so this method has little to no effect on
  * anything.
  *
  * @return the IIID of the parent group
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParentGroupIID()
  	throws ORIOException;

  /** Returns a collection of users that belong to this group.
  *
  * @return collection of users
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUserColl getUsers()
	  throws OculusException;

  /** Returns a collection of users that belong to this group.
  *
  * @param editable if true, the users in the collection will be locked
  * @return collection of users
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUserColl getUsers(boolean editable)
  	throws OculusException;

  /** Sets the IIID of the parent group.  Note: Hierarchical groups is a concept
  * that never really got implemented, so this method has little to no effect on
  * anything.
  *
  * @param the IIID of the parent group
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IGroup setParentGroupIID(IIID id)
  	throws ORIOException;
}
