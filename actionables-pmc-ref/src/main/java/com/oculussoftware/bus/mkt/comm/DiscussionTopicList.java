package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    DiscussionTopicList.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DiscussionTopicList extends BusinessObjectList implements IDiscussionTopicList, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ---             Cuihua Zhang    6/8/2000    added the construct method so that we only need
  *                                             one discussionTopicList instead of different ones
  *                                             for transactions, topics, pros, and cons
  *
  */
  protected String TABLE = "DISCUSSIONTOPIC";
  protected String COL_PAROBJECTID = "PAROBJECTID";
  protected String COL_PARDISCUSSIONID = "PARDISCUSSIONID";

  protected String COL_DELETESTATE = "DELETESTATE";
  protected String _where = "";



	//----------------------------- Public Constructor -------------------------
	/**
  *
  */
	public DiscussionTopicList() throws OculusException
	{
    super();
	}//

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue() +
           " AND "+DiscussionTopic.COL_ISROOT+"= 1"+

           " "+_where+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+ " "+
           " ORDER BY CREATIONDATE DESC";
//;
  }//

	//----------------- IDiscussionTopicList Methods ------------------------------------
	/**
	*
 	*/
  public IDiscussionTopic nextDiscussionTopic() throws OculusException
	{
//    IDiscussionTopic nextTopic = null;
//    while (nextTopic == null && hasMoreDiscussionTopics())
//    {
//      IIID topicIID = (IIID)next();  
//      nextTopic = (IDiscussionTopic)CRM.getInstance().getCompObject(getObjectContext(),"DiscussionTopic",topicIID,isLocked());
//    }//end while
		return (IDiscussionTopic)next();
	}//
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreDiscussionTopics()
	{
		return hasNext();
	}//
  
  //----------------------- IRList ---------------------------------
  
  public void   add(int index, Object obj)
    throws AccessException, ORIOException
  {}
    
  public Object get(int index)
 //   throws AccessException, ORIOException
  { return null; }

  public Object  remove(int index)
//    throws AccessException, ORIOException
  { return null; }

  public Object replace(int index, Object element)
    throws AccessException, ORIOException
  { return null; }
  
  public int indexOf(Object element)
  { return 0; }
  
  
  //-------------------------- BusinessObjectList ------------------------
  
  public String getClassName() { return "DiscussionTopic"; }
  
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  { return this; }
	
//----------------- IPoolable Methods ------------------------------------
	
	public Object dolly() throws OculusException
	{
	  DiscussionTopicList topicList = null;
			topicList = new DiscussionTopicList();
			topicList.setIID(_iid);
			topicList._items = this._items;
			topicList.reset();
		return topicList;
  }//
  
  
  
  
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);
    
 /*   if (args.containsKey(SortKind.KEY))
    {
      SortKind sort = (SortKind)args.get(SortKind.KEY);
      if (sort.equals(SortKind.BYNAME))
        _sortby = "rev.Name";
      if (sort.equals(SortKind.BYSTATE))
        _sortby = "link.STATEID";
      if (sort.equals(SortKind.BYPRIORITY))
        _sortby = "enum.ORDERNUM";
    }
    
    */

    if (args.containsKey("topicKind"))
    {
       String kind = (String) args.getString("topicKind");
       if (kind.equals("Disc"))
          _where += " AND TOPICKIND = "+TopicKind.NORMAL.getIntValue()+
                    " AND "+DiscussionTopic.COL_ISROOT+"= 1";
       if (kind.equals("Trans"))
          _where += " AND "+DiscussionTopic.COL_TOPICKIND+"="+TopicKind.TRANSACTIONCOMMENT.getIntValue();  
       if (kind.equals("Pros"))
          _where += " AND TOPICKIND = "+TopicKind.PRO.getIntValue();
       if (kind.equals("Cons"))
          _where += " AND TOPICKIND = "+TopicKind.CON.getIntValue();
       if (kind.equals("DiscTrans"))
          _where += "";
     }

     return this;
  }
}//end class DiscussionTopic