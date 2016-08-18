package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.ui.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Category.java
* Date:        2/14/00
* Description: Provides the functionality for a basic version for a category.
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
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
* BUG00074        Saleem Shafi    5/18/00     Added a createCopyFor() method that creates a copy for a different version.
* BUG00992				Saleem Shafi		6/13/00			Limited category indexing to categories, not baseline categories.
*/

public class Category extends BusinessObject implements ICategory
{
  static public long CLASSID = Long.parseLong("62397249695985973");

  protected String COL_VERSIONID = "VERSIONID";
  protected String COL_PARENTCATID = "PARENTCATID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_VISIBLEID = "VISIBLEID";

  protected static long _nextid = -1;
  protected long _id;
  protected IIID _versionIID;
  protected IIID _parentIID;
  private IIID _OLDPARENTIID = null;
  protected IRProperty _orderNum;                // integer type

/********************************************
			
**************************/
	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public Category() throws OculusException
	{
	super();
	TABLE = "CATEGORY";
	COL_GUID = "GUID";
	_orderNum = new BMProperty(this);
  _orderNum.setDefnObject(IDCONST.ORDERNUM.getIIDValue());
	}
  
  public IFeature associateFeature(IFeature feature)
	throws OculusException
  {
    IFeatureCategoryLink catLink = null;
    IIID classIID = IDCONST.FEATURECATEGORYLINK.getIIDValue();
    
    IFeatureCategoryLink oldLink = feature.getFeatureCategoryLinkObject();
    if (oldLink == null)
    {
      catLink = (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",(IDataSet)null,true);
      catLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
      catLink.setFeatureObject(feature);
    }
    else    
    {
      if (oldLink.getPersState().equals(PersState.NEW))
        catLink = oldLink;
      else
        catLink = oldLink.createLinkCopy();
    }
      
    catLink.setCategoryObject(this);
    feature.setFeatureCategoryLinkObject(catLink);
    
    return feature;
  }  


  public IFeature disAssociateFeature(IFeature feature)
    throws OculusException
  {
    IFeatureCategoryLink catLink = feature.getFeatureCategoryLinkObject();
   
    ISpecSignOffColl signoffcoll = catLink.getSpecSignOffs(true);
    if (signoffcoll != null)
    { 
      while(signoffcoll.hasMoreSpecSignOffs())
      {
         signoffcoll.nextSpecSignOff().softDelete();
      }
    }
  /*
    IAlternativeColl alternativecoll = catLink.getAlternatives(true);
   
    if (alternativecoll != null)
    {
      while(alternativecoll.hasMoreAlternatives())
      {
        alternativecoll.nextAlternative().softDelete();
      }
    }
    */
    catLink = feature.getFeatureCategoryLinkObject(true);
    System.out.println ("catLinkid =" + catLink.getIID()); 
    
    catLink.softDelete();
    return feature;
  }
  

  public void pinAllFeatureLinks()
    throws OculusException
  {
    ICategoryColl cats = getCategories();
    while (cats.hasMoreCategories())
      cats.nextCategory().pinAllFeatureLinks();

    IFeatureColl feats = getFeatures(true);
    while (feats.hasMoreFeatures())
    {
      IFeature feat = feats.nextFeature();
      IFeatureCategoryLink link = feat.getFeatureCategoryLinkObject();
      if (link.getPinnedFeatureRevisionObject() == null)
        link.setPinnedFeatureRevisionObject(feat.getFeatureRevisionObject());
    }
  }

  synchronized protected long getNextVisibleID()
    throws OculusException
  {
    if (_nextid == -1)
    {
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      IDataSet rs = conn.createProcessor().retrieve("SELECT MAX(VISIBLEID) AS MAXVISIBLEID FROM CATEGORY");
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

  public ICategory setVisibleID(long id)
  {
    _id = id;
    return this;
  }


  public ICategory moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException
  {
    IRState state = getStateObject();
    if (!state.getIID().equals(IDCONST.CATEGORYACTIVE.getIIDValue()))
    {
      interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.CATEGORYACTIVE.getIIDValue()),strComment);
    }//end if  
    if (recurse)
    {
      ICategoryColl cats = getCategories(true);
      while (cats.hasMoreCategories())
        cats.nextCategory().moveToAccolades(strComment,true,transIID);
      IFeatureColl feats = getFeatures(true);
      while (feats.hasMoreFeatures())
        feats.nextFeature().moveToAccolades(strComment,transIID);
    }//end if
    if(getParentObject() instanceof ICategory)
    {
      ICategory cat = (ICategory)getParentObject(true);
      cat.moveToAccolades(strComment,false,transIID);
    }//end if
    getVersionObject(true).moveToAccolades(strComment,false,transIID);
    return this;
  }
  
  public int numberFeaturesInState(IIID stateiid) throws OculusException
  { return numberFeaturesState(stateiid,false); }
  
  public int numberFeaturesNotInState(IIID stateiid) throws OculusException
  { return numberFeaturesState(stateiid,true); }

  private int numberFeaturesState(IIID stateiid, boolean not) throws OculusException
  {
    int intRV = 0;
    IQueryProcessor qp = null;
    try
    {
      IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
      qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT COUNT(cat.OBJECTID) AS cnt FROM CATEGORY cat "+
                                "LEFT OUTER JOIN CATFEATURELINK featcat ON featcat.CATEGORYID = cat.OBJECTID "+
                                "WHERE cat.OBJECTID = "+getIID()+
                                " AND featcat.STATEID "+(not?"<> ":"= ")+stateiid);
      if(rs.next())
        intRV = rs.getInt("cnt");
    }//end try
    finally {if(qp!=null)qp.close();}  
    return intRV;
  }
   
