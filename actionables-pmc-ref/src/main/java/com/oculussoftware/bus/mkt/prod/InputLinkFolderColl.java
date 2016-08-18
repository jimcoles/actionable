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

Get all folderinputlinks for a specific marketinput

*/



public class InputLinkFolderColl extends FolderInputLinkColl implements IFolderInputLinkColl

{
  
  
  protected String TABLE = "FOLDERINPUTLINK";  
  protected String COL_DELETESTATE = "DELETESTATE";
  private String _firstID;
  private String _secondID;
  
  public InputLinkFolderColl() throws OculusException
  {
    super();
  }

  protected InputLinkFolderColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT * "+
           " FROM "+TABLE+           
           " WHERE MARKETINPUTID="+_secondID+" AND FOLDERID="+_firstID;
           
  }

  protected String getClassName() { return "FolderInputLink"; }

  //----------------- IProductList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IFolderInputLink nextFolderInputLink()
    throws OculusException
  {
    return (IFolderInputLink)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreFolderInputLinks()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    InputLinkFolderColl sortedList = new InputLinkFolderColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    InputLinkFolderColl catList = null;
      catList = new InputLinkFolderColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
  
  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    super.construct(context,args);
    
    _firstID = ((IIID)args.get("FolderID")).getLongValue()+"";
    _secondID = ((IIID)args.get("InputID")).getLongValue()+"";
    return this;
  }

}