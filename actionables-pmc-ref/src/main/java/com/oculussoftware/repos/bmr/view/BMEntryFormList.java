package com.oculussoftware.repos.bmr.view;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMEntryFormList extends BMModelElementList 
{

	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the list */
	public BMEntryFormList() throws OculusException
	{
    super();
	}


  //------------------------ Protected Methods --------------------------------
  protected String getClassName() { return "EntryForm"; }

  protected String getLoadQuery()
    throws OculusException
  {
      
  String query = null;          
  
  if (!getIID().equals(IDCONST.IQUESTIONINPUT.getIIDValue()))
     
    {
      query = " SELECT ent.*,ctxt.CONTEXTOBJECTID, ifc.PRODLISTID "+
             " FROM (ENTRYFORM ent LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=ent.FORMCLASSID) "+             
             "   LEFT OUTER JOIN EXTENDSASC ext ON ext.SRCINTERFACEID=cls.DEFINTERFACEID "+         
             "   LEFT OUTER JOIN FORMCONTEXT ctxt ON ctxt.FORMID=ent.OBJECTID "+                
             "   LEFT OUTER JOIN IFCPRODLISTUSAGE ifc ON ifc.INTERFACEID=cls.DEFINTERFACEID "+                 
             "  WHERE ext.DESTINTERFACEID="+getIID()+
                   " AND ent.ISACTIVE=1 "+
                  " AND ent.CONFIGUREKIND<>"+ConfigKind.NO_CONFIG.getIntValue()+
             " ORDER BY ent.NAME ";
    }
   else
    {
     
     query = " SELECT ent.*,ctxt.CONTEXTOBJECTID, ifc.PRODLISTID "+
             " FROM (ENTRYFORM ent LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=ent.FORMCLASSID) "+
             "   LEFT OUTER JOIN EXTENDSASC ext ON ext.SRCINTERFACEID=cls.DEFINTERFACEID "+         
             "   LEFT OUTER JOIN FORMCONTEXT ctxt ON ctxt.FORMID=ent.OBJECTID "+         
             "   LEFT OUTER JOIN FOLDER fld ON fld.OBJECTID=ctxt.CONTEXTOBJECTID "+           
             "   LEFT OUTER JOIN IFCPRODLISTUSAGE ifc ON ifc.INTERFACEID=cls.DEFINTERFACEID "+                 
             "  WHERE ext.DESTINTERFACEID="+getIID()+
                  " AND ent.ISACTIVE=1 "+                        
                  " AND fld.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+
             " ORDER BY ent.NAME ";
     
    }   
   return query;      
  }




  
//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductList object, but without the ObjectContext */
  public Object dolly() throws OculusException 
  { 
    BMEntryFormList gColl = new BMEntryFormList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl.reset();
    return gColl;      
  }


}