package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    HyperLinkColl.java
* Date:        2/14/00
* Description: Handles a list of IHyperLink objects
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

public class HyperLinkColl extends BusinessObjectColl implements IHyperLinkColl, IPersistable
{
  protected String TABLE = "EXTHYPERLINK";
  protected String COL_PAROBJECTID = "PAROBJECTID";
  protected String COL_TYPE = "TYPE";

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public HyperLinkColl() throws OculusException
	{
    super();
	}

  protected HyperLinkColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT "+COL_OBJECTID+
           " FROM "+TABLE+
           " WHERE "+COL_PAROBJECTID+"="+getIID().getLongValue()+" "+
           " AND "+COL_TYPE+" <> "+HyperLinkType.ENGRSPEC.getIntValue()+
           " AND "+COL_DELETESTATE+" <> "+DeleteState.DELETED.getIntValue();
  }

  protected String getClassName () { return "HyperLink"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	* Returns the next available IProduct in the list.  If the next IProduct turns
	* out to be null, just go to the next one.
 	*/
  public IHyperLink nextHyperLink()
		throws OculusException
	{
		return (IHyperLink)next();
	}
	
	/**
	*	Returns true if there are more IProducts in the list
	*/
  public boolean hasMoreHyperLinks()
	{
		return hasNext();
	}
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    HyperLinkColl sortedList = new HyperLinkColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  HyperLinkColl linkList = null;
			linkList = new HyperLinkColl();
			linkList.setIID(_iid);
			linkList._items = this._items;
			linkList.reset();
		return linkList;
	}
}