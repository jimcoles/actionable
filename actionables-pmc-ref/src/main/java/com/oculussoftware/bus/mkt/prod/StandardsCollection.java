package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.ui.*;


import java.sql.*;
import java.util.*;

/**
* Filename:    StandardsCollection.java
* Date:        
* Description: Provides the functionality for a basic StandardsCollection.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* BUG00701        Cuihua Zhang    6/15/2000   recoded recover() and delete().
* ---             Cuihua Zhang    6/16/2000   added getStdFeatureLinks()
* PRB02562 BUG02578 BUG02623  Cuihua Zhang 9/18/2000  override the canEditAllRequiredFields() by return true
* ____            Cuihua Zhang    9/18/2000    commented out the method added this morning public boolean canEditAllRequiredFields()

*/


public class StandardsCollection extends BusinessObject implements IStandardsCollection
{
	//static public long CLASSID = IDCONST.StandardsCollection;
	public static final String COL_PARENTCOLLID  = "PARENTCOLLID";
  protected String COL_AUTOREVISION = "AUTOREVISION";
  protected IRProperty _autoRev;                // boolean type

  protected IIID _parobjectid;
  protected IIID _stdcollid;
  //protected IIID _stdcollid;

//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public StandardsCollection() throws OculusException
	{
	super();
	COL_GUID = "GUID";
	TABLE = "STANDARDSCOLLECTION";
	_autoRev = new BMProperty(this);
	}
//----------------- IPoolable Methods ------------------------------------
	/**
	*	Returns a copy of the current StandardsCollection object.
	*
	* Note: The exceptions are being withheld because this method overrides
	* the one in Object().  Consider using a different method name since it
	* doesn't look like we're taking advantage of Cloneable.
	*/
	public Object dolly() throws OculusException
	{
		StandardsCollection  stdcollection = null;
			stdcollection = new StandardsCollection();
			stdcollection.setIID(getIID());
	    stdcollection.setObjectContext(getObjectContext());
	    stdcollection.setPersState(getPersState());
	    stdcollection._parobjectid = _parobjectid; 
	    stdcollection._classIID = _classIID;
	    stdcollection._stateIID = _stateIID;
      stdcollection.setDeleteState(getDeleteState());
		  stdcollection.setName(getName());
		  stdcollection.setDescription(getDescription());
	    stdcollection._creatorIID = _creatorIID;
//	    stdcollection.setAutoRevision(getAutoRevision());
	    stdcollection.setCreationDate(getCreationDate());
	    stdcollection.setMessageAttached(hasMessageAttached());
	    stdcollection.setLinkAttached(hasLinkAttached());
	    stdcollection.setFileAttached(hasFileAttached());
	    if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
         stdcollection.putAll(getProperties());
	    stdcollection._accessIID = _accessIID;
		return stdcollection;
	}
  
	
	public IStandardsCollection createNewStdCollection()
	throws OculusException
  {
	    IStandardsCollection newStdColl;
	    IIID classIID = IDCONST.STANDARDSCOLLECTION.getIIDValue();

	    newStdColl = (IStandardsCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"StandardsCollection",(IDataSet)null,true);
	    newStdColl.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
	    newStdColl.setParentObject(this);
	
	    return newStdColl;
  }  
  
  
  public IFeature associateFeature(IFeature feature)
  throws OculusException
  {
    IStdFeatureLink stdLink = null;
    IIID classIID = IDCONST.STDFEATURELINK.getIIDValue();
    
    stdLink = (IStdFeatureLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLink",(IDataSet)null,true);
    stdLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    stdLink.setFeatureObject(feature);
    stdLink.setStdCollectionObject(this);
    //feature.setFeatureCategoryLinkObject(catLink);
    
    
    return feature;
  }  

  
  public IFeature createNewFeature()
  throws OculusException
  {
    IFeature newFeat;
    IIID classIID = IDCONST.FEATURE.getIIDValue();

    newFeat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",(IDataSet)null,true);
    newFeat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    newFeat.isStandard(true);
    associateFeature(newFeat);

    return newFeat;
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
    return (IFeatureColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureColl",args, editable);
  }
  
  public IStdFeatureLinkColl getStdFeatureLinks()
    throws OculusException
  {
    return getStdFeatureLinks(false);
  }
 
  public IStdFeatureLinkColl getStdFeatureLinks(boolean editable)
    throws OculusException
  {
    return (IStdFeatureLinkColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLinkColl",getIID(), editable);
  }


 
  //----------------- IPersistable Methods ------------------------------------

  /**
  * Marks the bo as deleted.
  */

 
  
  public IPersistable delete()
  throws OculusException
  {
  
    IStdFeatureLinkList deletedstdfeatlinks = (IStdFeatureLinkList)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLinkDeletedList", getIID(), true);
    while (deletedstdfeatlinks.hasMoreStdFeatureLink())
    {
      IStdFeatureLink featlink = deletedstdfeatlinks.nextStdFeatureLink();
      IFeature feat = featlink.getFeatureObject();
      featlink.delete();
      //if (feat.isStandard() && !feat.hasFeatureCategoryLinks() && !feat.hasStdFeatureLinks())
        //feat.delete();
    }//end while
    
    
      
  
    IStandardsCollectionList deletedStd = (IStandardsCollectionList)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdCollectionDeletedList", getIID(), true);
    while (deletedStd.hasMoreStandardsCollection())
      deletedStd.nextStandardsCollection().delete();

    ChildIndexer.removeChild(ChildIndexer.STDCOLL, getObjectContext(), getParObjectIID().getLongValue(), getIID().getLongValue());

  
    super.delete();  
  
    return this;
  }  

	public IPersistable save() throws OculusException
	{
		boolean isnew = getPersState().equals(PersState.NEW);
    super.save();
		if (isnew) {
    	ChildIndexer.addChild(ChildIndexer.STDCOLL, getObjectContext(), getParObjectIID().getLongValue(),getIID().getLongValue());
    }
    // NOTE: as of 9/22/00 std colls can not be moved so no need to handle that case.
    return this;
	}
 
  public IBusinessObject softDelete()
    throws OculusException
  { 
    
    
    IStandardsCollectionColl stdColl = getStdCollection(true);
    while (stdColl.hasMoreStandardsCollection())
    {
      IStandardsCollection stdc = stdColl.nextStandardsCollection();
      IStdFeatureLinkColl stdfc = stdc.getStdFeatureLinks(true);
      while (stdfc.hasMoreStdFeatureLink())
      {
        IStdFeatureLink stdf = (IStdFeatureLink) stdfc.next();
        stdf.softDelete();
      }
      stdc.softDelete();
    }
    
    IStdFeatureLinkColl stdfcc = getStdFeatureLinks(true);
    while (stdfcc.hasMoreStdFeatureLink())
      {
        IStdFeatureLink stdff = (IStdFeatureLink) stdfcc.next();
        //IFeature feat = stdff.getFeatureObject();
        //System.out.println("id = " + feat.getIID());
        stdff.softDelete();
        //if (feat.isStandard() && !feat.hasFeatureCategoryLinks() && !feat.hasStdFeatureLinks())
          //feat.softDelete();

        
      }
    super.softDelete();
    return this;
  }
  
  
  
  
   

  

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    
    IStdFeatureLinkList featlink = (IStdFeatureLinkList)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdFeatureLinkDeletedList",getIID(), true);
    while (featlink.hasNext())
      featlink.nextStdFeatureLink().recover();
    
    IStandardsCollectionList stdc = (IStandardsCollectionList)getObjectContext().getCRM().getCompObject(getObjectContext(),"StdCollectionDeletedList",getIID(),true);
    while (stdc.hasMoreStandardsCollection())
    {  
       IStandardsCollection std = stdc.nextStandardsCollection();
       std.recover();
    }
    return this;
}


//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_NAME))
		   return _name;
	  if (key.equals(LABEL_DESCRIPTION))
		   return _description;
//	  if (key.equals(LABEL_AUTOREVISION))
	//	   return _autoRev;
	  else
		   return super.get(key);
	}
	else
	  return null;
  }  
  //------------------------ ACCESSORS -------------------------------
  
