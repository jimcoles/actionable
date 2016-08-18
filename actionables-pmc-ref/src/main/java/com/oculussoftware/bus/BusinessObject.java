package com.oculussoftware.bus;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.util.*;
import com.oculussoftware.ui.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;


import java.sql.*;
import java.util.*;

/**
* Filename:    BusinessObject.java
* Date:        2/9/00
* Description: Provides the functionality for a basic BusinessObject.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ---             Saleem Shafi    2/23/00     Moved the labels to the interfaces, and removed
*                                             the part of delete() that deals with custom attributes
*                                             because the attributes delete state is the same as the parents.
* ---             Saleem Shafi    3/1/00      Added the setCreator() and getCreatorObject() methods
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
*                                             Rewired load() to take into account the fact that the IID may be null.
* ---             Zain Nemaze     3/1/00      Added COL_DELETESTATE
* BUG00597				Saleem Shafi		6/1/00			Needed to remove final keyword from column names because of aliasing.
* ---             Cuihua Zhang    6/8/2000    Added four variations of method getAttachedDiscussionTopics()        
*/
abstract public class BusinessObject extends com.oculussoftware.repos.ReposObject
                                     implements IBusinessObject, IRPropertyMap
{
  protected String TABLE = "DATAOBJECT";
  protected String COL_OBJECTID = "OBJECTID";
  protected String COL_GUID = "GUID";
  public String COL_CLASSID = "CLASSID";
  public String COL_STATEID = "STATEID";
  public String COL_NAME = "NAME";
  public String COL_DESCRIPTION = "DESCRIPTION";
  public String COL_CREATIONDATE = "CREATIONDATE";
  public String COL_CREATORID = "CREATORID";
  public String COL_ACCESSID = "ACCESSID";
  public String COL_MESSAGEATTACHED = "DISCUSSATTACHED";
  public String COL_FILEATTACHED = "FILEATTACHED";
  public String COL_LINKATTACHED = "LINKATTACHED";
  public String COL_DELETESTATE = "DELETESTATE";
  public static final String Comment = "No Comment Entered";
  
	protected IIID _stateIID;  											  	// the BO's current state IIID
	protected IIID _classIID;								        	  // the BO's class IIID
  protected IIID _creatorIID;
  protected IIID _accessIID;
  protected DeleteState _deleteState = DeleteState.NOT_DELETED;
  protected IRProperty _name,                         // string type
                       _description,                  // string type
                       _creationDate,           // Timestamp type
                       _messageAttached,        // boolean type
                       _linkAttached,           // boolean type
                       _fileAttached,           // boolean type
                       _class;  
	

	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public BusinessObject() throws OculusException
	{
		_guid = new GUID();
    _name = new BMProperty(this);
    _description = new BMProperty(this);
    _creationDate = new BMProperty(this);
    _messageAttached = new BMProperty(this);
    _linkAttached = new BMProperty(this);
    _fileAttached = new BMProperty(this);
    _class = new BMProperty(this);
    _accessIID = new SequentialIID(0);
    _attributes = new BMPropertyMap();
    
    _name.setDefnObject(IDCONST.NAME.getIIDValue());
    _name.setRequired(true);
    _description.setDefnObject(IDCONST.DESC.getIIDValue());
    _creationDate.setDefnObject(IDCONST.CREATION_DATE.getIIDValue());
    _messageAttached.setDefnObject(IDCONST.MESSAGE_ATTACHED.getIIDValue());
    _fileAttached.setDefnObject(IDCONST.FILE_ATTACHED.getIIDValue());
    _linkAttached.setDefnObject(IDCONST.LINK_ATTACHED.getIIDValue());
    _class.setDefnObject(IDCONST.CLASS.getIIDValue());
	}

  //-------------------------- Protected Methods -----------------------------
  
  protected String getLoadPropertiesQuery()
    throws OculusException
  {
		return  "SELECT attr.OBJECTID as ATTRIBUTEID, "+
						"		 assoc.ISREQUIRED as ISREQUIRED, "+
						"    blobVal.VALUE as blobValue, "+
						"    boolVal.VALUE as boolValue, "+       
						"    charVal.VALUE as charValue, "+       
						"    longCharVal.VALUE as longCharValue, "+
						"    timeVal.VALUE as timeValue, "+
						"    enumVal.VALUE as enumValue "+
						" FROM ((\"ATTRIBUTE\" attr LEFT OUTER JOIN INTERFACEATTRASC assoc ON attr.OBJECTID=assoc.ATTRIBUTEID)"+
						" 				LEFT OUTER JOIN \"CLASS\" cls ON assoc.INTERFACEID=cls.DEFINTERFACEID AND cls.OBJECTID="+getDefnObject().getIID()+") "+
						"  LEFT OUTER JOIN \"BLOBVALUE\" blobVal ON attr.OBJECTID=blobVal.ATTRIBUTEID "+
						"  LEFT OUTER JOIN \"BOOLEANVALUE\" boolVal ON attr.OBJECTID=boolVal.ATTRIBUTEID "+
						"  LEFT OUTER JOIN \"CHARVALUE\" charVal ON attr.OBJECTID=charVal.ATTRIBUTEID "+
						"  LEFT OUTER JOIN \"ENUMVALUE\" enumVal ON attr.OBJECTID=enumVal.ATTRIBUTEID "+
						"  LEFT OUTER JOIN \"LONGCHARVALUE\" longCharVal ON attr.OBJECTID=longCharVal.ATTRIBUTEID "+
						"  LEFT OUTER JOIN \"TIMEVALUE\" timeVal ON attr.OBJECTID=timeVal.ATTRIBUTEID "+
						"WHERE blobVal.PAROBJECTID = "+getIID()+
						"  OR boolVal.PAROBJECTID = "+getIID()+
						"  OR charVal.PAROBJECTID = "+getIID()+
						"  OR enumVal.PAROBJECTID = "+getIID()+
						"  OR longCharVal.PAROBJECTID = "+getIID()+
						"  OR timeVal.PAROBJECTID = "+getIID();
  }


protected String getLoadPropertiesQuery2()
    throws OculusException
  {
    return  " SELECT attr.OBJECTID as ATTRIBUTEID, "+
				    "    enumselVal.ENUMLITERALID as enumselValue "+
				    " FROM \"ATTRIBUTE\" attr "+
				    "  		LEFT OUTER JOIN \"ENUMSELECTION\" enumselVal ON attr.OBJECTID=enumselVal.ATTRIBUTEID "+
				    " WHERE enumselVal.PAROBJECTID = "+getIID();
  }



  protected void load(IDataSet results)
    throws OculusException
  {
    setPersState(PersState.PARTIAL);
    IRepository repos = getObjectContext().getRepository();
    _guid = new GUID(results.getString(COL_GUID).trim());       // get GUID
    _iid = results.getIID();    // get IID
    _classIID = repos.makeReposID(results.getLong(COL_CLASSID)); // get class
		if (_classIID.getLongValue() != 0)
	    _class.setValue(getDefnObject().getName());
//    if (getDefnObject().hasStateMachine())
      _stateIID = repos.makeReposID(results.getLong(COL_STATEID)); // get state
    setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE))); // get state
    setCreatorIID(repos.makeReposID(results.getLong(COL_CREATORID)));
    setAccessIID(repos.makeReposID(results.getLong(COL_ACCESSID)));
