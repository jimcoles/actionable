/*
* $Workfile: IRClass.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* An IRClass exposes the methods to deal with a repository meta-data element called "BMClass"
* All business objects have an underlying meta-data also called the definition object which is defined by IRClass
* An IRClass can be thought of as Java Class and the com.oculussoftware.bus.BusinessObject
* can be thought of as a Java Object. Just as all Java Objects are defined by a class. All
* businessobjects have IRClass as their definition.
* @author Alok Pota
* @see com.oculussoftware.repos.bmr.BMClass
*	
*/

package com.oculussoftware.api.repi;
import com.oculussoftware.api.sysi.*;
import java.util.*;

 
public interface IRClass extends IRModelElement
{
  /** 
  *  Attributes associated with the IRClass need to be in a default attribute group
  *	 which stores all the attributes of the IRClass. Attribute view/edit access permissions
  *  are defined on the attribute access group. A single class could have multiple attribute
  *  access group.
  * @return IRAttrAccessGroup
  * @exception OculusException - There was an error accessing the default attribute group
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  */
  public IRAttrAccessGroup getDefaultAttrGroup() throws OculusException;      
  
  /** 
  *  Get the definition object of the IRClass. Just as a Java Class can implement an interface
  *	 An IRClass can have its definition as IRType (which is similar in semantic to a Java interface)
  *  
  * @return IRType
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IRType
  */
	public IRType getDefinition() throws OculusException;      
  
  /** 
  *  Get the root definition object of the IRClass. Just as a Java Interface can implement other interfaces
  *	 An IRType of an IRClass can extend another interface IRType (which is similar in semantic to a Java interface extending another interface)
  *  Only one level of extension is allowed and thefore all IRClass can have one root type. 
  *  
  * @return IRType
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IRType
  */
	
  public IRType getRootDefinition() throws OculusException;      
  
  /** 
  *  Get the internal identifier of the rootdefinition of an IRClass. The internal identifier
  *	 is a unique value that is used to identify the specific IRType from the underlying data store
  *  
  * @return IIID
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IIID
  */	
  public IIID getRootDefinitionID() throws OculusException;      
  
  /** 
  *  Set the internal identifier of the rootdefinition of an IRClass. The internal identifier
  *	 is a unique value that is used to identify the specific IRType from the underlying data store  
  * @return IRClass (current object) 
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IIID
  */	
  public IRClass setRootDefinition(IIID id) throws OculusException;      
  
  /** 
  *  Get the IRClass associated with the root definition of this IRClass. This call first finds the root
  *  interface associated with the IRClass and then finds the IRClass asscoiated with this root interface.
  *  There is a one to correspondence between an IRClass and root IRType. An IRClass can have only one root IRType
  * @return IRClass (the current object)  
  * @param  IIID - The internal identifier of the root type.
  * @exception OculusException - There was an error accessing the definition object of the IRClass 
  * @see com.oculussoftware.api.repi.IIID
  */	
  public IRClass getBaseClass() throws OculusException;      
  
  /** 
  *  Get the IREntryForm associated with the IRClass. An IREntryForm is an entity which wraps over an
  *  IRClass. The entryform returned could have this IRClass as its base or a secondary class.
  * @return IREntryForm 
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IREntryForm
  */	
  public IREntryForm getEntryForm() throws OculusException;      
  
  
  /** 
  *  Get the IREntryForm associated with the IRClass. An IREntryForm is an entity which wraps over an
  *  IRClass. The entryform returned could have this IRClass as its base class or base definition object.
  * @return IREntryForm 
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IREntryForm
  */	
  
  public IREntryForm getForm() throws OculusException;      
	
	/** 
  *  Set the definition of the current class.
  * @return IRClass ( current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @param  IRType - The root type.
  * @see com.oculussoftware.api.repi.IRClass
  */	
  public IRClass setDefinition(IRType id) throws OculusException;    
  
