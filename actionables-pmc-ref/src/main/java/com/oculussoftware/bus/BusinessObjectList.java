package com.oculussoftware.bus;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    BusinessObjectList.java
* Date:        
* Description: Handles a list of business objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ---             Saleem Shafi    2/17/00     Changed COL_OBJECTID to 1 in the load() method to make it more generic.
*/

abstract public class BusinessObjectList 
  extends com.oculussoftware.repos.ReposObjectList
  implements IBusinessObjectList, IPersistable
{
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the list */
  public BusinessObjectList() throws OculusException
  {
    super();
  }

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the list */
  protected BusinessObjectList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

}