package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMTypeList extends BMModelElementList 
{
  
 public BMTypeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  
  public String getLoadQuery()
  {
    return "SELECT * FROM INTERFACE WHERE ISACTIVE=1 ORDER BY NAME "; 
            
  }// 
    
  public String getClassName()  { return "Type";}
  public Object dolly() throws OculusException 
  { 
    BMTypeList gColl = new BMTypeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}