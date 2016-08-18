/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 ISSUE BUG00112    APota          5/16      8:58 am    Syntax error when calling _getAccoEditableATtributes
 ISSUE BUG00148    APota          5/16      8:58 am    Nullpointer at hasInstances while deleting a custom problemstatement
 ISSUE BUG00551    APota          5/31      8:58 am    Deleting a form made less hasslefree.


*/


/**
@author Alok Pota
**/


package com.oculussoftware.repos.bmr;
//
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMClass extends BMModelElement implements IRClass
{
  public static final String TABLE_NAME="CLASS";  
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";    
  public static final String COL_DELETEKIND="DELETESTATE";
  public static final String COL_CONFIGKIND="CONFIGUREKIND";
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_ISROOT="ISROOT";  
  public static final String COL_ISLEAF="ISLEAF";   
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  public static final String COL_PACK="PACKAGEID";  
  public static final String COL_DEF="DEFINTERFACEID";  
  public static final String COL_ROOTDEF="DESTINTERFACEID";  
  public static final String COL_TERM="ALTTERMINOLOGY";  
  
  public static final String TABLE_ASC="CLASSINTERFACEASC";  
  public static final String COL_INTERFACEID="INTERFACEID";  
  public static final String COL_CLASSID="CLASSID";  

  public static final String TABLE_ASC2="CLASSATTRGROUPASC";    
  public static final String COL_ATTACCESSID="ATTRGROUPID";  
  public static final String COL_ATTRIBUTEID="ATTRIBUTEID";  
  public static final String COL_ORDER="ORDERNUM";  

  public static final String TABLE_ASC3="ATTRGROUPGRANT";    
  public static final String COL_PAROBJECTID="PAROBJECTID";  
  public static final String COL_ATTGROUPID="ATTRGROUPID";  
  public static final String COL_ACCESSORID="ACCESSORID";  
  public static final String COL_OPTYPE="OPERATIONTYPE";  
    
  long _defID;
  long _rootdefID=-1;
   
  IRType _def=null;
  IRAttribute _sinatt=null;
  ILock _lock=null;
  private boolean _isleaf;
  private boolean _isroot;
  String _label=null;
  
  /**
  Construtor for creating new Attributes. Pass it the IRObjectContext  
  */
  public BMClass() throws OculusException
  {
  	setGUID(new GUID());
  }
  
  public IRClass setUserLabel(String id)
  {
    _label = id;    
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;
  }
    
  public IRClass setDefinition(IRType id) throws OculusException
  {    
   _def = id;
   _defID = _def.getIID().getLongValue();
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;
  }
  
  public IRClass setRootDefinition(IIID id) throws OculusException
  {
    _rootdefID = id.getLongValue();
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;
  }      

  public IRClass setDefinition(IIID id) throws OculusException
  {    
  	if (getPersState().equals(PersState.UNMODIFIED))
  		setPersState(PersState.MODIFIED);
  	_defID = id.getLongValue();
    return this;
  }
  
	public IRClass isLeaf(boolean bln) throws OculusException
  {
    _isleaf = bln;
    if (getPersState().equals(PersState.UNMODIFIED))
    	setPersState(PersState.MODIFIED);
    return this;  
   }
  
  public IRClass  isRoot(boolean bln) throws OculusException
  {
    _isroot = bln;
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
    setUserLabel(rs.getString(COL_TERM));          
    isActive(rs.getBoolean(COL_ISACTIVE));             
    isLeaf(rs.getBoolean(COL_ISLEAF));             
    isRoot(rs.getBoolean(COL_ISROOT));               
    _defID = rs.getLong(COL_DEF);
    _rootdefID = rs.getLong(COL_ROOTDEF);
  }
    
  /***
  Public getters  
  **/
  public IRType getDefinition() throws OculusException
  {    
    IIID id1 = new SequentialIID(_defID);
    return (IRType)context.getCRM().getCompObject(context,"Interface",id1);
  }
  
  public boolean isRoot() throws OculusException { return _isroot; }
  public boolean isLeaf() throws OculusException { return _isleaf; }
  public String getUserLabel() throws ORIOException { return _label; }
  
	public IPersistable delete() throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }

	public IPersistable softdelete() throws ORIOException    
  {
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
    return "INSERT INTO CLASS ("+
							"OBJECTID,"+
							"ALTTERMINOLOGY,"+
							"DEFINTERFACEID,"+
							"GUID,"+
							"ISACTIVE,"+
							"DELETESTATE,"+
							"CONFIGUREKIND,"+
							"ISROOT,"+
							"ISLEAF,"+
							"NAME,"+
							"SHORTDESCRIPTION"+
						") VALUES ("+
				      getIID()+","+
							"'"+getUserLabel()+"',"+
      				getDefinition().getIID()+","+
							"'"+getGUID()+"',"+
							(isActive()?"1":"0")+","+
				      DeleteState.NOT_DELETED+","+
				      getConfigKind()+","+    
				      (isRoot()?"1":"0")+","+
				      (isLeaf()?"1":"0")+","+
				      "'"+SQLUtil.primer(getName(),250)+"',"+
				      "'"+SQLUtil.primer(getDescription(),250)+"'"+
						")";
  }
  
	protected String getUpdateQuery()
		throws OculusException
	{
    return "UPDATE CLASS SET "
				      +COL_NAME+"='"+SQLUtil.primer(getName(),250)+"',"
				      +COL_TERM+"='"+SQLUtil.primer(getUserLabel(),250)+"',"
				      +COL_DESC+"='"+SQLUtil.primer(getDescription(),250)+"',"
				      +"ISACTIVE="+(isActive()?"1":"0")+","
				      +"DELETESTATE="+getDeleteState()
				      +" WHERE "+COL_OBJECTID+"="+getIID();
  }
    
	protected String getDeleteQuery()
	  throws OculusException
  {
    return "DELETE FROM CLASS WHERE OBJECTID="+getIID();
  }
  
	protected String getLoadQuery()
		throws OculusException
	{
    return " SELECT * "+
					 " FROM \"CLASS\" as cls LEFT OUTER JOIN \"EXTENDSASC\" as ext ON ext.SRCINTERFACEID=cls.DEFINTERFACEID"+
					 " WHERE cls.OBJECTID="+getIID()+" AND cls.ISACTIVE=1";
  }
  
	public void setRootType(IRType type) throws OculusException
	{
	  IQueryProcessor psp = null;  
	  IRConnection jdtC = null;  
	  try
	  {
	    jdtC = getDatabaseConnection();
	    psp = jdtC.createProcessor();
	    psp.update("INSERT INTO EXTENDSASC (SRCINTERFACEID,DESTINTERFACEID,ORDERNUM) VALUES "+
				"("+getDefinition().getIID()+","+type.getIID()+",1)");   
	  }
	  finally 
	  { 
      if (psp != null) psp.close(); 
		  getObjectContext().getCRM().commitTransaction(getObjectContext());          
	  }      
	}

    
  public ILock getLock() { return _lock;}

  ////////////////////////////////////////////////////////////    
	//
	//
	//    METHODS THAT RETURN A LIST OF MODEL ELEMENTS
	//
	////////////////////////////////////////////////////////////    

	/****
  Gets the list of all extended interfaces supported by the class  
  */
  
	
	// TODO: This needs to be a collection class.
  public IRModelElementList getExtendedTypeList() throws OculusException
  {
    IRConnection jdtC = null;
	  IQueryProcessor qp = null;  
    IDataSet rs = null;
	  IRModelElementList map = null;
	  
	  try  
	   {
	     jdtC = getDatabaseConnection();
	     qp = jdtC.createProcessor();
	     map = new BMTypeList();    
	     map.setObjectContext(getObjectContext());
	     String query = "SELECT * FROM INTERFACE LEFT OUTER JOIN EXTENDSASC ON EXTENDSASC.DESTINTERFACEID=INTERFACE.OBJECTID LEFT OUTER JOIN CLASSINTERFACEASC ON CLASSINTERAFCEASC.INTERFACEID=EXTENDS.SRCINTERFACEID  WHERE CLASSINTERFACEASC.CLASSID="+getIID().getLongValue();
	     rs = qp.retrieve(query);        
	     while(rs.next()) 
	     {
	       IIID id = new SequentialIID(rs.getLong("OBJECTID"));
	       IRType ass = (IRType)context.getCRM().getCompObject(context,"Type",rs);
	       if (!map.contains(id))
	         map.add(id);             
	     } 
	     map.reset();
	  }
    finally { 
      if (qp != null) qp.close(); 
//	    returnDatabaseConnection(jdtC);
    }      
	  return map;
  }


  /***
  *  Get specific attribute map of an object 
  **/ 
  //Hopefully a less buggy getAttributes method.
  public IRPropertyMap getAttributes(IRObject obj)
		throws OculusException
  {
    IRPropertyMap bigMap=null;
    bigMap = new BMPropertyMap();
		IQueryProcessor stmt = null;
		IRConnection jdtC = null;
		try
		{
			jdtC = getObjectContext().getRepository().getDataConnection(getObjectContext());
			stmt = jdtC.createProcessor();			
      			  
      IDataSet results = stmt.retrieve("SELECT attr.OBJECTID as ATTRIBUTEID, "+
								      				"		 assoc.ISREQUIRED as ISREQUIRED "+
								      				" FROM (\"ATTRIBUTE\" attr LEFT OUTER JOIN INTERFACEATTRASC assoc ON attr.OBJECTID=assoc.ATTRIBUTEID)"+
								      				" 				LEFT OUTER JOIN \"CLASS\" cls ON assoc.INTERFACEID=cls.DEFINTERFACEID "+
								      				"WHERE cls.OBJECTID = "+getIID());
      while (results.next())
      {
        IRProperty thisProperty = new BMProperty(obj);
        //Saleem: I added the next two lines
        IIID id1 = new SequentialIID(results.getLong("ATTRIBUTEID"));
        thisProperty.setDefnObject(id1);                                    
        thisProperty.load(results);
        bigMap.put("prop"+thisProperty.getDefnObject().getIID().toString(),thisProperty);
        id1 = null;
        thisProperty = null;
      }
    }
		finally
		{
			if (stmt != null) stmt.close();
//			getObjectContext().getCRM().returnDatabaseConnection(jdtC);
		}
    return bigMap;
  }
 
  ////////////////////////////////////////////////////////////    
	//
	//
	//    METHODS THAT MODIFY CLASSATTRGROPASC
	//
	////////////////////////////////////////////////////////////    
	/***
  *  Add an attribute group/attribute to the CLASSATTRGROUPASC
	**/  
	public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg) throws OculusException
  {
    addAttributeGroup(cls,atg,false);
  }

  public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg, boolean isreq) throws OculusException
  {
	  int ord = _getMaxOrder()+1;
	  if (ord == 0) ord = 1;
		addAttributeGroup(cls, atg, isreq, ord);
  }

  public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg, int order) throws OculusException
  {
		addAttributeGroup(cls, atg, false, order);
  }

	public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg, boolean isreq, int order) throws OculusException
  {
    if (!isAttributeDuplicate(atg))
    {
      String query = null;    
      IRConnection jdtC = null;
      IQueryProcessor qp = null;
      StringBuffer sbf = new StringBuffer();
	    try
  	  {
        jdtC = getDatabaseConnection();
        qp = jdtC.createProcessor();   
				query = "INSERT INTO CLASSATTRGROUPASC ("+
									"ATTRGROUPID,"+
									"CLASSID,"+
									"ATTRIBUTEID,"+
									"ORDERNUM,"+
									"CONFIGUREKIND"+
								") VALUES ("+
        					cls.getIID()+","+
        					getIID()+","+
									atg.getIID()+","+
									order+","+                                       
									ConfigKind.FULL.getIntValue()+
								")";
        qp.update(query);

				query = "INSERT INTO INTERFACEATTRASC ("+
									"INTERFACEID,"+
									"ATTRIBUTEID,"+
									"ISREQUIRED"+
								") VALUES ("+
									getDefinition().getIID()+","+
									atg.getIID()+","+
									(isreq?"1":"0")+
								")";
        qp.update(query);
	    }
	    finally 
	    { 
	      if (qp != null) qp.close(); 
		    getObjectContext().getCRM().commitTransaction(getObjectContext());          
	    }    
    }
  }

  /***
  *  Remove an attribute group/attribute to the CLASSATTRGROUPASC
	**/    
  public void removeAttribute(IRAttribute atg) throws OculusException
  {
    String query = null;    
    IRConnection jdtC = null;  
    IQueryProcessor qp = null;
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();   
      qp.update("DELETE FROM CLASSATTRGROUPASC "+
								"WHERE "+COL_CLASSID+"="+getIID()+" AND "+COL_ATTRIBUTEID+"="+atg.getIID());  
      qp.update("DELETE FROM INTERFACEATTRASC "+
								"WHERE INTERFACEID="+getDefinition().getIID()+" AND ATTRIBUTEID="+atg.getIID());
    }
    finally 
    { 
      if (qp != null) qp.close();
  	  getObjectContext().getCRM().commitTransaction(getObjectContext());          
    }    
  }
  
  public void removeAllAttributes() throws OculusException
  {
    String query = null;    
    IRConnection jdtC = null;  
    IQueryProcessor qp = null;
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor(); 
      qp.setSingleton(false);              
      qp.update("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID="+getIID());
      qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getDefinition().getIID());
    }
    finally 
    { 
      if (qp != null) qp.close(); 
	    getObjectContext().getCRM().commitTransaction(getObjectContext());          
    }    
  }

  public IRStateMachine getStateMachine() throws OculusException
  {
  	return getStateMachine(false);
  }

  public IRStateMachine getStateMachine(boolean editable) throws OculusException
  {
    IRClass baseclass = getBaseClass();
    return getObjectContext().getRepository().getBMRepository().getStateMachineObject(baseclass,editable);
  }
 
  public IRAttrAccessGroup getDefaultAttrGroup() throws OculusException
  {
    IIID id = IDCONST.DEFAULT_ATTR_GROUP.getIIDValue();
    return (IRAttrAccessGroup)getObjectContext().getCRM().getCompObject(getObjectContext(),"AttributeGroup",id);
  }


  /***
  *  Is attribute duplicate for the class
  **/    
  public boolean isAttributeDuplicate(IRAttribute att)throws OculusException
  {
		return _isAttributeDuplicate(att.getIID().getLongValue());
  }

  private boolean _isAttributeDuplicate(long attid)throws OculusException
  {
	  String query = null;    
	  IRConnection jdtC = null;
	  IDataSet rs = null;
	  IQueryProcessor qp = null;
	  boolean yes = false;
	  try 
	  {         
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();   
	    rs = qp.retrieve(" SELECT * "+
	  									 " FROM CLASSATTRGROUPASC "+
	  									 " WHERE CLASSID="+getIID()+" AND ATTRIBUTEID="+attid);  
	    if (rs.next()) yes = true;  
	  }
	  finally {
	    qp.close();
//	    returnDatabaseConnection(jdtC);
	  }
	  return yes;
  }
  
	// TODO: this ought to be a boolean value on the object.
	public boolean hasStateMachine() throws OculusException
  {
    String query = null;    
    IRConnection jdtC = null;
    IDataSet rs = null;
    IQueryProcessor qp = null;
    boolean yes = false;
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();   
      rs = qp.retrieve("SELECT * FROM STATEMACHINE WHERE CONTROLCLASSID="+getBaseClass().getIID().getLongValue());
      yes =  rs.next();
    }
    finally {
      qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return yes;
  }

 	public IRModelElementList getEditableAttributeList() throws OculusException
  {
		IUser user = getObjectContext().getConnection().getUserObject();
    IDataSet args = new DataSet();
    args.setIID(this.getIID());
    args.put("UserID",user.getIID());
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"EditableClassAttributeList",args);
  }
 
	public IRModelElementList getViewableAttributeList() throws OculusException
  {
		IUser user = getObjectContext().getConnection().getUserObject();
    IDataSet args = new DataSet();
    args.setIID(this.getIID());
    args.put("UserID",user.getIID());
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ViewableClassAttributeList",args);
  }
  
	private int _getMaxOrder() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    int order = -1;
    
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();            
      IDataSet rs = qp.retrieve("SELECT MAX (ORDERNUM) as \"MaxOrder\" FROM CLASSATTRGROUPASC WHERE CLASSID="+getIID().getLongValue()); 
      if (rs.next())
				order = rs.getInt("MaxOrder");
    }
    finally
		{
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    } 
  	return order;  
  }
  
  public IRModelElementList getGlobalAttributeList() throws OculusException
  {
    IRConnection jdtC = null;
    IDataSet rs = null;
    IQueryProcessor qp = null;
    IRModelElementList list = null;
    try
    {
      list = new BMAttributeList();        
      list.setObjectContext(getObjectContext());
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();                     
      rs = qp.retrieve("SELECT * FROM ATTRIBUTE LEFT OUTER JOIN CLASSATTRGROUPASC ON CLASSATTRGROUPASC.ATTRIBUTEID=ATTRIBUTE.OBJECTID WHERE CLASSID="+getIID().getLongValue()+"ORDER BY CLASSATTRGROUPASC.ORDERNUM");  
      while(rs.next())
      {          
        IIID id = new SequentialIID(rs.getLong("OBJECTID"));           
        list.add(id);
      }
    }
    finally
		{
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    } 
  	return list;  
  }
    
  public List getObjectList() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;

    List objectiidlist = new ArrayList();
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();                  
      rs = qp.retrieve("SELECT OBJECTID FROM AllPMCObjects WHERE CLASSID="+getIID());
      while(rs.next())
        objectiidlist.add(new SequentialIID(rs.getLong("OBJECTID"))); 
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return objectiidlist;
  }
  
  
  public Map getObjectAttributeMap() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;

    
    List list = null;
    Map map = new HashMap();
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();                  
      String query = "SELECT list.OBJECTID AS objectid, attr.OBJECTID AS attributeid "+
                      " FROM ATTRIBUTE attr LEFT OUTER JOIN "+
                      " BOOLEANVALUE boolVal ON "+
                      " attr.OBJECTID = boolVal.ATTRIBUTEID LEFT OUTER JOIN "+
                      " CHARVALUE charVal ON "+
                      " attr.OBJECTID = charVal.ATTRIBUTEID LEFT OUTER JOIN "+
                      " ENUMVALUE enumVal ON "+
                      " attr.OBJECTID = enumVal.ATTRIBUTEID LEFT OUTER JOIN "+
                      " LONGCHARVALUE longCharVal ON "+
                      " attr.OBJECTID = longCharVal.ATTRIBUTEID LEFT OUTER JOIN "+
                      " ENUMSELECTION enumsel ON "+
                      " attr.OBJECTID = enumsel.ATTRIBUTEID LEFT OUTER JOIN "+
                      " TIMEVALUE timeVal ON "+
                      " attr.OBJECTID = timeVal.ATTRIBUTEID LEFT OUTER JOIN "+
                      " AllPMCObjects list ON "+
                      " (list.OBJECTID = boolVal.PAROBJECTID OR "+
                      " list.OBJECTID = charVal.PAROBJECTID OR "+
                      " list.OBJECTID = enumVal.PAROBJECTID OR "+
                      " list.OBJECTID = timeVal.PAROBJECTID OR "+
                      " list.OBJECTID = enumsel.PAROBJECTID OR "+
                      " list.OBJECTID = longCharVal.PAROBJECTID) "+
                      " WHERE list.CLASSID = "+getIID();
      rs = qp.retrieve(query);
      while(rs.next())
      {
        IIID id = new SequentialIID(rs.getLong("objectid"));
        list = (List)map.get(id);
        if (list == null) 
          {       
            list = new ArrayList();
            list.add(new SequentialIID(rs.getLong("attributeid")));
            map.put(id,list);
          }  
        else
        {
          list.add(new SequentialIID(rs.getLong("attributeid")));
           map.put(id,list);  
        }
      }
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return map;
  }
  
  private  List _getClassAttributeList() throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    List list = new ArrayList();    
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();                  
      rs = qp.retrieve("SELECT ATTRIBUTEID FROM CLASSATTRGROUPASC WHERE CLASSID="+getIID());
      while(rs.next())
      {
        IIID id = new SequentialIID(rs.getLong("ATTRIBUTEID"));
        list.add(id);                
      }
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return list;
  }
  
  //Checks if user attributes  defined for this class are used by some other
  //classes in which case we should not hard delete those user attributes
	public  boolean isUsed() throws OculusException
  { 
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;

    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query =  " SELECT * FROM CLASSATTRGROUPASC WHERE ATTRIBUTEID IN (SELECT ATTRIBUTEID "
       							+" FROM CLASSATTRGROUPASC WHERE CLASSID = "+getIID()+") AND CLASSID <>"+getIID()         
       							+" AND CONFIGUREKIND = "+ConfigKind.FULL;
      rs = qp.retrieve(query);
      yes = rs.next();
	  }
	  finally
		{
			if (qp != null) qp.close();
//	    returnDatabaseConnection(jdtC);
	  }      
	  return yes;
  }

	public IIID getRootDefinitionID() throws OculusException
  {
    if (_rootdefID == -1)
      _loadRootDefinition();
    return new SequentialIID(_rootdefID);
  }  

	public IRType getRootDefinition() throws OculusException
  {
    return (IRType)context.getCRM().getCompObject(context,"Interface",getRootDefinitionID());
  }
    
	private void _loadRootDefinition() throws OculusException
  {
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    IRType type = null;
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      rs = qp.retrieve("SELECT DESTINTERFACEID FROM EXTENDSASC WHERE SRCINTERFACEID="+_defID);
      if(rs.next())
        _rootdefID = rs.getLong("DESTINTERFACEID");       
    }
    finally
		{
			if (qp != null) qp.close();
//    	returnDatabaseConnection(jdtC);
    }
  }
  
  public IRClass getBaseClass() throws OculusException
  {    
  	 if (_rootdefID == _defID)           
    	return (IRClass) getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",getIID());
   	else
    	return getRootDefinition().getOwningClass();     
  }
  

	// TODO: This needs to be a variable on the object.
  public IREntryForm getEntryForm() throws OculusException
  {
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    IREntryForm type = null;
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      rs = qp.retrieve("SELECT * FROM ENTRYFORM WHERE FORMCLASSID="+getIID()+" OR SCNDCLASSID="+getIID());
      if (rs.next())
      {
        IIID id1 = new SequentialIID(rs.getLong("OBJECTID"));
        type = (IREntryForm)getObjectContext().getCRM().getCompObject(getObjectContext(),"EntryForm",id1);
      }
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return type;
  }
  
  
  // TODO: This needs to be a variable on the object.
  public IREntryForm getForm() throws OculusException
  {
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    IREntryForm type = null;
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      rs = qp.retrieve("SELECT * FROM ENTRYFORM WHERE FORMCLASSID="+getIID());
      if (rs.next())
      {
        IIID id1 = new SequentialIID(rs.getLong("OBJECTID"));
        type = (IREntryForm)getObjectContext().getCRM().getCompObject(getObjectContext(),"EntryForm",id1);
      }
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return type;
  }
  
 
  /////////////////////////////////CLONE///////////////////////////
  /////////////////////////////////CLONE///////////////////////////
  /////////////////////////////////CLONE///////////////////////////
  public  Object dolly() throws OculusException       
  { 
    BMClass state = new BMClass();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setIID(getIID());
    state.setGUID(getGUID());
    state.setName(getName());
    state.setDescription(getDescription());
    state._defID = _defID;
    state._rootdefID = _rootdefID;
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());
    state.isLeaf(isLeaf());
    state.isRoot(isRoot());
    state.setUserLabel(getUserLabel());
    state.setPersState(getPersState());
    return state;
  } 
  
  
  //Check if the class has instances irrespective of their deletestate.
  //This is used at the time of hard deleting a class.  If there are
  //instances then we do not allow hardelete. 
  public boolean hasInstances() throws OculusException
  {
    return (countInstances() > 0);
  }    
 
 public boolean hasActiveInstances() throws OculusException
  {
    
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    boolean found = false;
    long rootlong = getRootDefinition().getIID().getLongValue();
    String table = "";
    if (rootlong == IDCONST.IQUESTIONINPUT.getLongValue()) table = "MARKETINPUT";
    else if (rootlong == IDCONST.ISTANDARDINPUT.getLongValue()) table = "MARKETINPUT";
    else if (rootlong == IDCONST.IARTICLEINPUT.getLongValue()) table = "MARKETINPUT";    
    else if (rootlong == IDCONST.IREACTION.getLongValue()) table = "REACTION";
    else if (rootlong == IDCONST.IPROBLEMSTATEMENT.getLongValue()) table = "PROBLEMSTATEMENT";
    else if (rootlong == IDCONST.IPRODUCT.getLongValue()) table = "PRODUCT";
    else if (rootlong == IDCONST.IPRODUCTVERSION.getLongValue()) table = "PRODUCTVERSION";
    else if (rootlong == IDCONST.ICATEGORY.getLongValue()) table = "CATEGORY";
    else if (rootlong == IDCONST.IFEATURE.getLongValue()) table = "FEATURE";
    else if (rootlong == IDCONST.IFEATURECATEGORYLINK.getLongValue()) table = "CATFEATURELINK";
    else if (rootlong == IDCONST.IUSER.getLongValue()) table = "APPUSER";
    else if (rootlong == IDCONST.IORGANIZATION.getLongValue()) table = "ORGANIZATION";
    else if (rootlong == IDCONST.IALTERNATIVE.getLongValue()) table = "ALTERNATIVE";
    
    try
    {
      jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();
       rs = qp.retrieve("SELECT * FROM "+table+" WHERE DELETESTATE=1 AND CLASSID="+getIID());
      if (rs.next())
        found = true;
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return found;    
  }    
  
  
 
  public int countInstances()
 		throws OculusException
  {
	  IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    int found = 0;
    try
    {
      jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();
       rs = qp.retrieve("SELECT COUNT(OBJECTID) as numObjects FROM AllPMCObjects WHERE CLASSID="+getIID());
      if (rs.next())
        found = rs.getInt("numObjects");
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return found;
  }    
 
	public void setSIN(IRAttribute att) throws ORIOException
  {
    _sinatt = att;
  }
 
	public void removeExtension() throws OculusException
  {
		String query = null;    
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    try 
    {         
	    jdtC = getDatabaseConnection();
	    qp = jdtC.createProcessor();   
	    qp.update("DELETE FROM EXTENDSASC WHERE SRCINTERFACEID ="+getDefinition().getIID());  
    }
    finally 
    { 
			if (qp != null) qp.close(); 
      CRM.getInstance().commitTransaction(getObjectContext());          
    }    
  }
  


	// TUPLE METHODS

  public List getGroupsAndAttributes() throws ORIOException
  {
    IRConnection jdtC = null;
    IDataSet rs = null;
    IQueryProcessor qp = null;
    List list = null;
    Tuple tup = null;
    try
    {
         jdtC = getDatabaseConnection();
         qp = jdtC.createProcessor();            
         StringBuffer sbf = new StringBuffer();
        
         sbf.append("SELECT assoc.ISREQUIRED as isReq, att.NAME as attName,grp.NAME as grpName,casc.ATTRIBUTEID as attID,casc.ATTRGROUPID as grpID,casc.CONFIGUREKIND as ck ");
         sbf.append(" FROM CLASSATTRGROUPASC casc LEFT OUTER JOIN ATTRIBUTE att ON att.OBJECTID=casc.ATTRIBUTEID ");
         sbf.append(" LEFT OUTER JOIN ATTRACCESSGROUP grp ON grp.OBJECTID=casc.ATTRGROUPID ");                                    
         sbf.append(" LEFT OUTER JOIN INTERFACEATTRASC assoc ON assoc.ATTRIBUTEID=att.OBJECTID ");                  
         sbf.append(" WHERE casc.CLASSID="+getIID().getLongValue());         
         sbf.append(" AND assoc.INTERFACEID="+_defID);         
         sbf.append(" ORDER BY casc.ORDERNUM");       
    
         rs = qp.retrieve(sbf.toString());
         list = new ArrayList();
         while(rs.next())
      {
         long l1 = rs.getLong("attID");
         long l2 = rs.getLong("grpID");
         String s1 = rs.getString("attName");
         String s2 = rs.getString("grpName");         
         int ck = rs.getInt("ck");
         tup = new Tuple(l1,l2,s1,s2);
         tup.setConfigKind(ck);         
         tup.isRequired(rs.getBoolean("isReq"));         
         list.add(tup);         
      }     
      tup = null;
  }
  catch(Exception ex) { throw new ORIOException(ex);}
    finally {
      qp.close();
//        try {returnDatabaseConnection(jdtC);}
//     catch(Exception ex) { throw new ORIOException(ex);}
    } 
    
 return list;
}

  private boolean _isObjectTupleDuplicate(long objid,long attid, String tablename) throws ORIOException
  {
    
     IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;
     try  
   {
     jdtC = getDatabaseConnection();
     qp = jdtC.createProcessor();
     rs = qp.retrieve("SELECT * FROM "+tablename+" WHERE PAROBJECTID="+objid+ " AND ATTRIBUTEID="+attid);
     if (rs.next()) yes = true;     
     
  }
   
   catch(Exception ex) {throw new ORIOException(ex); }
    finally { qp.close();
//    try {returnDatabaseConnection(jdtC);}
//     catch(Exception ex) { throw new ORIOException(ex);}    
    }      
   return yes;
    
  }

  //This function was multipurpose. I.e., delete, add & update stuff in one sweep. The insert 
  //function is not optimized with this approach as duplicates have to be checked for every loop
  //iteration. A similar function called addObjectAttributes gets the list of objects & their attributes
  // and this way you don't have to check for duplicates
  public void fixObjectAttributes(List list) throws OculusException
  {     
   
      List list1 = getObjectList();      
      if (list1.size() == 0) 
       {
          return;
       }
      else
      { 
         IRConnection jdtC = null;
         IQueryProcessor qp = null;
         StringBuffer sbf = null;
         try
        {         
         jdtC = getDatabaseConnection();
         qp = jdtC.createProcessor();  
         qp.setSingleton(false);                            
        
         int size = list.size();
         int size1 = list1.size();
         for(int j =0; j < size1; ++j)
          { 
            long objid = ((IIID)list1.get(j)).getLongValue();         
            for(int i =0; i < size; ++i)
            {
              sbf = new StringBuffer();
              Tuple tup = (Tuple)list.get(i);
              long attid = tup.getStartIndex();
              IIID id = new SequentialIID(attid);
              IRAttribute att = (IRAttribute)context.getCRM().getCompObject(context,"Attribute",id);
              Primitive prim = att.getPrimitive();
              String valuetable = null;
              if (prim == Primitive.CHAR || prim == Primitive.INTEGER  || prim == Primitive.DECIMAL) 
                valuetable = "CHARVALUE";
              if (prim == Primitive.LONG_CHAR)
                valuetable = "LONGCHARVALUE";
              if (prim == Primitive.TIME)
                valuetable = "TIMEVALUE";
              if (prim == Primitive.BOOLEAN)
                valuetable = "BOOLEANVALUE";
              if (prim == Primitive.ENUM || prim == Primitive.RADIO)
                valuetable = "ENUMVALUE";
              if (prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK)
                valuetable = "ENUMSELECTION";
             //Adding   
             if (tup.getMode().equals("Add"))
              {   
                    if (prim == Primitive.CHAR  ) 
                    {
                      
                      String s = "-not specified-";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.INTEGER ) 
                    {
                      
                      String s = "0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.DECIMAL) 
                    {
                      
                      String s = "0.0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.LONG_CHAR ) 
                    {
                      
                      String s = "-not specified-";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                     if (prim == Primitive.BOOLEAN  ) 
                    {
                      
                      String s = "0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",");  
                      sbf.append(s);  
                      sbf.append(")");
                    }
                     if (prim == Primitive.TIME ) 
                    {
                      
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(")");
                    }
                     if ((prim == Primitive.ENUM || prim == Primitive.RADIO)) 
                    {
                      
                      long dummylong = IDCONST.DUMMYLITERAL.getLongValue();                
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",");  
                      sbf.append(""+dummylong);  
                      sbf.append(")");
                    }
                     
                    if ((prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK))  
                    {
                      
                      long dummylong = IDCONST.DUMMYLITERAL.getLongValue();                
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (ENUMLITERALID,PAROBJECTID,ATTRIBUTEID) VALUES (");
                      sbf.append(""+dummylong);
                      sbf.append(","+objid);
                      sbf.append(","+attid);  
                      sbf.append(")");
                    }
                      
                    qp.update(sbf.toString());
              }
              
              //Deleting
              if (tup.getMode().equals("Delete"))
                {
                  sbf.append("DELETE FROM ");                  
                  sbf.append(valuetable);
                  sbf.append(" WHERE PAROBJECTID="+objid);                  
                  sbf.append(" AND ATTRIBUTEID="+attid);      
                  qp.update(sbf.toString());                              
                }    
             sbf = null;   
                          
            }//inner for    
         }//outer for       
           
           
        }//end try
          
       catch(Exception ex) { throw new ORIOException(ex.toString());}
      finally 
      {    
        sbf = null;
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }       
      }//end big else     
  }//end function
  
  
  public void addObjectAttributes(List list) throws OculusException
  {     
   
      Map map = getObjectAttributeMap();            
      if (map.size() == 0) 
       {
          return;
       }
      else
      { 
         IRConnection jdtC = null;
         IQueryProcessor qp = null;
         StringBuffer sbf = null;
         try
        {         
         jdtC = getDatabaseConnection();
         qp = jdtC.createProcessor();  
         qp.setSingleton(false);                            
        
         int size = list.size();         
         Set set = map.keySet();
         Iterator itr = set.iterator();
         while(itr.hasNext())
          { 
            IIID objID = (IIID)itr.next(); 
            long objid = objID.getLongValue();
            List list1 = (List)map.get(objID);            
            for(int i =0; i < size; ++i)
            {
              sbf = new StringBuffer();
              Tuple tup = (Tuple)list.get(i);
              long attid = tup.getStartIndex();                          
              IIID attID = new SequentialIID(attid);
              //Check for duplicate              
              if (!list1.contains(attID)) 
              {                    
                    IIID id = new SequentialIID(attid);
                    IRAttribute att = (IRAttribute)context.getCRM().getCompObject(context,"Attribute",id);
                    Primitive prim = att.getPrimitive();
                    String valuetable = null;
                    if (prim == Primitive.CHAR || prim == Primitive.INTEGER  || prim == Primitive.DECIMAL) 
                      valuetable = "CHARVALUE";
                    if (prim == Primitive.LONG_CHAR)
                      valuetable = "LONGCHARVALUE";
                    if (prim == Primitive.TIME)
                      valuetable = "TIMEVALUE";
                    if (prim == Primitive.BOOLEAN)
                      valuetable = "BOOLEANVALUE";
                    if (prim == Primitive.ENUM || prim == Primitive.RADIO)
                      valuetable = "ENUMVALUE";
                    if (prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK)
                      valuetable = "ENUMSELECTION";
                      
                    if (prim == Primitive.CHAR  ) 
                    {
                      
                      String s = "-not specified-";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.INTEGER ) 
                    {
                      
                      String s = "0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.DECIMAL) 
                    {
                      
                      String s = "0.0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                    if (prim == Primitive.LONG_CHAR ) 
                    {
                      
                      String s = "-not specified-";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",'");  
                      sbf.append(s);  
                      sbf.append("')");
                    }
                     if (prim == Primitive.BOOLEAN  ) 
                    {
                      
                      String s = "0";  
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",");  
                      sbf.append(s);  
                      sbf.append(")");
                    }
                     if (prim == Primitive.TIME ) 
                    {
                      
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(")");
                    }
                     if ((prim == Primitive.ENUM || prim == Primitive.RADIO)) 
                    {
                      
                      long dummylong = IDCONST.DUMMYLITERAL.getLongValue();                
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (PAROBJECTID,ATTRIBUTEID,VALUE) VALUES (");
                      sbf.append(""+objid);
                      sbf.append(","+attid);  
                      sbf.append(",");  
                      sbf.append(""+dummylong);  
                      sbf.append(")");
                    }
                     
                    if ((prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK))  
                    {
                      
                      long dummylong = IDCONST.DUMMYLITERAL.getLongValue();                
                      sbf.append("INSERT INTO ");
                      sbf.append(valuetable);
                      sbf.append(" (ENUMLITERALID,PAROBJECTID,ATTRIBUTEID) VALUES (");
                      sbf.append(""+dummylong);
                      sbf.append(","+objid);
                      sbf.append(","+attid);  
                      sbf.append(")");
                    }
                      
                    qp.update(sbf.toString());                                
                   sbf = null;
              } 
                          
            }//inner for    
         }//outer for       
           
           
        }//end try
          
       catch(Exception ex) { throw new ORIOException(ex.toString());}
      finally 
      {    
        sbf = null;
        list = null;        
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }       
      }//end big else     
  }//end function

  
  
  public void addBatchGroupsAndAttributes(List list) throws ORIOException
  {
      
      int size = list.size();
      Tuple tup;
      IRConnection jdtC = null;
      IQueryProcessor qp = null;
      
      StringBuffer sbf;
      
      try
    {
       //Since we are inserting & deleting a bunch of things (n a for loop) 
       //its best to 
       //construct the SQL queries in a Stringbuffer
       
      List list1 = _getClassAttributeList(); 
       System.out.println(list1);
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();  
       qp.setSingleton(false);                            
       for (int i = 0; i < size; ++i)
      {
        sbf = new StringBuffer();
        tup = (Tuple)list.get(i);                
        String mode = tup.getMode();
        int order = i+1;
        int ck = tup.getConfigKind();
        if (mode.equals("Add") && !list1.contains(new SequentialIID(tup.getStartIndex())))
        {
            System.out.println("Inserting attribute with id"+tup.getStartIndex());
            order = _getMaxOrder()+1;
            sbf.append("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM, CONFIGUREKIND) VALUES(");
            sbf.append(Long.toString(getIID().getLongValue()));
            sbf.append(",");
            
            long startidx = tup.getStartIndex();
            if (startidx == IDCONST.SIN.getLongValue())
              sbf.append(_sinatt.getIID().toString());
            else               
              sbf.append(tup.getStartIndex());
            sbf.append(",");
            sbf.append(tup.getEndIndex());
            sbf.append(",");
            sbf.append(order);
            sbf.append(",");
            sbf.append(ck);
            sbf.append(")");                        
            qp.update(sbf.toString());
            sbf = null;         
            sbf = new StringBuffer();
            sbf.append("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES(");
            sbf.append(_defID);
            sbf.append(",");
            if (startidx == IDCONST.SIN.getLongValue())
              sbf.append(_sinatt.getIID().toString());
            else               
              sbf.append(tup.getStartIndex());            
            sbf.append(",");
           if (ck == 2 || ck == 0 || ck == 4  || ck == 5) 
            sbf.append("1");         
            else
            sbf.append("0");           
            sbf.append(")");
            qp.update(sbf.toString());
            sbf = null;
         
             
        }
       else if ((ck == 1 || ck == 3) && mode.equals("Delete"))
        {
        
            sbf.append("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID=");
            sbf.append(Long.toString(getIID().getLongValue()));
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(Long.toString(tup.getStartIndex()));
            sbf.append(" AND ATTRGROUPID= ");
            sbf.append(Long.toString(tup.getEndIndex()));
            sbf.append("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID=");
            sbf.append(_defID);
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());            
            qp.update(sbf.toString());            
        }
       else if (mode.equals("Update"))
        {
        
            sbf.append("UPDATE CLASSATTRGROUPASC SET ORDERNUM= ");
            sbf.append(order);
            sbf.append(" WHERE CLASSID= ");
            sbf.append(getIID().getLongValue());
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());                        
            qp.update(sbf.toString());            
        }
        
       sbf = null; 
      }               
    }
    
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        sbf = null;
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }    
  }

  private boolean _isTupleDuplicate(Tuple tup) throws ORIOException
  {
    
     IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;    
    long grpid = tup.getEndIndex();    
    long attid = tup.getStartIndex();    
     try  
   {
     jdtC = getDatabaseConnection();
     qp = jdtC.createProcessor();
     rs = qp.retrieve("SELECT * FROM INTERFACEATTRASC WHERE INTERFACEID="+_defID+ " AND ATTRIBUTEID="+attid);
     if(rs.next()) return true;
     
  }
  catch(Exception ex) {throw new ORIOException(ex); }
    finally { qp.close();
//    try {returnDatabaseConnection(jdtC);}
//     catch(Exception ex) { throw new ORIOException(ex);}    
    }      
   return false;
  }
  
   
  
  public void bufferBatchGroupsAndAttributes(List list) throws ORIOException
  {
      
      int size = list.size();
      Tuple tup;
      IRConnection jdtC = null;
      IQueryProcessor qp = null;

      StringBuffer sbf;
      
      try
    {
       //Since we are inserting & deleting a bunch of things (n a for loop) 
       //its best to 
       //construct the SQL queries in a Stringbuffer
       
       
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor(); 
       qp.setSingleton(false);                             
       for (int i = 0; i < size; ++i)
      {
        sbf = new StringBuffer();
        tup = (Tuple)list.get(i);        
        String mode = tup.getMode();
        int order = i+1;
        int ck = tup.getConfigKind();
        if (mode.equals("Add"))
        {
            sbf.append("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM, CONFIGUREKIND) VALUES(");
            sbf.append(Long.toString(getIID().getLongValue()));
            sbf.append(",");
            sbf.append(tup.getStartIndex());
            sbf.append(",");
            sbf.append(tup.getEndIndex());
            sbf.append(",");
            sbf.append(order);
            sbf.append(",");
            sbf.append(ck);
            sbf.append(")");                        
            qp.update(sbf.toString());
            sbf = null;         
            sbf = new StringBuffer();
            sbf.append("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES(");
            sbf.append(_defID);
            sbf.append(",");
            sbf.append(tup.getStartIndex());         
            sbf.append(",");
           if (ck == 2 || ck == 0 || ck == 4  || ck == 5) 
            sbf.append("1");         
            else
            sbf.append("0");           
            sbf.append(")");
            qp.update(sbf.toString());
            sbf = null;
         
             
        }
       else if ((ck == 1 || ck == 3) && mode.equals("Delete"))
        {
        
            sbf.append("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID=");
            sbf.append(Long.toString(getIID().getLongValue()));
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(Long.toString(tup.getStartIndex()));
            sbf.append(" AND ATTRGROUPID= ");
            sbf.append(Long.toString(tup.getEndIndex()));
            sbf.append("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID=");
            sbf.append(_defID);
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());            
            qp.update(sbf.toString());            
        }
       else if (mode.equals("Update"))
        {
        
            sbf.append("UPDATE CLASSATTRGROUPASC SET ORDERNUM= ");
            sbf.append(order);
            sbf.append(" WHERE CLASSID= ");
            sbf.append(getIID().getLongValue());
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());
            sbf.append(" AND ATTRGROUPID= ");
            sbf.append(tup.getEndIndex());
            qp.update(sbf.toString());            
        }
        
       sbf = null; 
      }               
    }
    
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        sbf = null;
        qp.close(); 
       }    
  }

  public void copyGroupsAndAttributes(IRClass cls) throws ORIOException  
   {
   
   List list = getGroupsAndAttributes();
   
   int size = list.size();
   StringBuffer sbf = null;
   IRConnection jdtC = null;
   IQueryProcessor qp = null;
   
   
   try
     {
   
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor(); 
       qp.setSingleton(false);                                    
       long _defID = cls.getDefinition().getIID().getLongValue();
       
       for(int i=0; i < size; i++)
       
       {
      
            sbf = new StringBuffer(); 
            Tuple tup = (Tuple)list.get(i); 
            int ck = tup.getConfigKind();     
            int j = i+1;
            sbf.append("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM, CONFIGUREKIND) VALUES(");
             sbf.append(Long.toString(getIID().getLongValue()));
             sbf.append(",");
             sbf.append(tup.getStartIndex());
             sbf.append(",");
             sbf.append(tup.getEndIndex());
             sbf.append(",");
             sbf.append(j);
             sbf.append(",");
             sbf.append(ck);
             sbf.append(")");                        
             qp.update(sbf.toString());
             sbf = null;         
             sbf = new StringBuffer();
             sbf.append("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES(");
             sbf.append(_defID);
             sbf.append(",");
             sbf.append(tup.getStartIndex());         
             sbf.append(",");
            if (ck == 2 || ck == 0 || ck == 4  || ck == 5) 
             sbf.append("1");         
             else
             sbf.append("0");           
             sbf.append(")");
             qp.update(sbf.toString());
             sbf = null;
 
       }
    }//end try
     
   catch(Exception ex) { throw new ORIOException(ex.toString());}
     finally 
       {    
         sbf = null;
         qp.close(); 
         try
           {
             CRM.getInstance().commitTransaction(getObjectContext());          
            }
          catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
        }      
   
   }

   public void copyAttributeListFromType(IRType type)throws OculusException
   {
     
     
     IRAttrAccessGroup grp = getDefaultAttrGroup();
     long grpid = grp.getIID().getLongValue();
     Tuple tup= null;
     List list = type.getAttributeList();
     List list2 = new ArrayList();    
     int size = list.size();
     Tuple tup2 = null;
      for(int i =0; i < size; ++i)
       {       
        tup = (Tuple)list.get(i);        
         long attid = tup.getEndIndex();
         tup2 = new Tuple();
         tup2.setStartIndex(attid);
         tup2.setEndIndex(grpid);
         tup2.setConfigKind(5);
         tup2.setMode("Add");
         list2.add(tup2);
         tup2 = null;
       }
     
   addBatchGroupsAndAttributes(list2);
     
   }

   public IRClass makecopy() throws OculusException
    {
      
      IObjectContext context = getObjectContext();    
      IRBusinessModel irb = context.getRepository().getBMRepository();    
      //Make a copy of the interface first    
      IRType copytype = getDefinition().makecopy();    
      //Make a copy of the attributes of this class & associate these new
      //attributes to this new class
      List attlist = getGroupsAndAttributes();
      List  copiedlist = new ArrayList();
      
      int size = attlist.size();
      Tuple tup = null;
      for(int i =0; i < size; ++i)
      {
        
      tup = ((Tuple)attlist.get(i));
      long attlong = tup.getStartIndex();
      IRAttribute att = (IRAttribute)context.getCRM().getCompObject(context,"Attribute", new SequentialIID(attlong));
      IRAttribute attcopy = att.makecopy();    
      Tuple tup2 = new Tuple();
      tup2.setStartIndex(attcopy.getIID().getLongValue());
      tup2.setEndIndex(tup.getEndIndex());
      tup2.setMode("Add");
      tup2.setConfigKind(tup.getConfigKind());
      copiedlist.add(tup2);          
      tup2 = null;
      }
      attlist = null;
      
      //Now make copy of the class
      IRClass state = (IRClass)context.getCRM().getCompObject(context,"Class",(IDataSet)null,true);
      state.setName("Copy of " +getName());
      state.setDescription(getDescription());
      state.setDeleteState(getDeleteState());
      state.isActive(true);
      state.setConfigKind(getConfigKind());
      state.setDefinition(copytype);
      state.isLeaf(false);             
      state.isRoot(true);             
      state.setUserLabel("Copy of "+getUserLabel());                 
      //Now make copy of attribute class associations
      state.bufferBatchGroupsAndAttributes(copiedlist);
      copiedlist = null;
      //copytype.bufferExtension(roottype);         
      return state;    
  
    }


