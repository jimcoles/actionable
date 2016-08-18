package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    ChildFolderColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the market inputs (SRCOBJECTID) associated with a given feature (DESTOBJECTID)

*/


public class ChildFolderColl extends FolderColl implements IFolderColl
{
  
  public ChildFolderColl() throws OculusException
  {
    super();
  }
 
  protected String getLoadQuery()
    throws ORIOException
  { 
    
    return "SELECT * FROM FOLDER WHERE FOLDERID="+getIID().getLongValue()+" AND DELETESTATE<>"+DeleteState.DELETED.getIntValue();    
  }
  
  
  public Object dolly() throws OculusException
  {
    ChildFolderColl featList = null;
      featList = new ChildFolderColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}