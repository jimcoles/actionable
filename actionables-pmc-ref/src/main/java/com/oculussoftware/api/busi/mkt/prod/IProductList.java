package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectList;

/** This interface represents any list of feature link changes.  It has no methods of
* its own.  
*
* @author Saleem Shafi
*/

/*
* $Workfile: IProductList.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Changed all of the void methods to return
*																							IProductList for consistency.
* ---             Saleem Shafi    2/14/00     Moved the IRPersistable interface to the concrete class.
* ---             Saleem Shafi    2/14/00     Create IBusinessObjectList
*/

public interface IProductList extends IBusinessObjectList, IProductColl
{
}