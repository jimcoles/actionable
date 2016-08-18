package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;

import java.util.*;
import java.sql.*;

public class BMAttrGroupList extends BMModelElementList 
{
  
 public BMAttrGroupList() throws OculusException
  {    
    super();     
   }
 

  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "AttributeGroup";}
  public String getLoadQuery()
  {
    return "SELECT * FROM ATTRACCESSGROUP WHERE ISACTIVE=1 ORDER BY NAME "; 
            
  }// 
    
  public Object dolly() throws OculusException 
    { 
      BMAttrGroupList gColl = new BMAttrGroupList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
      
    }  


}