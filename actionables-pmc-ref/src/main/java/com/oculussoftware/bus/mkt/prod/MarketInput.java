package com.oculussoftware.bus.mkt.prod;


import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.ui.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.sysi.license.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    MarketInput.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2

/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 ISSUE BUG00064    APota          5/15      2:12 pm    Found a nullpointer exception editing an input
 ISSUE BUG00306    APota          5/22      12:12 pm   Display visible id instead of system id on MI list 
 1029,1035         AP             6/12                 

*/


public class MarketInput extends BusinessObject implements IMarketInput
{
  
  protected String COL_SRCUSERID = "miSRCUSERID";  
  protected String COL_VISIBLEID = "miVISIBLEID";  
  protected String COL_BASECLASSID = "miBASECLASSID";  
  protected String COL_IMPORTANCE = "miIMPORTANCE";  
  protected String COL_COMMENT = "miUSERCOMMENT";  
  protected String COL_NAME = "miSUBJECT";  
  
  protected IIID _srcuserIID;  
  protected IRProperty _importance;
  protected IRProperty _comment;
  protected IRProperty _subject;
  //protected IFolderInputLink _inplink;                       
  protected IIID _inplinkIID;                       
  protected static long _nextid = -1;
  protected long _id;  
  protected long _baseclassid;  
  protected boolean isexternal = false;   
  protected boolean isanoncreator = false;

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public MarketInput() throws OculusException
  {
  super();
  TABLE = "MARKETINPUT";
  COL_OBJECTID         = "miOBJECTID";
    COL_GUID             = "miGUID";
    COL_CLASSID         = "miCLASSID";
    COL_STATEID         = "miSTATEID";
    COL_CREATIONDATE     = "miCREATIONDATE";
    COL_CREATORID       = "miCREATORID";
    COL_ACCESSID         = "miACCESSID";
    COL_MESSAGEATTACHED = "miDISCUSSATTACHED";
    COL_FILEATTACHED     = "miFILEATTACHED";
    COL_LINKATTACHED     = "miLINKATTACHED";
    COL_DELETESTATE     = "miDELETESTATE";

  _importance = new BMProperty(this);    
  _importance.setDefnObject(IDCONST.INPUT_IMPORTANCE.getIIDValue());
  _comment = new BMProperty(this);  
  _comment.setDefnObject(IDCONST.COMMENT.getIIDValue());
  _subject = new BMProperty(this);  
  _subject.setDefnObject(IDCONST.SUBJECT.getIIDValue());
  }
  
   synchronized protected long getNextVisibleID()
    throws OculusException
  {
  	IQueryProcessor qp =null;
  	try
  	{
    if (_nextid == -1)
    {
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT MAX(VISIBLEID) AS MAXVISIBLEID FROM MARKETINPUT");
      if (rs.next())
        _nextid = rs.getLong("MAXVISIBLEID")+1;
      if (_nextid < 1) _nextid = 1;
    }
  	}
  	finally {if (qp != null) qp.close();} 
   return _nextid++;
  }

  public long getVisibleID() throws ORIOException
  {
    return _id;
  }
  
  
  public IRClass getBaseClass() throws OculusException
  {
    return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",new SequentialIID(_baseclassid));
  }

  public IMarketInput setVisibleID(long id) throws ORIOException
  {
    _id = id;
    return this;
  }

  public IMarketInput setBaseClassLong(long id) throws ORIOException
  {
    _baseclassid = id;
    return this;
  }
  
