package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.ui.*;
import java.sql.*;
import java.util.*;

/**
* Filename:    FeatureCategoryLink.java
* Date:        3/10/00
* Description: Provides the functionality for a basic feature revision.
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
* ---             Saleem Shafi    3/13/00     Added getPinnedFeatureRevisionObject() method and added logic
*                                             to getFeatureObject() that sets the current FeatCatLink object
*                                             as the IFeature's FeatCatLink.
* BUG00076        Saleem Shafi    5/16/00     Added the concept of LINKCLASSID in the load() method to handle list results sets
*                                             with multiple CLASSID columns.
* --------  			Saleem Shafi    6/2/00			Undid and redid Egan's changes!!!
*	BUG00597				Saleem Shafi		6/2/00			Fixed the load() method for edits.
* BUG00821				Saleem Shafi		6/6/00			Changed attachEngrSpec file to set engspec bit.
* BUG00821				Saleem Shafi		6/6/00			Changed attachEngrSpec link to set engspec bit.
* BUG01051				Saleem Shafi		6/16/00			Added code to trip the dependencies bit.
*/

public class FeatureCategoryLink extends BusinessObject implements IFeatureCategoryLink, IRPropertyMap
{
  protected String COL_FEATUREID = "LINK_FEATUREID";
  protected String COL_PRIORITYID = "PRIORITYID";
  protected String COL_TESTLEVELID = "TESTLEVELID";
  protected String COL_DIFFLEVELID = "DIFFLEVELID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_PERCENTCOMPLETED = "PERCENTCOMPLETED";
  protected String COL_ESTDEVTIME = "ESTDEVTIME";
  protected String COL_ACTUALDEVTIME = "ACTUALDEVTIME";
  protected String COL_DEVSTARTDATE = "DEVSTARTDATE";
  protected String COL_DEVENDDATE = "DEVENDDATE";
  protected String COL_ESTTESTTIME = "ESTTESTTIME";
  protected String COL_TESTENDDATE = "TESTENDDATE";
  protected String COL_CATEGORYID = "CATEGORYID";
  protected String COL_PINNEDREVID = "PINNEDREVID";

  protected IFeatureLinkChange _change;
  
  private IFeature _feat = null;
  
  protected IIID 
                 _featIID,
                 _pinnedRevIID,
                 _originalCatIID;
  protected IRProperty _priority,
                       _test,
                       _difficulty,
                       _orderNum,
                       _percentCompleted,
                       _estDevTime,
                       _actDevTime,
                       _estTestTime,
                       _devStartDate,
                       _devEndDate,
                       _testEndDate,
                       _isChangeable,
                       _comment,
                       _revisionName,
                       _autoRevLabel; 

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public FeatureCategoryLink() throws OculusException
  {
    super();
    TABLE = "CATFEATURELINK";

    COL_OBJECTID 				= "LINK_OBJECTID";
    COL_GUID 						= "LINK_GUID";
    COL_CLASSID 				= "LINK_CLASSID";
    COL_STATEID 				= "LINK_STATEID";
    COL_NAME 						= "LINK_NAME";
    COL_DESCRIPTION 		= "LINK_DESCRIPTION";
    COL_CREATIONDATE 		= "LINK_CREATIONDATE";
    COL_CREATORID 			= "LINK_CREATORID";
    COL_ACCESSID 				= "LINK_ACCESSID";
    COL_MESSAGEATTACHED = "LINK_DISCUSSATTACHED";
    COL_FILEATTACHED 		= "LINK_FILEATTACHED";
    COL_LINKATTACHED 		= "LINK_LINKATTACHED";
    COL_DELETESTATE 		= "LINK_DELETESTATE";

    _orderNum = new BMProperty(this);
    _orderNum.setDefnObject(IDCONST.ORDERNUM.getIIDValue());
    _percentCompleted = new BMProperty(this);
    _percentCompleted.setDefnObject(IDCONST.PERCENT_COMPLETE.getIIDValue());
    _estDevTime = new BMProperty(this);
    _estDevTime.setDefnObject(IDCONST.EST_DEV_TIME.getIIDValue());
    _actDevTime = new BMProperty(this);
    _actDevTime.setDefnObject(IDCONST.ACTUAL_DEV_TIME.getIIDValue());
    _estTestTime = new BMProperty(this);
    _estTestTime.setDefnObject(IDCONST.EST_TEST_TIME.getIIDValue());
    _devStartDate = new BMProperty(this);
    _devStartDate.setDefnObject(IDCONST.DEV_START_DATE.getIIDValue());
    _devEndDate = new BMProperty(this);
    _devEndDate.setDefnObject(IDCONST.DEV_END_DATE.getIIDValue());
    _testEndDate = new BMProperty(this);
    _testEndDate.setDefnObject(IDCONST.TEST_END_DATE.getIIDValue());
    _isChangeable = new BMProperty(this);
    _isChangeable.setDefnObject(IDCONST.ISCHANGE.getIIDValue());
    _priority = new BMProperty(this);
    _priority.setDefnObject(IDCONST.FEAT_PRIORITY.getIIDValue());
    _priority.setRequired(true);
    _test = new BMProperty(this);
    _test.setDefnObject(IDCONST.FEAT_TEST.getIIDValue());
    _difficulty = new BMProperty(this);
    _difficulty.setDefnObject(IDCONST.FEAT_DIFFICULTY.getIIDValue());
  }

  public IFeatureLinkChange getChangeLog()
    throws OculusException
  {
    if (_change == null)
    {
      _change = (IFeatureLinkChange)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureLinkChange",(IDataSet)null,true);
      _change.setFeatureCategoryLinkObject(this);
    }
    return _change;
  }
    
