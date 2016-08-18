package com.oculussoftware.service.log;

import com.oculussoftware.api.service.log.*;
import com.oculussoftware.api.busi.common.org.*;
import java.sql.*;
/**
* Filename:    UserLogEntry.java
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

public class UserLogEntry implements IUserLogEntry {
	IUser _usr;
	Timestamp _ts;
/**
 * UserLogEntry constructor comment.
 */
public UserLogEntry() {
	super();
}
/**
 * getCreationDate method comment.
 */
public java.sql.Timestamp getCreationDate() {
	return null;
}
/**
 * getDate method comment.
 */
public Timestamp getDate() {
	return _ts;
}
/**
 * getUser method comment.
 */
public IUser getUser() {
	return _usr;
}
/**
 * getDate method comment.
 */
public IUserLogEntry setDate(Timestamp dt) {
	_ts = dt;
	return this;
}
/**
 * getUser method comment.
 */
public IUserLogEntry setUser(IUser usr) {
	_usr = usr;
	return this;
}
}
