package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;


/** 
*/

public interface IRGuardColl extends IRModelElementColl
{
  public boolean hasMoreGuards();
  public IRGuard nextGuard() throws OculusException;
  
}