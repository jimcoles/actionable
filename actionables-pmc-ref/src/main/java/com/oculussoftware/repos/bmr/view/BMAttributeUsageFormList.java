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

public class BMAttributeUsageFormList extends BMEntryFormList 
{
  
 public BMAttributeUsageFormList() throws OculusException
  {    
    super();    
  }


  public Object dolly() throws OculusException 
  { 
    BMAttributeUsageFormList gColl = new BMAttributeUsageFormList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }
 
  //-------------------------- IRModelElement -------------------------

  
  
  public String getLoadQuery()
    throws OculusException
  {
     return "SELECT ent.OBJECTID,ctxt.CONTEXTOBJECTID "+
      "FROM ENTRYFORM ent LEFT OUTER JOIN "+
      "CLASSATTRGROUPASC casc ON "+
      "casc.CLASSID = ent.FORMCLASSID OR "+
      "casc.CLASSID = ent.SCNDCLASSID "+
      "   LEFT OUTER JOIN FORMCONTEXT ctxt ON ctxt.FORMID=ent.OBJECTID "+           
      " WHERE casc.ATTRIBUTEID = "+getIID()+" AND ent.ISACTIVE = 1 ";
  }

}