  public IFeatureLinkChangeColl getChangeLogs()
    throws OculusException
  {
    return (IFeatureLinkChangeColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureLinkChangeList",getIID());
  }
    
  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
    throws OculusException
  {
    return " SELECT  link.OBJECTID AS LINK_OBJECTID, link.GUID AS LINK_GUID, link.CLASSID AS LINK_CLASSID, link.STATEID AS LINK_STATEID, "+
  								"link.ACCESSID AS LINK_ACCESSID, link.CREATORID AS LINK_CREATORID, link.CREATIONDATE AS LINK_CREATIONDATE, link.DELETESTATE AS LINK_DELETESTATE, "+
  								"link.FILEATTACHED AS LINK_FILEATTACHED, link.LINKATTACHED AS LINK_LINKATTACHED, link.DISCUSSATTACHED AS LINK_DISCUSSATTACHED, "+
  								"link.FEATUREID AS LINK_FEATUREID, "+
  							"link.PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
  							"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID "+
           "FROM "+TABLE+" link "+
           "WHERE OBJECTID="+getIID().getLongValue();
  } 
  
  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "  STATEID= "+getStateObject().getIID().getLongValue()+" "+
           " , DELETESTATE= "+getDeleteState().getIntValue()+" "+
           " , ACCESSID= "+getAccessIID().getLongValue()+" "+
           " , DISCUSSATTACHED= "+(hasMessageAttached()?"1":"0")+" "+
           " , FILEATTACHED= "+(hasFileAttached()?"1":"0")+" "+
           " , LINKATTACHED= "+(hasLinkAttached()?"1":"0")+" "+
           " , FEATUREID="+getFeatureObject().getIID().getLongValue()+" "+
           (getPriorityLevel() != null ? " , "+COL_PRIORITYID+"="+_priority.getValue()+" ":"")+
           (getTestLevel() != null ? " , "+COL_TESTLEVELID+"="+_test.getValue()+" ":"")+
           (getDifficultyLevel() != null ? " , "+COL_DIFFLEVELID+"="+_difficulty.getValue()+" ":"")+
           " , "+COL_ORDERNUM+"="+getOrderNum()+" "+
           " , "+COL_PERCENTCOMPLETED+"="+getPercentComplete()+" "+
           " , "+COL_ESTDEVTIME+"="+getEstimatedDevTime()+" "+
           " , "+COL_ACTUALDEVTIME+"="+getActualDevTime()+" "+
           " , "+COL_ESTTESTTIME+"="+getEstimatedTestTime()+" "+
           (getDevStartDate() != null ? " , "+COL_DEVSTARTDATE+"='"+getDevStartDate()+"' ":"")+
           (getDevEndDate() != null ? " , "+COL_DEVENDDATE+"='"+getDevEndDate()+"' ":"")+
           (getTestEndDate() != null ? " , "+COL_TESTENDDATE+"='"+getTestEndDate()+"' ":"")+
           (getPinnedFeatureRevisionObject()==null? "" : " , "+COL_PINNEDREVID+"="+getPinnedFeatureRevisionObject().getIID().getLongValue())+" "+
           " , "+COL_CATEGORYID+"="+getCategoryObject().getIID().getLongValue()+" "+
           " WHERE OBJECTID="+getIID().getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " (OBJECTID, "
               +"CLASSID, "
               +"STATEID, "
               +"DELETESTATE, "
               +"GUID, "
               +"CREATIONDATE, "
               +"CREATORID, "
               +"ACCESSID, "
               +"DISCUSSATTACHED, "
               +"FILEATTACHED, "
               +"LINKATTACHED, "
               +"FEATUREID, "
               +(getPinnedFeatureRevisionObject() == null?"":COL_PINNEDREVID+", ")
               +(getPriorityLevel() != null ? COL_PRIORITYID+", ":"")
               +(getTestLevel() != null ? COL_TESTLEVELID+", ":"")
               +(getDifficultyLevel() != null ? COL_DIFFLEVELID+", ":"")
               +COL_ORDERNUM+", "
               +COL_PERCENTCOMPLETED+", "
               +COL_ESTDEVTIME+", "
               +COL_ACTUALDEVTIME+", "
               +COL_ESTTESTTIME+", "
               +(getDevStartDate() != null ? COL_DEVSTARTDATE+", ":"")
               +(getDevEndDate() != null ? COL_DEVENDDATE+", ":"")
               +(getTestEndDate() != null ? COL_TESTENDDATE+", ":"")
               +COL_CATEGORYID+" "
           +") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +getStateObject().getIID().getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+","
              +getFeatureObject().getIID().getLongValue()+", "
              +(getPinnedFeatureRevisionObject() == null?"":getPinnedFeatureRevisionObject().getIID().getLongValue()+", ")
              +(getPriorityLevel() != null ? _priority.getValue()+", ":"")
              +(getTestLevel() != null ? _test.getValue()+", ":"")
              +(getDifficultyLevel() != null ? _difficulty.getValue()+", ":"")
              +getOrderNum()+", "
              +getPercentComplete()+", "
              +getEstimatedDevTime()+", "
              +getActualDevTime()+", "
              +(getTestEndDate() != null ? getTestEndDate()+", ":"")
              +(getDevStartDate() != null ? getDevStartDate()+", ":"")
              +(getDevEndDate() != null ? getDevEndDate()+", ":"")
              +getEstimatedTestTime()+", "
              +getCategoryObject().getIID().getLongValue()+" "
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
    
//    if (results.containsKey("LINK_OBJECTID"))
//      _classIID = getObjectContext().getRepository().makeReposID(results.getLong("LINK_CLASSID")); // get state
    if (COL_FEATUREID != null)
      _featIID = getObjectContext().getRepository().makeReposID(results.getLong(COL_FEATUREID));
    long pinnedRevID = results.getLong(COL_PINNEDREVID);
    if (pinnedRevID != 0)
      _pinnedRevIID = getObjectContext().getRepository().makeReposID(pinnedRevID);
    setPriorityLevel(getObjectContext().getRepository().makeReposID(results.getLong(COL_PRIORITYID)));
    setTestLevel(getObjectContext().getRepository().makeReposID(results.getLong(COL_TESTLEVELID)));
    setDifficultyLevel(getObjectContext().getRepository().makeReposID(results.getLong(COL_DIFFLEVELID)));
    setOrderNum(results.getInt(COL_ORDERNUM));
    setPercentComplete(results.getInt(COL_PERCENTCOMPLETED));
    setEstimatedDevTime(results.getInt(COL_ESTDEVTIME));
    setActualDevTime(results.getInt(COL_ACTUALDEVTIME));
    setDevStartDate(results.getTimestamp(COL_DEVSTARTDATE));
    setDevEndDate(results.getTimestamp(COL_DEVENDDATE));
    setEstimatedTestTime(results.getInt(COL_ESTTESTTIME));
    setTestEndDate(results.getTimestamp(COL_TESTENDDATE));
    _originalCatIID = getObjectContext().getRepository().makeReposID(results.getLong(COL_CATEGORYID));
  }

