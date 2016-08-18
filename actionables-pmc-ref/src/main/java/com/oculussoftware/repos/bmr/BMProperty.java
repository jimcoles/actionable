package com.oculussoftware.repos.bmr;

/****
ISSUE HISTORY

/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 ISSUE BUG00071    APota          5/15      3:12 pm    Saving a date values throws weird syntax errors
 ISSUE BUG00071    APota          7/12                 Need to make setValue() method more robust in type checking. A Javascript
                                                       validation error is making the database corrupt. Need to check if 
                                                       integer is numeric/float is numeric.




**/


import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.util.*;
import com.oculussoftware.system.*;

import java.util.*;
import java.sql.*;

public class BMProperty implements IRProperty
{
  protected final String TABLE_CHAR="CHARVALUE";  
  protected final String TABLE_LONGCHAR="LONGCHARVALUE";  
  protected final String TABLE_BOOLEAN="BOOLEANVALUE";  
  protected final String TABLE_TIME="TIMEVALUE";  
  protected final String TABLE_BLOB="BLOBVALUE";  
  protected final String TABLE_ENUM="ENUMVALUE";  
  protected final String TABLE_MULTIENUM="ENUMSELECTION";  

  protected final String COL_OBJECT="PAROBJECTID";  
  protected final String COL_ATTRIB="ATTRIBUTEID";  
  protected final String COL_VALUE="VALUE";  
  protected final String COL_ENUMLIT="ENUMLITERALID";  

  protected IRObject _parent;
  protected IIID _attribIID;
	protected boolean _required = false;
  
  protected Object _value=null;  
  protected PersState _perState;
	
	private static final String NULL_VALUE = "(none)";
  
	/**
	Construtor for creating new Attributes. 
	*/
  public BMProperty()
	{
		setPersState(PersState.UNINITED);
	}  

  public BMProperty(IRObject parentObject) throws ORIOException
  {     
	  _parent = parentObject;
	  setPersState(PersState.UNINITED);
  }  
  
	public IRProperty setRequired(boolean req)
	{
		_required = req;
		return this;
	}
	
	public boolean isRequired()
	{
		return _required;
	}
	
	public void delete() throws OculusException
	{
		if (getOwnerObject() == null)
			throw new ORIOException("Attempt to delete an attribute before defining its owner.");
	  IQueryProcessor qp = null;
		IRConnection jdtC = null;
		try
		{
			String tableName = getTableName();
			jdtC = CRM.getInstance().getDatabaseConnection(getObjectContext());    
	    qp = jdtC.createProcessor();
	    qp.setSingleton(false);					// because of MULTILISTs
	    qp.update(" DELETE FROM "+tableName+
								" WHERE "+COL_OBJECT+"="+getOwnerObject().getIID()+
								" 	AND "+COL_ATTRIB+"="+getDefnObject().getIID());
		}
		finally	{ if (qp != null)qp.close(); }
	}

  public IRAttribute getDefnObject()
		throws OculusException
  {
		return (IRAttribute)getObjectContext().getCRM().getCompObject(getObjectContext(),"Attribute",_attribIID);
  }  

  public IObjectContext getObjectContext()
		throws ORIOException
  {
		return getOwnerObject().getObjectContext();
  }  
  
	public IRObject getOwnerObject() { return _parent;  }  
  public PersState getPersState() {	return _perState; }  
	
  private String getTableName() throws OculusException
  {
		Primitive prim = getDefnObject().getDataType().getTypeKind();
		String tableName = null;
		
	 	if (prim.equals(Primitive.TIME))
			tableName = TABLE_TIME;
		else if (prim.equals(Primitive.BOOLEAN))
			tableName = TABLE_BOOLEAN;
    else if (prim.equals(Primitive.ENUM) || prim.equals(Primitive.RADIO))
			tableName = TABLE_ENUM;
    else if (prim.equals(Primitive.MULTIENUM) || prim.equals(Primitive.MULTICHECK))
			tableName = TABLE_MULTIENUM;
		else if (prim.equals(Primitive.LONG_CHAR))
			tableName = TABLE_LONGCHAR;
		else if (prim.equals(Primitive.BLOB))
			tableName = TABLE_BLOB;
		else
			tableName = TABLE_CHAR;
		return tableName;
  }  
  
