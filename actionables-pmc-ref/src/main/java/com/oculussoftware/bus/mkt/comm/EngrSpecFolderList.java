package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import com.oculussoftware.repos.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    EngrSpecFolderList.java
* Date:        2/14/00
* Description: Handles a list of IEngrSpecFolder objects
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

public class EngrSpecFolderList extends ReposObjectList implements IEngrSpecFolderList, IPersistable
{
 

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public EngrSpecFolderList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected EngrSpecFolderList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {

    return " SELECT * FROM ENGRDOCCONFIG";
  }

  protected String getClassName () { return "EngrSpecFolder"; }
  //----------------- IEngrSpecFolderList Methods ------------------------------------
  /**
  * Returns the next available IProduct in the list.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IEngrSpecFolder nextEngrSpecFolder()
    throws OculusException
  {
    return (IEngrSpecFolder)next();
  }
  
  /**
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreEngrSpecFolders()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    EngrSpecFolderList sortedList = new EngrSpecFolderList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  

	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  EngrSpecFolderList linkList = null;
			linkList = new EngrSpecFolderList();
			linkList.setIID(_iid);
			linkList._items = this._items;
			linkList.reset();
		return linkList;
	}
}