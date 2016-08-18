package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectList;

/** Represents a collection of engineering spec folders.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IEngrSpecFolderList.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IEngrSpecFolderList extends IRList, IPersistable
{
  /** Returns the next engineering spec folder in the collection
  *
  * @return the next engineering spec folder in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IEngrSpecFolder nextEngrSpecFolder()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more engineering spec folders.
  *
  * @return true if there are more engineering spec folders, false otherwise
  */
  public boolean hasMoreEngrSpecFolders();

}