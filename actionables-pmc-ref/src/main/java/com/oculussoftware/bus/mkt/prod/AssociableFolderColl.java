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
* Filename:    FolderColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/



public class AssociableFolderColl extends BusinessObjectColl implements IFolderColl

{
  
  
  protected String TABLE = "FOLDER";  
  protected String COL_DELETESTATE = "DELETESTATE";
  protected String _contextid = null;
  
  public AssociableFolderColl() throws OculusException
  {
    super();
  }

  protected AssociableFolderColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return "SELECT fold.* FROM FOLDER fold "
      +" LEFT OUTER JOIN DISPOSITION disp ON disp.TARGETLOCATIONID=fold.OBJECTID "
      +" LEFT OUTER JOIN PRODCONTEXTITEM pct ON pct.DISPOSITIONID=disp.OBJECTID "  
      +" WHERE pct.PRODCONTEXTLISTID="+_contextid+" AND fold.DELETESTATE=1 AND"  
      +" fold.OBJECTID NOT IN (SELECT FOLDERID FROM FOLDERINPUTLINK WHERE MARKETINPUTID="+getIID(); 
           
  }

  protected String getClassName() { return "Folder"; }

  //----------------- IProductList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IFolder nextFolder()
    throws OculusException
  {
    return (IFolder)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreFolders()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    AssociableFolderColl sortedList = new AssociableFolderColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
      AssociableFolderColl catList = null;
      catList = new AssociableFolderColl();
      catList.setIID(_iid);
      catList._items = this._items;
      catList.reset();
    return catList;
  }
  
  
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
    
    if (args.containsKey("ContextID"))
    {
       _contextid = (String)args.get("ContextID");
    }

    return this;
  }
  
}