  /** 
  *  Set the definition of the current class  
  * @return IRClass ( current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @param  IIID - The internal identifier of the root type.
  * @see com.oculussoftware.api.repi.IRClass
  */
  public IRClass setDefinition(IIID id) throws OculusException;    
	
	
	/** 
  *  Get the user label asscoiated with this class. Usually obtained from the end UI.
  * @return String ( current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass    
  */
  public String getUserLabel() throws OculusException;      
  
  /** 
  *  Set the definition of the current class  
  * @param  IIID - The internal identifier of the root type.
  * @return IRClass ( current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRClass
  */  
  public IRClass setUserLabel(String s) throws OculusException;      
  
  /** 
  *  Check to see if the class is a leaf level class.
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */    
  public boolean isLeaf() throws OculusException;
  
  /** 
  *  Set the tree level status of the class in the meta-data hierarchy
  * @param boolean (true makes the class a leaf node)  
  * @return IRClass (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */    
  public IRClass isLeaf(boolean bln) throws OculusException;
  
  
  /** 
  *  Check to see if the class is a root level class.
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */    
  public boolean isRoot() throws OculusException;
  
  /** 
  *  Set the tree level status of the class in the meta-data hierarchy
  * @param boolean (true makes the class a root node)  
  * @return IRClass (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */    
  public IRClass isRoot(boolean bln) throws OculusException;
   
  
  /** 
  *  Get the global list of attributes associated with the class.   
  * @return IRModelElementList (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRModelElementList
  * @see com.oculussoftware.api.repi.IRAttribute
  */      
  public IRModelElementList getGlobalAttributeList() throws OculusException;
  
  /** 
  *  Get the viewable list of attributes associated with the class. This method takes into account
  * specific permissions assciated with the attribute access groups linked to the IRClass and accordingly
  * returns only those attributes which can be viewed by an end user.  
  * @return IRModelElementList (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRModelElementList
  * @see com.oculussoftware.api.repi.IRAttribute
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.busi.sec.AttrGroupOper
  */      
  public IRModelElementList getViewableAttributeList() throws OculusException;
  
  /** 
  *  Get the editable list of attributes associated with the class. This method takes into account
  * specific permissions assciated with the attribute access groups linked to the IRClass and accordingly
  * returns only those attributes which can be edited by an end user.  
  * @return IRModelElementList (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRModelElementList
  * @see com.oculussoftware.api.repi.IRAttribute
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.busi.sec.AttrGroupOper
  */      
  public IRModelElementList getEditableAttributeList() throws OculusException;
  
  
  /** 
  *  Get the map of attributes associated with specific object of this class. 
  * @param IRObject (the underlying object whose definition is this IRClass)
  * @return IRPropertyMap (the current object)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRPropertyMap
  * @see com.oculussoftware.api.repi.IRAttribute
  * @see com.oculussoftware.api.repi.IRObject
  */      
  public IRPropertyMap getAttributes(IRObject obj) throws OculusException;    
  
  /** 
  *  Copy the all attributes associated with a specific type into the type of this class
  *  
  * @param IRType (the underlying object whose definition is this IRClass)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRType
  * @see com.oculussoftware.api.repi.IRAttribute  
  */      
  public void copyAttributeListFromType(IRType type)throws OculusException;
    
  
  /** 
  *  Update the order of the attribute associated with the class. Attributes are linked to the
  *  class and have an order number which determines the order in which they render on the UI.
  * @param IRAttribute (the attribute to be updated)
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)
  * @param IRClass (this object)
  * @param int (integer used to define the order)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */      
  public void  updateAttribute(IRAttribute att, IRAttrAccessGroup atg, IRClass cls,int kk)throws OculusException;  
  
