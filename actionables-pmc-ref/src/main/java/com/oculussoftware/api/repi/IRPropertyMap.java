package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import java.util.*;

/** Represents a named map of IRProperty's.
*/
public interface IRPropertyMap extends IRMap
{
  public void putAll(IRPropertyMap t) throws OculusException;
  public void putAll(IRPropertyMap t, IRObject obj) throws OculusException;
  public void remove(Object key);
}