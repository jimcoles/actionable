package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;

/** UML: 'Guard'
*/
public interface IRGuard extends IRModelElement
{
  //ACCESSORS
  public IIID getTransitionIID() throws ORIOException;
  public GuardKind  getGuardKind() throws ORIOException;
  public String getGuardExpression() throws ORIOException;
  
  //MUTATORS
  public IRGuard setTransitionIID(IIID t) throws ORIOException;
  public IRGuard setGuardKind(GuardKind g) throws ORIOException;
  public IRGuard setGuardExpression(String s) throws ORIOException;
  
  public IIID evaluate(IBusinessObject bo) throws OculusException;
}