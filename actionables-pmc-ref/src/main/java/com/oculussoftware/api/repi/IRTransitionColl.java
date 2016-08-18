package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;


/** 
*/

public interface IRTransitionColl extends IRModelElementColl
{
  public boolean hasMoreTransitions();
  public IRTransition nextTransition() throws OculusException;
  
}