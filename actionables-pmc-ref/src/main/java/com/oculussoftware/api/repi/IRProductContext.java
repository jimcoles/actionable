/**

@author Alok Pota
**/

/**
An interface to store the context list
*/
package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import java.util.*;

public interface IRProductContext extends IRModelElement
{
   //Get a list of product versions 
   IRModelElementList getDispositions() throws OculusException;   
   void setDispositions(long[] dispositionIIDs) throws OculusException;    
   java.util.List notify(IMarketInput inp, long[] dispositionIIDs) throws OculusException;    
   void associate(IMarketInput inp) throws OculusException;    
   
}