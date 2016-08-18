package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.repos.bmr.statemachine.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;

import java.sql.*;
import java.util.*;
import java.math.*;

/**
* Filename:    BMRepository.java
* Date:        2-2-2000
* Description: BMRepository is a singleton.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class BMRepository implements IRBusinessModel
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * CODE REVIEW		Saleem Shafi	5/28/00		
  *
  */
  
  protected IObjectContext _context;
  protected PersState      _pstate;
  protected IGUID          _guid;
  protected IIID           _iid;
  protected String         _name;
  protected String         _description; 
  protected DeleteState     _deletekind;
  protected ConfigKind     _configkind;
  protected boolean        _isactive;
  
  /**
  *
  */
  public BMRepository() throws OculusException
  {
    _guid = new GUID();
    _iid = new SequentialIID(123);//repos iid
  }//end Constuctor

  //--------------------------- IObject ---------------------------- 
  //accessors
  public IGUID getGUID() { return _guid; }
  public IObjectContext getObjectContext() { return _context; }
  //mutators
  public IObject setObjectContext(IObjectContext c) { _context = c; return this; }
  
  //--------------------------- IPersistable -----------------------
  //accessors
  public PersState getPersState() { return _pstate; }
  public IIID getIID() throws ORIOException { return _iid; }
  //mutators
  public IPersistable setPersState(PersState state) { _pstate = state; return this; }
  public IPersistable setIID(IIID i) throws ORIOException { _iid = i; return this; }
  //functional methods
  public IPersistable load() throws OculusException
  { return this; }
  public IPersistable save() throws ORIOException
  { return this; }
  public IPersistable delete() throws ORIOException
  { return this; }
  
  //-------------------------- IPoolable ---------------------------
  
  public boolean isLocked() { return PoolMgr.getInstance().isLocked(this); }
  
  public Object dolly() throws OculusException 
  {
    BMRepository bmr = new BMRepository();
    bmr.setIID(getIID());
    bmr.setObjectContext(getObjectContext());
    bmr.setPersState(getPersState());
    bmr.setName(getName());
    bmr.setDescription(getDescription());
    bmr.setDeleteState(getDeleteState());
    
    // ALOK: I think we're missing configKind and deleteKind in the dolly()
    
    bmr.isActive(isActive());
    return bmr; 
  }
  
  public boolean isRemoveable() { return false; }

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = (IIID)args.get("IID");

    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);
    if (iid == null)
    {
      iid = getObjectContext().getRepository().genReposID();
      setPersState(PersState.NEW);
    }//end if
    else
      setPersState(PersState.UNINITED);
    setIID(iid);
    return this;
  }//
  
  //------------------------- IRElement ---------------------------- 
  //accessors
  public String getName() throws ORIOException { return _name; }
  public String getDescription() throws ORIOException { return _description; }
  //mutators
  public IRElement setName(String n) throws ORIOException { _name = n; return this; }
  public IRElement setDescription(String d) throws ORIOException { _description = d; return this; }
  
  //-------------------------- IRModelElement ---------------------- 
  //accessors
  public DeleteState getDeleteState() throws ORIOException { return _deletekind; }
  public ConfigKind getConfigKind() throws ORIOException { return _configkind; }
  public boolean isActive() throws ORIOException { return _isactive; }
  //mutators
  public IRModelElement setDeleteState(DeleteState d) throws ORIOException { _deletekind = d; return this; }
  public IRModelElement setConfigKind(ConfigKind d) throws ORIOException { _configkind = d; return this; }
  public IRModelElement isActive(boolean a) throws ORIOException { _isactive = a; return this; }
  
  
  
// ALOK: You shouldn't need these methods.  Let's get rid of them when we fix the getStateMachine().
	  //Database Connections
	  private IRConnection getDatabaseConnection() throws OculusException, ObjectNotFoundException, AccessException, ClassNotFoundException, InstantiationException, IllegalAccessException
	  {
	    return _context.getCRM().getDatabaseConnection(_context);
	  }//end getDatabaseConnection
	  
