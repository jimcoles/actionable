package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.ui.*;
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

import java.sql.*;
import java.util.*;

/**
* Filename:    Concept.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/


public class ProblemStatement extends BusinessObject implements IProblemStatement
{
  protected String COL_FOLDERID = "FOLDERID";
  public static final String COL_SUBJECT = "SUBJECT";
  protected IIID _folderIID;
  private List inputScores;
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public ProblemStatement() throws OculusException
  {
  super();
  TABLE = "PROBLEMSTATEMENT";
  COL_GUID = "GUID";
  
  }
  

  public Object dolly() throws OculusException
  {
     ProblemStatement cat = null;
      cat = new ProblemStatement();
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

  public IFeature associateFeature(IFeature feat)
    throws OculusException
  {
    feat.createSemanticLink(this,LinkKind.PROBLEMSTATEMENT);
    return feat;
  }

  public IFeature disAssociateFeature(IFeature feat)
    throws OculusException
  {
    feat.removeSemanticLink(this,LinkKind.PROBLEMSTATEMENT);
    return feat;
  }

  public IMarketInput associateInput(IMarketInput input)
    throws OculusException
  {
    this.createSemanticLink(input.getFolderInputLink(),LinkKind.INPUT);
    return input;
  }
  
  public IFolderInputLink associateInput(IFolderInputLink inputlink)
    throws OculusException
  {
    this.createSemanticLink(inputlink,LinkKind.INPUT);
    return inputlink;
  }

  public IMarketInput disAssociateInput(IMarketInput input)
    throws OculusException
  {
    this.removeSemanticLink(input.getFolderInputLink(),LinkKind.INPUT);
    return input;
  }


//----------------- IConcept Methods ------------------------------------
  /** I don't know what this does */
  
  
  protected String getCreateQuery()
  throws OculusException
  {
  return "INSERT INTO "+TABLE+" "+
       " ("+COL_OBJECTID+", "
         +COL_CLASSID+", "
         +COL_STATEID+", "
         +COL_DELETESTATE+", "
         +COL_GUID+", "
         +COL_SUBJECT+", "
         +COL_FOLDERID+", "  
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
        +getFolderObject().getIID().getLongValue()+","        
        +"'"+getCreationDate().toString()+"',"
        +getCreatorIID().getLongValue()+","
        +"0, "//              +getAccessIID().getLongValue()+","        
        +(hasMessageAttached()?"1":"0")+","
        +(hasFileAttached()?"1":"0")+","
        +(hasLinkAttached()?"1":"0")+        
       ") ";
  }  

  protected String getDeleteQuery()
  throws ORIOException
  {
  return " DELETE FROM "+TABLE+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  
  public IFeatureColl getFeatures()
  throws OculusException
  {
    return getFeatures(false);
  }  
  
  public IFeatureColl getFeatures(boolean editable)
  throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureProblemStatementColl",getIID(), editable);
  }  
  
  public IMarketInputColl getInputs()
  throws OculusException
  {
    return getInputs(false);
  }  
  
  public IMarketInputColl getInputs(boolean editable)
  throws OculusException
  {
    return (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputProblemStatementColl",getIID(), editable);
  }  
  
  
  
  
  protected String getLoadQuery()
  throws ORIOException
  {
  return "SELECT * "+
       "FROM "+TABLE+" "+
       "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  
  protected String getPSPUpdateQuery()
  //throws OculusException
  {
  return " UPDATE "+TABLE+
       " SET "+COL_DESCRIPTION+"=? "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue()+"";
  }    

protected Object getPSPValue() throws ORIOException
{
  String ret = null;
  try
  {
    ret = getDescription();
  }
  catch (OculusException ox)
  {
    throw new ORIOException(ox);
  }
  return ret;
}

  protected String getUpdateQuery()
  throws OculusException
  {
  return " UPDATE "+TABLE+" "+
       " SET "+
       "   "+COL_SUBJECT+"='"+SQLUtil.primer(getName())+"' "+
       " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
       " , "+COL_FOLDERID+"="+getFolderObject().getIID().getLongValue()+" "+
       " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  
  //------------------------ ACCESSORS -------------------------------
  public IFolder getFolderObject()
  throws OculusException
  {
  return (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",_folderIID);
  }  



  List getInputScores()
    throws OculusException
  {
    List list = new Vector();

    IMarketInputColl inputs = getInputs();
    while (inputs.hasMoreMarketInputs())
    {
      IMarketInput input = inputs.nextMarketInput();
      Integer integer = new Integer(input.getScore());
      if (integer.intValue() != 0)
        list.add(integer);
    }
    Collections.sort(list);
    return list;
  } 

  public int getMinScore()
    throws OculusException
  {
    if (inputScores == null)
      inputScores = getInputScores();
      
    int minSoFar = -1;
    
    for (Iterator i = inputScores.iterator(); i.hasNext(); )
    {
      Integer thisRank = (Integer)i.next();
      if (minSoFar == -1)
        minSoFar = thisRank.intValue();
      else
        minSoFar = Math.min(thisRank.intValue(),minSoFar);
    }
    
    if (minSoFar == -1)
      return 0;
    else
      return minSoFar;
  }
    
  public int getMaxScore()
    throws OculusException
  {
    if (inputScores == null)
      inputScores = getInputScores();
      
    int maxSoFar = -1;
    
    for (Iterator i = inputScores.iterator(); i.hasNext(); )
    {
      Integer thisRank = (Integer)i.next();
      if (maxSoFar == -1)
        maxSoFar = thisRank.intValue();
      else
        maxSoFar = Math.max(thisRank.intValue(),maxSoFar);
    }
    
    if (maxSoFar == -1)
      return 0;
    else
      return maxSoFar;
  }
    
  public float getMeanScore()
    throws OculusException
  {
    if (numScoredInputs() == 0)
      return 0;
    int kkk = getTotalScore();
    String s = kkk +"f";     
    return  Float.parseFloat(s)/ numScoredInputs();
  }

  public float getMedianScore()
    throws OculusException
  {
    int size = numScoredInputs();
    float median = 0;
    if (size % 2 == 1)
    {
      int middle = size / 2;
      median = ((Integer)inputScores.get(middle)).intValue();
    }
    else
    {
      int first = size / 2 - 1;
//       int first = size / 2;
      int second = first + 1;
      median = (((Integer)inputScores.get(first)).intValue() + ((Integer)inputScores.get(second)).intValue()) / 2;
    }
    
    return median;
  }
  
  public int getTotalScore()
    throws OculusException
  {
    int total = 0;
    if (inputScores == null)
      inputScores = getInputScores();

    for (Iterator i = inputScores.iterator(); i.hasNext(); )
    {
      Integer thisRank = (Integer)i.next();
      total += thisRank.intValue();
    }
    return total;
  }

  public int numScoredInputs()
    throws OculusException
  {
    if (inputScores == null)
      inputScores = getInputScores();
    return inputScores.size();      
  }

  public int numInputs()
    throws OculusException
  {
    int num = 0;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("NOPRELOAD","sothingsarefaster");
    IMarketInputColl inputs = (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputProblemStatementColl",args);
    num = inputs.size();
    return num;
  }



  protected void load(IDataSet results)
  throws OculusException
  {
  if (results.getIID() == null)
    results.setIID(results.getLong(COL_OBJECTID));
  super.load(results);
    
  if (COL_FOLDERID != null)
    _folderIID = new SequentialIID(results.getLong(COL_FOLDERID));
  setName(results.getString(COL_SUBJECT));
  setDescription(results.getString(COL_DESCRIPTION));
  }  
  //-------------------------- Protected Methods -----------------------------
  
  protected PSPKind needsPreparedStatement()
  {
  return PSPKind.STRING;
  }  
  
  //------------------------ MUTATORS -------------------------------
  public IProblemStatement setFolderObject(IFolder folder)
  throws OculusException
  {
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);
  _folderIID = folder.getIID();
  return this;
  }  
  
   public boolean hasFiles() throws OculusException
  {
    
    IFileColl filecoll  = getAttachedFiles();
    
    if (filecoll != null && filecoll.size() > 0) 
      return true;
    else
      return false;  
    
  }
   
   public boolean hasLinks() throws OculusException
  {
    IHyperLinkColl filecoll  = getAttachedLinks();
    if (filecoll != null && filecoll.size() > 0) 
      return true;
    else
      return false;  
    
  }
  
  public boolean hasTopics() throws OculusException
  {
    return hasMessageAttached();
    
  }
   
public IFolder getFolder()
    throws OculusException
  {
    
  return (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",_folderIID);   
    
  }
 
  public String getPriorityLevel() throws OculusException
  {
   
   String key = "prop"+IDCONST.PS_PRIORITY.getIIDValue();
   IRPropertyMap map = getProperties();
   IRProperty prop = (IRProperty)(map.get(key)); 
   Object o = prop.getValue();
   IREnumliteral enumlite = null;
   if (o != null && o instanceof java.lang.Long)
    {
     IIID id = StringUtil.getIID(o.toString());
     enumlite = (IREnumliteral)getObjectContext().getCRM().getCompObject(getObjectContext(),"Enumliteral",id);   
     return enumlite.getName();
    }
  return null;  
  }
   
   public IIID getPriorityIID() throws OculusException 
  {
    
    String key = "prop"+IDCONST.PS_PRIORITY.getIIDValue();
   IRPropertyMap map = getProperties();
   IRProperty prop = (IRProperty)(map.get(key)); 
   Object o = prop.getValue();
   IREnumliteral enumlite = null;
   if (o != null && o instanceof java.lang.Long)
    {
     IIID id = StringUtil.getIID(o.toString());
     enumlite = (IREnumliteral)getObjectContext().getCRM().getCompObject(getObjectContext(),"Enumliteral",id);   
     return enumlite.getIID();
    }
  return null;  
    
    
  }
  
  
    public String getFullTreePathString()
    throws OculusException
  {
    return getFolder().getLocation();
  } 

  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;

    if (module.equals(ModuleKind.COMPASS))
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());        
        grant = acm.checkPerms(getFolder(), new IPermission[] {PermEnum.FOLD_VIEW,PermEnum.CONTENT_VIEW});
      }
  
      if (
          grant.contains(PermEnum.FOLD_VIEW) ||             
          grant.contains(PermEnum.CONTENT_VIEW)           
         )
        visible = true;
    }
    return visible;
  }


}