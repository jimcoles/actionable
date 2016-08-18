/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 
 ISSUE DES00091    APota          5/26                 Removed double quote problem

*/

package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;



public class BMAttrAccessGroup extends BMModelElement implements IRAttrAccessGroup
{
  public static final String TABLE_NAME="ATTRACCESSGROUP";  
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";    
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  public static final String COL_ORDER="ORDERNUM";   
   ILock _lock;
   ConfigKind _configkind;
   int _order;   

  /**
  Construtor for creating new Attributes. Pass it the IRObjectContext  
  */
  
  public BMAttrAccessGroup() throws OculusException
  {
    setGUID(new GUID());
  }
    
	public IRAttrAccessGroup setOrder(int id) 
	{
	  _order  = id;
		if (getPersState().equals(PersState.UNMODIFIED))
			setPersState(PersState.MODIFIED);
		return this;	
	}

  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    setGUID(new GUID((String)rs.getString(COL_BYTEGUID).trim()));
    setIID(new SequentialIID(rs.getLong(COL_OBJECTID)));
    setName((String)rs.getString(COL_NAME).trim());
    setDescription((String)rs.getString(COL_DESC).trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));          
    setOrder(rs.getInt(COL_ORDER));          
    isActive(rs.getBoolean(COL_ISACTIVE));             
  }
  
  public ILock getLock() { return _lock; }

  public int getOrder() { return _order; }
    

  public IPersistable delete( ) throws ORIOException
  {
    setPersState(PersState.DELETED);
    return this;
  }
   
   public IPersistable softdelete( ) throws OculusException    
  {
		if (getPersState().equals(PersState.UNMODIFIED))
			setPersState(PersState.MODIFIED);
    isActive(false);
		setDeleteState(DeleteState.DELETED);
    return this;
  }
  

	protected String getCreateQuery()
		throws OculusException
  {
    isActive(true);
    setDeleteState(DeleteState.NOT_DELETED);
    return "INSERT INTO ATTRACCESSGROUP ("+
							"OBJECTID,"+
							"GUID,"+
							"ISACTIVE,"+
							"DELETESTATE,"+
							"CONFIGUREKIND,"+
							"ORDERNUM,"+
							"NAME,"+
							"SHORTDESCRIPTION"+
						") VALUES ("+
				      getIID()+","+
							"'"+getGUID()+"',"+
				      (isActive()?"1":"0")+","+
				      DeleteState.NOT_DELETED+","+
				      getConfigKind()+","+
				      getOrder()+","+
							"'"+SQLUtil.primer(getName(),250)+"',"+
							"'"+SQLUtil.primer(getDescription(),250)+"'"+
						")";
  }
  

  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE " + TABLE_NAME+
		       " SET "+
			      COL_ORDER+"="+getOrder()+","+
			      COL_NAME+"='"+SQLUtil.primer(getName(),250)+"',"+
			      COL_DESC+"='"+SQLUtil.primer(getDescription(),250)+"',"+
			      "ISACTIVE="+(isActive()?"1":"0")+","+
			      "DELETESTATE="+getDeleteState()+
			      " WHERE "+COL_OBJECTID+"="+getIID();
  }
    
  protected String getDeleteQuery()
    throws OculusException
  {
    isSingleton(false);
    return " DELETE FROM ATTRGROUPGRANT WHERE ATTRGROUPID="+getIID()+                
           " DELETE FROM " + TABLE_NAME+
					 " WHERE "+COL_OBJECTID+"="+getIID();
  }
  
  
  protected String getLoadQuery()
    throws OculusException
  {
    return " SELECT * "+
					 " FROM "+TABLE_NAME+
					 " WHERE "+COL_OBJECTID+"="+getIID()+" AND "+COL_ISACTIVE+"=1";
  }
  

	public int getMaxOrder() throws OculusException
  {
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
		int maxOrder = -1;
    try  
	  {
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();
	    IDataSet rs = qp.retrieve("SELECT COUNT(*) as MAXIMUS FROM ATTRACCESSGROUP WHERE ISACTIVE=1 AND CONFIGUREKIND<>"+ConfigKind.NO_CONFIG.getIntValue());
	    if (rs.next())
				maxOrder = rs.getInt("MAXIMUS");
  	}
    finally
		{
			if (qp != null) qp.close();
//    	returnDatabaseConnection(jdtC);
    }      
    return maxOrder;
  }

	public  Object dolly() throws OculusException
  { 
    BMAttrAccessGroup state = new BMAttrAccessGroup();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setIID(getIID());
		state.setGUID(getGUID());
    state.setName(getName());
    state.setDescription(getDescription());
    state.isActive(isActive());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.setPersState(getPersState());
    return state;
  }
  

	public boolean isUsed() throws OculusException
  { 
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    boolean yes = false;
    try  
	  {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      IDataSet rs = qp.retrieve(
			 	"SELECT ATTRGROUPID FROM CLASSATTRGROUPASC "+
				"WHERE ATTRGROUPID="+getIID());				
	    yes = rs.next();
	  }
    finally
		{
			if (qp != null) qp.close();
// No need to return db connection.  Plus, if you do, and this method is called
// inside of a transaction, someone else might pick up the connection.
//    	returnDatabaseConnection(jdtC);
    }      
   	return yes;
  }
  

  
}