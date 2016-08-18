package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

/** Represents a collection of folders.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: IFolderColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFolderColl extends IBusinessObjectColl
{
  /** Returns the next folder in the collection
  *
  * @return the next folder in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolder nextFolder()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more alert folders.
  *
  * @return true if there are more folders, false otherwise
  */
  public boolean hasMoreFolders();
  

}