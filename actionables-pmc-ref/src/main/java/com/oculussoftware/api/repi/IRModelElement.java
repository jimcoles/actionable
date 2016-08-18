package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/** Base notion for all 'definition' elements */
public interface IRModelElement extends IRElement
{
  public ConfigKind getConfigKind()	throws ORIOException;
  public DeleteState getDeleteState()	throws ORIOException;
  public boolean isActive()	throws ORIOException;

  public IRModelElement setConfigKind(ConfigKind c)	throws ORIOException;
  public IRModelElement setDeleteState(DeleteState d)	throws ORIOException;
  public IRModelElement isActive(boolean a)	throws ORIOException;

  public IPersistable softdelete() throws OculusException;
  
  /****
  
  Please do not remove this method. The BMModelElement which implements 
  this interface returns false as default. 
  
  Egan's code should not break as long as 
  all his classes extend BMModelElement.
  
  ***/
  
  public boolean isUsed() throws OculusException;
  
  //Trash for forms! 
  public IPersistable recover() throws OculusException;
}