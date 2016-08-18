package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;

import java.util.*;
import java.sql.*;

public class BMConfigurableAttrGroupList extends BMAttrGroupList
{
  
 public BMConfigurableAttrGroupList() throws OculusException
  {    
    super();     
   }
 

  //-------------------------- IRModelElement -------------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM ATTRACCESSGROUP WHERE ISACTIVE=1 AND CONFIGUREKIND<>"+ConfigKind.NO_CONFIG.getIntValue()+" ORDER BY NAME "; 
            
  }// 
    
  public Object dolly() throws OculusException    
    {    
     BMConfigurableAttrGroupList gColl = new BMConfigurableAttrGroupList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
     
    }  

}