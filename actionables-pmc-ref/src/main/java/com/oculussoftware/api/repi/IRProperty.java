package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import java.sql.*;
import java.util.*;

/** Low-level repository notion. */
public interface IRProperty
{
  public IRProperty setValue(Object value)
    throws ORIOException;

  public Object getValue()
    throws OculusException;
  
  public String getName()
    throws OculusException;
  
  
  public IRObject getOwnerObject()
    throws ORIOException;
  
  public void setOwnerObject(IRObject obj)
    throws ORIOException;
    
  public IRAttribute getDefnObject()
    throws OculusException;
    
  public Primitive getPrimitive()
    throws OculusException;
    
  public AttributeKind getAttributeKind()
    throws OculusException;

  public void setDefnObject(IRAttribute attrib)
    throws ORIOException;
  
  public void setDefnObject(IIID attrib)
    throws ORIOException;
  
     
  public void load(IDataSet rs)
    throws OculusException;
  
  public void save()
    throws OculusException;
	
	public void renderView(IAttributeTable table)
		throws OculusException;	
  
  public void renderEdit(IAttributeTable table)
    throws OculusException;

  public void renderEdit(ITableData data)
    throws OculusException;
    
  public void renderEdit(ITableData data, String name)
    throws OculusException;
    
  public PersState getPersState();
      
  public IRProperty setPersState(PersState state);
  
  public Object dolly() throws OculusException;
  
  public IRProperty setRequired(boolean req) throws OculusException;
  
  public boolean isRequired() throws OculusException;
}