  public ICategory moveToCompass(String strComment, boolean recurse)
    throws OculusException
  {
    IRState state = getStateObject();
    if (recurse)
    {
      IFeatureColl feats = getFeatures(true);
      while (feats.hasMoreFeatures())
        feats.nextFeature().moveToCompass(strComment);
      ICategoryColl cats = getCategories(true);
      while (cats.hasMoreCategories())
        cats.nextCategory().moveToCompass(strComment,true);
    }//end if
    
    if (state.getIID().equals(IDCONST.CATEGORYACTIVE.getIIDValue()) && numberFeaturesNotInState(IDCONST.COMPASS.getIIDValue()) <= 1)
    {
      interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.CATEGORYCOMPASS.getIIDValue()),strComment);
//      // dunno if this is right
//      IProductVersion ver = getVersionObject(true);
//      if(!ver.getStateObject().getIID().equals(IDCONST.VERSIONCOMPASS.getIIDValue()))
//        ver.moveToCompass(strComment,false);
    }//end if  
    
    return this;
  }
  
  public boolean isDefaultCategory()
    throws OculusException
  {  return (_parentIID.equals(getIID())); }
  
//----------------- IPoolable Methods ------------------------------------
	/**
	*	Returns a copy of the current product object.
	*
	* Note: The exceptions are being withheld because this method overrides
	* the one in Object().  Consider using a different method name since it
	* doesn't look like we're taking advantage of Cloneable.
	*/
	public Object dolly() throws OculusException
	{
		Category cat = null;
			cat = new Category();
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
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
		cat.putAll(getProperties());

    cat.setVisibleID(getVisibleID());
	  cat._versionIID = _versionIID;
	  cat._parentIID = _parentIID;
	  cat.setOrderNum(getOrderNum());
		return cat;
	}
  
  public ICategory createCopy()
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.CATEGORY.getIIDValue();
    ICategory newCategory = (ICategory)context.getCRM().getCompObject(context,"Category",(IDataSet)null,true);
    IRClass categoryClass = (IRClass)context.getCRM().getCompObject(context,"Class",classIID);
    newCategory.setDefnObject(categoryClass);
    
    newCategory.setStateObject(getStateObject());
    newCategory.setName(getName());
    newCategory.setDescription(getDescription());
    newCategory.setAccessIID(getAccessIID());
    newCategory.setOrderNum(getOrderNum());
    IProductVersion thisVer = getVersionObject();
    newCategory.setVersionObject(thisVer);   

    ICategoryColl subcats = getCategories();
    while (subcats.hasMoreCategories())
    {
      ICategory nextCat = subcats.nextCategory();
      ICategory subcat = nextCat.createCopy();
      subcat.setParentObject(newCategory);

      subcat.copyAttachmentsOf(nextCat);
      subcat.copyHyperLinksOf(nextCat);
      subcat.copyDiscussionTopicsOf(nextCat);
    }
    IFeatureColl feats = getFeatures();
    while (feats.hasMoreFeatures())
    {
      IFeature feat = feats.nextFeature();
      newCategory.associateFeature(feat);
    }
    return newCategory;
  }
  
  public ICategory createCopyFor(IProductVersion ver)
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.CATEGORY.getIIDValue();
    ICategory newCategory = (ICategory)context.getCRM().getCompObject(context,"Category",(IDataSet)null,true);
    IRClass categoryClass = (IRClass)context.getCRM().getCompObject(context,"Class",classIID);
    newCategory.setDefnObject(categoryClass);
    
    newCategory.setStateObject(getStateObject());
    newCategory.setName(getName());
    newCategory.setDescription(getDescription());
    newCategory.setAccessIID(getAccessIID());
    newCategory.setMessageAttached(hasMessageAttached());
    newCategory.setLinkAttached(hasLinkAttached());
    newCategory.setFileAttached(hasFileAttached());
    newCategory.setOrderNum(getOrderNum());
    newCategory.setVersionObject(ver);

    newCategory.copyAttachmentsOf(this);
    newCategory.copyHyperLinksOf(this);
    newCategory.copyDiscussionTopicsOf(this);
   
    ICategoryColl subcats = getCategories();
    while (subcats.hasMoreCategories())
    {
      ICategory subcat = subcats.nextCategory().createCopyFor(ver);
      subcat.setParentObject(newCategory);
      subcat.setVersionObject(ver);
    }
    IFeatureColl feats = getFeatures();
    while (feats.hasMoreFeatures())
    {
      IFeature feat = feats.nextFeature();
      newCategory.associateFeature(feat);
    }
    return newCategory;
  }

  
  
  public ICategory createNewCategory()
	throws OculusException
  {
	ICategory newCat;
	IIID classIID = IDCONST.CATEGORY.getIIDValue();

	newCat = (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",(IDataSet)null,true);
	newCat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
	newCat.setParentObject(this);
	newCat.setVersionObject(getVersionObject());
	newCat.setSingleRoles();
	return newCat;
  }  
  public IFeature createNewFeature()
	throws OculusException
  {
	IFeature newFeat;
	IIID classIID = IDCONST.FEATURE.getIIDValue();

	newFeat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",(IDataSet)null,true);
	newFeat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
	associateFeature(newFeat);

//    IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
//    IQueryProcessor qp = conn.createProcessor();
//    qp.update("INSERT INTO CATFEATURELINK "+
//              "(CATEGORYID, FEATUREID, ORDERNUM) "+
//              "VALUES "+
//              "("+getIID().getLongValue()+", "+newFeat.getIID().getLongValue()+", "+1+") ");
//    qp.close();
//    getObjectContext().getCRM().returnDatabaseConnection(conn);

	return newFeat;
  }  
//----------------- IPersistable Methods ------------------------------------

  /**
  * Marks the bo as deleted.
  */
  public IPersistable delete()
	throws OculusException
  {
	
	  IFeatureList deletedfeats = (IFeatureList)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryFeatureDeletedList", getIID(), true);
	  while (deletedfeats.hasMoreFeatures())
	  {
		  IFeature feat = deletedfeats.nextFeature();
		  IFeatureCategoryLink featcat = feat.getFeatureCategoryLinkObject(true);
			IFeatureRevision rev = feat.getFeatureRevisionObject();
	    featcat.delete();
			if(!feat.hasFeatureCategoryLinks())
		    feat.delete();
	  }//end while
	
	  ICategoryList deletedcats = (ICategoryList)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryDeletedList", getIID(), true);
	  while (deletedcats.hasMoreCategories())
	    deletedcats.nextCategory().delete();

   	ChildIndexer.removeChild(ChildIndexer.CATEGORY,getObjectContext(),_parentIID.getLongValue(),getIID().getLongValue());

	
	  super.delete();	
	
	  return this;
  }  


  public IBusinessObject softDelete()
    throws OculusException
  { 
    
		
		IFeatureColl features = getFeatures(true);
		while (features.hasNext())
	    features.nextFeature().getFeatureCategoryLinkObject(true).softDelete();
    
    ICategoryColl categories = getCategories(true);
    while (categories.hasMoreCategories())
      categories.nextCategory().softDelete();
		
		super.softDelete();	
  
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    
    IFeatureList features = (IFeatureList)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryFeatureDeletedList",getIID(), true);
    while (features.hasNext())
      features.nextFeature().getFeatureCategoryLinkObject(true).recover();
		
    ICategoryList categories = (ICategoryList)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryDeletedList",getIID(),true);
    while (categories.hasMoreCategories())
      categories.nextCategory().recover();
  
    return this;
  }




//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_ORDERNUM))
		return _orderNum;
	  else
		return super.get(key);
	}
	else
	  return null;
  }  
