/**

@author Alok Pota
**/

/**
This class will be used to define and work with Enumerated literals associated
with an enumerated attribute type. This class i s a little different from others
Editing an Enum literal could mean adding new values to the database and not 
necessarily just updating them and so there are no seperate update & insert
routines. The class should be smart enough to identify which entries to update
and which to mark as delete. 
*/

/*****

Change history

Date       Modifier      Change
-------------------------------------------------
3/3/00    Alok           Changed private variable from a concrete ArrayList to ArrayList

**/

package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.repi.*;
import java.util.*;
import java.sql.*;

public class BMEnumliteral extends BMModelElement implements IREnumliteral
{
  public static final String TABLE_NAME="ENUMLITERAL";    
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";  
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_ORDER="ORDERNUM";  
  public static final String COL_ENUMID="ENUMERATIONID";    
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  
  //Instance variables
  private IIID _enumID;
  private IIID _refID;
  ILock _lock=null;
  int _ordernum;

  public BMEnumliteral()
		throws OculusException
  {
  	setGUID(new GUID());
  }
  
  public IREnumliteral setEnumID(IIID id)
  {    
   	_enumID = id;
	  if (getPersState().equals(PersState.UNMODIFIED))
	  	setPersState(PersState.MODIFIED);
		return this;	
  }
  
  public IREnumliteral setRefID(IIID id)
  {    
	  _refID = id;
	  if (getPersState().equals(PersState.UNMODIFIED))
	  	setPersState(PersState.MODIFIED);
	  return this;	
  }
  
  public IREnumliteral setOrderNum(int i)
  {    
	  _ordernum = i;
	  if (getPersState().equals(PersState.UNMODIFIED))
	  	setPersState(PersState.MODIFIED);
	  return this;	
  }
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    setGUID(new GUID(rs.getString(COL_BYTEGUID).trim()));
    setIID(getIID());
    setName(rs.getString(COL_NAME).trim());
    setDescription(rs.getString(COL_DESC).trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));          
    isActive(rs.getBoolean(COL_ISACTIVE));                      
    _enumID = getObjectContext().getRepository().makeReposID(rs.getLong(COL_ENUMID));              
    _ordernum = rs.getInt(COL_ORDER);          
  }
   
  public IIID getEnumID() { return _enumID; }
  public IIID getRefID() { return _refID; }
 	public int getOrderNum() throws ORIOException { return _ordernum; }

  protected String getUpdateQuery()
		throws OculusException
	{
		return " UPDATE ENUMLITERAL "+
					 " SET "+COL_NAME+"='"+SQLUtil.primer(name,250)+"',"        
									+COL_DESC+"='"+SQLUtil.primer(description,250)+"',"                
									+"ISACTIVE="+(isActive()?1:0)+","
									+"DELETESTATE="+getDeleteState()        
					+" WHERE "+COL_OBJECTID+"="+getIID();
	}

	protected String getLoadQuery()
		throws OculusException
	{
		return " SELECT * "+
					 " FROM ENUMLITERAL "+
					 " WHERE OBJECTID="+getIID();
	}

	protected String getCreateQuery()
		throws OculusException
	{
	  //setIID(context.getRepository().genReposID());
		//setOrderNum(_getMaxOrder()+1);
		return "INSERT INTO ENUMLITERAL ("+
							"OBJECTID,"+
							"ENUMERATIONID,"+
							"GUID,"+
							"ISACTIVE,"+
							"DELETESTATE,"+
							"CONFIGUREKIND,"+							
							"ORDERNUM,"+
							"NAME,"+
							"SHORTDESCRIPTION"+
							") VALUES ("
               +getIID()+","
               +getEnumID()+","
							 +"'"+getGUID()+"',"
               +(isActive()?1:0)+","  
               +DeleteState.NOT_DELETED+","                                          
               +ConfigKind.FULL+","                                         
               +getOrderNum()+","
               +"'"+SQLUtil.primer(getName())+"',"                    
               +"'"+SQLUtil.primer(getDescription(),250)+"'"
               +")";
	}

	protected String getDeleteQuery()
		throws OculusException
	{		
		return  "DELETE FROM ENUMLITERAL WHERE OBJECTID="+getIID();
		
	}

  protected int _getMaxOrder()
    throws OculusException
  {
    IQueryProcessor qp = null;
		int maxOrder = -1;
    String query = " SELECT MAX(ORDERNUM) as maximus "+
									 " FROM ENUMLITERAL "+
									 " WHERE ENUMERATIONID="+_enumID+" AND ISACTIVE=1 ";
    try 
    {         
			IRConnection jdtC = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = jdtC.createProcessor();         
      IDataSet rs = qp.retrieve(query);
      if (rs.next())
				maxOrder = rs.getInt("maximus"); 
    }
    finally { qp.close();}      
    return maxOrder;
  }
  
  public ILock getLock() { return _lock;}

	public IPersistable delete() throws ORIOException 
  {
    setPersState(PersState.DELETED);
    return this;
  }

	public IPersistable softdelete() throws ORIOException 
  {
    return this;
  }
   
	public  Object dolly() throws OculusException       
  { 
    BMEnumliteral state = new BMEnumliteral();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setIID(getIID());
    state.setGUID(getGUID());
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());    
    state.setEnumID(getEnumID());
    state.setPersState(getPersState());
    return state;
  } 
        
	public  IREnumliteral makecopy() throws OculusException       
  {
    IObjectContext context = getObjectContext();    
    IREnumliteral state = (IREnumliteral)context.getCRM().getCompObject(context,"Enumliteral",(IDataSet)null,true);
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.isActive(true);
    state.setConfigKind(getConfigKind());    
    state.setEnumID(getEnumID());  
    state.setOrderNum(getOrderNum());      
    return state;    
  }  
}
  
  
 