//    setName(results.getString(COL_NAME));                // get name
//    setDescription(results.getString(COL_DESCRIPTION));  // get desc
    setCreationDate(results.getTimestamp(COL_CREATIONDATE));
    setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
    setFileAttached(results.getBoolean(COL_FILEATTACHED));
    setLinkAttached(results.getBoolean(COL_LINKATTACHED)); 
    
    _deleteState = DeleteState.getInstance(results.getInt(COL_DELETESTATE));
  }


//------------------------ MUTATORS -------------------------------------

  public IBusinessObject setCreationDate(Timestamp creationDate)
    throws ORIOException
  {
    _creationDate.setValue(creationDate);
    return this;
  }
    
  public IBusinessObject setCreatorIID(IIID creator)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _creatorIID = creator;
    return this;
  }
    
  public IBusinessObject setCreator(IUser creator)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _creatorIID = creator.getIID();
    return this;
  }
  
  public IBusinessObject setDeleteState(DeleteState ds)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED) && !_deleteState.equals(ds))
      setPersState(PersState.MODIFIED);
    _deleteState = ds;
    return this;
  }
    
  public IBusinessObject setAccessIID(IIID access)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED) && !_accessIID.equals(access))
      setPersState(PersState.MODIFIED);
    _accessIID = access;
    return this;
  }
    
  public IBusinessObject setMessageAttached(boolean messageAttached)
    throws OculusException
  {
    _messageAttached.setValue(new Boolean(messageAttached));
    return this;
  }
    
  public IBusinessObject setFileAttached(boolean fileAttached)
    throws OculusException
  {
    _fileAttached.setValue(new Boolean(fileAttached));
    return this;
  }
    
  public IBusinessObject setLinkAttached(boolean linkAttached)
    throws OculusException
  {
    _linkAttached.setValue(new Boolean(linkAttached));
    return this;
  }
  
  //the subclass must implement this method if needed
  public IBusinessObject addToWorkforce(IIID useriid, int ordernum)
    throws OculusException
  { return null; }
  
  //the subclass must implement this method if needed
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException
  { return null; }
	
	//the subclass must implement this method if needed
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum, boolean recurse)
    throws OculusException
  { return null; }
  
  //the subclass must implement this method if needed
  public IBusinessObject removeFromWorkforce(IIID useriid)
    throws OculusException
  { return null; }
  
  //the subclass must implement this method if needed
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException
  { return null; }

  //the subclass must implement this method if needed
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid, boolean recurse)
    throws OculusException
  { return null; }
  
  public boolean canEditAllRequiredFields()
    throws OculusException
  {
    return getDefnObject().getEntryForm().canEditAllRequiredFields();
  }

