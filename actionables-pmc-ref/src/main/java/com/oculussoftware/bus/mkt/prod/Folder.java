package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.ui.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Folder.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/

public class Folder extends BusinessObject implements IFolder
{
  
//  static public long CLASSID = Long.parseLong("62540471990036181L");
  protected String COL_FOLDERID = "FOLDERID";  
  protected IIID _folderIID;
  protected java.util.List trashlist=null;
  protected java.util.List displaylist=null;
  

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Folder() throws OculusException
  {
  super();
  TABLE = "FOLDER";
  COL_GUID = "GUID";
  
  }
  
  
  

  public Object dolly() throws OculusException
  {
    Folder cat = null;
      cat = new Folder();
      cat.setIID(getIID());
    cat.setObjectContext(getObjectContext());
    cat.setPersState(getPersState());
    cat._classIID = _classIID;
    cat._stateIID = _stateIID;
    cat.setDeleteState(getDeleteState());
      cat.setName(getName());
      cat.setDescription(getDescription());
    cat._creatorIID = _creatorIID;
    cat._accessIID = _accessIID;
    cat.setCreationDate(getCreationDate());
    cat.setMessageAttached(hasMessageAttached());
    cat.setLinkAttached(hasLinkAttached());
    cat.setFileAttached(hasFileAttached());
   if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
    cat.putAll(getProperties());
    cat._folderIID = _folderIID;    
    return cat;
  }
  
