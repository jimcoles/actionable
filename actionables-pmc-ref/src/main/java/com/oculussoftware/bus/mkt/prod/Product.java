package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.system.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.ui.*;
import com.oculussoftware.api.busi.common.process.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Product.java
* Date:        
* Description: Provides the functionality for a basic product.
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
*	---							Saleem Shafi		2/4/00			Implemented the get and setStateObject()
*																							methods, as well as adding the getAllTransitions()
*																							and getEnabledTransitions() methods.
*																							Went back to the idea of having actual name and desc
*																							variables instead of putting them in the PropertyMap.
*																							Altered save() and load() to use the new database.
* ---							Saleem Shafi		2/5/00			Added IRStateMachine stuff.
* ---             Saleem Shafi    2/10/00     Started using the BusinessObject as an abstract base class.
* ---             Saleem Shafi    2/14/00     Implemented a getVersions() method.
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
*/
public class Product extends BusinessObject implements IProduct
{
  protected String COL_PROJECTID = "PROJECTID";
  protected String COL_AUTOREVISION = "AUTOREVISION";

  protected IRProperty _autoRev;                // boolean type

/********************************************

  public boolean hasVersions() throws SQLException
  {
	boolean gotem = false;
	QueryProcessor qp = null;
	ResultSet jdtRS = null; 
	try
	{
	  qp = new QueryProcessor(conn);
	  // Query the database for the name
	  jdtRS = qp.retrieve(" SELECT "+Version.col_ID+
						  " FROM "+Version.TABLE+
						  " WHERE "+Version.col_ProductID+"="+id+" AND "+Version.col_State+">0 AND "+Version.col_ID+">0");
	  if (jdtRS.next())
		gotem = true;
	}//end try
	finally{if(qp!=null)qp.close();}
	return gotem;
  }

**************************/
	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public Product() throws OculusException
	{
	super();
	COL_GUID = "GUID";
	TABLE = "PRODUCT";
	_autoRev = new BMProperty(this);  
  _autoRev.setDefnObject(IDCONST.AUTOREV.getIIDValue());
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
		Product prod = null;
			prod = new Product();
			prod.setIID(getIID());
	  prod.setObjectContext(getObjectContext());
	  prod.setPersState(getPersState());
	  prod._classIID = _classIID;
	  prod._stateIID = _stateIID;
    prod.setDeleteState(getDeleteState());
		prod.setName(getName());
		prod.setDescription(getDescription());
	  prod._creatorIID = _creatorIID;
	  prod.setAutoRevision(getAutoRevision());
	  prod.setCreationDate(getCreationDate());
	  prod.setMessageAttached(hasMessageAttached());
	  prod.setLinkAttached(hasLinkAttached());
	  prod.setFileAttached(hasFileAttached());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
		prod.putAll(getProperties());

	  prod._accessIID = _accessIID;
		return prod;
	}
  public IProductVersion createNewVersion()
	throws OculusException
  {
	IProductVersion newVer;
	IIID classIID = IDCONST.PRODUCTVERSION.getIIDValue();

	newVer = (IProductVersion)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersion",(IDataSet)null,true);
	newVer.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
	newVer.setProductObject(this);
	newVer.createDefaultCategory();
	return newVer;
  }  

  public IProduct moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException
  {
    IRState state = getStateObject();
    if (state.getIID().equals(IDCONST.PRODUCTCOMPASS.getIIDValue()))
    {
      interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.PRODUCTACTIVE.getIIDValue()),strComment);
    }
    
    if (recurse)
    {
      IProductVersionColl vers = getVersions(true);
      while (vers.hasMoreProductVersions())
        vers.nextProductVersion().moveToAccolades(strComment,true,transIID);
    }
    return this;
  }

  public String getFullTreePathString()
    throws OculusException
  {
    return getName();
  }//
  
  public IProduct moveToCompass(String strComment, boolean recurse)
    throws OculusException
  {
    return (IProduct)interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.PRODUCTCOMPASS.getIIDValue()),strComment);
  }

	public boolean nameExists()
		throws OculusException
	{
		IRConnection conn = null;
		IQueryProcessor qp = null;
		IDataSet rs = null;
		boolean found = false;
		try
		{
			conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
			qp = conn.createProcessor();
			rs = qp.retrieve("SELECT OBJECTID FROM PRODUCT WHERE NAME='"+SQLUtil.primer(getName(),250)+"' AND OBJECTID<>"+getIID());
			found = rs.next();
		}
		finally
		{
			if (qp != null) qp.close();
//			getObjectContext().getCRM().returnDatabaseConnection(conn);
		}
		return found;
	}

