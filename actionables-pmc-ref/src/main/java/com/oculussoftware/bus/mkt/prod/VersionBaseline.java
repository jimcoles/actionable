package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    VersionBaseline.java
* Date:        2/24/00
* Description: 
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
* BUG00062        Saleem Shafi    5/15/00     Altered the define() method to use the default category.
*/

public class VersionBaseline extends ProductVersion implements IVersionBaseline
{
  protected String COL_VERSIONID = "VERSIONID";
  protected String COL_VERSIONNAME = "VERSIONNAME";
  public static final String COL_BASELINENAME = "BASELINENAME";
  public static final String COL_VERSIONDESCRIPTION = "VERSIONDESCRIPTION";

  protected IIID _versionIID;
  protected IRProperty _vername;

	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public VersionBaseline() throws OculusException
	{
    super();
    TABLE = "VERSIONBASELINE";
    COL_PRODUCTID = null;
    COL_ORDERNUM = null;
    _vername = new BMProperty(this);
    _vername.setDefnObject(IDCONST.NAME.getIIDValue());
	}

  //-------------------------- Protected Methods -----------------------------

  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   "+COL_BASELINENAME+"='"+SQLUtil.primer(getName())+"' "+
           " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
           " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " ("
             +COL_OBJECTID+", "
             +COL_CLASSID+", "
             +COL_STATEID+", "
             +COL_DELETESTATE+", "
             +COL_GUID+", "
             +COL_BASELINENAME+", "
             +COL_VERSIONNAME+", "
             +COL_VERSIONID+", "
             +COL_CREATIONDATE+", "
             +COL_CREATORID+", "
             +COL_ACCESSID+", "
             +(getTargetReleaseDate() == null? "" : COL_TARGETRELEASEDATE+", ")
             +(getEstimatedReleaseDate() == null? "" : COL_ESTIMATEDRELEASEDATE+", ")
             +(getActualReleaseDate() == null? "" : COL_ACTUALRELEASEDATE+", ")
             +COL_MESSAGEATTACHED+", "
             +COL_FILEATTACHED+", "
             +COL_LINKATTACHED+
           ") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
            +getDefnObject().getIID().getLongValue()+","
            +getStateObject().getIID().getLongValue()+","
            +getDeleteState().getIntValue()+", "
            +"'"+getGUID().toString()+"',"
            +"'"+SQLUtil.primer(getName())+"',"
            +"'"+getVersionName()+"',"
            +getProductVersionObject().getIID().getLongValue()+","
            +"'"+getCreationDate().toString()+"',"
            +getCreatorIID().getLongValue()+","
            +getAccessIID().getLongValue()+","
            +(getTargetReleaseDate() == null? "" : "'"+getTargetReleaseDate()+"', ")
            +(getEstimatedReleaseDate() == null? "" : "'"+getEstimatedReleaseDate()+"', ")
            +(getActualReleaseDate() == null? "" : "'"+getActualReleaseDate()+"', ")
            +(hasMessageAttached()?"1":"0")+","
            +(hasFileAttached()?"1":"0")+","
            +(hasLinkAttached()?"1":"0")+
           ") ";
  } 

  protected String getPSPUpdateQuery()
  //throws OculusException  // STUBBED MAK THROWS?
  {
  return " UPDATE "+TABLE+
       " SET "+COL_VERSIONDESCRIPTION+"=? "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue()+"";
  }    

  public ICategory getDefaultCategory()
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    IRConnection conn = context.getRepository().getDataConnection(context);
    IQueryProcessor qp = conn.createProcessor();
    IDataSet rs = qp.retrieve("SELECT OBJECTID FROM BCATEGORY WHERE PARENTCATID=OBJECTID AND BASELINEID="+getIID());
    if (rs.next())
    {
      long catid = rs.getLong("OBJECTID");
      IIID catIID = context.getRepository().makeReposID(catid);
      return (ICategory)context.getCRM().getCompObject(context,"BaselineCategory",catIID);
    }
    return null;
  }

  protected void load(IDataSet results)
    throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));

    setPersState(PersState.PARTIAL);
    IRepository repos = getObjectContext().getRepository();
    _guid = new GUID(results.getString(COL_GUID).trim());       // get GUID
    _iid = results.getIID();    // get IID
    _classIID = repos.makeReposID(results.getLong(COL_CLASSID)); // get class
    _stateIID = repos.makeReposID(results.getLong(COL_STATEID)); // get state
    setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE))); // get state
    setCreatorIID(repos.makeReposID(results.getLong(COL_CREATORID)));
    setAccessIID(repos.makeReposID(results.getLong(COL_ACCESSID)));
    setCreationDate(results.getTimestamp(COL_CREATIONDATE));
    setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
    setFileAttached(results.getBoolean(COL_FILEATTACHED));
    setLinkAttached(results.getBoolean(COL_LINKATTACHED)); 
    _deleteState = DeleteState.getInstance(results.getInt(COL_DELETESTATE));
  

    if (COL_PRODUCTID != null)
      _productIID = new SequentialIID(results.getLong(COL_PRODUCTID));
    if (COL_ORDERNUM != null)
      setOrderNum(results.getInt(COL_ORDERNUM));
    setName(results.getString(COL_BASELINENAME));
    setTargetReleaseDate(results.getTimestamp(COL_TARGETRELEASEDATE));
    setEstimatedReleaseDate(results.getTimestamp(COL_ESTIMATEDRELEASEDATE));
    setActualReleaseDate(results.getTimestamp(COL_ACTUALRELEASEDATE));
    
    _versionIID = new SequentialIID(results.getLong(COL_VERSIONID));
    setVersionName(results.getString(COL_VERSIONNAME));
    setDescription(results.getString(COL_VERSIONDESCRIPTION));
  }



