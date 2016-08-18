/*
* $Workfile: IRDataType.java $
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
* An IRDataType is a repository representation of the different user defined and primitive data types
* available. All attributes have an underlying datatype (IRDataType). All IRDataTypes have
* and underlying Primitive (com.oculussoftware.api.repi.Primitive)
* 
* 
* @author Alok Pota
* @see com.oculussoftware.repos.bmr.BMDataType
*	@see com.oculussoftware.repos.bmr.BMModelElement
* @see com.oculussoftware.repos.bmr.IRModelElement
*/


package com.oculussoftware.api.repi;


import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;

public interface IRDataType extends IRModelElement
{
  
  
  public static final String DATATYPE_BYTE = "d1b88fc47f9928f1dd0000003c721d2880630000";
  public static final String DATATYPE_SHORT = "d1b88fc47f9928f1dd0000003c721d2880620000";
  public static final String DATATYPE_INTEGER = "d1b88fc47f9928f1dd0000003c721d2880610000";
  public static final String DATATYPE_LONG = "d1b88fc47f9928f1dd0000003c721d2880600000";
  public static final String DATATYPE_FLOAT = "d1b88fc47f9928f1dd0000003c721d28805f0000";
  public static final String DATATYPE_DOUBLE = "d1b88fc47f9928f1dd0000003c721d28805e0000";
  public static final String DATATYPE_CHAR = "d1b88fc47f9928f1dd0000003c721d28805d0000";
  public static final String DATATYPE_BOOLEAN = "d1b88fc47f9928f1dd0000003c721d28805c0000";
  public static final String DATATYPE_STRING = "d1b88fc47fd7feb9dd00000041aae79a80630000";
 
 /** 
  *  Get the underlying Primitive of this IRDataType
  * @return com.oculussoftware.api.repi.Primitive
  * @exception OculusException - There was an error accessing the default attribute group
  * @see com.oculussoftware.api.repi.Primitive
  */
  public Primitive getTypeKind();  
 
 /** 
  *  Set the underlying Primitive of this IRDataType
  * @return com.oculussoftware.api.repi.Primitive  
  * @exception OculusException - There was an error accessing the default attribute group
  * @see com.oculussoftware.api.repi.Primitive
  */
  public IRDataType setTypeKind(Primitive k);   
     
 /** 
  *  Make copy of this datatype
  * @return (current object)
  * @exception OculusException - There was an error accessing the default attribute group
  * @see com.oculussoftware.api.repi.Primitive
  */
  public IRDataType makecopy() throws OculusException;
 
 /** 
  *  Check to see if this datatype is an enumeration. 
  * @return boolean
  * @exception OculusException - There was an error accessing the default attribute group
  * @see com.oculussoftware.api.repi.IREnumeration
  */
  public boolean isenum() throws OculusException;
}