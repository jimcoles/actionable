package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import java.util.*;

public interface IRType extends IRModelElement
{
 	
  
  
  
    
  public boolean isLeaf();
   public IRType isLeaf(boolean bln);
   
   public boolean isRoot();
   public IRType isRoot(boolean bln);
   
   
   //Methods that deal with the INTERFACEATTRASC table
   public void addAttribute(IRAttribute att, boolean bln) throws OculusException;
   public void addAttributeList(IRModelElementList list) throws OculusException;
   public void removeAttribute(IRAttribute att) throws OculusException; 
   public void removeAllAttributes() throws OculusException; 
   
   public List getAttributeList() throws OculusException;    
   public List getAttributeList(IIID typeIID) throws OculusException;    

   //Methods that deal with the EXTENDSASC table
   public void addExtension(IRType att) throws OculusException;
   public void bufferExtension(IRType att) throws OculusException;
   public void removeExtensions() throws OculusException; 
   public boolean isExtended() throws OculusException; 
   public IRClass getOwningClass() throws OculusException;   
   public IRClass getOwningClass(boolean editable) throws OculusException;   
   public IRType makecopy() throws OculusException;
}