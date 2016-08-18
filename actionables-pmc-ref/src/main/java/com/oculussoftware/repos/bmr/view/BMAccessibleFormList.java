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
/**
Gets the list of forms available to the user. IID is that of the User object.
Checks for GROUPS and DEPARTMENTS of the user and returns entryforms available to that user
used in SvtMIConduitList, SvtMICreate,SvtArtCreate,
**/
public class BMAccessibleFormList extends BMEntryFormList 
{
  
 public BMAccessibleFormList() throws OculusException
  {    
    super();    
  }


  public Object dolly() throws OculusException 
  { 
    BMAccessibleFormList gColl = new BMAccessibleFormList();
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
    
    s =  " SELECT ent.OBJECTID "+
				" FROM ocurepos.ENTRYFORM ent "+
				" LEFT OUTER JOIN ocurepos.PERMISSIONGRANT perms ON perms.PAROBJECTID=ent.OBJECTID "+
				" LEFT OUTER JOIN ocurepos.ENUMVALUE enumval ON enumval.VALUE=perms.ACCESSORID "+
				" WHERE perms.PERMISSIONID="+PermEnum.FORM_USE.getID()+ 
				" AND ent.ISACTIVE=1 "+
				" AND enumval.ATTRIBUTEID = "+IDCONST.DEPARTMENT.getIIDValue()+
				" AND enumval.PAROBJECTID = "+getIID()+
				" UNION "+
				" SELECT ent.OBJECTID "+
				" FROM ocurepos.ENTRYFORM ent "+
				" LEFT OUTER JOIN ocurepos.PERMISSIONGRANT perms ON perms.PAROBJECTID=ent.OBJECTID "+
				" LEFT OUTER JOIN ocurepos.USERGROUPASC userasc ON userasc.GROUPID=perms.ACCESSORID "+
				" LEFT OUTER JOIN ocurepos.USERGROUP grp ON grp.OBJECTID=userasc.GROUPID "+
				" WHERE perms.PERMISSIONID="+PermEnum.FORM_USE.getID()+ 
				" AND ent.ISACTIVE=1 "+
				" AND grp.DELETESTATE = 1 "+
				" AND userasc.USERID = "+getIID();
				
		
    return s;
  }// 

}