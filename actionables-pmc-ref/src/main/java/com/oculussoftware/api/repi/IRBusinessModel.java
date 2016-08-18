package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/**
*
*/
public interface IRBusinessModel extends IRModel
{
  public IRStateMachineColl getStateMachineColl() throws OculusException;
  public IRStateMachine getStateMachineObject(IIID id, boolean edit) throws OculusException;
  public IRStateMachine getStateMachineObject(IRClass c, boolean edit) throws OculusException;
  public IRState getStateObject(IRClass c, IIID s, boolean edit) throws OculusException;
  public IRState getStateObject(IIID sm, IIID s, boolean edit) throws OculusException;
  public IRTransition getTransitionObject(IIID sm, IIID s, IIID t, boolean edit) throws OculusException;
  public IRTransition getTransitionObject(IRClass c, IIID s, IIID t, boolean edit) throws OculusException;
  public IRAction getActionObject(IIID sm, IIID s, IIID t, IIID a, boolean edit) throws OculusException;
  public IRAction getActionObject(IRClass c, IIID s, IIID t, IIID a, boolean edit) throws OculusException;
  public IRGuard getGuardObject(IIID sm, IIID s, IIID t, IIID g, boolean edit) throws OculusException;
  public IRGuard getGuardObject(IRClass c, IIID s, IIID t, IIID g, boolean edit) throws OculusException;  
  
  
  //---get a Meta object
  
  public IRClass getClass(IIID id, boolean edit) throws OculusException;
  public IRProductContext getProductContext(IIID id, boolean edit) throws OculusException;
  public IRDisposition getDisposition(IIID id, boolean edit) throws OculusException;  
  public IRPackage getPackage(IIID id, boolean edit) throws OculusException;
  public IRDataType getDataType(IIID id, boolean edit) throws OculusException;
  public IREnumeration getEnum(IIID id, boolean edit) throws OculusException;
  public IREnumliteral getEnumliteral(IIID id, boolean edit) throws OculusException;
  public IRProdEnumeration getProdEnum(IIID id, boolean edit) throws OculusException;
  public IRAttribute getAttribute(IIID id, boolean edit) throws OculusException;
  public IRType getType(IIID id, boolean edit) throws OculusException;
  public IRAttrAccessGroup getAttrAccessGroup(IIID id, boolean edit) throws OculusException;  
  public IREntryForm getEntryForm(IIID id, boolean edit) throws OculusException;
  
  //---get Meta object list
  
  //These lists are for internal use
   public IRModelElementList getProductContextList() throws OculusException;  
    public IRModelElementList getDispositionList() throws OculusException;  
  public IRModelElementList getDataTypeList() throws OculusException;  
  public IRModelElementList getAttributeList() throws OculusException;
  public IRModelElementList getAttrGroupList()  throws OculusException;
  public IRModelElementList getTypeList() throws OculusException;  
  public IRModelElementList getEnumList() throws OculusException;
  public IRModelElementList getEntryFormList() throws OculusException;
  
  //These lists will be show to the user at config time
  public IRModelElementList getConfigurableAttributeList() throws OculusException;
  public IRModelElementList getConfigurableAttrGroupList()  throws OculusException;
  
  public IRDataType getChar()  throws OculusException;
  public IRDataType getLongChar()  throws OculusException;
  public IRDataType getBoolean()  throws OculusException;  
  public IRDataType getTime()  throws OculusException;
  public IRDataType getInteger()  throws OculusException;
  public IRDataType getDecimal()  throws OculusException;
  
  
  public IRModelElementList getFeatureTypes() throws OculusException;  
  public IRModelElementList getCategoryTypes() throws OculusException;
  public IRModelElementList getProductVersionTypes() throws OculusException;
  public IRModelElementList getProductTypes() throws OculusException;  
  public IRModelElementList getStandardInputTypes() throws OculusException;  
  public IRModelElementList getArticleInputTypes() throws OculusException;  
  public IRModelElementList getQuestionInputTypes() throws OculusException;    
  public IRModelElementList getWinLossInputTypes() throws OculusException;    
  public IRModelElementList getContactTypes() throws OculusException;    
  public IRModelElementList getOrganizationTypes() throws OculusException;    
  
  public IRModelElementList getFormAttrs(IIID formIID) throws OculusException;
  public IRModelElementList getFormAttrs(IDataSet args) throws OculusException;
  //get feature category link attrs
  public IRModelElementList getFeatureAttrs() throws OculusException;  
  //get feature attrs
  public IRModelElementList getFeature1Attrs() throws OculusException;  
  //get feature aggregate atts
  public IRModelElementList getFeatureAggAttrs() throws OculusException;  
  public IRModelElementList getCategoryAttrs() throws OculusException;
  public IRModelElementList getProductVersionAttrs() throws OculusException;
  public IRModelElementList getProductAttrs() throws OculusException;    
  public IRModelElementList getStandardInputAttrs() throws OculusException;  
  public IRModelElementList getArticleInputAttrs() throws OculusException;  
  public IRModelElementList getQuestionInputAttrs() throws OculusException;    
  public IRModelElementList getReactionAttrs() throws OculusException;  
  public IRModelElementList getPSAttrs() throws OculusException;    
  public IRModelElementList getContactAttrs() throws OculusException;    
  public IRModelElementList getOrganizationAttrs() throws OculusException;    

  public IRModelElementList getReactionTypes() throws OculusException;
  public IRModelElementList getProblemStatementTypes() throws OculusException;
  public IRModelElementList getAlternativeTypes() throws OculusException;
  
  public IRModelElementList getDepartments() throws OculusException;
 
  
  
    
}