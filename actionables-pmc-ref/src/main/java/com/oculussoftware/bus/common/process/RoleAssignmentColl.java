package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;

import java.util.*;

/**
* Filename:    ProcessRoleColl.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class RoleAssignmentColl extends ReposObjectColl implements IRoleAssignmentColl, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "OBJECTROLEASSIGN";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public RoleAssignmentColl() throws OculusException
	{
    super();
	}//

  protected RoleAssignmentColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+RoleAssignment.COL_PAROBJECTID+"="+getIID().getLongValue()+" ";
  }//

  protected String getClassName () { return "RoleAssignment"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public IRoleAssignment nextRoleAssignment() throws OculusException
	{
		return (IRoleAssignment)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreRoleAssignments()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    RoleAssignmentColl sortedList = new RoleAssignmentColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  RoleAssignmentColl roleAssList = null;
			roleAssList = new RoleAssignmentColl();
			roleAssList.setIID(_iid);
			roleAssList._items = this._items;
			roleAssList.reset();
		return roleAssList;
	}//
}//end class