//----------------------- ACCESSORS -------------------------------------

  public Timestamp getCreationDate()
    throws OculusException
  {
    return (Timestamp)_creationDate.getValue();
  }
    
  public IIID getCreatorIID()
    throws ORIOException
  {
    return _creatorIID;
  }
  
  public IUser getCreatorObject()
    throws OculusException
  {
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_creatorIID);
  }
    
  public IIID getAccessIID()
    throws ORIOException
  {
    return _accessIID;
  }
    
  public DeleteState getDeleteState()
    throws ORIOException
  {
    return _deleteState;
  }
  
  public boolean hasMessageAttached()
    throws OculusException
  {
    if (_messageAttached.getValue() != null)
      return ((Boolean)_messageAttached.getValue()).booleanValue();
    else
      return false;
  }
    
  public boolean hasFileAttached()
    throws OculusException
  {
    if (_fileAttached.getValue() != null)
      return ((Boolean)_fileAttached.getValue()).booleanValue();
    else
      return false;
  }
    
  public boolean hasLinkAttached()
    throws OculusException
  {
    if (_linkAttached.getValue() != null)
      return ((Boolean)_linkAttached.getValue()).booleanValue();
    else
      return false;
  }
    
  public IFileColl getAttachedFiles()
    throws OculusException
  {
    return getAttachedFiles(false);
  }

  public IFileColl getAttachedFiles(boolean edit)
    throws OculusException
  {
	  IDataSet args = new DataSet();
    return getAttachedFiles(args,edit);
  }
	
  public IHyperLinkColl getAttachedLinks()
    throws OculusException
  {
    return getAttachedLinks(false);
  }
	
	public IHyperLinkColl getAttachedLinks(boolean edit)
    throws OculusException
  {
	  IDataSet args = new DataSet();
    return getAttachedLinks(args,edit);
  }
	
  public IFileColl getViewableAttachedFiles()
    throws OculusException
  {
    return getViewableAttachedFiles(false);
  }

  public IFileColl getViewableAttachedFiles(boolean edit)
    throws OculusException
  {
	  IDataSet args = new DataSet();
    return getViewableAttachedFiles(args,edit);
  }

	public IFileColl getViewableAttachedFiles(IDataSet args)
    throws OculusException
  {
	  return getViewableAttachedFiles(args,false);
  }

  public IFileColl getViewableAttachedFiles(IDataSet args,boolean edit)
    throws OculusException
  {
    if (hasFileAttached())
    {
      args.setIID(getIID());
      return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ViewableFileList",args,edit);
    }
    else
      return null;
  }

	public IFileColl getAttachedFiles(IDataSet args)
    throws OculusException
  {
	  return getAttachedFiles(args,false);
  }

  public IFileColl getAttachedFiles(IDataSet args,boolean edit)
    throws OculusException
  {
    if (hasFileAttached())
    {
      args.setIID(getIID());
      return (IFileColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FileList",args,edit);
    }
    else
      return null;
  }

  public IHyperLinkColl getAttachedLinks(IDataSet args)
    throws OculusException
  {
	  return getAttachedLinks(args,false);
  }
	
  public IHyperLinkColl getAttachedLinks(IDataSet args,boolean edit)
    throws OculusException
  {
    if (hasLinkAttached())
    {
      args.setIID(getIID());
      return (IHyperLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"HyperLinkList",args,edit);
    }
    else
      return null;
  }
  
  public IDiscussionTopicList getAttachedDiscussionTopics()
    throws OculusException
  {
    return getAttachedDiscussionTopics(false);
  }  
  public IDiscussionTopicList getAttachedDiscussionTopics(boolean edit)
    throws OculusException
  {
    IDataSet args = new DataSet();
    return getAttachedDiscussionTopics(args,edit);
  }

  public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args)
    throws OculusException
  {
    return getAttachedDiscussionTopics(args,false);
  }
  

  public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args, boolean edit)
    throws OculusException
  {
    if (hasMessageAttached())
    {
      args.setIID(getIID());
      return (IDiscussionTopicList)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopicList",args,edit);
    }
    else
      return null;
  }

  public IBusinessObject copyAttachmentsOf(IBusinessObject source)
    throws OculusException
  {
    IFileColl files = source.getAttachedFiles();
    while (files != null && files.hasMoreFiles())
    {
      IAttachment newFile = files.nextFile().createCopy();
      newFile.setParentObject(this);
    }
    setFileAttached(source.hasFileAttached());
    return this;
  }

  public IBusinessObject copyHyperLinksOf(IBusinessObject source)
    throws OculusException
  {
    IHyperLinkColl links = source.getAttachedLinks();
    while (links != null && links.hasMoreHyperLinks())
    {
      IHyperLink newLink = links.nextHyperLink().createCopy();
      newLink.setParentObject(this);
    }
    setLinkAttached(source.hasLinkAttached());
    return this;
  }

  public IBusinessObject copyDiscussionTopicsOf(IBusinessObject source)
    throws OculusException
  {

    IDiscussionTopicList topics = source.getAttachedDiscussionTopics();
     System.out.println("got into business");  
    IDCONST idconst=null;
    long rootlong = getDefnObject().getRootDefinition().getIID().getLongValue();
    if(rootlong == IDCONST.ISTANDARDINPUT.getLongValue())
      idconst = IDCONST.STANDARDINPUT;
    if(rootlong == IDCONST.IARTICLEINPUT.getLongValue())
      idconst = IDCONST.ARTICLEINPUT;
    if(rootlong == IDCONST.IQUESTIONINPUT.getLongValue())
      idconst = IDCONST.QUESTIONINPUT;
    if(rootlong == IDCONST.IPROBLEMSTATEMENT.getLongValue())
      idconst = IDCONST.PROBLEMSTATEMENT;
    if(rootlong == IDCONST.IFEATURE.getLongValue())
      idconst = IDCONST.FEATURE;
    if(rootlong == IDCONST.IPRODUCTVERSION.getLongValue())
      idconst = IDCONST.PRODUCTVERSION;
    if(rootlong == IDCONST.IPRODUCT.getLongValue())
      idconst = IDCONST.PRODUCT;
    if(rootlong == IDCONST.ICATEGORY.getLongValue())
      idconst = IDCONST.CATEGORY;
    if(rootlong == IDCONST.IFEATURECATEGORYLINK.getLongValue())
      idconst = IDCONST.FEATURECATEGORYLINK;
    if(rootlong == IDCONST.ISTDFEATURELINK.getLongValue())
      idconst = IDCONST.STDFEATURELINK;
    if(rootlong == IDCONST.IALTERNATIVE.getLongValue())
      idconst = IDCONST.ALTERNATIVE;
    if(rootlong == IDCONST.IREACTION.getLongValue())
      idconst = IDCONST.REACTION;
        
    while (topics != null && topics.hasMoreDiscussionTopics())
    {
      IDiscussionTopic newTopic = topics.nextDiscussionTopic().createCopy();
      newTopic.setParObjectIID(getIID());
      newTopic.setParObjectType(idconst);
    }
    setMessageAttached(source.hasMessageAttached());
    return this;
  }

  



  public ISemanticLinkColl getChildSemanticLinks()
    throws OculusException
  {
    return getChildSemanticLinks(null,false);
  }
	
	public ISemanticLinkColl getChildSemanticLinks(boolean edit)
    throws OculusException
  {
    return getChildSemanticLinks(null,edit);
  }
	
	public ISemanticLinkColl getChildSemanticLinks(LinkKind type)
    throws OculusException
  {
	  return getChildSemanticLinks(type,false);
  }

  public ISemanticLinkColl getChildSemanticLinks(LinkKind type,boolean edit)
    throws OculusException
  {
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("PARENTCOL","SRCOBJECTID");
    if (type != null)
      args.put("LINKTYPE",type);
    return (ISemanticLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SemanticLinkColl",args,edit);
  }

  public ISemanticLinkColl getParentSemanticLinks()
    throws OculusException
  {
    return getParentSemanticLinks(null,false);
  }
	
	public ISemanticLinkColl getParentSemanticLinks(boolean edit)
    throws OculusException
  {
    return getParentSemanticLinks(null,edit);
  }
	
	public ISemanticLinkColl getParentSemanticLinks(LinkKind type)
    throws OculusException
  {
	  return getParentSemanticLinks(type,false);
  }

  public ISemanticLinkColl getParentSemanticLinks(LinkKind type,boolean edit)
    throws OculusException
  {
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("PARENTCOL","DESTOBJECTID");
    if (type != null)
      args.put("LINKTYPE",type);
    return (ISemanticLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SemanticLinkColl",args,edit);
  }
  
  public INotificationColl getNotifications() 
    throws OculusException
  {
    return getNotifications(false); 
  }
  
  public INotificationColl getNotifications(boolean edit) 
    throws OculusException
  {
    return (INotificationColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ObjectNotificationColl",getIID(),edit);
  }
  
	public IRoleAssignmentColl getRoleAssignments()
    throws OculusException
  {
	  return getRoleAssignments(false);
  }
	
  public IRoleAssignmentColl getRoleAssignments(boolean edit)
    throws OculusException
  {
    return (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID(),edit); 
  }
  
  public IUser getSingularRoleAssignedUser(IIID roleiid)
    throws OculusException
  {
    IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(),"Role",roleiid);
    if(role.isMultiUser()) throw new OculusException("This role ("+role.getIID()+") is not a Singular Role.");
    IUser user = null;
    IDataSet args = new DataSet();
    args.setIID(getIID());
    args.put("roleiid",role.getIID());
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);
    if(rac.hasNext())
			user = rac.nextRoleAssignment().getUserObject();
    return user;
  }  
  
  
  
//----------------- IBusinessObject Methods ------------------------------------
	/** Returns this product's state machine object */
	public IRStateMachine getStateMachine()
		throws OculusException
	{
		return getDefnObject().getStateMachine();
	}

  /** Returns the transition objects that represent valid transitions for the current state of the object */
  public IRTransitionColl getEnabledTransitions()
    throws OculusException
  {
    if (getDefnObject().hasStateMachine())
      return getStateMachine().getEnabledTransitions(this);
    else
      return null;
  }

  public IBusinessObject doTransition(IRTransition trans)
    throws OculusException
  {
    return doTransition(trans,"");
  }
    
  public IBusinessObject doTransition(IRTransition trans, String strProcessChangeComment)
    throws OculusException
  {
    getStateMachine().doTransition(this,trans,strProcessChangeComment);
    return this;
  }
  
  /** Returns the file object to be attached to the bo */
  public IAttachment attachFile()
    throws OculusException
  {
    IAttachment newAttachment = (IAttachment)getObjectContext().getCRM().getCompObject(getObjectContext(),"File",(IDataSet)null,true);
    newAttachment.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",IDCONST.FILE.getIIDValue()));
    newAttachment.setParentObject(this);
    if (this instanceof IFeatureCategoryLink)
      ((IFeatureCategoryLink)this).getFeatureObject(true).setFileAttached(true);
    else
      setFileAttached(true);
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
    if (this instanceof IFeatureCategoryLink)
      ((IFeatureCategoryLink)this).getFeatureObject(true).setLinkAttached(true);
    else
      setLinkAttached(true);
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
    
    IDCONST idconst=null;
    long rootlong = getDefnObject().getRootDefinition().getIID().getLongValue();
    if(rootlong == IDCONST.ISTANDARDINPUT.getLongValue())
      idconst = IDCONST.STANDARDINPUT;
    if(rootlong == IDCONST.IARTICLEINPUT.getLongValue())
      idconst = IDCONST.ARTICLEINPUT;
    if(rootlong == IDCONST.IQUESTIONINPUT.getLongValue())
      idconst = IDCONST.QUESTIONINPUT;
    if(rootlong == IDCONST.IPROBLEMSTATEMENT.getLongValue())
      idconst = IDCONST.PROBLEMSTATEMENT;
    if(rootlong == IDCONST.IFEATURE.getLongValue())
      idconst = IDCONST.FEATURE;
    if(rootlong == IDCONST.IPRODUCTVERSION.getLongValue())
      idconst = IDCONST.PRODUCTVERSION;
    if(rootlong == IDCONST.IPRODUCT.getLongValue())
      idconst = IDCONST.PRODUCT;
    if(rootlong == IDCONST.ICATEGORY.getLongValue())
      idconst = IDCONST.CATEGORY;
    if(rootlong == IDCONST.IFEATURECATEGORYLINK.getLongValue())
      idconst = IDCONST.FEATURECATEGORYLINK;
    if(rootlong == IDCONST.ISTDFEATURELINK.getLongValue())
      idconst = IDCONST.STDFEATURELINK;
    if(rootlong == IDCONST.IALTERNATIVE.getLongValue())
      idconst = IDCONST.ALTERNATIVE;
    if(rootlong == IDCONST.IREACTION.getLongValue())
      idconst = IDCONST.REACTION;
    
    newDT.setParObjectType(idconst);  
    //newDT.setParObjectType(IDCONST.getInstance(getDefnObject().getIID()));
    if(setMessageAttached)
    {
      if (this instanceof IFeatureCategoryLink)
        ((IFeatureCategoryLink)this).getFeatureObject(true).setMessageAttached(true);
      else
     		setMessageAttached(true);
    }
    return newDT;
  }
	
  public void deleteFile(IAttachment file)
    throws OculusException
  {
		file.delete();

		IFileColl files = getAttachedFiles();
		if (files == null || !files.hasNext())
		  setFileAttached(false);
  }

  public void deleteLink(IHyperLink link)
    throws OculusException
  {
		link.delete();

	  IHyperLinkColl links = getAttachedLinks();
	  if (links == null || !links.hasNext())
	    setLinkAttached(false);
  }

  public void deleteDiscussionTopic(IDiscussionTopic topic)
    throws OculusException
  {
		topic.delete();
    
    IDiscussionTopicList topics = getAttachedDiscussionTopics();
    if (topics == null || !topics.hasNext())
      setMessageAttached(false);
  }
  

	
  public INotification createNotification()
    throws OculusException
  {
    IIID classIID = IDCONST.NOTIFICATION.getIIDValue();
    INotification newNT = (INotification)getObjectContext().getCRM().getCompObject(getObjectContext(),"Notification",(IDataSet)null,true);
    newNT.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    newNT.setParObjectIID(getIID());
    return newNT;
  }
  
  public ISemanticLink createSemanticLink(IBusinessObject dest, LinkKind type)
    throws OculusException
  {
    ISemanticLink newLink = (ISemanticLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"SemanticLink",(IDataSet)null,true);
    newLink.setSourceObjectIID(this.getIID());
    newLink.setDestObjectIID(dest.getIID());
    newLink.setLinkType(type);
    return newLink;
  }
  
  public void removeSemanticLink(IBusinessObject dest, LinkKind type)
    throws OculusException
  {
    ISemanticLinkColl children = getChildSemanticLinks(type, true);
    while (children.hasMoreSemanticLinks())
    {
      ISemanticLink child = children.nextSemanticLink();
      if (child.getDestObjectIID().equals(dest.getIID()))
        child.delete();
    }
  }
  
  public IPersistable delete()
    throws OculusException
  {
	  //delete attachments
		IFileColl files = getAttachedFiles(true);
		while(files != null && files.hasNext())
		  files.nextFile().delete();
			
		IHyperLinkColl links = getAttachedLinks(true);	
		while(links != null && links.hasNext())
		  links.nextHyperLink().delete();
		
		INotificationColl notes = getNotifications(true);
		while(notes != null && notes.hasNext())
		  notes.nextNotification().delete();
			
	  IRoleAssignmentColl roles = getRoleAssignments(true);
		while(roles != null && roles.hasNext())
		  roles.nextRoleAssignment().delete();
			
		ISemanticLinkColl parlinks = getParentSemanticLinks(true);
		while(parlinks != null && parlinks.hasNext())
		  parlinks.nextSemanticLink().delete();
			
		ISemanticLinkColl childlinks = getChildSemanticLinks(true);
		while(childlinks != null && childlinks.hasNext())
		  childlinks.nextSemanticLink().delete();
		
		IDiscussionTopicColl topics = (IDiscussionTopicColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"DiscussionTopicColl", getIID(),true);
		while(topics != null && topics.hasNext())
		  topics.nextDiscussionTopic().delete();
			
	  IProcessChangeList changes = (IProcessChangeList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProcessChangeList", getIID(),true);
		while(changes != null && changes.hasNext())
		  changes.nextProcessChange().delete();
			
	  super.delete();
		return this;
  }
	
  public IBusinessObject softDelete()
    throws OculusException
  {
    setDeleteState(DeleteState.DELETED);
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    setDeleteState(DeleteState.NOT_DELETED);
    return this;
  }

  public IProcessChange addProcessChange(IRTransition t)
    throws OculusException
  {
    return addProcessChange(t, Comment);
  }//
  
  public IProcessChange addProcessChange(IRTransition t, String comment)
    throws OculusException
  {
    IRepository rep = getObjectContext().getRepository();
    //write the process change
    IProcessChange pc = (IProcessChange)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProcessChange",(IDataSet)null, true);
    pc.setChangeObjectIID(getIID());
    boolean isFeature = true;
    if(this instanceof IFeatureCategoryLink)
      pc.setRevisionIID(((IFeatureCategoryLink)this).getFeatureObject().getFeatureRevisionObject().getIID());
    else
      isFeature = false;
    pc.setFromUserIID(getObjectContext().getConnection().getUserIID());
    if(t!=null)
      pc.setTransitionIID(t.getIID());
    pc.setAckMask(0);
    pc.setComment(comment);
    //write the discussiontopic
    IDiscussionTopic dt = attachDiscussionTopic(false);
    dt.setNumChildren(0);
    dt.setEditedDate(new java.sql.Timestamp(System.currentTimeMillis()));
    dt.isRoot(true);
    dt.setTreeLevel(0);
    dt.setTopicKind(TopicKind.TRANSACTIONCOMMENT);//
    String strComment = ""+getName();
    if(t != null)
      strComment += " changed from "+t.getFromStateObject(false).getName()+" to "+t.getToStateObject(false).getName();
    dt.setSubject((t!=null?""+t.getToStateObject(false).getName():strComment));
    dt.setBody(comment);
    if(isFeature)
    {
      updateInbox(pc,strComment);
    }//end if
    return pc;
  }//
  
  private void updateInbox(IProcessChange pc, String strComment) throws OculusException
  {
    //delete the old notifications
    INotificationColl ntc = getNotifications();
    ntc.setAsLocked();
    while(ntc.hasNext())
      ntc.nextNotification().delete();
    //write the notifications
		IIID stateiid = getStateObject().getIID();
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID());
    while(rac.hasNext())
    {
      IRoleAssignment ra = rac.nextRoleAssignment();
      if(ra.getRoleIID().equals(getStateObject().getRoleIID()))
      {
        if(!stateiid.equals(IDCONST.COMPASS.getIIDValue()) 
		        && !stateiid.equals(IDCONST.MYCONCEPTS.getIIDValue()) 
				    && !stateiid.equals(IDCONST.DEFERRED.getIIDValue())
				    && !stateiid.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())
				    && !stateiid.equals(IDCONST.MMVERIFIED.getIIDValue()))
        {
          INotification nt = createNotification();
          nt.setAckMask(0);
          nt.setRecipientIID(ra.getUserIID());
          nt.setBody(strComment);
          nt.setSubject(strComment);
          nt.setNotificationKind(NotificationKind.WORKFLOW);
          pc.addReceiver(ra.getUserObject());
        }//end if
      }//end if
    }//end while
  }
  
  public IBusinessObject interruptProcess(IRState s, String comment)
    throws OculusException
  {
    IRStateMachine machine = getStateMachine();
    if(machine.getIID().equals(s.getStateMachineIID()))
    {
      //write the process change
      IProcessChange pc = (IProcessChange)CRM.getInstance().getCompObject(getObjectContext(),"ProcessChange",(IDataSet)null, true);
      pc.setChangeObjectIID(getIID());
      if(this instanceof IFeatureCategoryLink)
        pc.setRevisionIID(((IFeatureCategoryLink)this).getFeatureObject().getFeatureRevisionObject().getIID());
      pc.setFromUserIID(getObjectContext().getConnection().getUserIID());
      pc.setAckMask(0);
      pc.setComment(comment);
      
      IDataSet args = new DataSet();
      args.setIID(getIID());
      args.put("roleiid",s.getRoleIID());
      IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);  
      while(rac.hasNext())
        pc.addReceiver(rac.nextRoleAssignment().getUserObject());
      
      
      //write the discussiontopic
      IDiscussionTopic dt = attachDiscussionTopic(false);
      dt.setNumChildren(0);
      dt.setEditedDate(new java.sql.Timestamp(System.currentTimeMillis()));
      dt.isRoot(true);
      dt.setTreeLevel(0);
      dt.setTopicKind(TopicKind.TRANSACTIONCOMMENT);//
      String strComment = ""+getName();
      strComment += " changed from "+getStateObject().getName()+" to "+s.getName();
      dt.setSubject(strComment);
      dt.setBody(comment);
      
      //delete the old notifications
      INotificationColl ntc = getNotifications();
      ntc.setAsLocked();
      while(ntc.hasNext())
        ntc.nextNotification().delete();
      
      setStateObject(s);
    }
    else 
      throw new OculusException("Not a valid state for this statemachine.");

    return this;
  }
  
