package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Organization.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a Organization.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class OrganizationColl extends BusinessObjectColl implements IOrganizationColl
{
  protected String TABLE      = "ORGANIZATION";
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a Coll of empty properties */  
  public OrganizationColl() throws OculusException
  {
	super();
  }  
  protected OrganizationColl(Comparator sortCrit) throws OculusException
  {
	super (sortCrit);
  }  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductColl object, but without the ObjectContext */
  public Object dolly() throws OculusException
  {
	OrganizationColl orgColl = null;

	  orgColl = new OrganizationColl();
	  orgColl.setIID(_iid);
	  orgColl._items = this._items;
	  orgColl.reset();
		return orgColl;
  }    
  protected String getClassName() { return "Organization"; }  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
	throws ORIOException
  {
	return " SELECT "+COL_OBJECTID+
		   " FROM "+TABLE;
  }  
  /**
  *  Returns true if there are more IProducts in the Coll
  */
  public boolean hasMoreOrganizations()
  {
	return hasNext();
  }  
  //----------------- IProductColl Methods ------------------------------------
  /**
  * Returns the next available IProduct in the Coll.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IOrganization nextOrganization()
	throws OculusException
  {
	return (IOrganization)next();
  }  
//------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
	throws OculusException
  {
	OrganizationColl sortedColl = new OrganizationColl(sortCrit);
	sortedColl._items.addAll(this._items);
	return sortedColl;
  }  
}
