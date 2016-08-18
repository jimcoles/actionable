package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    FileColl.java
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
*/

public class FileColl extends BusinessObjectColl implements IFileColl, IPersistable
{
  protected String TABLE = "ATTACHMENT";
  protected String COL_TYPE = "TYPE";
  protected String COL_PARENTOBJECTID = "PAROBJECTID";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FileColl() throws OculusException
	{
    super();
	}

  protected FileColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_TYPE+"=1 AND "+COL_PARENTOBJECTID+"="+getIID().getLongValue()+" ";
  }

  protected String getClassName() { return "File"; }
  
	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IAttachment nextFile()
		throws OculusException
	{
		return (IAttachment)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreFiles()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FileColl sortedList = new FileColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		FileColl fileList = null;
			fileList = new FileColl();
			fileList.setIID(_iid);
			fileList._items = this._items;
			fileList.reset();
    return fileList;
	}
}