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
* Filename:    WorkforceGroupColl.java
* Date:        
* Description:
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class WorkforceGroupColl extends GroupColl implements IGroupColl, IPersistable
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
	public WorkforceGroupColl() throws OculusException
	{
	super();
	}
  protected WorkforceGroupColl(Comparator sortCrit) throws OculusException
  {
	super(sortCrit);
  }  
//----------------- IPoolable Methods ------------------------------------
/** Returns a duplicate IProductList object, but without the ObjectContext */
public Object dolly() throws OculusException
{
	WorkforceGroupColl grpList = null;
	grpList = new WorkforceGroupColl();
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
 		return "SELECT DISTINCT GID." + COL_OBJECTID + 
		   " FROM "+TABLE+" GID, "+TABLE_USERGROUPASC+" UGA, "+TABLE_USER+" UID, OBJECTROLEASSIGN ORA"+ 
 			      " WHERE GID."+COL_OBJECTID+" = "+"UGA."+COL_GROUPID+ 
 			      " AND UGA."+COL_USERID+" = "+"UID."+COL_OBJECTID+ 
			" AND ORA.PAROBJECTID="+_iid.getLongValue()+
      " AND ORA.ROLEID="+IDCONST.VERSIONTEAMROLE.getLongValue()+
      " AND ORA.USERID=UID.OBJECTID"+
   		      " AND UID."+COL_DELETESTATE+" <> "+DeleteState.DELETED.getIntValue();
	}
}
