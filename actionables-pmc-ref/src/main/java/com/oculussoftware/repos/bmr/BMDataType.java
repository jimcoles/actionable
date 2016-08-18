/**

@author Alok Pota
**/

/**
This class will be used to store datatypes associated with the attributes.
Datatypes are all primitives. A new Primitive.ENUM has been added to the Primitive class
so as to factor in enumerated types.
*/

package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMDataType extends BMModelElement implements IRDataType
{
  public static final String TABLE_NAME="DATATYPE";    
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";  
  public static final String COL_TYPEKIND="TYPEKIND";  
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
 
  //Instance variables
  private Primitive _primType;
  private ILock _lock;
  
	/**
	Construtor for creating new Attributes. Pass it the IRObjectContext	
	*/
  public BMDataType() throws OculusException
  {
		setGUID(new GUID()); 
  }

  protected void load(IDataSet rs) throws  OculusException
	{
    setPersState(PersState.PARTIAL);
   	setGUID(new GUID(rs.getString(COL_BYTEGUID).trim()));
    setIID(new SequentialIID(rs.getLong(COL_OBJECTID)));
    setName(rs.getString(COL_NAME).trim());
    setDescription(rs.getString(COL_DESC).trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
   	isActive(rs.getBoolean(COL_ISACTIVE));           	
   	setTypeKind(Primitive.getInstance(rs.getInt(COL_TYPEKIND)));          
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));
	}
  	
  public IRDataType setTypeKind(Primitive prim)
  {
    _primType = prim; 
   	if (getPersState().equals(PersState.UNMODIFIED))
			setPersState(PersState.MODIFIED);
   	return this;
  }
  
  public Primitive getTypeKind() { return _primType; }
  public ILock getLock() { return _lock; }

	public IPersistable delete( ) throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }

	public IPersistable softdelete( ) throws OculusException    
  {
    setPersState(PersState.MODIFIED);
    setDeleteState(DeleteState.DELETED);
   	isActive(false);
    return this;
  }

	protected String getCreateQuery()
		throws OculusException
	{
		return "INSERT INTO DATATYPE ("+
							"OBJECTID,"+
							"GUID,"+
							"ISACTIVE,"+
							"DELETESTATE,"+
							"CONFIGUREKIND,"+
							"TYPEKIND,"+
							"NAME,"+
							"SHORTDESCRIPTION"+
						") VALUES ("+
				      iid+","+
							"'"+getGUID()+"',"+
				      (isActive()?"1":"0")+","+
				      DeleteState.NOT_DELETED+","+
				      getConfigKind()+","+
							getTypeKind().getIntValue()+","+
							"'"+SQLUtil.primer(getName(),250)+"',"+
							"'"+SQLUtil.primer(getDescription(),250)+"'"+
						")";
	}
  
  protected String getUpdateQuery()
		throws OculusException
	{
    return "UPDATE DATATYPE SET "       
			      +COL_TYPEKIND+"="+getTypeKind().getIntValue()+","                
			      +COL_NAME+"='"+SQLUtil.primer(getName(),250)+"',"        
			      +COL_DESC+"='"+SQLUtil.primer(getDescription(),250)+"',"        
			      +"ISACTIVE="+(isActive()?"1":"0")+","
			      +"DELETESTATE="+getDeleteState()
			      +" WHERE OBJECTID="+getIID();
	}  

	protected String getDeleteQuery()
		throws OculusException
	{
		return "DELETE FROM DATATYPE WHERE OBJECTID="+getIID();
	}
  
	protected String getLoadQuery()
		throws OculusException
	{
		return "SELECT * FROM DATATYPE WHERE OBJECTID="+getIID()+" AND ISACTIVE=1";
	}
 
  public  Object dolly() throws OculusException       
  { 
    BMDataType state = new BMDataType();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setGUID(getGUID());
    state.setIID(getIID());
    state.setName(getName());
    state.isActive(isActive());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.setTypeKind(getTypeKind());
    state.setPersState(getPersState());
    return state;
  } 
    
  public boolean isenum() throws OculusException
  {
    Primitive prim = getTypeKind();
    if(prim == Primitive.ENUM ||  prim == Primitive.MULTIENUM ||  prim == Primitive.RADIO ||  prim == Primitive.MULTICHECK)
      return true;
    else
      return false;
  }
    
  public IRDataType makecopy() throws OculusException
  {
    IObjectContext context = getObjectContext();    
    IRDataType state = (IRDataType)context.getCRM().getCompObject(context,"DataType",(IDataSet)null,true);
    state.setName("Copy of " +getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.isActive(true);
    state.setConfigKind(getConfigKind());
    state.setTypeKind(getTypeKind());  
    
    IREnumeration renum= (IREnumeration)context.getCRM().getCompObject(context,"Enumeration",getIID(),false);
    IRModelElementList list = renum.getEnumLiterals();
    
    int size = list.size();
    for(int i =0; i < size; ++i)
    {
     IREnumliteral lit = (IREnumliteral)list.getModelElement(i);     
     lit.setEnumID(state.getIID());
     lit.makecopy(); 
    }
    return state;    
  }            

}