//----------------- IRPropertyMap Methods -------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_NAME))
        return _name;
      else if (key.equals(LABEL_DESCRIPTION))
        return _description;
      else if (key.equals(LABEL_CREATIONDATE))
        return _creationDate;
      else if (key.equals(LABEL_MESSAGEATTACHED))
        return _messageAttached;
      else if (key.equals(LABEL_FILEATTACHED))
        return _fileAttached;
      else if (key.equals(LABEL_LINKATTACHED))
        return _linkAttached;
      else
        return _attributes.get(key);
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
      if (key.equals(LABEL_NAME))
        setName((String)property.getValue());
      else if (key.equals(LABEL_DESCRIPTION))
        setDescription((String)property.getValue());
      else if (key.equals(LABEL_CREATIONDATE))
        setCreationDate((Timestamp)property.getValue());
      else if (key.equals(LABEL_MESSAGEATTACHED))
        setMessageAttached(((Boolean)property.getValue()).booleanValue());
      else if (key.equals(LABEL_FILEATTACHED))
        setFileAttached(((Boolean)property.getValue()).booleanValue());
      else if (key.equals(LABEL_LINKATTACHED))
        setLinkAttached(((Boolean)property.getValue()).booleanValue());
      else
        _attributes.put(key,value);
    }
  }




  /** Copys the given list of properties to this product */
  public void putAll(IRPropertyMap props) throws OculusException
  {
    _attributes.putAll(props, this);
  }
  
  /** Copys the given list of properties to this product */
  public void putAll(IRPropertyMap props, IRObject obj) throws OculusException
  {
    _attributes.putAll(props, obj);
  }

	/** Returns a Collection object representing the values for this product */
  public Collection values()
    throws ORIOException
	{
		return _attributes.values();
	}
    
	/** Returns a Set of property keys */
  public Set keys()
    throws ORIOException
	{
		return _attributes.keys();
	}
  
	/** Returns a Set of property values */
  public Set entries()
    throws ORIOException
	{
		return _attributes.entries();
	}
  
  /** Returns true if this product contains an attribute with the given key */
  public boolean containsKey(Object key)
 	{
 		return _attributes.containsKey(key);
	}

  /** Returns the number of attributes for this bo */
  public int size()
  {
    return _attributes.size();
  }

  /** Returns the Map object that represents the data in the IRPropertyMap */
  public Map getMap()
  {
    return _attributes.getMap();
  }
  

