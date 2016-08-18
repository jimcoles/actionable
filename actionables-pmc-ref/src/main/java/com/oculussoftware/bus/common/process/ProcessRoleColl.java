package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.bus.BusinessObject;
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
public class ProcessRoleColl extends ReposObjectColl implements IProcessRoleColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "PROCESSROLE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProcessRoleColl() throws OculusException
	{
    super();
	}//

  protected ProcessRoleColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE DELETESTATE ="+DeleteState.NOT_DELETED.getIntValue();
    
  }//

  protected String getClassName () { return "Role"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public IProcessRole nextProcessRole() throws OculusException
	{
		return (IProcessRole)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreProcessRoles()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    ProcessRoleColl sortedList = new ProcessRoleColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  ProcessRoleColl roleList = null;
			roleList = new ProcessRoleColl();
			roleList.setIID(_iid);
			roleList._items = this._items;
			roleList.reset();
		return roleList;
	}//
}//end class