//----------------- IProductBaseline Methods ------------------------------------
  public ICategory createNewCategory()
    throws OculusException
  {
    IIID classIID = IDCONST.BASELINECATEGORY.getIIDValue();
    IBaselineCategory newBaseCat = null;
    IRClass baseCatClass = null;
    
    baseCatClass = (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID);
    newBaseCat = (IBaselineCategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategory",(IDataSet)null,true);
    newBaseCat.setDefnObject(baseCatClass);
    
    newBaseCat.setVersionBaselineObject(this);
    newBaseCat.setParentObject(newBaseCat);
    
    return newBaseCat;
  }

  public void define()
    throws OculusException
  {
    if (_versionIID == null)
      throw new OculusException("Attempt to define a baseline without setting the productversion");
    IProductVersion thisVer = getProductVersionObject();
    setMessageAttached(thisVer.hasMessageAttached());
    setFileAttached(thisVer.hasFileAttached());
    setLinkAttached(thisVer.hasLinkAttached());
    setVersionName(thisVer.getName());
    setDescription(thisVer.getDescription());
    
    
    // Saleem (BUG00062) : Started using default category.
    IBaselineCategory defaultBaseCat = (IBaselineCategory)createNewCategory();
    defaultBaseCat.define(thisVer.getDefaultCategory());
    //    ICategoryColl catList = thisVer.getCategories();
    //    while (catList.hasMoreCategories())
    //    {
    //      IBaselineCategory baseCat = (IBaselineCategory)createNewCategory();
    //      baseCat.define(catList.nextCategory());
    //    }
  }
  

  public void define(IProductVersion version)
    throws OculusException
  {
    setProductVersionObject(version);
    define();
  }


  //------------------------ MUTATORS -------------------------------
  public IVersionBaseline setProductVersionObject(IProductVersion version)
    throws OculusException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _versionIID = version.getIID();
    setVersionName(version.getName());
    setDescription(version.getDescription());
    return this;
  }
   
  public IVersionBaseline setVersionName(String vername)
    throws ORIOException
  {
    _vername.setValue(vername);
    return this;
  } 


  //------------------------ ACCESSORS -------------------------------
  public IProductVersion getProductVersionObject()
    throws OculusException
  {
    return (IProductVersion)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductVersion",_versionIID);
  }
    
  public String getVersionName()
    throws OculusException
  {
    return (String)_vername.getValue();
  }

  public ICategoryColl getCategories(boolean editable)
    throws OculusException
  {
    return (ICategoryColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategoryColl",getIID(), editable);
  }

  public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
    IGrantSet gs = acm.checkPerms(this, new IPermission[] {PermEnum.OWNER});

    return gs;
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
    VersionBaseline base = null;
      base = new VersionBaseline();
      base.setIID(getIID());
      base.setObjectContext(getObjectContext());
      base.setPersState(getPersState());
      base._classIID = _classIID;
      base._stateIID = _stateIID;
      base._deleteState = _deleteState;
      base.setName(getName());
      base.setDescription(getDescription());
      base._creatorIID = _creatorIID;
      base._accessIID = _accessIID;
      base.setCreationDate(getCreationDate());
      base.setMessageAttached(hasMessageAttached());
      base.setLinkAttached(hasLinkAttached());
      base.setFileAttached(hasFileAttached());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        base.putAll(getProperties());

      base._versionIID = _versionIID;
      base.setVersionName(getVersionName());
      base.setTargetReleaseDate(getTargetReleaseDate());
      base.setEstimatedReleaseDate(getEstimatedReleaseDate());
      base.setActualReleaseDate(getActualReleaseDate());
    return base;
  }

/********************************************
			
**************************/
}