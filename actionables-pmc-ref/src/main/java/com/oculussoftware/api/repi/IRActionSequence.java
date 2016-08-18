package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;

/** UML: 'ActionSequence'
*/
public interface IRActionSequence extends IRModelElementColl
{
  public boolean hasMoreActions();
  public IRAction nextAction() throws OculusException;
}