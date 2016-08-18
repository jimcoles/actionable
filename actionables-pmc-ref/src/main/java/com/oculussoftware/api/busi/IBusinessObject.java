package com.oculussoftware.api.busi;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.ui.*;
import com.oculussoftware.api.ui.html.*;
import java.sql.*;



/** [Description]
*
* @author Saleem Shafi
*/

/*
* $Workfile: IBusinessObject.java $
* Description: Defines the interface that an object must implement in order for the
* Oculus CRM to manage the object as a business object.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*	---							Saleem Shafi		2/4/00			Added getAllTransitions() and
*																							getEnabledTransitions()
*	---							Saleem Shafi		2/5/00			Got rid of all of the methods and then
*																							added getStateMachine()
* ---             Saleem Shafi    2/9/00      Added getEnabledTransitions()
* ---             Saleem Shafi    3/1/00      Added the setCreator() and getCreatorObject() methods
* ---             Saleem Shafi    3/14/00     Added softDelete() and recover() to mirror the behavior
*                                             of delete() and restore() in 1.1.
* ---             Saleem Shafi    3/14/00     Moved class, state and property methods from
*                                             IRObject to IBusinessObject.
* ---             Saleem Shafi    3/16/00     Added SemanticLink methods
* ---             Cuihua Zhang    6/8/2000    Added four variations of method getAttachedDiscussionTopics()
*/

public interface IBusinessObject extends IRObject
{
  /**The value to use with getProperties().get(String) method to return the name property. */
  public static final String LABEL_NAME = "prop"+IDCONST.NAME.getIIDValue();
  /**The value to use with getProperties().get(String) method to return the description property. */
  public static final String LABEL_DESCRIPTION = "prop"+IDCONST.DESC.getIIDValue();
  /**The value to use with getProperties().get(String) method to return the creation date property. */
  public static final String LABEL_CREATIONDATE = "prop"+IDCONST.CREATION_DATE.getIIDValue();
  /**The value to use with getProperties().get(String) method to return the discussion topic attached property. */
  public static final String LABEL_MESSAGEATTACHED = "prop"+IDCONST.MESSAGE_ATTACHED.getIIDValue();
  /**The value to use with getProperties().get(String) method to return the file attached property. */
  public static final String LABEL_FILEATTACHED = "prop"+IDCONST.FILE_ATTACHED.getIIDValue();
  /**The value to use with getProperties().get(String) method to return the URL attached property. */
  public static final String LABEL_LINKATTACHED = "prop"+IDCONST.LINK_ATTACHED.getIIDValue();
  
  /** Returns the IRStateMachine object for the current object.
  *
  * @return the IRStateMachine for this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IRStateMachine getStateMachine()
		throws OculusException;    
    
  /** Returns a collection of IRTransition objects that are valid for this object
  * from its current state.
  *
  * @return the collection of valid IRTransition objects for this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRTransitionColl getEnabledTransitions()
    throws OculusException;    

  /** Executes the given transition on the current object.
  *
  * @param trans the IRTransition to execute
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException if the given transaction is not
  *            valid for the current state of this object.
  * @see #doTransition(IRTransition, String)
  */
  public IBusinessObject doTransition(IRTransition trans)
    throws OculusException;
    
  /** Executes the given transition on the current object and stores the given
  * comment as an explanation of the transaction.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException if the given transition is not
  *            valid for the current state of this object.
  * @see #doTransition(IRTransition)
  */
  public IBusinessObject doTransition(IRTransition trans, String strProcessChangeComment)
    throws OculusException;  
    
