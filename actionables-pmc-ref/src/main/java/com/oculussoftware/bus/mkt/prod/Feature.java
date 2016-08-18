package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.service.mail.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Feature.java
* Date:        
* Description: Provides the functionality for a basic feature.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
* ---             Saleem Shafi    3/13/00     Implemented getLatestFeatureRevisionObject()
*	BUG00597				Saleem Shafi		6/2/00			Fixed the load() method for edits.
* -------         Cuihua Zhang    6/15/2000   added hasStdFeatureLinks() 
* BUG01168        Cuihua Zhang    6/28/2000   added getAttachedDiscussionTopics(), 
*                                             and deleteDiscussionTopic() 
*/
public class Feature extends BusinessObject implements IFeature, IRPropertyMap
{
  static public long CLASSID = Long.parseLong("62397249743390016");
  protected String COL_DEPENDENCIES = "DEPENDENCIES";
  protected String COL_ISSTANDARD = "ISSTANDARD";
  protected String COL_LATESTREVISIONID = "LATESTREVISIONID";
  protected String COL_ENGRSPECATTACHED = "ENGRSPECATTACHED";
  protected String COL_FEATURETYPE = "FEATURETYPEID";
  protected String COL_VISIBLEID = "VISIBLEID";

  protected static long _nextid = -1;
  protected long _id;
  protected IIID _owningProdIID;
  protected IIID _revIID;
  protected IIID _latestRevIID;
  protected IIID _catLinkIID;  
  protected String linkType;
  protected IIID _stdLinkIID;  
  private List inputScores;
  
  // Trying to use object references for more speed.
  // Note:  These are not being maintained in the dolly(), so don't worry.
  private IFeatureCategoryLink _link = null;
  private IFeatureRevision _rev = null;
  
  protected IRProperty _dependencies,                // integer type
                       _isstandard,               // boolean type
                       _engrSpecAttached,         // boolean type
                       _feattype;  



  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Feature() throws OculusException
  {
    super();
    TABLE = "FEATURE";

    COL_OBJECTID 				= "FEAT_OBJECTID";
    COL_GUID 						= "FEAT_GUID";
    COL_CLASSID 				= "FEAT_CLASSID";
    COL_STATEID 				= "FEAT_STATEID";
    COL_NAME 						= "FEAT_NAME";
    COL_DESCRIPTION 		= "FEAT_DESCRIPTION";
    COL_CREATIONDATE 		= "FEAT_CREATIONDATE";
    COL_CREATORID 			= "FEAT_CREATORID";
    COL_ACCESSID 				= "FEAT_ACCESSID";
    COL_MESSAGEATTACHED = "FEAT_DISCUSSATTACHED";
    COL_FILEATTACHED 		= "FEAT_FILEATTACHED";
    COL_LINKATTACHED 		= "FEAT_LINKATTACHED";
    COL_DELETESTATE 		= "FEAT_DELETESTATE";

    _feattype = new BMProperty(this);
    _feattype.setDefnObject(IDCONST.FEAT_TYPE.getIIDValue());
    _isstandard = new BMProperty(this);
    _isstandard.setDefnObject(IDCONST.ISSTANDARD.getIIDValue());
    _dependencies = new BMProperty(this);
    _dependencies.setDefnObject(IDCONST.DEPENDENCIES.getIIDValue());
    _engrSpecAttached = new BMProperty(this);
    _engrSpecAttached.setDefnObject(IDCONST.SPEC_ATTACHED.getIIDValue());
  }

  //-------------------------- Protected Methods -----------------------------
  protected IFeatureRevision makeNewRevision()
    throws OculusException
  {
    IFeatureRevision newRev = (IFeatureRevision)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision", (IDataSet)null, true);
    newRev.setPersState(PersState.NEW);
    newRev.setFeatureObject(this);
    if (_revIID != null)
    {
      newRev.setName(getName());
      newRev.setDescription(getDescription());
      String label = getAutoRevisionLabel();
      if (label == null || label.equals(""))
        label = "-1";
      long number = Long.parseLong(getAutoRevisionLabel());
      newRev.setAutoRevisionLabel(number+1+"");
    }
    else
      newRev.setAutoRevisionLabel("0");
    newRev.setRevisionLabel(newRev.getAutoRevisionLabel());
    setFeatureRevisionObject(newRev);
    _latestRevIID = newRev.getIID();
    //update the inboxes
    IInboxRowList rows = (IInboxRowList)getObjectContext().getCRM().getCompObject(getObjectContext(),"InboxRowFeatureList",getIID(),true);
    while(rows.hasNext())
      rows.nextInboxRow().unacknowledge(AckKind.REVISION);
    rows.reset();
    //send the emails
    sendRevisionMail(rows);
    return newRev;    
  }
  
  //this may need to go in it's own thread
  private void sendRevisionMail(IInboxRowList rows) throws OculusException
  {
    String strRevInboxMsg = 
    "There has been a Revision made to Feature ("+getName()+") that is currently in your Inbox.";
    String strRevAssignedMsg = 
    "There has been a Revision made to Feature ("+getName()+") that you are currently assigned to.";
    String strRevVerMsg = 
    "There has been a Revision made to Feature ("+getName()+").";
    IIID fromuseriid = getObjectContext().getConnection().getUserIID();
    IUser fromuser = (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",fromuseriid);
    while(rows.hasNext())
    {
      IInboxRow row = rows.nextInboxRow();
      INotification notif = (INotification)getObjectContext().getCRM().getCompObject(getObjectContext(),"Notification",row.getIID());
      IUser user = notif.getRecipientObject();
      MailService.getInstance().sendMail(EmailKind.REVISIONINBOX,user,fromuser,strRevInboxMsg);
    
      IFeatureCategoryLink featcat = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",row.getParObjectIID());
      
      //
      IRoleAssignmentColl rac = featcat.getRoleAssignments();
      while(rac.hasNext())
        MailService.getInstance().sendMail(EmailKind.REVISIONASSIGNED,rac.nextRoleAssignment().getUserObject(),fromuser,strRevAssignedMsg);
      
      //
      ICategory cat = featcat.getCategoryObject();
      rac = cat.getVersionObject().getRoleAssignments();
      while(rac.hasNext())
      {
        IRoleAssignment ra = rac.nextRoleAssignment();
        if(IDCONST.VERSIONTEAMROLE.getIIDValue().equals(ra.getRoleIID()))
          MailService.getInstance().sendMail(EmailKind.REVISIONVERSION,ra.getUserObject(),fromuser,strRevVerMsg);
      }//end while
    }//end while
  }

  public IFeatureLinkChange getChangeLog()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getChangeLog();
  }