public void addAttributes(IRModelElementList attribList) throws OculusException
  {
    
   int size = attribList.size();    
   IRConnection jdtC = null;
   IQueryProcessor qp = null;
   int ck = ConfigKind.FULL.getIntValue();
   StringBuffer sbf = null;   
   try
     {
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor(); 
       qp.setSingleton(false);                                    
       int order = _getMaxOrder();
       for (int i = 0; i < size; ++i)
      {
        
        IRAttribute att = (IRAttribute)attribList.getModelElement(i);
        sbf = new StringBuffer();
        sbf.append("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM, CONFIGUREKIND) VALUES(");
        sbf.append(getIID().toString());
        sbf.append(",");
        sbf.append(att.getIID().toString());
        sbf.append(",");
        sbf.append(IDCONST.DEFAULT_ATTR_GROUP.getIIDValue().toString());
        sbf.append(",");
        sbf.append(order);
        sbf.append(",");
        sbf.append(ck);
        sbf.append(")");                        
        qp.update(sbf.toString());
        sbf = null;         
        sbf = new StringBuffer();
        sbf.append("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES(");
        sbf.append(_defID);
        sbf.append(",");
        sbf.append(att.getIID().toString());         
        sbf.append(",");
        sbf.append("0");           
        sbf.append(")");
        qp.update(sbf.toString());
        sbf = null;
        ++order;
      }
    }//end try
    
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        sbf = null;
        qp.close();         
       }    
    
  }//end method


