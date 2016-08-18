/**

@author Alok Pota
**/

/**
An interface to store enumerated types
*/
package com.oculussoftware.api.repi;

import java.util.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;

public interface IREnumliteral extends IRModelElement
{
   
  
  
   public IREnumliteral setEnumID(IIID id);
   public IIID getEnumID();
   public int getOrderNum() throws ORIOException;
   public IREnumliteral setOrderNum(int j) throws ORIOException;
   public IREnumliteral setRefID(IIID id);
   public IREnumliteral makecopy() throws OculusException;
   public IIID getRefID();
   
}