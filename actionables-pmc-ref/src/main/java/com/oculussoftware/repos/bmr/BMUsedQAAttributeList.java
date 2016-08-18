package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMUsedQAAttributeList extends BMModelElementList 
{
  
   
 public BMUsedQAAttributeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  public String getLoadQuery()
    throws OculusException
  {

    return "  SELECT DISTINCT(attr.OBJECTID) "+
    " FROM ATTRIBUTE attr "+
    " LEFT OUTER JOIN BOOLEANVALUE bool ON attr.OBJECTID=bool.ATTRIBUTEID "+
    " LEFT OUTER JOIN CHARVALUE char ON attr.OBJECTID=char.ATTRIBUTEID "+
    " LEFT OUTER JOIN LONGCHARVALUE longchar ON attr.OBJECTID=longchar.ATTRIBUTEID "+
    " LEFT OUTER JOIN TIMEVALUE timval ON attr.OBJECTID=timval.ATTRIBUTEID "+
    " LEFT OUTER JOIN ENUMVALUE enumval ON attr.OBJECTID=enumval.ATTRIBUTEID "+
    " LEFT OUTER JOIN ENUMSELECTION enumsel ON attr.OBJECTID=enumsel.ATTRIBUTEID "+  
    " LEFT OUTER JOIN MARKETINPUT mi ON mi.CLASSID="+getIID()+
    " WHERE enumval.PAROBJECTID = mi.OBJECTID OR "+
    " bool.PAROBJECTID = mi.OBJECTID OR "+
    " timval.PAROBJECTID = mi.OBJECTID OR "+
    " char.PAROBJECTID = mi.OBJECTID OR "+
    " enumsel.PAROBJECTID = mi.OBJECTID OR "+  
    " longchar.PAROBJECTID = mi.OBJECTID ";

  } 
    
  public Object dolly() throws OculusException 
  { 
    BMUsedQAAttributeList gColl = new BMUsedQAAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);    
    gColl.reset();
    return gColl;      
  }    
  



}