/**

@author Alok Pota
**/

package com.oculussoftware.api.repi;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.common.process.*;
import java.util.*;

/** 
Definition element of IRObject
 */
 
public interface IREntryForm extends IRModelElement
{

 
  //Getters & Setters
  public IRClass getFormClass() throws OculusException;      
  public IREntryForm setFormClass(IRClass cls) throws OculusException;      
  public IREntryForm setFormClass(IIID cls) throws OculusException;      
  public IRClass getFormSecondClass() throws OculusException;      
  public IREntryForm setFormSecondClass(IRClass cls) throws OculusException;      
  public IREntryForm setFormSecondClass(IIID cls) throws OculusException;      
  public IUser getCreator() throws OculusException;
  public java.sql.Timestamp getCreationDate() throws OculusException;
  public IREntryForm setFolder(IFolder fold) throws OculusException;  
  public IFolder getFolder() throws OculusException;  
  public IREntryForm setHelp(String s) throws OculusException;  
  public String getHelp() throws OculusException;  
  public IREntryForm setProductContext(IRProductContext att) throws OculusException;  
  public IRProductContext getProductContext() throws OculusException;  
  
  //Default the boolean in the next method as always true. All entry forms
  //by default are shown on both folder & conduit. If it is explicitly set to false
  //then it will not be shown on the conduit.
  public IREntryForm isConduit(boolean bit) throws OculusException;  
  public boolean isConduit() throws OculusException;  
  
  
    //Methods dealing with attributes
  public void removeAttribute(IRAttribute att) throws OculusException;    
  public IRPropertyMap getAttributes(IRObject obj) throws OculusException;    
  public IRModelElementList getGlobalAttributeList() throws OculusException;    
  public IRModelElementList getViewableAttributeList() throws OculusException;
  public IRModelElementList getEditableAttributeList() throws OculusException;  
  public boolean canEditAllRequiredFields() throws OculusException;
  public void removeAllAttributes() throws OculusException;  
  public void addAttribute(IRAttribute att) throws OculusException;
  public void addAttributeGroup(IRAttrAccessGroup grp,IRAttribute att) throws OculusException;  
  public boolean isAttributeDuplicate(IRAttribute att)throws OculusException;    
  public List getGroupsAndAttributes() throws OculusException;
  public void addBatchGroupsAndAttributes(List list) throws OculusException;  
  public void addScndBatchGroupsAndAttributes(List list) throws OculusException;  
  public void copyAttributeListFromType(IRType type)throws OculusException;    
  public void addAttributes(IRModelElementList attlist) throws OculusException;  
  
  
  
  //Render methods
  public void renderCreate(ITable tab) throws Exception; 
  public void renderView(IRObject obj,ITable tab) throws Exception;    
  public void renderEdit(IRObject obj,ITable tab) throws Exception;  
  public void renderQAView(IRObject obj,ITable tab) throws Exception;
  public void renderQAEdit(IRObject obj,ITable tab) throws Exception;
  public void renderQACreate(ITable tab) throws Exception;
  
  //Misc methods
  public IMarketInput getSummaryRecord() throws OculusException;    
  public IMarketInput getSummaryRecord(boolean ignoreDeletedOnes) throws OculusException;    
  public boolean hasInstances() throws OculusException;   
  public int countInstances() throws OculusException;   
  public int countResponses() throws OculusException;   
  public List getAllInputs(IRAttribute question) throws OculusException;   
  public List getMultiEnumInputs(IRAttribute question) throws OculusException;     
  public IREntryForm isByPass() throws OculusException;;
  public IREntryForm setEntity(String s) throws OculusException;;   
  public IREntryForm setPermissions(long[] arrs) throws OculusException;  
  public long[] getPermissions() throws OculusException;  
  public IREntryForm setQAClass(IRClass cls) throws OculusException;
  public boolean isUsed(IRAttribute att) throws OculusException;
  public boolean isQuestionnaire() throws OculusException;
  public boolean isPosted() throws OculusException;
  
  
  
}