  /*public boolean getAutoRevision()
	throws ORIOException
  {
	if (_autoRev.getValue() != null)
	  return ((Boolean)_autoRev.getValue()).booleanValue();
	else
	  return false;
  }
  */
  
//----------------- IProduct Methods ------------------------------------
	/** I don't know what this does */
  
  
  public ISet getBranches()
 	{
 		return null;
	}
  
	protected String getCreateQuery()
	throws OculusException
  {
	return "INSERT INTO "+TABLE+" "+
		   " ("+COL_OBJECTID+", "
		       +COL_PARENTCOLLID+", "
			     +COL_CLASSID+", "
			     +COL_STATEID+", "
           +COL_DELETESTATE+", "
			     +COL_GUID+", "
			     +COL_NAME+", "
           //+COL_AUTOREVISION+", "
			     +COL_CREATIONDATE+", "
			     +COL_CREATORID+", "
			     +COL_ACCESSID+", "
			     +COL_MESSAGEATTACHED+", "
			     +COL_FILEATTACHED+", "
			     +COL_LINKATTACHED+
		   ") "+
		   " VALUES "+
		   " ("+getIID().getLongValue()+","
		       +getParentObject().getIID().getLongValue()+","
			     +getDefnObject().getIID().getLongValue()+","
			     +getStateObject().getIID().getLongValue()+","
           +getDeleteState().getIntValue()+","
			     +"'"+getGUID().toString()+"',"
			     +"'"+SQLUtil.primer(getName())+"',"
			     +"'"+getCreationDate().toString()+"',"
			     +getCreatorIID().getLongValue()+","
			     +getAccessIID().getLongValue()+","
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
		        "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
		        " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
		        " , "+COL_PARENTCOLLID+"= "+getParentObject().getIID().getLongValue()+           
            " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		        " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		        " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		        " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		        " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  
  public IStandardsCollectionColl getStdCollection()
	throws OculusException
  {
	   return getStdCollection(false);
  }  
  
  public IStandardsCollectionColl getStdCollection(boolean editable)
	throws OculusException
  {  
  
	   
	//   	 System.out.println("got here?");
	   return (IStandardsCollectionColl)getChildCollection(editable);

  }  
  
  
  public IIID getParObjectIID() throws ORIOException
  { 
  	 return _parobjectid; 
  
  }
  
//  public boolean canEditAllRequiredFields()
//    throws OculusException
//  {return true;}
  
  
  protected void load(IDataSet results)
	throws OculusException
  {
	   if (results.getIID() == null)
	      results.setIID(results.getLong(COL_OBJECTID));
	   
	   super.load(results);
	   _parobjectid = getObjectContext().getRepository().makeReposID(results.getLong(COL_PARENTCOLLID));
	   setName(results.getString(COL_NAME));
	   setDescription(results.getString(COL_DESCRIPTION));
     //setAutoRevision(results.getBoolean(COL_AUTOREVISION));
	 //  setAutoRevision(true);
  }  
  
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
	      if (key.equals(LABEL_NAME))
		       setName((String)property.getValue());
	      if (key.equals(LABEL_DESCRIPTION))
		       setDescription((String)property.getValue());
//	      if (key.equals(LABEL_AUTOREVISION))
	//	       setAutoRevision(((Boolean)property.getValue()).booleanValue());
	      else
		       super.put(key,value);
	   }
  }  
  
  //------------------------ MUTATORS -------------------------------
  /*public IStandardsCollection setAutoRevision(boolean autoRev)
	throws ORIOException
  {
	   _autoRev.setValue(new Boolean(autoRev));
	   return this;
  }  
  */
  public IStandardsCollection setParentObject()
	throws ORIOException
  {
     if (getPersState().equals(PersState.UNMODIFIED))
        setPersState(PersState.MODIFIED);
     _parobjectid = getIID();
     //System.out.println("See if it is here" + _parobjectid.getLongValue());
     return this;
  }

  
  public IStandardsCollection setParentObject(IStandardsCollection stdcoll)
	throws ORIOException
  {
     if (getPersState().equals(PersState.UNMODIFIED))
        setPersState(PersState.MODIFIED);
     _parobjectid = stdcoll.getIID();
     return this;
  }
  
  public IStandardsCollection setParObjectIID(IIID piid) throws ORIOException
  {
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parobjectid = piid;
    return this;
  }//

  
  public IRObject getParentObject(boolean editable)
	throws OculusException
  {
	  if (_parobjectid == null)
	  	_parobjectid = getIID();
  	return (IStandardsCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"StandardsCollection",_parobjectid,editable);
  }

  
  
  public IRCollection getChildCollection(boolean editable)
    throws OculusException
  {
    return (IRCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"StandardsCollectionColl",getIID(),editable);
  }
  

  public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
    IGrantSet grant = acm.checkReposPerms(new IPermission[] {PermEnum.SCOLL_VIEW});
    IGrantSet gs = acm.checkPerms(this, new IPermission[]
      {PermEnum.COLL_VIEW,PermEnum.COLL_EDIT,PermEnum.SCOLL_ADD,PermEnum.OWNER, PermEnum.SUBCOLL_ADD});
    gs.addAll(grant);

    return gs;
  }


  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    
    if (module.equals(ModuleKind.ACCOLADES))
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkReposPerms(new IPermission[] {PermEnum.SCOLL_VIEW});
        grant.addAll(acm.checkPerms(this, new IPermission[] {PermEnum.COLL_VIEW}));
      }
      
      if (grant.contains(PermEnum.SCOLL_VIEW))
        visible = true;
    }
    return visible;
  }
}