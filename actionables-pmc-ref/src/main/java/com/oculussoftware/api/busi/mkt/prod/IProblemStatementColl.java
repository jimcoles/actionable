package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;


/** Represents a collection of problem statements.  The value-add of this interface is
* that it performs the casting for the client.
*
* @author Alok Pota
*/

/*
* $Workfile: IProblemStatementColl.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IProblemStatementColl extends IBusinessObjectColl
{

  /** Returns the next problem statement in the collection
  *
  * @return the next problem statement in the collection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProblemStatement nextProblemStatement()
    throws OculusException;
  
  /** Returns whether or not the collection contains any more problem statements.
  *
  * @return true if there are more problem statements, false otherwise
  */
  public boolean hasMoreProblemStatements();
  

}