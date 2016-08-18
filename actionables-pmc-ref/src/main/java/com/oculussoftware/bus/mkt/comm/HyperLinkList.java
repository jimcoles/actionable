package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.rdb.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
* Filename:    HyperLinkList.java
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

public class HyperLinkList extends BusinessObjectList implements IHyperLinkList, IPersistable
{
  protected String TABLE = "EXTHYPERLINK";
  protected String COL_PAROBJECTID = "PAROBJECTID";
  protected String COL_TYPE = "TYPE";
  protected String COL_DELETESTATE = "DELETESTATE";
 

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public HyperLinkList() throws OculusException
	{
    super();
    _sortby = "2";
	}

  //----------------------------- Protected Constructor -------------------------
  /** Default constructor just initializes the product list */
  protected HyperLinkList(Comparator sortCrit) throws OculusException
  {
    super(sortCrit);
  }

  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return " SELECT LINKTABLE."+COL_OBJECTID+", LINKTABLE.LABEL, LINKTABLE.URL, LINKTABLE.CREATIONDATE, USERTABLE.LASTNAME, USERTABLE.FIRSTNAME" +

           " FROM "+TABLE+" AS LINKTABLE LEFT OUTER JOIN APPUSER AS USERTABLE ON USERTABLE.OBJECTID=LINKTABLE.CREATORID "+
           " WHERE LINKTABLE."+COL_PAROBJECTID+"="+getIID().getLongValue()+" "+
           "   AND LINKTABLE."+COL_TYPE+" <> "+HyperLinkType.ENGRSPEC.getIntValue()+
					 "   AND LINKTABLE."+COL_DELETESTATE+" <> "+DeleteState.DELETED+" "+
           " ORDER BY "+_sortby;
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
  *  Returns true if there are more IProducts in the list
  */
  public boolean hasMoreHyperLinks()
  {
    return hasNext();
  }
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    HyperLinkList sortedList = new HyperLinkList(sortCrit);
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
        _sortby = "2";
      if (sort.equals(SortKind.BYPATH))
        _sortby = "3";
      if (sort.equals(SortKind.BYDATE))
        _sortby = "4";
      if (sort.equals(SortKind.BYCREATOR))
        _sortby = "5, 6";
    }
    return this;
  }

	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
	  HyperLinkList linkList = null;
			linkList = new HyperLinkList();
			linkList.setIID(_iid);
			linkList._items = this._items;
			linkList.reset();
		return linkList;
	}
}