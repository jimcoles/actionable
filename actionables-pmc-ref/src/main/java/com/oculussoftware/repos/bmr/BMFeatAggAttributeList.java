package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMFeatAggAttributeList extends BMAttributeList
{
  
 public BMFeatAggAttributeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  public String getLoadQuery()
  {
    String s1 = "SELECT att.OBJECTID FROM \"ATTRIBUTE\" att LEFT OUTER JOIN \"INTERFACEATTRASC\" iit ON iit.ATTRIBUTEID=att.OBJECTID LEFT OUTER JOIN \"EXTENDSASC\" ext ON ext.SRCINTERFACEID=iit.INTERFACEID WHERE ext.DESTINTERFACEID="+IDCONST.IFEATURECATEGORYLINK.getLongValue()+"AND att.ATTRKIND=1 AND att.ISACTIVE=1 AND att.DATATYPEID=-375 "; 
    String s2 = " UNION ";
    String s3 = "SELECT att.OBJECTID FROM \"ATTRIBUTE\" att LEFT OUTER JOIN \"INTERFACEATTRASC\" iit ON iit.ATTRIBUTEID=att.OBJECTID LEFT OUTER JOIN \"EXTENDSASC\" ext ON ext.SRCINTERFACEID=iit.INTERFACEID WHERE ext.DESTINTERFACEID="+IDCONST.IFEATURECATEGORYLINK.getLongValue()+"AND att.ATTRKIND=1 AND att.ISACTIVE=1 AND att.DATATYPEID=-376 "; 
    return s1+s2+s3;
            
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMFeatAggAttributeList gColl = new BMFeatAggAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}