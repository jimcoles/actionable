package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.ui.html.*;

/** This interface represents an independent feature.  It is intended to represent the
* free-standing entity, not the version specific one and the essentials, not the revisable
* stuff.  However, it also extends those other interfaces and so this interface acts
* as a union of all three entities.  This turned out to be a huge mistake, but it was
* too late to do anything about it.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IFeature.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    2/24/00     Removed the IRPropertyMap interface so clients are forced to use the getProperties method.
* ---             Saleem Shafi    3/9/00      Added isStandard() methods due to db change.
* ---             Saleem Shafi    3/13/00     Added getLatestFeatureRevisionObject() method.
*/

public interface IFeature extends IBusinessObject, IFeatureRevision, IFeatureCategoryLink
{
  /** This value should be used to get the dependencies property using IRPropertyMap.get(). */
	public static final String LABEL_DEPENDENCIES = "prop"+IDCONST.DEPENDENCIES.getIIDValue();
  /** This value should be used to get the engineering spec property using IRPropertyMap.get(). */
	public static final String LABEL_ENGRSPECATTACHED = "prop"+IDCONST.ENGRSPEC.getIIDValue();
  /** This value should be used to get the standard property using IRPropertyMap.get(). */
	public static final String LABEL_ISSTANDARD = "prop"+IDCONST.ISSTANDARD.getIIDValue();
  /** This value should be used to get the feature type property using IRPropertyMap.get(). */
	public static final String LABEL_FEATURETYPE = "prop"+IDCONST.FEAT_TYPE.getIIDValue();

