/**

@author Alok Pota
**/

/**
An interface to store the context list
*/
package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

public interface IRDisposition extends IRModelElement
{
   
   IFolder getFolder() throws OculusException;
   IIID getProdVerIID() throws OculusException;
   IUserColl getRecipients() throws OculusException;
   
   
   IRDisposition setFolder(IFolder folder) throws OculusException;      
   IRDisposition setProdVerIID(IIID id) throws OculusException;   
   IRDisposition setRecipients(long[] users) throws OculusException;
  
     
}