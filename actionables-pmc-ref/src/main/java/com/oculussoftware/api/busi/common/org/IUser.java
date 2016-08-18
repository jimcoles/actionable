package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import java.sql.*;


/** 
*
* @author Zain Nemazie
*/

/*
* $Workfile: IUser.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* Bug86						Zain Nemazie	  5/17/00     added isSysUser, isAdmin for Jim
* DES00099        Zain Nemazie    5/17/00     removed setNumberofTrials, should not be a public function
* DES00216        Zain Nemazie    5/18/00     Adding set/getGroup -removing associate, disassociate since 1:1 user:group
* DES00216        Zain Nemazie    5/18/00     Changed set/get group to take iid not IGroups to avoid object creation overhead
* 041							Zain Nemazie 		5/22/00			Adding licensing methods
* BUG86						Zain Nemazie 		5/23/00			Added isTheSys user.
* BUG1194         Zain Nemazie    7/05/00     Added convenience method get Org Object.
*/

public interface IUser extends IBusinessObject, IAccessor, IRPropertyMap
{
  /** Returns the email address of this user.
  *
  * @return the email address
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getEmailAddr()
    throws OculusException;

  /** Returns the email-ability of this user.  Note: This method is not currently being
  * used.
  *
  * @return the email address
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getEmailStatus()
    throws OculusException;


  /** Returns the first name of the user.
  *
  * @return the user's first name
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFirstName()
    throws OculusException;


  /** Returns the full name of the user.
  *
  * @param overrideDefault whether or not the default format should be used.  This
  *        parameter should be false when using the default format.
  * @return the user's full name
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFullName(boolean overrideDefault)
    throws OculusException;

  /** Returns the last name of the user.
  *
  * @return the user's last name
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getLastName()
    throws OculusException;

  /** Returns the login ID of the user.
  *
  * @return the user's login ID
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getLoginId()
    throws OculusException;


  /** Returns the number of times this user has consecutively unsuccessfully tried 
  * to log in.  This count is maintained in order to lock out a login ID after a
  * certain number of bad log ins.
  *
  * @return the user's first name
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getNumberOfTrials()
    throws OculusException;

  /** Returns the IOrganization object to which this user belongs.
  *
  * @return the IOrganization for this user.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IOrganization getOrganizationObject()
    throws OculusException;

  /** Returns the IOrganization object to which this user belongs.
  *
  * @param edit whether or not this object should be locked or not.  true locks the object.
  * @return the IOrganization for this user.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IOrganization getOrganizationObject(boolean edit)
    throws OculusException;

  /** Returns the IIID of the organization to which this user belongs.
  *
  * @return the IIID of the organization for this user.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getOrgIID()
  throws ORIOException;

  /** Returns the password for this user.  Note: The result of this method is NOT encrpyted.
  *
  * @return the password for this user.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getPassword()
    throws OculusException;

  /** Returns the phone number of this user.
  *
  * @return the phone number of this user.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getPhone()
    throws OculusException;

  /** Returns whether or not this user has an accolades license.
  *
  * @return true if this user has an accolades license, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isAccolades()
    throws OculusException;

  /** Returns whether or not this user is an administrator.
  *
  * @return true if this user is an adminstrator, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isAdmin()
    throws ORIOException;

  /** Returns whether or not this user has an compass license.
  *
  * @return true if this user has an compass license, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isCompass()
    throws OculusException;

  /** Returns whether or not this user has an conduit license.
  *
  * @return true if this user has an conduit license, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isConduit()
    throws OculusException;

  /** Sets whether or not this user is a user of the  system.
  *
  * @param bool true if this user is a user of the system, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isSysUser()
  throws OculusException;

  /** Sets whether or not this user is the system administrator.
  *
  * @param bool true if this user is the system administrator, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isTheSysUser()
  throws OculusException;

  /** Sets whether or not this user has an accolades license or not.
  *
  * @param bool true if this user has an accolades license, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setAccolades(boolean bool)
  throws ORIOException;

  /** Sets whether or not this user has a compass license or not.
  *
  * @param bool true if this user has a compass license, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setCompass(boolean bool)
    throws ORIOException;

  /** Sets whether or not this user has a conduit license or not.
  *
  * @param bool true if this user has a conduit license, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setConduit(boolean bool)
    throws ORIOException;

  /** Sets the email address of this user.
  *
  * @param emailAddr the email address for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setEmailAddr(String emailAddr)
    throws ORIOException;

  /** Sets the email status of this user.  Note:  This method is not currently being used.
  *
  * @param emailStatus the email status for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setEmailStatus(int emailStatus)
    throws ORIOException;

  /** Sets the first name of this user.
  *
  * @param firstName the first name for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setFirstName(String firstName)
    throws ORIOException;

  /** Sets the last name of this user.
  *
  * @param lastName the last name for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setLastName(String lastName)
    throws ORIOException;

  /** Sets the login ID of this user.
  *
  * @param loginID the login ID for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setLoginId(String loginId)
    throws ORIOException;

  /** Sets the organization that this user belongs to.
  *
  * @param id the IIID of the organization for this user.
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setOrgIID(IIID id)
    throws ORIOException;

  /** Sets the password of this user.  Note: The password should not be encrypted.
  *
  * @param password the password for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setPassword(String password)
    throws ORIOException;

  /** Sets the phone number of this user.
  *
  * @param phone the phone number for this user
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setPhone(String phone)
    throws ORIOException;

  /** Returns the IIID of the department that this user belongs to.
  *
  * @return the IIID of the department that this user belongs to
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getDepartmentIID()
    throws ORIOException;

  /** Returns a collection of groups that this user belongs to.
  *
  * @return a collection of groups that this user belongs to
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IGroupColl getGroups()
    throws OculusException;

  /** Returns the active state of this user.
  *
  * @return the active state of this user
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ActiveKind getActive()
  throws OculusException;

  /** Sets the active state of this user.
  *
  * @param the active state to set this user to
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IUser setActive(ActiveKind active)
    throws ORIOException;

  /** Associates this user to the group for the given IIID.
  *
  * @param the IIID of the group to associate this user to
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser associateGroup(IIID grpIID)
  throws OculusException;

  /** Disassociates this user from the group for the given IIID.
  *
  * @param the IIID of the group to disassociate this user from
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser disassociateGroup(IIID grpIID)
    throws OculusException;

  /** Returns whether or not this user is a customer.
  *
  * @return true if this user is a customer, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isCustomer()
    throws OculusException;

  /** Sets whether or not this user is a customer.
  *
  * @param cust true if this user is a customer, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser isCustomer(boolean cust)
    throws OculusException;

  /** Returns whether or not this user is temporary.
  *
  * @return true if this user is temporary, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isTemporary()
    throws OculusException;  

  /** Sets whether or not this user is a temporary user.
  *
  * @param temp true if this user is temporary, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser isTemporary(boolean temp)
    throws OculusException;  
}
