package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMDispositionFolderList extends BMModelElementList 
{
  
 public BMDispositionFolderList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Disposition";}
  public String getLoadQuery()
  {
    String s = null;
    
    try
    {
    s = "SELECT * FROM DISPOSITION WHERE TARGETLOCATIONID="+getIID();
    }
    catch(Exception ex) {}
    return s;
            
  }// 
    
  public Object dolly() throws OculusException 
  { 
    BMDispositionFolderList gColl = new BMDispositionFolderList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }

}