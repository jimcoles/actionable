package com.oculussoftware.api.sysi;
  
/** Thrown any time a resource cannot be obtained because it or its class
    of objects are overloaded withing the system.  E.g., user license limit
    exceeded.
*/
public class ObjectBusyException extends OculusException
{
  public ObjectBusyException( )
  {
    super("Resource busy");
  }
}