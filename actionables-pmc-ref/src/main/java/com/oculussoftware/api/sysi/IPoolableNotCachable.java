package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.repi.IDataSet;

/** This interface represents an object that wants to be created using the CRM and
* PoolMgr, but should not be cached.  It acts only as a flag and has no methods.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IPoolableNotCachable.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IPoolableNotCachable extends IPoolable
{
}