//----------------- IRObject Methods ------------------------------------
	/** Returns the name attribute of this bo. */
  public String getName()
    throws OculusException
	{
		return (String)_name.getValue();
	}
	
	/** Sets the name attribute of this bo */
	public IRElement setName(String name)
    throws OculusException
	{
    _name.setValue(name);
    return this;
	}
	
	/** Returns the description attribute of this bo */
	public String getDescription()
		throws OculusException
	{
		return (String)_description.getValue();
	}
	
	/** Sets the description attribute of this bo */
	public IRElement setDescription(String description)
    throws OculusException
	{
    _description.setValue(description);
		return this;
	}

  /** Sets the specific class of this bo. */
  public IBusinessObject setDefnObject(IRClass newClass)
    throws OculusException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _classIID = newClass.getIID();
    _class.setValue(newClass.getName());
    // start at the default start state for the class
    if (getDefnObject().hasStateMachine())
      setStateObject(getStateMachine().getDefaultStartStateObject());
    // get the attributes that correspond to this class
    _attributes = newClass.getAttributes(this);
    return this;
  }

	/** Returns the class object that defines this product. */
  public IRClass  getDefnObject()
    throws OculusException
	{
    if (_classIID != null)
      return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class", _classIID);
    else
      return null;
	}
	
	/** Returns the state object for the current state of this product. */
	public IRState  getStateObject()
    throws OculusException
	{
    if (getDefnObject().hasStateMachine() && _stateIID != null)
      return getStateMachine().getStateObject(_stateIID,false);
    else
      return null;
	}
	
	/** Sets the current state of this object. */
  public IBusinessObject setStateObject(IRState state)
    throws OculusException
	{
    if (getDefnObject().hasStateMachine())
    {
      if (getPersState().equals(PersState.UNMODIFIED))
        setPersState(PersState.MODIFIED);
      _stateIID = state.getIID();
    }
		return this;
	}
	
	/** This returns an IRPropertyMap for this product */
  public IRPropertyMap getProperties( )
    throws OculusException
	{
    if (getPersState().equals(PersState.PARTIAL))
    {
      loadProperties();
      PoolMgr.getInstance().replace(this);
    }
		return this;
	}
	
