package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.common.org.IUser;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.busi.common.process.IProcessChange;
import com.oculussoftware.api.busi.mkt.comm.*;
import java.sql.*;

/** This interface represents the link between a feature and a category and all of
* the data that goes along with it.  It has knowledge of all version-specific feature
* information, such as priority, percent complete, etc.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeatureCategoryLink.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
* ---             Saleem Shafi    3/13/00     Added getPinnedFeatureRevisionObject()
*/

public interface IFeatureCategoryLink extends IBusinessObject
{
  /** This value is to be used to retrieve the priority property using IRPropertyMap.get(). */
	public static final String LABEL_PRIORITYID = "prop"+IDCONST.FEAT_PRIORITY.getIIDValue();
  /** This value is to be used to retrieve the test level property using IRPropertyMap.get(). */
	public static final String LABEL_TESTLEVELID = "prop"+IDCONST.FEAT_TEST.getIIDValue();
  /** This value is to be used to retrieve the difficulty level property using IRPropertyMap.get(). */
	public static final String LABEL_DIFFLEVELID = "prop"+IDCONST.FEAT_DIFFICULTY.getIIDValue();
  /** This value is to be used to retrieve the order number property using IRPropertyMap.get(). */
	public static final String LABEL_ORDERNUM = "prop"+IDCONST.ORDERNUM.getIIDValue();
  /** This value is to be used to retrieve the percent complete property using IRPropertyMap.get(). */
	public static final String LABEL_PERCENTCOMPLETED = "prop"+IDCONST.PERCENT_COMPLETE.getIIDValue();
  /** This value is to be used to retrieve the estimated development time property using IRPropertyMap.get(). */
	public static final String LABEL_ESTDEVTIME = "prop"+IDCONST.EST_DEV_TIME.getIIDValue();
  /** This value is to be used to retrieve the actual development time property using IRPropertyMap.get(). */
	public static final String LABEL_ACTUALDEVTIME = "prop"+IDCONST.ACTUAL_DEV_TIME.getIIDValue();
  /** This value is to be used to retrieve the development start date property using IRPropertyMap.get(). */
	public static final String LABEL_DEVSTARTDATE = "prop"+IDCONST.DEV_START_DATE.getIIDValue();
  /** This value is to be used to retrieve the development end date property using IRPropertyMap.get(). */
	public static final String LABEL_DEVENDDATE = "prop"+IDCONST.DEV_END_DATE.getIIDValue();
  /** This value is to be used to retrieve the estimated test time property using IRPropertyMap.get(). */
	public static final String LABEL_ESTTESTTIME = "prop"+IDCONST.EST_TEST_TIME.getIIDValue();
  /** This value is to be used to retrieve the test end date property using IRPropertyMap.get(). */
	public static final String LABEL_TESTENDDATE = "prop"+IDCONST.TEST_END_DATE.getIIDValue();

