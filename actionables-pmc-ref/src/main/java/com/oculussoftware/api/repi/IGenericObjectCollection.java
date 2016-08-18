package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

import java.util.*;

/** The base collection interface for an IRepository.  This behaves
*   essentially like a JCF SortedSet.
*/
public abstract interface IGenericObjectCollection extends IRCollection
{
  public void add(long objectID, long classID)
    throws OculusException;
    
  public Object get(int index)
    throws OculusException;

  public IPersistable load(IDataSet data, String oidSyn, String cidSyn)
    throws OculusException; 

  public void remove(int index)
    throws OculusException;
}