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
* Filename:    ConceptFolderColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the concepts for a given folder

*/


public class ProblemStatementFolderColl extends ProblemStatementColl implements IProblemStatementColl
{
  
  public ProblemStatementFolderColl() throws OculusException
  {
    super();
  }

  protected ProblemStatementFolderColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
 
  protected String getLoadQuery()
    throws ORIOException
  { 
    
    return "SELECT * FROM PROBLEMSTATEMENT WHERE FOLDERID="+getIID().getLongValue()+" AND DELETESTATE="+DeleteState.NOT_DELETED.getIntValue();    
  }
  
  
  public Object dolly() throws OculusException
  {
    ProblemStatementFolderColl featList = null;
      featList = new ProblemStatementFolderColl();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}