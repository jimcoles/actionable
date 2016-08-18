package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

//Used by the SvtConfigFormAssign when showing global custom attributes
public class BMConfigurableAttributeList extends BMModelElementList 
{  
  
 public BMConfigurableAttributeList() throws OculusException
  {
    super();
  }
  
  //-------------------------- IRModelElement -------------------------
  
  protected String getLoadQuery()
		throws ORIOException
  {
    return " SELECT attr.* "+
           " FROM ATTRIBUTE attr "+
           " WHERE attr.ISACTIVE=1 AND attr.ATTRKIND="+AttributeKind.CUSTOM.getIntValue()+
           " ORDER BY attr.NAME ";       
  
  }// 
 
 	protected String getClassName() { return "Attribute"; }
  public Object dolly() throws OculusException    
    {    
     BMConfigurableAttributeList gColl = new BMConfigurableAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
     
    }     

  
}
    