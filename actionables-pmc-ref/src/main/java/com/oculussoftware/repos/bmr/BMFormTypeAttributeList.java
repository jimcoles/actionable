package com.oculussoftware.repos.bmr;

/*
* $Workfile: BMFormTypeAttributeList.java $
* Create Date:  5-12-2000
* Description: 
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author A. Pota
* Version 1.2
*
*/

/*
* $History: BMFormTypeAttributeList.java $
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 9/11/00    Time: 2:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * 2091 - made query more clear.  Now joins in Interface table twice: once
 * for 'child' ifc, once for 'parent' ifc and constrains via the
 * extendsasc table.
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Removed order by name logic since UI side is doing ordering.
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Issues 1795 and 2091.
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Added comments and header only.  No code changes.
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

/**
* Gets the list of all (or some depending on parameters) attributes associated
* with the specified base interface IID.
*/
public class BMFormTypeAttributeList extends BMModelElementList
{

  //-----------------------------------------------------------------------------
  // Static vars and methods
  //-----------------------------------------------------------------------------
  private static Map _childClasses = new HashMap();
  private static AttributeKind[] _defaultAttrKindFilter =
    new AttributeKind[] {AttributeKind.CUSTOM, AttributeKind.AGGREGATE};
  
  /**
  
  */
  private static String _getBaseInterfaceList(IIID ifciid)
  {
    String retVal = ""+ifciid.getLongValue();
    
    if (ifciid == IDCONST.IFEATURECATEGORYLINK.getIIDValue()) {
      retVal = ifciid.getLongValue() + ", " + IDCONST.IFEATURE.getIIDValue();
    }
    
    return retVal;
  }
  
  private static boolean _includeChildClassAggrs(IIID iid)
  {
    boolean retVal = false;
    if (iid == IDCONST.IPRODUCTVERSION.getIIDValue())
      retVal = true;
      
    return retVal;
  }
  
  private static String _getChildClassAggrSQL(IIID iid)
  {
    String retVal = "";
    if (iid == IDCONST.IPRODUCTVERSION.getIIDValue())
    {
      retVal ="(ext.DESTINTERFACEID="+IDCONST.ICATEGORY.getIIDValue()
             +" AND att.ATTRKIND="+AttributeKind.AGGREGATE.getIntValue()+")";
    } 
    
    return retVal;
  }

  //-----------------------------------------------------------------------------
  // Instance vars
  //-----------------------------------------------------------------------------
  private AttributeKind _arg_attrKinds[] = null;
  private IRCollection  _classes = null;
  
  //-----------------------------------------------------------------------------
  // Constructor(s)
  //-----------------------------------------------------------------------------
  
  public BMFormTypeAttributeList() throws OculusException
  {
    super();
  }
  
  /** Psuedo constructor */
	public IPoolable construct(IObjectContext context, IDataSet args) throws OculusException
	{
		super.construct(context, args);
    _arg_attrKinds = (AttributeKind[]) args.get("ATTRKINDS");
    
    // TODO: add logic to get scope item attributes
    
    return this;
	}

  
  //-----------------------------------------------------------------------------
  // Instance methods
  //-----------------------------------------------------------------------------
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  
  /**
  * This query must limit the attribute list based on the following:
  * 1. (maybe) User's access to attribute groups based on permissions permissions.
  * 2. (maybe) Only include attributes that are 'in use' under the specified scope object(s).
  * 3. The desired attribute kinds specified by calling object (or use default).
  */
  protected String getLoadQuery()
		throws ORIOException
  {
  
/* from adhoc query tool
JOIN interfaceattrasc iaa ON iaa.attributeid = atr.objectid
--, interface ifcpar
JOIN interface ifcchild ON iaa.interfaceid = ifcchild.objectid
JOIN extendsAsc exa ON exa.srcinterfaceid = ifcchild.objectid
JOIN interface ifcpar ON exa.destinterfaceid = ifcpar.objectid
JOIN Class cls ON cls.definterfaceid = exa.srcinterfaceid
*/  
    // joins attribute table to interface and constrains to only child interfaces
    // that extend the specified parent interface
    String retSQL = " SELECT DISTINCT att.OBJECTID "
          +" FROM ATTRIBUTE att "
          +"  JOIN INTERFACEATTRASC iit ON iit.ATTRIBUTEID=att.OBJECTID "
          +"  JOIN INTERFACE ifcchild ON iit.INTERFACEID=ifcchild.OBJECTID "
          +"  JOIN EXTENDSASC ext ON ext.SRCINTERFACEID=ifcchild.OBJECTID "
          +"  JOIN INTERFACE ifcpar ON ext.DESTINTERFACEID=ifcpar.OBJECTID "
          +"  JOIN CLASS cls ON cls.DEFINTERFACEID = ifcchild.OBJECTID "
          +" WHERE att.ISACTIVE = 1"
          +" AND (ifcpar.OBJECTID in (" + _getBaseInterfaceList(getIID()) + ")" 
          +"      AND att.ATTRKIND in (" + _getAttrKindFilter() + ")"
          + (_includeChildClassAggrs(getIID()) ? " OR (" + _getChildClassAggrSQL(getIID()) + ")" : "")
          +" ) ";
    return retSQL;
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMFormTypeAttributeList gColl = new BMFormTypeAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

  private String _getAttrKindFilter()
  {
    String retVal = "";
    AttributeKind kinds[] = _defaultAttrKindFilter;
    if(_arg_attrKinds != null)
      kinds = _arg_attrKinds;
      
    retVal = StringUtil.buildCommaDelList(Arrays.asList(kinds), IntEnumLister.INSTANCE);
    return retVal;
  }
  
  // NOTE: not currently in use as of 9/6/2000
  private String _getClassList()
    throws OculusException
  {
    IRType irtype = (IRType) getObjectContext().getRepository().getBMRepository().getType(getIID(), false);
    String retVal = "("+irtype.getOwningClass().getIID().getLongValue()+")";
    // TODO: finish logic to get class list based for type, scope, etc....
    return retVal;
  }
  
}