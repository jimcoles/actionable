package com.oculussoftware.bus.common.org;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    GroupUserColl
* Date:        
* Description: Gets the group list for a user
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class GroupUserColl extends GroupList implements IGroupList, IPersistable
{
	protected String TABLE = "USERGROUP";
	protected String COL_PARENTID = "PARVERSIONID";
	protected String COL_ORDERNUM = "ORDERNUM";
	protected String COL_DELETESTATE = "DELETESTATE";

	public final static java.lang.String TABLE_USER = "APPUSER";
	public final static java.lang.String COL_USERID = "USERID";
	public final static java.lang.String COL_GROUPID = "GROUPID";
	public final static java.lang.String TABLE_USERGROUPASC = "USERGROUPASC";

  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public GroupUserColl() throws OculusException
  {
  super();
  }

  protected GroupUserColl(Comparator sortCrit) throws OculusException
  {
  super(sortCrit);
  }

//----------------- IPoolable Methods ------------------------------------
/** Returns a duplicate IProductList object, but without the ObjectContext */
public Object dolly() throws OculusException
{
  GroupUserColl grpList = null;
  grpList = new GroupUserColl();
  grpList.setIID(_iid);
  grpList._items = this._items;
  grpList.reset();
  return grpList;
}

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
  throws ORIOException
  {
  /**return " SELECT UID.* FROM "+TABLE_USER+" UID, " + TABLE + " UGA " +
       "WHERE UID." + COL_OBJECTID + " = " + "UGA." + COL_USERID +
       " AND UID."+ COL_DELETESTATE+" <> " + DeleteState.DELETED.getIntValue() +
       " AND UGA." + COL_GROUPID + "="+getIID().getLongValue();
 */
    return "SELECT DISTINCT GID." + COL_OBJECTID + " FROM " + TABLE + " GID, "  + TABLE_USERGROUPASC + " UGA "  + 
      "WHERE GID." + COL_OBJECTID + " = " + "UGA." + COL_GROUPID + 
      " AND UGA." + COL_USERID + " = " + getIID();
  }
}
