package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;


/** UML: 'Action'
*/
public interface IRAction extends IRModelElement
{
  //ACCESSORS
  public int        getOrderNum() throws ORIOException;
  public IIID       getTransitionIID() throws ORIOException;
  public IIID       getAttributeIID() throws ORIOException;
  public ActionKind getActionKind() throws ORIOException;
  public String     getSetValue() throws ORIOException;
  public String     getActionExpression() throws ORIOException;
  
  //MUTATORS
  public IRAction setOrderNum(int o) throws ORIOException;
  public IRAction setTransitionIID(IIID t) throws ORIOException;
  public IRAction setAttributeIID(IIID a) throws ORIOException;
  public IRAction setActionKind(ActionKind a) throws ORIOException;
  public IRAction setSetValue(String s) throws ORIOException;
  public IRAction setActionExpression(String s) throws ORIOException;
  
  //FUNCTIONAL METHODS
  public IRAction execute(IBusinessObject bo) throws OculusException;
}