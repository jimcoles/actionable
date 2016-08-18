package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;

import java.sql.*;
import java.util.*;

/** 
*
* @author Zain Nemazie
*/

/*
* $Workfile: IOrganization.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Zain Nemazie    2/29/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
*/

public interface IOrganization extends IBusinessObject
{
  /** Returns the first line of the address
  *
  * @return the first line of the address
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getBusAddressLine1()
  	throws OculusException;

  /** Returns the second line of the address
  *
  * @return the second line of the address
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getBusAddressLine2()
	  throws OculusException;

  /** Returns the name of the city
  *
  * @return the name of the city
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getCity()
  	throws OculusException;

  /** Returns the name of the country
  *
  * @return the name of the country
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getCountry()
	  throws OculusException;

  /** Returns whether or not this organization owns installation.
  *
  * @return the name of the country
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean getIsInstallOwner()
  	throws OculusException;

  /** Returns the name of this organization.
  *
  * @return the name of the user
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getName()
    throws OculusException;

  /** Returns the IIID of the parent organization for this organization.
  *
  * @return the IIID of the parent organization for this organization
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getParentOrgIID()
  	throws ORIOException;

  /** Returns the name of the state
  *
  * @return the name of the state
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getState()
	  throws OculusException;

  /** Returns the zip code
  *
  * @return the zip code
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getZipcode()
  	throws OculusException;

  /** Sets the first line of the address for this organization.
  *
  * @param busAddrLine1 the first line of the address
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setBusAddressLine1(String busAddrLine1)
	  throws ORIOException;

  /** Sets the second line of the address for this organization.
  *
  * @param busAddrLine2 the second line of the address
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setBusAddressLine2(String busAddrLine2)
  	throws ORIOException;

  /** Sets the name of the city.
  *
  * @param city the name of the city
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setCity(String city)
	  throws ORIOException;

  /** Sets the name of the country.
  *
  * @param country the name of the country
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setCountry(String country)
  	throws ORIOException;

  /** Sets whether or not this organization owns installation.
  *
  * @param isInstallOwner whether or not this organization owns installation.
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setIsInstallOwner(boolean isInstallOwner)
  	throws ORIOException;

  /** Sets parent organization for this organization to the organization for the given IIID.
  *
  * @param id the IIID for the new parent organization
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setParentOrgIID(IIID id)
  	throws ORIOException;

  /** Sets the name of the state.
  *
  * @param state the name of the state
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setState(String state)
  	throws ORIOException;

  /** Sets the zipcode.
  *
  * @param zipcode the zipcode
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IOrganization setZipcode(String zipcode)
	  throws ORIOException;
}
