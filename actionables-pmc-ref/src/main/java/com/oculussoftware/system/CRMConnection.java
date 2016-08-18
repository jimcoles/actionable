package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.api.busi.common.org.IUser;

/**
* Filename:    CRMConnection.java
* Date:        
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*
*/

public class CRMConnection implements ICRMConnection
{
	private IGUID _guid;											// this classes IGUID
	private IIID _userid;											// the user who holds this connections
	private IObjectContext _context;					// the connection context
  private ICRM _thecrm = null;              // the ICRM holding this connection
	
	
	//------------------------ Public Constructor --------------------------------
	/** generates a CRMConnection for the given user */
	public CRMConnection(IIID userid, ICRM crm) throws OculusException
	{
		_userid = userid;
		_guid = new GUID();
    _thecrm = crm;
	}
	
	//------------------------- ICRMConnection Methods -----------------------------
	/** Returns the IID of this connection */
	public IIID getIID()
	{
		return _userid;
	}
	
	/** Returns the ID of the user that has this IConnection */
	public IIID getUserIID()
	{
		return _userid;
	}
  
  /** Returns the User object that has this ICRMConnection */
  public IUser getUserObject() throws OculusException
  {
	  if(getObjectContext() != null && getUserIID() != null)
      return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",getUserIID());
    return null;
	}
  
	/** Returns true if the IConnection is a valid IConnection */
	public boolean isValid()
    throws OculusException
	{
		return CRM.getInstance().isValid(this);
	}
	

	//------------------------------ IObject Methods ----------------------------------
  /** return the ObjectContext of this object */
  public IObjectContext getObjectContext()
	{
		return _context;
	}

	/** sets the object context of this object */
	public IObject setObjectContext(IObjectContext context)
	{
		_context = context;
		return this;
	}

	/** returns this objects GUID */
	public IGUID getGUID()
	{
		return _guid;
	}
	
  public ICRM getCRM()
  {
    return _thecrm;
  }
  
	//I commented out this code to try to resolve the multiple login
	//issue
	//Egan
	
//  public boolean equals(Object other)
// {
//   if (!(other instanceof ICRMConnection))
//     return false;
//   return (((ICRMConnection)other).getIID().equals(getIID()));
// }
// 
// //override Object hashcode
// public int hashCode()
// {
//   return getIID().hashCode();
// }
}