  protected void insert() throws OculusException
  {
		if (getOwnerObject() == null)
		  throw (ORIOException)(new ORIOException("Attempt to insert an attribute before defining its owner.").fillInStackTrace());
	
		IRConnection jdtC = null;
		IPreparedStatementProcessor psp = null;
	  IQueryProcessor qp = null;
		String tabName = getTableName();
	  Primitive prim = getPrimitive();
  
		IIID attribIID = getDefnObject().getIID();
		IIID parentIID = getOwnerObject().getIID();
		
	  Object value = getValue();  
    
		try
		{    
			jdtC = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());                
			if (value == null)
			{
				if (!prim.equals(Primitive.MULTIENUM) && !prim.equals(Primitive.MULTICHECK))
				{
					qp = jdtC.createProcessor();
					qp.update("INSERT INTO "+tabName+
										" ("+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
										" ("+attribIID+","+parentIID+")");
				}
			}
			else
			{
				if (prim.equals(Primitive.MULTIENUM) || prim.equals(Primitive.MULTICHECK))
				{
					long values[] = StringUtil.getLongArray(value.toString());        
					qp = jdtC.createProcessor();
					String x = "";
					for(int i =0; i < values.length; ++i)
					  if (values[i] != IDCONST.DUMMYLITERAL.getLongValue())
							qp.update("INSERT INTO ENUMSELECTION "+
						    				" ("+COL_ENUMLIT+","+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
												" ("+values[i]+","+attribIID+","+parentIID+")");
				}
				else if (prim.equals(Primitive.BLOB) || prim.equals(Primitive.LONG_CHAR))				
				{
					psp = jdtC.prepareProcessor(
											"INSERT INTO "+tabName+
					  					" ("+COL_VALUE+","+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
					  					" (?,"+attribIID+","+parentIID+")");

					if (prim.equals(Primitive.BLOB))
					{
						java.io.InputStream is = (java.io.InputStream)value;
			      psp.setBinaryStream(1, is, is.available());  
					}
					else
					if (prim.equals(Primitive.LONG_CHAR))
						psp.setString(1, value.toString());
						
					psp.update();	
				}
				else
				{
					if (prim.equals(Primitive.INTEGER))
          {
            value = StringUtil.isValidIntOrLong(value.toString()) ? value : "0";            
            value = "'"+SQLUtil.primer(value.toString(),250)+"'";  
          }
          else if (prim.equals(Primitive.DECIMAL))          
          {
            value = StringUtil.isValidFloat(value.toString()) ? value : "0";            
            value = "'"+SQLUtil.primer(value.toString(),250)+"'";  
          }          
          else  if (prim.equals(Primitive.BOOLEAN))          
            value = value.toString().equals("true") ? "1" : "0";         
          else  if (prim.equals(Primitive.TIME))          
            value = "'"+value+"'";  
          else  if (prim.equals(Primitive.CHAR))          
            value = "'"+SQLUtil.primer(value.toString(),250)+"'";                   
      
         qp = jdtC.createProcessor();
         if (value == null || value.toString().equals("null") || value.toString().equals(""))
            {
              qp.update("INSERT INTO "+tabName+
                      " ("+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
                      " ("+attribIID+","+parentIID+")");    
            } 
        else
           {  
              qp.update("INSERT INTO "+tabName+
                      " ("+COL_VALUE+","+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
                      " ("+value+","+attribIID+","+parentIID+")");    
            }					      
				 }
			}
		}    //end try
		catch(java.io.IOException ex) { throw new ORIOException(ex); }    
  	finally { if (psp != null) psp.close(); if (qp != null) qp.close();}
  } 
  
  public void load(IDataSet rs)
  	throws OculusException
  {
	  if (getOwnerObject() == null)
	    throw new ORIOException("Attempt to load an attribute before defining its owner.");

    _attribIID = getObjectContext().getRepository().makeReposID(rs.getLong(COL_ATTRIB));
		setRequired(rs.getInt("ISREQUIRED")==1?true:false);

    String tableName = getTableName();
    if (tableName.equals(TABLE_TIME))
		{
	    if (rs.containsKey("timeValue")) setValue(rs.getTimestamp("timeValue"));
		}
    else if (tableName.equals(TABLE_BOOLEAN))
		{
	    if (rs.containsKey("boolValue")) setValue(new Boolean(rs.getBoolean("boolValue")));
		}
    else if (tableName.equals(TABLE_LONGCHAR))
		{
  	  if (rs.containsKey("longCharValue")) setValue(rs.getString("longCharValue"));
		}
    else if (tableName.equals(TABLE_CHAR))
		{
    	if (rs.containsKey("charValue")) setValue(rs.getString("charValue"));
		}
    else if (tableName.equals(TABLE_BLOB))
		{
	  	if (rs.containsKey("blobValue")) setValue(rs.getBinaryStream("blobValue"));
		}
    else if (tableName.equals(TABLE_ENUM))
		{
	    if (rs.containsKey("enumValue"))
			{
				long val = rs.getLong("enumValue");
				if (val != 0)
					setValue(new Long(val));
			}
		}

    setPersState(PersState.UNMODIFIED);  
  }  
  
  public void save() throws OculusException
  {
		if (_parent.getPersState().equals(PersState.NEW)) insert();
		if (_parent.getPersState().equals(PersState.DELETED)) delete();
		if (getPersState().equals(PersState.MODIFIED)) update();
	
	  setPersState(PersState.UNMODIFIED);  
  }  

  public void setDefnObject(IIID id) throws ORIOException
  {
	  _attribIID = id;
  }
  
  public void setDefnObject(IRAttribute attrib) throws ORIOException
  {
		setDefnObject(attrib.getIID());
  }  

  public void setOwnerObject(IRObject obj) throws ORIOException
  {
	  _parent = obj;
  }  
  
  public IRProperty setPersState(PersState state)
  {
	  _perState = state;
		if ((getPersState().equals(PersState.MODIFIED) || 
				getPersState().equals(PersState.NEW) ||
				getPersState().equals(PersState.DELETED)) && getOwnerObject().getPersState().equals(PersState.UNMODIFIED))
			getOwnerObject().setPersState(PersState.MODIFIED);
	  return this;
  }  
  
  public Primitive getPrimitive() throws OculusException
  {
	  return getDefnObject().getPrimitive();
  }  
  
  public String getName() throws OculusException
  {  
	  return getDefnObject().getName();
  }  
  
  public AttributeKind getAttributeKind() throws OculusException
  {  
  	return getDefnObject().getAttrKind();
  }  
  
  
	public IRProperty setValue(Object value)
		throws ORIOException
	{
		    
    String strValue = null;    
		if (value != null && !value.toString().equals(""))
			strValue = value.toString();
   
		try
    {
      Primitive prim = getPrimitive();
      AttributeKind ak = getAttributeKind();
      
      if (prim.equals(Primitive.BOOLEAN))
				_value = strValue == null ? null : new Boolean(strValue.equals("true"));
      else if (prim.equals(Primitive.TIME))
      {
        if (strValue != null) strValue = StringUtil.removeLeadingSpaces(strValue); 
				_value = strValue == null ? null : com.oculussoftware.ui.DateFormatter.getDateTimestamp(strValue);
      }
      else if (prim.equals(Primitive.INTEGER))
      {      
        _value = StringUtil.isValidIntOrLong(strValue)? new Integer(strValue) : null;          
      }
      else if (prim.equals(Primitive.DECIMAL))
      {
        _value = StringUtil.isValidFloat(strValue)? new Float(strValue) : null;          
      }
      else if ((prim.equals(Primitive.ENUM) || prim.equals(Primitive.RADIO)))
        _value = (strValue == null || strValue.equals("0")) ? null : new Long(strValue);
      else
				_value = strValue;

		  if (getPersState().equals(PersState.UNMODIFIED))
				setPersState(PersState.MODIFIED);
			if (getPersState().equals(PersState.UNINITED))
				setPersState(PersState.UNMODIFIED);
    }
    catch(OculusException ex) { throw new ORIOException(ex);}
		return this;
	}
  
  protected void update() throws OculusException
  {
		if (getOwnerObject() == null)
		  throw new ORIOException("Attempt to update an attribute before defining its owner.");
	
		String tabuname = getTableName();
		if (tabuname == null)
		  throw new ORIOException("Attempt to update an attribute before loading it.");
	
		IRConnection jdtC = null;
		IPreparedStatementProcessor psp = null;
	  IQueryProcessor qp = null;
		String tabName = getTableName();
	  Primitive prim = getPrimitive();
  
		IIID attribIID = getDefnObject().getIID();
		IIID parentIID = getOwnerObject().getIID();
		
	  Object value = getValue();  
		try
		{    
			jdtC = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
			if (prim.equals(Primitive.BOOLEAN) && value == null) value = "false";
			if (value == null || (value.toString().equals("") || value.toString().equals("null") || value.toString().equals(" ")))
			{
				if (!prim.equals(Primitive.MULTIENUM) && !prim.equals(Primitive.MULTICHECK))
				{
					qp = jdtC.createProcessor();
					qp.update("UPDATE "+tabName+
										" SET "+COL_VALUE+" = NULL "+
										" WHERE "+COL_ATTRIB+"="+attribIID+
											" AND "+COL_OBJECT+"="+parentIID);
				}
			}
			else
			{
				if (prim.equals(Primitive.MULTIENUM) || prim.equals(Primitive.MULTICHECK))
				{
					qp = jdtC.createProcessor();
					qp.setSingleton(false);
					qp.update(" DELETE FROM "+tabName+
										" WHERE "+COL_OBJECT+"="+parentIID+
										" 	AND "+COL_ATTRIB+"="+attribIID);

					long values[] = StringUtil.getLongArray(value.toString());        
					for(int i =0; i < values.length; ++i)
					{
					  if (values[i] != IDCONST.DUMMYLITERAL.getLongValue())
					  {
							qp = jdtC.createProcessor();
							qp.update("INSERT INTO "+tabName+" "+
						    				" ("+COL_ENUMLIT+","+COL_ATTRIB+","+COL_OBJECT+") VALUES "+
												" ("+values[i]+","+attribIID+","+parentIID+")");
					  }
					}
				}
				else if (prim.equals(Primitive.BLOB) || prim.equals(Primitive.LONG_CHAR))				
				{
					psp = jdtC.prepareProcessor(
										" UPDATE "+tabName+
										" SET "+COL_VALUE+"=? "+
										" WHERE "+COL_ATTRIB+"="+attribIID+
											" AND "+COL_OBJECT+"="+parentIID);

					if (prim.equals(Primitive.BLOB))
					{
						java.io.InputStream is = (java.io.InputStream)value;
			      psp.setBinaryStream(1, is, is.available());  
					}
					else
					if (prim.equals(Primitive.LONG_CHAR))
						psp.setString(1, value.toString());
						
					psp.update();	
				}
				else
				{
					if (prim.equals(Primitive.INTEGER))
          {
            value = StringUtil.isValidIntOrLong(value.toString()) ? value : null;
            value = "'"+SQLUtil.primer(value.toString(),250)+"'";  
           
          }
          else if (prim.equals(Primitive.DECIMAL))
          {
            value = StringUtil.isValidFloat(value.toString()) ? value : null;
            value = "'"+SQLUtil.primer(value.toString(),250)+"'";  
           
          }
          else	if (prim.equals(Primitive.BOOLEAN))					
						value = value.toString().equals("true") ? "1" : "0";
					else	if (prim.equals(Primitive.TIME))					
            value = "'"+value+"'";  
          else if (prim.equals(Primitive.CHAR))
            value = "'"+SQLUtil.primer(value.toString(), 250)+"'";    
                
					qp = jdtC.createProcessor();
          if (value == null || value.toString().equals("null") || value.toString().equals(""))
            {              
              qp.update("UPDATE "+tabName+
                    " SET "+COL_VALUE+"= NULL"+
                    " WHERE "+COL_ATTRIB+"="+attribIID+
                      " AND "+COL_OBJECT+"="+parentIID);
            } 
          else
          {            
					qp.update("UPDATE "+tabName+
                    " SET "+COL_VALUE+"="+value+" "+
                    " WHERE "+COL_ATTRIB+"="+attribIID+
                      " AND "+COL_OBJECT+"="+parentIID);
          }
				}  
			}
		}    //end try
		catch(Exception ex) { throw new ORIOException("Error updating attribute "+getName()+": "+ex); }    
  	finally { if (psp != null) psp.close(); if (qp != null) qp.close();}
  } 
 
  public Object getValue()
  throws OculusException
  {
  	Object value = null;
  	IRAttribute attr = getDefnObject();
  	
  	if (attr.getAttrKind().equals(AttributeKind.AGGREGATE))
  	{
  		IRObject owner = getOwnerObject();
  		if (owner instanceof IBusinessObject)
  			value = _getRollUpValue((IBusinessObject)owner,attr.getRollUpAttribute());
  		else
  			throw new OculusException("Aggregate attributes can only be assigned to business objects.");	
  	}
  	else
  		value = _value;

   /*
   Saleem: I think the default value should be shown only at the
   time of creating something and the user gets to override the value. 
   The following overrides user specified values (if they are null)
   and returns the default value whenever something is null at both 
   create & edit time.
   
   if (_parent.getPersState().equals(PersState.NEW) && value == null)    
      value = getDefnObject().getDefaultValue();    
   
   */

    return value;
  }  

  // Returns the sum of the values of the given attribute for the given object
  // and all of it's children.  It returns a Float instead of float to keep the  attribute
  // values consistent.
  // NOTE: Attempting to use instanceof instead of getRootDefinition().
  private Float _getRollUpValue(IBusinessObject obj,IRAttribute attr)
  	throws OculusException
  { 
    StringBuffer sbf=null;
    String rollupkey = "prop"+attr.getIID();
    

  	if (obj instanceof IProductVersion)
      obj = ((IProductVersion)obj).getDefaultCategory();
    sbf = new StringBuffer();
    _rollUpCat((ICategory)obj,rollupkey,sbf); 
    
    //Use the stringbuffer to calculate the sum now
    //String s = sbf.substring(0,sbf.length()-1);            //chop off last ,
    String s = sbf.toString();
    sbf = null;																						//gc
    StringTokenizer st = new StringTokenizer(s,",");
    float sum = 0.0f;
    while(st.hasMoreTokens())
    {
      String t = st.nextToken();
      if (StringUtil.isValidFloat(t))
        sum += Float.parseFloat(t);
  		else
  			throw new ORIOException("Invalid attribute value in rollup for "+obj.getName()+","+attr.getName());	
    }
    st = null;
   	return new Float(sum);
  }

  // Returns a String/StringBuffer that contains a comma-separated list of all of the values
  // for the given attribute on the given category and all of it's subcategories and features.
  // It uses a String so that it can be used to roll-up any kind of attribute, not just numeric
  // ones.
  private void _rollUpCat(ICategory cat, String key, StringBuffer sbf) throws OculusException
  {    
    IFeatureColl featurecoll = cat.getFeatures();
    while (featurecoll.hasMoreFeatures())
    {
      
      IFeature feat = featurecoll.nextFeature();        
     
      IFeatureCategoryLink featlink = feat.getFeatureCategoryLinkObject();              
     
      IRPropertyMap map = feat.getProperties();
      if (map.containsKey(key))
      {
        IRProperty prop = (IRProperty)map.get(key);
        Object o = prop.getValue();
     
        if (o != null)
          sbf.append(o+",");
      }
  		map = featlink.getProperties();
      if (map.containsKey(key))
      {
        IRProperty prop = (IRProperty)map.get(key);
        Object o = prop.getValue();
        
        if (o != null)
          sbf.append(o+",");
      }
   }

   ICategoryColl catcoll = cat.getCategories();
   while (catcoll.hasMoreCategories())
     _rollUpCat(catcoll.nextCategory(),key,sbf);      
  }
 
 
  public  Object dolly() throws OculusException       
  { 
    BMProperty state = new BMProperty();
    state.setOwnerObject(getOwnerObject());
    state._attribIID = _attribIID;
    state._value = _value;
		state.setRequired(isRequired());
    state.setPersState(getPersState()); 
    return state;
	} 
 
  public String toString()
  {
		String strValue = NULL_VALUE;
		try
		{
			IRAttribute attr = getDefnObject();
			AttributeKind ak = attr.getAttrKind();
			IRDataType dt = attr.getDataType();
			Primitive prim = dt.getTypeKind();
			String unitlabel = attr.getUnitLabel();
			UnitPosition unitpos = attr.getUnitPosition();
			if (unitlabel == null) unitlabel = " ";

		  Object value = getValue();
		  
			if (value != null)
			{
			  strValue = value.toString();
			  if (!unitlabel.equals("") && (prim.equals(Primitive.CHAR) || prim.equals(Primitive.INTEGER) || prim.equals(Primitive.DECIMAL)))
			  {
			    if (unitpos.equals(UnitPosition.RIGHT))
			  		strValue = strValue + " " + unitlabel;			
			  	else
			  		strValue = unitlabel + " " + strValue;
			  }
			  else 
        if (prim.equals(Primitive.TIME))
        {
          strValue = com.oculussoftware.ui.DateFormatter.format(strValue);  
        }
        else      
			  if ((prim.equals(Primitive.ENUM) && !ak.equals(AttributeKind.PRODENUM)) || (prim.equals(Primitive.RADIO)))
			  {
			  	String enumLit = null;
			  	if (strValue != null)
			  	{		
			  		long enumLitID = Long.parseLong(strValue);
			  		IIID enumLitIID = getObjectContext().getRepository().makeReposID(enumLitID);
			  	  IREnumliteral enumlite = getObjectContext().getRepository().getBMRepository().getEnumliteral(enumLitIID,false);
			  	  enumLit = enumlite.getName();
			  	}
			  	strValue = enumLit;
			  }
			  else
			  if (prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK) 
			  {
			  	String enumLit = "";
			  	if (strValue != null)
			  	{
			  		StringTokenizer allValues = new StringTokenizer(strValue,",");
			  		while (allValues.hasMoreTokens())
			  		{
			    	 	long enumLitID = Long.parseLong(allValues.nextToken());
			    	 	IIID enumLitIID = getObjectContext().getRepository().makeReposID(enumLitID);
			    	  IREnumliteral enumlite = getObjectContext().getRepository().getBMRepository().getEnumliteral(enumLitIID,false);
			    	  enumLit += enumlite.getName();
			  			if (allValues.hasMoreTokens())
			  				enumLit += "<BR>";
			  		}
				  	strValue = enumLit;
			  	}
			  }
			}
		}
		catch (OculusException exp)
		{
			strValue = exp.toString();
		}
		return strValue;
  }

  public void renderView(IAttributeTable table)
  	throws OculusException
	{
		IRAttribute attr = getDefnObject();
		IRDataType dt = attr.getDataType();
		Primitive prim = dt.getTypeKind();
    String dispValue = this.toString();
    if (!StringUtil.isPrintable(dispValue))  dispValue = "-not specified-";
 		table.addAttribute(getName()+":",dispValue);
 	}

  public void renderEdit(IAttributeTable table)
    throws OculusException
  {
    IRAttribute attr = getDefnObject();
    AttributeKind ak = attr.getAttrKind();
    if (ak.equals(AttributeKind.SYSTEM_GENERATED) || ak.equals(AttributeKind.AGGREGATE)) 
    {
      renderView(table);
      return;
    }
    IRDataType dt = attr.getDataType();
    Primitive prim = dt.getTypeKind();

    if (ak.equals(AttributeKind.SYSTEM_GENERATED))
			return;
		
		String label = this.getDefnObject().getName();        
    if (isRequired()) label = "*"+label;
    ITableData data = null;
    if (label.length() < 30 && !prim.equals(Primitive.LONG_CHAR))
			data = table.addSingleLabel(label+":");
    else
     	data = table.addDoubleLabel(label+":");
    renderEdit(data,label);    
  }

  public void renderEdit(ITableData data)
    throws OculusException
  {
  	renderEdit(data,this.getDefnObject().getName());
  }

  public void renderEdit(ITableData data, String name)
    throws OculusException
  {
		IRAttribute attr = getDefnObject();
		AttributeKind ak = attr.getAttrKind();
		IRDataType dt = attr.getDataType();
	  IIID dtIID = dt.getIID();
	  Primitive prim = dt.getTypeKind();
		int maxlen = attr.getMaxLength();
	  if (maxlen == -1) maxlen = 250;
	  String unitlabel = attr.getUnitLabel();
	  UnitPosition unitpos = attr.getUnitPosition();
		String showName = attr.getName();
	  String fieldName="prop"+attr.getIID();
		fieldName = fieldName.replace('-','_');
    Object defVal = attr.getDefaultValue();
    
		if (attr.getIID().equals(IDCONST.FILENAME.getIIDValue()))
		{
		}
		else
		// ONLY USED FOR CREATE, NOT EDIT, SO NO NEED TO REMEMBER WHATS SELECTED
	  
	  if (prim.equals(Primitive.CHAR) || prim.equals(Primitive.INTEGER) || prim.equals(Primitive.DECIMAL))
	  {
			int size = 10;
			if (prim.equals(Primitive.CHAR)) size = 40;
			String value = "";
			IInput input = null;
      if (_parent.getPersState().equals(PersState.NEW))            
      {
        if (defVal != null) value = defVal.toString();
      }
      else
       {
         if (getValue() != null) value = getValue()+"";       
       }
         
      	if (unitpos.equals(UnitPosition.RIGHT))
		    {
					input =	data.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(size).setName(fieldName).setAlias(showName).setValue(value);
					data.addAnchor().setStringValue(unitlabel);
		    }
		    else
		    {
					data.addAnchor().setStringValue(unitlabel);
					input = data.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(size).setName(fieldName).setAlias(showName).setValue(value);
		  	}
        if (prim.equals(Primitive.INTEGER) || prim.equals(Primitive.DECIMAL))
          input.excludeSpellCheck();
			input.setValidation(ValidationKind.NOTAG1,showName);
			if (prim.equals(Primitive.INTEGER)) input.setValidation(ValidationKind.NUMERIC,showName);
			if (prim.equals(Primitive.DECIMAL)) input.setValidation(ValidationKind.FLOAT,showName);
			if (isRequired()) input.setValidation(ValidationKind.NOBLANK,showName);
		}
		else
		if (prim.equals(Primitive.LONG_CHAR))
		{
	  	String value = "";
			ITextArea input = null;
      if (_parent.getPersState().equals(PersState.NEW))            
      {
        if (defVal != null) value = defVal.toString();
      }
      else
       {
         if (getValue() != null) value = getValue()+"";       
       }
      input =	data.addTextArea().setName(fieldName).setAlias(showName).setStringValue(value);
			if (isRequired()) input.setValidation(ValidationKind.NOBLANK,showName);
		}
		else
	  if (prim.equals(Primitive.TIME))
	  {
	  	String value = "";
			IInput input = null; 
      if (_parent.getPersState().equals(PersState.NEW))            
      {
        if (defVal != null) value = com.oculussoftware.ui.DateFormatter.format(defVal.toString());
      }
      else
       {
         if (getValue() != null) value = com.oculussoftware.ui.DateFormatter.format(getValue()+"");           
       }     
	  	input = data.addInput().setType(InputKind.TEXT).setSize(10).setName(fieldName).setAlias(showName).setValue(value).excludeSpellCheck();
			data.addCalendar("parent.Body.document.forms[0]."+fieldName);
			input.setValidation(ValidationKind.DATE,showName);
			if (isRequired()) input.setValidation(ValidationKind.NOBLANK,showName);
		}
		else
	  if (prim.equals(Primitive.BOOLEAN))
	  {
	  	Boolean value = null;
      if (_parent.getPersState().equals(PersState.NEW))            
      {
        if (defVal != null) value = (Boolean)defVal;
      }
      else
       {
        if (getValue() != null)value = (Boolean)getValue();                
       }
      
	  	IInput input = data.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setAlias(showName).setValue("true");
			if (value != null && value.booleanValue())
				input.setChecked();
	  }
	  else
	  if (prim.equals(Primitive.ENUM))
	  {
      IREnumeration renum = getObjectContext().getRepository().getBMRepository().getEnum(dtIID,false);
      ISelect sel = data.addSelect().setSize(1).setName(fieldName);
      Object realValue = null;
      if (_parent.getPersState().equals(PersState.NEW))            
      {
        if (defVal != null)  realValue = defVal;       
      }
      else      
			  realValue = getValue();
      String strValue = "";
			if (realValue != null)
				strValue = realValue.toString();
			else
      {
				if (!isRequired())
					sel.addOption().setStringValue(NULL_VALUE).setValue(IDCONST.DUMMYLITERAL.getIIDValue().toString()).setSelected();
        else
					sel.addOption().setStringValue(NULL_VALUE).setValue("-1").setSelected();
      }

      IRModelElementList list = renum.getEnumLiterals();
      while(list.hasNext())
      {
				IREnumliteral enumLit = (IREnumliteral)list.next();
				String value = enumLit.getIID()+"";
				String enumName = enumLit.getName();
				IOption option = sel.addOption().setStringValue(enumName).setValue(value);
				if (value.equals(strValue))
					option.setSelected();
      }
 	    sel.setValidation(ValidationKind.NOSELECT,showName);
	  }
		else
		if (prim.equals(Primitive.RADIO))
		{
			String strValue = getValue()+"";
			IREnumeration renum = getObjectContext().getRepository().getBMRepository().getEnum(dtIID,false);
			IRModelElementList list = renum.getEnumLiterals();
			IInput inp = null;
			while(list.hasNext())
			{
				IREnumliteral enumLit = (IREnumliteral)list.next();
				String value = enumLit.getIID()+"";
				String enumName = enumLit.getName();
				inp = data.addInput().setType(InputKind.RADIO).setName(fieldName).setAlias(showName).setValue(value);
				if (value.equals(strValue))
					inp.setChecked();
				data.addAnchor(enumName);
				data.addBR();
			}
		}
		else
		{
			Set selectedValues = new TreeSet();
			if (prim.equals(Primitive.MULTIENUM) || prim.equals(Primitive.MULTICHECK))
			{
				String strValue = getValue()+"";
				StringTokenizer values = new StringTokenizer(strValue,",");
				while (values.hasMoreTokens())
					selectedValues.add(values.nextToken());
			}	

		  if (prim.equals(Primitive.MULTIENUM)) 
		  {
	  		IREnumeration renum = getObjectContext().getRepository().getBMRepository().getEnum(dtIID,false);
		  	ISelect sel = data.addSelect().setName(fieldName).setSize(4).setMultiple();
		    IRModelElementList list = renum.getEnumLiterals();
		    while(list.hasNext())
		    {
		    	IREnumliteral enumLit = (IREnumliteral)list.next();
		    	String value = enumLit.getIID()+"";
		    	String enumName = enumLit.getName();
			    IOption option = sel.addOption().setStringValue(enumName).setValue(value);
					if (selectedValues.contains(value))
						option.setSelected();
			  }
			  if (isRequired()) sel.setValidation(ValidationKind.NOSELECT,showName);
		  }
			else
			if (prim.equals(Primitive.MULTICHECK)) 
		  {
	      IREnumeration renum = getObjectContext().getRepository().getBMRepository().getEnum(dtIID,false);
	      IRModelElementList list = renum.getEnumLiterals();
	      while(list.hasNext())
	      {
	      	IREnumliteral enumLit = (IREnumliteral)list.next();
	      	String value = enumLit.getIID()+"";
	      	String enumName = enumLit.getName();
		      IInput inp = data.addInput().setType(InputKind.CHECKBOX).setName(value).setAlias(showName).setValue(value);
					if (selectedValues.contains(value))
						inp.setChecked();
		      data.addAnchor(enumName);
					data.addBR();
		    }
		  }
		}
  }
	
}