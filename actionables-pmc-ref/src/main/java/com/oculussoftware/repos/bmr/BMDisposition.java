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

public class BMDisposition extends BMModelElement implements IRDisposition
{


  public static final String TABLE="DISPOSITION";    
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_GUID="GUID";  
  public static final String COL_DELETEKIND="DELETESTATE";  
  public static final String COL_CONFIGKIND="CONFIGUREKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";    
  public static final String COL_FOLDID="TARGETLOCATIONID";    
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  public static final String COL_VERID="CONTEXTOBJID";  
  
  //Instance variables
  
  ILock _lock=null;  
  ConfigKind _configkind=null;  
  private  long[] _notifiees = null;  
  private  IIID _folderIID = null;  
  private  IIID _verIID = null;  
  int recovery = -1;
  //Public setters
  
  
  public BMDisposition() throws OculusException
  {
    setGUID(new GUID()); 
  }
  
  public IRDisposition setFolder(IFolder folder) throws OculusException
  {       
    if (folder == null ) _folderIID = null;
    else 
      _folderIID = folder.getIID();    
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);        
    return this;
  }
  
  public IRDisposition setProdVerIID(IIID id) throws OculusException
  {       
    _verIID = id;
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);        
    return this;
  }
  
  public IFolder getFolder() throws OculusException
  {
    if (_folderIID != null)
      return  (IFolder) context.getCRM().getCompObject(context,"Folder",_folderIID);    
    return (IFolder)null;    
  }
  
  public IIID getProdVerIID() throws OculusException
  {
    return _verIID;    
  }
  
  public IRDisposition setRecipients(long[] v) throws OculusException
  {
    _notifiees = v;        
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);        
    return this;
  }
      
  public IPersistable recover( ) throws ORIOException    
  {
    setPersState(PersState.MODIFIED);        
    setDeleteState(DeleteState.NOT_DELETED);
    isActive(true);
    recovery = 2;
    return this;
  }


  /******************************************************
  Private setters 
  *******************************************************/
  
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);                  

    setGUID(new GUID(rs.getString(COL_GUID).trim()));
    setIID(new SequentialIID(rs.getLong(COL_OBJECTID)));
    setName(rs.getString(COL_NAME).trim());
    setDescription(rs.getString(COL_DESC).trim());          
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
    isActive(rs.getBoolean(COL_ISACTIVE));                        
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));
    if (rs.getLong(COL_FOLDID) == 0) _folderIID = null;
    else _folderIID = new SequentialIID(rs.getLong(COL_FOLDID));                      
    if (rs.getLong(COL_VERID) == 0) _verIID = null;
    else _verIID = new SequentialIID(rs.getLong(COL_VERID));                      
  }
   
  public IUserColl getRecipients() throws OculusException
  {             
    IObjectContext ctxt = getObjectContext();
    return (IUserColl) ctxt.getCRM().getCompObject(ctxt,"InputRecipientColl",getIID());
  }
     
  /******************************
 Public database access
 *******************************/  

  protected String getLoadQuery()
    throws OculusException
  {
    return "SELECT * FROM DISPOSITION WHERE OBJECTID="+getIID()+" AND ISACTIVE=1";
  }

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
    
      if (isActive() && recovery < 0)
      {
        String updatequery = " UPDATE DISPOSITION SET NAME='"+SQLUtil.primer(name,250)+"' "+
                             ", TARGETLOCATIONID="+_folderIID+
                             (_verIID != null ? ", CONTEXTOBJID="+_verIID : "")+
                             " WHERE OBJECTID="+getIID();
        psp.update(updatequery);                     
        
        psp.update ("DELETE FROM INPUTRECIPIENT WHERE DISPOSITIONID="+getIID());                                     
        if (_folderIID != null) _notifiees = null;         
        if (_notifiees != null)
        {
          int size = _notifiees.length; 
          for(int i =0; i < size; ++i)
          {
            if (_notifiees[i] != -1)
            {
              psp.update(       
                    "INSERT INTO INPUTRECIPIENT (DISPOSITIONID,RECIPIENTID) VALUES( "
                              +getIID()+","+_notifiees[i]+")"
                );
            }//end if
          }//end for                  
        }//end if
      }//end if
      else if (!isActive() && recovery < 0)
      {         
        String updatequery = "UPDATE DISPOSITION SET ISACTIVE=0, DELETESTATE=0 WHERE OBJECTID="+getIID();
        psp.update(updatequery);                     
      }   
      else if (recovery > 0)
      {         
        String updatequery = "UPDATE DISPOSITION SET ISACTIVE=1, DELETESTATE=1 WHERE OBJECTID="+getIID();            
        psp.update(updatequery);                     
      }      
    }
    finally { psp.close();setPersState(PersState.UNMODIFIED);}  
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
      psp.update("DELETE FROM INPUTRECIPIENT WHERE DISPOSITIONID="+getIID());
      psp.update("DELETE FROM PRODUCTCONTEXTITEM WHERE DISPOSITIONID="+getIID());
      psp.update("DELETE FROM DISPOSITION WHERE OBJECTID="+getIID());
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
      int ck = getConfigKind().getIntValue();
      psp.update(       
              "INSERT INTO "+TABLE+" ("
             +COL_OBJECTID+", "
             +COL_GUID+", "
             +COL_ISACTIVE+", "
             +COL_CONFIGKIND+", "
             +COL_DELETEKIND+", "  
             +(_folderIID!=null?COL_FOLDID+",":"")      
             +(_verIID!=null?COL_VERID+",":"")                 
             +COL_NAME+", "         
             +COL_DESC+
           ") "+
           " VALUES ("
            +getIID().getLongValue()+",'"
            +guid+"',1,"+ck+",1,"
            +(_folderIID != null ?_folderIID+", ":"")              
            +(_verIID != null ?_verIID+", ":"")              
            +"'"+SQLUtil.primer(name.trim(),250)+"',"                      
            +"'"+SQLUtil.primer(description.trim(),250)+"')"
      );
       
     /*
       This feature forces notifees to be null when _folderIID is not null
       This way we do not inadvertently notify people if skipinbox is true.
     */  
      if (_folderIID != null) _notifiees = null;
      if (_notifiees != null)
      {
        int size = _notifiees.length;
        for(int i =0; i < size; ++i)
          if (_notifiees[i] != -1)
            psp.update("INSERT INTO INPUTRECIPIENT (DISPOSITIONID,RECIPIENTID) VALUES("+getIID()+","+_notifiees[i]+")");
      }
    }
    finally { if (psp != null) psp.close(); setPersState(PersState.UNMODIFIED);}  
  }
  
  public IPersistable delete( ) throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }  
  
  public ILock getLock() { return _lock;}

 
  public IPersistable softdelete() throws ORIOException 
  {
    setPersState(PersState.MODIFIED);
    _deletestate = DeleteState.DELETED;
    isactive = false;  
    return this;
  }
   
  public  Object dolly() throws OculusException       
  { 
    BMDisposition state = new BMDisposition();
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setGUID(getGUID());
    state.setIID(getIID());
    state.setName(getName());
    state.setDescription(getDescription());
    state.isActive(isActive());    
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());    
    state._folderIID = _folderIID;    
    state._verIID = _verIID;    
    state.setRecipients(_notifiees);
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
      String query =  " SELECT prod.PRODCONTEXTLISTID,inp.RECIPIENTID FROM PRODCONTEXTITEM prod, INPUTRECIPIENT inp "+
                      " WHERE prod.DISPOSITIONID="+getIID()+" OR inp.DISPOSITIONID="+getIID();
      rs = qp.retrieve(query);
      yes = rs.next();
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }      
    return yes; 
    
  }

}
  
  
 