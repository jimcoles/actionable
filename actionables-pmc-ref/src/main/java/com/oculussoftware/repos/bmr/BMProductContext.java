/**

@author Alok Pota
**/

/**
*/

/*****

Change history

**/

package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.repi.*;
import java.util.*;
import java.sql.*;

public class BMProductContext extends BMModelElement implements IRProductContext
{

//
  public static final String TABLE_NAME="PRODCONTEXTLIST";    
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";  
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";      
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  
  //Instance variables
  ILock _lock=null;    
  long[] _dispositions;


  public BMProductContext() throws OculusException
  {   
     setGUID(new GUID());    
  }
  
  public void  setDispositions(long[] v) throws OculusException
  {
    _dispositions = v;            
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);          
  }
      
  public IRElement setDescription(String s)
  {
    description = s;
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);          
    return this;  
  }

  public IRElement setName(String s)
  {
    name = s;  
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);          
    return this;  
  }
  
  public IRModelElement setConfigKind(ConfigKind id) throws ORIOException
  {    
    _configkind = id;
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);          
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
    setPersState(PersState.UNMODIFIED);
  }
   
  public IRModelElementList getDispositions() throws OculusException
  {                  
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"SpecDispositionList",getIID());     
  }
        
 
  /******************************
 Public database access
 *******************************/  

  /*******
  select
  ****/
  protected String getLoadQuery()
    throws OculusException
  {
    return "SELECT * FROM PRODCONTEXTLIST WHERE OBJECTID="+getIID()+" AND ISACTIVE=1";
  }

 /******************************
 Update
  ********************/

   protected void update()
    throws OculusException
  {  
    IRConnection jdtC = null;
    IQueryProcessor psp = null;    
    try
    {  
      jdtC = getDatabaseConnection();
      psp = jdtC.createProcessor();    
      psp.setSingleton(false);
      psp.update("UPDATE PRODCONTEXTLIST SET NAME='"+SQLUtil.primer(name,250)+"' WHERE OBJECTID="+getIID());       
          
      //Flush & fill          
      psp.update("DELETE PRODUCTCONTEXTITEM WHERE PRODCONTEXTLISTID="+getIID());
      if (_dispositions != null)
      { 
        int size = _dispositions.length;        
        for(int i =0; i < size; ++i)
        {                 
          int j = i+1;
          psp.update("INSERT INTO PRODUCTCONTEXTITEM (PRODCONTEXTLISTID,DISPOSITIONID,ORDERNUM) VALUES( "                                   
                     +getIID()+","+_dispositions[i]+","+j+")");
        }      
      }
    }
    finally { if (psp != null) psp.close();setPersState(PersState.UNMODIFIED);    }  
  }

 /********
 Delete
 ***/  
  protected void harddelete()
  throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor psp = null;     
    try
    {          
      jdtC = getDatabaseConnection();
      psp = jdtC.createProcessor();
      psp.setSingleton(false);      
      psp.update("DELETE FROM PRODUCTCONTEXTITEM WHERE PRODCONTEXTLISTID="+getIID());
      psp.update("DELETE FROM PRODCONTEXTLIST WHERE OBJECTID="+getIID());    
    }
    finally {if (psp != null) psp.close();}
  }  
 
 /******************************
 Insert
  ********************/  
  protected void insert()
    throws OculusException
  {  
    IRConnection jdtC = null;
    IQueryProcessor psp = null;    
    try
    {
      jdtC = getDatabaseConnection();        
      psp = jdtC.createProcessor();    
      psp.setSingleton(false);
      long mcHack = context.getRepository().genReposID().getLongValue();        
      guid = genGUID();  
      int ck =getConfigKind().getIntValue();
          
      psp.update("INSERT INTO PRODCONTEXTLIST (OBJECTID, GUID, ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION) VALUES( "
                      +getIID().getLongValue()+",'"                    
                      +guid.toString()+"',"  
                      +"1,1,"+ck+",'"  
                      +SQLUtil.primer(name.trim(),250)+"','"                    
                      +SQLUtil.primer(name.trim(),250)+"'"                      
                      +")");
          
      //Just Fill
      if (_dispositions != null)
      {
        int size = _dispositions.length;        
        for(int i =0; i < size; ++i)
        {                 
          int j = i+1;
          psp.update("INSERT INTO PRODUCTCONTEXTITEM (PRODCONTEXTLISTID,DISPOSITIONID,ORDERNUM) VALUES( "                                   
                      +getIID()+","+_dispositions[i]+","+j+")");
        }      
      }
    }
    finally { if (psp != null) psp.close();setPersState(PersState.UNMODIFIED);}  
  }
  
  public IPersistable delete( ) throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }  
  
  public ILock getLock() { return _lock;}

  public IPersistable softdelete() throws ORIOException 
  {
    return this;
  }
   
  public  Object dolly() throws OculusException       
  { 
    BMProductContext state = new BMProductContext();
    state.setIID(getIID());
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setName(getName());
    state.setDescription(getDescription());    
    state.isActive(isActive());    
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());        
    state.setDispositions(_dispositions);
    state.setPersState(getPersState());
    return state;
  } 
 
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
      String query =  " SELECT * FROM IFCPRODLISTUSAGE WHERE PRODLISTID = "+getIID();
      rs = qp.retrieve(query);
      yes = rs.next();
    }
    finally { if (qp != null) qp.close(); }      
    return yes;
  }

 
  //I think its faster to notify users this way. Instead of processing different
  //collections and getting only distinct values. The entire user object does not
  //have to be loaded. long[] disps is passed from the UI
  public java.util.List notify(IMarketInput inp, long[] disps) throws OculusException
  {
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;
    List list = new ArrayList();
    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query = "SELECT DISTINCT (appuser.OBJECTID) FROM APPUSER appuser "+
        " LEFT OUTER JOIN INPUTRECIPIENT inp ON inp.RECIPIENTID = appuser.OBJECTID ";
        
      int size = disps.length;
      if (size > 0) 
      {
        query += " WHERE ";
        int kk = size-1;
        for(int j =0; j < kk; ++j)        
        {
        //  if (disps[j] != -1)
            query += " inp.DISPOSITIONID="+disps[j]+" OR ";      
        }
        //if (disps[kk] != -1) 
        query +=  " inp.DISPOSITIONID="+disps[kk];
      }     
      rs = qp.retrieve(query);
      while(rs.next())
      {       
        INotification nt = inp.createNotification();
        nt.setAckMask(0);
        IIID iid = new SequentialIID(rs.getLong("OBJECTID"));
        list.add(iid);
        nt.setRecipientIID(iid);
        nt.setBody("You have an input with ID "+inp.getIID().toString());
        nt.setSubject("You have an input with ID "+inp.getIID().toString());
        nt.setNotificationKind(NotificationKind.INPUT);         
      }
    }
    finally {if (qp != null) qp.close();}    
    return list;
  } 
  
  
  public void associate(IMarketInput inp) throws OculusException
  {
    IRModelElementList list = getDispositions();
    int size = list.size();
    IFolderInputLink filk =null;
    IFolder folder =null;
    for(int i =0; i < size; ++i)
    {
     IRDisposition disp = (IRDisposition)list.getModelElement(i);
     folder = disp.getFolder();
     if (folder != null)
      {       
       filk = inp.getFolderInputLink(folder);
       if (filk == null)
        {
           TransactionMgr.getInstance().getTransaction(context).startTransaction();
           inp = (IMarketInput)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInput",inp.getIID(), true); 
           inp.associateFolder(folder);       
           getObjectContext().getCRM().commitTransaction(getObjectContext());
        }
      }
    }
  } 


}