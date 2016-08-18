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
* Filename:    FileList.java
* Date:        2/15/00
* Description: 
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
* ---             Cuihua Zhang    6/23/2000   changed sortby to column numbers
*                                             added the necessary column names in the select statement
*                                             to accomodate the sortby change
*/

public class ViewableFileList extends FileList implements IFileList, IPersistable
{
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public ViewableFileList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected ViewableFileList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT ATTACH."+COL_OBJECTID+ ", ATTACH.NAME, ATTACH.FILESIZEBYTES, ATTACH.CREATIONDATE, ATTACH.INCLUDEINDOCRPTS, APPTABLE.LASTNAME, APPTABLE.FIRSTNAME " +
           " FROM "+TABLE+" AS ATTACH LEFT OUTER JOIN APPUSER AS APPTABLE ON APPTABLE.OBJECTID = ATTACH.CREATORID"+
           " WHERE ATTACH."+COL_TYPE+"=1 AND ATTACH.INCLUDEINDOCRPTS=1 AND ATTACH."+COL_PARENTOBJECTID+"="+getIID().getLongValue()+
           " ORDER BY "+_sortby;
  }

  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    ViewableFileList sortedList = new ViewableFileList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    ViewableFileList fileList = null;
      fileList = new ViewableFileList();
      fileList.setIID(_iid);
      fileList._items = this._items;
      fileList.reset();
    return fileList;
  }
  
}