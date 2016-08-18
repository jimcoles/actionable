package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;

/** UML: 'Transition'
*/

public interface IRTransition extends IRModelElement
{
  //ACCESSORS
  public IIID             getFromStateIID() throws ORIOException;
  public IIID             getToStateIID() throws ORIOException;
  public IRState          getFromStateObject() throws OculusException;
  public IRState          getFromStateObject(boolean edit) throws OculusException;
  public IRState          getToStateObject() throws OculusException;
  public IRState          getToStateObject(boolean edit) throws OculusException;
  public IRAction         getActionObject(IIID aIID) throws OculusException;
  public IRAction         getActionObject(IIID aIID, boolean edit) throws OculusException;
  public IRGuard          getGuardObject(IIID gIID) throws OculusException;
  public IRGuard          getGuardObject(IIID gIID, boolean edit) throws OculusException;
  public IRActionSequence getActionSequence() throws OculusException;
  public IRActionSequence getActionSequence(boolean edit) throws OculusException;
  public IRGuardColl      getGuardColl() throws OculusException;
  public IRGuardColl      getGuardColl(boolean edit) throws OculusException;

  
  //MUTATORS
  public IRTransition setFromStateIID(IIID f) throws ORIOException;
  public IRTransition setToStateIID(IIID t) throws ORIOException;
  public IRTransition setStateMachineIID(IIID smiid) throws ORIOException;
  
  //FUNCTIONAL METHODS
  public boolean          isEnabled(IBusinessObject bo) throws OculusException;
	public IIID             execute(IBusinessObject bo, String processChangeComment)
    throws OculusException; 
    
  public IRGuard createGuard() throws OculusException;
  public IRAction createAction() throws OculusException;
}