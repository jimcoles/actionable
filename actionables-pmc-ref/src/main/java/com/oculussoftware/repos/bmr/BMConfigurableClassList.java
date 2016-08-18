package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMConfigurableClassList extends BMClassList 
{
  
 public BMConfigurableClassList() throws OculusException
  {    
    super();    
  }


  
 
  //-------------------------- IRModelElement -------------------------

  
  
  public String getLoadQuery()
  {
    return "SELECT * FROM CLASS WHERE ISACTIVE=1 AND CONFIGUREKIND<>"+ConfigKind.NO_CONFIG.getIntValue()+" ORDER BY NAME "; 
            
  }// 

public Object dolly() throws OculusException    
    {    
     BMConfigurableClassList gColl = new BMConfigurableClassList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
     
    }  

}