  /** 
  *  Update the isRequired bit of the attribute associated with the class. Attributes that are required for a given class
  *  force the user to enter some value for them (on the UI level). A value of true forces the user enter a value for that attribute
  * @param IRAttribute (the attribute to be updated)
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)
  * @param boolean (true forces the attribute to be a required field on the class)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void updateIsRequired(IRAttribute att,IRAttrAccessGroup atg, int bln) throws OculusException;  
  
  /** 
  *  Add the attribute to the class under the given attribute group. Creates a new association between the class, attribute
  * and the attribute access group.   
  *  
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)  
  * @param IRAttribute (the attribute to be updated)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void addAttributeGroup(IRAttrAccessGroup grp,IRAttribute att) throws OculusException;  
  
  /** 
  *  Add the attribute to the class under the given attribute group. Creates a new association between the class, attribute
  * and the attribute access group also specifiy whether that attribute is required. Default is not required.
  *  
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)  
  * @param IRAttribute (the attribute to be updated)  
  * @param boolean (a flag to indicate if the attribute is a required field on the class)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void addAttributeGroup(IRAttrAccessGroup grp,IRAttribute att, boolean isrequred) throws OculusException;
  
  /** 
  *  Add the attribute to the class under the given attribute group. Creates a new association between the class, attribute
  * and the attribute access group also specify the order that attribute is required. Default order is the next largest available number.
  *  
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)  
  * @param IRAttribute (the attribute to be updated)  
  * @param int (a flag to indicate if the attribute is a required field on the class)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void addAttributeGroup(IRAttrAccessGroup grp,IRAttribute att, int order) throws OculusException;
  
  
  
  /** 
  *  Add the attribute to the class under a specific attribute group and indicate if the 
  * attribute is required and also indicate the order of the association of this attribute.
  *  
  * @param IRAttrAccessGroup (the attribute access group under which the previous attribute is to be located defines the context under which to find the attribute for this class)  
  * @param IRAttribute (the attribute to be updated)  
  * @param int (a flag to indicate if the attribute is a required field on the class)  
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg, boolean isreq, int order) throws OculusException;

  /** 
  *  Remove the attribute from the class. An attribute can be associated only once with a class 
  * and so this method removes that one associate between the class and the attribute
  *    
  * @param IRAttribute (the attribute to be updated)    
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void removeAttribute(IRAttribute att) throws OculusException;
  
  
  /** 
  *  Removes all attributes associated with the class. 
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public void removeAllAttributes() throws OculusException;
  
  
  /** 
  *  Check to see if the attribute is a duplicate. A class can have an attribute associated with it only once.
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.api.repi.IRAttribute  
  */        
  public boolean isAttributeDuplicate(IRAttribute att)throws OculusException;
  
  
  /** 
  *  Get a list of groups and attributes associated with a class.  
  * @param com.oculussoftware.api.repi.IRAttribute  
  * @return java.util.List 
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.util.Tuple
  */        
  public List getGroupsAndAttributes() throws ORIOException;
  
  /** 
  *  Copy the groups and attributes of an existing IRClass to that of the current class. Duplicate attributes are ignored
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.api.repi.IRAttrAccessGroup  
  * @see com.oculussoftware.api.repi.IRAttribute    
  */        
  public void copyGroupsAndAttributes(IRClass targetCls) throws ORIOException;
  
  /** 
  *  A convenience method that adds a list of class-attribute-attribute access group assciations
  *  to the class
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see java.util.List  
  */        
  public void addBatchGroupsAndAttributes(List list) throws ORIOException;
  
