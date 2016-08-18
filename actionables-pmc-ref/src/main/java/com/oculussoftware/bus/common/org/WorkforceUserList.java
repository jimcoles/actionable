package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    WorkforceUserList.java
* Date:        5/25/00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class WorkforceUserList extends UserList
{
 /*
 * Change Activity
 *
 * Issue number    Programmer      Date        Description
 */
 //--------------------------- Public Constructors --------------------------
 /** Default constructor set the state to NEW and gets a list of empty properties */
 public WorkforceUserList() throws OculusException
 {
   super();
 }  
   
 protected WorkforceUserList (Comparator sortCrit) throws OculusException
 {
   super(sortCrit);
 }  
   
 public Object dolly() throws OculusException
 {
   WorkforceUserList usrColl = null;
   usrColl = new WorkforceUserList();
   usrColl.setIID(_iid);
   usrColl._items = this._items;
   usrColl.reset();
   return usrColl;
 }  
 
 //------------------------ Protected Methods --------------------------------
 protected String getLoadQuery()
 throws ORIOException
 {
   return "SELECT APPUSER.* FROM "+
     "((APPUSER LEFT OUTER JOIN OBJECTROLEASSIGN ON OBJECTROLEASSIGN.USERID = APPUSER.OBJECTID) "+
	      "LEFT OUTER JOIN PRODUCTVERSION ON OBJECTROLEASSIGN.PAROBJECTID = PRODUCTVERSION.OBJECTID) "+
     "WHERE PRODUCTVERSION.OBJECTID = "+getIID()+ 
     "AND OBJECTROLEASSIGN.ROLEID = "+IDCONST.VERSIONTEAMROLE+
			" ORDER BY APPUSER.LASTNAME, APPUSER.FIRSTNAME"; 
 }
     
 //------------------- IBusinessObjectColl Methods --------------------------
 
 public IRCollection setSort(Comparator sortCrit)
 throws OculusException
 {
   WorkforceUserList sortedList = new WorkforceUserList(sortCrit);
   sortedList._items.addAll(this._items);
   return sortedList;
 }      
}
