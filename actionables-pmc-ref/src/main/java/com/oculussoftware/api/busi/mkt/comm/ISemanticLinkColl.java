package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;

/** Represents a collection of semantic links.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Egan Royal
*/

/*
* $Workfile: ISemanticLinkColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface ISemanticLinkColl extends IRCollection
{
  /** Returns the next semantic link in the collection
  *
  * @return the next semantic link in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink nextSemanticLink() throws OculusException;
  
  /** Returns whether or not the collection contains any more semantic links.
  *
  * @return true if there are more semantic links, false otherwise
  */
  public boolean hasMoreSemanticLinks();
}