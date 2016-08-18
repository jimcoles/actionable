/**

@author Alok Pota
**/

/**
An interface to store enumerated types
*/
package com.oculussoftware.api.repi;

import java.util.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.sysi.*;



public interface IRProdEnumeration extends IRModelElement
{
   
   public IRProdEnumeration setMode(String s);
   public IRProdEnumeration setEnumID(IIID id);   
   public IRProdEnumeration setUser(IUser id)throws ORIOException;
   public IUser getUser() throws OculusException;
   public IIID getEnumID();
   public List getValues() throws ORIOException;      
   public List getValuesAndNotifiees() throws ORIOException;      
   public List getNotifiees() throws ORIOException;         
   public IRProdEnumeration setValues(List ay) throws ORIOException;      
   public IRProdEnumeration setNotifiees(List ay) throws ORIOException;      
   public  IRProdEnumeration setForCopy(String as);      
   public  IRProdEnumeration makecopy() throws OculusException;       
   
}