package com.oculussoftware.bus.mkt.prod;


import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.bus.*;
import com.oculussoftware.rdb.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    ConceptColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/



public class ReactionColl extends BusinessObjectColl implements IReactionColl

{
  
  
  protected String TABLE = "REACTION";  
  protected String COL_DELETESTATE = "DELETESTATE";

  public ReactionColl() throws OculusException
  {
    super();
  }

  protected ReactionColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+           
           " WHERE "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue();
           
  }

  protected String getClassName() { return "Reaction"; }

  //----------------- IProductList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IReaction nextReaction()
    throws OculusException
  {
    return (IReaction)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreReactions()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ReactionColl sortedList = new ReactionColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    ReactionColl catList = null;
      catList = new ReactionColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
}