  public IFeatureLinkChangeColl getChangeLogs()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getChangeLogs();
  }

  public IFeature createCopy()
    throws OculusException
  {
    IFeature branch;
    IIID classIID = IDCONST.FEATURE.getIIDValue();
    
    branch = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",(IDataSet)null,true);
    branch.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    
    branch.setName(getName());
    branch.setDescription(getDescription());
    branch.setMessageAttached(hasMessageAttached());
    branch.setLinkAttached(hasLinkAttached());
    branch.setFileAttached(hasFileAttached());
    branch.setFeatureType(getFeatureTypeIID());
    if (getProperties() != null)
      branch.getProperties().putAll(getProperties());
    branch.setDependencies(hasDependencies());
    branch.setEngrSpecAttached(hasEngrSpecAttached());
    branch.isStandard(isStandard());
    
    IFeatureCategoryLink link = createLinkCopy();
    link.setFeatureObject(branch);
    branch.setFeatureCategoryLinkObject(link);
    link.setPinnedFeatureRevisionObject(null);
    
    link.copyAttachmentsOf(this);
    
    return branch;
  }
  
  public ISpecSignOffColl getSpecSignOffs()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getSpecSignOffs();
  }  
  
	public IIID getAlternativeSetIID() throws OculusException
	{
    return getFeatureCategoryLinkObject().getAlternativeSetIID();
	}
  
  public IAlternativeSet getAlternativeSetObject()
    throws OculusException
  { return getAlternativeSetObject(false); }
  
  public IAlternativeSet getAlternativeSetObject(boolean edit)
    throws OculusException
  { return getFeatureCategoryLinkObject().getAlternativeSetObject(edit); }
  
  
  public ISpecSignOffColl getSpecSignOffs(boolean editable)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getSpecSignOffs(editable);
  }  
  
  
  public IDiscussionTopicList getCons()
    throws OculusException
  { return getCons(false); }
    
  public IDiscussionTopicList getCons(boolean edit)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getCons(edit);
  }
    
  public boolean hasCons()
    throws OculusException
  { return (getCons() != null && getCons().size() > 0); }
    
  public IDiscussionTopicList getPros()
    throws OculusException
  { return getPros(false); }
    
  public IDiscussionTopicList getPros(boolean edit)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getPros(edit);
  }
    
  public boolean hasPros()
    throws OculusException
  { return (getPros() != null && getPros().size() > 0); }
  
  
  
  public IFeatureCategoryLink createLinkCopy()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().createLinkCopy();
  }  

  public ISpecSignOff createSpecSignOff(IUser user)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().createSpecSignOff(user);
  } 

  public IProcessChange createProcessChange()
    throws OculusException
  {
    return createProcessChange("Feature ("+getName()+") Created");
  }//

  public IProcessChange createProcessChange(String strComment)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().createProcessChange(strComment);
  }
  
	public IProcessChange createProcessChange(IUser recipient)
    throws OculusException
  {
	  return createProcessChange(recipient,"Feature ("+getName()+") Created");
  }
  
  public IProcessChange createProcessChange(IUser recipient, String comment)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().createProcessChange(recipient, comment);
  }
  
  public IFeature createBranch()
    throws OculusException
  {
    IFeature branch = createCopy();
    this.getFeatureRevisionObject().createSemanticLink(branch,LinkKind.BRANCH);
    return branch;
  }

  public String getFullTreePathString()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getFullTreePathString();
  }

  public IMarketInput associateInput(IMarketInput input)
    throws OculusException
  {
    ISemanticLink sl = createSemanticLink(input.getFolderInputLink(),LinkKind.INPUT);
    return input;
  }
    
  public IIID getPriorityIID()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getPriorityIID();
  }

  public IIID getTestIID()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getTestIID();
  }

  public IIID getDifficultyIID()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getDifficultyIID();
  }

  
  
  public IMarketInput disAssociateInput(IMarketInput input)
    throws OculusException
  {
    removeSemanticLink(input.getFolderInputLink(),LinkKind.INPUT);
    return input;
  }

  public IMarketInputColl getInputs()
    throws OculusException
  {
    return (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputFeatureColl",getIID());
  }

  public int numInputs()
    throws OculusException
  {
    int num = 0;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("NOPRELOAD","sothingsarefaster");
    IMarketInputColl inputs = (IMarketInputColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInputFeatureColl",args);
    num = inputs.size();
    
    IProblemStatementColl probs = getProblemStatements();
    while (probs.hasMoreProblemStatements())
    {
      IProblemStatement probState = probs.nextProblemStatement();
      num += probState.numInputs();
    }
    
    
    return num;
  }
    
  public int numProblemStatements()
    throws OculusException
  {
    int num = 0;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("NOPRELOAD","sothingsarefaster");
    IProblemStatementColl inputs = (IProblemStatementColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProblemStatementFeatureColl",args);
    num = inputs.size();
    return num;
  }
   
  List getInputScores()
    throws OculusException
  {
    List list = new Vector();

    IProblemStatementColl probs = getProblemStatements();
    while (probs.hasMoreProblemStatements())
    {
      IProblemStatement probState = probs.nextProblemStatement();
      list.addAll(((ProblemStatement)probState).getInputScores());
    }
    
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

  synchronized protected long getNextVisibleID()
    throws OculusException
  {
    if (_nextid == -1)
    {
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      IDataSet rs = conn.createProcessor().retrieve("SELECT MAX(VISIBLEID) AS MAXVISIBLEID FROM FEATURE");
      if (rs.next())
        _nextid = rs.getLong("MAXVISIBLEID")+1;
      if (_nextid < 1) _nextid = 1;
    }
    
    return _nextid++;
  }

  public long getVisibleID()
  {
    return _id;
  }

  public IFeature setVisibleID(long id)
  {
    _id = id;
    return this;
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
    String s = kkk + "f";     
    return  Float.parseFloat(s)/numScoredInputs();
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



  public IProblemStatementColl getProblemStatements()
    throws OculusException
  {
    return (IProblemStatementColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProblemStatementFeatureColl",getIID());
  }

  public IProblemStatement associateProblemStatement(IProblemStatement ps)
    throws OculusException
  {
    createSemanticLink(ps,LinkKind.PROBLEMSTATEMENT);
    return ps;
  }
    
  public IProblemStatement disAssociateProblemStatement(IProblemStatement ps)
    throws OculusException
  {
    removeSemanticLink(ps,LinkKind.PROBLEMSTATEMENT);
    return ps;
  }



  public ISemanticLink addWorkflowDependency(IFeature parent)
    throws OculusException
  {
    return addWorkflowDependency(parent.getFeatureCategoryLinkObject());
  }
    
  public ISemanticLink addFunctionalDependency(IFeature parent)
    throws OculusException
  {
    return addFunctionalDependency(parent.getFeatureCategoryLinkObject());
  }
    
  public void removeWorkflowDependency(IFeature parent)
    throws OculusException
  {
    removeWorkflowDependency(parent.getFeatureCategoryLinkObject());
  }
    
  public void removeFunctionalDependency(IFeature parent)
    throws OculusException
  {
    removeFunctionalDependency(parent.getFeatureCategoryLinkObject());
  }
    
  public ISemanticLink addWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().addWorkflowDependency(parent);
  }
    
  public ISemanticLink addFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().addFunctionalDependency(parent);
  }
    
  public void removeWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return;
    getFeatureCategoryLinkObject().removeWorkflowDependency(parent);
  }
    
  public void removeFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return;
    getFeatureCategoryLinkObject().removeFunctionalDependency(parent);
  }
    
  public IBusinessObject addToWorkforce(IIID useriid, int ordernum)
    throws OculusException
  { return getFeatureCategoryLinkObject().addToWorkforce(useriid,ordernum); }
  
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException
  { return getFeatureCategoryLinkObject().addToRole(useriid,roleiid,ordernum); }
  
  public IBusinessObject removeFromWorkforce(IIID useriid)
    throws OculusException
  { return getFeatureCategoryLinkObject().removeFromWorkforce(useriid); }
  
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException
  { return getFeatureCategoryLinkObject().removeFromRole(useriid,roleiid); }

  protected String getLoadQuery()
    throws OculusException
  {
    return " SELECT  feat.OBJECTID AS FEAT_OBJECTID, feat.GUID AS FEAT_GUID, feat.CLASSID AS FEAT_CLASSID, feat.STATEID AS FEAT_STATEID, "+
  								"feat.ACCESSID AS FEAT_ACCESSID, feat.CREATORID AS FEAT_CREATORID, feat.CREATIONDATE AS FEAT_CREATIONDATE, feat.DELETESTATE AS FEAT_DELETESTATE, "+
  								"feat.FILEATTACHED AS FEAT_FILEATTACHED, feat.LINKATTACHED AS FEAT_LINKATTACHED, feat.DISCUSSATTACHED AS FEAT_DISCUSSATTACHED,"+
  							"feat.DEPENDENCIES, feat.ISSTANDARD, feat.LATESTREVISIONID, feat.VISIBLEID, feat.FEATURETYPEID, feat.ENGRSPECATTACHED "+
					 "FROM "+TABLE+" feat "+
           "WHERE OBJECTID="+getIID().getLongValue();
  } 
  
  protected String getUpdateQuery()
    throws OculusException
  {
    String query = " UPDATE "+TABLE+" "+
           " SET "+
//           "   "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
           "   DELETESTATE= "+getDeleteState().getIntValue()+" "+
           " , ACCESSID= "+getAccessIID().getLongValue()+" "+
           " , DEPENDENCIES= "+(hasDependencies()?"1":"0")+" "+
           " , "+COL_ISSTANDARD+"= "+(isStandard()?"1":"0")+" "+
           " , "+COL_LATESTREVISIONID+"="+_latestRevIID.getLongValue()+" "+
           " , DISCUSSATTACHED= "+(hasMessageAttached()?"1":"0")+" "+
           " , FILEATTACHED= "+(hasFileAttached()?"1":"0")+" "+
           " , LINKATTACHED= "+(hasLinkAttached()?"1":"0")+" "+
           " , ENGRSPECATTACHED= "+(hasEngrSpecAttached()?"1":"0")+" "+
           " WHERE OBJECTID="+getIID().getLongValue();
    return query;
  }

  synchronized protected String getCreateQuery()
    throws OculusException
  {
    setVisibleID(getNextVisibleID());
    return "INSERT INTO "+TABLE+" "+
           " (OBJECTID, "
               +"CLASSID, "
//               +COL_STATEID+", "
               +"DELETESTATE, "
               +"GUID, "
               +"CREATIONDATE, "
               +"CREATORID, "
               +"ACCESSID, "
               +"DISCUSSATTACHED, "
               +"FILEATTACHED, "
               +"LINKATTACHED, "
               +"FEATURETYPEID, "
               +COL_LATESTREVISIONID+", "
               +COL_VISIBLEID+", "
//               +COL_OWNINGPRODUCTID+", "
               +COL_DEPENDENCIES+", "
               +COL_ISSTANDARD+", "
               +COL_ENGRSPECATTACHED
           +") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
//              +getStateObject().getIID().getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+","
              +getFeatureTypeIID().getLongValue()+", "
              +_latestRevIID.getLongValue()+","
              +getVisibleID()+","
//              +getOwningProductObject().getIID().getLongValue()+","
              +(hasDependencies()?"1":"0")+","
              +(isStandard()?"1":"0")+", "
              +(hasEngrSpecAttached()?"1":"0")
           +") ";
  } 

  protected String getDeleteQuery()
    throws OculusException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE OBJECTID="+getIID().getLongValue();
  } 

  protected void load(IDataSet results)
    throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));
    setPersState(PersState.PARTIAL);
    super.load(results);
        
    isStandard(results.getBoolean(COL_ISSTANDARD));
    setDependencies(results.getBoolean(COL_DEPENDENCIES));
    setEngrSpecAttached(results.getInt(COL_ENGRSPECATTACHED)==1);
    setFeatureType(getObjectContext().getRepository().makeReposID(results.getLong(COL_FEATURETYPE)));
    _latestRevIID = new SequentialIID(results.getLong(COL_LATESTREVISIONID));
    setVisibleID(results.getLong(COL_VISIBLEID));
    if (results.containsKey("REV_OBJECTID"))
      _revIID = new SequentialIID(results.getLong("REV_OBJECTID"));
    else
      _revIID = _latestRevIID;
    if (results.containsKey("LINK_OBJECTID"))
      _catLinkIID = new SequentialIID(results.getLong("LINK_OBJECTID"));
    else
      _catLinkIID = null;
  }

