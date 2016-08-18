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
* Filename:    EngrSpecColl.java
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
* PRB01209        Cuihua Zhang    6/28/2000   added to the select list the _sortby items
*/

public class EngrSpecFileList extends FeatureFileList
{
	public EngrSpecFileList() throws OculusException
	{
    super();
	}

  public EngrSpecFileList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    
    return " SELECT ATTACH."+COL_OBJECTID+ ", ATTACH.NAME, ATTACH.FILESIZEBYTES, ATTACH.CREATIONDATE, APP.LASTNAME, APP.FIRSTNAME " +
           " FROM (ATTACHMENT AS ATTACH LEFT OUTER JOIN APPUSER AS APP ON APP.OBJECTID = ATTACH.CREATORID) "+
           "   LEFT OUTER JOIN CATFEATURELINK ON CATFEATURELINK.OBJECTID=ATTACH.PAROBJECTID "+
           " WHERE ATTACH."+COL_TYPE+"=2 AND CATFEATURELINK.FEATUREID="+getIID().getLongValue()+
           " ORDER BY "+_sortby;
  }

//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    EngrSpecFileList sortedList = new EngrSpecFileList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }
  
  
	
//----------------- IPoolable Methods ------------------------------------
	/** Returns a duplicate IProductList object, but without the ObjectContext */
	public Object dolly() throws OculusException
	{
		EngrSpecFileList engrSpecList = null;
			engrSpecList = new EngrSpecFileList();
			engrSpecList.setIID(_iid);
			engrSpecList._items = this._items;
			engrSpecList.reset();
		return engrSpecList;
	}
}