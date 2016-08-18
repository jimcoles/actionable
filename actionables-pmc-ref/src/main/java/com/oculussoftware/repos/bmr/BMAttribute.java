package com.oculussoftware.repos.bmr;

/**

@author Alok Pota
**/

/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 ISSUE DES00091    APota          5/26                 Removed double quote problem

*/
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.sysi.sec.*;
import java.util.*;
import java.sql.*;
import com.oculussoftware.ui.DateFormatter;

public class BMAttribute extends BMModelElement implements IRAttribute
{
	public static final String TABLE_NAME = "ATTRIBUTE";
	public static final String COL_OBJECTID = "OBJECTID";
	public static final String COL_BYTEGUID = "GUID";
	public static final String COL_DATAID = "DATATYPEID";
	public static final String COL_MAXLENGTH = "MAXLENGTH";
	public static final String COL_ATTRKIND = "ATTRKIND";
	public static final String COL_DELETEKIND = "DELETESTATE";
	public static final String COL_CONFIGKIND = "CONFIGUREKIND";
	public static final String COL_ISACTIVE = "ISACTIVE";
	public static final String COL_UNITLABEL = "UNITLABEL";
	public static final String COL_UNITPOS = "UNITPOSITION";
	public static final String COL_NAME = "NAME";
	public static final String COL_DESC = "SHORTDESCRIPTION";
	public static final String COL_EXP = "EXPRESSION";
  public static final String COL_PROMPT = "ALTUSERPROMPT";
	public static String COL_INTERFACEID = "INTERFACEID"; // MAK: 3/15/00 just to get BMAttributeGroup to compile
// Mak: just moved these up in file to highlight that these are not in the table 3/24/00
	public static final String COL_ATTRIB = "ATTRIBUTEID";
	public static final String COL_VALUE = "VALUE";
	public static final String COL_OBJECT = "PAROBJECTID";

// Mak : STUBBED: moved these TABLE_* fields to IRAttribute to get BMAttribute to compile 3/24/00
	////Stores defaults and actual values
	//public static final String TABLE_CHAR = "CHARVALUE";
	//public static final String TABLE_LONGCHAR = "LONGCHARVALUE";
	//public static final String TABLE_TIME = "TIMEVALUE";
	//public static final String TABLE_BOOLEAN = "BOOLEANVALUE";
	//public static final String TABLE_BLOB = "BLOBVALUE";
	//public static final String TABLE_ENUM = "ENUMVALUE";
	//Instance variables
	private Object _defValue = null;
	private AttributeKind _attrKind = null;
  private ConfigKind _configkind = null;
  private int _intMaxLength;
  private UnitPosition _intUnitPos;  
  private long _dataTypeID;
  private String _strUnitLabel = null;
  private String _strExpr = null;
  private String _strPrompt = NULL;
  private AttrGroupOper _oper = null;
  
	private ILock _lock = null;
	private static final String SPACE = " ";
	private final static String NULL = "-not specified-";
	private com.oculussoftware.api.repi.DeleteKind _DeleteKind = DeleteKind.DELETE;
	/**************************************************
	Construtor 
	***************************************************/
	
  public BMAttribute() throws OculusException
  {
    guid = new GUID();
  }  
  
  public int getEnumSize() throws OculusException  
  {
    IREnumeration renum = null;
    renum = getEnum();
    if (renum == null) return -1;
    else
      return renum.getValues().size();
  }
  
  public IREnumeration getEnum() throws OculusException  
  {
    if(isenum())
    {
      IIID id = getDataType().getIID();
      return getObjectContext().getRepository().getBMRepository().getEnum(id,false);      
    }
    return null;
  }  
  
  public IRAttribute setUserPermission(AttrGroupOper oper)
    throws OculusException
  {
    _oper = oper;
    return this;
  }
  
  public AttrGroupOper getUserPermission()
    throws OculusException
  {
    return _oper;
  }
  

