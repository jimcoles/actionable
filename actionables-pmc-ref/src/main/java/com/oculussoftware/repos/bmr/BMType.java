/**

@author Alok Pota


ISSUE BUG00228    APota          5/18      3:26 pm    General bug fix effort
**/


package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMType extends BMModelElement implements IRType
{
  public static final String TABLE_NAME="INTERFACE";  
  public static final String TABLE_ASC="INTERFACEATTRASC";  
  public static final String TABLE_ASC1="EXTENDSASC";  
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";    
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  public static final String COL_PACK="PACKAGEID";    
  
  public static final String COL_ISROOT="ISROOT";  
  public static final String COL_ISLEAF="ISLEAF";  
  public static final String COL_ATTRIBUTE="ATTRIBUTEID";  
  public static final String COL_REQ="ISREQUIRED";  
  
  //child interface
  public static final String COL_SRCID="SRCINTERFACEID";  
  //parent interface it extends
  public static final String COL_DESTID="DESTINTERFACEID";  
  public static final String COL_ORDER="ORDERNUM";     
   
  IIID _owningID;
  IIID _packID;
  ILock _lock;
  private boolean _isleaf;
  private boolean _isroot;
  
  public BMType() throws OculusException
  {
  	setGUID(new GUID());
  }
  
  public IRType isLeaf(boolean bln)
  {
    _isleaf = bln;
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;  
  }
  
  public IRType isRoot(boolean bln)
  {
    _isroot = bln;
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;
  }
  
  protected void load(IDataSet rs) throws OculusException
	{
    setPersState(PersState.PARTIAL);
		
   	setGUID(new GUID(rs.getString("guid").trim()));
    setIID(getObjectContext().getRepository().makeReposID(rs.getLong("objectid")));
    setName(rs.getString("name").trim());
    setDescription(rs.getString("shortdescription").trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt("deletestate")));          
    setConfigKind(ConfigKind.getInstance(rs.getInt("configurekind")));          
   	isActive(rs.getBoolean("isactive"));           	
    isLeaf(rs.getBoolean("isleaf"));             
    isRoot(rs.getBoolean("isroot"));             
    _owningID = getObjectContext().getRepository().makeReposID(rs.getLong("classid"));
	}
  
  /////////////////////Public getters	
  public ILock getLock() { return _lock; }
  public boolean isRoot() { return _isroot; }
  public boolean isLeaf() { return _isleaf; }
  
  /******************************
  Public database access
  *******************************/  
	public String getLoadQuery()
 		throws OculusException
	{
	 	return "SELECT int.OBJECTID as objectid, int.GUID as guid,int.NAME as name,"+
						" int.SHORTDESCRIPTION as shortdescription,int.CONFIGUREKIND as configurekind, "+
						" int.ISACTIVE as isactive, int.DELETESTATE as deletestate,int.ISROOT as isroot, "+
						" int.ISLEAF as isleaf, clz.OBJECTID as classid  "+
						"FROM \"INTERFACE\" int LEFT OUTER JOIN \"CLASS\" clz ON clz.DEFINTERFACEID=int.OBJECTID "+
						"WHERE int.OBJECTID="+getIID()+" AND int.ISACTIVE=1";
 	}

	public String getCreateQuery()
		throws OculusException
	{
	  isActive(true);
	  setDeleteState(DeleteState.NOT_DELETED);
		return "INSERT INTO INTERFACE ("+
								"OBJECTID,"+
								"GUID,"+
								"ISACTIVE,"+
								"DELETESTATE,"+
								"ISROOT,"+
								"ISLEAF,"+
								"CONFIGUREKIND,"+
								"NAME,"+
								"SHORTDESCRIPTION"+
							") VALUES ("+
								getIID()+","+
						    "'"+getGUID()+"',"+
						    (isActive()?"1":"0")+","+
						    DeleteState.NOT_DELETED+","+
						    (isRoot()?"1":"0")+","+
						    (isLeaf()?"1":"0")+","+
						    getConfigKind()+","+
						    "'"+SQLUtil.primer(getName(),250)+"',"+
						    "'"+SQLUtil.primer(getDescription(),250)+"'"+
							")";
	}
 
  public String getUpdateQuery()
  	throws OculusException
  {
	  return "UPDATE INTERFACE SET "       
	    + COL_NAME+"='"+SQLUtil.primer(getName(),250)+"',"        
	    + COL_DESC+"='"+SQLUtil.primer(getDescription(),250)+"',"                
	    + "   ISACTIVE="+(isActive()?"1":"0")+","        
	    + "   DELETESTATE="+getDeleteState()
	    + " WHERE "+COL_OBJECTID+"="+getIID();
  }

	public String getDeleteQuery()
		throws OculusException
	{
		isSingleton(false);
		return "DELETE FROM EXTENDSASC WHERE SRCINTERFACEID="+getIID()+";"+               
           "DELETE FROM IFCPRODLISTUSAGE WHERE INTERFACEID="+getIID()+";"+  
					 "DELETE FROM INTERFACE WHERE OBJECTID="+getIID();
	}

  public IPersistable delete() throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }
 
  public IPersistable softdelete() throws OculusException    
  {
    setPersState(PersState.MODIFIED);
    setDeleteState(DeleteState.DELETED);
    isActive(false);
    return this;
  }
  
	public  Object dolly() throws OculusException         
  { 
    BMType state = new BMType();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setIID(getIID());
    state.setGUID(getGUID());
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());
    state.isLeaf(isLeaf());
    state.isRoot(isRoot());
    state._owningID = _owningID;
    state.setPersState(getPersState());
    return state;
  } 

  public IRClass getOwningClass() throws OculusException 
  {
		return getOwningClass(false);
  }
	
	public IRClass getOwningClass(boolean editable) throws OculusException 
  {
    return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",_owningID, editable);
  }


	////////////////////////////////////////////////////////////    
	//    METHODS THAT MODIFY INTERFACEATTRASC & EXTENDSASC
	////////////////////////////////////////////////////////////    
	public void addAttributeList(IRModelElementList list) throws OculusException
  {
    int size = list.size();
    for(int i=0;i<size;++i)
    {
      IIID id = (IIID)list.get(i);
      _addAttribute(id,false);
    }
  }

  public void addAttribute(IRAttribute att, boolean b) throws OculusException
  {
    _addAttribute(att.getIID(), b);
  }  

	private void _addAttribute(IIID attribid, boolean req) throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    try 
    {         
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();   
	    qp.update("INSERT INTO INTERFACEATTRASC ("+
									"INTERFACEID,"+
									"ATTRIBUTEID,"+
									"ISREQUIRED"+
								") VALUES ("
							    +getIID()+","
							    +attribid+","
							    +req+")");  
    }
    finally 
    { 
      if (qp != null) qp.close(); 
      CRM.getInstance().commitTransaction(getObjectContext());          
    }    
  }

	public void removeAttribute(IRAttribute att) throws OculusException 
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    
    try 
    {         
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();   
	    qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getIID()
						    + " AND ATTRIBUTEID="+att.getIID());  
    }
    finally 
    { 
      if (qp != null) qp.close(); 
      CRM.getInstance().commitTransaction(getObjectContext());          
    }    
  }  

	public void removeAllAttributes() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    
    try 
    {         
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();   
	    qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getIID());  
    }
    finally 
    { 
      if (qp != null) qp.close(); 
      CRM.getInstance().commitTransaction(getObjectContext());          
    }    
  }

	/***
	Remove the inheritence between the current interface and any parent interface
	 **/
	public void removeExtensions() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    
    if (!isExtended())     
    {
      try 
      {          
        jdtC = getDatabaseConnection();
        qp = jdtC.createProcessor();   
        qp.update("DELETE FROM EXTENDSASC WHERE SRCINTERFACEID="+getIID());
      }
      finally 
      { 
        if (qp != null) qp.close(); 
        CRM.getInstance().commitTransaction(getObjectContext());          
      }
    }    
  }

	/***
	Add an inheritence to the current interface
	**/
	public void addExtension(IRType att) throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    
    //Ordernum is 1 (canned) here. Will need clarification as to how this ordernum
    //is going to be used and who enters it?
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();   
      qp.update("INSERT INTO EXTENDSASC (SRCINTERFACEID,DESTINTERFACEID,ORDERNUM) "+
								" VALUES "+
								"("+getIID()+","+ att.getIID()+",1)");  
    }
    finally 
    { 
	    if (qp != null) qp.close(); 
      CRM.getInstance().commitTransaction(getObjectContext());          
    }    
  } 

	public void bufferExtension(IRType att) throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    
	  try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();   
      qp.update("INSERT INTO EXTENDSASC (SRCINTERFACEID,DESTINTERFACEID)"+
								" VALUES "+
								"("+getIID()+","+att.getIID()+")");  
    }
    finally
		{
			if (qp != null) qp.close();
		}
  } 

	/***
	Check if the current type is a parent interface of some of other interface
	in which case you cannot delete the current interface.
  **/
  public boolean isExtended() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    boolean yes = false;
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();   
      IDataSet rs = qp.retrieve("SELECT * FROM EXTENDSASC WHERE DESTINTERFACEID="+getIID());
      yes = rs.next();
    }
    finally 
    { 
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }    
    return yes; 
  }

	public IRType makecopy() throws OculusException
  {
    IObjectContext context = getObjectContext();    
    IRType state = (IRType)context.getCRM().getCompObject(context,"Interface",(IDataSet)null,true);
    state.setName("Copy of " +getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.isActive(true);
    state.setConfigKind(getConfigKind());
    state.isLeaf(false);             
    state.isRoot(true);                     
    return state;
  }  



  // TUPLE METHODS

  public List getAttributeList() throws OculusException
  {
//    IRConnection jdtC = null;
//    IQueryProcessor qp = null;  
//    IDataSet rs = null;
//    List bigMap = null;
//    Tuple tup = null;
//  
//    try  
//     {
//      bigMap = new ArrayList();
//      jdtC = getDatabaseConnection();
//      qp = jdtC.createProcessor();
//     
//      StringBuffer sbf = new StringBuffer();
//      sbf.append("SELECT * FROM INTERFACEATTRASC intasc "+
//        " LEFT OUTER JOIN CLASS cls ON cls.DEFINTERFACEID=intasc.INTERFACEID "+
//        " LEFT OUTER JOIN CLASSATTRGROUPASC casc ON casc.CLASSID=cls.OBJECTID "+
//        "  WHERE intasc.INTERFACEID="+getIID()+
//        " AND casc.ATTRIBUTEID=intasc.ATTRIBUTEID ORDER BY casc.ORDERNUM"); 
//      rs = qp.retrieve(sbf.toString());
//      while(rs.next())
//      {
//        long lo1 = rs.getLong("INTERFACEID");  
//        long lo2 = rs.getLong("ATTRIBUTEID");         
//        boolean req = rs.getBoolean("ISREQUIRED");                       
//         tup = new Tuple(lo1,lo2);
//       tup.isRequired(req); 
//         bigMap.add(tup);
//      }             
//     }
//    finally
//    { 
//      if (qp != null) qp.close(); 
//      returnDatabaseConnection(jdtC);
//    }      
//    return bigMap; 

    return getAttributeList(getIID());
  }


public List getAttributeList(IIID typeIID) throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;  
    IDataSet rs = null;
    List bigMap = null;
    Tuple tup = null;
  
    try  
     {
      bigMap = new ArrayList();
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
     
      StringBuffer sbf = new StringBuffer();
      sbf.append("SELECT intasc.*,casc.CLASSID FROM INTERFACEATTRASC intasc "+
        " LEFT OUTER JOIN CLASS cls ON cls.DEFINTERFACEID=intasc.INTERFACEID "+
        " LEFT OUTER JOIN CLASSATTRGROUPASC casc ON casc.CLASSID=cls.OBJECTID "+
        "  WHERE (intasc.INTERFACEID="+getIID()+" OR intasc.INTERFACEID="+typeIID+") "+
        " AND casc.ATTRIBUTEID=intasc.ATTRIBUTEID ORDER BY casc.ORDERNUM"); 
      rs = qp.retrieve(sbf.toString());
      while(rs.next())
      {
        long lo1 = rs.getLong("INTERFACEID");  
        long lo2 = rs.getLong("ATTRIBUTEID");         
        long lo3 = rs.getLong("CLASSID");         
        boolean req = rs.getBoolean("ISREQUIRED");                       
         tup = new Tuple(lo1,lo2);
       tup.isRequired(req); 
       tup.setClazz(lo3);
         bigMap.add(tup);
      }             
     }
    finally
    { 
      if (qp != null) qp.close(); 
//      returnDatabaseConnection(jdtC);
    }      
    return bigMap; 
  }



}