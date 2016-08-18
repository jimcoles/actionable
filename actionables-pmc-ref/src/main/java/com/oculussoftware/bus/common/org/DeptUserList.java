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
* Filename:    DeptUserList.java
* Date:        5/25/00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DeptUserList extends UserList
{
 /*
 * Change Activity
 *
 * Issue number    Programmer      Date        Description
 */
 //--------------------------- Public Constructors --------------------------
 /** Default constructor set the state to NEW and gets a list of empty properties */
 public DeptUserList() throws OculusException
 {
   super();
 }  
   
 protected DeptUserList (Comparator sortCrit) throws OculusException
 {
   super(sortCrit);
 }  
   
 public Object dolly() throws OculusException
 {
   DeptUserList usrColl = null;
   usrColl = new DeptUserList();
   usrColl.setIID(_iid);
   usrColl._items = this._items;
   usrColl.reset();
   return usrColl;
 }  
 
 //------------------------ Protected Methods --------------------------------
 protected String getLoadQuery()
 throws ORIOException
 {
   return "SELECT APPUSER.OBJECTID FROM "+
         "(APPUSER LEFT OUTER JOIN ENUMVALUE ON APPUSER.OBJECTID = ENUMVALUE.PAROBJECTID) "+
         "WHERE ENUMVALUE.VALUE = "+getIID().getLongValue();
 }    
     
 //------------------- IBusinessObjectColl Methods --------------------------
 
 public IRCollection setSort(Comparator sortCrit)
 throws OculusException
 {
   DeptUserList sortedList = new DeptUserList(sortCrit);
   sortedList._items.addAll(this._items);
   return sortedList;
 }      
}
