package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMAttributeList extends BMModelElementList 
{
  
 public BMAttributeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  public String getLoadQuery()
  {
    return "SELECT * FROM ATTRIBUTE WHERE ISACTIVE=1 AND ATTRKIND<>"+AttributeKind.QUESTION.getIntValue()+"AND ATTRKIND<>"+AttributeKind.PRODENUM.getIntValue()+" ORDER BY NAME "; 
            
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMAttributeList gColl = new BMAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }    
  
   public IRPropertyMap convert2PropertyMap() throws ORIOException
  {
    try
    {
   IRPropertyMap map = new BMPropertyMap();
   int siz = this.size();
   for(int i = 0; i < siz; ++i)
    {
      IRAttribute att = (IRAttribute)getModelElement(i);
      String key = "prop"+att.getIID().toString();
      map.put(key,key);
    }
    return map;
  }
  catch(Exception ex) { throw new ORIOException(ex);}
  }
  
}