//----------------- IFeature Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  public IAttachment attachEngrSpecFile()
  throws OculusException
  {
    IFeatureCategoryLink link = getFeatureCategoryLinkObject();
    if (link != null)
      return link.attachEngrSpecFile();
    return null;
  }  

  public IHyperLink attachEngrSpecHyperLink()
  throws OculusException
  {
    IFeatureCategoryLink link = getFeatureCategoryLinkObject();
    if (link != null)
      return link.attachEngrSpecHyperLink();
    return null;
  }  

  //------------------------ MUTATORS -------------------------------
  public IFeature setOwningProductObject(IProduct product)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _owningProdIID = product.getIID();
    return this;
  }
  
  public IFeature setFeatureRevisionObject(IFeatureRevision revision)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _revIID = revision.getIID();
    _rev = revision;
    return this;
  }
    
  public IFeature setFeatureCategoryLinkObject(IFeatureCategoryLink catlink)
    throws OculusException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _catLinkIID = catlink.getIID();
    _link = catlink;
    if (catlink instanceof BaselineFeatureCategoryLink)
      linkType = "BaselineFeatureCategoryLink";
    else
      linkType = "FeatureCategoryLink";
    IFeatureRevision tempRev = catlink.getPinnedFeatureRevisionObject();
    
    if (tempRev == null)
      this.setFeatureRevisionObject(this.getLatestFeatureRevisionObject());
    else
      this.setFeatureRevisionObject(tempRev);
    return this;
  }

  public IFeature setStandardFeatureLinkObject(IStdFeatureLink catlink)
    throws OculusException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _stdLinkIID = catlink.getIID();
    return this;
  }
  
  public IFeature isStandard(boolean isstandard)
    throws ORIOException
  {
    _isstandard.setValue(new Boolean(isstandard));
    return this;
  }
  
  public IFeature setDependencies(boolean dependencies)
    throws ORIOException
  {
    _dependencies.setValue(new Boolean(dependencies));
    return this;
  }

  public IRElement setName(String name)
    throws OculusException
  {
    if (getName() != null && !getName().equals(name) && !getFeatureRevisionObject().getPersState().equals(PersState.NEW))
      makeNewRevision();
    getFeatureRevisionObject().setName(name);
    return this;
  }

  public IRElement setDescription(String description)
    throws OculusException
  {
    if (getDescription() != null && !getDescription().equals(description) && !getFeatureRevisionObject().getPersState().equals(PersState.NEW))
      makeNewRevision();
    getFeatureRevisionObject().setDescription(description);
    return this;
  }

  //------------------------ ACCESSORS -------------------------------
  
  public String getDisplayName()
    throws OculusException
  { return getName()+" [ID:"+getVisibleID()+"]"; }
  
  public String getName()
    throws OculusException
  {
    return getFeatureRevisionObject().getName();
  }

  public String getDescription()
    throws OculusException
  {
    return getFeatureRevisionObject().getDescription();
  }
  
  public boolean isStandard()
    throws OculusException
  {
    if (_isstandard.getValue() != null)
      return (((Boolean)_isstandard.getValue()).booleanValue());
    else
      return false;
  }

  public IProduct getOwningProductObject()
    throws OculusException
  {
    return (IProduct)getObjectContext().getCRM().getCompObject(getObjectContext(),"Product",_owningProdIID);
  }

  public boolean hasDependencies()
    throws OculusException
  {
    if (_dependencies.getValue() != null)
      return ((Boolean)_dependencies.getValue()).booleanValue();
    else
      return false;
  }
	
	public IFeature setEngrSpecAttached(boolean attach)
		throws ORIOException
	{
		_engrSpecAttached.setValue(new Boolean(attach));
		return this;
	}

  public boolean hasEngrSpecAttached()
    throws OculusException
  {
    if (_engrSpecAttached.getValue() != null)
      return ((Boolean)_engrSpecAttached.getValue()).booleanValue();
    else
      return false;
  }

  public IFileColl getAttachedEngrSpecFiles()
  throws OculusException
  {
  if (hasEngrSpecAttached())
    return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"EngrSpecFileColl",getIID());
  else
    return null;
  }  
  public IHyperLinkColl getAttachedEngrSpecHyperLinks()
  throws OculusException
  {
  if (hasEngrSpecAttached())
    return (IHyperLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"EngrSpecHyperLinkColl",getIID());
  else
    return null;
  }  

  public IFeatureRevision getFeatureRevisionObject()
    throws OculusException
  {
    if (_rev == null)
      _rev = (IFeatureRevision)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision",_revIID, isLocked());
    return _rev;
  }
      
  public IFeatureRevision getLatestFeatureRevisionObject()
    throws OculusException
  {
    return (IFeatureRevision)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision",_latestRevIID, isLocked());
  }
      
  public IFeatureCategoryLink getFeatureCategoryLinkObject()
    throws OculusException
  {
    return getFeatureCategoryLinkObject(false);
  }
  
  public IFeatureCategoryLink getFeatureCategoryLinkObject(boolean editable)
    throws OculusException
  {
    if (_catLinkIID == null)
      return null;
    if (_link == null || (editable && !_link.isLocked()))
      _link = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),linkType,_catLinkIID, editable);
    return _link;
  }

  public IStdFeatureLink getStandardFeatureLinkObject()
    throws OculusException
  {
    return getStandardFeatureLinkObject(false);    
  }
  

  public IStdFeatureLink getStandardFeatureLinkObject(boolean editable)
    throws OculusException
  {
    if (_stdLinkIID == null)
      return null;
    return (IStdFeatureLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLink",_stdLinkIID, editable);
  }

  
  
  
  
  public IFeatureColl getChildBranches()
    throws OculusException
  {
    ISemanticLinkColl links = getFeatureRevisionObject().getChildSemanticLinks(LinkKind.BRANCH);
    IFeatureColl feats = new com.oculussoftware.bus.mkt.prod.FeatureColl();
    feats.setObjectContext(getObjectContext());
    while (links.hasMoreSemanticLinks())
    {
      ISemanticLink link = links.nextSemanticLink();
      IFeature feat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",link.getDestObjectIID());
      feats.add(feat.getIID());
    }
    feats.reset();
    return feats;
  }

  public IFeature getParentBranch()
    throws OculusException
  {
    ISemanticLinkColl parents = getParentSemanticLinks(LinkKind.BRANCH);
    if (parents.size() == 0)
      return null;
    else
    {
      IFeatureRevision rev = (IFeatureRevision)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision",parents.nextSemanticLink().getSourceObjectIID());
      return rev.getFeatureObject();
    }
  }

  public IProductVersionColl getAssociatedVersions()
    throws OculusException
  {
    if (getFeatureRevisionObject() == null)
      return null;
    return getFeatureRevisionObject().getAssociatedVersions();
  }

  public IFeatureColl getWorkflowDependencies()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getWorkflowDependencies();
  }
    
  public IFeatureColl getFunctionalDependencies()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getFunctionalDependencies();
  }

  public IFeatureColl getWorkflowDependents()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getWorkflowDependents();
  }
    
  public IFeatureColl getFunctionalDependents()
    throws OculusException
  {
    if (getFeatureCategoryLinkObject() == null)
      return null;
    return getFeatureCategoryLinkObject().getFunctionalDependents();
  }

    
  public boolean isWorkflowDependent(IFeature child)
    throws OculusException
  {
    boolean found = false;
    IFeatureColl deps = child.getWorkflowDependencies();
    while (deps.hasMoreFeatures() && !found)
    {
      IFeature dep = deps.nextFeature();
      if (getIID().equals(dep.getIID()))
        found = true;
    }
    return found;
  }
    
  public boolean isFunctionalDependent(IFeature child)
    throws OculusException
  {
    boolean found = false;
    IFeatureColl deps = child.getFunctionalDependencies();
    while (deps.hasMoreFeatures() && !found)
    {
      IFeature dep = deps.nextFeature();
      if (getIID().equals(dep.getIID()))
        found = true;
    }
    return found;
  }
  
  public boolean hasAssociatedVersions()
    throws OculusException
  {
    IProductVersionColl prodvercoll = getAssociatedVersions();
    if (prodvercoll != null && prodvercoll.hasNext())
    {
      return true;
    }
    
    return false;
  }

  /** Returns the state object for the current state of this product. */
  public IRState  getStateObject()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getStateObject();
  }
  
  /** Sets the current state of this object. */
 // public IBusinessObject setStateObject(IRState state)
 //   throws OculusException
 // {
   // if (getFeatureCategoryLinkObject() != null)
   //   getFeatureCategoryLinkObject().setStateObject(state);
 //   return this;
 // }
  

  
