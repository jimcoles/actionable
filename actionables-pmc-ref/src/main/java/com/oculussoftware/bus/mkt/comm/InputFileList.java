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
* ---             Cuihua Zhang    6/23/2000   added stdfeaturelink to the getLoadQuery function
*/

public class InputFileList extends FileList
{
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public InputFileList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected InputFileList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT ATT."+COL_OBJECTID+ ", ATT.NAME, ATT.FILESIZEBYTES, ATT.CREATIONDATE, ATT.INCLUDEINDOCRPTS, APP.LASTNAME, APP.FIRSTNAME " +
           " FROM (ATTACHMENT AS ATT LEFT OUTER JOIN APPUSER AS APP ON APP.OBJECTID = ATT.CREATORID) "+
           " LEFT OUTER JOIN FOLDERINPUTLINK AS CAT ON CAT.OBJECTID=ATT.PAROBJECTID "+
           " WHERE ATT."+COL_TYPE+"=1 AND CAT.MARKETINPUTID="+getIID().getLongValue()+
         
           " UNION" + 
         
           " SELECT ATTT." +COL_OBJECTID+ ", ATTT.NAME, ATTT.FILESIZEBYTES, ATTT.CREATIONDATE, ATTT.INCLUDEINDOCRPTS, APPP.LASTNAME, APPP.FIRSTNAME " +
           " FROM (ATTACHMENT AS ATTT LEFT OUTER JOIN APPUSER AS APPP ON APPP.OBJECTID = ATTT.CREATORID) "+
           " LEFT OUTER JOIN MARKETINPUT AS STD ON STD.OBJECTID=ATTT.PAROBJECTID "+
           " WHERE ATTT."+COL_TYPE+"=1 AND STD.OBJECTID="+getIID().getLongValue();
           
  }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    InputFileList sortedList = new InputFileList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    InputFileList fileList = null;
      fileList = new InputFileList();
      fileList.setIID(_iid);
      fileList._items = this._items;
      fileList.reset();
    return fileList;
  }
}