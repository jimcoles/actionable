package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.system.*;


/** 
*/

public interface IRModelElementList extends IRList
{
//  public String getLoadQuery();
//  public void setClassName(String name);
  public IRModelElement getModelElement(int index) throws ORIOException;
  
  
  public boolean addAll(IRModelElementList ll) throws ORIOException;
  public boolean removeAll(IRModelElementList ll) throws ORIOException;
  public java.util.List getList();
  public IRPropertyMap convert2PropertyMap() throws ORIOException;
 
  
}      