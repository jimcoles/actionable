package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMEnumLiteralList extends BMModelElementList 
{
  
 public BMEnumLiteralList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Enumliteral";}
  public String getLoadQuery()
  {
    String s = null;
    
    try
    {
    s = "SELECT * FROM ENUMLITERAL WHERE ISACTIVE=1 AND ENUMERATIONID="+getIID()+" ORDER BY ORDERNUM "; 
    }
    catch(Exception ex) {}
    return s;
            
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMEnumLiteralList gColl = new BMEnumLiteralList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}