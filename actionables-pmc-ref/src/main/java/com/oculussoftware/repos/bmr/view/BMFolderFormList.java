package com.oculussoftware.repos.bmr.view;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.bmr.*;

import java.util.*;
import java.sql.*;

public class BMFolderFormList extends BMModelElementList 
{
  
 public BMFolderFormList() throws OculusException
  {    
    super();    
  }


  public Object dolly() throws OculusException 
  { 
    BMFolderFormList gColl = new BMFolderFormList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }
 
  //-------------------------- IRModelElement -------------------------

  public String getClassName()
  {
   return "EntryForm"; 
  }

  public String getLoadQuery() throws ORIOException
  {
      String s = "SELECT ent.*,perms.CONTEXTOBJECTID,ifc.PRODLISTID "+
        " FROM \"ENTRYFORM\" ent LEFT OUTER JOIN \"FORMCONTEXT\" perms ON perms.FORMID=ent.OBJECTID "+
        "  LEFT OUTER JOIN \"CLASS\" clss ON clss.OBJECTID=ent.FORMCLASSID "+            
        "  LEFT OUTER JOIN \"IFCPRODLISTUSAGE\" ifc ON ifc.INTERFACEID=clss.DEFINTERFACEID "+  
        "  WHERE ent.CONFIGUREKIND<>"+ConfigKind.SUMMARY_ONLY.getIntValue()+" AND ent.ISACTIVE=1 AND perms.CONTEXTOBJECTID="+getIID().getLongValue(); 
    return s;        
  }// 

}