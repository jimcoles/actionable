package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

public interface IRNamedElement extends IObject
{
  public String getName()
    throws ORIOException;
  public IRNamedElement setName(String name)
    throws ORIOException;
}