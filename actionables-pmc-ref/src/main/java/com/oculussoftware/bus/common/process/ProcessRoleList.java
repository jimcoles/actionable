package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.bus.BusinessObject;
import java.util.*;

/**
* Filename:    ProcessRoleList.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessRoleList extends ReposObjectList implements IProcessRoleList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "PROCESSROLE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ProcessRoleList() throws OculusException
	{
    super();
    _sortby = "NAME";
	}//

  protected ProcessRoleList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
    _sortby = "NAME";
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE DELETESTATE ="+DeleteState.NOT_DELETED.getIntValue()+
           " ORDER BY "+_sortby;
    
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
    ProcessRoleList sortedList = new ProcessRoleList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------
	public Object dolly() throws OculusException
	{
	  ProcessRoleList roleList = null;
			roleList = new ProcessRoleList();
			roleList.setIID(_iid);
			roleList._items = this._items;
			roleList.reset();
		return roleList;
	}//
  
  /**
  *  Override the load for order.
  */
  public IPersistable load()
    throws OculusException
  {
    super.load();
    int size = _items.size();
    //mkt mgr
    int i = _items.indexOf(IDCONST.MKTMGRROLE.getIIDValue());
    if(i != -1 && size > 0)
    { Object o = _items.get(i); _items.remove(i); _items.add(0,o); }
    //eng mgr
    i = _items.indexOf(IDCONST.ENGMGRROLE.getIIDValue());
    if(i != -1 && size > 1)
    { Object o = _items.get(i); _items.remove(i); _items.add(1,o); }
    //qa mgr
    i = _items.indexOf(IDCONST.QAMGRROLE.getIIDValue());
    if(i != -1 && size > 2)
    { Object o = _items.get(i); _items.remove(i); _items.add(2,o); }
    reset();
    return this;
  }
  
}//end class