package com.oculussoftware.service.log;

import com.oculussoftware.api.service.log.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;
/**
* Filename:    UserLogEntryList.java
* Date:        4-21-00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*
*/

public class UserLogEntryList implements IUserLogEntryList {

	protected String _sortby;                        // sort by criteria
	protected IObjectContext _context;
	protected Vector _elements;
	protected Iterator _ids;
/**
 * UserLogEntryList constructor comment.
 */
private UserLogEntryList() {
	super();
}
/**
 * UserLogEntryList constructor comment.
 */
public UserLogEntryList(IObjectContext context) throws OculusException {
	this();
	_context = context;
	getLoadQueryAndLoad();
}
protected void getLoadQueryAndLoad() throws OculusException
{
	IQueryProcessor stmt = null;
	try
	{
		IRConnection repConn = _context.getRepository().getDataConnection(_context);
		stmt = repConn.createProcessor();
		String query = "SELECT * FROM USERLOG ORDER BY DATE"; //"Select " + COL_LOGINID +" from " + TABLE + " where "+ COL_OBJECTID + " <> " + _iid.getLongValue() + " AND "  + COL_DELETESTATE + "<>" + DeleteState.DELETED.getIntValue() + " AND " + COL_LOGINID + " IS NOT NULL"; 
		IDataSet ds = stmt.retrieve(query);
		_elements = new Vector();
		while (ds.next())
		{
				UserLogEntry ent = new UserLogEntry();
				
				ent.setDate(ds.getTimestamp("DATE"));
				IIID usrID = _context.getRepository().makeReposID(ds.getLong("USERID"));
				IUser usr = (IUser)_context.getCRM().getCompObject(_context,"User",usrID);
				ent.setUser(usr);
				_elements.addElement(ent);
								
		}

		_ids = _elements.iterator();

		if (stmt != null)
			stmt.close();
	}
	finally
	{
		if (stmt != null)
			stmt.close();
	}
}
/**
 * hasMoreEntries method comment.
 */
public boolean hasMoreEntries() {
	return _ids.hasNext();
}
/**
 * nextEntry method comment.
 */
public IUserLogEntry nextEntry() {
	return (IUserLogEntry) _ids.next();
}
/**
 * reset method comment.
 */
public IUserLogEntryList reset() {
	_ids = _elements.iterator();
	return this;
}
/**
 * setSortBy method comment.
 */
public IUserLogEntryList setSortBy(String sortby) {
	_sortby = sortby;
	return this;
}
}
