/*
* $Workfile: IRAttribute.java $
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
* An IRAttribute is a repository representation of the different user defined and primitive data types
* available. All attributes have an underlying datatype (IRDataType). 
* 
* 
* @author Alok Pota
* @see com.oculussoftware.repos.bmr.BMDataType
*	@see com.oculussoftware.repos.bmr.BMModelElement
* @see com.oculussoftware.api.repi.IRModelElement
* @see com.oculussoftware.api.repi.IRDataType
*/

package com.oculussoftware.api.repi;


import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.sec.*;


public interface IRAttribute extends IRModelElement
{
	public static final String TABLE_CHAR = "CHARVALUE";
	public static final String TABLE_LONGCHAR = "LONGCHARVALUE";
	public static final String TABLE_TIME = "TIMEVALUE";
	public static final String TABLE_BOOLEAN = "BOOLEANVALUE";
	public static final String TABLE_BLOB = "BLOBVALUE";
	public static final String TABLE_ENUM = "ENUMVALUE";
  
  /** 
  *  Get the attribute kind of this attribute
  * @return com.oculussoftware.api.repi.AttributeKind
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.AttributeKind
  */

  public AttributeKind getAttrKind() throws OculusException;  
  
  /** 
  *  Get the underlying datatype of this attribute
  * @return com.oculussoftware.api.repi.IRDataType
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.IRDataType
  */

  public IRDataType getDataType() throws OculusException;  
  
  /** 
  *  Get the underlying primitive of this attribute
  * @return com.oculussoftware.api.repi.Primitive
  * @exception OculusException - There was an error accessing the definition object of the IRClass
  * @see com.oculussoftware.api.repi.Primitive
  */
  public Primitive getPrimitive() throws OculusException;  
  
  /** 
  *  Get the default value of the attribute
  * @return java.lang.Object
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public Object   getDefaultValue() throws OculusException;  
  
  /** 
  *  Get the deletekind flag for this attribute
  * @return com.oculussoftware.api.repi.DeleteKind
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  DeleteKind getDeleteKind() throws com.oculussoftware.api.sysi.OculusException;
  
  /** 
  *  Get the expression related to this attribute (if this attribute is aggregation of other attributes)
  * @return java.lang.String
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public String getExpression() throws OculusException;  
  
  /** 
  *  Get the user prompt associated with this attribute. This is what gets displayed on the UI for a question and answer kind attribute
  * @return java.lang.String
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public String getUserPrompt() throws OculusException;  
  
  /** 
  *  Get the maximum length of this attribute. 
  * @return int
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public int getMaxLength() throws OculusException;  
  
  /** 
  *  Get the unit label of this attribute
  * @return java.lang.String
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public String getUnitLabel() throws OculusException;  
  
  /** 
  *  Get the unit position of this attribute. The unitposition determines whether the unit label shows up on the left or right of the attribute
  * @return com.oculussoftware.api.repi.UnitPosition
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  UnitPosition getUnitPosition() throws OculusException;  
  
  
  /** 
  *  Set the attribute kind of this attribute. 
  * @param com.oculussoftware.api.repi.AttributeKind
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute setAttrKind(AttributeKind k) throws OculusException;  
  
  /** 
  *  Set the datatype of this attribute. 
  * @param com.oculussoftware.api.repi.IRDataType
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  IRAttribute setDataType(IRDataType id) throws OculusException;  
  
  
  /** 
  *  Set the datatype of this attribute. 
  * @param com.oculussoftware.api.repi.IIID (internal identifier of the datatype)
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  IRAttribute setDataType(IIID id) throws OculusException;  
  
  /** 
  *  Set the default value of the attribute. 
  * @param java.lang.Object
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  IRAttribute setDefaultValue(Object value) throws OculusException;
  
  /** 
  *  Set the attribute group permission value of the attribute. 
  * @param com.oculussoftware.api.sysi.sec.AttrGroupOper 
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute setUserPermission(AttrGroupOper oper) throws OculusException;
  
  /** 
  *  Set the deletekind flag of this attribute. 
  * @param com.oculussoftware.api.repi.DeleteKind 
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  void setDeleteKind(DeleteKind dk) throws com.oculussoftware.api.sysi.OculusException;
  
  
  /** 
  *  Set the expression of the attribute
  * @param java.lang.String
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute setExpression(String s) throws OculusException;  
  
  /** 
  *  Set the maximum length of the attribute
  * @param int
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute setMaxLength(int k) throws OculusException;  
  
  /** 
  *  Set the unit label of the attribute
  * @param java.lang.String
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute setUnitLabel(String s) throws OculusException;  
  
   /** 
  *  Set the unit position of the attribute
  * @param com.oculussoftware.api.repi.UnitPosition
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  IRAttribute setUnitPosition(UnitPosition id) throws OculusException;  
  
  /** 
  *  Set the user prompt of the attribute
  * @param java.lang.String
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public  IRAttribute setUserPrompt(String s) throws OculusException;  
  
  /** 
  *  Mark attribute for softdelete
  * 
  * @return com.oculussoftware.sysi.IPersistable (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IPersistable softdelete() throws OculusException;  
  
  /** 
  *  Return true of the attribute is an enumerated type
  * 
  * @return boolean
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public boolean isenum() throws OculusException;    
  
  /** 
  *  Return enumeration of the attribute
  * 
  * @return com.oculussoftware.api.repi.IREnumeration
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IREnumeration getEnum() throws OculusException;    
  
  /** 
  *  Return enumeration size of the attribute
  * 
  * @return int
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public int getEnumSize() throws OculusException;        
  
  
  /** 
  *  Make copy of the attribute
  * 
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute makecopy() throws OculusException;
  
  /** 
  *  Get the roll up attribute corresponding to the IRAttribute. In case of an aggregate attribute this returns 
  *  the attribute that gets rolled up.
  * @return com.oculussoftware.api.repi.IRAttribute (current object)
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public IRAttribute getRollUpAttribute() throws OculusException;
  
  /** 
  *  Get the attribute user permission
  *  
  * @return com.oculussoftware.api.repi.AttrGroupOper
  * @exception OculusException - There was an error accessing the definition object of the IRClass  
  */
  public AttrGroupOper getUserPermission() throws OculusException;
}