  private void _initDefaultValue() throws OculusException
	{
		IDataSet rs = null;
		IQueryProcessor qp = null;		
		String query = null;
		IRConnection jdtC = null;
		
		try 
  	{    		
 			jdtC = getDatabaseConnection();
			qp = jdtC.createProcessor();    		 
			String tabName = getTabName();
	  	query = "SELECT VALUE FROM "+tabName+" WHERE ATTRIBUTEID="+getIID()+" AND PAROBJECTID="+getIID();
		
		  rs = qp.retrieve(query);        
		  if (rs.next())
			{
 			  if (tabName.equals("CHARVALUE") || tabName.equals("LONGCHARVALUE"))
		  	  setDefaultValue(rs.getString(COL_VALUE));
		    if (tabName.equals("TIMEVALUE"))
		  	  setDefaultValue(rs.getTimestamp(COL_VALUE));	
		    if (tabName.equals("BOOLEANVALUE"))
          setDefaultValue(new Boolean(rs.getBoolean(COL_VALUE)));    
        if (tabName.equals("ENUMVALUE"))
        {
          long lo = rs.getLong(COL_VALUE);
          if (lo != 0)  setDefaultValue(new Long(lo));    
        }
			}
  	}
  	finally 
	  {
      if (qp != null) qp.close();    
    }		
  }

  /*********************************************************
  SQL Insert
  ************************************************************/
  
  protected void _sqlInsertObject(IRConnection jdtC)
  	throws OculusException
	{
	  IQueryProcessor psp = null;
	  try
	  {
		  psp = jdtC.createProcessor();
	    setGUID(guid);
	    isActive(true);
	    setDeleteState(DeleteState.NOT_DELETED);
	    psp.update("INSERT INTO ATTRIBUTE (OBJECTID,GUID,ISACTIVE,DELETESTATE,MAXLENGTH,UNITLABEL,UNITPOSITION,CONFIGUREKIND,ATTRKIND,NAME,SHORTDESCRIPTION,EXPRESSION,ALTUSERPROMPT,DATATYPEID) VALUES("    
	      + iid.getLongValue()+",'"
	      + getGUID().toString()+"',"
	      +"1,"          
	      + DeleteState.NOT_DELETED.getIntValue()+","      
	      + getMaxLength()+",'"
	      + SQLUtil.primer(getUnitLabel(),20)+"',"  
	      + getUnitPosition().getIntValue()+","    
	      + getConfigKind().getIntValue()+","      
	      + getAttrKind().getIntValue()+",'"          
	      + SQLUtil.primer(getName(),250)+"','"
	      + SQLUtil.primer(getDescription(),250)+"','"  
	      + SQLUtil.primer(getExpression())+"','"
        + SQLUtil.primer(_strPrompt,250)+"',"  
	      +_dataTypeID+")"
		    );    
	
	    //Insert in the corresponding DefaultValue table	
      //Default do not make sense for question type attributes. Because there is
      //no such thing as a default answer in a survey.
      //if (!_attrKind.equals(AttributeKind.QUESTION))
      //{
        Primitive prim = getDataType().getTypeKind();	
        String table = getTabName();
        String value = null;
        if (_defValue == null)
          psp.update("INSERT INTO "+table+" (PAROBJECTID,ATTRIBUTEID) VALUES ("+getIID()+","+getIID()+")");
        else
        {  
          value = _defValue.toString();
          if (value != null && prim.equals(Primitive.CHAR))
            value = "'"+SQLUtil.primer(value,250)+"'";
          if (value != null && (prim.equals(Primitive.INTEGER) || prim.equals(Primitive.DECIMAL)))
            value = SQLUtil.primer(value,10); 
          if (value != null && prim.equals(Primitive.LONG_CHAR))
            value = "'"+SQLUtil.primer(value)+"'";
          if (prim.equals(Primitive.BOOLEAN))
          {
            if (value != null && value.equals("true"))
              value = "1";
            else
              value = "0";
          }
          if (value != null  && prim.equals(Primitive.TIME))
            value = "'"+value+"'";  
          psp.update("INSERT INTO "+table+" VALUES ("+getIID()+","+getIID()+","+value+")");
        }
      //} 
	  }
	  finally {if (psp != null) psp.close();}
	}
  /****************************************************************************  
  Execute a SQL Select  
  ********************************************************************************/
  
  protected String getLoadQuery()
    throws OculusException
  {
		return  " SELECT * FROM "+TABLE_NAME+
            " WHERE "+COL_OBJECTID+"="+getIID()+" AND "+COL_ISACTIVE+"= 1";
  }

  /****************************************************************************  
  Execute a SQL Update
  ********************************************************************************/
  
