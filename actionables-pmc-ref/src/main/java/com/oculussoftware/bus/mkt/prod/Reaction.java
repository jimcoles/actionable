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
* Filename:    Reaction.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2


*/


public class Reaction extends BusinessObject implements IReaction
{
  protected String COL_INPUTID = "INPUTLINKID";
  protected String COL_WEIGHT = "WEIGHT";
  protected IIID _inputIID;
  protected IRProperty _weight= null;
  
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Reaction() throws OculusException
  {
  super();
  TABLE = "REACTION";
  COL_GUID = "GUID";
  _weight = new BMProperty(this);
  _weight.setDefnObject(IDCONST.WEIGHT.getIIDValue());
  }
  

  public Object dolly() throws OculusException
  {
    Reaction cat = null;
      cat = new Reaction();
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
      cat.setWeight(getWeight());
      cat._inputIID = _inputIID;
    return cat;
  }
  
  public IReaction createNewReaction()
  throws OculusException
  {
  IReaction react;
  IIID classIID = IDCONST.REACTION.getIIDValue();
  react = (IReaction)getObjectContext().getCRM().getCompObject(getObjectContext(),"Reaction",(IDataSet)null,true);
  react.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));  
  return react;
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
//         +COL_NAME+", "
         +COL_INPUTID+", "  
         +COL_CREATIONDATE+", "
         +COL_CREATORID+", "
         +COL_ACCESSID+", "         
         +COL_WEIGHT+", "           
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
//        +"'"+SQLUtil.primer(getName())+"',"
        +getInputLink().getIID().getLongValue()+","        
        +"'"+getCreationDate().toString()+"',"
        +getCreatorIID().getLongValue()+","
        +"0, "//              +getAccessIID().getLongValue()+","        
        +getWeight()+","  
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
  
  
  public IReactionColl getReactions()
  throws OculusException
  {
    return getReactions(false);
  }  
  
  public IReactionColl getReactions(boolean editable)
  throws OculusException
  {
    return (IReactionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ReactionReactionColl",getIID(), editable);
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
//       "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
       "  "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
       " , "+COL_INPUTID+"="+getInputLink().getIID().getLongValue()+" "+
       " , "+COL_WEIGHT+"="+getWeight()+" "+  
       " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  
  //------------------------ ACCESSORS -------------------------------
  public IFolderInputLink getInputLink()
  throws OculusException
  {
  return (IFolderInputLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLink",_inputIID);
  }  

    
  protected void load(IDataSet results)
  throws OculusException
  {
  if (results.getIID() == null)
    results.setIID(results.getLong(COL_OBJECTID));
  super.load(results);
    
  if (COL_INPUTID != null)
    _inputIID = new SequentialIID(results.getLong(COL_INPUTID));
    setWeight(results.getInt(COL_WEIGHT));
//  setName(results.getString(COL_NAME));
//  setDescription(results.getString(COL_DESCRIPTION));
  }  
  //-------------------------- Protected Methods -----------------------------
  
  protected PSPKind needsPreparedStatement()
  {
  return PSPKind.NONE;
  }  
  
  //------------------------ MUTATORS -------------------------------
  public IReaction setInputLink(IFolderInputLink input)
  throws OculusException
  {
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);
  _inputIID = input.getIID();
  return this;
  }  
 
  public IReaction setWeight(int imp)
    throws ORIOException
  {
    _weight.setValue(new Integer(imp));
    return this;
  } 
  
   public int getWeight()
    throws OculusException
  {
    Object o = _weight.getValue();
    
    if (o != null)
    {
      String s = o.toString();
      return Integer.parseInt(s); 
    }      
    else
      return 0;
    
  }
  
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
   
 public String getName() throws OculusException
  {    
     return "Reaction ID= "+getIID();
  } 
}