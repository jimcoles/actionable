package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMDispositionList extends BMModelElementList 
{
  
 public BMDispositionList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Disposition";}
  public String getLoadQuery()
    throws OculusException
  {
    return "SELECT * FROM DISPOSITION WHERE ISACTIVE=1 ";
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMDispositionList gColl = new BMDispositionList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}