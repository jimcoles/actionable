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

public class BMEnumeration extends BMModelElement implements IREnumeration
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
  
  ILock _lock=null;
  private List _values = null;
  private  List _newvalues = null;
  

  public BMEnumeration()
		throws OculusException
  {
		setGUID(new GUID());    
  }
  

  public IREnumeration setValues(List v)
  {
    _newvalues = v;
    setPersState(PersState.MODIFIED);
    return this;
  }
      
  public IREnumeration setEnumID(IIID id)
		throws ORIOException
  {    
		setIID(id);
    return this;
  }
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    setGUID(new GUID(rs.getString(COL_BYTEGUID).trim()));
    setIID(getObjectContext().getRepository().makeReposID(rs.getLong(COL_ENUMID)));
    setName(rs.getString(COL_NAME).trim());
    setDescription(rs.getString(COL_DESC).trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));          
    isActive(rs.getBoolean(COL_ISACTIVE));                      
  }
   
  public IIID getEnumID()
		throws ORIOException
  {    
		return getIID();
  }
  
  //I have to do this because ther CRM will give stale _values. If I load the
  //entire enum once. 
  //Storing the enum in the CRM is good for changing its name & desc not for
  //changing the individual values of the enum.
  public List getValues() throws OculusException
  {
    loadit();    
    return _values;
  }

  /*******************  
  Execute a SQL Update
  ********************/

  protected void _setName(IRConnection jdtC, String bln, long id, int order)
    throws SQLException, ORIOException
  {
    
  IQueryProcessor psp = null;  
  try
  {
    bln  = SQLUtil.primer(bln);
    psp = jdtC.createProcessor();
    psp.update(    
        "UPDATE ENUMLITERAL SET ORDERNUM="+order+", NAME='"+bln.trim()+"'"          
      + " WHERE OBJECTID="+id      
        );    
  }
  finally {psp.close();}
  }  
  
 
    
  public void wipeAllLiterals()
    throws ORIOException
  {
    
  IQueryProcessor psp = null;  
  IRConnection jdtC = null;
  try
  {
  
    jdtC = getDatabaseConnection();      
    psp = jdtC.createProcessor();      
    psp.setSingleton(false);
    psp.update(    
        "DELETE FROM ENUMLITERAL WHERE ENUMERATIONID="+getIID().getLongValue()      
        );    
  }
  catch(Exception ex) { throw new ORIOException(ex);}
  finally {psp.close();}
  }  

  
  
  /*******************  
  Execute a SQL Select  
  ********************/
  
  protected void _sqlSelectObject(IRConnection jdtC)
    throws SQLException, ORIOException
	{
		
    String query = null;    
    IDataSet rs = null;
    IQueryProcessor qp = null;
    query = "SELECT * FROM ENUMLITERAL WHERE ENUMERATIONID="+getIID().getLongValue()+" AND ISACTIVE=1  ORDER BY ORDERNUM";
    
    try 
    {         
         qp = jdtC.createProcessor();         
         rs = qp.retrieve(query);
         Tuple tup = null;
         _values = new ArrayList();
         while(rs.next()) 
      {
        
        tup = new Tuple(rs.getLong("OBJECTID"),rs.getString("NAME"),rs.getInt("CONFIGUREKIND"),"Loaded");
        _values.add(tup);
        
      }
        
        
              
    }
    catch(Exception ex) { throw new ORIOException(ex);}
    finally { qp.close();}      
  }
  
  public ILock getLock() { return _lock;}
	public IPersistable delete() throws ORIOException { return this; }
	public IPersistable softdelete() throws ORIOException { return this; }
   
	public IREnumeration clearValues() throws ORIOException 
  {
    _values = null;
    _newvalues = null;
    return this;
  }
  
 /////////////////////////////////CLONE///////////////////////////
 /////////////////////////////////CLONE///////////////////////////
 /////////////////////////////////CLONE///////////////////////////
 
  public  Object dolly() throws OculusException       
  { 
    BMEnumeration state = new BMEnumeration();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setIID(getIID());
    state.setGUID(getGUID());
    state.setName(getName());
    state.isActive(isActive());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.setValues(getValues());
    state.setPersState(getPersState());
    
    return state;
  } 


	public IRModelElementList getEnumLiterals() throws OculusException
  {
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"EnumliteralList",getIID());
  }  


  /******************************
  Public database access
  *******************************/  
 
   public IPersistable load() throws OculusException
   { 
     return this;
   }
   
   public IPersistable loadit() throws OculusException
   {
   	IRConnection jdtC = null;
   	
     try {
       jdtC = getDatabaseConnection();
       _sqlSelectObject(jdtC);
       setPersState(PersState.UNMODIFIED);
   
     }
     catch (Exception ex) {
       throw new ORIOException(ex);
     }
     
     finally
     {
//      try {returnDatabaseConnection(jdtC);}
//      catch(Exception ex) { throw new ORIOException(ex);}
     }
     
     return this;
   }
 
 
   public IPersistable save() throws ORIOException
  
   {
     IRConnection jdtC = null;
     try {
       jdtC = getDatabaseConnection();    
       _sqlSaveObject(jdtC);              
     }
     catch(Exception ex) { throw new ORIOException(ex);}
     setPersState(PersState.UNMODIFIED);    
     return this;
   }
 
   
  /******************************
  	Protected database access
  
   Execute a SQL Insert
   ********************/
   
   
   protected void _sqlSaveObject(IRConnection jdtC)
     throws Exception, ORIOException
   {    
    if (_newvalues !=null) 
     {       
       int newSize = _newvalues.size();      
       for (int in = 0;in<newSize;++in)
       {
          Tuple tup = (Tuple) _newvalues.get(in);
          String mode = tup.getMode();
          long idx = tup.getIndex();
          if (idx < 0)
             {                                    
               if (mode.equals("Update")) 
                {
                   tup.setMode("Loaded"); _setName(jdtC,tup.getString(), idx,in+1);
                }        
                if (!mode.equals("Delete") && !mode.equals("Update") && !mode.equals("Loaded")) 
               {
                _sqlInsertObject(jdtC,in+1,tup);   
               }  
             }
             else 
           {
             /*
             Special case when you are copying literals from one list into another
             */
             if (mode.equals("Add")) 
             {             
               _sqlInsertObject(jdtC,in+1,tup);  
             }
             
             else if (mode.equals("Delete")) {_setActive(jdtC,0, idx);}                  
             else if (mode.equals("Update")) { tup.setMode("Loaded"); _setName(jdtC,tup.getString(), idx,in+1);}        
           }         
       } //for loop     
     }
       
   }
   
   protected void _sqlInsertObject(IRConnection jdtC, int order, Tuple tupl)
     throws SQLException, ORIOException, OculusException
   {
   
  IQueryProcessor psp = null;	
  	
  try
  {
   
  	String name = SQLUtil.primer(tupl.getString());
  	psp = jdtC.createProcessor();    
     long mcHack = context.getRepository().genReposID().getLongValue();        
     tupl.setIndex(mcHack);
     long refid = tupl.getRef();
     tupl.setMode("Loaded");
     guid = genGUID();  
     psp.update
       (       
           "INSERT INTO ENUMLITERAL (OBJECTID, ENUMERATIONID,GUID, ISACTIVE, DELETESTATE,CONFIGUREKIND,ORDERNUM,NAME,SHORTDESCRIPTION) VALUES( "
                     +mcHack+","
                     +getEnumID().getLongValue()+",'"                    
                     +guid.toString()+"',"  
                     +1+","  
                     +DeleteState.NOT_DELETED.getIntValue()+","                                          
                     +ConfigKind.FULL.getIntValue()+","                                               
                     +order+",'"                                            
                     +name.trim()+"','"                    
                     +getDescription().trim()+"'"                      
                     +")"
                       
       );
  }
  
   catch(Exception ex) { throw new ORIOException(ex);}
  finally { psp.close();}
  
  }
   
  protected void _setActive(IRConnection jdtC, int bln, long id)
    throws SQLException, ORIOException
  {
    
  IQueryProcessor psp = null;  
  try
  {
  
    psp = jdtC.createProcessor();
    psp.update(    
        "UPDATE ENUMLITERAL SET ISACTIVE="+bln+", DELETESTATE="+DeleteState.DELETED.getIntValue()          
      + " WHERE OBJECTID="+id      
        );    
      
  }  
  
    finally { psp.close();}
  }
   


}
  
  
 