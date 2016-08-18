package com.oculussoftware.api.busi.common.process;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.*;
import java.sql.Timestamp;

/*
* $Workfile: IProcessChange.java $
* Description: Maintains the ProcessChange and the ProcessReceiver Tables.
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* The ProcessChange and ProcessReceiver tables contain a log of all the 
* state changes for a BusinessObject.  IProcessChange is responsible 
* for updating the values in the ProcessChange and the ProcessReceiver
* tables.  Each time a BusinessObject executes a Transition or the 
* process is interrupted, an entry is written to the tables.  
*
* @author Egan Royal
*/
public interface IProcessChange extends IRObject
{
  
  //Accessors
  
  /**
  * This accessor method returns the IIID for the Object whose 
  * State was changed.
  * @return The IIID for the Object whose State was changed.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getChangeObjectIID() throws ORIOException;
  
  /**
  * This accessor method returns the IIID for the FeatureRevision.  The 
  * field in the databse is nullable and the method will return
  * null when the field is null.
  * @return The IIID for the FeatureRevision or null.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getRevisionIID() throws ORIOException;
  
  /**
  * This accessor method returns the IIID of the From User or the 
  * User that initiated the ProcessChange.
  * @return The IIID of the From User.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getFromUserIID() throws ORIOException;
  
  /**
  * This accessor method returns the From User.
  * @return The From User.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IUser getFromUserObject() throws OculusException;
  
  /**
  * This accessor method returns the IIID of the Transition that
  * was executed for this ProcessChange.  The Transition IIID can 
  * be used to obtain the To and From IRStates.
  * @return The IIID of the Transition.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IIID getTransitionIID() throws ORIOException;
  
  /**
  * This accessor method returns the IRTransition that was executed 
  * for this ProcessChange.  The IRTransition can be used to obtain the 
  * To and From IRStates.
  * @return The IRTransition for this ProcessChange.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IRTransition getTransitionObject() throws OculusException;
  
  /**
  * This accessor method returns the Timestamp for the change date
  * of the ProcessChange.
  * @return The creation date of the ProcessChange.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public Timestamp getChangeDate() throws ORIOException;
  
  /**
  * This accessor method returns the int value of the ackmask for this 
  * ProcessChange.  The ackmask is not longer being used it has been moved
  * to the Notification.
  * @return The int value of the ackmask.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public int getAckMask() throws ORIOException;
  
  /**
  * This accessor method returns the String value of the ProcessChange
  * comment.
  * @return The comment.
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public String getComment() throws ORIOException;
  
  /**
  * This accessor method returns the list of IUsers(receivers) that the
  * ProcessChange was sent to.
  * @return The IUserList of receivers.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IUserList getReceivers() throws OculusException;
  
  //Mutators
  /**
  * This mutator method sets the Change Object IIID.
  * @param iid The IIID of the BusinessObject whose IRState was changed.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setChangeObjectIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the FeatureRevision IIID.
  * @param iid The IIID of the FeatureRevision.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setRevisionIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the From User IIID (the User that initiated the 
  * ProcessChange).
  * @param iid The IIID of the From User.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setFromUserIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the From User Object (the User that initiated the 
  * ProcessChange).
  * @param user The From User Object.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IProcessChange setFromUserObject(IUser user) throws OculusException;
  
  /**
  * This mutator method sets the IRTranstion IIID. 
  * @param iid The IIID of the IRTransition.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setTransitionIID(IIID iid) throws ORIOException;
  
  /**
  * This mutator method sets the IRTranstion Object. 
  * @param trans The IRTransition.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IProcessChange setTransitionObject(IRTransition trans) throws OculusException;
  
  /**
  * This mutator method sets the ackmask. 
  * @param a The ackmask.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setAckMask(int a) throws ORIOException;
  
  /**
  * This mutator method sets the comment. 
  * @param c The comment.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setComment(String c) throws ORIOException;
  
  /**
  * This mutator method sets the change date. 
  * @param t The change date.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException  
  */
  public IProcessChange setChangeDate(Timestamp t) throws ORIOException; 
  
  /**
  * This mutator method adds an IUser to the list of Process Recievers. 
  * @param user The IUser to be added.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IProcessChange addReceiver(IUser user) throws OculusException;
  
}