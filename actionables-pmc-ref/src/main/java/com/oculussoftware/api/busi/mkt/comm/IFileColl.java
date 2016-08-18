package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObjectColl;

/** Represents a collection of file attachments.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFileColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IFileColl extends IBusinessObjectColl
{
  /** Returns the next file attachment in the collection
  *
  * @return the next file in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment nextFile()
		throws OculusException;
	
  /** Returns whether or not the collection contains any more file attachments.
  *
  * @return true if there are more file attachments, false otherwise
  */
  public boolean hasMoreFiles();
  
}