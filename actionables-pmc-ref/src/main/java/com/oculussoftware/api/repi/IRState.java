package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.process.*;

public interface IRState extends IRModelElement
{	 
  //ACCESSORS
  public IIID             getStateMachineIID() throws ORIOException;
  public IIID             getRoleIID() throws ORIOException;
  public IProcessRole     getRoleObject() throws OculusException;
  public IProcessRole     getRoleObject(boolean edit) throws OculusException;
  public StateKind        getStateKind() throws ORIOException;
  public IRStateMachine   getStateMachineObject() throws OculusException;  
  public IRStateMachine   getStateMachineObject(boolean edit) throws OculusException;
  public IRTransition     getTransitionObject(IIID transIID) throws OculusException;
  public IRTransition     getTransitionObject(IIID transIID, boolean edit) throws OculusException;
  public IRTransitionColl getTransitionColl() throws OculusException;
  public IRTransitionColl getTransitionColl(boolean edit) throws OculusException;
  
  //MUTATORS
  public IRState setStateMachineIID(IIID s) throws ORIOException;
  public IRState setRoleIID(IIID r) throws ORIOException;
  public IRState setStateKind(StateKind s) throws ORIOException;
	
  
  //FUNCTIONAL METHODS
  public IRTransitionColl getEnabledTransitions(IBusinessObject o) throws OculusException;
  public IRTransitionColl getEnabledTransitions(IBusinessObject o, boolean edit) throws OculusException;
  public IRTransitionColl getReferenceTransitionColl() throws OculusException;
  public IRTransitionColl getReferenceTransitionColl(boolean edit) throws OculusException;
  public boolean isFinal() throws ORIOException;
  public boolean isStart() throws ORIOException;
  
  public IRTransition createTransition() throws OculusException;

}