//	  private void returnDatabaseConnection(IRConnection conn) throws ORIOException
//	  {
//	    try
//	    {
//	      _context.getCRM().returnDatabaseConnection(conn);
//	    }//end try
//	    catch(OculusException ex) { throw new ORIOException(ex); }
//	  }//end returnDatabaseConnection
	  
  //-------------------- IRBusinessModel ---------------------------
  
  /**
  *
  */
  public IRStateMachineColl getStateMachineColl() throws OculusException
  {
    return (IRStateMachineColl)_context.getCRM().getCompObject(_context,"StateMachineColl",new SequentialIID(1));  
  }//
  
  /**
  * 
  */
  public IRStateMachine getStateMachineObject(IIID id, boolean edit) throws OculusException
  {
    return (IRStateMachine)_context.getCRM().getCompObject(_context,"StateMachine",id,edit);
  }//end getStateMachine
  
  /**
  * ************have to resolve the iid for the IRClass
  */
  
  // ALOK:  This method should not be going to the database.  It should be requesting the
  // state machine object from IRClass.  However, the implementation of BMClass.getStateMachine()
  // refers to this method because it does not have a reference to the state machine IID.
  // We need to add the state machine IID to BMClass and ask the CRM for the State Machine.
  
  public IRStateMachine getStateMachineObject(IRClass c, boolean edit) throws OculusException
  {
    IRConnection conn = null; IQueryProcessor qp = null;  IIID iid = null;
    try
    {
      IRepository rep = getObjectContext().getRepository();
      conn = getDatabaseConnection();
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT "+BMStateMachine.COL_OBJECTID+
                                  " FROM "+BMStateMachine.TABLE+
                                  " WHERE "+BMStateMachine.COL_CLASSID+"="+c.getIID().getLongValue());
      if(rs.next())
        iid = rep.makeReposID(rs.getLong(BMStateMachine.COL_OBJECTID));
    }//end try
    catch(Exception ex) { throw new ORIOException(ex); }
    finally{
    if(qp!=null) qp.close();
//    if(conn!=null) returnDatabaseConnection(conn);
    }
    return getStateMachineObject(iid, edit);
  }//end getStateMachine
  
  /**
  *
  */
  public IRState getStateObject(IRClass c, IIID s, boolean edit) throws OculusException
  {
    return getStateMachineObject(c, false).getStateObject(s, edit); 
  }//end getStateObject
  
  /**
  *
  */
  public IRState getStateObject(IIID sm, IIID s, boolean edit) throws OculusException
  {
    return getStateMachineObject(sm, false).getStateObject(s, edit); 
  }//end getStateObject
  
  /**
  *
  */
  public IRTransition getTransitionObject(IIID sm, IIID s, IIID t, boolean edit) throws OculusException
  {
  	return getStateObject(sm,s,false).getTransitionObject(t,edit);
// Saleem changed this to get more reuse.
//   return getStateMachineObject(sm, false).getStateObject(s, false).getTransitionObject(t, edit); 
  }
  
  /**
  *
  */
  public IRTransition getTransitionObject(IRClass c, IIID s, IIID t, boolean edit) throws OculusException
  {
  	return getStateObject(c,s,false).getTransitionObject(t,edit);
// Saleem changed this to get more reuse.
//    return getStateMachineObject(c, false).getStateObject(s, false).getTransitionObject(t, edit);
  }//end getTransitionObject
  
  /**
  *
  */
  public IRAction getActionObject(IIID sm, IIID s, IIID t, IIID a, boolean edit) throws OculusException
  {
  	return getTransitionObject(sm,s,t,false).getActionObject(a,edit);
// Saleem changed this to get more reuse;
//    return getStateMachineObject(sm, false).getStateObject(s, false).getTransitionObject(t, false).getActionObject(a, edit); 
  }//end getActionObject
  
  /**
  *
  */
  public IRAction getActionObject(IRClass c, IIID s, IIID t, IIID a, boolean edit) throws OculusException
  {
  	return getTransitionObject(c,s,t,false).getActionObject(a,edit);
// Saleem changed this to get more reuse;
//    return getStateMachineObject(c, false).getStateObject(s, false).getTransitionObject(t, false).getActionObject(a, edit); 
  }//end getActionObject
  
  /**
  *
  */
  public IRGuard getGuardObject(IIID sm, IIID s, IIID t, IIID g, boolean edit) throws OculusException
  {
  	return getTransitionObject(sm,s,t,false).getGuardObject(g,edit);
// Saleem changed this to get more reuse;
//    return getStateMachineObject(sm, false).getStateObject(s, false).getTransitionObject(t, false).getGuardObject(g, edit); 
  }//end getGuardObject
  
  /**
  *
  */
  public IRGuard getGuardObject(IRClass c, IIID s, IIID t, IIID g, boolean edit) throws OculusException
  {
  	return getTransitionObject(c,s,t,false).getGuardObject(g,edit);
// Saleem change this to get more reuse.
//    return getStateMachineObject(c, false).getStateObject(s, false).getTransitionObject(t, false).getGuardObject(g, edit); 
  }//end getGuardObject    
    
  
  
  //----------- Meta stuff
    
  public IRAttribute getAttribute(IIID id, boolean edit)  throws OculusException
  {
   return (IRAttribute)_context.getCRM().getCompObject(_context,"Attribute",id,edit); 
  }
  
  public IRClass getClass(IIID id, boolean edit)  throws OculusException
  {
   return (IRClass)_context.getCRM().getCompObject(_context,"Class",id,edit); 
  }
  public IRProductContext getProductContext(IIID id, boolean edit)  throws OculusException
  {
   return (IRProductContext)_context.getCRM().getCompObject(_context,"ProductContext",id,edit); 
  }
  public IRDisposition getDisposition(IIID id, boolean edit)  throws OculusException
  {
   return (IRDisposition)_context.getCRM().getCompObject(_context,"Disposition",id,edit); 
  }
  
  public IRType getType(IIID id, boolean edit)  throws OculusException
  {
   return (IRType)_context.getCRM().getCompObject(_context,"Interface",id,edit); 
  }
  
  public IRPackage getPackage(IIID id, boolean edit)  throws OculusException
  {
   return (IRPackage)_context.getCRM().getCompObject(_context,"Package",id,edit); 
  }
  
  public IRDataType getDataType(IIID id, boolean edit)  throws OculusException
  {
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id,edit); 
  }
  
  public IREnumeration getEnum(IIID id, boolean edit)  throws OculusException
  {
   return (IREnumeration)_context.getCRM().getCompObject(_context,"Enumeration",id,edit); 
  }
  
  public IREnumliteral getEnumliteral(IIID id, boolean edit)  throws OculusException
  {
   return (IREnumliteral)_context.getCRM().getCompObject(_context,"Enumliteral",id,edit); 
  }
  
  
  // ALOK: What is a ProdEnum??
  public IRProdEnumeration getProdEnum(IIID id, boolean edit)  throws OculusException
  {
   return (IRProdEnumeration)_context.getCRM().getCompObject(_context,"ProdEnumeration",id,edit); 
  }
  
  public IRAttrAccessGroup getAttrAccessGroup(IIID id, boolean edit)  throws OculusException
  {
   return (IRAttrAccessGroup)_context.getCRM().getCompObject(_context,"AttributeGroup",id,edit); 
  }
  
  public IREntryForm getEntryForm(IIID id, boolean edit)  throws OculusException
  {
   return (IREntryForm)_context.getCRM().getCompObject(_context,"EntryForm",id,edit); 
  }
  
  //----------- Meta lists 

