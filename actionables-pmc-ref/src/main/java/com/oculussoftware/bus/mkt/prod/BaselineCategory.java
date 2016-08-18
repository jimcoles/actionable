package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    BaselineCategory.java
* Date:        2/24/00
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
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
* BUG00059        Saleem Shafi    5/16/00     Added VisibleID information.
* BUG00489				Saleem Shafi		5/30/00			Overloaded getParentObject(boolean) method so i don't try to get the version object.
*/

public class BaselineCategory extends Category implements IBaselineCategory
{
  protected String COL_BASELINEID = "BASELINEID";

  protected IIID _baselineIID;

	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public BaselineCategory() throws OculusException
	{
    super();
    TABLE = "BCATEGORY";
    COL_VERSIONID = null;
	}

  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
    throws ORIOException
  {
    return "SELECT * "+
           "FROM "+TABLE+" "+
           "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
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
         " , "+COL_BASELINEID+"="+getVersionBaselineObject().getIID().getLongValue()+" "+
         " , "+COL_PARENTCATID+"="+getParentObject().getIID().getLongValue()+" "+
         " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
         " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
         " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
         " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected String getCreateQuery()
    throws OculusException
  {
  return "INSERT INTO "+TABLE+" "+
         " ("+COL_OBJECTID+", "
           +COL_CLASSID+", "
           +COL_STATEID+", "
           +COL_DELETESTATE+", "
           +COL_GUID+", "
           +COL_NAME+", "
           +COL_VISIBLEID+", "
           +COL_BASELINEID+", "
           +COL_ORDERNUM+", "
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
          +"'"+SQLUtil.primer(getGUID().toString())+"',"
          +"'"+SQLUtil.primer(getName())+"',"
          +getVisibleID()+","
          +getVersionBaselineObject().getIID().getLongValue()+","
          +getOrderNum()+","
          +"'"+getCreationDate().toString()+"',"
          +getCreatorIID().getLongValue()+","
          +getAccessIID().getLongValue()+","
          +(isDefaultCategory()?getIID().getLongValue():getParentObject().getIID().getLongValue())+","
          +(hasMessageAttached()?"1":"0")+","
          +(hasFileAttached()?"1":"0")+","
          +(hasLinkAttached()?"1":"0")+""+
         ") ";
  } 

  protected void load(IDataSet results)
    throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));
    super.load(results);
        
    _baselineIID = new SequentialIID(results.getLong(COL_BASELINEID));
  }

  public String getFullTreePathString()
    throws OculusException
  {
    IVersionBaseline base = getVersionBaselineObject();
    IProductVersion ver = base.getProductVersionObject();
    IProduct prod = ver.getProductObject();
    ICategory cat = this;
    String strPath = prod.getName() + "/" + ver.getName() + "/"+ base.getName();
    String strTemp = "";
    while(!cat.isDefaultCategory())
    {
      strTemp = "/"+cat.getName() + strTemp;
      cat = (ICategory)cat.getParentObject();
    }//end while
    strPath += strTemp;
    return strPath;
  }//


//----------------- ICategory Methods ------------------------------------
  public ICategory createNewCategory()
    throws OculusException
  {
    IIID classIID = IDCONST.BASELINECATEGORY.getIIDValue();
    IBaselineCategory newBaseCat = null;
    IRClass baseCatClass = null;
    
    baseCatClass = (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID);
    newBaseCat = (IBaselineCategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategory",(IDataSet)null,true);
    newBaseCat.setDefnObject(baseCatClass);
    
    newBaseCat.setVersionBaselineObject(getVersionBaselineObject());
    newBaseCat.setParentObject(this);
    
    return newBaseCat;
  }

  public void define(ICategory category)
    throws OculusException
  {
    if (_baselineIID == null)
      throw new OculusException("Attempt to define a baseline without setting the productversion");
    setOrderNum(category.getOrderNum());
    setVisibleID(category.getVisibleID());
    setMessageAttached(category.hasMessageAttached());
    setFileAttached(category.hasFileAttached());
    setLinkAttached(category.hasLinkAttached());
    setName(category.getName());
    setDescription(category.getDescription());
    
    IFeatureColl featList = category.getFeatures();
    while (featList.hasMoreFeatures())
      associateFeature(featList.nextFeature());
            
    ICategoryColl catList = category.getCategories();
    while (catList.hasMoreCategories())
    {
      IBaselineCategory baseCat = (IBaselineCategory)createNewCategory();
      baseCat.define(catList.nextCategory());
    }
  }
  
  public IFeature associateFeature(IFeature feature)
    throws OculusException
  {
    IBaselineFeatureCategoryLink catLink = null;
    IIID classIID = IDCONST.BASELINEFEATURECATEGORYLINK.getIIDValue();
    
    catLink = (IBaselineFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineFeatureCategoryLink",(IDataSet)null,true);
    catLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    catLink.define(feature.getFeatureCategoryLinkObject());
    catLink.setCategoryObject(this);
    
    return feature;
  }  

  //------------------------ MUTATORS -------------------------------
  public IBaselineCategory setVersionBaselineObject(IVersionBaseline baseline)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _baselineIID = baseline.getIID();
    return this;
  }
    
  //------------------------ ACCESSORS -------------------------------
  public IVersionBaseline getVersionBaselineObject()
    throws OculusException
  {
    return (IVersionBaseline)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductBaseline",_baselineIID);
  }

	public IRObject getParentObject()
		throws OculusException
  {
		return getParentObject(false);
  }

  public IRObject getParentObject(boolean editable)
    throws OculusException
  {
    if (_baselineIID.getLongValue() == _parentIID.getLongValue())
      return (IVersionBaseline)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductBaseline",_parentIID);
    else
      return (IBaselineCategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategory",_parentIID);
  }

  public ICategoryColl getCategories(boolean editable)
    throws OculusException
  {
    return (ICategoryColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategoryColl",getIID(), editable);
  }

  public IFeatureColl getFeatures(IDataSet args, boolean editable)
    throws OculusException
  {
    args.setIID(getIID());
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategoryFeatureColl",args, editable);
  }

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
    BaselineCategory basecat = null;
      basecat = new BaselineCategory();
      basecat.setIID(getIID());
      basecat.setObjectContext(getObjectContext());
      basecat.setPersState(getPersState());
      basecat._classIID = _classIID;
      basecat._stateIID = _stateIID;
      basecat.setDeleteState(getDeleteState());
      basecat.setName(getName());
      basecat.setDescription(getDescription());
      basecat._creatorIID = _creatorIID;
      basecat._accessIID = _accessIID;
      basecat.setCreationDate(getCreationDate());
      basecat.setMessageAttached(hasMessageAttached());
      basecat.setLinkAttached(hasLinkAttached());
      basecat.setFileAttached(hasFileAttached());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        basecat.putAll(getProperties());

      basecat._baselineIID = _baselineIID;
      basecat._parentIID = _parentIID;
      basecat.setVisibleID(getVisibleID());
      basecat.setOrderNum(getOrderNum());
    return basecat;
  }

/********************************************
			
**************************/
}