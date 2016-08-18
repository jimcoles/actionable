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

import java.sql.*;
import java.util.*;

/**
* Filename:    FolderInputLink.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/

public class FolderInputLink extends BusinessObject implements IFolderInputLink
{
  
//  static public long CLASSID = Long.parseLong("62540471990036181L");
  protected String COL_FOLDERID = "filFOLDERID";  
  protected String COL_INPUTID = "filMARKETINPUTID";  
  protected String COL_REACTID = "filREACTIONID";  
  protected IIID _folderIID;
  protected IIID _inpIID;
  public IIID _reactIID;
  
  

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public FolderInputLink() throws OculusException
  {
  super();
  TABLE = "FOLDERINPUTLINK";
  
    COL_OBJECTID         = "filOBJECTID";
    COL_GUID             = "filGUID";
    COL_CLASSID         = "filCLASSID";
    COL_STATEID         = "filSTATEID";
    COL_CREATIONDATE     = "filCREATIONDATE";
    COL_CREATORID       = "filCREATORID";
    COL_ACCESSID         = "filACCESSID";
    COL_MESSAGEATTACHED = "filDISCUSSATTACHED";
    COL_FILEATTACHED     = "filFILEATTACHED";
    COL_LINKATTACHED     = "filLINKATTACHED";
    COL_DELETESTATE     = "filDELETESTATE";

  }
  
  
  

  public Object dolly() throws OculusException
  {
    FolderInputLink cat = new FolderInputLink();
    cat.setIID(getIID());
    cat.setObjectContext(getObjectContext());
    cat.setPersState(getPersState());
    cat._classIID = _classIID;
    cat._stateIID = _stateIID;
    cat.setDeleteState(getDeleteState());
    cat._creatorIID = _creatorIID;
    cat._accessIID = _accessIID;
    cat.setCreationDate(getCreationDate());
    cat.setMessageAttached(hasMessageAttached());
    cat.setLinkAttached(hasLinkAttached());
    cat.setFileAttached(hasFileAttached());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
    cat.putAll(getProperties());
    cat._folderIID = _folderIID;    
    cat._inpIID = _inpIID;    
    cat._reactIID = _reactIID;    
    return cat;
  }
  
  public IFolderInputLink createCopy()
    throws OculusException
  {
    
    //First create the new folder
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.FOLDERINPUTLINK.getIIDValue();
    IFolderInputLink newFolder = (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",(IDataSet)null,true);
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
 
  
  public IFolderInputLink createNewFolderInputLink()
  throws OculusException
  {
  IFolderInputLink newCat;
  IIID classIID = IDCONST.FOLDERINPUTLINK.getIIDValue();
  newCat = (IFolderInputLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLink",(IDataSet)null,true);
  newCat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
  
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
  return this;
  }  


  public IBusinessObject softDelete()
    throws OculusException
  { 
    super.softDelete();
    if (_reactIID != null)
    {
      IReaction react = (IReaction)getObjectContext().getCRM().getCompObject(getObjectContext(),"Reaction",_reactIID, true);
      react.softDelete();
    }
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
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
 
  public IFolder getFolder()
    throws OculusException
  {
     return (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",_folderIID,false);  
   
  }


 public IMarketInput getInput()
    throws OculusException
  {
     IMarketInput mkinp =  (IMarketInput)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInput",_inpIID,false);  
     mkinp.setFolderInputLink(this);
     return mkinp; 
  }


 public IMarketInput getInput(boolean editable)
    throws OculusException
  {
     IMarketInput mkinp =  (IMarketInput)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInput",_inpIID,editable);  
     mkinp.setFolderInputLink(this);
     return mkinp; 
  }
  
  public IIID getInputIID()
    throws OculusException
  {
     return _inpIID; 
  }
  
  public String getName()
    throws OculusException
  {
    IMarketInput inp = getInput();
    if (inp == null)
      return null;
    return inp.getName();
  }

  public IReaction getReaction()
    throws OculusException
  {
    if (_reactIID == null)
      return null;
     return (IReaction)getObjectContext().getCRM().getCompObject(getObjectContext(),"Reaction",_reactIID,false);  
   
  }

  
  public IFolderColl getFolders(boolean b)
    throws OculusException
  {
       
   return (IFolderColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ChildFolderColl",getIID(), b);  
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
       " ("+"OBJECTID"+", "
         +"CLASSID"+", "
         +"STATEID"+", "
         +"DELETESTATE"+", "
         +"GUID"+", "
         +(_folderIID != null?"FOLDERID"+", ":"")
         +(_inpIID != null?"MARKETINPUTID"+", ":"")
         +(_reactIID != null?"REACTIONID"+", ":"")    
         +"CREATIONDATE"+", "
         +"CREATORID"+", "
         +"ACCESSID"+", "         
         +"DISCUSSATTACHED"+", "
         +"FILEATTACHED"+", "
         +"LINKATTACHED"+         
       ") "+
       " VALUES "+
       " ("+getIID().getLongValue()+","
        +getDefnObject().getIID().getLongValue()+","
        +getStateObject().getIID().getLongValue()+","
        +getDeleteState().getIntValue()+","  
        +"'"+getGUID().toString()+"',"
        +_folderIID.getLongValue()+", "
        +_inpIID.getLongValue()+", "
        +(_reactIID != null? _reactIID.getLongValue()+",":"")    
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
  return " DELETE FROM "+TABLE+" "+
       " WHERE OBJECTID="+getIID().getLongValue();
  }  
  
  
  protected String getLoadQuery()
  throws ORIOException
  {
  return "SELECT OBJECTID as filOBJECTID,GUID as filGUID, FOLDERID as filFOLDERID, MARKETINPUTID as filMARKETINPUTID, "
    +" CREATIONDATE as filCREATIONDATE, CLASSID as filCLASSID, REACTIONID as filREACTIONID,"
    +" STATEID as filSTATEID, ACCESSID as filACCESSID, CREATORID as filCREATORID,"           
    +" FILEATTACHED as filFILEATTACHED, LINKATTACHED as filLINKATTACHED, DISCUSSATTACHED as filDISCUSSATTACHED, "             
    +" DELETESTATE as filDELETESTATE "
    +"FROM "+TABLE+" "+
    " WHERE OBJECTID="+getIID().getLongValue();

  }  
  
  
  protected String getUpdateQuery()
  throws OculusException
  {
      
   if (_reactIID != null)
    {   
   return " UPDATE "+TABLE+" "+
       " SET "+
       "  "+"STATEID"+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+"DELETESTATE"+"= "+getDeleteState().getIntValue()+" "+
       " , "+"ACCESSID"+"= "+getAccessIID().getLongValue()+" "+
       " , "+"FOLDERID"+"="+_folderIID.getLongValue()+" "+
       " , "+"MARKETINPUTID"+"="+_inpIID.getLongValue()+" "+
       " , "+"REACTIONID"+"="+_reactIID.getLongValue()+" "+    
       " , "+"DISCUSSATTACHED"+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+"FILEATTACHED"+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+"LINKATTACHED"+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE OBJECTID="+getIID().getLongValue();
    }
  else
    {
      
   return " UPDATE "+TABLE+" "+       
       " SET "+
       "  "+"STATEID"+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+"DELETESTATE"+"= "+getDeleteState().getIntValue()+" "+
       " , "+"ACCESSID"+"= "+getAccessIID().getLongValue()+" "+
       " , "+"FOLDERID"+"="+_folderIID.getLongValue()+" "+
       " , "+"MARKETINPUTID"+"="+_inpIID.getLongValue()+" "+       
       " , "+"REACTIONID"+"=NULL"+" "+
       " , "+"DISCUSSATTACHED"+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+"FILEATTACHED"+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+"LINKATTACHED"+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE OBJECTID="+getIID().getLongValue();
    }  
    
  }  
  


 protected void load(IDataSet results)
  throws OculusException
  {
  
  
  if (results.getIID() == null)
    results.setIID(results.getLong("filOBJECTID"));
    
  setPersState(PersState.PARTIAL);
    IRepository repos = getObjectContext().getRepository();
    _guid = new GUID(results.getString("filGUID").trim());       // get GUID
    _iid = results.getIID();    // get IID
    _classIID = repos.makeReposID(results.getLong("filCLASSID")); // get class
    if (_classIID.getLongValue() != 0)
      _class.setValue(getDefnObject().getName());
      _stateIID = repos.makeReposID(results.getLong("filSTATEID")); // get state
   
    setDeleteState(DeleteState.getInstance(results.getInt("filDELETESTATE"))); // get state
    setCreatorIID(repos.makeReposID(results.getLong("filCREATORID")));
    
    setAccessIID(repos.makeReposID(results.getLong("filACCESSID")));
    setCreationDate(results.getTimestamp("filCREATIONDATE"));
    
    setMessageAttached(results.getBoolean("filDISCUSSATTACHED"));
    
    setFileAttached(results.getBoolean("filFILEATTACHED"));
    
    setLinkAttached(results.getBoolean("filLINKATTACHED")); 
    
    _deleteState = DeleteState.getInstance(results.getInt("filDELETESTATE"));
  
 // super.load(results);
  
  _folderIID = new SequentialIID(results.getLong(COL_FOLDERID));
  _inpIID = new SequentialIID(results.getLong(COL_INPUTID));
  if (results.getLong(COL_REACTID) != 0)
    _reactIID = new SequentialIID(results.getLong(COL_REACTID));
  
  }
  
  
  //------------------------ ACCESSORS -------------------------------
    
 
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
  
 public IFolderInputLink setFolder(IFolder folr) throws OculusException
   { 
     _folderIID = folr.getIID();
     if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
     return this;
     
   } 
 public IFolderInputLink setInput(IMarketInput folr) throws OculusException
   { 
     _inpIID = folr.getIID();
      if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
     return this;
     } 
 
 public  IFolderInputLink setReaction(IReaction folr) throws OculusException
   { 
     if (folr == null) 
       _reactIID = null;
     else
       _reactIID = folr.getIID();
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
   return this;
     } 
     
    public  IFolderInputLink move(IFolder source, IFolder dest) throws OculusException
   { 
    setFolder(dest);
    IIID id = _reactIID;
    setReaction(null);
    if (id != null)
     {
       IReaction react = (IReaction)getObjectContext().getCRM().getCompObject(getObjectContext(),"Reaction",id,true);
       react.delete();
     }
     
    if (getPersState().equals(PersState.UNMODIFIED)) setPersState(PersState.MODIFIED);
   return this;
     } 
  
   
   /******************
   Attachment related stuff
   
   
   *************/
   
    /** Returns the file object to be attached to the bo */
  public IAttachment attachFile()
    throws OculusException
  {
    IAttachment newAttachment = (IAttachment)getObjectContext().getCRM().getCompObject(getObjectContext(),"File",(IDataSet)null,true);
    newAttachment.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",IDCONST.FILE.getIIDValue()));
    newAttachment.setParentObject(this); 
    setFileAttached(true);   
    getInput(true).setFileAttached(true);
    return newAttachment;
  }

  /** Returns the hyperlink object to be attached to the bo */
  public IHyperLink attachLink()
    throws OculusException
  {
    IIID classIID = IDCONST.HYPERLINK.getIIDValue();
    IHyperLink newLink = (IHyperLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"HyperLink",(IDataSet)null,true);
    newLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    newLink.setParentObject(this);
    setLinkAttached(true);   
    getInput(true).setLinkAttached(true);
    return newLink;
  }
  
  /** Returns the hyperlink object to be attached to the bo */
  public IDiscussionTopic attachDiscussionTopic()
    throws OculusException
  {
    return attachDiscussionTopic(true);
  }
  
	
	private IDiscussionTopic attachDiscussionTopic(boolean setMessageAttached)
    throws OculusException
  {
    IIID classIID = IDCONST.DISCUSSIONTOPIC.getIIDValue();
    IDiscussionTopic newDT = (IDiscussionTopic)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopic",(IDataSet)null,true);
    newDT.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    newDT.setParObjectIID(getIID());    
    IDCONST idconst=IDCONST.FOLDERINPUTLINK;    
    newDT.setParObjectType(idconst);  
    setMessageAttached(true);
    getInput(true).setMessageAttached(true);    
    return newDT;
  }


 public void deleteFile(IAttachment file)
    throws OculusException
  {
		file.delete();

		IFileColl files = getAttachedFiles();
		if (files == null || !files.hasNext())
   	{
		  setFileAttached(false);
		  getInput(true).setFileAttached(false);
    }
  }
  public void deleteLink(IHyperLink link)
    throws OculusException
  {
		link.delete();

	  IHyperLinkColl links = getAttachedLinks();
	  if (links == null || !links.hasNext())
  	{
	    setLinkAttached(false);
	    getInput(true).setLinkAttached(false);
  	}
  }

  public void deleteDiscussionTopic(IDiscussionTopic topic)
    throws OculusException
  {
		topic.delete();
    
    IDiscussionTopicList topics = getAttachedDiscussionTopics();
    if (topics == null || !topics.hasNext())
  	{
      setMessageAttached(false);
      getInput(true).setMessageAttached(false);
  	}
  }
  

  public String getFullTreePathString()
    throws OculusException
  {
    return getFolder().getLocation();
  } 


       
}