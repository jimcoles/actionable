package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

//Returns only a list of the enumerated datatypes as the rest of the
//datatypes are fixed

public class BMDataTypeList extends BMModelElementList 
{
  
 public BMDataTypeList() throws OculusException
  {    
    super();    
  }


  
 
  //-------------------------- IRModelElement -------------------------

  public String getClassName() { return "DataType";}
  
  public String getLoadQuery()
  {
    return "SELECT * FROM DATATYPE WHERE ISACTIVE=1 and TYPEKIND="+Primitive.ENUM.getIntValue()+" ORDER BY NAME "; 
            
  }// 
    
    
   public Object dolly() throws OculusException    
    {    
     BMDataTypeList gColl = new BMDataTypeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
     
    }   

}