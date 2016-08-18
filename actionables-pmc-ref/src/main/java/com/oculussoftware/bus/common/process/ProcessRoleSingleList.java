package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.bus.BusinessObject;
import java.util.*;

/**
* Filename:    ProcessRoleSingleList.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessRoleSingleList extends ProcessRoleList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProcessRoleSingleList() throws OculusException
	{
    super();
	}//

  protected ProcessRoleSingleList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE DELETESTATE ="+DeleteState.NOT_DELETED.getIntValue()+
           " AND "+ProcessRole.COL_MULTIPLE+"=0"+
           " ORDER BY "+_sortby;
    
  }//
  
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  ProcessRoleSingleList roleList = null;
			roleList = new ProcessRoleSingleList();
			roleList.setIID(_iid);
			roleList._items = this._items;
			roleList.reset();
		return roleList;
	}//
  
  
  
  
  
}//end class