package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;

public class ORIOException extends OculusException
{
  public ORIOException()
  {
    super("Repository I/O Exception");
  }
  public ORIOException(String msg)
  {
    super(msg);
  }
  public ORIOException(Exception ex)
  {
    super(ex);
  }
}