  public IBusinessObject copyAttachmentsOf(IBusinessObject source)
    throws OculusException
  {
    super.copyAttachmentsOf(source);
    
    if (source instanceof IFeature)
    {
      IHyperLinkColl links = ((IFeature)source).getAttachedEngrSpecHyperLinks();
      while (links != null && links.hasMoreHyperLinks())
      {
        IHyperLink newLink = links.nextHyperLink().createCopy();
        newLink.setParentObject(this);
      }
      IFileColl files = ((IFeature)source).getAttachedEngrSpecFiles();
      while (files != null && files.hasMoreFiles())
      {
        IAttachment newFile = files.nextFile().createCopy();
        newFile.setParentObject(this);
      }
    }

    return this;
  }



  public IFeatureCategoryLink createLinkCopy()
    throws OculusException
  {
    FeatureCategoryLink branch;
    
    branch = (FeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",(IDataSet)null,true);
    branch._classIID = _classIID;
    branch._stateIID = _stateIID;
    
    branch._messageAttached.setValue(_messageAttached.getValue());
    branch._linkAttached.setValue(_linkAttached.getValue());
    branch._fileAttached.setValue(_fileAttached.getValue());
    if (getProperties() != null)
      branch.getProperties().putAll(getProperties());
    
    branch._featIID = _featIID;
    branch._pinnedRevIID = _pinnedRevIID;
    branch._priority = _priority;
    branch._test = _test;
    branch._difficulty = _difficulty;
    branch.setOrderNum(getOrderNum());
    branch.setPercentComplete(getPercentComplete());
    branch.setEstimatedDevTime(getEstimatedDevTime());
    branch.setActualDevTime(getActualDevTime());
    branch.setEstimatedTestTime(getEstimatedTestTime());
    branch.setDevStartDate(getDevStartDate());
    branch.setDevEndDate(getDevEndDate());
    branch.setTestEndDate(getTestEndDate());
    branch._originalCatIID = _originalCatIID;
    
    return branch;
  }

  public IAttachment attachEngrSpecFile()
  throws OculusException
  {
	  IAttachment file = (IAttachment)getObjectContext().getCRM().getCompObject(getObjectContext(),"File",(IDataSet)null,true);
	  file.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",IDCONST.FILE.getIIDValue()));
	  file.setParentObject(this);
	  getFeatureObject(true).setEngrSpecAttached(true);
    file.setAttachmentType(AttachmentType.ENGRSPEC);
    return file;
  }  

  public IHyperLink attachEngrSpecHyperLink()
  throws OculusException
  {
    IIID classIID = IDCONST.HYPERLINK.getIIDValue();
    IHyperLink newLink = (IHyperLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"HyperLink",(IDataSet)null,true);
    newLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    newLink.setParentObject(this);
    getFeatureObject(true).setEngrSpecAttached(true);
    newLink.setLinkType(HyperLinkType.ENGRSPEC);
    return newLink;
  }  

  public void deleteEngrSpecFile(IAttachment file)
    throws OculusException
  {
	  getFeatureObject(true).deleteEngrSpecFile(file);
  }

  public void deleteEngrSpecHyperLink(IHyperLink link)
    throws OculusException
  {
    getFeatureObject(true).deleteEngrSpecHyperLink(link);
  }

  
  public ISemanticLink addWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
		getFeatureObject(true).setDependencies(true);
    return parent.createSemanticLink(this,LinkKind.WORKFLOW_DEP);
  }
    
