package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/** Provides repository (data) objects with a handle into the context of their
    existence such as the owning repository.
*/
public interface IRObjectContext extends IObjectContext
{
  public IRepository  getRepository();
    
  public IRObject     getParent();

//  public ITransaction getLockingTransaction();
    
}