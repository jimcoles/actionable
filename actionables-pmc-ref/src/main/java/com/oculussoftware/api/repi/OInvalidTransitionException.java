package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;

public class OInvalidTransitionException extends OculusException
{
  public OInvalidTransitionException()
  {
    super("Invalid Transition Exception");
  }
  public OInvalidTransitionException(String msg)
  {
    super(msg);
  }
  public OInvalidTransitionException(Exception ex)
  {
    super(ex.getClass().getName() + "==>" + ex.getMessage());
  }
}