  /** 
  *  A convenience method that adds a list of class-attribute-attribute access group assciations
  *  to the class. Same as above method but the additions don't get committed to the data store
  * @param java.util.List
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see java.util.List  
  */        
  public void bufferBatchGroupsAndAttributes(List list) throws ORIOException;
  
  
  /** 
  *  
  *  A convenience method that takes in a list of class-attribute-attribute access group assciations
  *  to the class and uses those associations to update the corresponding associations for all the underlying objects of that class.
  *  so that object properties/attributes always mirror those of its meta-data or class attributes. 
  *  This method deletes, updates and adds the associatiosn based on flags set on the individual associations. The associiations are 
  *  stored as a Tuple object
  * @param java.util.List
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.util.Tuple  
  */        
  public void fixObjectAttributes(List list) throws OculusException; 
  
  
  /** 
  *  
  *  A convenience method that takes in a list of class-attribute-attribute access group assciations
  *  to the class and uses those associations to update the corresponding associations for all the underlying objects of that class.
  *  so that object properties/attributes always mirror those of its meta-data or class attributes. 
  *  This method only adds the associatiosn based on flags set on the individual associations. The associiations are 
  *  stored as a Tuple object
  * @param java.util.List
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.util.Tuple  
  */        
  public void addObjectAttributes(List list) throws OculusException;  
  
  
  /** 
  *  
  *  Gets the list of underlying IRObjects whose definitions are IRClass
  *  to the class and uses those associations to update the corresponding associations for all the underlying objects of that class.
  *  so that object properties/attributes always mirror those of its meta-data or class attributes. 
  *  This method only adds the associatiosn based on flags set on the individual associations. The associiations are 
  *  stored as a Tuple object
  * @return java.util.List
  * @exception OculusException - There was an error accessing the definition object of the IRClass   
  * @see com.oculussoftware.util.Tuple  
  */        
  public List getObjectList() throws OculusException; 
  
  
  /** 
  *  
  *  Gets a map of underlying IRClass objects and their attributes. 
  * @return java.util.Map
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public Map getObjectAttributeMap() throws OculusException; 
    
  
  /** 
  *  
  *  Set the root definition of this IRClass 
  * @param com.oculussoftware.api.repi.IRType
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public void setRootType(IRType type)throws OculusException;
  
  
  /** 
  *  
  *  Determine if this class has a statemachine associated with it.  A statemachine is used
  * to manage the different business states an object can go through.
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public boolean hasStateMachine() throws OculusException;
  
  
  /** 
  *  
  *  Get the statemachine associated with the IRClass.  A statemachine is used
  * to manage the different business states an object can go through.
  * @return com.oculussoftware.api.repi.IRStateMachine 
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public IRStateMachine getStateMachine() throws OculusException;
  
  /**    
  *  Determine if this class has a statemachine associated with it.  A statemachine is used
  * to manage the different business states an object can go through.
	* @param boolean (true indicates open for edit)
	* @return com.oculussoftware.api.repi.IRStateMachine
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public IRStateMachine getStateMachine(boolean editable) throws OculusException;
  
  
  /** 
  *  
  *  Do a softdelete on the IRClass. Marks it as deleted instead of hard deleting from the data store
  * 
  * @return com.oculussoftware.api.sysi.IPersistable
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public IPersistable softdelete() throws OculusException; 
  
  /** 
  *  
  *  Returns true if the IRClass has actual instances (business objects that use this class as their definition id)
  * 
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public boolean hasInstances() throws OculusException;   
  
  /** 
  *  
  * Returns the number of actual instances (business objects that use this class as their definition id) 
  * 
  * @return int
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public int countInstances() throws OculusException;   
  
  /** 
  *  
  *  Make a copy of the IRClass
  * 
  * 
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */        
  public IRClass makecopy() throws OculusException; 
  
 
  /** 
  *  
  *  Returns true if there are objects using the IRClass as their definition and if these objects are active (i.e., not softdeleted)
  * 
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */            
  public boolean hasActiveInstances() throws OculusException; 
  
  
  /** 
  *  
  * Add attributes to the class
  * 
  * @param com.oculussoftware.api.repi.IRModelElementList
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */            
  public void addAttributes(IRModelElementList attribList) throws OculusException;  
  
  /** 
  *  
  * Add attributes to the class
  * 
  * @param java.util.List
  * @exception OculusException - There was an error accessing the definition object of the IRClass     
  */            
  public void addAttributes(List attribList) throws OculusException;  
  
}