   public boolean isClosed() throws OculusException
  {
    boolean ret = false;
   if (isArticleInput())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.ARTICLEINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
     
   if (isStandardInput())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.STANDARDINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
     
   if (isQuestionInput())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.QUESTIONINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
     if (isWinLoss())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.WINLOSSINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
     if (isSummaryInput())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.SUMMARYINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
     if (isImportedInput())
    {
       if (getStateObject().getIID().getLongValue() == IDCONST.IMPORTEDINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
    return ret;
  }
  
   public boolean isOpen() throws OculusException
  {
   
   if (isClosed()) 
     return false; 
   else
     return true;
       
  }
  
        
  
  
    
   public void setAsOpen() throws OculusException
  {
   IIID sid = null;
   IIID smid = null;
   if (isArticleInput())
    {
       
       sid = IDCONST.ARTICLEINPUTOPEN.getIIDValue();
       smid = IDCONST.ARTICLEINPUTSTATEMACHINE.getIIDValue();
    }
    
   if (isStandardInput())
    {
       
       sid = IDCONST.STANDARDINPUTOPEN.getIIDValue();
       smid = IDCONST.STANDARDINPUTSTATEMACHINE.getIIDValue();
    }
    
   if (isQuestionInput())
    {
       
       sid = IDCONST.QUESTIONINPUTOPEN.getIIDValue();
       smid = IDCONST.QUESTIONINPUTSTATEMACHINE.getIIDValue();
    }
    
     if (isImportedInput())
    {
       
       sid = IDCONST.IMPORTEDINPUTOPEN.getIIDValue();
       smid = IDCONST.IMPORTEDINPUTSTATEMACHINE.getIIDValue();
    }
    
       
       IRState st = getObjectContext().getRepository().getBMRepository().getStateObject(smid,sid,false);
       setStateObject(st); 
         
    }
     
     
      public void setAsClosed() throws OculusException
  {
   IIID sid = null;
   IIID smid = null;
   if (isArticleInput())
    {
       
       sid = IDCONST.ARTICLEINPUTCLOSED.getIIDValue();
       smid = IDCONST.ARTICLEINPUTSTATEMACHINE.getIIDValue();
    }
    
   if (isStandardInput())
    {
       
       sid = IDCONST.STANDARDINPUTCLOSED.getIIDValue();
       smid = IDCONST.STANDARDINPUTSTATEMACHINE.getIIDValue();
    }
    
   if (isQuestionInput())
    {
       
       sid = IDCONST.QUESTIONINPUTCLOSED.getIIDValue();
       smid = IDCONST.QUESTIONINPUTSTATEMACHINE.getIIDValue();
    }
    
   
    if (isImportedInput())
    {
       
       sid = IDCONST.IMPORTEDINPUTCLOSED.getIIDValue();
       smid = IDCONST.IMPORTEDINPUTSTATEMACHINE.getIIDValue();
    }
        
       IRState st = getObjectContext().getRepository().getBMRepository().getStateObject(smid,sid,false);
       setStateObject(st); 
         
    }
     
    

  public Object dolly() throws OculusException
  {
    MarketInput cat = new MarketInput();
    cat.setIID(getIID());
    cat.setObjectContext(getObjectContext());
    cat.setPersState(getPersState());
    cat._classIID = _classIID;
    cat._stateIID = _stateIID;
    cat._id = _id;
    cat.setDeleteState(getDeleteState());
    cat.setName(getName());
    //cat.setDescription(getDescription());
    cat._creatorIID = _creatorIID;
    cat._accessIID = _accessIID;
    cat.setCreationDate(getCreationDate());
    cat.setMessageAttached(hasMessageAttached());
    cat.setLinkAttached(hasLinkAttached());
    cat.setFileAttached(hasFileAttached());    
    
    if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
    cat.putAll(getProperties());

    cat._srcuserIID = _srcuserIID;
    cat._baseclassid = _baseclassid;
    cat.setImportance(getImportance());    
    cat.setSubject(getSubject());
    return cat;
  }
  
  public IMarketInput createCopy()
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.MARKETINPUT.getIIDValue();
    IMarketInput newMarketInput = (IMarketInput)context.getCRM().getCompObject(context,"MarketInput",(IDataSet)null,true);
    IRClass marketInputClass = (IRClass)context.getCRM().getCompObject(context,"Class",classIID);
    newMarketInput.setDefnObject(marketInputClass);    
    newMarketInput.setName(getName());
    newMarketInput.setDescription(getDescription());
    newMarketInput.setAccessIID(getAccessIID());
    newMarketInput.setMessageAttached(hasMessageAttached());
    newMarketInput.setLinkAttached(hasLinkAttached());
    newMarketInput.setFileAttached(hasFileAttached());
    newMarketInput.setSourceUserObject(getSourceUserObject());
    newMarketInput.setImportance(getImportance());
    //newMarketInput.setUserComment(getUserComment());
    newMarketInput.setSubject(getSubject());
    newMarketInput.setBaseClassLong(_baseclassid);    
    return newMarketInput;
  }
  
  
  public IFeature createNewFeature()
  throws OculusException
  {
  IFeature newFeat;
  IIID classIID = IDCONST.FEATURE.getIIDValue();

  newFeat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",(IDataSet)null,true);
  newFeat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
  associateFeature(newFeat);

  return newFeat;
  }  

  public IFeatureColl getFeatures()
  throws OculusException
  {
    return getFeatures(false);
  }  
  
  public IFeatureColl getFeatures(boolean editable)
  throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureMarketInputColl",getFolderInputLink().getIID(), editable);
  }  

