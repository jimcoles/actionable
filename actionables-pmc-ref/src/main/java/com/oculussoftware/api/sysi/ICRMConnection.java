package com.oculussoftware.api.sysi;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.repi.ORIOException;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.busi.common.org.IUser;

/** Defines the interface of a connection to the CRM.  This is basically used to
* validate requests from the CRM by clients.
*
* @author Saleem Shafi
*/

/*
* $Workfile: ICRMConnection.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/3/00			Add an ORIOException to getIID() and getUserIID()
*																							for consistency with other methods in this project.
* ---             Saleem Shafi    2/7/00      Added OculusException to isValid()
*
* $History: ICRMConnection.java $
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 9/29/00    Time: 11:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 9/29/00    Time: 10:18a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 5/19/00    Time: 10:06a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi
 * Issue # 240
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 5/17/00    Time: 2:42p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:31a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi
 * Added convenience method for getting the ICRM of anICRMConnection.
*/

public interface ICRMConnection extends IObject
{
	/** Returns the IIID of the ICRMConnection.
  *
  * @return the IIID of the ICRMConnection
  */
	public IIID getIID();
	
	/** Returns the IIID of the user using the ICRMConnection.
  *
  * @return the IIID of the user for this connection
  * @exception com.oculussoftware.api.repi.ORIOException
  */
	public IIID getUserIID()
		throws ORIOException;
    
	/** Returns the user using the ICRMConnection.
  *
  * @return the user for this connection
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser getUserObject()
    throws OculusException;
    
	/** Returns whether or not this connection is a valid one.
  *
  * @return true if this is a valid connection, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public boolean isValid()
    throws OculusException;
    
  /** Returns a reference to the CRM that this connection is connected to.
  *
  * @return a reference to the CRM being connected to
  */
  public ICRM getCRM();
}