//----------------- IFeatureCategoryLink Methods ------------------------------------

  //------------------------- MUTATORS ------------------------------

  public IFeatureCategoryLink setSingleRoles()
    throws OculusException
    { return getFeatureCategoryLinkObject().setSingleRoles(); }
        
  public IFeatureCategoryLink setPriorityLevel(IIID priorityIID)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setPriorityLevel(priorityIID);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink moveToAccolades(String strComment, IIID transIID)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().moveToAccolades(strComment,transIID);
  }
  
  public IFeatureCategoryLink moveToCompass(String strComment)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().moveToCompass(strComment);
  }

  public IFeatureCategoryLink moveToDeferred(String strComment)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().moveToDeferred(strComment);
  }
  
  public IBusinessObject interruptProcess(IRState s, String strComment)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().interruptProcess(s,strComment);
  }
  
  public IFeatureCategoryLink setTestLevel(IIID testIID)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setTestLevel(testIID);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setDifficultyLevel(IIID diffIID)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setDifficultyLevel(diffIID);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setOrderNum(int orderNum)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setOrderNum(orderNum);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setPercentComplete(int percentComplete)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setPercentComplete(percentComplete);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setEstimatedDevTime(int duration)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setEstimatedDevTime(duration);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setActualDevTime(int duration)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setActualDevTime(duration);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setEstimatedTestTime(int duration)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setEstimatedTestTime(duration);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setDevStartDate(Timestamp startDate)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setDevStartDate(startDate);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setDevEndDate(Timestamp endDate)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setDevEndDate(endDate);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setTestEndDate(Timestamp endDate)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setTestEndDate(endDate);
    return getFeatureCategoryLinkObject();
  }

  public IFeatureCategoryLink setCategoryObject(ICategory original)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setCategoryObject(original);
    return getFeatureCategoryLinkObject();
  }
  
  public IFeatureCategoryLink setPinnedFeatureRevisionObject(IFeatureRevision rev)
    throws OculusException
  {
    getFeatureCategoryLinkObject().setPinnedFeatureRevisionObject(rev);
    return getFeatureCategoryLinkObject();
  }

  //------------------------- ACCESSORS -----------------------------
    
  public String getPriorityLevel()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getPriorityLevel();
  }

  public String getTestLevel()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getTestLevel();
  }

  public String getDifficultyLevel()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getDifficultyLevel();
  }

  public int getOrderNum()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getOrderNum();
  }

  public int getPercentComplete()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getPercentComplete();
  }

  public int getEstimatedDevTime()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getEstimatedDevTime();
  }

  public int getActualDevTime()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getActualDevTime();
  }

  public int getEstimatedTestTime()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getEstimatedTestTime();
  }

  public Timestamp getDevStartDate()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getDevStartDate();
  }

  public Timestamp getDevEndDate()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getDevEndDate();
  }

  public Timestamp getTestEndDate()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getTestEndDate();
  }
  
  public ICategory getCategoryObject()
    throws OculusException
  { return getCategoryObject(false); }

  public ICategory getCategoryObject(boolean edit)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getCategoryObject(edit);
  }

  public IFeatureRevision getPinnedFeatureRevisionObject()
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getPinnedFeatureRevisionObject();
  }

  public IFeature getFeatureObject()
    throws OculusException
  {
    return getFeatureObject(false);
  }

  public IFeature getFeatureObject(boolean edit)
    throws OculusException
  {
    return getFeatureCategoryLinkObject().getFeatureObject(edit);
  }

  public IFeature setFeatureType(IIID typeID)
    throws OculusException
  {
    if (typeID != null)
    {
      Long type = new Long(typeID.getLongValue());
      Long oldType = (Long)_feattype.getValue();
      
      if (oldType != null && !oldType.equals(type) && !getFeatureRevisionObject().getPersState().equals(PersState.NEW))
        makeNewRevision();
      _feattype.setValue(type);
    }
    return this;
  }
  
  public IIID getFeatureTypeIID()
    throws OculusException
  {
    if (_feattype.getValue() == null || _feattype.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().makeReposID(((Long)_feattype.getValue()).longValue());
  }
  
  public String getFeatureTypeName()
    throws OculusException
  {
    if (_feattype.getValue() == null || _feattype.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().getBMRepository().getEnumliteral(getObjectContext().getRepository().makeReposID(((Long)_feattype.getValue()).longValue()),false).getName();
  }

//----------------- IFeatureRevision Methods ------------------------------------

  //------------------------- MUTATORS ------------------------------
  public IFeatureRevision setIsChangeable(boolean changeable)
    throws OculusException
  {
    getFeatureRevisionObject().setIsChangeable(changeable);
    return getFeatureRevisionObject();
  }

  public IFeatureRevision setComment(String comment)
    throws OculusException
  {
    getFeatureRevisionObject().setComment(comment);
    return getFeatureRevisionObject();
  }

  public IFeatureRevision setRevisionLabel(String revName)
    throws OculusException
  {
    getFeatureRevisionObject().setRevisionLabel(revName);
    return getFeatureRevisionObject();
  }

  public IFeatureRevision setAutoRevisionLabel(String autoRevLabel)
    throws OculusException
  {
    getFeatureRevisionObject().setAutoRevisionLabel(autoRevLabel);
    return getFeatureRevisionObject();
  }

  public IBusinessObject setFeatureObject(IFeature feature)
    throws OculusException
  {
    getFeatureRevisionObject().setFeatureObject(feature);
    return getFeatureRevisionObject();
  }

  //------------------------- ACCESSORS -----------------------------

  public boolean isChangeable()
    throws OculusException
  {
    return getFeatureRevisionObject().isChangeable();
  }

  public String getComment()
    throws OculusException
  {
    return getFeatureRevisionObject().getComment();
  }

  public String getRevisionLabel()
    throws OculusException
  {
    return getFeatureRevisionObject().getRevisionLabel();
  }

  public String getAutoRevisionLabel()
    throws OculusException
  {
    return getFeatureRevisionObject().getAutoRevisionLabel();
  }

//--------------------- IRObject Methods ---------------------------------
  /** Sets the specific class of this bo. */
  public IBusinessObject setDefnObject(IRClass newClass)
    throws OculusException
  {
    super.setDefnObject(newClass);
    if (_revIID == null)
      makeNewRevision();

    IFeatureCategoryLink link = getFeatureCategoryLinkObject(true);
    if (link != null)
    {
      IREntryForm form = getDefnObject().getEntryForm();
      link.setDefnObject(form.getFormSecondClass());
    }
    return this;
  }



//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_DEPENDENCIES))
        return _dependencies;
      else if (key.equals(LABEL_ENGRSPECATTACHED))
        return _engrSpecAttached;
      else if (key.equals(LABEL_ISSTANDARD))
        return _isstandard;
      else if (key.equals(LABEL_FEATURETYPE))
        return _feattype;
      else
      {
        Object revObj = getFeatureRevisionObject().getProperties().get(key);
//        if (tryAgain == null)
//        {
//          IFeatureCategoryLink link = getFeatureCategoryLinkObject();
//          if (link != null)
//            tryAgain = link.getProperties().get(key);
//        }
        if (revObj != null)
          return revObj;
        else
          return super.get(key);
      }
    }
    else
      return null;
  }
  
  public void put(Object key, Object value)
    throws OculusException
  {
    if (key instanceof String && value instanceof IRProperty)
    {
      IRProperty property = (IRProperty)value;
      if (key.equals(LABEL_DEPENDENCIES))
        setDependencies(((Boolean)property.getValue()).booleanValue());
      else if (key.equals(LABEL_ENGRSPECATTACHED))
        setEngrSpecAttached(((Boolean)property.getValue()).booleanValue());
      if (key.equals(LABEL_ISSTANDARD))
        isStandard(((Boolean)property.getValue()).booleanValue());
      else if (key.equals(LABEL_FEATURETYPE))
        setFeatureType(getObjectContext().getRepository().makeReposID(((Integer)property.getValue()).intValue()));
      else
        getFeatureRevisionObject().getProperties().put(key,value);
    }
  }


