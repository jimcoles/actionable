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


public class BMQAFormTrashList extends BMEntryFormList 
{
  
 public BMQAFormTrashList() throws OculusException
  {    
    super();    
  }


  public Object dolly() throws OculusException 
  { 
    BMQAFormTrashList gColl = new BMQAFormTrashList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }
 
  //-------------------------- IRModelElement -------------------------

  

  public String getLoadQuery()
    throws OculusException
  {
    String s = "";
     s = " SELECT ent.*,ctxt.CONTEXTOBJECTID, ifc.PRODLISTID  "+
             " FROM (ENTRYFORM ent LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=ent.FORMCLASSID) "+
             "   LEFT OUTER JOIN EXTENDSASC ext ON ext.SRCINTERFACEID=cls.DEFINTERFACEID "+         
             "   LEFT OUTER JOIN FORMCONTEXT ctxt ON ctxt.FORMID=ent.OBJECTID "+         
             "   LEFT OUTER JOIN FOLDER fld ON fld.OBJECTID=ctxt.CONTEXTOBJECTID "+           
             "   LEFT OUTER JOIN IFCPRODLISTUSAGE ifc ON ifc.INTERFACEID=cls.DEFINTERFACEID "+                              
             "  WHERE ext.DESTINTERFACEID="+IDCONST.IQUESTIONINPUT.getIIDValue()+
                  " AND ent.ISACTIVE=0 "+                        
                  " AND ent.CONFIGUREKIND<>"+ConfigKind.SUMMARY_ONLY.getIntValue()+                  
             " ORDER BY ent.NAME ";
    return s;        
  }// 

}