//----------------- IRPersistable Methods ------------------------------------

  public IPersistable save()
    throws OculusException
  {
    if (getPersState().equals(PersState.NEW))
    {
      setCreationDate(new Timestamp(System.currentTimeMillis()));
      setCreatorIID(getObjectContext().getConnection().getUserIID());

      // create the grant set
      Set gs = new HashSet();
      gs.add(PermEnum.OWNER);
      // request the actual grant
      grantPermissions(getCreatorIID(), gs);      
    }
    return super.save();
  }

//----------------- IPoolable Methods ------------------------------------
  /** The concrete bo must implement this */
	abstract public Object dolly() throws OculusException;

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = null;
    
    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);

    if (args == null)
    {
      iid = getObjectContext().getRepository().genReposID();
      setPersState(PersState.NEW);
    }
    else
    {
      setPersState(PersState.UNINITED);
      iid = args.getIID();
    }
    setIID(iid);

    if (args != null && args.containsKey(COL_CLASSID))
      load(args);

    return this;
  }

  // This method will add any attribute to the object that were added to the form
  // after the object was created.
  protected void _resyncProps(IRModelElementList list) throws OculusException
  {
	  IRPropertyMap props = this.getProperties();
	  IQueryProcessor stmt = null;
	  IRConnection jdtC = null;
	  try
	  {
	  	jdtC = getObjectContext().getRepository().getDataConnection(getObjectContext());
	  	stmt = jdtC.createProcessor();			
	    			  
	    IDataSet results = stmt.retrieve("SELECT attr.OBJECTID as ATTRIBUTEID, "+
	  						      				"		 assoc.ISREQUIRED as ISREQUIRED "+
	  						      				" FROM (\"ATTRIBUTE\" attr LEFT OUTER JOIN INTERFACEATTRASC assoc ON attr.OBJECTID=assoc.ATTRIBUTEID)"+
	  						      				" 				LEFT OUTER JOIN \"CLASS\" cls ON assoc.INTERFACEID=cls.DEFINTERFACEID "+
	  						      				"WHERE cls.OBJECTID = "+getDefnObject().getIID());
	    while (results.next())
	    {
	      IRProperty thisProperty = new BMProperty(this);
	      //Saleem: I added the next two lines
	      IIID id1 = new SequentialIID(results.getLong("ATTRIBUTEID"));
				String key = "prop"+id1;
				if (props.get(key) == null)
				{
					IRProperty newProp = new BMProperty(this);
					newProp.setDefnObject(id1);
					newProp.load(results);
					newProp.setPersState(PersState.NEW);
					props.put(key,newProp);
				}
	    }
	  }
	  finally
	  {
	  	if (stmt != null) stmt.close();
//	  	getObjectContext().getCRM().returnDatabaseConnection(jdtC);
	  }
  }
 
 
  public void renderCustomView(IAttributeTable table)
  	throws OculusException
  	{
      IRClass cls = this.getDefnObject();
      if (cls == null) throw new OculusException("Object does not have a corresponding IRClass object.");
      IREntryForm frm = cls.getEntryForm();
      if (frm == null) throw new OculusException("Class does not have a corresponding EntryForm object.");
      IRModelElementList customs = frm.getViewableAttributeList();
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

  public void renderCustomEdit(IAttributeTable table)
    throws OculusException
    {
      IRClass cls = this.getDefnObject();
      if (cls == null) throw new OculusException("Object does not have a corresponding IRClass object.");
      IREntryForm frm = cls.getEntryForm();
      if (frm == null) throw new OculusException("Class does not have a corresponding EntryForm object.");
	    IRModelElementList customs = frm.getViewableAttributeList();
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
 
    public IRObject grantPermissions(IIID useriid, Set gs) throws OculusException
    {
       try
      {
        // get a 'system' user context
//        IObjectContext systemContext = new ObjectContext();
//        ICRMConnection conn = getObjectContext().getCRM().connect("system", "system");
//        systemContext.setConnection(conn);
        // get the ACM for the system context
//        com.oculussoftware.api.sysi.sec.IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(systemContext);
        
        // JKC 5/30/00: try using normal context instead of 'system'...
        com.oculussoftware.api.sysi.sec.IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        
        // request the actual grant
        IRClass cls = this.getDefnObject();
        if (cls != null 
          && PermEnum.areAllValidPermissions(gs,IDCONST.getInstance(cls.getBaseClass().getIID())))
    	    acm.grantPermissions(useriid.getLongValue(), this, gs);
      }//end try
      catch (OculusException ocu)
      { throw ocu; }
      catch (Exception exp)
      { throw new OculusException(exp); }
      return this;
    }
 
  public String getFullTreePathString()
    throws OculusException
  {
    return "";
  }

  public IGrantSet getPermissions()
    throws OculusException
  {
    return null;
  }

  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    return false;
  }

 public void remove (Object key)
  {
  }
}