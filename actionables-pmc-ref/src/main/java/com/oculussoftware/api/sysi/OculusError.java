package com.oculussoftware.api.sysi;
  
/** Base exception class for Oculus systems.
*/
public class OculusError extends Error
{
  public OculusError (String msg)
  {
    super(msg);
  }
}