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

public class FileList extends BusinessObjectList implements IFileList, IPersistable
{
  protected String TABLE = "ATTACHMENT";
  protected String COL_TYPE = "TYPE";
  protected String COL_PARENTOBJECTID = "PAROBJECTID";
  protected String _sortby = "2";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public FileList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected FileList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT ATTACH."+COL_OBJECTID+ ", ATTACH.NAME, ATTACH.FILESIZEBYTES, ATTACH.CREATIONDATE, ATTACH.INCLUDEINDOCRPTS, APPTABLE.LASTNAME, APPTABLE.FIRSTNAME " +
           " FROM "+TABLE+" AS ATTACH LEFT OUTER JOIN APPUSER AS APPTABLE ON APPTABLE.OBJECTID = ATTACH.CREATORID"+
           " WHERE ATTACH."+COL_TYPE+"=1 AND ATTACH."+COL_PARENTOBJECTID+"="+getIID().getLongValue()+
           " ORDER BY "+_sortby;
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
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreFiles()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    FileList sortedList = new FileList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
    FileList fileList = null;
      fileList = new FileList();
      fileList.setIID(_iid);
      fileList._items = this._items;
      fileList.reset();
    return fileList;
  }
  
    /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
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
    
    if (args.containsKey(SortKind.KEY))
    {
      SortKind sort = (SortKind)args.get(SortKind.KEY);
      if (sort.equals(SortKind.BYNAME))
        _sortby = "2";
      if (sort.equals(SortKind.BYSIZE))
        _sortby = "3";
      if (sort.equals(SortKind.BYDATE))
        _sortby = "4";
      if (sort.equals(SortKind.BYCREATOR))
        _sortby = "5, 6";
    }
    return this;
  }

}