  /** Creates an IAttachment object that is associated with the current object.
  *
  * @return the new IAttachment object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment attachFile()
    throws OculusException;

  /** Creates an IHyperLink object that is associated with the current object.
  *
  * @return the new IHyperLink object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLink attachLink()
    throws OculusException;

  /** Creates an IDiscussionTopic object that is associated with the current object.
  *
  * @return the new IDiscussionTopic object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopic attachDiscussionTopic()
    throws OculusException;
  
  /** Copies the attachments from the given IBusinessObject to this object.
  *
  * @param source the IBusinessObject whose attachments are being copied
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject copyAttachmentsOf(IBusinessObject source)
    throws OculusException;

  /** Copies the URLs from the given IBusinessObject to this object.
  *
  * @param source the IBusinessObject whose URLs are being copied
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject copyHyperLinksOf(IBusinessObject source)
    throws OculusException;

  /** Copies the discussion topics from the given IBusinessObject to this object.
  *
  * @param source the IBusinessObject whose discussion topics are being copied
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject copyDiscussionTopicsOf(IBusinessObject source)
    throws OculusException;

  /** Removes the given IAttachment object from this object and deletes it.
  *
  * @param file the IAttachment being deleted
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void deleteFile(IAttachment file)
    throws OculusException;

  /** Removes the given IHyperLink object from this object and deletes it.
  *
  * @param link the IHyperLink being deleted
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void deleteLink(IHyperLink link)
    throws OculusException;

  /** Removes the given IDiscussionTopic object from this object and deletes it.
  *
  * @param topic the IDiscussionTopic being deleted
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void deleteDiscussionTopic(IDiscussionTopic topic)
    throws OculusException;
  
  /** Creates an INotification object for this object.
  *
  * @return the newly created INotification object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public INotification createNotification()
    throws OculusException;
     
  /** Creates an ISemanticLink of the given type between this object and the given object.
  * This object is the parent object in the relationship.
  *
  * @param dest the child object in the relationship
  * @param type the type of relationship being made
  * @return the newly created ISemanticLink
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink createSemanticLink(IBusinessObject dest, LinkKind type)
    throws OculusException;

  /** Removes the semantic link of the given type between this object and the given object.
  * This object is the parent object in the relationship.
  *
  * @param dest the child object in the relationship
  * @param type the type of relationship being made
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeSemanticLink(IBusinessObject dest, LinkKind type)
    throws OculusException;
  
  /** Adds the user for the given IIID to the workforce of this object in the given order.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @param ordernum the order in which to add the user
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject addToWorkforce(IIID useriid, int ordernum)
    throws OculusException;
    
  /** Adds the user for the given IIID to the workforce of this object in the given order
  * at the given role.  Note: The order number currently has no meaning.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @param roleiid the IIID of the role to which the user is assigned
  * @param ordernum the order in which to add the user
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException;
	
  /** Adds the user for the given IIID to the workforce of this object in the given order
  * at the given role.  if <code>recurse</code> is true, this method also adds this user 
  * to the workforce of all child object.  Note:The order number currently has no meaning.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @param roleiid the IIID of the role to which the user is assigned
  * @param ordernum the order in which to add the user
  * @param recurse pass true if you want this to happen to all child objects.
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum, boolean recurse)
    throws OculusException;	
  
  /** Removes the user for the given IIID from the workforce of this object for any and all
  * roles that this user has in the workforce.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject removeFromWorkforce(IIID useriid)
    throws OculusException;
    
  /** Removes the user for the given IIID from the workforce of this object for the role
  * of the given IIID.  If the user is not assigned to the role for the workforce, nothing
  * happens.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @param roleiid the IIID of the role to which the user is assigned
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException;
	
  /** Removes the user for the given IIID from the workforce of this object for the role
  * of the given IIID.  If the user is not assigned to the role for the workforce, nothing
  * happens.  If <code>recurse</code> is true, this method also removes the user from the
  * role of the workforces of all child objects.
  *
  * @param useriid the IIID of the user to add to the workforce
  * @param roleiid the IIID of the role to which the user is assigned
  * @param recurse pass true if you want this to happen to all child object.
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IBusinessObject removeFromRole(IIID useriid, IIID roleiid, boolean recurse)
    throws OculusException;	
  
  /** Creates and returns a new IProcessChange object for given IRTransition and this object.
  *
  * @param t the IRTransition being executed to cause the state change
  * @return the new IProcessChange object
  * @exception com.oculussoftware.api.sysi.OculusException if the given IRTransition is not a
  *            valid transition for this object in its current state.
  */
  public IProcessChange addProcessChange(IRTransition t)
    throws OculusException;
  