//----------------- IPoolable Methods ------------------------------------
//  public IPoolable construct(IObjectContext context, IDataSet args)
//    throws OculusException
//  {
//    IIID iid = null;
//    
//    if (context == null)
//      throw new OculusException("Context Argument expected.");
//    setObjectContext(context);
//
//    if (args == null)
//    {
//      iid = getObjectContext().getRepository().genReposID();
//      setPersState(PersState.NEW);
//    }
//    else
//    {
//      setPersState(PersState.UNINITED);
//      iid = args.getIID();
//    }
//    setIID(iid);
//    return this;
//  }

//----------------- IPersistable Methods ------------------------------------
  /**
  * Marks the bo as deleted.
  
  FIXME
  
  */
  public IPersistable delete()
    throws OculusException
  {
	  if (!hasFeatureCategoryLinks() && !hasBaselineFeatureCategoryLinks() && !hasStdFeatureLinks())
	  {
      IFeatureRevisionColl revList = (IFeatureRevisionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevisionList",getIID(),true);
      while(revList.hasNext())
		    revList.nextFeatureRevision().delete();

      super.delete();
	  }//end if
    return this;
  }
	
	public boolean hasBaselineFeatureCategoryLinks()
	  throws OculusException
	{
	  boolean rv = false;
		IQueryProcessor qp = null;
		try
		{
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT BCATFEATURELINK.OBJECTID FROM (BCATFEATURELINK LEFT OUTER JOIN FEATUREREVISION ON BCATFEATURELINK.REVISIONID = FEATUREREVISION.OBJECTID)"+
                                "WHERE FEATUREREVISION.FEATUREID = "+getIID().getLongValue());
																//" AND BCATFEATURELINK.DELETESTATE <> "+DeleteState.DELETED.getIntValue());
	    rv = rs.next();
		}//end try
		finally { if(qp!=null) qp.close(); }
		return rv;
  }

  public boolean hasFeatureCategoryLinks()
	  throws OculusException
	{
	  boolean rv = false;
		IQueryProcessor qp = null;
		try
		{
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT OBJECTID FROM CATFEATURELINK "+
                                "WHERE FEATUREID = "+getIID().getLongValue());
																//" AND CATFEATURELINK.DELETESTATE <> "+DeleteState.DELETED.getIntValue());
	    rv = rs.next();
		}//end try
		finally { if(qp!=null) qp.close(); }
		return rv;
  }
  
  public boolean hasStdFeatureLinks()
    throws OculusException
  {
    boolean rv = false;
    IQueryProcessor qp = null;
    try
    {
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT OBJECTID FROM STDFEATURELINK "+
                                "WHERE FEATUREID = "+getIID().getLongValue());
                                //" AND STDFEATURELINK.DELETESTATE <> "+DeleteState.DELETED.getIntValue());
      rv = rs.next();
    }//end try
    finally { if(qp!=null) qp.close(); }
    return rv;
  }
  

  
  
	
