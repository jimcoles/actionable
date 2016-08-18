package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

/** Represents a collection of folder input links.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Alok Pota
*/

/*
* $Workfile: IFolderInputLinkColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFolderInputLinkColl extends IBusinessObjectColl
{
  /** Returns the next folder input link in the collection
  *
  * @return the next folder input link in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolderInputLink nextFolderInputLink()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more folder input links.
  *
  * @return true if there are more folder input links, false otherwise
  */
  public boolean hasMoreFolderInputLinks();
  

}