//----------------- IPersistable Methods ------------------------------------

  /**
  * Marks the bo as deleted.
  */
  public IPersistable delete()
	throws OculusException
  {
    //delete children 
		
		IProductVersionList deletedversions = (IProductVersionList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersionDeletedList", getIID(),true);
		while (deletedversions.hasMoreProductVersions())
	    deletedversions.nextProductVersion().delete();
			
	  IProductVersionColl versions = getVersions(true);
	  while (versions.hasMoreProductVersions())
	    versions.nextProductVersion().delete();
		//delete attachments etc.
			
	  //delete yourself
	  super.delete();	
	  return this;
  }  

  public IBusinessObject softDelete()
    throws OculusException
  { 
    super.softDelete();
    
    IProductVersionColl versions = getVersions(true);
    while (versions.hasMoreProductVersions())
      versions.nextProductVersion().softDelete();
  
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    
    IProductVersionList versions = (IProductVersionList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersionDeletedList",getIID(),true);
    while (versions.hasMoreProductVersions())
      versions.nextProductVersion().recover();
  
    return this;
  }


//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_AUTOREVISION))
		return _autoRev;
	  else
		return super.get(key);
	}
	else
	  return null;
  }  
  //------------------------ ACCESSORS -------------------------------
  public boolean getAutoRevision()
	throws OculusException
  {
	if (_autoRev.getValue() != null)
	  return ((Boolean)_autoRev.getValue()).booleanValue();
	else
	  return false;
  }  
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
			   +COL_CLASSID+", "
			   +COL_STATEID+", "
         +COL_DELETESTATE+", "
			   +COL_GUID+", "
			   +COL_NAME+", "
			   +COL_CREATIONDATE+", "
			   +COL_CREATORID+", "
			   +COL_ACCESSID+", "
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
		   " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		   " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		   " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		   " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public IProductVersionColl getVersions()
	throws OculusException
  {
	return getVersions(false);
  }  
  public IProductVersionColl getVersions(boolean editable)
	throws OculusException
  {
	return (IProductVersionColl)getChildCollection(editable);
  }  
  protected void load(IDataSet results)
	throws OculusException
  {
	if (results.getIID() == null)
	  results.setIID(results.getLong(COL_OBJECTID));
	super.load(results);
	setName(results.getString(COL_NAME));
	setDescription(results.getString(COL_DESCRIPTION));
	setAutoRevision(true);
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
	  if (key.equals(LABEL_AUTOREVISION))
		setAutoRevision(((Boolean)property.getValue()).booleanValue());
	  else
		super.put(key,value);
	}
  }  
  //------------------------ MUTATORS -------------------------------
  public IProduct setAutoRevision(boolean autoRev)
	throws ORIOException
  {
	_autoRev.setValue(new Boolean(autoRev));
	return this;
  }  
  
  
  
  
  public IRObject getParentObject(boolean editable)
    throws OculusException
  {
    return null;
  }
  
  public IRCollection getChildCollection(boolean editable)
    throws OculusException
  {
    return (IRCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersionList",getIID(),editable);
  }


  public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
    IGrantSet gsRepos = acm.checkReposPerms(new IPermission[] {PermEnum.PROD_VIEW});
    IGrantSet gs = acm.checkPerms(this, new IPermission[]
      {PermEnum.ITEM_VIEW,PermEnum.ITEM_EDIT,PermEnum.OWNER,PermEnum.OWNER,PermEnum.VERSION_ADD});
    gs.addAll(gsRepos);

    return gs;
  }

  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    
    IIID stateIID = getStateObject().getIID();
    if ((module.equals(ModuleKind.ACCOLADES) &&
       (!stateIID.equals(IDCONST.PRODUCTCOMPASS.getIIDValue()) && (stateIID.equals(IDCONST.PRODUCTACTIVE.getIIDValue()) || (settings.getArchived() && stateIID.equals(IDCONST.PRODUCTARCHIVED.getIIDValue())))))
       ||
       (module.equals(ModuleKind.COMPASS) &&
       ((stateIID.equals(IDCONST.PRODUCTACTIVE.getIIDValue()) || stateIID.equals(IDCONST.PRODUCTCOMPASS.getIIDValue())) || (settings.getArchived() && stateIID.equals(IDCONST.PRODUCTARCHIVED.getIIDValue())))))
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkReposPerms(new IPermission[] {PermEnum.PROD_VIEW});
        grant.addAll(acm.checkPerms(this, new IPermission[] {PermEnum.ITEM_VIEW}));
      }
      
      if (grant.contains(PermEnum.ITEM_VIEW) || grant.contains(PermEnum.PROD_VIEW))
        visible = true;
    }
    return visible;
  }

}