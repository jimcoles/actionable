package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;


/** 
*/

public interface IRStateMachineColl extends IRModelElementColl
{
  public boolean hasMoreStateMachines();
  public IRStateMachine nextStateMachine() throws OculusException;
  
}