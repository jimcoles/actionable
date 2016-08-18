package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.api.busi.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    InputDiscussionList.java
* Date:        6/8/2000
* Description: This class will get all the discussiontopics for a feature, 
*              no matter which link they are attached to
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*
* DES00950        Cuihua Zhang    6/9/2000    newly created to suit the relationship between feature and catfeaturelink
* ----            Cuihua Zhang    6/15/2000   added more conditions for the query
* BUG01201        Cuihua Zhang    6/27/2000   changed table.column name to the column number
*                                             in the ORDER BY clause in the query
*/

public class InputDiscussionList extends DiscussionTopicList
{
   //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the product list */
  public InputDiscussionList() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  /*protected InputDiscussionList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }
*/

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT DIS."+COL_OBJECTID+", DIS.CREATIONDATE" +
           " FROM (DISCUSSIONTOPIC AS DIS LEFT OUTER JOIN APPUSER AS APP ON APP.OBJECTID = DIS.CREATORID) "+ 
           " LEFT OUTER JOIN FOLDERINPUTLINK AS CAT ON CAT.OBJECTID=DIS.PAROBJECTID "+
           " WHERE CAT.MARKETINPUTID="+getIID().getLongValue()+             
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND DIS." +COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue() +
           " UNION" +
           " SELECT DISC."+COL_OBJECTID+", DISC.CREATIONDATE" +
           " FROM (DISCUSSIONTOPIC AS DISC LEFT OUTER JOIN APPUSER AS APPP ON APPP.OBJECTID = DISC.CREATORID) "+ 
           " LEFT OUTER JOIN MARKETINPUT STD ON STD.OBJECTID=DISC.PAROBJECTID "+
           " WHERE STD.OBJECTID="+getIID().getLongValue()+             
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+
           " AND DISC." +COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();

  }

//------------------- IBusinessObjectList Methods --------------------------
 /* public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    InputDiscussionList sortedList = new InputDiscussionList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
 */ 
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    InputDiscussionList DiscussionList = null;
      DiscussionList = new InputDiscussionList();
      DiscussionList.setIID(_iid);
      DiscussionList._items = this._items;
      DiscussionList.reset();
    return DiscussionList;
  }
}