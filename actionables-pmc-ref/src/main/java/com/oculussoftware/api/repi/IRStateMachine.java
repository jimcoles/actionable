package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
//import com.oculussoftware.repos.*;

public interface IRStateMachine extends IRModelElement
{ 
  //ACCESSORS
  public IIID getClassIID() throws ORIOException;
  public IRState getStateObject(IIID id) throws OculusException;
  public IRState getStateObject(IIID id, boolean edit) throws OculusException;
  public IRStateColl getStateColl() throws OculusException;
  public IRStateColl getStateColl(boolean edit) throws OculusException;
  
  //MUTATORS
  public IRStateMachine setClassIID(IIID c) throws ORIOException;
  

  //FUNCTIONAL METHODS
  public IRTransitionColl getEnabledTransitions(IBusinessObject o) throws OculusException;
  public IRTransitionColl getEnabledTransitions(IBusinessObject o,boolean edit) throws OculusException;
  public IIID doTransition(IBusinessObject o, IRTransition t, String processChangeComment)
    throws OculusException;
    
  public IRState getDefaultStartStateObject() throws OculusException;
  public IRState getDefaultStartStateObject(boolean edit) throws OculusException;

  public IRState createState() throws OculusException;
}