  /** Creates a functional dependency from this feature-category link to the
  * given parent.
  *
  * @param parent the parent feature-category link in the relationship
  * @return the semantic link object representing the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink addFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException;

  /** Creates a workflow dependency from this feature-category link to the
  * given parent.
  *
  * @param parent the parent feature-category link in the relationship
  * @return the semantic link object representing the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink addWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException;

  /** Creates, attaches and returns a new engineering spec file.
  *
  * @return the newly created engineering spec file
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment attachEngrSpecFile()
    throws OculusException;

  /** Creates, attaches and returns a new engineering spec URL.
  *
  * @return the newly created engineering spec URL
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLink attachEngrSpecHyperLink()
    throws OculusException;

  /** Creates and returns an extact copy of this feature-category link.
  *
  * @return the newly created feature-category link object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink createLinkCopy()
    throws OculusException;

  /** Creates and returns a new process change object.
  *
  * @return the newly created process change object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProcessChange createProcessChange()
    throws OculusException;

  /** Creates and returns a new process change object.
  *
  * @param recipient the user to be notified of the change
  * @return the newly created process change object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProcessChange createProcessChange(IUser recipient)
    throws OculusException;

  /** Creates and returns a new process change object.
  *
  * @param recipient the user to be notified of the change
  * @param comment the comment about the change
  * @return the newly created process change object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProcessChange createProcessChange(IUser recipient, String comment)
    throws OculusException;

  /** Creates and returns a new process change object.
  *
  * @param comment the comment about the change
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProcessChange createProcessChange(String comment)
    throws OculusException;

  /** Creates and returns a new spec signoff object for the given user.
  *
  * @param user the newly created spec signoff object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISpecSignOff createSpecSignOff(IUser user)
    throws OculusException;

  /** Disassociates and deletes the given engineering spec file.
  *
  * @param the engineering spec file to delete
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void deleteEngrSpecFile(IAttachment file)
    throws OculusException;

  /** Disassociates and deletes the given engineering spec URL.
  *
  * @param the engineering spec URL to delete
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void deleteEngrSpecHyperLink(IHyperLink link)
    throws OculusException;

  /** Returns the number of actual development hours required to complete the feature.
  *
  * @return the number of development hours
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getActualDevTime()
    throws OculusException;

  /** Returns the IIID of the alternative set for this feature-category link.
  *
  * @return the IIID of the alternative set for this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getAlternativeSetIID()
    throws OculusException;

  /** Returns the alternative set for this feature-category link.
  *
  * @return the alternative set for this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlternativeSet getAlternativeSetObject()
    throws OculusException;

  /** Returns the alternative set for this feature-category link.
  *
  * @param edit true if the category should be locked
  * @return the alternative set for this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAlternativeSet getAlternativeSetObject(boolean edit)
    throws OculusException;

  /** Returns the category in this feature-category link.
  *
  * @return the category in this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory getCategoryObject()
    throws OculusException;

  /** Returns the category in this feature-category link.
  *
  * @param edit true if the category should be locked
  * @return the category in this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ICategory getCategoryObject(boolean edit)
    throws OculusException;

  /** Returns the current change log entry for this feature-category link.
  *
  * @return current change log entry
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChange getChangeLog()
    throws OculusException;

  /** Returns the list of change log entries for this feature-category link.
  *
  * @return collection of change log entries
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureLinkChangeColl getChangeLogs()
    throws OculusException;

  /** Returns the list of cons for this feature-category link.
  *
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getCons()
    throws OculusException;

  /** Returns the list of cons for this feature-category link.
  *
  * @param editable true if the pros should be locked.
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getCons(boolean edit)
    throws OculusException;

  /** Returns the date on which development ended
  *
  * @return the development end date
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getDevEndDate()
    throws OculusException;

  /** Returns the date on which development started
  *
  * @return the development start date
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getDevStartDate()
    throws OculusException;

  /** Returns the IIID of the difficulty level for this feature-category link.
  *
  * @return IIID of the difficulty level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getDifficultyIID()
    throws OculusException;

  /** Returns the String representation of the difficulty level for this feature-category
  * link.
  *
  * @return String difficulty level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getDifficultyLevel()
    throws OculusException;

  /** Returns the number of hours estimated for development
  *
  * @return number of development hours
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getEstimatedDevTime()
    throws OculusException;

  /** Returns the number of hours estimated for testing
  *
  * @return number of test hours
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getEstimatedTestTime()
    throws OculusException;

  /** The feature object in this feature-category link object.
  *
  * @return the feature in this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature getFeatureObject()
    throws OculusException;

  /** The feature object in this feature-category link object.
  *
  * @param editable true if the feature should be locked
  * @return the feature in this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature getFeatureObject(boolean editable)
    throws OculusException;

  /** Returns the String representing the fully-qualified location of this 
  * feature-category link.
  *
  * @return fully-qualified location of this feature-category link
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFullTreePathString()
    throws OculusException;

  /** Returns the collection of features that this feature-category link is functional
  * dependent upon.
  *
  * @return collection of workflow dependencies
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFunctionalDependencies()
    throws OculusException;

  /** Returns the collection of features that are functional dependents of this
  * feature-category link.
  *
  * @return collection of workflow-dependent features
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getFunctionalDependents()
    throws OculusException;

  /** Returns the order number of this feature-category link with respect to the 
  * other features in this category.
  *
  * @return order number
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getOrderNum()
    throws OculusException;

  /** Returns the percent of development that is complete.
  *
  * @return percent of development complete
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getPercentComplete()
    throws OculusException;

  /** Returns feature revision object that this feature-category link is pinned to.
  *
  * @return feature revision this feature-category link is pinned to
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision getPinnedFeatureRevisionObject()
    throws OculusException;

  /** Returns the IIID of the priority level for this feature-category link.
  *
  * @return IIID of the priority level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getPriorityIID()
    throws OculusException;

  /** Returns the String representation of the priority level for this feature-category
  * link.
  *
  * @return String priority level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getPriorityLevel()
    throws OculusException;

  /** Returns the list of pros for this feature-category link.
  *
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getPros()
    throws OculusException;

  /** Returns the list of pros for this feature-category link.
  *
  * @param editable true if the pros should be locked.
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IDiscussionTopicList getPros(boolean edit)
    throws OculusException;

  /** Returns the list of spec signoff for this feature-category link.
  *
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISpecSignOffColl getSpecSignOffs()
    throws OculusException;

  /** Returns the list of spec signoff for this feature-category link.
  *
  * @param editable true if the signoffs should be locked.
  * @return collection of spec signoffs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISpecSignOffColl getSpecSignOffs(boolean editable)
    throws OculusException;

  /** Returns the date on which testing ended
  *
  * @return the testing end date
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public Timestamp getTestEndDate()
    throws OculusException;

  /** Returns the IIID of the test level for this feature-category link.
  *
  * @return IIID of the test level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getTestIID()
    throws OculusException;

  /** Returns the String representation of the test level for this feature-category
  * link.
  *
  * @return String test level
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getTestLevel()
    throws OculusException;

  /** Returns the collection of features that this feature-category link is workflow
  * dependent upon.
  *
  * @return collection of workflow dependencies
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getWorkflowDependencies()
    throws OculusException;

  /** Returns the collection of features that are workflow dependents of this
  * feature-category link.
  *
  * @return collection of workflow-dependent features
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getWorkflowDependents()
    throws OculusException;

  /** Returns whether or not this feature-category link has cons.
  *
  * @return true if it has cons, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasCons()
    throws OculusException;

  /** Returns whether or not this feature-category link has pros.
  *
  * @return true if it has pros, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasPros()
    throws OculusException;

  /** Returns whether or not the given feature-category link is a functional dependent
  * of this feature-category link.
  *
  * @param strComment the comment made about moving this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isFunctionalDependent(IFeatureCategoryLink featcatlink)
    throws OculusException;

  /** Returns whether or not the given feature-category link is a workflow dependent
  * of this feature-category link.
  *
  * @param strComment the comment made about moving this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isWorkflowDependent(IFeatureCategoryLink featcatlink)
    throws OculusException;

  /** Moves this feature-category link object to Accolades
  *
  * @param strComment the comment made about moving this feature
  * @param transIID the IIID of the transition to use to make the move
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink moveToAccolades(String strComment, IIID transIID)
    throws OculusException;

  /** Moves this feature-category link object to Compass.
  *
  * @param strComment the comment made about moving this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink moveToCompass(String strComment)
    throws OculusException;

  /** Moves this feature-category link object to the deferred spec view.
  *
  * @param strComment the comment made about moving this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink moveToDeferred(String strComment)
    throws OculusException;

  /** Removes a functional dependency between this object and the given parent
  * feature-category link.
  *
  * @param parent the parent feature-category link in the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeFunctionalDependency(IFeatureCategoryLink parent)
    throws OculusException;

  /** Removes a workflow dependency between this object and the given parent
  * feature-category link.
  *
  * @param parent the parent feature-category link in the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeWorkflowDependency(IFeatureCategoryLink parent)
    throws OculusException;

  /** Generates HTML in the given table to edit this object.
  *
  * @param table the HTML table in which to put the information
  * @param newRev true if the edit is creating a new revision, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void renderCustomEdit(IAttributeTable table, boolean newRev)
    throws OculusException;

  /** Sets the actual amount of time that development took in hours.
  *
  * @param duration number of hours of development
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setActualDevTime(int duration)
    throws OculusException;

  /** Sets category object for this feature-category link.
  *
  * @param feature the feature in this feature-category link
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setCategoryObject(ICategory original)
    throws OculusException;

  /** Sets the date that development ended.
  *
  * @param endDate end date of development
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setDevEndDate(Timestamp endDate)
    throws OculusException;

  /** Sets the date that development will start.
  *
  * @param startDate start date of development
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setDevStartDate(Timestamp startDate)
    throws OculusException;

  /** Sets the difficulty level of this feature-category link to the level for the given IIID.
  *
  * @param diffIID the IIID of the new priority level
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setDifficultyLevel(IIID diffIID)
    throws OculusException;

  /** Sets the estimated development time in hours.
  *
  * @param duration estimated development time in hours
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setEstimatedDevTime(int duration)
    throws OculusException;

  /** Sets the estimated test time in hours.
  *
  * @param duration estimated test time in hours
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setEstimatedTestTime(int duration)
    throws OculusException;

  /** Sets feature object for this feature-category link.
  *
  * @param feature the feature in this feature-category link
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IBusinessObject setFeatureObject(IFeature feature)
    throws OculusException;

  /** Sets order number of this feature-category link with respect to the other features in
  * this category.
  *
  * @param orderNum the order number of this feature-category link
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setOrderNum(int orderNum)
    throws OculusException;

  /** Sets the percent of development that is complete on this feature for this link.
  *
  * @param percentComplete the percent of development that is complete (0 - 100)
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setPercentComplete(int percentComplete)
    throws OculusException;

  /** Sets the feature revision object that this link is pinned to.
  *
  * @param rev the feature revision to pin to
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setPinnedFeatureRevisionObject(IFeatureRevision rev)
    throws OculusException;

  /** Sets the priority level of this feature-category link to the level for the given IIID.
  *
  * @param priorityIID the IIID of the new priority level
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setPriorityLevel(IIID priorityIID)
    throws OculusException;

  /** Sets the users for the single-user roles of this object to the users in the category.
  *
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setSingleRoles()
    throws OculusException;

  /** Sets the test end date of this feature-category link to the date given.
  *
  * @param endDate the end date of testing
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setTestEndDate(Timestamp endDate)
    throws OculusException;

  /** Sets the test level of this feature-category link to the level for the given IIID.
  *
  * @param testIID the IIID of the new test level
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink setTestLevel(IIID testIID)
    throws OculusException;
}