  protected void _sqlUpdateObject(IRConnection jdtC)
  	throws OculusException
	{
	  IQueryProcessor psp = null;
	  IPreparedStatementProcessor psp1 = null;
	
	  try
	  {
			psp = jdtC.createProcessor(); 			              
      psp.setSingleton(false);
		  psp.update(
		  "UPDATE ATTRIBUTE SET "            
	    + COL_MAXLENGTH+"="+getMaxLength()+","
	    + COL_UNITPOS+"="+getUnitPosition().getIntValue()+","      
	    + COL_UNITLABEL+"='"+SQLUtil.primer(getUnitLabel(),20)+"',"            
	    + COL_ATTRKIND+"="+getAttrKind().getIntValue()+","    
      + COL_DATAID+"="+getDataType().getIID().getLongValue()+","      
	    + COL_NAME+"='"+SQLUtil.primer(getName(),250)+"',"            
	    + COL_DESC+"='"+SQLUtil.primer(getDescription(),250)+"',"            
	    + COL_EXP+"='"+SQLUtil.primer(getExpression())+"',"            
      + COL_PROMPT+"='"+SQLUtil.primer(_strPrompt,250)+"',"              
	    + "ISACTIVE="+(isActive()?1:0)+","            
	    + "DELETESTATE="+_deletestate.getIntValue()
	    + " WHERE "+COL_OBJECTID+"="+getIID()
		  );

      Primitive prim = getDataType().getTypeKind();	
      String table = getTabName();
      String value = null;
      if (_defValue == null) 
        psp.update("UPDATE "+table+" SET VALUE=NULL WHERE PAROBJECTID="+getIID()+" AND ATTRIBUTEID="+getIID());
      else
      {  
              value = _defValue.toString();
            if (value != null && prim.equals(Primitive.CHAR))
              value = "'"+SQLUtil.primer(value,250)+"'";
            if (value != null && (prim.equals(Primitive.INTEGER) || prim.equals(Primitive.DECIMAL)))
              value = SQLUtil.primer(value,10); 
            if (value != null && prim.equals(Primitive.LONG_CHAR))
              value = "'"+SQLUtil.primer(value)+"'";
            if (prim.equals(Primitive.BOOLEAN))
            {
              if (value != null && value.equals("true"))
                value = "1";
              else
                value = "0";
            }
            if (value != null  && prim.equals(Primitive.TIME))
              value = "'"+value+"'";
      
            psp.update("UPDATE "+table+" SET VALUE="+value+" WHERE PAROBJECTID="+getIID()+" AND ATTRIBUTEID="+getIID());
      }
	  }	
	  catch(Exception ex) { throw new ORIOException(ex);}
	  finally { psp.close();if (psp1 != null) psp1.close();}	
	}

  /*********************************************************  
  Execute a Wipe (Physical Delete)
  ************************************************************/
  
  protected void _sqlWipeObject(IRConnection jdtC)
  	throws OculusException
  {
    IQueryProcessor psp = null;  
    try
    {
		  psp = jdtC.createProcessor();
      psp.setSingleton(false);
       
      String tabName = getTabName();
      psp.update("DELETE FROM "+tabName+" WHERE ATTRIBUTEID="+getIID());
  	  psp.update("DELETE FROM ATTRIBUTE WHERE OBJECTID="+getIID());
    }
    finally {psp.close();}
  }  
  
  public IPersistable delete() throws ORIOException    
  {
	  setPersState(PersState.DELETED);
	  return this;
  }  
  
  public AttributeKind getAttrKind() throws OculusException
  {
  	return _attrKind;
  }  
  
  public ConfigKind getConfigKind() throws ORIOException
  {
  	return _configkind;
  }  

  public IRDataType getDataType() throws OculusException
  {
	  IIID id1 = new SequentialIID(_dataTypeID);
	  return (IRDataType)context.getCRM().getCompObject(context,"DataType",id1);
	}
	
  /******************************************************
  	Class specific public getters
  *******************************************************/
  
  public Object getDefaultValue() throws OculusException
  {
  	if (_defValue == null) _initDefaultValue();      
    return _defValue;
  }  

  public com.oculussoftware.api.repi.DeleteKind getDeleteKind()
  {
	  return _DeleteKind;
  }

  public DeleteState getDeleteState() throws ORIOException
  {
	  return _deletestate;
  }  
  public String getDescription() throws ORIOException
  {
  	return description;
  }  
  
  public String getExpression() throws OculusException
  {
    return _strExpr;
  }  
  
