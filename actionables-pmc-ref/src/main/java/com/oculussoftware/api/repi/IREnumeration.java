/**

@author Alok Pota
**/

/**
An interface to store enumerated types
*/
package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

public interface IREnumeration extends IRModelElement
{
   
  
  
	 public IREnumeration setEnumID(IIID id) throws ORIOException;
	 public IIID getEnumID() throws ORIOException;
   public List getValues() throws OculusException;      
   public IRModelElementList getEnumLiterals() throws OculusException;      
   public IREnumeration setValues(List ay) throws ORIOException;      
   public IREnumeration clearValues() throws ORIOException;   
   
   //Hard delete all literal values
   public void wipeAllLiterals() throws ORIOException;   
   
   
}