package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;

import java.util.*;

/**
* Filename:    RoleAssignmentRoleColl.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class RoleAssignmentRoleColl extends RoleAssignmentColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected IIID _roleiid;
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public RoleAssignmentRoleColl() throws OculusException
	{
    super();
	}//

  protected RoleAssignmentRoleColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+RoleAssignment.COL_PAROBJECTID+"="+getIID().getLongValue()+
           " AND ROLEID = "+_roleiid.getLongValue();
  }//

  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    RoleAssignmentRoleColl sortedList = new RoleAssignmentRoleColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  RoleAssignmentRoleColl roleAssList = null;
			roleAssList = new RoleAssignmentRoleColl();
			roleAssList.setIID(_iid);
			roleAssList._roleiid = _roleiid;
			roleAssList._items = this._items;
			roleAssList.reset();
		return roleAssList;
	}//
  
  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);
    
    if(args.containsKey("roleiid"))
      _roleiid = (IIID)args.get("roleiid");
      
    return this;
  }
}//end class