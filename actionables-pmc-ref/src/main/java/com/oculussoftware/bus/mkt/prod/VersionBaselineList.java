package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    ProductVersionColl.java
* Date:        
* Description: Handles a list of IProductVersion objects
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

public class VersionBaselineList extends BusinessObjectList implements IVersionBaselineList
{
  protected String TABLE = "VERSIONBASELINE";
  protected String COL_VERSIONID = "VERSIONID";
  protected String COL_CREATIONDATE = "CREATIONDATE";
  protected String COL_DELETESTATE = "DELETESTATE";
  protected String _sortby = "base.CREATIONDATE";
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public VersionBaselineList() throws OculusException
	{
    super();
	}

  protected VersionBaselineList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT base.* "+
           " FROM VERSIONBASELINE base LEFT OUTER JOIN APPUSER ON base.CREATORID=APPUSER.OBJECTID "+
           " WHERE base."+COL_VERSIONID+"="+getIID().getLongValue()+
             " AND base."+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+_sortby;
  }
  
  protected String getClassName()
  {
    return "ProductBaseline";
  }

	//----------------- IProductList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IVersionBaseline nextVersionBaseline()
		throws OculusException
	{
		return (IVersionBaseline)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreVersionBaselines()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    VersionBaselineList sortedList = new VersionBaselineList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
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
        _sortby = "base.BASELINENAME";
      if (sort.equals(SortKind.BYCREATOR))
        _sortby = "APPUSER.LASTNAME, APPUSER.FIRSTNAME";
      if (sort.equals(SortKind.BYDATE))
        _sortby = "base.CREATIONDATE";
    }
    return this;
  }
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		VersionBaselineList verList = null;
			verList = new VersionBaselineList();
			verList.setIID(_iid);
			verList._items = this._items;
			verList.reset();
		return verList;
	}
}