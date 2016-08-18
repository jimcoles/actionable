package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    StandardsCollectionList.java
* Date:        
* Description: Handles a list of IStandardsCollection objects
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class StandardsCollectionList extends BusinessObjectList implements IStandardsCollectionList, IPersistable
{
  protected String TABLE = "STANDARDSCOLLECTION";
  protected String COL_NAME = "NAME";
  protected String COL_PARENTID = "PARENTCOLLID";
  
  protected String COL_DELETESTATE = "DELETESTATE";


  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the StandardsCollection list */
	public StandardsCollectionList() throws OculusException
	{
    super();
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the StandardsCollection list */
  protected StandardsCollectionList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
  
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_PARENTID+"="+getIID().getLongValue()+
           " AND "+COL_DELETESTATE+"<>"+DeleteState.DELETED.getIntValue()+
           " ORDER BY "+COL_NAME;
 }


  protected String getClassName()
  {
    return "StandardsCollection";
  }

	//----------------- IStandardsCollectionList Methods ------------------------------------
	/**
	* Returns the next available IStandardsCollection in the list.  If the next IStandardsCollection turns
	* out to be null, just go to the next one.
 */
  public IStandardsCollection nextStdCollection()
		throws OculusException
	{
		
		return (IStandardsCollection)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreStdCollection()
	{
		return hasNext();
	}
  
	
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    StandardsCollectionList sortedList = new StandardsCollectionList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IStandardsCollectionList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		StandardsCollectionList stdcollList = null;
			stdcollList = new StandardsCollectionList();
			stdcollList.setIID(_iid);
			stdcollList._items.addAll(this._items);
			stdcollList.reset();
		return stdcollList;
	}

    @Override
    public IStandardsCollection nextStandardsCollection() throws OculusException {
        return null;
    }

    @Override
    public boolean hasMoreStandardsCollection() {
        return false;
    }
}