public void addAttributes(List attribList) throws OculusException
  {
    
   int size = attribList.size();    
   IRConnection jdtC = null;
   IQueryProcessor qp = null;
   int ck = ConfigKind.FULL.getIntValue();
   StringBuffer sbf = null;   
   try
     {
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor(); 
       qp.setSingleton(false);                                    
       for (int i = 0; i < size; ++i)
      {
        int order = i+1;        
        IIID attIID = (IIID)attribList.get(i);
        sbf = new StringBuffer();
        sbf.append("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM, CONFIGUREKIND) VALUES(");
        sbf.append(getIID().toString());
        sbf.append(",");
        sbf.append(attIID.toString());
        sbf.append(",");
        sbf.append(IDCONST.DEFAULT_ATTR_GROUP.getIIDValue().toString());
        sbf.append(",");
        sbf.append(order);
        sbf.append(",");
        sbf.append(ck);
        sbf.append(")");                        
        qp.update(sbf.toString());
        sbf = null;         
        sbf = new StringBuffer();
        sbf.append("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES(");
        sbf.append(_defID);
        sbf.append(",");
        sbf.append(attIID.toString());         
        sbf.append(",");
        sbf.append("0");           
        sbf.append(")");
        qp.update(sbf.toString());
        sbf = null;
      }
    }//end try
    
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        sbf = null;
        qp.close();         
       }    
    
  }//end method

  public void  updateAttribute(IRAttribute att, IRAttrAccessGroup atg, IRClass cls,int kk)throws OculusException
  {
   IRConnection jdtC = null;
   IQueryProcessor qp = null;
   try
     {
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();        
       qp.setSingleton(false);
       qp.update("UPDATE CLASSATTRGROUPASC SET ATTRGROUPID="+atg.getIID()+", CLASSID="+cls.getIID()+" WHERE ATTRIBUTEID="+att.getIID()+" AND CLASSID="+getIID());       
       qp.update("UPDATE INTERFACEATTRASC SET ISREQUIRED="+kk+", INTERFACEID="+cls.getDefinition().getIID()+" WHERE ATTRIBUTEID="+att.getIID()+" AND INTERFACEID="+_defID);       
      }
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }    
    
  }
  
  public void updateIsRequired(IRAttribute att,IRAttrAccessGroup atg, int bln) throws OculusException
  {
    
     IRConnection jdtC = null;
     IQueryProcessor qp = null;     
      try
    {       
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();  
       qp.setSingleton(false);
       qp.update("UPDATE INTERFACEATTRASC SET ISREQUIRED="+bln+" WHERE INTERFACEID="+_defID+" AND ATTRIBUTEID="+att.getIID());
       qp.update("UPDATE CLASSATTRGROUPASC SET ATTRGROUPID="+atg.getIID()+" WHERE CLASSID="+getIID()+" AND ATTRIBUTEID="+att.getIID());
    }
     catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {           
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }    

  }      


}