//----------------- ICategory Methods ------------------------------------
	/** I don't know what this does */
  public ISet getBranches()
 	{
 		return null;
	}
  public ICategoryColl getCategories()
	throws OculusException
  {
	return getCategories(false);
  }  
  public ICategoryColl getCategories(boolean editable)
	throws OculusException
  {
    return (ICategoryColl)getChildCollection(editable);
  }  
  synchronized protected String getCreateQuery()
	throws OculusException
  {
    setVisibleID(getNextVisibleID());
	return "INSERT INTO "+TABLE+" "+
		   " ("+COL_OBJECTID+", "
			   +COL_CLASSID+", "
			   +COL_STATEID+", "
         +COL_DELETESTATE+", "
			   +COL_GUID+", "
			   +COL_NAME+", "
			   +COL_VERSIONID+", "
			   +COL_ORDERNUM+", "
         +COL_VISIBLEID+", "
			   +COL_CREATIONDATE+", "
			   +COL_CREATORID+", "
			   +COL_ACCESSID+", "
			   +COL_PARENTCATID+", "
			   +COL_MESSAGEATTACHED+", "
			   +COL_FILEATTACHED+", "
			   +COL_LINKATTACHED+" "+
		   ") "+
		   " VALUES "+
		   " ("+getIID().getLongValue()+","
			  +getDefnObject().getIID().getLongValue()+","
			  +getStateObject().getIID().getLongValue()+","
        +getDeleteState().getIntValue()+","  
			  +"'"+getGUID().toString()+"',"
			  +"'"+SQLUtil.primer(getName())+"',"
			  +getVersionObject().getIID().getLongValue()+","
			  +getOrderNum()+","
        +getVisibleID()+","
			  +"'"+getCreationDate().toString()+"',"
			  +getCreatorIID().getLongValue()+","
			  +getAccessIID().getLongValue()+","
			  +_parentIID.getLongValue()+","
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
  public IFeatureColl getFeatures(IDataSet args)
    throws OculusException
  {
    return getFeatures(args, false);
  }
  public IFeatureColl getFeatures(boolean editable)
	throws OculusException
  {
    IDataSet args = new DataSet();
    return getFeatures(args,editable);
  }  
  public IFeatureColl getFeatures(IDataSet args, boolean editable)
    throws OculusException
  {
    args.setIID(getIID());
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryFeatureList",args, editable);
  }



  protected String getLoadQuery()
	throws ORIOException
  {
	return "SELECT * "+
		   "FROM "+TABLE+" "+
		   "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public int getOrderNum()
	throws OculusException
  {
	if (_orderNum.getValue() != null)
	  return ((Integer)_orderNum.getValue()).intValue();
	else
	  return 0;
  }  
  public IRObject getParentObject(boolean editable)
	throws OculusException
  {
	if (isDefaultCategory())
	  return getVersionObject(editable);
	else
	  return (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",_parentIID,editable);
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
		   "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
		   " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		   " , "+COL_ORDERNUM+"= "+getOrderNum()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
		   " , "+COL_VERSIONID+"="+getVersionObject().getIID().getLongValue()+" "+
		   " , "+COL_PARENTCATID+"="+_parentIID.getLongValue()+" "+
		   " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		   " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		   " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  public IBusinessObject addToWorkforce(IIID useriid,int ordernum)
    throws OculusException
  {
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getVersionObject().getIID());
    ra.setRoleIID(IDCONST.VERSIONTEAMROLE.getIIDValue());
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
    return this;
  }//
  
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum, boolean recurse)
    throws OculusException
  {
    addToWorkforce(useriid,ordernum);
    
    IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(),"Role",roleiid);
    if(role != null && !role.isMultiUser())
    {
      IDataSet args = new DataSet();
      args.setIID(getIID());
      args.put("roleiid",roleiid);
      IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args,true);
      while(rac.hasNext())
			  rac.nextRoleAssignment().delete();
//          removeFromRole(rac.nextRoleAssignment().getUserObject().getIID(),roleiid);
      if (recurse)
	    {
		    ICategoryColl cats = getCategories();
        while (cats.hasMoreCategories())
          cats.nextCategory().addToRole(useriid,roleiid,ordernum,recurse);
				IFeatureColl feats = getFeatures();
				while	(feats.hasNext())
				  feats.nextFeature().addToRole(useriid,roleiid,ordernum);
	    }//end if
		}//end if
    
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(roleiid);
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
    return this;
  }//
	
	public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException
  {
	  return addToRole(useriid,roleiid,ordernum,false);
  }
  
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
		ICategoryColl cats = getCategories();
    while (cats.hasMoreCategories())
      cats.nextCategory().removeFromWorkforce(useriid);
    IFeatureColl fc = getFeatures();
    while(fc.hasNext())
      fc.nextFeature().getFeatureCategoryLinkObject().removeFromWorkforce(useriid); 
    return this;
  }
  
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid, boolean recurse)
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
		if (recurse)
		{
		  ICategoryColl cats = getCategories();
      while (cats.hasMoreCategories())
        cats.nextCategory().removeFromRole(useriid,roleiid,recurse);
      IFeatureColl fc = getFeatures();
      while(fc.hasNext())
        fc.nextFeature().getFeatureCategoryLinkObject().removeFromRole(useriid,roleiid); 
		}//end if
		return this;
  }
	
	public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException
  {
	  return removeFromRole(useriid,roleiid,false);
  } 
  
  //------------------------ ACCESSORS -------------------------------
  
  public String getDisplayName()
    throws OculusException
  { return getName()+" [ID:"+getVisibleID()+"]"; }
  
  public ICategory setSingleRoles()
    throws OculusException
  {
	  IIID pariid = getParentObject().getIID();
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
    
  public IProductVersion getVersionObject()
    throws OculusException
  { return getVersionObject(false); }
  
  public IProductVersion getVersionObject(boolean edit)
	  throws OculusException
  {
	  return (IProductVersion)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersion",_versionIID,edit);
  }  
  protected void load(IDataSet results)
	throws OculusException
  {
	if (results.getIID() == null)
	  results.setIID(results.getLong(COL_OBJECTID));
	super.load(results);
		
  if (COL_VERSIONID != null)
    _versionIID = new SequentialIID(results.getLong(COL_VERSIONID));
	setName(results.getString(COL_NAME));
	setDescription(results.getString(COL_DESCRIPTION));
	_parentIID = new SequentialIID(results.getLong(COL_PARENTCATID));
  setVisibleID(results.getLong(COL_VISIBLEID));
	setOrderNum(results.getInt(COL_ORDERNUM));
  }  
  
  
  
 public ISpecSignOffColl getSpecSignOffs()
    throws OculusException
 {
   ISpecSignOffColl signoffcoll = (ISpecSignOffColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SpecSignOffColl",getIID(),true);
   return signoffcoll;
 }
 
 public IAlternativeColl getAlternatives()
    throws OculusException
 {
   IAlternativeColl alternativecoll = (IAlternativeColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlternativeColl",getIID(),true);
   return alternativecoll;
 }
    

  
  
  
  public String getFullTreePathString()
    throws OculusException
  {
    if (isDefaultCategory())
      return getVersionObject().getFullTreePathString();
    else
      return ((ICategory)getParentObject()).getFullTreePathString()+"/"+getName();
  }//
  
  
  
  
  
//  public String getFullTreePathString()
//    throws OculusException
//  {
//    IProductVersion ver = getVersionObject();
//    IProduct prod = ver.getProductObject();
//    ICategory cat = this;
//    String strPath = prod.getName() + "/" + ver.getName();
//    String strTemp = "";
//    while(!cat.isDefaultCategory())
//    {
//      strTemp = "/"+cat.getName() + strTemp;
//      cat = (ICategory)cat.getParentObject();
//    }//end while
//    strPath += strTemp;
//    return strPath;
//  }//
  
  //-------------------------- Protected Methods -----------------------------
  protected PSPKind needsPreparedStatement()
  {
	return PSPKind.STRING;
  }
	
  public void put(Object key, Object value)
	throws OculusException
  {
	if (key instanceof String && value instanceof IRProperty)
	{
	  IRProperty property = (IRProperty)value;
	  if (key.equals(LABEL_ORDERNUM))
		setOrderNum(((Integer)property.getValue()).intValue());
	  else
		super.put(key,value);
	}
  }  
  public ICategory setOrderNum(int order)
	throws ORIOException
  {
	_orderNum.setValue(new Integer(order));
	return this;
  }  
  public ICategory setParentObject(IBusinessObject parent)
	throws ORIOException
  {
	if (getPersState().equals(PersState.UNMODIFIED))
	  setPersState(PersState.MODIFIED);
	if (_parentIID != null && parent.getIID().getLongValue() != _parentIID.getLongValue())
		_OLDPARENTIID = _parentIID;
		
  _parentIID = parent.getIID();
	return this;
  }  
  
  public IPersistable save()
    throws OculusException
  {
    if (getPersState().equals(PersState.NEW) && getOrderNum()==0)
    {
      ICategory parentCat = null;
      IRObject parent = getParentObject();
      if (parent instanceof IProductVersion)
        parentCat = ((IProductVersion)parent).getDefaultCategory();
      else
        parentCat = (ICategory)parent;
      if (parentCat != null)
      {
        ICategoryColl cats = parentCat.getCategories();
        setOrderNum(cats.size()+1);
      }
    }
    
		boolean noo = getPersState().equals(PersState.NEW);
		boolean mod = getPersState().equals(PersState.MODIFIED) && _OLDPARENTIID != null;
		
		super.save();

    if (noo && !(this instanceof IBaselineCategory))
    	ChildIndexer.addChild(ChildIndexer.CATEGORY,getObjectContext(),_parentIID.getLongValue(),getIID().getLongValue());
  	if (mod && !(this instanceof IBaselineCategory))
		{
   		ChildIndexer.moveChild(ChildIndexer.CATEGORY,getObjectContext(),_OLDPARENTIID.getLongValue(),_parentIID.getLongValue(),getIID().getLongValue());
			_OLDPARENTIID = null;
		}
			
		return this;
  }
  
  //------------------------ MUTATORS -------------------------------
  public ICategory setVersionObject(IProductVersion version)
	throws OculusException
  {
	if (getPersState().equals(PersState.UNMODIFIED))
	  setPersState(PersState.MODIFIED);
	_versionIID = version.getIID();
  ICategoryColl subcats = getCategories(true);
  while (subcats.hasMoreCategories())
    {
      subcats.nextCategory().setVersionObject(version);
    }
	return this;
  }  
  
  
  public IRCollection getChildCollection(boolean editable)
    throws OculusException
  {
    return (ICategoryColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryList",getIID(), editable);
  }
  
  public ICategory setEngineeringManager(IUser engmgr)
    throws OculusException
  { return setEngineeringManager(engmgr.getIID()); }
  
  public ICategory setEngineeringManager(IIID engmgr)
    throws OculusException
  { 
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(IDCONST.ENGMGRROLE.getIIDValue());
    ra.setUserIID(engmgr);
    ra.setOrderNum(0);
    return this;
  }//
  
  public ICategory setMarketingManager(IUser mktmgr)
    throws OculusException
  { return setMarketingManager(mktmgr.getIID()); }
  
  public ICategory setMarketingManager(IIID mktmgr)
    throws OculusException
  { 
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(IDCONST.MKTMGRROLE.getIIDValue());
    ra.setUserIID(mktmgr);
    ra.setOrderNum(0);
    return this;
  }


  public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());

    IGrantSet gs = acm.checkPerms(this, new IPermission[] {PermEnum.OWNER});
    IGrantSet gsVer = acm.checkPerms(this.getVersionObject(),new IPermission[]
      {PermEnum.SPEC_EDIT, PermEnum.SPEC_VIEW, PermEnum.OWNER});
 
    gs.addAll(gsVer);

    return gs;
  }

  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    
    IIID stateIID = getStateObject().getIID();
    if ((module.equals(ModuleKind.ACCOLADES) && !stateIID.equals(IDCONST.CATEGORYCOMPASS.getIIDValue()))
        || (module.equals(ModuleKind.COMPASS)))
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkPerms(this, new IPermission[] {PermEnum.OWNER});
        IGrantSet gsVer = acm.checkPerms(this.getVersionObject(),new IPermission[]
          {PermEnum.SPEC_EDIT, PermEnum.SPEC_VIEW, PermEnum.OWNER});
        grant.addAll(gsVer);
      }
      
      if (grant.contains(PermEnum.SPEC_VIEW))
        visible = true;
    }
    return visible;
  }



}