  public String getUserPrompt() throws OculusException
  {
    return _strPrompt;
  }  

  public ILock getLock() { return _lock;}
  
  public int getMaxLength() throws OculusException
  {
  	return _intMaxLength;
  }  

  private String getTabName() throws OculusException
  {
    Primitive prim = getDataType().getTypeKind();
		if (prim == Primitive.TIME) return "TIMEVALUE";
		else if (prim == Primitive.BOOLEAN) return "BOOLEANVALUE";		
		else if (prim == Primitive.LONG_CHAR) return "LONGCHARVALUE";
    else if (prim == Primitive.ENUM) return "ENUMVALUE";
    else if (prim == Primitive.RADIO) return "ENUMVALUE";
		else return "CHARVALUE";
  }
  
  public String getUnitLabel() throws OculusException
  {
  	return _strUnitLabel;
  }  
  
  public UnitPosition getUnitPosition() throws OculusException
  {
  	return _intUnitPos;
  }  
  
  public boolean isActive() throws ORIOException
  {
  	return isactive;
  }  
  
  public IRModelElement isActive(boolean id)throws ORIOException
  {
    isactive = id;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }
 /************************************************************
 Public database access
 **************************************************************/  

  protected void load(IDataSet rs) throws OculusException
	{
    setPersState(PersState.PARTIAL);
		setGUID(new GUID(rs.getString(COL_BYTEGUID).trim()));
		setIID(new SequentialIID(rs.getLong(COL_OBJECTID)));                    
		setName(rs.getString(COL_NAME).trim());                    
		setUnitPosition(UnitPosition.getInstance(rs.getInt(COL_UNITPOS)));          
		setMaxLength(rs.getInt(COL_MAXLENGTH));
		setAttrKind(AttributeKind.getInstance(rs.getInt(COL_ATTRKIND)));          
		setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));          
		setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
		isActive(rs.getBoolean(COL_ISACTIVE));                               
		setUnitLabel(rs.getString(COL_UNITLABEL));
		setDescription(rs.getString(COL_DESC).trim());                                                  
		setExpression(rs.getString(COL_EXP));
    _dataTypeID = rs.getLong(COL_DATAID);
    _strPrompt = rs.getString(COL_PROMPT);
	}

  public IPersistable save( ) throws OculusException
  {
  	IRConnection jdtC = null;  
	  jdtC = getDatabaseConnection();
	  if(getPersState().equals(PersState.NEW))
  	{		 
  		_sqlInsertObject(jdtC);              
      setPersState(PersState.UNMODIFIED);    
  	}
	  else if (getPersState().equals(PersState.MODIFIED))
		{
		  _sqlUpdateObject(jdtC);        
		  setPersState(PersState.UNMODIFIED);    
		}
	  else if (getPersState().equals(PersState.DELETED))
      _sqlWipeObject(jdtC);              

	  return this;
  }  
  
  public IRAttribute setAttrKind(AttributeKind attrkind) throws OculusException
  {
  	_attrKind = attrkind;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  
  
  public IRModelElement setConfigKind(ConfigKind attrkind) throws ORIOException
  {
  	_configkind = attrkind;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  

  public IRAttribute setDataType(IRDataType data) throws OculusException
  {
    _dataTypeID = data.getIID().getLongValue();
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }  

  public IRAttribute setDataType(IIID dataID) throws OculusException
  {
    _dataTypeID = dataID.getLongValue();
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }  

  public IRAttribute setDefaultValue(Object value) throws OculusException
  {
	  _defValue = value;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  

  public void setDeleteKind(com.oculussoftware.api.repi.DeleteKind newDeleteKind) throws OculusException
  {
	  _DeleteKind = newDeleteKind;
  }

  public IRModelElement setDeleteState(DeleteState id) throws ORIOException
  {
    _deletestate = id;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }

  public IRElement setDescription(String s) throws ORIOException
  {
//    if (s == null || s.equals("null") || s.equals("")) s = NULL;;
    description = s;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }

  public IRAttribute setExpression(String s) throws OculusException
  {
//  if (s == null || s.equals("null") || s.equals("")) s = NULL;;
    _strExpr = s;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
	  return this;
  }  

 /******************************************************
  Public setters
 *******************************************************/
  
  public IPersistable setIID(IIID id)
  {
	  iid = id;    
	  return this;
  }  
 
  public IRAttribute setMaxLength(int maxlen) throws OculusException
  {
  	_intMaxLength = maxlen;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  

  public IRElement setName(String s) throws ORIOException
  {
//    if (s == null) s = " ";
    name = s;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }
 
  public IRAttribute setUserPrompt(String s) throws ORIOException
  {
//   if (s == null) s = " ";
    _strPrompt = s;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
    return this;
  }
 
  public IRAttribute setUnitLabel(String unitlabel) throws OculusException
  {
//    if (unitlabel == null || unitlabel.equals(NULL) || unitlabel.equals("null") || unitlabel.equals("")) unitlabel = "";
  	_strUnitLabel = unitlabel;
  
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  
  
  public IRAttribute setUnitPosition(UnitPosition unitpos) throws OculusException  
	{
    _intUnitPos = unitpos;
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
  	return this;
  }  
 
  public IPersistable softdelete() throws OculusException    
  {
  	if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
	  setDeleteState(DeleteState.DELETED);
	  isActive(false);
	  return this;
  }  
  
  public Primitive getPrimitive() throws OculusException
  {
    return getDataType().getTypeKind();
  }
  
  
  /////////////////////////////////CLONE///////////////////////////
 /////////////////////////////////CLONE///////////////////////////
 /////////////////////////////////CLONE///////////////////////////
 
  public  Object dolly() throws OculusException       
  { 
    BMAttribute state = new BMAttribute();
    state.setIID(getIID());
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());
    state.setAttrKind(getAttrKind());  
    state.setUnitPosition(getUnitPosition());  
    state.setUnitLabel(getUnitLabel());  
    state._dataTypeID = _dataTypeID;    
    state.setExpression(getExpression());  
    state.setUserPrompt(getUserPrompt());  
    state.setMaxLength(getMaxLength());  
    state._defValue = _defValue;    
    state.setPersState(getPersState());
    return state;
  } 
  
  public boolean isUsed() throws OculusException
  { 
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;
    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      rs = qp.retrieve(" SELECT ATTRIBUTEID FROM CLASSATTRGROUPASC WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM INTERFACEATTRASC WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM ENUMVALUE WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM CHARVALUE WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM TIMEVALUE WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM LONGCHARVALUE WHERE ATTRIBUTEID="+getIID()+" "+
                       " UNION "+
                       " SELECT ATTRIBUTEID FROM BOOLEANVALUE WHERE ATTRIBUTEID="+getIID()+" "
      );
      if (rs.next()) yes = true;     
    }
    finally { if (qp != null) qp.close(); }      
    return yes;
  }
    
  public boolean isenum() throws OculusException
  {   
    Primitive prim = getPrimitive();
    if (prim.equals(Primitive.ENUM) || prim.equals(Primitive.MULTIENUM) || prim.equals(Primitive.MULTICHECK) || prim.equals(Primitive.RADIO))
      return true;
    else
      return false; 
  }   
           
   
  public IRAttribute makecopy() throws OculusException
  {
    IObjectContext context = getObjectContext();    
    IRDataType copydtt=getDataType();    
    AttributeKind ak = getAttrKind();
    if (isenum()) 
      copydtt = copydtt.makecopy();
   
    IRAttribute state = (IRAttribute)context.getCRM().getCompObject(context,"Attribute",(IDataSet)null,true);
    state.setName("Copy of " +getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.isActive(true);
    state.setConfigKind(getConfigKind());
    state.setAttrKind(getAttrKind());  
    state.setUnitPosition(getUnitPosition());  
    state.setUnitLabel(getUnitLabel());  
    state.setDataType(copydtt);  
    state.setExpression(getExpression());  
    state.setUserPrompt(getUserPrompt());  
    state.setMaxLength(getMaxLength());  
    state.setUserPrompt(getUserPrompt());  
    return state;    
  }            
          
  public IRAttribute getRollUpAttribute() throws OculusException
  {
    AttributeKind ak = getAttrKind();
    if (ak.getIntValue() == AttributeKind.AGGREGATE.getIntValue())
    {
      String exp = getExpression();
      if (StringUtil.isValidIntOrLong(exp))
      {
        IIID id = StringUtil.getIID(exp);
        IRAttribute att = (IRAttribute)getObjectContext().getCRM().getCompObject(getObjectContext(),"Attribute",id); 
        return att;              
      }
    }
    return null;   
  }       
}