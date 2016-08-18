package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

//Please do not change this file. I need this to reuse attributes among question types

public class BMQuestionAttributeList extends BMModelElementList
{
  
 public BMQuestionAttributeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  protected String getLoadQuery()
    throws ORIOException
  {
     //Saleem I had to add DISTINCT objectId logic as the old methods gives me duplicate names     
     return "SELECT DISTINCT att.OBJECTID "+
      " FROM (ATTRIBUTE att LEFT OUTER JOIN INTERFACEATTRASC iit ON "+
      " iit.ATTRIBUTEID = att.OBJECTID) LEFT OUTER JOIN EXTENDSASC ext ON "+
      " ext.SRCINTERFACEID = iit.INTERFACEID LEFT OUTER JOIN DATATYPE dt ON "+ 
      " dt.OBJECTID = att.DATATYPEID WHERE ext.DESTINTERFACEID = "+IDCONST.IQUESTIONINPUT.getIIDValue()+            
      " AND att.ATTRKIND <> "+AttributeKind.CANNED.getIntValue()+
      " AND att.ATTRKIND <> "+AttributeKind.SYSTEM_GENERATED.getIntValue()+
      " AND att.ISACTIVE = 1 AND "+
      " (dt.TYPEKIND = "+Primitive.ENUM.getIntValue()+
      " OR dt.TYPEKIND = "+Primitive.MULTIENUM.getIntValue()+
      " OR dt.TYPEKIND = "+Primitive.MULTICHECK.getIntValue()+
      " OR dt.TYPEKIND = "+Primitive.RADIO.getIntValue()+")";
      
            
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMQuestionAttributeList gColl = new BMQuestionAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}