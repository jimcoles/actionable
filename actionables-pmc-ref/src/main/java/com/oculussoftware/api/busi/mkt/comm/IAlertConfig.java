package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.Timestamp;
import java.util.Map;

/** This interface represents an alert configuration.  It has knowledge of the alert
* type, parameters, and recipients.  It is used to alert users of the system when
* certain conditions have been met within the system.  For example, a user might want
* to be notified if a feature has been added to a version after a Feature Freeze baseline
* has been run.
*
* @author Egan Royal
*/

/*
* $Workfile: IAlertConfig.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IAlertConfig extends IBusinessObject
{
  /** Sets the type of this alert.
  *
  * @param at the type of alert
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAlertConfig setAlertType(AlertType at)
    throws ORIOException;

  /** Sets the date the this alert sent its last notification.
  *
  * @param t the date of the last notification
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAlertConfig setLastNotifyDate(Timestamp t)
    throws ORIOException;

  /** Sets the parent object of this alert to the object represented by the given IIID.
  * The parent object should always be a version.
  *
  * @param iid the IIID of the parent object.
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAlertConfig setParObjectIID(IIID iid)
    throws ORIOException;

  /** Sets the active status of the alert.
  *
  * @param a true if the alert is active, false if inactive
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAlertConfig isActive(boolean a)
    throws ORIOException;

  /** Adds the given user to the recipient list of this alert.
  *
  * @param user the user to add to the recipient list
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlertConfig addRecipient(IUser user)
    throws OculusException;

  /** Adds to the recipient list of this alert all of the users in the given collection.
  *
  * @param userColl the collection of users to add to the recipient list
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlertConfig addRecipientColl(IUserColl userColl)
    throws OculusException;

  /** Sets the value of the parameter given to the given value.
  *
  * @param apt the type of parameter to set
  * @param paramValue the value of the parameter
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlertConfig addParam(AlertParamType apt, String paramValue)
    throws OculusException;

  /** Sets the parameters of this alert to the values in the given Map.  The Map should
  * contain AlertParamTypes as the keys and the values are Strings.
  *
  * @param paramMap the Map of values for this alert
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlertConfig addParamMap(Map paramMap)
    throws OculusException;

  /** Returns the type of this alert.
  *
  * @return true if the alert is active, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public AlertType getAlertType()
    throws ORIOException;

  /** Returns the date and time of that last alert notification sent by this alert.
  *
  * @return the and time of the last notification
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public Timestamp getLastNotifyDate()
    throws ORIOException;

  /** Returns the IIID of the object that this alert is monitoring.  This IIID should
  * always correspond to an IProductVersion object.
  *
  * @return true if the alert is active, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getParObjectIID()
    throws ORIOException;

  /** Returns whether or not this alert is active.
  *
  * @return true if the alert is active, false otherwise
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public boolean isActive()
    throws ORIOException;

  /** Returns a collection of users that will be recipients of this alert.
  *
  * @return collection of users
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUserColl getRecipientColl()
    throws OculusException;

  /** Returns a Map containing all of the parameter types and values for this configuration.
  * The parameter type is the key.
  *
  * @return Map of parameter types and values
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Map getParamMap()
    throws OculusException;
  
  /** Returns a String value representation of the value for the given parameter type.
  *
  * @param apt the parameter type being requested
  * @return String representation of value
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getParamValue(AlertParamType apt)
    throws OculusException;
}