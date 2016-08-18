package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;


/** 
*/

public interface IRStateColl extends IRModelElementColl
{
  public boolean hasMoreStates();
  public IRState nextState() throws OculusException;
  
}