  public ISemanticLink addFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    getFeatureObject(true).setDependencies(true);
    return parent.createSemanticLink(this,LinkKind.FUNCTIONAL_DEP);
  }
    
  public void removeWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    parent.removeSemanticLink(this,LinkKind.WORKFLOW_DEP);
		
		IFeatureColl wDeps = getWorkflowDependencies();
		if (wDeps == null || !wDeps.hasNext())
		{
			IFeatureColl fDeps = getFunctionalDependencies();
			if (fDeps == null || !fDeps.hasNext())
				getFeatureObject(true).setDependencies(false);
		}
		
  }
    
  public void removeFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException
  {
    parent.removeSemanticLink(this,LinkKind.FUNCTIONAL_DEP);

	  IFeatureColl fDeps = getFunctionalDependencies();
	  if (fDeps == null || !fDeps.hasNext())
	  {
	  	IFeatureColl wDeps = getWorkflowDependencies();
	  	if (wDeps == null || !wDeps.hasNext())
	  		getFeatureObject(true).setDependencies(false);
	  }
  }

  public IFeatureColl getWorkflowDependencies()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureWorkflowDependencyColl",getIID());
  }
    
  public IFeatureColl getFunctionalDependencies()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureFunctionalDependencyColl",getIID());
  }
    
  public IFeatureColl getWorkflowDependents()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureWorkflowDependentsColl",getIID());
  }
    
  public IFeatureColl getFunctionalDependents()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureFunctionalDependentsColl",getIID());
  }
    

  private IFeatureColl _getAllWorkflowDependents()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureAllWorkflowDependentsColl",getIID());
  }
    
  private IFeatureColl _getAllFunctionalDependents()
    throws OculusException
  {
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureAllFunctionalDependentsColl",getIID());
  }
    
  public ISpecSignOff createSpecSignOff(IUser user)
    throws OculusException
  {
    ISpecSignOff sso = (ISpecSignOff)getObjectContext().getCRM().getCompObject(getObjectContext(),"SpecSignOff",(IDataSet)null,true);
    sso.setFeatureCategoryLinkObject(this);
    sso.setUserObject(user);
    return sso;
  }

  public IProcessChange createProcessChange()
    throws OculusException
  {
    return createProcessChange("Feature ("+getFeatureObject().getName()+") Created");
  }//

  public IProcessChange createProcessChange(String strComment)
    throws OculusException
  {
	  return createProcessChange(getObjectContext().getConnection().getUserObject(),strComment);
  }
	
  public IProcessChange createProcessChange(IUser recipient)
    throws OculusException
  {
	  return createProcessChange(recipient,"Feature ("+getFeatureObject().getName()+") Created");
  }
  
  public IProcessChange createProcessChange(IUser recipient, String strComment)
    throws OculusException
  {
		IIID stateiid = getStateObject().getIID();
    if (!stateiid.equals(IDCONST.COMPASS.getIIDValue()) 
        && !stateiid.equals(IDCONST.MYCONCEPTS.getIIDValue()) 
      	&& !stateiid.equals(IDCONST.DEFERRED.getIIDValue())
      	&& !stateiid.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())
      	&& !stateiid.equals(IDCONST.MMVERIFIED.getIIDValue()))
	  {
      IRepository rep = getObjectContext().getRepository();
      //create a notification for the inbox when there are no roles defined
      INotification nt = createNotification();
      nt.setAckMask(0);
      nt.setRecipientIID(recipient.getIID());
      nt.setBody(strComment);
      nt.setSubject(strComment);
      nt.setNotificationKind(NotificationKind.WORKFLOW);
    }//end if
    IProcessChange pc = addProcessChange(null,strComment);
    pc.addReceiver(recipient);
    return pc;
  }

  public IFeatureCategoryLink moveToAccolades(String strComment, IIID transIID)
    throws OculusException
  {
    IRState state = getStateObject();
    if (state.getIID().equals(IDCONST.COMPASS.getIIDValue()))
    {
      if(transIID != null)
      {
        IRStateMachine statemachine = getStateMachine();
        IRTransition t = getObjectContext().getRepository().getBMRepository().getTransitionObject(statemachine.getIID(), getStateObject().getIID(), transIID, false);
        statemachine.doTransition(this,t,strComment);
      }//end if
    }//end if
    getCategoryObject(true).moveToAccolades(strComment,false,transIID);
    
    return this;
  }

  public IFeatureCategoryLink moveToCompass(String strComment)
    throws OculusException
  {
    IRState state = getStateObject();
    if (!state.getIID().equals(IDCONST.COMPASS.getIIDValue()))
    {
      interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.COMPASS.getIIDValue()),strComment);
      ICategory cat = getCategoryObject(true);
      if(!cat.getStateObject().getIID().equals(IDCONST.CATEGORYCOMPASS.getIIDValue()))
        cat.moveToCompass(strComment,false);
    }
  
    return this; //(IFeatureCategoryLink)interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.COMPASS.getIIDValue()),strComment);
  }

  public IFeatureCategoryLink moveToDeferred(String strComment)
    throws OculusException
  {
    return (IFeatureCategoryLink)interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.DEFERRED.getIIDValue()),strComment);
  }  
  
  public IBusinessObject interruptProcess(IRState s, String strComment)
    throws OculusException
  {
    super.interruptProcess(s,strComment);
    if(s.getIID().equals(IDCONST.DEFINPROGRESSACCEPTED.getIIDValue()))
    {
      INotification nt = createNotification();
      nt.setAckMask(0);
      nt.setRecipientIID(getMarketingManagerObject().getIID());
      nt.setBody(strComment);
      nt.setSubject(strComment);
      nt.setNotificationKind(NotificationKind.WORKFLOW);
    }//end if
    else if(s.getIID().equals(IDCONST.SUBTOEM.getIIDValue()))
    {
      INotification nt = createNotification();
      nt.setAckMask(0);
      nt.setRecipientIID(getEngineeringManagerObject().getIID());
      nt.setBody(strComment);
      nt.setSubject(strComment);
      nt.setNotificationKind(NotificationKind.WORKFLOW);
    }//end else if
    return this;
  }
	
  private IUser getMarketingManagerObject()
    throws OculusException
  { 
    IUser user = null;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("roleiid",IDCONST.MKTMGRROLE.getIIDValue());
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);
    if(rac.hasNext())
      user = rac.nextRoleAssignment().getUserObject();
    return user; 
  }//
      
  private IUser getEngineeringManagerObject()
    throws OculusException
  { 
    IUser user = null;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("roleiid",IDCONST.ENGMGRROLE.getIIDValue());
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);
    if(rac.hasNext())
      user = rac.nextRoleAssignment().getUserObject();
    return user; 
  }//  
	
    
  public IIID getPriorityIID()
    throws OculusException
  {
    if (_priority.getValue() == null || _priority.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().makeReposID(((Long)_priority.getValue()).longValue());
  }

  public IIID getTestIID()
    throws OculusException
  {
    if (_test.getValue() == null || _test.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().makeReposID(((Long)_test.getValue()).longValue());
  }

  public IIID getDifficultyIID()
    throws OculusException
  {
    if (_difficulty.getValue() == null || _difficulty.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().makeReposID(((Long)_difficulty.getValue()).longValue());
  }


//----------------- IFeatureRevision Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
  {
     return null;
  }
  
  //------------------------- MUTATORS ------------------------------
  public IFeatureCategoryLink setSingleRoles()
    throws OculusException
  {
    IIID pariid = getCategoryObject().getIID();
    IProcessRoleList singleroles = (IProcessRoleList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProcessRoleSingleList",IDCONST.PROCESSROLECOLL.getIIDValue());
    while (singleroles.hasNext())
    {
      IProcessRole role = singleroles.nextProcessRole();
      IDataSet args = new DataSet();
      args.setIID(pariid);
      args.put("roleiid",role.getIID());
      IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);
  	  while (rac.hasNext())
  	  {
  	    IRoleAssignment ra = rac.nextRoleAssignment();
  		  if (role.getIID().equals(ra.getRoleIID()))
  		  {
  		    addToRole(ra.getUserIID(), ra.getRoleIID(),0);
  		  }//end if
  	  }//end while
    }//end while
    return this;
  }
      
  public IFeatureCategoryLink setPriorityLevel(IIID priorityIID)
    throws OculusException
  {
    if (priorityIID != null)
    {
      Long prior = new Long(priorityIID.getLongValue());
      if (getPersState().equals(PersState.UNMODIFIED) && (_priority.getValue() == null || !_priority.getValue().equals(prior) ))
        setPersState(PersState.MODIFIED);
      if (_priority.getValue() != null && !_priority.getValue().equals(prior))
      {
        String oldValue = getPriorityLevel();
        _priority.setValue(prior);
        String newValue = getPriorityLevel();
        if (isLocked() && !getPersState().equals(PersState.NEW))
        {
          IFeatureLinkChange change = getChangeLog();
          change.record(IDCONST.FEAT_PRIORITY.getIIDValue(),oldValue,newValue);
        }
      }
			else
      if (_priority.getValue() == null || !_priority.getValue().equals(prior))
        _priority.setValue(prior);
    }
    return this;
  }

  public IFeatureCategoryLink setTestLevel(IIID testIID)
    throws ORIOException
  {
    if (testIID != null)
    {
      Long test = new Long(testIID.getLongValue());
      if (getPersState().equals(PersState.UNMODIFIED) && !_test.equals(test))
        setPersState(PersState.MODIFIED);
      _test.setValue(test);
    }
    return this;
  }

  public IFeatureCategoryLink setDifficultyLevel(IIID diffIID)
    throws ORIOException
  {
    if (diffIID != null)
    {
      Long diff = new Long(diffIID.getLongValue());
      if (getPersState().equals(PersState.UNMODIFIED) && !_difficulty.equals(diff))
        setPersState(PersState.MODIFIED);
      _difficulty.setValue(diff);
    }
    return this;
  }

  public IFeatureCategoryLink setOrderNum(int orderNum)
    throws ORIOException
  {
    _orderNum.setValue(new Integer(orderNum));
    return this;
  }

  public IFeatureCategoryLink setPercentComplete(int percentComplete)
    throws ORIOException
  {
    _percentCompleted.setValue(new Integer(percentComplete));
    return this;
  }

  public IFeatureCategoryLink setEstimatedDevTime(int duration)
    throws ORIOException
  {
    _estDevTime.setValue(new Integer(duration));
    return this;
  }

  public IFeatureCategoryLink setActualDevTime(int duration)
    throws ORIOException
  {
    _actDevTime.setValue(new Integer(duration));
    return this;
  }

  public IFeatureCategoryLink setEstimatedTestTime(int duration)
    throws ORIOException
  {
    _estTestTime.setValue(new Integer(duration));
    return this;
  }

  public IFeatureCategoryLink setDevStartDate(Timestamp startDate)
    throws ORIOException
  {
    _devStartDate.setValue(startDate);
    return this;
  }

  public IFeatureCategoryLink setDevEndDate(Timestamp endDate)
    throws ORIOException
  {
    _devEndDate.setValue(endDate);
    return this;
  }

  public IFeatureCategoryLink setTestEndDate(Timestamp endDate)
    throws ORIOException
  {
    _testEndDate.setValue(endDate);
    return this;
  }

  public IFeatureCategoryLink setCategoryObject(ICategory original)
    throws OculusException
  {
    if (getPersState().equals(PersState.UNMODIFIED) && !_originalCatIID.equals(original.getIID()))
      setPersState(PersState.MODIFIED);
    _originalCatIID = original.getIID();
		setSingleRoles();
//    setMarketingManagerObject(original.getMarketingManagerObject());
//    setEngineeringManagerObject(original.getEngineeringManagerObject());
    return this;
  }

  public IBusinessObject setFeatureObject(IFeature feature)
    throws ORIOException
  {
    _featIID = feature.getIID();
    _feat = feature;
    return this;
  }

  public IFeatureCategoryLink setPinnedFeatureRevisionObject(IFeatureRevision rev)
    throws ORIOException
  {
    if (rev != null)
      _pinnedRevIID = rev.getIID();
    else
      _pinnedRevIID = null;
    return this;
  }
  
  public IBusinessObject addToWorkforce(IIID useriid, int ordernum)
    throws OculusException
  {
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getCategoryObject().getVersionObject().getIID());
    ra.setRoleIID(IDCONST.VERSIONTEAMROLE.getIIDValue());
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
    return this;
  }//
  
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException
  {
    //first add them to the team if they are not already there
    addToWorkforce(useriid,ordernum);
    //if the role is singular, add someone is already assigned, remove then add
    IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(),"Role",roleiid);
    if(role != null && !role.isMultiUser())
    {
      IDataSet args = new DataSet();
      args.setIID(getIID());
      args.put("roleiid",roleiid);
      IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args,true);
      while (rac.hasNext())
			  rac.nextRoleAssignment().delete();
//        removeFromRole(rac.nextRoleAssignment().getUserObject().getIID(),roleiid);
    }//end if
    
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(roleiid);
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
		
		if (!getPersState().equals(PersState.NEW) && role != null && !role.isMultiUser() && getStateObject().getRoleIID().equals(roleiid))
		{
		  //update the inbox
			//delete the old notifications
      INotificationColl ntc = getNotifications();
      ntc.setAsLocked();
      while(ntc.hasNext())
        ntc.nextNotification().delete();
			IIID stateiid = getStateObject().getIID();	
			if(!stateiid.equals(IDCONST.COMPASS.getIIDValue()) 
		        && !stateiid.equals(IDCONST.MYCONCEPTS.getIIDValue()) 
				    && !stateiid.equals(IDCONST.DEFERRED.getIIDValue())
				    && !stateiid.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())
				    && !stateiid.equals(IDCONST.MMVERIFIED.getIIDValue()))
      {
        INotification nt = createNotification();
        nt.setAckMask(0);
        nt.setRecipientIID(useriid);
        nt.setBody("Role changed");
        nt.setSubject("Role changed");
        nt.setNotificationKind(NotificationKind.WORKFLOW);
      }//end if	
		}
    return this;
  }//
  
  public IBusinessObject removeFromWorkforce(IIID useriid)
    throws OculusException
  { 
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID());
    while(rac.hasNext())
    {
      IRoleAssignment ra = rac.nextRoleAssignment();
      if (ra.getUserIID().getLongValue() == useriid.getLongValue())
	     {
	       IRoleAssignment rad = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment", ra.getIID(),true);
        rad.delete();
	     }//end if
    }//end while
    return this;
  }
  
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException
  { 
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID());
    while(rac.hasNext())
    {
      IRoleAssignment ra = rac.nextRoleAssignment();
      if (ra.getRoleIID().getLongValue() == roleiid.getLongValue() && ra.getUserIID().getLongValue() == useriid.getLongValue())
	     {
	       IRoleAssignment rad = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment", ra.getIID(),true);
				 rad.delete();
	     }//end if
    }//end while
    return this;
  }
	
	
	public IPersistable delete()
	throws OculusException
  {
	  ISpecSignOffColl ssoc = getSpecSignOffs(true);
		while(ssoc.hasNext())
		  ssoc.nextSpecSignOff().delete();
		
		//IAlternativeColl ac = getAlternatives(true);
	//	while(ac.hasNext())
		  //ac.nextAlternative().delete();
			
	  super.delete();
	
//		getFeatureObject(true).delete();
	  return this;
  }
  
  //------------------------- ACCESSORS -----------------------------

  public boolean isWorkflowDependent(IFeatureCategoryLink featcatlink)
    throws OculusException
  {
    boolean isDep = false;
    IFeatureColl dependents = _getAllWorkflowDependents();
    while (dependents.hasMoreFeatures() && !isDep)
    {
      IFeature dep = dependents.nextFeature();
      IFeatureCategoryLink deplink = dep.getFeatureCategoryLinkObject();
      if (deplink.getIID().equals(featcatlink.getIID()))
        isDep = true;
    }
    return isDep;
  }
    
  public boolean isFunctionalDependent(IFeatureCategoryLink featcatlink)
    throws OculusException
  {
    boolean isDep = false;
    IFeatureColl dependents = _getAllFunctionalDependents();
    while (dependents.hasMoreFeatures() && !isDep)
    {
      IFeature dep = dependents.nextFeature();
      IFeatureCategoryLink deplink = dep.getFeatureCategoryLinkObject();
      if (deplink.getIID().equals(featcatlink.getIID()))
        isDep = true;
    }
    return isDep;
  }

  
  public String getPriorityLevel()
    throws OculusException
  {
    if (_priority.getValue() == null || _priority.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().getBMRepository().getEnumliteral(getObjectContext().getRepository().makeReposID(((Long)_priority.getValue()).longValue()),false).getName();
  }

  public String getTestLevel()
    throws OculusException
  {
    if (_test.getValue() == null || _test.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().getBMRepository().getEnumliteral(getObjectContext().getRepository().makeReposID(((Long)_test.getValue()).longValue()),false).getName();
  }

  public String getDifficultyLevel()
    throws OculusException
  {
    if (_difficulty.getValue() == null || _difficulty.getValue().equals(new Long(0)))
      return null;
    return getObjectContext().getRepository().getBMRepository().getEnumliteral(getObjectContext().getRepository().makeReposID(((Long)_difficulty.getValue()).longValue()),false).getName();
  }

  public int getOrderNum()
    throws OculusException
  {
    if (_orderNum.getValue() != null)
      return ((Integer)_orderNum.getValue()).intValue();
    else
      return -1;
  }

  public int getPercentComplete()
    throws OculusException
  {
    if (_percentCompleted.getValue() != null)
      return ((Integer)_percentCompleted.getValue()).intValue();
    else
      return 0;
  }

  public int getEstimatedDevTime()
    throws OculusException
  {
    if (_estDevTime.getValue() != null)
      return ((Integer)_estDevTime.getValue()).intValue();
    else
      return 0;
  }

  public int getActualDevTime()
    throws OculusException
  {
    if (_actDevTime.getValue() != null)
      return ((Integer)_actDevTime.getValue()).intValue();
    else
      return 0;
  }

  public int getEstimatedTestTime()
    throws OculusException
  {
    if (_estTestTime.getValue() != null)
      return ((Integer)_estTestTime.getValue()).intValue();
    else
      return 0;
  }

  public Timestamp getDevStartDate()
    throws OculusException
  {
    if (_devStartDate.getValue() != null)
      return (Timestamp)_devStartDate.getValue();
    else
      return null;
  }

  public Timestamp getDevEndDate()
    throws OculusException
  {
    if (_devEndDate.getValue() != null)
      return (Timestamp)_devEndDate.getValue();
    else
      return null;
  }

  public Timestamp getTestEndDate()
    throws OculusException
  {
    if (_testEndDate.getValue() != null)
      return (Timestamp)_testEndDate.getValue();
    else
      return null;
  }
  
  public ICategory getCategoryObject()
    throws OculusException
  { return getCategoryObject(false);}

  public ICategory getCategoryObject(boolean edit)
    throws OculusException
  {
    if (_originalCatIID != null)//Egan changed this method so that when you have a featcatlink you have to specify cat lock
      return (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",_originalCatIID,edit);
    else
      return null;
  }

  public IFeatureRevision getPinnedFeatureRevisionObject()
    throws OculusException
  {
    if (_pinnedRevIID != null)
    {
      return (IFeatureRevision)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevision",_pinnedRevIID,isLocked());
    }
    else
      return null;
  }

  public IFeature getFeatureObject()
    throws OculusException
  {
		return getFeatureObject(false);
  }

  public IFeature getFeatureObject(boolean editable)
    throws OculusException
  {
    if (_feat == null || (editable && !_feat.isLocked()))
    {
      if (_pinnedRevIID != null)
        _feat = getPinnedFeatureRevisionObject().getFeatureObject(editable);
      else
        _feat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",_featIID,editable);
      _feat.setFeatureCategoryLinkObject(this);
    }
    return _feat;
  }
  
  public ISpecSignOffColl getSpecSignOffs()
     throws OculusException
  {
    return getSpecSignOffs(false);
  }
  
  public ISpecSignOffColl getSpecSignOffs(boolean editable)
     throws OculusException
  {
    ISpecSignOffColl signoffcoll = (ISpecSignOffColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SpecSignOffColl",getIID(),editable);
    return signoffcoll;
  }
  
  public IAlternativeSet getAlternativeSetObject()
    throws OculusException
  { return getAlternativeSetObject(false); }
  
  public IAlternativeSet getAlternativeSetObject(boolean edit)
    throws OculusException
  { 
    if(getAlternativeSetIID() != null)
      return (IAlternativeSet)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlternativeSet",getAlternativeSetIID(),edit); 
    else
      return null;
  }
 
	public IIID getAlternativeSetIID() throws OculusException
	{
		//need to get the alternativesetid from current iid to get list of alternatives for this feature
		IQueryProcessor stmt = null;
		try
		{
			IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
			stmt = repConn.createProcessor();
			String query;
			query = "Select OBJECTID from ALTERNATIVESET where FEATURELINKID" + " = " + _iid.getLongValue() + " AND DELETESTATE <> " + DeleteState.DELETED.getIntValue();
			IDataSet ds = stmt.retrieve(query);
			if (ds.next() && (ds.getLong("OBJECTID") != 0))
			{
				return new SequentialIID(ds.getLong("OBJECTID"));
			}
			else
			{
        //its up to the caller to create a trans and create a new alternative set
				return null; 
			}
		}
		finally
		{
			if (stmt != null)
				stmt.close();
		}
	}

  public IDiscussionTopicList getCons()
    throws OculusException
  { return getCons(false); }
    
  public IDiscussionTopicList getCons(boolean edit)
    throws OculusException
  {
    return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopicConsList",getIID(),edit);
  }
    
  public boolean hasCons()
    throws OculusException
  {  return (getCons().size() > 0); }
    
  public IDiscussionTopicList getPros()
    throws OculusException
  { return getPros(false); }
    
  public IDiscussionTopicList getPros(boolean edit)
    throws OculusException
  {
    return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopicProsList",getIID(),edit);
  }
    
  public boolean hasPros()
    throws OculusException
  { return (getPros().size() > 0); }
    
    
  
  public String getName()
    throws OculusException
  {
    if (getFeatureObject() == null)
      return null;
    return getFeatureObject().getName();
  }

  public String getDescription()
    throws OculusException
  {
    if (getFeatureObject() == null)
      return null;
    return getFeatureObject().getDescription();
  }

  public String getFullTreePathString()
    throws OculusException
  {
    return getCategoryObject().getFullTreePathString()+"/"+getName();
  }//
  
  
  public IPersistable save()
    throws OculusException
  {
    if (getPersState().equals(PersState.NEW) || getOrderNum()==0)
    {
      ICategory parentCat = getCategoryObject();
      if (parentCat != null)
      {
        IFeatureColl cats = parentCat.getFeatures();
        setOrderNum(cats.size()+1);
      }
    }
    return super.save();
  }


  public void deleteFile(IAttachment file)
    throws OculusException
  {
    getFeatureObject(true).deleteFile(file);
  }

  public void deleteLink(IHyperLink link)
    throws OculusException
  {
    getFeatureObject(true).deleteLink(link);
  }

  public void deleteDiscussionTopic(IDiscussionTopic topic)
    throws OculusException
  {
    getFeatureObject(true).deleteDiscussionTopic(topic);
  }

  
//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_ORDERNUM))
        return _orderNum;
      else if (key.equals(LABEL_PRIORITYID))
        return _priority;
      else if (key.equals(LABEL_DIFFLEVELID))
        return _difficulty;
      else if (key.equals(LABEL_TESTLEVELID))
        return _test;
      else if (key.equals(LABEL_PERCENTCOMPLETED))
        return _percentCompleted;
      else if (key.equals(LABEL_ESTDEVTIME))
        return _estDevTime;
      else if (key.equals(LABEL_ACTUALDEVTIME))
        return _actDevTime;
      else if (key.equals(LABEL_DEVSTARTDATE))
        return _devStartDate;
      else if (key.equals(LABEL_DEVENDDATE))
        return _devEndDate;
      else if (key.equals(LABEL_ESTTESTTIME))
        return _estTestTime;
      else if (key.equals(LABEL_TESTENDDATE))
        return _testEndDate;
     else
     {
        Object featProp = getFeatureObject().getProperties().get(key);
        if (featProp != null)
          return featProp;
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
      if (key.equals(LABEL_ORDERNUM))
        setOrderNum(((Integer)property.getValue()).intValue());
      else if (key.equals(LABEL_PRIORITYID))
        setPriorityLevel(getObjectContext().getRepository().makeReposID(((Integer)property.getValue()).intValue()));
      else if (key.equals(LABEL_DIFFLEVELID))
        setDifficultyLevel(getObjectContext().getRepository().makeReposID(((Integer)property.getValue()).intValue()));
      else if (key.equals(LABEL_TESTLEVELID))
        setTestLevel(getObjectContext().getRepository().makeReposID(((Integer)property.getValue()).intValue()));
      else if (key.equals(LABEL_PERCENTCOMPLETED))
        setPercentComplete(((Integer)property.getValue()).intValue());
      else if (key.equals(LABEL_ESTDEVTIME))
        setEstimatedDevTime(((Integer)property.getValue()).intValue());
      else if (key.equals(LABEL_ACTUALDEVTIME))
        setActualDevTime(((Integer)property.getValue()).intValue());
      else if (key.equals(LABEL_DEVSTARTDATE))
        setDevStartDate((Timestamp)property.getValue());
      else if (key.equals(LABEL_DEVENDDATE))
        setDevEndDate((Timestamp)property.getValue());
      else if (key.equals(LABEL_ESTTESTTIME))
        setEstimatedTestTime(((Integer)property.getValue()).intValue());
      else if (key.equals(LABEL_TESTENDDATE))
        setTestEndDate((Timestamp)property.getValue());
      else
        super.put(key,value);
    }
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
    FeatureCategoryLink rev = null;
      rev = new FeatureCategoryLink();
      rev.setIID(getIID());
      rev.setObjectContext(getObjectContext());
      rev._classIID = _classIID;
      rev._stateIID = _stateIID;
      rev.setPersState(getPersState());
      rev.setDeleteState(getDeleteState());
      if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        rev.putAll(getProperties());
//      rev.setName(getName());
//      rev.setDescription(getDescription());
      rev._creatorIID = _creatorIID;
      rev._accessIID = _accessIID;
      rev.setCreationDate(getCreationDate());
      rev._messageAttached.setValue(_messageAttached.getValue());
      rev._linkAttached.setValue(_linkAttached.getValue());
      rev._fileAttached.setValue(_fileAttached.getValue());

      rev._featIID = _featIID;
      rev._pinnedRevIID = _pinnedRevIID;
			_priority.setOwnerObject(this);
      rev._priority = (IRProperty)_priority.dolly();
      _test.setOwnerObject(this);
      rev._test = (IRProperty)_test.dolly();
      _difficulty.setOwnerObject(this);
      rev._difficulty = (IRProperty)_difficulty.dolly();
      rev.setOrderNum(getOrderNum());
      rev.setPercentComplete(getPercentComplete());
      rev.setEstimatedDevTime(getEstimatedDevTime());
      rev.setActualDevTime(getActualDevTime());
      rev.setEstimatedTestTime(getEstimatedTestTime());
      rev.setDevStartDate(getDevStartDate());
      rev.setDevEndDate(getDevEndDate());
      rev.setTestEndDate(getTestEndDate());
      rev._originalCatIID = _originalCatIID;
      rev.setPersState(getPersState());

    return rev;
  }
  
  
  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    IIID stateIID = getStateObject().getIID();
    IFeature feat = getFeatureObject();

    if (stateIID.equals(IDCONST.COMPASS.getIIDValue()) || module.equals(ModuleKind.COMPASS)) {
      if (stateIID.equals(IDCONST.COMPASS.getIIDValue()) && module.equals(ModuleKind.COMPASS)) visible = true;
    }
    else
    {
      if (stateIID.equals(IDCONST.DEFERRED.getIIDValue())) {
        if (settings.getPotFeat()) visible = true;
      }
      else
      {
        if (stateIID.equals(IDCONST.MYCONCEPTS.getIIDValue())) {
          if (settings.getMyCept() && feat.getCreatorIID().equals(getObjectContext().getConnection().getUserIID()))
            visible = true;
        }
        else
        {
          if (stateIID.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())) {
            if (settings.getReview()) visible = true;
          }
          else {
            if (settings.getActive()) visible = true;
          }
        }
      }
    }

    if (visible)
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkPerms(getCategoryObject().getVersionObject(),
       			  								 new IPermission[] {PermEnum.SPEC_VIEW,PermEnum.POT_SPEC_VIEW});
      }
      
      if ((grant.contains(PermEnum.SPEC_VIEW) && !stateIID.equals(IDCONST.DEFERRED.getIIDValue())) ||
          (grant.contains(PermEnum.POT_SPEC_VIEW) && stateIID.equals(IDCONST.DEFERRED.getIIDValue())))
        visible = true;
      else
        visible = false;
    }
    return visible;
  }

  
/********************************************/

  public void renderCustomEdit(IAttributeTable table, boolean newRev)
    throws OculusException
    {
      IFeature feat = getFeatureObject();
      IIID thisID = getIID();
      
	    IRModelElementList customs = null;
      customs = this.getDefnObject().getEntryForm().getViewableAttributeList();
        
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
              if (thisID.equals(prop.getOwnerObject().getIID())
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

/**************************/
}