  /** Creates and returns a new IProcessChange object for given IRTransition and this object.
  * Also initializes the IProcessChange object with the given comment.
  *
  * @param t the IRTransition being executed to cause the state change
  * @param comment comment about the reason for the change
  * @return the new IProcessChange object
  * @exception com.oculussoftware.api.sysi.OculusException if the given IRTransition is not a
  *            valid transition for this object in its current state.
  */
  public IProcessChange addProcessChange(IRTransition t, String comment)
    throws OculusException;
    
  /** Changes the state of this object to the given IRState without a transition, but with
  * a process change record.
  *
  * @param s the IRState to set this object's state to
  * @param comment comment about the reason for the change
  * @return the new IProcessChange object
  * @exception com.oculussoftware.api.sysi.OculusException if the given IRState object is not
  *            in the state machine for this object.
  */
  public IBusinessObject interruptProcess(IRState s, String comment)
    throws OculusException;
    
  /** Determines if the user has the permission to edit all required fields for this object.
  * This is used to plug the hole created if the user has the permission to create an object but
  * does not have the permission to edit the name of the object.
  *
  * @return true if the user has the permission to edit all required field for this object.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean canEditAllRequiredFields() throws OculusException;

  /** Sets the IRClass that defines the type of object this is.
  *
  * @param newClass the new IRClass
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setDefnObject(IRClass newClass)
    throws OculusException;
  
  /** Sets the IRState that represents the current state of this object.
  *
  * @param newClass the new IRState
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setStateObject(IRState state)
    throws OculusException;
  
  /** Sets the time this object was first created.
  *
  * @param creationDate the time this object was created.
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IBusinessObject setCreationDate(Timestamp creationDate)
    throws ORIOException;
  
  /** Sets the creator of this object to the user for the given IIID
  *
  * @param creator the IIID of the creator
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IBusinessObject setCreatorIID(IIID creator)
    throws ORIOException;
    
  /** Sets the creator of this object to the given user
  *
  * @param creator the creator
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IBusinessObject setCreator(IUser creator)
    throws ORIOException;
    
  /** Sets the deletion state of this object.
  *
  * @param ds the deletion state for this object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IBusinessObject setDeleteState(DeleteState ds)
    throws ORIOException;
  
  /** Sets the access IIID of this object to the given IIID.  Note: The Access ID is not
  * being used at all. 
  *
  * @param access - the new access IIID
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IBusinessObject setAccessIID(IIID access)
    throws ORIOException;
    
  /** Sets whether or not the object has any discussion topics associated with it.
  *
  * @param messageAttached - boolean value for whether or not the object has discussion topics
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setMessageAttached(boolean messageAttached)
    throws OculusException;

  /** Sets whether or not the object has any files associated with it.
  *
  * @param fileAttached - boolean value for whether or not the object has files
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setFileAttached(boolean fileAttached)
    throws OculusException;

  /** Sets whether or not the object has any URLs associated with it.
  *
  * @param linkAttached - boolean value for whether or not the object has URLs
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setLinkAttached(boolean linkAttached)
    throws OculusException;

  /** Marks this object as being deleted.  In effect, this method will put the object
  * in the Trash (ie. Recycle Bin).
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject softDelete()
    throws OculusException;
    
  /** Marks this object as being non-deleted.  In effect, this method pulls the object
  * out of the Trash.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject recover()
    throws OculusException;

  /** Returns the IRClass object that defines this object.
  *
  * @return the defining IRClass object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRClass  getDefnObject()
    throws OculusException;

  /** Returns the IRState object representing the state of this object.
  *
  * @return the current IRState object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRState  getStateObject()
    throws OculusException;

  /** Returns the time that this object was created.
  *
  * @return the Timestamp object representing the creation time of this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getCreationDate()
    throws OculusException;
  
  /** Returns the IIID of the creator of this object.
  *
  * @return the IIID of the creator
  * @exception com.oculussoftware.api.sysi.ORIOException
  */
  public IIID getCreatorIID()
    throws ORIOException;
    