//----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current product object.  It does not copy the
  * IObjectContext.  
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws OculusException
  {
    Feature feat = null;
      feat = new Feature();
      feat.setIID(getIID());
      feat.setObjectContext(getObjectContext());
      feat.setPersState(getPersState());
      feat._classIID = _classIID;
      feat._stateIID = _stateIID;
      feat.setDeleteState(getDeleteState());
      feat._revIID = _revIID;
      feat._creatorIID = _creatorIID;
      feat._accessIID = _accessIID;
      feat._latestRevIID = _latestRevIID;
      feat.setCreationDate(getCreationDate());
      feat._messageAttached.setValue(_messageAttached.getValue());
      feat._linkAttached.setValue(_linkAttached.getValue());
      feat._fileAttached.setValue(_fileAttached.getValue());
      if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        feat.putAll(getProperties());

      feat.setVisibleID(getVisibleID());
      feat._owningProdIID = _owningProdIID;
      feat.setDependencies(hasDependencies());
      feat.setEngrSpecAttached(hasEngrSpecAttached());
      feat.setFeatureType(getFeatureTypeIID());
      feat.isStandard(isStandard());

      feat.setPersState(getPersState());

    return feat;
  }

  public void deleteEngrSpecFile(IAttachment file)
    throws OculusException
  {
		file.delete();

		IFileColl files = getAttachedEngrSpecFiles();
		IHyperLinkColl links = getAttachedEngrSpecHyperLinks();
		if ((files == null || !files.hasNext()) && (links == null || !links.hasNext()))
		  setEngrSpecAttached(false);
    else
    {
      String test = "";
      if (files == null) test += "files: null   ";
      else test += "files: "+files.size()+"   ";
      if (links == null) test += "links: null   ";
      else test += "links: "+links.size()+"   ";
    }
  }

  public void deleteEngrSpecHyperLink(IHyperLink link)
    throws OculusException
  {
		link.delete();

		IFileColl files = getAttachedEngrSpecFiles();
		IHyperLinkColl links = getAttachedEngrSpecHyperLinks();
		if ((files == null || !files.hasNext()) && (links == null || !links.hasNext()))
		  setEngrSpecAttached(false);
  }


  public IFileColl getAttachedFiles(IDataSet args,boolean editable)
    throws OculusException
  {
    if (hasFileAttached())
    {
      args.setIID(getIID());
      return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureFileList",args,editable);
    }
    else
      return null;
  }

  public IFileColl getViewableAttachedFiles(IDataSet args,boolean editable)
    throws OculusException
  {
    if (hasFileAttached())
    {
      args.setIID(getIID());
      return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureViewableFileList",args,editable);
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
      return (IHyperLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureHyperLinkList",args,editable);
    }
    else
      return null;
  }
  
  public IDiscussionTopicList getAttachedDiscussionTopics()
    throws OculusException
  {
    
      return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureDiscussionList",getIID(), false);
    
  }

  public IDiscussionTopicList getAttachedDiscussionTopics(boolean editable)
    throws OculusException
  {
    if (hasMessageAttached())
      return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureDiscussionList",getIID(), editable);
    else
      return null;
  }
  
  public void deleteDiscussionTopic(IDiscussionTopic topic)
    throws OculusException
  {
    topic.delete();
    
    IDiscussionTopicList topics = getAttachedDiscussionTopics();
    if (topics == null || !topics.hasNext())
      setMessageAttached(false);
  }



  public boolean isWorkflowDependent(IFeatureCategoryLink featcatlink)
    throws OculusException
  {
    IFeatureCategoryLink featcat = getFeatureCategoryLinkObject();
    if (featcat == null)
      return false;
    else
      return featcat.isWorkflowDependent(featcatlink);
  }
    
  public boolean isFunctionalDependent(IFeatureCategoryLink featcatlink)
    throws OculusException
  {
    IFeatureCategoryLink featcat = getFeatureCategoryLinkObject();
    if (featcat == null)
      return false;
    else
      return featcat.isFunctionalDependent(featcatlink);
  }
    
  public IFeatureCategoryLink getFeatureCategoryLinkObject(ICategory category)
    throws OculusException
  {
	  IFeatureCategoryLink link = null;
    IObjectContext context = getObjectContext();
		IQueryProcessor qp = null;
		try
		{
      IRConnection conn = context.getRepository().getDataConnection(context);
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve(
       " SELECT  link.OBJECTID AS LINK_OBJECTID, link.GUID AS LINK_GUID, link.CLASSID AS LINK_CLASSID, link.STATEID AS LINK_STATEID, "+
  								"link.ACCESSID AS LINK_ACCESSID, link.CREATORID AS LINK_CREATORID, link.CREATIONDATE AS LINK_CREATIONDATE, link.DELETESTATE AS LINK_DELETESTATE, "+
  								"link.FILEATTACHED AS LINK_FILEATTACHED, link.LINKATTACHED AS LINK_LINKATTACHED, link.DISCUSSATTACHED AS LINK_DISCUSSATTACHED, "+
  								"link.FEATUREID AS LINK_FEATUREID, "+
  							"link.PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
  							"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID "+
           "FROM CATFEATURELINK link  "+
           "WHERE link.FEATUREID = "+getIID()+" AND link.CATEGORYID="+category.getIID());
	    if (rs.next())
      {
        long id = rs.getLong("LINK_OBJECTID");
        rs.setIID(context.getRepository().makeReposID(id));
        link = (IFeatureCategoryLink)context.getCRM().getCompObject(context,"FeatureCategoryLink",rs);
      }
		}//end try
		finally { if(qp!=null) qp.close(); }
		return link;
  }
      

/********************************************


**************************/

  public void renderCustomEdit(IAttributeTable table)
    throws OculusException
  {
    IFeatureCategoryLink link = this.getFeatureCategoryLinkObject();
    if (link != null)
      link.renderCustomEdit(table);
    else
    {
	    IRModelElementList customs = this.getDefnObject().getViewableAttributeList();
	    _resyncProps(customs);
	    IRPropertyMap props = this.getProperties();
	    customs.reset();
	    if (customs.hasNext())
	    {
	      while (customs.hasNext())
	      {
	        IRAttribute custom = (IRAttribute)customs.next();
	        String key = "prop"+custom.getIID();
	        IRProperty prop = (IRProperty)props.get(key);
	        if (prop != null)
          {
            AttrGroupOper oper = custom.getUserPermission();
            if (oper != null && oper.equals(AttrGroupOper.EDIT))
              prop.renderEdit(table);
            else
            if (oper != null && oper.equals(AttrGroupOper.VIEW))
              prop.renderView(table);
          }
	        else
	          table.addAttribute(custom.getName()+":","(undefined)");    
	      }
	    }
    }

  }


  public void renderCustomView(IAttributeTable table)
    throws OculusException
  {
    IFeatureCategoryLink link = this.getFeatureCategoryLinkObject();
    if (link != null)
      link.renderCustomView(table);
    else
    {
      IRModelElementList customs = this.getDefnObject().getViewableAttributeList();
			_resyncProps(customs);
	  	IRPropertyMap props = this.getProperties();
	  	customs.reset();
	  	if (customs.hasNext())
	  	{
	  	  while (customs.hasNext())
	  	  {
	  	    IRAttribute custom = (IRAttribute)customs.next();
	  	    String key = "prop"+custom.getIID();
	  	    IRProperty prop = (IRProperty)props.get(key);
	  	    if (prop != null)
            prop.renderView(table);
	  	    else
	  	      table.addAttribute(custom.getName()+":","(undefined)");    
	  	  }
	  	}
    }
  }


  public void renderCustomEdit(IAttributeTable table, boolean newRev)
    throws OculusException
    {
      IIID thisID = getIID();
      IIID revID = getFeatureRevisionObject().getIID();
      
	    IRModelElementList customs = null;
      IFeatureCategoryLink link = this.getFeatureCategoryLinkObject();
//      if (link == null)
//        customs = this.getDefnObject().getEntryForm().getViewableAttributeList();
//      else
        customs = this.getDefnObject().getViewableAttributeList();
        
	    _resyncProps(customs);
	    IRPropertyMap props = this.getProperties();
	    customs.reset();
	    if (customs.hasNext())
	    {
	        while (customs.hasNext())
	        {
	        	IRAttribute custom = (IRAttribute)customs.next();
	          String key = "prop"+custom.getIID();
	          IRProperty prop = (IRProperty)props.get(key);
	          if (prop != null)
            {
              AttrGroupOper oper = custom.getUserPermission();
              if (((thisID.equals(prop.getOwnerObject().getIID()) || revID.equals(prop.getOwnerObject().getIID())) == newRev)
                && (oper != null && oper.equals(AttrGroupOper.EDIT)))
                prop.renderEdit(table);
              else
              if (oper != null && (oper.equals(AttrGroupOper.VIEW) || oper.equals(AttrGroupOper.EDIT)))
                prop.renderView(table);
            }
	          else
	            table.addAttribute(custom.getName()+":","(undefined)");    
	        }
	    }
    }
 


}