// ALOK : These meta-lists need to be combined.  We don't need a separate class for each list, just
// for each list-type.  We can reuse the same class for multiple lists by passing meaningful IIDs
// instead of new SequentiallIID(1).
  
  public IRModelElementList getAttributeList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"AttributeList",new SequentialIID(1)); 
  }
  
  public IRModelElementList getConfigurableAttributeList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"ConfigurableAttributeList",new SequentialIID(1)); 
  }
    
  
  public IRModelElementList getTypeList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"InterfaceList",new SequentialIID(1)); 
  }
  
 
  
  public IRModelElementList getAttrGroupList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"AttrGroupList",new SequentialIID(1)); 
  }
  
  public IRModelElementList getDispositionList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"DispositionList",new SequentialIID(1)); 
  }
  public IRModelElementList getProductContextList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"ProductContextList",new SequentialIID(1)); 
  }
  
  public IRModelElementList getConfigurableAttrGroupList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"ConfigurableAttrGroupList",new SequentialIID(1)); 
  }

  public IRModelElementList getEnumList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"EnumerationList",new SequentialIID(1)); 
  }
  
  
  public IRModelElementList getEntryFormList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"EntryFormList",new SequentialIID(1)); 
  }
  
  public IRModelElementList getDataTypeList() throws OculusException
  {
   return (IRModelElementList)_context.getCRM().getCompObject(_context,"DataTypeList",new SequentialIID(1)); 
  }
    
  
  /*
  Get a list of the different TYPES of our Canned Entities  
  Canned entities: feature,category,product,version,featurecategory,marketinput
  alternatives, reaction (this will keep growing)
  */
	protected IRModelElementList getEntryFormList(IIID type) throws OculusException
	{
		return (IRModelElementList)_context.getCRM().getCompObject(_context, "EntryFormList", type); 
	}
	
  public IRModelElementList getFeatureTypes() throws OculusException
  {
   return getEntryFormList(IDCONST.IFEATURE.getIIDValue()); 
  }
    
  public IRModelElementList getCategoryTypes() throws OculusException
  {
   return getEntryFormList(IDCONST.ICATEGORY.getIIDValue()); 
  }
    
  public IRModelElementList getProductVersionTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.IPRODUCTVERSION.getIIDValue());
  }
    
  public IRModelElementList getProductTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.IPRODUCT.getIIDValue());
  }
    
  public IRModelElementList getReactionTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.IREACTION.getIIDValue());
  }
  
  public IRModelElementList getProblemStatementTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.IPROBLEMSTATEMENT.getIIDValue());
  }
  
  public IRModelElementList getAlternativeTypes() throws OculusException
  {
		return getEntryFormList(IDCONST.IALTERNATIVE.getIIDValue()); 
  }
  
  public IRModelElementList getStandardInputTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.ISTANDARDINPUT.getIIDValue());
  }   
  
  public IRModelElementList getArticleInputTypes() throws OculusException
  {
   return getEntryFormList(IDCONST.IARTICLEINPUT.getIIDValue()); 
  }
  
  public IRModelElementList getQuestionInputTypes() throws OculusException
  {
  return getEntryFormList(IDCONST.IQUESTIONINPUT.getIIDValue());
  }
  
  public IRModelElementList getWinLossInputTypes() throws OculusException
  {
     return getEntryFormList(IDCONST.IWINLOSSINPUT.getIIDValue());
  }
  
  
  
  public IRDataType getChar()  throws OculusException
  {   
   IIID id = IDCONST.CHAR.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }
  
  public IRDataType getLongChar()  throws OculusException
  {   
   IIID id = IDCONST.LONGCHAR.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }
  
  public IRDataType getInteger()  throws OculusException
  {   
   IIID id = IDCONST.INTEGER.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }
  
  public IRDataType getDecimal()  throws OculusException
  {   
   IIID id = IDCONST.DECIMAL.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }
  
  public IRDataType getBoolean()  throws OculusException
  {   
   IIID id = IDCONST.BOOLEAN.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }

  public IRDataType getTime()  throws OculusException
  {   
   IIID id = IDCONST.TIME.getIIDValue();
   return (IRDataType)_context.getCRM().getCompObject(_context,"DataType",id);     
  }
  
	public IRModelElementList getFormAttrs(IIID formIID) throws OculusException
	{
	return (IRModelElementList)_context.getCRM().getCompObject(_context,"FormAttrs", formIID);   
	}
	public IRModelElementList getFormAttrs(IDataSet args) throws OculusException
	{
  	return (IRModelElementList)_context.getCRM().getCompObject(_context,"FormAttrs", args);   
	}
	
  public IRModelElementList getFeatureAttrs() throws OculusException
  {
		return getFormAttrs(IDCONST.IFEATURECATEGORYLINK.getIIDValue());
  }  

