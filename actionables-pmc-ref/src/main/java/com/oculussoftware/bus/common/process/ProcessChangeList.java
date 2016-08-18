package com.oculussoftware.bus.common.process;

import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;

import java.util.*;

/**
* Filename:    ProcessChangeList.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class ProcessChangeList extends ReposObjectList implements IProcessChangeList
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public ProcessChangeList() throws OculusException
  {
    super();
    _sortby = ProcessChange.COL_CHANGEDATE+" DESC";
  }
  
  protected ProcessChangeList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
    _sortby = ProcessChange.COL_CHANGEDATE+" DESC";
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return "SELECT * FROM "+ProcessChange.TABLE+
            " WHERE "+ProcessChange.COL_CHGOBJECTID+"="+_iid.getLongValue()+
            " ORDER BY "+_sortby;
  }//
  
  protected String getClassName() { return "ProcessChange"; }
  
  //------------IRProcessChangeColl
  
  public IProcessChange nextProcessChange() throws OculusException
  { return (IProcessChange)next(); }
  
  public boolean hasMoreProcessChanges()
  { return hasNext(); }
  
  
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    ProcessChangeList sortedList = new ProcessChangeList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
  
  //------------------------------ IPersistable ----------------------
  
  public Object dolly() throws OculusException
  { 
    ProcessChangeList pcColl = null;
      pcColl = new ProcessChangeList();
      pcColl.setIID(_iid);
      pcColl._items.addAll(this._items);
      pcColl.reset();
    return pcColl;
  }//end dolly
}