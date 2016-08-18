package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

  public interface IRPackage extends IRModelElement
{
public IRPackage getParentPackage() throws OculusException;
public IRPackage setParentPackage(IRPackage id) throws ORIOException;

  
  
}