  /** Returns the IUser object for the creator of this object.
  *
  * @return the IUser object representing the creator of this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IUser getCreatorObject()
    throws OculusException;
    
  /** Returns the DeleteState for this object
  *
  * @return the deletion state of this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public DeleteState getDeleteState()
    throws OculusException;
  
  /** Returns the IIID of the access level for this object.  Note: Access IID is obsolete.
  *
  * @return the IIID of the access level of this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getAccessIID()
    throws ORIOException;
    
  /** Returns whether or not this object has discussion topics or not.
  *
  * @return true if this object has discussion topics, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasMessageAttached()
    throws OculusException;

  /** Returns whether or not this object has files or not.
  *
  * @return true if this object has files, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasFileAttached()
    throws OculusException;

  /** Returns whether or not this object has URLs or not.
  *
  * @return true if this object has URLs, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasLinkAttached()
    throws OculusException;

  /** Returns a collection of files associated with this object.
  *
  * @return the IFileColl of files associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getAttachedFiles()
    throws OculusException;
	
  /** Returns a collection of files associated with this object.
  *
  * @param edit if this is true, then all of the files in the collection will be locked
  * @return the IFileColl of files associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IFileColl getAttachedFiles(boolean edit)
    throws OculusException;
    
  /** Returns a collection of files associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @return the IFileColl of files associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getAttachedFiles(IDataSet args)
    throws OculusException;
		
  /** Returns a collection of files associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @param edit if this is true, then all of the files in the collection will be locked
  * @return the IFileColl of files associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getAttachedFiles(IDataSet args, boolean edit)
    throws OculusException;
    
  /** Returns a collection of images associated with this object that are designated to
  * be displayed in reports.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @return the IFileColl of images
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getViewableAttachedFiles(IDataSet args)
    throws OculusException;
		
  /** Returns a collection of images associated with this object that are designated to
  * be displayed in reports.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @param edit if this is true, then all of the files in the collection will be locked
  * @return the IFileColl of images associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getViewableAttachedFiles(IDataSet args, boolean edit)
    throws OculusException;
    
  /** Returns a collection of URLs associated with this object.
  *
  * @return the IHyperLinkColl of URLs associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLinkColl getAttachedLinks()
    throws OculusException;
	
  /** Returns a collection of URLs associated with this object.
  *
  * @param edit if this is true, then all of the URLs in the collection will be locked
  * @return the IHyperLinkColl of URLs associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IHyperLinkColl getAttachedLinks(boolean edit)
    throws OculusException;	
    
  /** Returns a collection of URLs associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @return the IHyperLinkColl of URLs associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public IHyperLinkColl getAttachedLinks(IDataSet args)
    throws OculusException;
    
  /** Returns a collection of URLs associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @param edit if this is true, then all of the URLs in the collection will be locked
  * @return the IHyperLinkColl of URLs associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLinkColl getAttachedLinks(IDataSet args, boolean edit)
    throws OculusException;
    
  /** Returns a collection of discussion topics associated with this object.
  *
  * @return the IDiscussionTopicList of discussion topics associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getAttachedDiscussionTopics()
    throws OculusException;

  /** Returns a collection of discussion topics associated with this object.
  *
  * @param edit if this is true, then all of the topics in the collection will be locked
  * @return the IDiscussionTopicList of discussion topics associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getAttachedDiscussionTopics(boolean edit)
    throws OculusException;
    
  /** Returns a collection of discussion topics associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @return the IDiscussionTopicList of discussion topics associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args)
    throws OculusException;

  /** Returns a collection of discussion topics associated with this object.
  *
  * @param args an IDataSet of information including sorting of the collection
  * @param edit if this is true, then all of the topics in the collection will be locked
  * @return the IDiscussionTopicList of discussion topics associated with this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args, boolean edit)
    throws OculusException;

  /** Returns a collection of semantic links where this object is the parent.
  *
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLinkColl getChildSemanticLinks()
    throws OculusException;

  /** Returns a collection of semantic links where this object is the parent.
  *
  * @param edit if this is true, then all of the links in the collection will be locked
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLinkColl getChildSemanticLinks(boolean edit)
    throws OculusException;
	
  /** Returns a collection of semantic links of the given type where this object is the parent.
  *
  * @param type the type of semantic link
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public ISemanticLinkColl getChildSemanticLinks(LinkKind type)
    throws OculusException;	

  /** Returns a collection of semantic links of the given type where this object is the parent.
  *
  * @param type the type of semantic link
  * @param edit if this is true, then all of the links in the collection will be locked
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLinkColl getChildSemanticLinks(LinkKind type,boolean edit)
    throws OculusException;
    
  /** Returns a collection of semantic links where this object is the child.
  *
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLinkColl getParentSemanticLinks()
    throws OculusException;
	
  /** Returns a collection of semantic links where this object is the child.
  *
  * @param edit if this is true, then all of the links in the collection will be locked
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public ISemanticLinkColl getParentSemanticLinks(boolean edit)
    throws OculusException;	
    
  /** Returns a collection of semantic links of the given type where this object is the child.
  *
  * @param type the type of semantic link
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLinkColl getParentSemanticLinks(LinkKind type)
    throws OculusException;
    
  /** Returns a collection of semantic links of the given type where this object is the child.
  *
  * @param type the type of semantic link
  * @param edit if this is true, then all of the links in the collection will be locked
  * @return the ISemanticLinkColl of links
  * @exception com.oculussoftware.api.sysi.OculusException
  */
	public ISemanticLinkColl getParentSemanticLinks(LinkKind type, boolean edit)
    throws OculusException;	
	