  public IProblemStatementColl getProblemStatements()
    throws OculusException
  {
    return getProblemStatements(false);
  }


  public IProblemStatementColl getProblemStatements(boolean editable)
    throws OculusException
  {
    return (IProblemStatementColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProblemStatementMarketInputColl",getFolderInputLink().getIID(), editable);
  }

  public IFeature associateFeature(IFeature feat)
    throws OculusException
  {
    feat.createSemanticLink(getFolderInputLink(),LinkKind.INPUT);
    return feat;
  }

  public IFeature disAssociateFeature(IFeature feat)
    throws OculusException
  {
    feat.removeSemanticLink(getFolderInputLink(),LinkKind.INPUT);
    return feat;
  }

  public IProblemStatement associateProblemStatement(IProblemStatement ps)
    throws OculusException
  {
    ps.createSemanticLink(getFolderInputLink(),LinkKind.INPUT);
    return ps;
  }

  public IProblemStatement disAssociateProblemStatement(IProblemStatement ps)
    throws OculusException
  {
    ps.removeSemanticLink(getFolderInputLink(),LinkKind.INPUT);
    return ps;
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
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    return this;
  }

  
  public IIID getLinkIID() throws OculusException
  {
    return getFolderInputLink().getIID();
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
  
  
//----------------- IMarketInput Methods ------------------------------------
  
  
  
  synchronized protected String getCreateQuery()
  throws OculusException
  {
  
  
  setVisibleID(getNextVisibleID());
  long creator = getCreatorIID().getLongValue();
  if (isexternal)
  	{
  		if (_srcuserIID != null)
  		creator = _srcuserIID.getLongValue();
  	}
  	if (isanoncreator) creator = IDCONST.USER_ANON.getLongValue();
  	
  return "INSERT INTO "+TABLE+" "+
       " ("+"OBJECTID"+", "
         +"CLASSID"+", "
         +"STATEID"+", "
         +"DELETESTATE"+", "
         +"GUID"+", "
         +"SUBJECT"+", "         
         +"CREATIONDATE"+", "
         +"CREATORID"+", "
         +"VISIBLEID"+", "  
         +"BASECLASSID"+", "    
         +"ACCESSID"+", "           
         +(_srcuserIID!=null?"SRCUSERID"+",":"")    
         +(getImportance()!=0?"IMPORTANCE"+",":"")  
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
        +"'"+SQLUtil.primer(getSubject())+"',"                         
        +"'"+getCreationDate().toString()+"',"
        //+getCreatorIID().getLongValue()+","
        +creator+","
        +getVisibleID()+","  
        +_baseclassid+","    
        +getAccessIID().getLongValue()+","                  
        +(_srcuserIID != null ?_srcuserIID+", ":"")              
        +(getImportance() !=0 ?getImportance()+", ":"")            
        +(hasMessageAttached()?"1":"0")+","
        +(hasFileAttached()?"1":"0")+","
        +(hasLinkAttached()?"1":"0")+        
       ") ";
 

    }
 
  
  public IMarketInput setExternal()
    throws ORIOException
	{
		isexternal = true;
		return this;
	}
		
  public IMarketInput setAnonymousCreator()
    throws ORIOException
	{
	  isanoncreator = true;
	  return this;	
		
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
  return "SELECT OBJECTID as miOBJECTID,GUID as miGUID, VISIBLEID as miVISIBLEID, "
    +" CREATIONDATE as miCREATIONDATE,BASECLASSID as miBASECLASSID,CLASSID as miCLASSID, "
    +" STATEID as miSTATEID, ACCESSID as miACCESSID, CREATORID as miCREATORID, SRCUSERID as miSRCUSERID, "           
    +" IMPORTANCE as miIMPORTANCE, FILEATTACHED as miFILEATTACHED, LINKATTACHED as miLINKATTACHED, DISCUSSATTACHED as miDISCUSSATTACHED, "             
    +" SUBJECT as miSUBJECT, USERCOMMENT as miUSERCOMMENT,DELETESTATE as miDELETESTATE "
    +"FROM "+TABLE+" "+
    " WHERE OBJECTID="+getIID().getLongValue();
  }  
  
  
  protected String getUpdateQuery()
  throws OculusException
  {
    
  if (getSourceUserObject() != null)
    {  
  return " UPDATE "+TABLE+" "+
       " SET "+
       "   "+"SUBJECT"+"='"+SQLUtil.primer(getSubject())+"' "+
       " , "+"STATEID"+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+"DELETESTATE"+"="+getDeleteState().getIntValue()+" "+
       " , "+"ACCESSID"+"= "+getAccessIID().getLongValue()+" "+
       " , "+"IMPORTANCE"+"= "+getImportance()+" "+            
       " , "+"SRCUSERID"+"="+getSourceUserObject().getIID().getLongValue()+" "+
       " , "+"DISCUSSATTACHED"+"="+(hasMessageAttached()?"1":"0")+" "+
       " , "+"FILEATTACHED"+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+"LINKATTACHED"+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE OBJECTID="+getIID().getLongValue();
    }
  else
    {
  return " UPDATE "+TABLE+" "+
         " SET "+
       "   "+"SUBJECT"+"='"+SQLUtil.primer(getSubject())+"' "+
       " , "+"STATEID"+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+"DELETESTATE"+"="+getDeleteState().getIntValue()+" "+
       " , "+"ACCESSID"+"= "+getAccessIID().getLongValue()+" "+
       " , "+"IMPORTANCE"+"= "+getImportance()+" "+                   
       " , "+"DISCUSSATTACHED"+"="+(hasMessageAttached()?"1":"0")+" "+
       " , "+"FILEATTACHED"+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+"LINKATTACHED"+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE OBJECTID="+getIID().getLongValue();
    }  
    
  }  
  
  
  protected void load(IDataSet results)
  throws OculusException
  {
    
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));     
    setPersState(PersState.PARTIAL);
    IRepository repos = getObjectContext().getRepository();
    _guid = new GUID(results.getString("miGUID").trim());           
    _iid = results.getIID();    
    _classIID = repos.makeReposID(results.getLong("miCLASSID"));
    if (_classIID.getLongValue() != 0)
    _class.setValue(getDefnObject().getName());
    _stateIID = repos.makeReposID(results.getLong("miSTATEID"));         
    setDeleteState(DeleteState.getInstance(results.getInt("miDELETESTATE"))); // get state        
    setCreatorIID(repos.makeReposID(results.getLong("miCREATORID")));        
    setAccessIID(repos.makeReposID(results.getLong("miACCESSID")));        
    setCreationDate(results.getTimestamp("miCREATIONDATE"));
    setMessageAttached(results.getBoolean("miDISCUSSATTACHED"));
    setFileAttached(results.getBoolean("miFILEATTACHED"));
    setLinkAttached(results.getBoolean("miLINKATTACHED"));     
    _deleteState = DeleteState.getInstance(results.getInt("miDELETESTATE"));
     long srclong = results.getLong(COL_SRCUSERID);
     if (srclong == 0) 
       _srcuserIID = null;
     else
      _srcuserIID = new SequentialIID(srclong);    
    setImportance(results.getInt(COL_IMPORTANCE));
    setSubject(results.getString(COL_NAME));
    setVisibleID(results.getLong(COL_VISIBLEID));
    setBaseClassLong(results.getLong(COL_BASECLASSID));
    
  }
 
  
  //------------------------ ACCESSORS -------------------------------
  public IUser getSourceUserObject() throws OculusException
  {
  if (_srcuserIID == null) 
    return (IUser) null;
  else
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_srcuserIID);
  }  

  public int getImportance()
    throws OculusException
  {
    
    Object o = _importance.getValue();
    
    if (o != null)
    {
      String s = o.toString();
      return Integer.parseInt(s); 
    }      
    else
      return 0;
    
      
  }
  
  public String getUserComment()
    throws OculusException
  {
   
    if (_comment.getValue() != null)
      return ((String)_comment.getValue());
    else
      return null;
  
  }
  
  public String getSubject()
    throws OculusException
  {
   
    if (_subject.getValue() != null)
      return ((String)_subject.getValue());
    else
      return null;
  
  }
  
    
  public int getScore()
    throws ORIOException
  {
    int score = 0; 
    try
    {
    IReaction reaction = getReaction();
    if (getReaction() != null)
      score = getImportance() * getReaction().getWeight();
    }
    catch(Exception ex) { throw new ORIOException(ex);}
    return score;
  }

  public String getName()
    throws OculusException
  {
    //return ""+getIID().getLongValue();
    return ""+getSubject();
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
  public IMarketInput setSourceUserObject(IUser user)
  throws OculusException
  {
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);
   if (user != null) 
    _srcuserIID = user.getIID();
   else
    _srcuserIID = null;
  return this;
  }  
 
  public IMarketInput setImportance(int imp)
    throws ORIOException
  {
    _importance.setValue(new Integer(imp));
    
    return this;
  }
    
 
  public IMarketInput setUserComment(String imp)
    throws ORIOException
  {
    
    _comment.setValue(imp);
    return this;
  }
 
 public IMarketInput setSubject(String imp)
    throws ORIOException
  {
    
    _subject.setValue(imp);
    return this;
  }
       
  
 public IReactionColl getReactions() throws OculusException
  {
    
   return (IReactionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ReactionMarketInputColl",getIID()); 
    
  }
  
public  IReaction getReaction() throws OculusException
  {
    return getFolderInputLink().getReaction();
  }
  
 public boolean isStandardInput() 
   throws OculusException
  {
   
    if (_baseclassid == IDCONST.STANDARDINPUT.getLongValue()) 
        return true;
     else
       return false;   
   
  }
      
 public boolean isQuestionInput() 
   throws OculusException
  {
    
    if (_baseclassid == IDCONST.QUESTIONINPUT.getLongValue()) 
        return true;
     else
       return false;   
    
  }
   
     
  public boolean isWinLoss() 
   throws OculusException
  {
    
    if (_baseclassid == IDCONST.WINLOSSINPUT.getLongValue()) 
        return true;
     else
       return false;   
  
  }
   
  public boolean isArticleInput() 
   throws OculusException
  {
    if (_baseclassid == IDCONST.ARTICLEINPUT.getLongValue()) 
        return true;
     else
       return false;   
  }    
  
   public boolean isSummaryInput() 
   throws OculusException
  {
      if (_baseclassid == IDCONST.SUMMARYINPUT.getLongValue()) 
        return true;
     else
       return false;   
  }    
  
  public boolean isImportedInput() 
   throws OculusException
  {
   
   if (_baseclassid == IDCONST.IMPORTEDINPUT.getLongValue()) 
        return true;
     else
       return false;   
    
  }    
 
 public IMarketInput setFolderInputLink(IFolderInputLink link) throws ORIOException
  {
     _inplinkIID =link.getIID();
     return this;
  }   
  
  public IFolderInputLink getFolderInputLink() throws OculusException
  {
     
     IObjectContext context = getObjectContext();
     return (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",_inplinkIID);
  }   
  
 /* 
  public IFolderInputLink getFolderInputLink(IIID folderIID) throws OculusException
  {
     IIID linkIID = null;
     IObjectContext context = getObjectContext();
     IRConnection jdtC = null;
     IDataSet rs= null;
     IQueryProcessor qp = null;
     try
    {
      jdtC = context.getCRM().getDatabaseConnection(context);
      qp = jdtC.createProcessor();
      rs = qp.retrieve("SELECT OBJECTID FROM FOLDERINPUTLINK WHERE MARKETINPUTID="+getIID().getLongValue()+" AND FOLDERID="+folderIID.getLongValue()+" AND DELETESTATE<>"+DeleteState.DELETED.getIntValue());
      if (rs.next())
        linkIID = new SequentialIID(rs.getLong("OBJECTID"));      
    }
    catch(Exception ex) { throw new OculusException(ex);}
    finally { if (qp != null) qp.close();}     
    return (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",linkIID,false);
  }   
  */
  
   public boolean hasFiles() throws OculusException
  {
    
    IObjectContext ctxt = getObjectContext();
    IFileColl filecoll  = (IFileColl)ctxt.getCRM().getCompObject(ctxt,"FileList",getIID());    
    if (filecoll != null && filecoll.size() > 0) 
      return true;
    else
      return false;  
    
  }
  
   
   public boolean hasLinks() throws OculusException
  {
    IObjectContext ctxt = getObjectContext();
    IHyperLinkColl filecoll  = (IHyperLinkColl)ctxt.getCRM().getCompObject(ctxt,"HyperLinkList",getIID());            
    if (filecoll != null && filecoll.size() > 0) 
      return true;
    else
      return false;      
  }
   
   public boolean hasTopics() throws OculusException
  {
    
    IObjectContext ctxt = getObjectContext();
    IDiscussionTopicColl topiccoll  = (IDiscussionTopicColl)ctxt.getCRM().getCompObject(ctxt,"DiscussionTopicColl",getIID());                
    if (topiccoll != null && topiccoll.size() > 0) 
      return true;
    else
      return false;      
  }
   
  public String getEmailString()  throws OculusException
    {
      return null;
    } 
    
  public IFolderInputLink associateFolder(IFolder folder)
    throws OculusException
  {    
      IObjectContext context = getObjectContext();
      IFolderInputLink objLink = (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",(IDataSet)null,true);
      IRClass prodClass = (IRClass) context.getCRM().getCompObject(context, "Class", IDCONST.FOLDERINPUTLINK.getIIDValue());
      objLink.setDefnObject(prodClass);
      objLink.setFolder(folder);
      objLink.setInput(this);    
      return objLink;
  }  
 /*  
  public String getEmailString()  throws OculusException
    {
    
   StringBuffer sbf = new StringBuffer();
   sbf.append("You received a marketinput with the following details: \n");
   sbf.append("ID:" +getVisibleID()+"\n");
   sbf.append("Name:" +getSubject()+"\n");
   sbf.append("Creator:" +getCreatorObject().getFullName(false)+"\n");
   sbf.append("Creation date:" +getCreationDate().toString()+"\n");
   IRPropertyMap props = getProperties(); 
   Set set = props.keys();
   Iterator itr = set.iterator();
   while(itr.hasNext())
    {
      IRProperty prop = (IRProperty)itr.next();
      sbf.append(prop.getName()+":"+prop);
      
    }
   return sbf.toString();
  }
  */

public IFolderInputLink getFolderInputLink(IFolder fold)
    throws OculusException
  {
    
    IObjectContext context = getObjectContext();
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("FolderID",fold.getIID());
    args.put("InputID",getIID());  
    IFolderInputLinkColl folcoll = (IFolderInputLinkColl)context.getCRM().getCompObject(context,"InputLinkFolderColl",args);    
    return folcoll.nextFolderInputLink();
    
  }


public IFolderInputLinkColl getFolderInputLinks()
    throws OculusException
  {
    
    IObjectContext context = getObjectContext();
    IFolderInputLinkColl folcoll = (IFolderInputLinkColl)context.getCRM().getCompObject(context,"FolderInputLinkMarketInputColl",getIID(),false);    
    return folcoll;
    
  }

public IFolderInputLink getFolderInputLink(IIID foldid)
    throws OculusException
  {
    
    IObjectContext context = getObjectContext();
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("FolderID",foldid);
    args.put("InputID",getIID());  
    IFolderInputLinkColl folcoll = (IFolderInputLinkColl)context.getCRM().getCompObject(context,"InputLinkFolderColl",args);    
    return folcoll.nextFolderInputLink();
    
  }
  
public String getDescription() throws OculusException
  {
    
   IRProperty prop = (IRProperty)getProperties().get("prop"+IDCONST.INPUT_DESC.getIIDValue()); 
   if (prop != null)
    {
     Object o = prop.getValue();
     if (o != null) 
       return o.toString();
     else
       return null;
    }
   return  null;
  }
    
  //Setting the fromFolder to null will move all links to the toFolder  
  public void move(IFolder fromFolder, IFolder toFolder) throws OculusException    
  {
     if (fromFolder == null) allmove(toFolder);
     else
    {
       IObjectContext context = getObjectContext(); 
       IFolderInputLinkColl links = (IFolderInputLinkColl)context.getCRM().getCompObject(context,"FolderInputLinkMarketInputColl",getIID());
       IFolderInputLink inplink = null;        
       while(links.hasNext())
        {
           inplink = links.nextFolderInputLink();
           if(inplink != null)
            {
              inplink = (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",inplink.getIID());                    
              if (fromFolder != null)
              {
                if (inplink.getFolder().getIID().equals(fromFolder.getIID()))
                {
                  inplink = (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",inplink.getIID(), true);                                  
                  inplink.move(fromFolder,toFolder);
                  break;
                } 
              }            
            }
        }
    }
  }  
  
  
  protected void allmove(IFolder toFolder) throws OculusException    
  {
     
     IObjectContext context = getObjectContext(); 
     IFolderInputLinkColl links = (IFolderInputLinkColl)context.getCRM().getCompObject(context,"FolderInputLinkMarketInputColl",getIID());     
     while(links.hasNext())
      {
         IFolderInputLink inplink = links.nextFolderInputLink();
         if(inplink != null)
          {
            inplink = (IFolderInputLink)context.getCRM().getCompObject(context,"FolderInputLink",inplink.getIID(), true);                    
            inplink.move(null,toFolder);                          
          }
      }
  }  
    
      
 public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
//    if (!module.equals(ModuleKind.COMPASS)) return false;
//    if (isOpen()) 
    	return true;
//    else 
//    	return false;   
  }

  
  /**********************************
  Attachment related stuff
 
  
  **********************************/
 
 /*
 public IFileColl getAttachedFiles() throws OculusException
  {
 	if (hasFileAttached())
  	{	   
    IObjectContext ctxt = getObjectContext();
    return (IFileColl)ctxt.getCRM().getCompObject(ctxt,"InputFileList",getIID());        
  	}
  	else
  		return null;  	
  }
  */
 
  
  public IFileColl getAttachedFiles(IDataSet args,boolean editable)
    throws OculusException
  {
    if (hasFileAttached())
    {
      args.setIID(getIID());
      return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"InputFileList",args,editable);
    }
    else
      return null;
  }


 public IHyperLinkColl getAttachedLinks(IDataSet args,boolean editable)
    throws OculusException
  {
    if (hasLinkAttached())
    {
      args.setIID(getIID());
      return (IHyperLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"InputHyperLinkList",args,editable);
    }
    else
      return null;
  }
  
   public IDiscussionTopicList getAttachedDiscussionTopics(boolean editable)
    throws OculusException
  {
    if (hasMessageAttached())
      return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"InputDiscussionList",getIID(), editable);
    else
      return null;
  }


  public String getFullTreePathString()
    throws OculusException
  {
 	 String s = "";
 	 IFolderInputLinkColl folcoll = getFolderInputLinks();
 	 while(folcoll.hasMoreFolderInputLinks())
  	{
  	   IFolderInputLink filk = folcoll.nextFolderInputLink();
  	   s += filk.getFolder().getLocation()+";";
  	}		
    return s;
  } 

      
}