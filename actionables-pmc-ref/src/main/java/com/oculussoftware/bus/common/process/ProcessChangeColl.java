package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;

import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.*;

import java.util.*;
import java.sql.*;

/**
* Filename:    ProcessChangeColl.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessChangeColl extends ReposObjectColl implements IProcessChangeColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public ProcessChangeColl() throws OculusException
  {
    super();
  }
  
  protected ProcessChangeColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return "SELECT * FROM "+ProcessChange.TABLE+
            " WHERE "+ProcessChange.COL_CHGOBJECTID+"="+_iid.getLongValue()+
            " ORDER BY "+ProcessChange.COL_CHANGEDATE+" DESC";
  }//
  
  protected String getClassName() { return "ProcessChange"; }
  
  //------------IRProcessChangeColl
  
  public IProcessChange nextProcessChange() throws OculusException
  { return (IProcessChange)next(); }
  
  public boolean hasMoreProcessChanges()
  { return hasNext(); }
  
  
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    ProcessChangeColl sortedList = new ProcessChangeColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
  
  //------------------------------ IPersistable ----------------------
  
  public Object dolly() throws OculusException
  { 
    ProcessChangeColl pcColl = null;
      pcColl = new ProcessChangeColl();
      pcColl.setIID(_iid);
      pcColl._items.addAll(this._items);
      pcColl.reset();
    return pcColl;
  }//end clone
  
}