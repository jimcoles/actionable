package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMSpecDispositionList extends BMDispositionList
{
  
 public BMSpecDispositionList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  
  public String getLoadQuery()
    throws OculusException
  {
    return "SELECT disp.* FROM DISPOSITION disp LEFT OUTER JOIN PRODUCTCONTEXTITEM prod ON prod.DISPOSITIONID=disp.OBJECTID WHERE disp.ISACTIVE=1 AND prod.PRODCONTEXTLISTID="+getIID()+" ORDER BY prod.ORDERNUM ";
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMSpecDispositionList gColl = new BMSpecDispositionList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}