  public IFolder createCopy()
    throws OculusException
  {
    
    //First create the new folder
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.FOLDER.getIIDValue();
    IFolder newFolder = (IFolder)context.getCRM().getCompObject(context,"Folder",(IDataSet)null,true);
    IRClass marketInputClass = (IRClass)context.getCRM().getCompObject(context,"Class",classIID);
    newFolder.setDefnObject(marketInputClass);    
    newFolder.setName(getName());
    newFolder.setDescription(getDescription());
    newFolder.setAccessIID(getAccessIID());
    newFolder.setMessageAttached(hasMessageAttached());
    newFolder.setLinkAttached(hasLinkAttached());
    newFolder.setFileAttached(hasFileAttached());
    return newFolder;
  }
 
  
  public IFolder createNewFolder()
  throws OculusException
  {
  IFolder newCat;
  IIID classIID = IDCONST.FOLDER.getIIDValue();
  newCat = (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",(IDataSet)null,true);
  newCat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
  newCat.setParentFolder(this);  
  return newCat;
  }  
  
    
//----------------- IPersistable Methods ------------------------------------

  /**
  * Marks the bo as deleted.
  */
  public IPersistable delete()
  throws OculusException
  {
  super.delete();
  IFolderColl folders = getFolders(true);
    while (folders.hasMoreFolders())
      folders.nextFolder().delete();    
  return this;
  }  


  public IBusinessObject softDelete()
    throws OculusException
  { 
    super.softDelete();
    /*
    IFolderColl folders = getFolders(true);
    while (folders.hasMoreFolders())
      folders.nextFolder().softDelete();
    */  
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    /*
    IFolderColl folders = getFolders(true);
    while (folders.hasMoreFolders())
      folders.nextFolder().recover();
      */
    return this;
  }




//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
  throws OculusException
  {
  if (key instanceof String)
  {
    return super.get(key);
  }
  else
    return null;
  }  
  
  
//----------------- IFolder Methods ------------------------------------
 
  public IFolderColl getFolders()
    throws OculusException
  {
       
   return getFolders(false);
  }

  public IFolderColl getFolders(boolean b)
    throws OculusException
  {
       
   return (IFolderColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ChildFolderColl",getIID(), b);  
  }
      
      
  public IFolderInputLink getInputLink(IMarketInput inp)
    throws OculusException
  {
    IRConnection repConn=null; 
    IQueryProcessor qp =null;
    IDataSet rs = null;
    IFolderInputLink inplink = null;
    IObjectContext _context = getObjectContext();
    try
    {
    
    repConn = _context.getRepository().getDataConnection(_context);
    qp = repConn.createProcessor();
    rs = qp.retrieve("SELECT * FROM FOLDERINPUTLINK WHERE FOLDERID="+getIID().getLongValue()+" AND MARKETINPUTID="+inp.getIID().getLongValue());
    if (rs.next())
      {
      IIID id = new SequentialIID(rs.getLong("OBJECTID"));
      rs.setIID(id);
      inplink = (IFolderInputLink)_context.getCRM().getCompObject(_context,"FolderInputLink", rs);
      }
    }
    catch(Exception ex) { throw new OculusException(ex);}
    finally {    
    qp.close();
    getObjectContext().getCRM().returnDatabaseConnection(repConn);
    }
    return inplink;
  }           
  
  
  
  public void preDelete()
    throws OculusException
  {
    IRConnection repConn=null; 
    IQueryProcessor qp =null;
    IDataSet rs = null;    
    IObjectContext _context = getObjectContext();
    try
    {
    
    repConn = _context.getRepository().getDataConnection(_context);
    qp = repConn.createProcessor();
    qp.setSingleton(false);
    qp.update("UPDATE FOLDER SET FOLDERID=NULL WHERE FOLDERID="+getIID());
    }    
    finally {    
    qp.close();      
    }    
  }    
  
  
  public void batchMove(long[] inps, long targetfolderid)
    throws OculusException
  {
    IRConnection repConn=null; 
    IQueryProcessor qp =null;
    IDataSet rs = null;    
    IObjectContext _context = getObjectContext();
    try
    {
    
    repConn = _context.getRepository().getDataConnection(_context);
    qp = repConn.createProcessor();
    qp.setSingleton(false);
    for (int i =0; i < inps.length; ++i)             
      {
        qp.update("UPDATE FOLDERINPUTLINK SET FOLDERID="+targetfolderid+", REACTIONID=NULL WHERE OBJECTID="+inps[i]);
        qp.update("DELETE FROM REACTION WHERE INPUTLINKID="+inps[i]);
      }
    }    
    finally {    
    qp.close();      
    }    
  }                
      
  public IMarketInputColl getMarketInputs()
    throws OculusException
  {
       
   return getMarketInputs(false);
  }
  
  public IMarketInputColl getMarketInputs(boolean b)
    throws OculusException
  {
       
   return (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputFolderColl",getIID(), b);  
  }

 public IProblemStatementColl getProblemStatements()
    throws OculusException
  {
       
   return (IProblemStatementColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProblemStatementFolderColl",getIID(), false);  
  }

  
  protected String getCreateQuery()
  throws OculusException
  {
  return "INSERT INTO "+TABLE+" "+
       " ("+COL_OBJECTID+", "
         +COL_CLASSID+", "
         +COL_STATEID+", "
         +COL_DELETESTATE+", "
         +COL_GUID+", "
         +COL_NAME+", "
         +(_folderIID != null?COL_FOLDERID+", ":"")
         +COL_CREATIONDATE+", "
         +COL_CREATORID+", "
         +COL_ACCESSID+", "         
         +COL_MESSAGEATTACHED+", "
         +COL_FILEATTACHED+", "
         +COL_LINKATTACHED+         
       ") "+
       " VALUES "+
       " ("+getIID().getLongValue()+","
        +getDefnObject().getIID().getLongValue()+","
        +getStateObject().getIID().getLongValue()+","
        +getDeleteState().getIntValue()+","  
        +"'"+getGUID().toString()+"',"
        +"'"+SQLUtil.primer(getName())+"',"
        +(_folderIID != null? _folderIID.getLongValue()+",":"")
        +"'"+getCreationDate().toString()+"',"
        +getCreatorIID().getLongValue()+","
        +getAccessIID().getLongValue()+","        
        +(hasMessageAttached()?"1":"0")+","
        +(hasFileAttached()?"1":"0")+","
        +(hasLinkAttached()?"1":"0")+        
       ") ";
  }  
  protected String getDeleteQuery()
  throws ORIOException
  {  
      return " DELETE FROM FOLDER WHERE OBJECTID="+getIID();       
  }  
  
  
  protected String getLoadQuery()
  throws ORIOException
  {
  return "SELECT * "+
       "FROM "+TABLE+" "+
       "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  
  protected String getUpdateQuery()
  throws OculusException
  {
  
  if (_folderIID == null)    
  return " UPDATE "+TABLE+" "+
       " SET "+
       "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
       " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
       " , "+COL_FOLDERID+"= NULL "+
       " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();                              
   else
     return " UPDATE "+TABLE+" "+
       " SET "+
       "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
       " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+       
       " , "+COL_FOLDERID+"="+_folderIID+" "+  
       " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();                                  
  }  
  


 protected void load(IDataSet results)
  throws OculusException
  {
  if (results.getIID() == null)
    results.setIID(results.getLong(COL_OBJECTID));
  super.load(results);
  
  if (results.getLong(COL_FOLDERID) != 0)
    _folderIID = new SequentialIID(results.getLong(COL_FOLDERID));
  setName(results.getString(COL_NAME));
  }
  
  
  //------------------------ ACCESSORS -------------------------------
  public IFolder getParentFolder()
  throws OculusException
  {
    if (_folderIID == null)
    {
      return null;
    }
    return (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",_folderIID);
  }  

  /** Overrides IRObject.getParentObject( ). */
  public IRObject getParentObject() throws OculusException
  {
  	return getParentFolder();
  }
    
  public IRObject getParentObject(boolean edit) throws OculusException
  {
  	return getParentFolder();
  }
 
  //-------------------------- Protected Methods -----------------------------
  
  protected PSPKind needsPreparedStatement()
  {
  return PSPKind.NONE;
  }  
  
  public void put(Object key, Object value)
  throws OculusException
  {
  if (key instanceof String && value instanceof IRProperty)
  {
    IRProperty property = (IRProperty)value;
    super.put(key,value);
  }
  }  
  
  //------------------------ MUTATORS -------------------------------
  public IFolder setParentFolder(IFolder folder)
  throws OculusException
  {
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);    
   if (folder == null) 
     _folderIID = (IIID)null;
   else  
    _folderIID = folder.getIID();
  return this;
  }  
 
 public IFolder setParentFolder(IIID folderid)
    throws OculusException
  {    
    _folderIID = folderid;
    return this;
  }
  
 public String getPath() throws OculusException
  {
    StringBuffer sbf = new StringBuffer();
    sbf.append("/");
      IFolder currentfolder = _getCurrentInstance();        
      while(currentfolder != null)
      {
         sbf.append(currentfolder.getName());        
         sbf.append("/");        
         currentfolder = currentfolder.getParentFolder();   
      }
    return sbf.toString();
   
  }
    
   private IFolder _getCurrentInstance() 
     { return this;}

  public IFolder setTrashList(java.util.List sux)
    throws OculusException
  {
    
    trashlist = sux;
    return this;
  }

  
  public IRModelElementList getForms() throws OculusException
  {
    
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderForms",getIID());  
    
  }  
  
  
   
  public IBusinessObjectList getMarketInputList() throws OculusException
  {
    
    return (IBusinessObjectList)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputFolderList",getIID());  
    
  }  

  public boolean isRoot()
  { return (_folderIID == null); }
  
  public IMarketInputColl getSummaryInputs() throws OculusException
  {
    
    return (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SummaryInputFolderColl",_folderIID);   
    
  }
 
 public IFolderInputLinkColl getFolderInputLinks()
    throws OculusException
  {
    
     return (IFolderInputLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLinkFolderColl",_folderIID);   
    
  }
  
  public String getLocation() throws OculusException
  {    
      Stack path = new Stack();
      IFolder parent = getParentFolder();      
      while(parent != null)
      {
        path.push(parent.getName());
        parent = parent.getParentFolder();
      }
      StringBuffer sbf = new StringBuffer();      
      while(!path.empty())
      {
        sbf.append("/");
        sbf.append(path.pop().toString());
      }
      sbf.append("/");
      sbf.append(getName());
      sbf.append("/");
      path = null;
      return sbf.toString();
          
  }
  
  
  public IMarketInput getSummaryRecord() throws OculusException
  {
    
    IRModelElementList list = getForms();
    if (list.size() == 0) 
      return (IMarketInput)null;
    else
      return ((IREntryForm)list.getModelElement(0)).getSummaryRecord();    
   
  }
  
  
  public IREntryForm getForm() throws OculusException
  {
    
    IRModelElementList list = getForms();
    if (list.size() == 0) 
      return (IREntryForm)null;
    else
      return ((IREntryForm)list.getModelElement(0));
  }

  public void fixDispositions() throws OculusException
  {
    
   IObjectContext ctxt = getObjectContext(); 
   IRModelElementList list = (IRModelElementList)ctxt.getCRM().getCompObject(ctxt,"DispositionFolderList",getIID());
   if (list != null)
    {
      int size = list.size();
      for(int i =0; i < size; ++i)
      {        
        IRDisposition disp = (IRDisposition)ctxt.getCRM().getCompObject(ctxt,"Disposition",(IIID)list.get(i), true);
        disp.setFolder((IFolder)null);
      }
    }
  }


  

  
  public IFolder setInputRows(java.util.List list) throws OculusException
  {
   displaylist = list;
   return this; 
  }
  
  
  //Display methods  
  public List getInputRows() throws OculusException
  {
    if (displaylist == null)
    {
      IRConnection repConn=null; 
      IQueryProcessor qp =null;
      IDataSet rs = null;    
      IObjectContext _context = getObjectContext();
      displaylist = new ArrayList();   
      try
      {
      
      repConn = _context.getRepository().getDataConnection(_context);
      qp = repConn.createProcessor();    
      String query = "SELECT link.OBJECTID as linkid, inp.OBJECTID as iid,inp.VISIBLEID as vid,inp.BASECLASSID as baseid, inp.STATEID as stateid, inp.FILEATTACHED as fil,inp.LINKATTACHED as link,inp.DISCUSSATTACHED as disc,inp.SUBJECT as name ,inp.CREATIONDATE as dat "
                    + " FROM MARKETINPUT inp LEFT OUTER JOIN FOLDERINPUTLINK link ON link.MARKETINPUTID=inp.OBJECTID "
                    + " WHERE link.FOLDERID= "+getIID()+" AND link.DELETESTATE=1 AND inp.DELETESTATE=1 "; 
      rs = qp.retrieve(query);
      while(rs.next())
        {
         displaylist.add(new InputRow(rs.getLong("iid"), rs.getLong("vid"),  rs.getLong("baseid"),  rs.getLong("linkid"),  rs.getLong("stateid"),  rs.getString("name"),  rs.getBoolean("fil"),  rs.getBoolean("link"),  rs.getBoolean("disc"), rs.getTimestamp("dat")));           
        }
      
      }    
      finally {    
      qp.close();      
      }
    }
    return displaylist;    
  }        
  
  public void addInputRow(IMarketInput inp) throws OculusException
  {
   if (displaylist == null) displaylist = getInputRows();   
   InputRow row = getRowForInput(inp);
   if (row != null)displaylist.add(row);      
  }      
  
  public void removeInputRow(IMarketInput inp) throws OculusException
  {
   if (displaylist == null) displaylist = getInputRows();
   InputRow row = getRowForInput(inp);
   if (row != null)displaylist.remove(row);         
  }      
  
  public void updateInputRow(IMarketInput inp) throws OculusException
  {
   if (displaylist == null) displaylist = getInputRows();
   int index = findRowForInput(inp);
   InputRow row = getRowForInput(inp);
   displaylist.set(index,row);

  }
  
  protected InputRow getRowForInput(IMarketInput inp) throws OculusException
  {    
   InputRow row = null;
   try
    {     
    row = new InputRow();
    row.setIID(inp.getIID())
       .setVisibleID(inp.getVisibleID())
       .setStateID(inp.getStateObject().getIID().getLongValue())
       .setBaseClassID(inp.getBaseClass().getIID().getLongValue())
       .setName(inp.getSubject())
       .setLinkID(this.getInputLink(inp).getIID().getLongValue())
       .hasFileAttached(inp.hasFileAttached())
       .hasLinkAttached(inp.hasLinkAttached())
       .hasMessageAttached(inp.hasMessageAttached());    
    }
    catch(Exception ex) { throw new OculusException(ex);}   
    finally { return row;}
  }
  
  
  protected int findRowForInput(IMarketInput inp) throws OculusException
  {    
   int index = -1;
    InputRow row = new InputRow();
    row.setIID(inp.getIID());
    index = displaylist.indexOf(row);       
   return index;
  }
 
 public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
    IGrantSet grant = acm.checkReposPerms(new IPermission[] {PermEnum.FOLD_ADD,PermEnum.FOLD_VIEW});
    grant.addAll(acm.checkPerms(this, new IPermission[] {PermEnum.CONTENT_VIEW,PermEnum.OWNER,PermEnum.SUBFOLD_ADD}));
    return grant;
  }

 
 public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    
    IIID stateIID = getStateObject().getIID();
    if (!(module.equals(ModuleKind.COMPASS))) 
      return false;
   else   
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkReposPerms(new IPermission[] {PermEnum.FOLD_ADD,PermEnum.FOLD_VIEW});
        grant.addAll(acm.checkPerms(this, new IPermission[] {PermEnum.CONTENT_VIEW}));
      }
      
      if (
          grant.contains(PermEnum.FOLD_ADD) ||           
          grant.contains(PermEnum.FOLD_VIEW) ||             
          grant.contains(PermEnum.CONTENT_VIEW)           
        )
        visible = true;
    }
    return visible;
  }
 
    public String getFullTreePathString()
    throws OculusException
  {
    return getLocation();
  } 
    
}