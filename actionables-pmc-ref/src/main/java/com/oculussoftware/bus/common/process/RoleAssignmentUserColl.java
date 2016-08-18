package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;

import java.util.*;

/**
* Filename:    RoleAssignmentUserColl.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class RoleAssignmentUserColl extends RoleAssignmentColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected IIID _useriid;
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public RoleAssignmentUserColl() throws OculusException
	{
    super();
	}//

  protected RoleAssignmentUserColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+RoleAssignment.COL_PAROBJECTID+"="+getIID().getLongValue()+
           " AND USERID = "+_useriid.getLongValue();
  }//

  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    RoleAssignmentUserColl sortedList = new RoleAssignmentUserColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  RoleAssignmentUserColl roleAssList = null;
			roleAssList = new RoleAssignmentUserColl();
			roleAssList.setIID(_iid);
			roleAssList._useriid = _useriid;
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
    
    if(args.containsKey("useriid"))
      _useriid = (IIID)args.get("useriid");
      
    return this;
  }
}//end class