  /** Returns a collection of notifications for this object.
  *
  * @return the INotificationColl
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public INotificationColl getNotifications()
    throws OculusException;
    
  /** Returns a collection of notifications for this object.
  *
  * @param edit if this is true, then all of the notifications in the collection will be locked
  * @return the collection of notifications
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public INotificationColl getNotifications(boolean edit)
    throws OculusException;
    
  /** Returns a collection of all of the role assignments in the workforce of this object.
  *
  * @return the collection of role assignments
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRoleAssignmentColl getRoleAssignments()
    throws OculusException;

  /** Returns a collection of all of the role assignments in the workforce of this object.
  *
  * @param edit if this is true, then all of the notifications in the collection will be locked
  * @return the collection of role assignments
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IRoleAssignmentColl getRoleAssignments(boolean edit)
    throws OculusException;
  
  /** Returns the IUser object for the user assigned the given role.
  *
  * @param roleiid the IIID of the role
  * @return the user for the given role
  * @exception com.oculussoftware.api.sysi.OculusException if the given role is not a sigular role
  */
  public IUser getSingularRoleAssignedUser(IIID roleiid)
    throws OculusException;  
  
  /** Adds the information for this object to the given IAttributeTable in the attribute:value format
  * for view only.
  *
  * @param table the table to which the information should be added
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void renderCustomView(IAttributeTable table)
  	throws OculusException;

  /** Adds the information for this object to the given IAttributeTable in the attribute:value format
  * for edit/create.
  *
  * @param table the table to which the information should be added
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void renderCustomEdit(IAttributeTable table)
    throws OculusException;    

  /** Returns a String representing the parent heirarchy of this object.  The String should be '/'
  * delimited.  For example, if object of name 'Bill' is the child of 'Sue' is the child of 'Fred',
  * then this method run for 'Bill' would return 'Fred/Sue/Bill'.
  *
  * @param String representation of heirarchy
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFullTreePathString()
    throws OculusException;

  /** Returns an IGrantSet of permissions that the current user has on this object.
  *
  * @return the set of permissions
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IGrantSet getPermissions()
    throws OculusException;

  /** Returns whether or not the current user should see this object.  If <code>grant</code> is
  * null, then this method will check the appropriate permissions itself.
  *
  * @param module the application the user is in
  * @param settings the current view filters
  * @param grant the set of permissions the user has on this object.
  * @return true if the user can see this object, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException;
}