package com.oculussoftware.repos.bmr.view;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMDepartmentFormList extends BMEntryFormList 
{
  
 public BMDepartmentFormList() throws OculusException
  {    
    super();    
  }


  public Object dolly() throws OculusException 
  { 
    BMDepartmentFormList gColl = new BMDepartmentFormList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }
 
  //-------------------------- IRModelElement -------------------------

  
  
  public String getLoadQuery()
    throws OculusException
  {
    return "SELECT ent.OBJECTID FROM \"ENTRYFORM\" ent LEFT OUTER JOIN \"PERMISSIONGRANT\" perms ON perms.PAROBJECTID=ent.OBJECTID WHERE perms.PERMISSIONID="+PermEnum.FORM_USE.getID()+" AND perms.ACCESSORID="+getIID().getLongValue()+" AND ent.ISACTIVE=1 ORDER BY ent.NAME"; 
  }// 

}