  /** Creates a functional dependency from this feature to the given parent feature.
  *
  * @param parent the parent feature in the dependency
  * @return the semantic link representing the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink addFunctionalDependency(IFeature parent)
    throws OculusException;

  /** Creates a workflow dependency from this feature to the given parent feature.
  *
  * @param parent the parent feature in the dependency
  * @return the semantic link representing the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public ISemanticLink addWorkflowDependency(IFeature parent)
    throws OculusException;

  /** Associates the given market input with this feature.
  *
  * @return the newly associated market input
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IMarketInput associateInput(IMarketInput input)
    throws OculusException;

  /** Associates the given problem statement with this feature.
  *
  * @return the newly associated problem statement
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProblemStatement associateProblemStatement(IProblemStatement ps)
    throws OculusException;

  /** Creates a new feature as a branch of this feature.
  *
  * @return the newly created feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature createBranch()
    throws OculusException;

  /** Creates a new, exact copy of this feature.
  *
  * @return the newly created feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature createCopy()
    throws OculusException;

  /** Disassociates this feature with the given market input.
  *
  * @return the disassociated market input
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IMarketInput disAssociateInput(IMarketInput input)
    throws OculusException;

  /** Disassociates this feature with the given problem statement.
  *
  * @return the disassociated problem statement
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProblemStatement disAssociateProblemStatement(IProblemStatement ps)
    throws OculusException;

  /** Returns the collection of version that this feature is associated with.
  *
  * @return the collection of version that this feature is associated with
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProductVersionColl getAssociatedVersions()
    throws OculusException;

  /** Returns the collection of engineering spec files associated with this feature.
  *
  * @return the collection of engineering spec files associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFileColl getAttachedEngrSpecFiles()
    throws OculusException;

  /** Returns the collection of engineering spec URLs associated with this feature.
  *
  * @return the collection of engineering spec URLs associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IHyperLinkColl getAttachedEngrSpecHyperLinks()
    throws OculusException;

  /** Returns a collection of feature that have been branched from this feature.
  *
  * @return the collection of features that have been branched from this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureColl getChildBranches()
    throws OculusException;

  /** Returns the display name of this feature.  Note: It should probably be a function of the
  * name of the feature and the visible id.
  *
  * @return the display name of this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getDisplayName()
    throws OculusException;

  /** Returns the feature-category link object that this feature is currently using.
  *
  * @return the feature-category link object currently being used
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink getFeatureCategoryLinkObject()
    throws OculusException;

  /** Returns the feature-category link object between this feature and the given category.
  *
  * @param category the category that this feature is linked to
  * @return the feature-category link object connecting this feature and the given category
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink getFeatureCategoryLinkObject(ICategory category)
    throws OculusException;

  /** Returns the feature-category link object that this feature is currently using.
  *
  * @param editable true if the feature-category link should be locked
  * @return the feature-category link object currently being used
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureCategoryLink getFeatureCategoryLinkObject(boolean editable)
    throws OculusException;

  /** Returns the feature revision object that this feature is currently using.
  *
  * @return the feature revision object currently being used
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision getFeatureRevisionObject()
    throws OculusException;

  /** Returns the IIID of the feature type
  *
  * @return the IIID of the feature type
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IIID getFeatureTypeIID()
    throws OculusException;

  /** Returns the feature type as a String.
  *
  * @return String representation of the feature type
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFeatureTypeName()
    throws OculusException;

  /** Returns a collection of the inputs associated directly with this feature.
  *
  * @return a collection of inputs associated directly with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IMarketInputColl getInputs()
    throws OculusException;

  /** Returns the most current revision of this feature.
  *
  * @return the most current revision of this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeatureRevision getLatestFeatureRevisionObject()
    throws OculusException;

  /** Returns the maximum score of any scored input associated with this feature.
  *
  * @return the maximum score of any scored input associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getMaxScore()
    throws OculusException;

  /** Returns the average score of all scored inputs associated with this feature.
  *
  * @return the average score of all scored inputs associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public float getMeanScore()
    throws OculusException;

  /** Returns the median score of all scored inputs associated with this feature.
  *
  * @return the median score of all scored inputs associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public float getMedianScore()
    throws OculusException;

  /** Returns the minimum score of any scored input associated with this feature.
  *
  * @return the minimum score of any scored input associated with this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getMinScore()
    throws OculusException;

  /** Returns the product that owns this feature.  Note: This method is not being used.
  *
  * @return the owning product
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProduct getOwningProductObject()
    throws OculusException;

  /** Returns the feature that this feature was branched from.
  *
  * @return the parent feature branch
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature getParentBranch()
    throws OculusException;

  /** Returns the problem statements that are associated with this feature.
  *
  * @return a collection of problem statements
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IProblemStatementColl getProblemStatements()
    throws OculusException;

  /** Returns the standard-feature link object that this feature object is using.
  *
  * @return the standard-feature link object this feature is using
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStdFeatureLink getStandardFeatureLinkObject()
    throws OculusException;

  /** Returns the standard-feature link object that this feature object is using.
  *
  * @param editable true if the standard-feature link should be locked, false otherwise
  * @return the standard-feature link object this feature is using
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IStdFeatureLink getStandardFeatureLinkObject(boolean editable)
    throws OculusException;

  /** Returns the total score for this feature.  This is not an average, but a total.
  *
  * @return the visible id for this feature
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getTotalScore()
    throws OculusException;

  /** Returns the visible id for this feature.
  *
  * @return the visible id for this feature
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public long getVisibleID()
    throws ORIOException;

  /** Returns whether or not this feature is associated with any versions.
  *
  * @return true if the feature has associations with versions, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasAssociatedVersions()
    throws OculusException;

  /** Returns whether or not this feature has dependencies.
  *
  * @return true if the feature has standard-feature links, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasDependencies()
    throws OculusException;

  /** Returns whether or not this feature has engineering specifications.
  *
  * @return true if the feature has engineering specifications, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasEngrSpecAttached()
    throws OculusException;

  /** Returns whether or not this feature has feature-category links.  
  *
  * @return true if the feature has feature-category links, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasFeatureCategoryLinks()
    throws OculusException;

  /** Returns whether or not this feature has standard-feature links.  
  *
  * @return true if the feature has standard-feature links, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasStdFeatureLinks()
    throws OculusException;

  /** Returns whether or not the given feature is a Standard Feature.  A Standard Feature is 
  * a feature that associated with a Standards Collection.
  *
  * @return true if the feature is a standard, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isStandard()
    throws OculusException;

  /** Sets whether or not this feature is a Standard Feature.  A Standard Feature is a feature
  * that associated with a Standards Collection.
  *
  * @param isstandard true if this feature is a standard, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature isStandard(boolean isstandard)
    throws ORIOException;

  /** Returns whether or not the given feature is a functional dependent of this feature.
  *
  * @return true if the feature is functionally dependent, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isFunctionalDependent(IFeature child)
    throws OculusException;

  /** Returns whether or not the given feature is a workflow dependent of this feature.
  *
  * @return true if the feature is workflow dependent, false otherwise
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean isWorkflowDependent(IFeature child)
    throws OculusException;

  /** Returns the number of inputs that are associated with this feature.
  *
  * @return the number of inputs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int numInputs()
    throws OculusException;

  /** Returns the number of problem statements that are associated with this feature.
  *
  * @return the number of problem statements
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int numProblemStatements()
    throws OculusException;

  /** Returns the number of inputs that are associated with this feature and are scored.
  *
  * @return the number of scored inputs
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int numScoredInputs()
    throws OculusException;

  /** Removes a functional dependency between this object and the given parent feature.
  *
  * @param parent the parent feature in the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeFunctionalDependency(IFeature parent)
    throws OculusException;

  /** Removes a workflow dependency between this object and the given parent feature.
  *
  * @param parent the parent feature in the dependency
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void removeWorkflowDependency(IFeature parent)
    throws OculusException;

  /** Sets whether or not this feature has a dependency.
  *
  * @param attach true if this feature has a dependency
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature setDependencies(boolean dependencies)
    throws ORIOException;

  /** Sets whether or not this feature has an engineering specification attached.
  *
  * @param attach true if this feature has an engineering spec, false otherwise
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature setEngrSpecAttached(boolean attach)
    throws ORIOException;

  /** Sets the feature-category link that this feature should currently use.  Note: This value does
  * not persist, either to the database or in the cache, so its specific to the user.
  *
  * @param catlink the feature-category link to use
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature setFeatureCategoryLinkObject(IFeatureCategoryLink catlink)
    throws OculusException;

  /** Sets the feature revision that this feature should currently use.  Note: This value does
  * not persist, either to the database or in the cache, so its specific to the user.
  *
  * @param revision the IFeatureRevision to use
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature setFeatureRevisionObject(IFeatureRevision revision)
    throws ORIOException;

  /** Sets the type of this feature to the type for the given IIID.  Feature Type is an
  * enumerated property and the IIID being passed in the particular value in that enumeration.
  *
  * @param typeID the IIID of the type of this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature setFeatureType(IIID typeID)
    throws OculusException;

  /** Sets the product that owns this feature.  Note: This method is not being used.
  *
  * @param product the owning IProduct object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IFeature setOwningProductObject(IProduct product)
    throws ORIOException;

  /** Sets the standards-feature link object for this feature.  This method tells the
  * feature about a link to a Standards Collection.
  *
  * @param id the visible id for this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature setStandardFeatureLinkObject(IStdFeatureLink stdfeatlink)
    throws OculusException;

  /** Sets the visible id of this feature.  The IIID value for this object is
  * too "ugly" for the user to have to see and remember, so this id should be 
  * used for the user.  It needs to be unique for all features.
  *
  * @param id the visible id for this feature
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFeature setVisibleID(long id)
    throws ORIOException;
}