// ALOK: This method needs to be renamed.  Feat1Attrs doesn't really mean anything.  Let's see
// if we can find a more meaningful name.  
  public IRModelElementList getFeature1Attrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IFEATURE.getIIDValue());
  }  
  
  public IRModelElementList getFeatureAggAttrs() throws OculusException
  {
  return (IRModelElementList)_context.getCRM().getCompObject(_context,"FeatAggAttrs",new SequentialIID(1));   
  }  
  
  public IRModelElementList getCategoryAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.ICATEGORY.getIIDValue());
  }  
  
  public IRModelElementList getProductAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IPRODUCT.getIIDValue());
  }  
  
  public IRModelElementList getProductVersionAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IPRODUCTVERSION.getIIDValue());
  }  
  
  public IRModelElementList getStandardInputAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.ISTANDARDINPUT.getIIDValue());
  }  
  
  public IRModelElementList getArticleInputAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IARTICLEINPUT.getIIDValue());
  }  
  
  public IRModelElementList getQuestionInputAttrs() throws OculusException
  {
  return (IRModelElementList)_context.getCRM().getCompObject(_context,"QuestionAttrs",new SequentialIID(1));   
  }  
  
  public IRModelElementList getReactionAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IREACTION.getIIDValue());
  }  
  
  public IRModelElementList getPSAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IPROBLEMSTATEMENT.getIIDValue());
  }  
  
  public IRModelElementList getContactAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IUSER.getIIDValue());
  }  

  public IRModelElementList getOrganizationAttrs() throws OculusException
  {
  return getFormAttrs(IDCONST.IORGANIZATION.getIIDValue());
  }  
  
  public IRModelElementList getContactTypes() throws OculusException
  {
	  return getEntryFormList(IDCONST.IUSER.getIIDValue()); 
  }
  
  public IRModelElementList getOrganizationTypes() throws OculusException
  {
		return getEntryFormList(IDCONST.IORGANIZATION.getIIDValue());
  }

public IRModelElementList getDepartments() throws OculusException
  {
    
     return (IRModelElementList)_context.getCRM().getCompObject(_context,"EnumliteralList",IDCONST.DEPARTMENT_ENUM.getIIDValue());   
  }
  
public IPersistable softdelete()
  throws OculusException
{ return null; }            

public boolean isUsed() throws ORIOException { return false;}

public IPersistable recover() throws OculusException { return null;}

  
  
  
}//end BMRepository