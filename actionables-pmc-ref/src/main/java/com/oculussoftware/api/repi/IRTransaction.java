package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/** An IRTransaction is analogous to a relational database transaction.  For
    RDBMS-based repository implementations it is probably a thin wrapper.
 */
public interface IRTransaction extends IObject 
{
  /** Saves all modified objects. */
  public void save()
    throws ORIOException;
    
  /** Commits current transaction */
  public void commit()
    throws ORIOException;

  public void abort()
    throws ORIOException;
    
  public IRObject addLockedObject(IRObject obj);
  
  public void removeLockedObject(IRObject obj);
}